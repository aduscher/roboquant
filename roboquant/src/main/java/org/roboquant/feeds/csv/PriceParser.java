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

import org.robok.common.Asset;
import org.robok.common.TimeSpan;
import org.robok.common.PriceBar;
import org.robok.common.PriceItem;
import org.robok.common.PriceQuote;
import org.robok.common.TradePrice;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Interface for price parsers that can use a config to support parsing logic
 */
@FunctionalInterface
public interface PriceParser {

    /**
     * Initialize the parser based on the header. Default is to do nothing.
     */
    default void init(List<String> header) {
    }

    /**
     * Return a PriceItem given the provided line of strings and asset
     */
    PriceItem parse(List<String> line, Asset asset);
}

/**
 * Parse lines and create PriceBar
 */
class PriceBarParser implements org.robok.feeds.csv.PriceParser {

    private int open = -1;
    private int high = -1;
    private int low = -1;
    private int close = -1;
    private int volume = -1;
    private int adjustedClose = -1;
    private boolean priceAdjust = false;
    private boolean autodetect = true;
    private TimeSpan timeSpan;

    public PriceBarParser() {
        this(-1, -1, -1, -1, -1, -1, false, true, null);
    }

    public PriceBarParser(int open, int high, int low, int close, int volume, int adjustedClose, 
                          boolean priceAdjust, boolean autodetect, TimeSpan timeSpan) {
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.adjustedClose = adjustedClose;
        this.priceAdjust = priceAdjust;
        this.autodetect = autodetect;
        this.timeSpan = timeSpan;
    }

    private void validate() {
        if (open == -1) throw new IllegalArgumentException("No open-prices column");
        if (low == -1) throw new IllegalArgumentException("No low-prices column found");
        if (high == -1) throw new IllegalArgumentException("No high-prices column found");
        if (close == -1) throw new IllegalArgumentException("No close-prices column found");
        if (priceAdjust && adjustedClose == -1) {
            throw new IllegalArgumentException("No adjusted close prices column found");
        }
    }

    @Override
    public void init(List<String> header) {
        if (autodetect) {
            Pattern notCapital = Pattern.compile("[^A-Z]");
            for (int index = 0; index < header.size(); index++) {
                String column = header.get(index).toUpperCase().replaceAll("[^A-Z]", "");
                switch (column) {
                    case "OPEN":
                        open = index;
                        break;
                    case "HIGH":
                        high = index;
                        break;
                    case "LOW":
                        low = index;
                        break;
                    case "CLOSE":
                        close = index;
                        break;
                    case "ADJCLOSE":
                    case "ADJUSTEDCLOSE":
                        adjustedClose = index;
                        break;
                    case "VOLUME":
                    case "VOL":
                        volume = index;
                        break;
                }
            }
        }
        validate();
    }

    @Override
    public PriceBar parse(List<String> line, Asset asset) {
        double volumeValue = volume != -1 ? 
            (line.get(volume).isBlank() ? Double.NaN : Double.parseDouble(line.get(volume))) : 
            Double.NaN;
        
        PriceBar pb = new PriceBar(
            asset,
            Double.parseDouble(line.get(open)),
            Double.parseDouble(line.get(high)),
            Double.parseDouble(line.get(low)),
            Double.parseDouble(line.get(close)),
            volumeValue,
            timeSpan
        );
        
        if (priceAdjust) {
            pb.adjustClose(Double.parseDouble(line.get(adjustedClose)));
        }
        return pb;
    }
}

/**
 * Parse lines and create PriceQuote
 */
class PriceQuoteParser implements org.robok.feeds.csv.PriceParser {

    private int ask = -1;
    private int bid = -1;
    private int askVolume = -1;
    private int bidVolume = -1;
    private boolean autodetect = true;

    public PriceQuoteParser() {
        this(-1, -1, -1, -1, true);
    }

    public PriceQuoteParser(int ask, int bid, int bidVolume, int askVolume, boolean autodetect) {
        this.ask = ask;
        this.bid = bid;
        this.askVolume = askVolume;
        this.bidVolume = bidVolume;
        this.autodetect = autodetect;
    }

    private void validate() {
        if (ask == -1) throw new IllegalArgumentException("No ask-prices column");
        if (bid == -1) throw new IllegalArgumentException("No bid-prices column found");
    }

    @Override
    public void init(List<String> header) {
        if (autodetect) {
            Pattern notCapital = Pattern.compile("[^A-Z]");
            for (int index = 0; index < header.size(); index++) {
                String column = header.get(index).toUpperCase().replaceAll("[^A-Z]", "");
                switch (column) {
                    case "ASK":
                        ask = index;
                        break;
                    case "BID":
                        bid = index;
                        break;
                    case "ASKVOLUME":
                    case "ASKSIZE":
                        askVolume = index;
                        break;
                    case "BIDVOLUME":
                    case "BIDSIZE":
                        bidVolume = index;
                        break;
                }
            }
        }
        validate();
    }

    @Override
    public PriceQuote parse(List<String> line, Asset asset) {
        double askVol = askVolume != -1 ? Double.parseDouble(line.get(askVolume)) : Double.NaN;
        double bidVol = bidVolume != -1 ? Double.parseDouble(line.get(bidVolume)) : Double.NaN;
        return new PriceQuote(
            asset,
            Double.parseDouble(line.get(ask)),
            askVol,
            Double.parseDouble(line.get(bid)),
            bidVol
        );
    }
}

/**
 * Parse lines and create Trade Prices
 */
class TradePriceParser implements org.robok.feeds.csv.PriceParser {

    private int price = -1;
    private int volume = -1;
    private boolean autodetect = true;

    public TradePriceParser() {
        this(-1, -1, true);
    }

    public TradePriceParser(int price, int volume, boolean autodetect) {
        this.price = price;
        this.volume = volume;
        this.autodetect = autodetect;
    }

    private void validate() {
        if (price == -1) throw new IllegalArgumentException("No price column");
    }

    @Override
    public void init(List<String> header) {
        if (autodetect) {
            Pattern notCapital = Pattern.compile("[^A-Z]");
            for (int index = 0; index < header.size(); index++) {
                String column = header.get(index).toUpperCase().replaceAll("[^A-Z]", "");
                switch (column) {
                    case "PRICE":
                        price = index;
                        break;
                    case "VOLUME":
                        volume = index;
                        break;
                }
            }
        }
        validate();
    }

    @Override
    public TradePrice parse(List<String> line, Asset asset) {
        double vol = volume != -1 ? Double.parseDouble(line.get(volume)) : Double.NaN;
        return new TradePrice(
            asset,
            Double.parseDouble(line.get(price)),
            vol
        );
    }
}
