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

import org.robok.common.TimeSpan;
import org.robok.common.Timeframe;
import org.robok.common.PriceItemType;
import org.robok.feeds.random.RandomWalk;

/**
 * Extension functions for RandomWalk translated to Java static methods.
 */
public final class RandomPriceGeneratorKt {

    private RandomPriceGeneratorKt() {
    }

    /**
     * Create a random walk for the last years, generating daily prices
     */
    public static org.robok.feeds.random.RandomWalk randomWalkLastYears(int years, int nAssets, PriceItemType priceType) {
        return org.robok.feeds.random.RandomWalk.lastYears(years, nAssets, priceType);
    }

    /**
     * Create a random walk for the last days, generating minute prices.
     */
    public static org.robok.feeds.random.RandomWalk randomWalkLastDays(int days, int nAssets, PriceItemType priceType) {
        return org.robok.feeds.random.RandomWalk.lastDays(days, nAssets, priceType);
    }

    /**
     * Create a random walk with default parameters
     */
    public static org.robok.feeds.random.RandomWalk randomWalk() {
        return RandomWalk.create();
    }
}
