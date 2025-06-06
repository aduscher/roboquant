/*
 * Copyright 2020-2025 Neural Layer
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

package org.roboquant.ta

import org.junit.jupiter.api.assertDoesNotThrow
import org.roboquant.brokers.InternalAccount
import org.roboquant.common.Currency
import org.roboquant.common.Event
import org.roboquant.common.PriceBar
import org.roboquant.feeds.filter
import org.roboquant.feeds.util.HistoricTestFeed
import java.time.Instant
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class TaLibMetricTest {

    @Test
    fun test() {
        val metric = TaLibMetric { series ->
            mapOf("ema10" to ema(series.close, 10))
        }

        val account = InternalAccount(Currency.USD).toAccount()


        val results = metric.calculate(Event(Instant.now(), emptyList()), account, listOf(), listOf())
        assertTrue(results.isEmpty())

        val feed = HistoricTestFeed(100 until 111, priceBar = true)
        val events = feed.filter<PriceBar>()
        var mResult = emptyMap<String, Double>()
        for (event in events) {
            mResult = metric.calculate(Event(event.first, listOf(event.second)), account, listOf(), listOf())
        }
        assertTrue(mResult.isNotEmpty())
        assertEquals(feed.assets.size, mResult.size)
        assertContains(mResult, "ema10.${feed.assets.first().symbol.lowercase()}")

        assertDoesNotThrow {
            metric.reset()
        }

    }
}
