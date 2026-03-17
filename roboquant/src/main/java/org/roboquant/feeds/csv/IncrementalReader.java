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

import de.siegmar.fastcsv.reader.CloseableIterator;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;
import org.robok.common.Asset;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;
import java.util.List;

/**
 * Will read a line at a time of a CSV file.
 */
class IncrementalReader {

    private final Asset asset;
    private final CSVConfig config;
    private final CloseableIterator<CsvRecord> reader;
    private long errors;

    IncrementalReader(Asset asset, File file, CSVConfig config) {
        this.asset = asset;
        this.config = config;
        this.reader = CsvReader.builder()
                .fieldSeparator(config.getSeparator())
                .skipEmptyLines(true)
                .ofCsvRecord(new FileReader(file))
                .iterator();
        if (config.isHasHeader() && reader.hasNext()) {
            List<String> line = reader.next().getFields();
            config.configure(line);
        }
    }

    Asset getAsset() {
        return asset;
    }

    CSVConfig getConfig() {
        return config;
    }

    long getErrors() {
        return errors;
    }

    void setErrors(long errors) {
        this.errors = errors;
    }

    PriceEntry next() {
        while (reader.hasNext()) {
            List<String> line = reader.next().getFields();
            try {
                return config.processLine(line, asset);
            } catch (Throwable t) {
                errors++;
            }
        }
        close();
        return null;
    }

    void close() {
        try {
            reader.close();
        } catch (IOException e) {
            // ignore
        }
    }
}
