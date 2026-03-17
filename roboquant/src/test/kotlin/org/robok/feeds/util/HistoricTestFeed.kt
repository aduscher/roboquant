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

package org.robok.feeds.util

import org.robok.common.Asset
import org.robok.common.Background
import org.robok.common.PriceItem
import org.robok.common.TimeSpan
import org.robok.common.Timeframe
import org.robok.common.days
import org.robok.common.plus
import org.robok.feeds.EventChannel
import org.robok.feeds.Feed
import org.robok.feeds.HistoricPriceFeed
import org.roboquant.common.*
import org.roboquant.feeds.*
import java.time.Instant



fun play(feed: Feed, timeframe: Timeframe = Timeframe.INFINITE): EventChannel {
    val channel = _root_ide_package_.org.robok.feeds.EventChannel(timeframe = timeframe)

    Background.job {
        feed.play(channel)
        channel.close()
    }
    return channel
}

/**
 * Feed that will generate events for a series of prices using the system time. It can be used to validate if a
 * strategy is behaving as expected given a known set of prices.
 *
 * @constructor Create a new Test feed
 */
class HistoricTestFeed(
    vararg prices: Iterable<Number> = arrayOf(90..100, 100 downTo 90),
    start: Instant = Instant.parse("1970-01-01T12:00:00Z"),
    private val timeSpan: TimeSpan = 1.days,
    asset: Asset = _root_ide_package_.org.robok.common.Stock("TEST"),
    private val priceBar: Boolean = false,
    private val volume: Double = 1000.0
) : HistoricPriceFeed() {

    init {
        require(prices.isNotEmpty()) { "prices cannot be empty" }
        var now = start
        for (range in prices) {
            for (price in range) {
                val item = getAction(asset, price.toDouble())
                add(now, item)
                now + timeSpan
            }
        }
    }

    private fun getAction(asset: Asset, price: Double): PriceItem {
        return if (priceBar) {
            _root_ide_package_.org.robok.common.PriceBar(
                asset,
                price,
                price * 1.001,
                price * 0.999,
                price,
                volume,
                timeSpan
            )
        } else {
            _root_ide_package_.org.robok.common.TradePrice(asset, price, volume)
        }
    }

}
