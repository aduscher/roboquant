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

package org.robok.traders

import org.robok.TestData
import org.robok.common.Account
import org.roboquant.common.*
import org.robok.common.EventK
import org.robok.common.Order
import org.robok.common.Signal
import org.robok.common.Size
import org.robok.common.Stock
import org.robok.common.hours
import org.robok.common.minutes
import org.robok.common.plus
import java.time.Instant
import kotlin.test.Test
import kotlin.test.assertEquals

internal class CircuitBreakerTest {

    private class MyTrader : Trader {
        override fun createOrders(signals: List<Signal>, event: EventK, account: Account): List<Order> {
            return listOf(
                Order(
                    Stock("A"),
                    Size(10),
                    100.0
                ),
                Order(
                    Stock("B"),
                    Size(10),
                    100.0
                ),
                Order(
                    Stock("C"),
                    Size(10),
                    100.0
                )
            )
        }

    }

    @Test
    fun test() {
        val account = TestData.usAccount()
        val time = Instant.now()
        val policy = _root_ide_package_.org.robok.traders.CircuitBreaker(MyTrader(), 8, 1.hours)
        var orders = policy.createOrders(emptyList(), EventK.empty(time), account)
        assertEquals(3, orders.size)

        orders = policy.createOrders(emptyList(), EventK.empty(time + 30.minutes), account)
        assertEquals(3, orders.size)

        orders = policy.createOrders(emptyList(), EventK.empty(time + 50.minutes), account)
        assertEquals(0, orders.size)

        orders = policy.createOrders(emptyList(), EventK.empty(time + 51.minutes), account)
        assertEquals(0, orders.size)

        orders = policy.createOrders(emptyList(), EventK.empty(time + 120.minutes), account)
        assertEquals(3, orders.size)

    }
}
