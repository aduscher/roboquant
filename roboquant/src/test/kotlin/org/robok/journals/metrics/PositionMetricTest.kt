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

package org.robok.journals.metrics

import org.robok.TestData
import org.robok.common.EventK
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class PositionMetricTest {

    @Test
    fun calc() {
        val metric = _root_ide_package_.org.robok.journals.metrics.PositionMetric()
        val account = TestData.usAccount()
        val result = metric.calculate(EventK.empty(), account, listOf(), listOf())
        assertEquals(account.positions.size * 4, result.size)

        val symbol = account.positions.keys.first().symbol
        assertContains(result, "position.$symbol.size")
        assertContains(result, "position.$symbol.value")
        assertContains(result, "position.$symbol.cost")
        assertContains(result, "position.$symbol.pnl")
        assertTrue(result.all { it.key.startsWith("position.") })
    }
}
