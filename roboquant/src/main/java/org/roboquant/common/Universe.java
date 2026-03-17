package org.roboquant.common;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A Universe represents a collection of assets.
 * Where it differs from regular collections, is that the assets that belong to a collection can change over time.
 * So the assets in the collection at time t can be different from the assets in the collection at time t+1.
 */
public interface Universe {

    /**
     * Return the list of assets in this universe at the given point in time.
     */
    List<Asset> getAssets(Instant time);

    /**
     * Factory for standard universes.
     */
    class Factory {
        private static Universe sp500;

        private Factory() {
        }

        /**
         * Return the universe of all the S&P 500 stocks.
         */
        public static synchronized Universe getSp500() {
            if (sp500 == null) {
                sp500 = new SP500Universe();
            }
            return sp500;
        }
    }

    /**
     * SP500 asset collection.
     */
    class SP500Universe implements Universe {

        private final LocalDate startSP500 = LocalDate.parse("1960-01-01");
        private final List<AssetEntry> assets;
        private static final String fileName = "/sp500.csv";

        public SP500Universe() {
            this.assets = new ArrayList<>();
            loadAssets();
        }

        private void loadAssets() {
            try {
                java.io.InputStream stream = SP500Universe.class.getResourceAsStream(fileName);
                if (stream == null) {
                    throw new RoboquantException("Couldn't find file " + fileName);
                }

                try {
                    byte[] bytes = stream.readAllBytes();
                    String content = new String(bytes, java.nio.charset.StandardCharsets.UTF_8);
                    
                    // Simple CSV parsing
                    String[] lines = content.split("\n");
                    if (lines.length > 0) {
                        // Skip header
                        for (int i = 1; i < lines.length; i++) {
                            String line = lines[i].trim();
                            if (line.isEmpty()) continue;
                            
                            String[] parts = line.split(";");
                            if (parts.length >= 2) {
                                String symbol = parts[0].trim();
                                String dateStr = parts.length > 1 ? parts[1].trim() : "";
                                
                                Asset asset = new Stock(symbol, Currency.USD);
                                LocalDate startDate = dateStr.isEmpty() ? startSP500 : LocalDate.parse(dateStr);
                                Instant start = startDate.atTime(0, 0).atZone(Exchange.US.getZoneId()).toInstant();
                                Timeframe timeframe = new Timeframe(start, Timeframe.MAX);
                                
                                assets.add(new AssetEntry(asset, timeframe));
                            }
                        }
                    }
                } finally {
                    stream.close();
                }
            } catch (Exception e) {
                throw new RoboquantException("Failed to load SP500: " + e.getMessage(), e);
            }
        }

        @Override
        public List<Asset> getAssets(Instant time) {
            List<Asset> result = new ArrayList<>();
            for (AssetEntry entry : assets) {
                if (entry.timeframe.contains(time)) {
                    result.add(entry.asset);
                }
            }
            return result;
        }

        private static class AssetEntry {
            final Asset asset;
            final Timeframe timeframe;

            AssetEntry(Asset asset, Timeframe timeframe) {
                this.asset = asset;
                this.timeframe = timeframe;
            }
        }
    }
}
