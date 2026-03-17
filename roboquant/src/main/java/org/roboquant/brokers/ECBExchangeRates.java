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

package org.roboquant.brokers;

import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;
import org.roboquant.common.Config;
import org.roboquant.common.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Currency converter that uses the exchange reference rates as published by the ECB and that are freely available at
 * ECB website (https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/index.en.html)
 *
 * It contains daily exchange rates, and with this file loaded it is possible to trade in most currencies
 * in the world. However, please note that:
 *
 * 1. The published rates are all in relationship to the Euro. So they only go back to the introduction date of
 * the Euro and don't cover earlier periods.
 * 2. This file does not cover cryptocurrencies.
 */
public class ECBExchangeRates extends TimedExchangeRates {

    private static final String DEFAULT_ECB_URL = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist.zip";
    private final Logger logger = LoggerFactory.getLogger(ECBExchangeRates.class);

    public ECBExchangeRates(String url, boolean compressed, boolean useCache) {
        super(Currency.getInstance("EUR"));
        load(url, compressed, useCache);
        logger.info("loaded conversion rates for " + getExchangeRates().size() + " currencies");
    }

    public ECBExchangeRates(String url) {
        this(url, false, false);
    }

    /**
     * Load the latest exchange rate file directly from the ECB website. This method uses cache by
     * default to avoid making unnecessary requests.
     */
    public static ECBExchangeRates fromWeb() {
        return fromWeb(true);
    }

    /**
     * Load the latest exchange rate file directly from the ECB website.
     *
     * @param useCache whether to use cache
     */
    public static ECBExchangeRates fromWeb(boolean useCache) {
        return new ECBExchangeRates(DEFAULT_ECB_URL, true, useCache);
    }

    /**
     * Load the ECB exchange rates from a local CSV file
     *
     * @param path the path to the file
     * @param compressed whether the file is compressed
     */
    public static ECBExchangeRates fromFile(String path, boolean compressed) {
        return new ECBExchangeRates("file:" + path, compressed, false);
    }

    private URL cache(URL url) throws MalformedURLException {
        String fileName = new File(url.getPath()).getName();
        long day = LocalDate.now().toEpochDay();
        Path cacheFile = Config.getHome().resolve(day + "-" + fileName);

        if (!Files.exists(cacheFile)) {
            logger.debug("Caching " + url + " as " + cacheFile);
            try {
                Files.copy(url.openStream(), cacheFile, StandardCopyOption.REPLACE_EXISTING);
            } catch (Exception e) {
                logger.error("Failed to cache file: " + e.getMessage());
            }
        }
        return cacheFile.toUri().toURL();
    }

    private void load(String urlString, boolean compressed, boolean useCache) {
        try {
            URL url = new URI(urlString).toURL();

            if (useCache && url.getProtocol().toUpperCase().startsWith("HTTP")) {
                url = cache(url);
            }

            InputStream input = url.openStream();

            if (compressed) {
                ZipInputStream zis = new ZipInputStream(input);
                ZipEntry entry = zis.getNextEntry();
                if (entry != null) {
                    logger.debug("Found file " + entry.getName() + " from " + url);
                    input = zis;
                } else {
                    logger.error("File " + urlString + " is not compressed");
                    input.close();
                    input = url.openStream();
                }
            }

            Reader inputReader = new InputStreamReader(input);
            CsvReader reader = CsvReader.builder().ofCsvRecord(inputReader);

            List<List<String>> lines = new ArrayList<>();
            for (var record : reader) {
                lines.add(((CsvRecord)record).getFields());
            }
            reader.close();

            List<String> header = lines.get(0);
            List<Currency> currencies = new ArrayList<>();
            for (int i = 1; i < header.size(); i++) {
                String c = header.get(i).trim();
                if (!c.isBlank()) {
                    currencies.add(Currency.getInstance(c));
                }
            }

            ZoneId zoneId = ZoneId.of("Europe/Brussels");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(zoneId);

            for (int lineIdx = 1; lineIdx < lines.size(); lineIdx++) {
                List<String> line = lines.get(lineIdx);
                ZonedDateTime zdt = ZonedDateTime.parse(line.get(0) + " 16:00:00", dtf);
                java.time.Instant instant = zdt.toInstant();

                for (int i = 0; i < line.size() - 1 && i < currencies.size(); i++) {
                    String rateStr = line.get(i + 1).trim();
                    try {
                        if (!rateStr.isBlank()) {
                            double v = 1.0 / Double.parseDouble(rateStr);
                            Currency k = currencies.get(i);
                            NavigableMap<java.time.Instant, Double> map = getExchangeRates().get(k);
                            if (map == null) {
                                map = new TreeMap<>();
                                getExchangeRates().put(k, map);
                            }
                            map.put(instant, v);
                        }
                    } catch (NumberFormatException e) {
                        logger.debug("Encounter number format exception for string " + rateStr, e);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load exchange rates: " + e.getMessage(), e);
        }
    }

}
