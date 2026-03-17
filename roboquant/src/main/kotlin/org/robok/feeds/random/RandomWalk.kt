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

package org.robok.feeds.random

import org.roboquant.common.*
import org.robok.common.EventK
import org.robok.feeds.EventChannel
import org.robok.feeds.HistoricFeed
import org.robok.common.PriceItemType
import org.robok.common.bips
import org.robok.common.days
import org.robok.common.minus
import org.robok.common.minutes
import org.robok.common.plus
import java.time.Instant
import java.time.LocalDate

/**
 * Random walk feed contains a number of assets with a price history that follows a random walk. It can be useful for
 * testing since, if your strategy does well using this feed, there might be something suspicious going on.
 *
 * Internally, it uses a seeded random generator. So while it generates random data, the results can be reproduced if
 * instantiated with the same seed. It can generate price-bar or trade-prices prices.
 *
 * ## Background
 * Random walk theory suggests that changes in stock prices have the same distribution and are independent of each
 * other. Therefore, it assumes the past movement or trend of a stock price or market cannot be used to predict its
 * future movement. In short, random walk theory proclaims that stocks take a random and unpredictable path that makes
 * all methods of predicting stock prices futile in the long run.
 *
 * @property timeframe the timeframe of this random walk
 * @property timeSpan the timeSpan between two events, default is `1.days`
 * @param nAssets the number of assets to generate, symbol names will be ASSET1, ASSET2, ..., ASSET<N>. Default is 10.
 * @property priceType should PriceBars be generated or plain TradePrice, default is true
 * @property volumeRange what is the volume range, default = 1000
 * @property priceChange the price range, the default is 10 bips.
 * @property seed seed to use for initializing the random generator, default is 42
 */
class RandomWalk(
    override val timeframe: org.robok.common.Timeframe,
    private val timeSpan: org.robok.common.TimeSpan = 1.days,
    nAssets: Int = 10,
    private val priceType: PriceItemType = PriceItemType.BAR,
    private val volumeRange: Int = 1000,
    private val priceChange: Double = 10.bips,
    private val seed: Int = 42
) : HistoricFeed {

    /**
     * The assets contained in this feed. Each asset has a unique symbol name of `template.symbol<nr>`
     */
    override val assets = randomAssets(nAssets)

    /**
     * The timeline
     */
    override val timeline: org.robok.common.Timeline
        get() {
            val result = mutableListOf<Instant>()
            var time = timeframe.start
            while (timeframe.contains(time)) {
                result.add(time)
                time + timeSpan
            }
            return result
        }

    init {
        logger.debug { "assets=$nAssets timeframe=$timeframe" }
    }

    /**
     * @see HistoricFeed.play
     */
    override suspend fun play(channel: EventChannel) {
        val gen = RandomPriceGenerator(assets.toList(), priceChange, volumeRange, timeSpan, priceType, seed)
        var time = timeframe.start
        while (timeframe.contains(time)) {
            val items = gen.next()
            val event = EventK(time, items)
            channel.send(event)
            time += timeSpan
        }
    }

    /**
     * @suppress
     */
    companion object {

        private val logger = _root_ide_package_.org.robok.common.Logging.getLogger(RandomWalk::class)

        /**
         * Create a random walk for the last [years], generating daily prices
         */
        fun lastYears(years: Int = 1, nAssets: Int = 10, priceType: PriceItemType = PriceItemType.BAR): RandomWalk {
            val lastYear = LocalDate.now().year
            val tf = _root_ide_package_.org.robok.common.Timeframe.fromYears(lastYear - years, lastYear)
            return RandomWalk(tf, 1.days, nAssets, priceType)
        }

        /**
         * Create a random walk for the last [days], generating minute prices.
         */
        fun lastDays(days: Int = 1, nAssets: Int = 10, priceType: PriceItemType = PriceItemType.BAR): RandomWalk {
            val last = Instant.now()
            val first = last - days.days
            val tf = _root_ide_package_.org.robok.common.Timeframe(first, last)
            return RandomWalk(tf, 1.minutes, nAssets, priceType)
        }
    }

}

