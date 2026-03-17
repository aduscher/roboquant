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

import org.roboquant.common.*;
import org.roboquant.common.Event;
import org.roboquant.feeds.EventChannel;
import org.roboquant.feeds.HistoricFeed;
import org.roboquant.common.Item.PriceItemType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

import static org.roboquant.common.Config.random;

/**
 * Random walk feed contains a number of assets with a price history that follows a random walk. It can be useful for
 * testing since, if your strategy does well using this feed, there might be something suspicious going on.
 *
 * Internally, it uses a seeded random generator. So while it generates random data, the results can be reproduced if
 * instantiated with the same seed. It can generate price-bar or trade-prices prices.
 */
public class RandomWalk implements HistoricFeed {

    private final Timeframe timeframe;
    private final TimeSpan timeSpan;
    private final int nAssets;
    private final PriceItemType priceType;
    private final int volumeRange;
    private final double priceChange;
    private final int seed;
    private final Set<org.robok.common.Asset> assets;
    private final Logger logger = LoggerFactory.getLogger(random.RandomWalk.class);

    public RandomWalk(Timeframe timeframe, TimeSpan timeSpan, int nAssets,
                      PriceItemType priceType, int volumeRange, double priceChange, int seed) {
        this.timeframe = timeframe;
        this.timeSpan = timeSpan != null ? timeSpan : TimeSpan.days(1);
        this.nAssets = nAssets;
        this.priceType = priceType != null ? priceType : PriceItemType.BAR;
        this.volumeRange = volumeRange > 0 ? volumeRange : 1000;
        this.priceChange = priceChange > 0 ? priceChange : 0.001; // 10 bips
        this.seed = seed;
        this.assets = RandomAssets.generate(nAssets);
        
        logger.debug("assets=" + nAssets + " timeframe=" + timeframe);
    }

    public static random.RandomWalk create(Timeframe timeframe, TimeSpan timeSpan, int nAssets,
                                                           PriceItemType priceType, int volumeRange, double priceChange, int seed) {
        return new random.RandomWalk(timeframe, timeSpan, nAssets, priceType, volumeRange, priceChange, seed);
    }

    @Override
    public Set<Asset> getAssets() {
        return assets;
    }

    @Override
    public List<Instant> getTimeline() {
        List<Instant> result = new ArrayList<>();
        Instant time = timeframe.getStart();
        while (timeframe.contains(time)) {
            result.add(time);
            time = time.plusMillis(timeSpan.toMillis());
        }
        return result;
    }

    @Override
    public void play(EventChannel channel) throws InterruptedException {
        RandomPriceGenerator gen = new RandomPriceGenerator(
            new ArrayList<>(assets), priceChange, volumeRange, timeSpan, priceType, seed
        );
        
        Instant time = timeframe.getStart();
        while (timeframe.contains(time)) {
            List<Item.PriceItem> items = gen.next();
            Event event = new Event(time, items);
            channel.send(event);
            time = time.plusMillis(timeSpan.toMillis());
        }
    }

    @Override
    public void close() {
        // No resources to close
    }

    /**
     * Create a random walk for the last years, generating daily prices
     */
    public static RandomWalk lastYears(int years, int nAssets, PriceItemType priceType) {
        int lastYear = LocalDate.now().getYear();
        Timeframe tf = Timeframe.fromYears(lastYear - years, lastYear);
        return new RandomWalk(tf, TimeSpan.days(1), nAssets, priceType, 1000, 0.001, 42);
    }

    /**
     * Create a random walk for the last days, generating minute prices.
     */
    public static RandomWalk lastDays(int days, int nAssets, PriceItemType priceType) {
        Instant last = Instant.now();
        Instant first = last.minusMillis(TimeSpan.days(days).toMillis());
        Timeframe tf = new Timeframe(first, last);
        return new RandomWalk(tf, TimeSpan.minutes(1), nAssets, priceType, 1000, 0.001, 42);
    }

    /**
     * Create a random walk with default parameters
     */
    public static RandomWalk create() {
        return lastYears(1, 10, PriceItemType.BAR);
    }
}
