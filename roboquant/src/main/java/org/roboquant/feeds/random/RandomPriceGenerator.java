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

package org.roboquant.feeds.random;

import org.robok.common.Asset;
import org.roboquant.common.*;
import org.robok.common.CurrencyK;
import org.robok.common.PriceItem;
import org.robok.common.PriceItemType;
import org.robok.common.Stock;
import org.robok.common.TimeSpan;

import java.util.*;
import java.util.random.RandomGenerator;

class RandomPriceGenerator {

    private final List<org.robok.common.Asset> assets;
    private final double priceChange;
    private final int volumeRange;
    private final TimeSpan timeSpan;
    private final PriceItemType priceType;
    private final RandomGenerator random;
    private final double[] prices;

    RandomPriceGenerator(List<org.robok.common.Asset> assets, double priceChange, int volumeRange,
                         TimeSpan timeSpan, PriceItemType priceType, int seed) {
        this.assets = assets;
        this.priceChange = priceChange;
        this.volumeRange = volumeRange;
        this.timeSpan = timeSpan;
        this.priceType = priceType;
        this.random = RandomGenerator.getDefault();
        
        // Create initial prices for all assets between 50 and 500
        this.prices = new double[assets.size()];
        for (int i = 0; i < assets.size(); i++) {
            prices[i] = 50.0 + random.nextDouble() * 450.0;
        }
    }

    private double nextPrice(double currentPrice) {
        return currentPrice * (1.0 + random.nextDouble(-priceChange, priceChange));
    }

    private double[] createOhlc(double price) {
        double[] v = new double[4];
        for (int i = 0; i < 4; i++) {
            v[i] = nextPrice(price);
        }
        Arrays.sort(v);
        return v;
    }

    private PriceItem priceBar(org.robok.common.Asset asset, double price) {
        double[] v = createOhlc(price);
        int volume = volumeRange / 2 + random.nextInt(volumeRange);
        
        if (random.nextBoolean()) {
            return new org.robok.common.PriceBar(asset, v[1], v[3], v[0], v[2], volume, timeSpan);
        } else {
            return new org.robok.common.PriceBar(asset, v[2], v[3], v[0], v[1], volume, timeSpan);
        }
    }

    private PriceItem priceQuote(org.robok.common.Asset asset, double price) {
        double midPoint = nextPrice(price);
        double volume = (volumeRange / 2 + random.nextInt(volumeRange));
        return new org.robok.common.PriceQuote(asset, midPoint * 0.99, volume, midPoint * 1.01, volume);
    }

    private PriceItem tradePrice(org.robok.common.Asset asset, double price) {
        int volume = volumeRange / 2 + random.nextInt(volumeRange);
        return new org.robok.common.TradePrice(asset, price, volume);
    }

    List<PriceItem> next() {
        List<PriceItem> result = new ArrayList<>();
        for (int idx = 0; idx < assets.size(); idx++) {
            org.robok.common.Asset asset = assets.get(idx);
            double lastPrice = prices[idx];
            double price = Math.max(nextPrice(lastPrice), priceChange * 2.0);
            
            PriceItem item;
            switch (priceType) {
                case BAR:
                    item = priceBar(asset, price);
                    break;
                case TRADE:
                    item = tradePrice(asset, price);
                    break;
                case QUOTE:
                    item = priceQuote(asset, price);
                    break;
                default:
                    throw new UnsupportedOperationException("Unknown price type: " + priceType);
            }

            result.add(item);
            prices[idx] = price;
        }
        return result;
    }
}

class RandomNames {

    static Set<String> generate(int size, int len) {
        Set<String> result = new HashSet<>();
        RandomGenerator random = RandomGenerator.getDefault();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        
        while (result.size() < size) {
            StringBuilder name = new StringBuilder();
            for (int i = 0; i < len; i++) {
                name.append(chars.charAt(random.nextInt(chars.length())));
            }
            result.add(name.toString());
        }
        return result;
    }
}

/**
 * Create assets with random names based on a template
 */
public class RandomAssets {

    public static Set<org.robok.common.Asset> generate(int nAssets) {
        Set<String> uniqueNames = RandomNames.generate(nAssets, 5);
        Set<Asset> assets = new HashSet<>();
        for (String name : uniqueNames) {
            assets.add(new Stock(name, CurrencyK.USD));
        }
        return assets;
    }
}
