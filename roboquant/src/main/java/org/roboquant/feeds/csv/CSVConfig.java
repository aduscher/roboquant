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

import org.roboquant.common.*;
import org.roboquant.common.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
import java.util.function.Function;

/**
 * Define the configuration to use when parsing CSV files.
 */
public class CSVConfig {

    private String filePattern = ".*.csv";
    private List<String> fileSkip = Collections.emptyList();
    private boolean hasHeader = true;
    private char separator = ',';
    private org.robok.feeds.csv.TimeParser timeParser = new org.robok.feeds.csv.AutoDetectTimeParser();
    private org.robok.feeds.csv.PriceParser priceParser = new org.robok.feeds.csv.PriceBarParser();
    private org.robok.feeds.csv.AssetBuilder assetBuilder = new StockBuilder();

    private Pattern pattern;
    private boolean isInitialized = false;

    private static final String CONFIG_FILE = "config.properties";
    private static final Logger logger = LoggerFactory.getLogger(org.robok.feeds.csv.CSVConfig.class);

    public CSVConfig() {
        this.pattern = Pattern.compile(filePattern);
    }

    public String getFilePattern() {
        return filePattern;
    }

    public void setFilePattern(String filePattern) {
        this.filePattern = filePattern;
        this.pattern = Pattern.compile(filePattern);
    }

    public List<String> getFileSkip() {
        return fileSkip;
    }

    public void setFileSkip(List<String> fileSkip) {
        this.fileSkip = fileSkip;
    }

    public boolean isHasHeader() {
        return hasHeader;
    }

    public void setHasHeader(boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    public org.robok.feeds.csv.TimeParser getTimeParser() {
        return timeParser;
    }

    public void setTimeParser(TimeParser timeParser) {
        this.timeParser = timeParser;
    }

    public org.robok.feeds.csv.PriceParser getPriceParser() {
        return priceParser;
    }

    public void setPriceParser(org.robok.feeds.csv.PriceParser priceParser) {
        this.priceParser = priceParser;
    }

    public org.robok.feeds.csv.AssetBuilder getAssetBuilder() {
        return assetBuilder;
    }

    public void setAssetBuilder(AssetBuilder assetBuilder) {
        this.assetBuilder = assetBuilder;
    }

    /**
     * Returns true is the provided file be included, false otherwise
     */
    public boolean shouldInclude(File file) {
        String name = file.getName();
        return file.isFile() && pattern.matcher(name).matches() && !fileSkip.contains(name);
    }

    /**
     * Process a single line and return a PriceEntry (if the line could be parsed). Otherwise, an exception will
     * be thrown.
     */
    public org.robok.feeds.csv.PriceEntry processLine(List<String> line, Asset asset) {
        Instant now = timeParser.parse(line);
        PriceItem item = priceParser.parse(line, asset);
        return new PriceEntry(now, item);
    }

    /**
     * Configure the time & price parsers based on the provided header
     */
    public synchronized void configure(List<String> header) {
        if (isInitialized) return;
        timeParser.init(header);
        priceParser.init(header);
        isInitialized = true;
    }

    /**
     * Merge a config map into this CSV config
     */
    private void merge(Map<String, String> config) {
        for (Map.Entry<String, String> entry : config.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            logger.debug("Found property key=" + key + " value=" + value);
            switch (key) {
                case "file.pattern":
                    setFilePattern(value);
                    break;
                case "file.skip":
                    setFileSkip(Arrays.asList(value.split(",")));
                    break;
            }
        }
    }

    /**
     * Returns a CSVConfig suited for parsing stooq.pl CSV files
     */
    public static org.robok.feeds.csv.CSVConfig stooq() {
        org.robok.feeds.csv.CSVConfig config = new org.robok.feeds.csv.CSVConfig();
        config.setFilePattern(".*.txt");
        config.setTimeParser(new org.robok.feeds.csv.AutoDetectTimeParser(2, Exchange.US));
        config.setAssetBuilder(name -> {
            String symbol = name;
            if (symbol.endsWith(".us.txt")) {
                symbol = symbol.substring(0, symbol.length() - 7);
            } else if (symbol.endsWith(".us")) {
                symbol = symbol.substring(0, symbol.length() - 3);
            }
            symbol = symbol.replace('-', '.').toUpperCase();
            return new Stock(symbol, Currency.USD);
        });
        return config;
    }

    /**
     * Returns a CSVConfig suited for parsing MT5 CSV files
     */
    public static org.robok.feeds.csv.CSVConfig mt5(boolean priceQuote, TimeSpan timeSpan) {
        Function<String, Asset> assetBuilder = name -> {
            String symbol = name.split("_")[0].toUpperCase();
            return new Stock(symbol, Currency.USD);
        };

        DateTimeFormatter dtf = priceQuote ? 
            DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss.SSS") :
            DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

        PriceParser pp = priceQuote ?
            new PriceQuoteParser(3, 2, -1, -1, false) :
            new org.robok.feeds.csv.PriceBarParser(2, 3, 4, 5, 6, -1, false, false, timeSpan);

        Function<List<String>, Instant> tp = columns -> {
            String text = columns.get(0) + " " + columns.get(1);
            LocalDateTime dt = LocalDateTime.parse(text, dtf);
            return dt.toInstant(ZoneOffset.UTC);
        };

        CSVConfig config = new CSVConfig();
        config.setAssetBuilder(assetBuilder::apply);
        config.setSeparator('\t');
        config.setTimeParser(tp::apply);
        config.setPriceParser(pp);
        return config;
    }

    /**
     * Returns a CSVConfig suited for parsing HistData.com ASCII CSV files
     */
    public static org.robok.feeds.csv.CSVConfig histData() {
        org.robok.feeds.csv.CSVConfig config = new org.robok.feeds.csv.CSVConfig();
        config.setPriceParser(new org.robok.feeds.csv.PriceBarParser(1, 2, 3, 4, -1, -1, false, false, null));
        config.setTimeParser(new AutoDetectTimeParser(0, Exchange.US));
        config.setSeparator(';');
        config.setHasHeader(false);
        config.setAssetBuilder(name -> {
            String symbol = name.split("_")[2];
            String[] pair = symbolToCurrencyPair(symbol);
            return new Forex(symbol, pair[1] != null ? Currency.getInstance(pair[1]) : Currency.USD);
        });
        return config;
    }

    /**
     * Returns a CSVConfig suited for parsing Yahoo Finance ASCII CSV files
     */
    public static org.robok.feeds.csv.CSVConfig yahoo(Currency currency) {
        if (currency == null) currency = Currency.USD;
        
        org.robok.feeds.csv.CSVConfig config = new org.robok.feeds.csv.CSVConfig();
        config.setPriceParser(new PriceBarParser(1, 2, 3, 4, -1, -1, false, false, null));
        config.setTimeParser(columns -> Instant.parse(columns.get(0)));
        config.setAssetBuilder(name -> {
            String symbol = name.split(" ")[0].toUpperCase();
            return new Stock(symbol, currency);
        });
        return config;
    }

    /**
     * Returns a CSVConfig suited for parsing Kraken CSV trade files.
     */
    public static org.robok.feeds.csv.CSVConfig kraken() {
        org.robok.feeds.csv.CSVConfig config = new org.robok.feeds.csv.CSVConfig();
        config.setPriceParser(new TradePriceParser(1, 2, false));
        config.setTimeParser(columns -> Instant.ofEpochSecond(Long.parseLong(columns.get(0))));
        config.setHasHeader(false);
        config.setAssetBuilder(name -> {
            String[] pair = symbolToCurrencyPair(name);
            return new Crypto(name, pair[1] != null ? Currency.getInstance(pair[1]) : Currency.USD);
        });
        return config;
    }

    /**
     * Read a CSV configuration from a path.
     */
    public static org.robok.feeds.csv.CSVConfig fromFile(String path) {
        org.robok.feeds.csv.CSVConfig result = new org.robok.feeds.csv.CSVConfig();
        Path cfgPath = Path.of(path, CONFIG_FILE);
        File file = cfgPath.toFile();
        Properties prop = new Properties();
        if (file.exists()) {
            logger.debug("Found configuration file " + file);
            try {
                prop.load(new java.io.FileInputStream(file));
                Map<String, String> map = new HashMap<>();
                prop.forEach((k, v) -> map.put(k.toString(), v.toString()));
                result.merge(map);
            } catch (java.io.IOException e) {
                logger.warn(("Failed to read config file: " + e.getMessage());
            }
        }
        return result;
    }

    private static String[] symbolToCurrencyPair(String symbol) {
        String[] result = new String[]{symbol, null};
        if (symbol.length() >= 6) {
            result[0] = symbol.substring(0, 3);
            result[1] = symbol.substring(3, 6);
        }
        return result;
    }
}
