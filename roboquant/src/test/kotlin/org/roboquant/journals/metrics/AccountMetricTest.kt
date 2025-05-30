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
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

internal class AccountMetricTest {

    @Test
    fun calc() {
        val metric = AccountMetric()
        val (account, event) = TestData.metricInput()
        val result = metric.calculate(event, account, listOf(), listOf())
        assertEquals(6, result.size)
        assertContains(result, "account.orders")
        assertContains(result, "account.equity")
        assertContains(result, "account.positions")
        assertContains(result, "account.buyingpower")
        assertContains(result, "account.cash")
        assertContains(result, "account.mdd")

        assertEquals(account.positions.size.toDouble(), result["account.positions"])
    }
}
