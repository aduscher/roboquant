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

import org.robok.common.ConfigurationException;
import org.robok.common.Exchange;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneOffset;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Interface for time parsers that can use an Exchange to support parsing logic like the time zone or opening and
 * closing times.
 */
@FunctionalInterface
public interface TimeParser {

    /**
     * Initialize the parser based on the header. Default is to do nothing.
     */
    default void init(List<String> header) {
        // Default implementation is to do nothing
    }

    /**
     * Return an Instant given the provided line of strings
     */
    Instant parse(List<String> line);
}

/**
 * Internal interface for auto-detecting parsers
 */
interface AutoDetectParser {
    Instant parse(String text);
}

/**
 * Datetime parser that parses local date-time
 */
class LocalTimeParser implements AutoDetectParser {

    private final DateTimeFormatter dtf;
    private final Exchange exchange;

    LocalTimeParser(String pattern, Exchange exchange) {
        this.dtf = DateTimeFormatter.ofPattern(pattern);
        this.exchange = exchange != null ? exchange : Exchange.US;
    }

    @Override
    public Instant parse(String text) {
        LocalDateTime dt = LocalDateTime.parse(text, dtf);
        return exchange.getInstant(dt);
    }
}

/**
 * Parser that parses local dates and uses the exchange closing time to determine the time.
 */
class LocalDateParser implements AutoDetectParser {

    private final DateTimeFormatter dtf;
    private final Exchange exchange;

    LocalDateParser(String pattern, Exchange exchange) {
        this.dtf = DateTimeFormatter.ofPattern(pattern);
        this.exchange = exchange != null ? exchange : Exchange.US;
    }

    @Override
    public Instant parse(String text) {
        LocalDate date = LocalDate.parse(text, dtf);
        return exchange.getClosingTime(date);
    }
}

/**
 * Auto-detect the appropriate time parser to use based on the first sample it receives.
 */
public class AutoDetectTimeParser implements org.robok.feeds.csv.TimeParser {

    private int timeColumn;
    private final Exchange exchange;
    private AutoDetectParser parser;

    private static final Pattern[] patterns = new Pattern[] {
        Pattern.compile("19\\d{6}"),
        Pattern.compile("20\\d{6}"),
        Pattern.compile("\\d{8} \\d{6}"),
        Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}Z"),
        Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"),
        Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}"),
        Pattern.compile("\\d{4}-\\d{2}-\\d{2}"),
        Pattern.compile("\\d{8} \\d{2}:\\d{2}:\\d{2}"),
        Pattern.compile("\\d{8}  \\d{2}:\\d{2}:\\d{2}"),
        Pattern.compile("-?\\d{1,19}")
    };

    public AutoDetectTimeParser() {
        this(-1, Exchange.US);
    }

    public AutoDetectTimeParser(int timeColumn, Exchange exchange) {
        this.timeColumn = timeColumn;
        this.exchange = exchange != null ? exchange : Exchange.US;
    }

    @Override
    public void init(List<String> header) {
        if (timeColumn != -1) return;
        Pattern notCapital = Pattern.compile("[^A-Z]");
        for (int index = 0; index < header.size(); index++) {
            String column = header.get(index).toUpperCase().replaceAll("[^A-Z]", "");
            switch (column) {
                case "TIME":
                case "DATE":
                case "DAY":
                case "DATETIME":
                case "TIMESTAMP":
                    timeColumn = index;
                    break;
            }
        }
    }

    @Override
    public Instant parse(List<String> line) {
        String text = line.get(timeColumn);
        if (parser == null) {
            detect(text);
        }
        return parser.parse(text);
    }

    private void detect(String sample) {
        synchronized (this) {
            if (parser != null) return;
            
            AutoDetectParser detected = null;
            String pattern = null;
            
            if (patterns[0].matcher(sample).matches() || patterns[1].matcher(sample).matches()) {
                detected = new org.robok.feeds.csv.LocalDateParser("yyyyMMdd", exchange);
                pattern = "yyyyMMdd";
            } else if (patterns[2].matcher(sample).matches()) {
                detected = new org.robok.feeds.csv.LocalTimeParser("yyyyMMdd HHmmss", exchange);
                pattern = "yyyyMMdd HHmmss";
            } else if (patterns[3].matcher(sample).matches()) {
                detected = text -> Instant.parse(text);
                pattern = "ISO";
            } else if (patterns[4].matcher(sample).matches()) {
                detected = new org.robok.feeds.csv.LocalTimeParser("yyyy-MM-dd HH:mm:ss", exchange);
                pattern = "yyyy-MM-dd HH:mm:ss";
            } else if (patterns[5].matcher(sample).matches()) {
                detected = new org.robok.feeds.csv.LocalTimeParser("yyyy-MM-dd HH:mm", exchange);
                pattern = "yyyy-MM-dd HH:mm";
            } else if (patterns[6].matcher(sample).matches()) {
                detected = new org.robok.feeds.csv.LocalDateParser("yyyy-MM-dd", exchange);
                pattern = "yyyy-MM-dd";
            } else if (patterns[7].matcher(sample).matches()) {
                detected = new org.robok.feeds.csv.LocalTimeParser("yyyyMMdd HH:mm:ss", exchange);
                pattern = "yyyyMMdd HH:mm:ss";
            } else if (patterns[8].matcher(sample).matches()) {
                detected = new org.robok.feeds.csv.LocalTimeParser("yyyyMMdd  HH:mm:ss", exchange);
                pattern = "yyyyMMdd  HH:mm:ss";
            } else if (patterns[9].matcher(sample).matches()) {
                detected = text -> Instant.ofEpochMilli(Long.parseLong(text));
                pattern = "epoch millis";
            }

            if (detected == null) {
                throw new ConfigurationException("No suitable time parser found for time=" + sample);
            }
            parser = detected;
        }
    }
}
