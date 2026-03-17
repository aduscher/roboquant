package org.roboquant.common;

import java.util.*;

/**
 * Base interface for all items in a feed.
 */
public interface Item {

    // --- NESTED ENUMS ---

    enum PriceItemType {
        BAR, QUOTE, TRADE, BOOK
    }

    // --- NESTED INTERFACES ---

    /**
     * PriceItem represents an Item that contains pricing information for a single Asset.
     */
    interface PriceItem extends Item {
        Asset getAsset();

        double getPrice(String type);

        default double getPrice() {
            return getPrice("DEFAULT");
        }

        default Amount getPriceAmount(String type) {
            return new Amount(getAsset().getCurrency(), getPrice(type));
        }

        default Amount getPriceAmount() {
            return getPriceAmount("DEFAULT");
        }

        double getVolume(String type);

        default double getVolume() {
            return getVolume("DEFAULT");
        }
    }

    // --- IMPLEMENTATIONS ---

    /**
     * Provides OHLCV data (Candlestick).
     */
    class PriceBar implements PriceItem {
        private final Asset asset;
        private final double[] ohlcv;
        private final TimeSpan timeSpan;

        public PriceBar(Asset asset, double[] ohlcv, TimeSpan timeSpan) {
            this.asset = asset;
            this.ohlcv = ohlcv;
            this.timeSpan = timeSpan;
        }

        public PriceBar(Asset asset, Number open, Number high, Number low, Number close, Number volume, TimeSpan timeSpan) {
            this(asset, new double[]{open.doubleValue(), high.doubleValue(), low.doubleValue(),
                    close.doubleValue(), volume.doubleValue()}, timeSpan);
        }

        @Override public Asset getAsset() { return asset; }
        public double getOpen() { return ohlcv[0]; }
        public double getHigh() { return ohlcv[1]; }
        public double getLow() { return ohlcv[2]; }
        public double getClose() { return ohlcv[3]; }
        public double getVolume() { return ohlcv[4]; }

        @Override
        public double getPrice(String type) {
            switch (type) {
                case "OPEN": return ohlcv[0];
                case "HIGH": return ohlcv[1];
                case "LOW": return ohlcv[2];
                case "TYPICAL": return (ohlcv[1] + ohlcv[2] + ohlcv[3]) / 3.0;
                case "CLOSE":
                case "DEFAULT":
                default: return ohlcv[3];
            }
        }

        @Override
        public double getVolume(String type) {
            return Math.abs(ohlcv[4]);
        }

        public void adjustClose(Number price) {
            double adj = price.doubleValue() / getClose();
            ohlcv[0] *= adj; ohlcv[1] *= adj; ohlcv[2] *= adj; ohlcv[3] *= adj;
            ohlcv[4] /= adj;
        }

        @Override
        public String toString() {
            return String.format("symbol=%s ohlcv=%s timeSpan=%s",
                    asset.getSymbol(), Arrays.toString(ohlcv), (timeSpan != null ? timeSpan : "unknown"));
        }
    }

    /**
     * Single trade price.
     */
    class TradePrice implements PriceItem {
        private final Asset asset;
        private final double price;
        private final double volume;

        public TradePrice(Asset asset, double price, double volume) {
            this.asset = asset;
            this.price = price;
            this.volume = volume;
        }

        public TradePrice(Asset asset, double price) { this(asset, price, Double.NaN); }

        @Override public Asset getAsset() { return asset; }
        @Override public double getPrice(String type) { return price; }
        @Override public double getVolume(String type) { return volume; }
    }

    /**
     * Best Bid and Offer.
     */
    class PriceQuote implements PriceItem {
        private final Asset asset;
        private final double askPrice, askSize, bidPrice, bidSize;

        public PriceQuote(Asset asset, double askPrice, double askSize, double bidPrice, double bidSize) {
            this.asset = asset;
            this.askPrice = askPrice; this.askSize = askSize;
            this.bidPrice = bidPrice; this.bidSize = bidSize;
        }

        @Override public Asset getAsset() { return asset; }

        @Override
        public double getPrice(String type) {
            switch (type) {
                case "WEIGHTED": return (askPrice * askSize + bidPrice * bidSize) / (askSize + bidSize);
                case "ASK": return askPrice;
                case "BID": return bidPrice;
                default: return (askPrice + bidPrice) / 2.0;
            }
        }

        public double getAskPrice() { return askPrice; }

        public double getBidPrice() { return bidPrice; }

        @Override
        public double getVolume(String type) {
            if ("ASK".equals(type)) return askSize;
            if ("BID".equals(type)) return bidSize;
            return (Math.abs(askSize) + Math.abs(bidSize)) / 2.0;
        }

        public double getSpread() { return (askPrice - bidPrice) / askPrice; }
    }

    /**
     * Full Order Book.
     */
    class OrderBook implements PriceItem {
        private final Asset asset;
        private final List<OrderBookEntry> asks;
        private final List<OrderBookEntry> bids;

        public OrderBook(Asset asset, List<OrderBookEntry> asks, List<OrderBookEntry> bids) {
            this.asset = asset;
            this.asks = asks;
            this.bids = bids;
        }

        @Override public Asset getAsset() { return asset; }

        public double getBestBid() { return bids.stream().mapToDouble(e -> e.limit).max().orElse(Double.NaN); }
        public double getBestOffer() { return asks.stream().mapToDouble(e -> e.limit).min().orElse(Double.NaN); }

        @Override
        public double getPrice(String type) {
            switch (type) {
                case "ASK": return getBestOffer();
                case "BID": return getBestBid();
                case "WEIGHTED":
                    double av = getVol(asks); double bv = getVol(bids);
                    return (getBestOffer() * av + getBestBid() * bv) / (av + bv);
                default: return (getBestBid() + getBestOffer()) / 2.0;
            }
        }

        @Override
        public double getVolume(String type) { return getVol(asks) + getVol(bids); }

        private double getVol(List<OrderBookEntry> entries) {
            return entries.stream().mapToDouble(e -> Math.abs(e.size)).sum();
        }

        public static class OrderBookEntry {
            public final double size;
            public final double limit;
            public OrderBookEntry(double size, double limit) { this.size = size; this.limit = limit; }
        }
    }

    /**
     * Corporate events like splits/dividends.
     */
    class CorporateItem implements Item {
        public final Asset asset;
        public final String type;
        public final double value;

        public CorporateItem(Asset asset, String type, double value) {
            this.asset = asset;
            this.type = type;
            this.value = value;
        }
    }

    /**
     * Market news container.
     */
    class NewsItems implements Item {
        public final List<NewsItem> items;
        public NewsItems(List<NewsItem> items) { this.items = items; }

        public static class NewsItem {
            public final String id, content, headline, url;
            public final List<Asset> assets;
            public final Map<String, Object> meta;

            public NewsItem(String id, List<Asset> assets, String content, String headline, String url, Map<String, Object> meta) {
                this.id = id; this.assets = assets; this.content = content;
                this.headline = headline; this.url = url; this.meta = meta;
            }
        }
    }
}