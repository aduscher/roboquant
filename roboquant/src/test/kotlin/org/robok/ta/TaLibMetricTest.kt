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

package org.robok.ta

import org.junit.jupiter.api.assertDoesNotThrow
import org.robok.brokers.InternalAccount
import org.robok.common.CurrencyK
import org.robok.common.EventK
import org.robok.common.PriceBar
import org.robok.feeds.filter
import org.robok.feeds.util.HistoricTestFeed
import java.time.Instant
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class TaLibMetricTest {

    @Test
    fun test() {
        val metric = _root_ide_package_.org.robok.ta.TaLibMetric { series ->
            mapOf("ema10" to ema(series.close, 10))
        }

        val account = InternalAccount(CurrencyK.USD).toAccount()


        val results = metric.calculate(EventK(Instant.now(), emptyList()), account, listOf(), listOf())
        assertTrue(results.isEmpty())

        val feed = HistoricTestFeed(100 until 111, priceBar = true)
        val events = feed.filter<PriceBar>()
        var mResult = emptyMap<String, Double>()
        for (event in events) {
            mResult = metric.calculate(EventK(event.first, listOf(event.second)), account, listOf(), listOf())
        }
        assertTrue(mResult.isNotEmpty())
        assertEquals(feed.assets.size, mResult.size)
        assertContains(mResult, "ema10.${feed.assets.first().symbol.lowercase()}")

        assertDoesNotThrow {
            metric.reset()
        }

    }
}
