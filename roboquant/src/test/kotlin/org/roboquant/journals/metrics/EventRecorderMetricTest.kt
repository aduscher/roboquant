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

package org.roboquant.journals.metrics

import org.roboquant.TestData
import org.roboquant.common.Timeframe
import org.roboquant.common.PriceItem
import org.roboquant.feeds.filter
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class EventRecorderMetricTest {

    @Test
    fun basic() {
        val (account, event) = TestData.metricInput()
        val metric = EventRecorderMetric()

        assertTrue(metric.calculate(event, account, listOf(), listOf()).isEmpty())
        assertEquals(1, metric.timeline.size)
        assertEquals(Timeframe(event.time, event.time, true), metric.timeframe)

        metric.calculate(event, account, listOf(), listOf())
        var results = metric.filter<PriceItem>()
        assertEquals(2, results.size)

        metric.reset()
        results = metric.filter()
        assertTrue(results.isEmpty())
    }

}
