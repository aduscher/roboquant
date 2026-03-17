/*
 * Copyright 2020-2026 Neural Layer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.roboquant.feeds.csv;

import de.siegmar.fastcsv.reader.CsvReader;
import org.robok.common.Asset;
import org.robok.common.ParallelJobs;
import org.robok.feeds.HistoricPriceFeed;
import org.robok.feeds.csv.CSVConfig;
import org.robok.feeds.csv.PriceEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Read historic price data from CSV files in a directory. It will traverse down if it finds subdirectories. This
 * implementation is thread safe and can be shared across multiple runs at the same time.
 *
 * This implementation will store all the data in memory, using Double for the prices.
 */
public class CSVFeed extends HistoricPriceFeed {

    private final Logger logger = LoggerFactory.getLogger(org.robok.feeds.csv.CSVFeed.class);
    private final org.robok.feeds.csv.CSVConfig config;
    private final String pathStr;

    public CSVFeed(String pathStr, org.robok.feeds.csv.CSVConfig config) {
        this(pathStr, config, false);
    }

    public CSVFeed(String pathStr, org.robok.feeds.csv.CSVConfig config, boolean configure) {
        this.pathStr = pathStr;
        this.config = config != null ? config : org.robok.feeds.csv.CSVConfig.fromFile(pathStr);
        
        Path path = Path.of(pathStr);
        if (!Files.exists(path) || (!Files.isDirectory(path) && !Files.isRegularFile(path))) {
            throw new IllegalArgumentException(path + " does not exist");
        }

        if (configure) {
            config.configure(new ArrayList<>());
        }

        readFiles(path);
        logger.info(() -> "events=" + getTimeline().size() + " assets=" + getAssets().size() + " timeframe=" + getTimeframe());
    }

    public CSVFeed(String pathStr) {
        this(pathStr, CSVConfig.fromFile(pathStr));
    }

    /**
     * Read a path (directory or single file) and all its descendants and return the found CSV files
     */
    private List<File> readPath(Path path) {
        File entry = path.toFile();
        if (entry.isFile()) {
            return List.of(entry);
        } else {
            return java.io.FileWalker.getDefault()
                .onPreVisitDirectory((d, s) -> java.io.FileVisitResult.CONTINUE)
                .onVisitFile(f -> config.shouldInclude(f) ? java.io.FileVisitResult.CONTINUE : java.io.FileVisitResult.SKIP_SIBLINGS)
                .walk(entry)
                .map(File::toPath)
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
        }
    }

    private List<File> readPathSimple(Path path) {
        File entry = path.toFile();
        List<File> result = new ArrayList<>();
        
        if (entry.isFile()) {
            result.add(entry);
            return result;
        }
        
        collectFiles(entry, result);
        return result;
    }

    private void collectFiles(File dir, List<File> result) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    collectFiles(file, result);
                } else if (config.shouldInclude(file)) {
                    result.add(file);
                }
            }
        }
    }

    /**
     * Read and parse CSV files in parallel from the path
     */
    private void readFiles(Path path) {
        List<File> files = readPathSimple(path);
        if (files.isEmpty()) {
            logger.warn(() -> "Found no CSV files at " + path);
            return;
        }
        logger.debug(() -> "Found " + files.size() + " CSV files");

        ParallelJobs jobs = new ParallelJobs();
        for (File file : files) {
            Asset asset = config.getAssetBuilder().build(getFileNameWithoutExtension(file.getName()));
            jobs.add(() -> {
                List<org.robok.feeds.csv.PriceEntry> steps = readFile(asset, file);
                for (org.robok.feeds.csv.PriceEntry step : steps) {
                    add(step.getTime(), step.getPrice());
                }
            });
        }
        jobs.joinAll();
    }

    private String getFileNameWithoutExtension(String name) {
        int lastDot = name.lastIndexOf('.');
        return lastDot > 0 ? name.substring(0, lastDot) : name;
    }

    private List<org.robok.feeds.csv.PriceEntry> readFile(Asset asset, File file) {
        List<org.robok.feeds.csv.PriceEntry> result = new ArrayList<>();
        int errors = 0;
        boolean isHeader = config.isHasHeader();

        try (CsvReader reader = CsvReader.builder()
                .fieldSeparator(config.getSeparator())
                .skipEmptyLines(true)
                .ofCsvRecord(new FileReader(file))) {
            
            for (var row : reader) {
                if (isHeader) {
                    config.configure(row.getFields());
                    isHeader = false;
                } else {
                    try {
                        PriceEntry step = config.processLine(row.getFields(), asset);
                        result.add(step);
                    } catch (Throwable t) {
                        logger.debug(t, () -> asset.getSymbol() + " " + row);
                        errors++;
                    }
                }
            }
        } catch (IOException e) {
            logger.warn(() -> "Error reading file " + file + ": " + e.getMessage());
        }

        if (errors > 0) {
            logger.warn(() -> "Skipped " + errors + " lines due to errors in " + file);
        }
        return result;
    }
}
