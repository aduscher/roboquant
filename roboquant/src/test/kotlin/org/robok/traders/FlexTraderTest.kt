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
import org.robok.brokers.InternalAccount
import org.robok.common.CurrencyK
import org.roboquant.common.*
import org.robok.common.EventK
import org.robok.common.Order
import org.robok.common.TradePrice
import org.robok.common.Signal
import org.robok.common.Size
import org.robok.common.USD
import org.robok.common.days
import java.time.Instant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class FlexTraderTest {

    @Test
    fun order() {
        val policy = _root_ide_package_.org.robok.traders.FlexTrader()
        val signals = mutableListOf<Signal>()
        val event = EventK(Instant.now(), emptyList())
        val account = InternalAccount(CurrencyK.USD).toAccount()
        val orders = policy.createOrders(signals, event, account)
        assertTrue(orders.isEmpty())
    }

    @Test
    fun order3() {
        val policy = _root_ide_package_.org.robok.traders.FlexTrader()
        val orders = run(policy)
        assertTrue(orders.isNotEmpty())

        val order = orders.first()
        assertEquals("TEST123", order.asset.symbol)
        assertEquals(Size(204), order.size)
    }

    @Test
    fun orderMinPrice() {
        val policy = _root_ide_package_.org.robok.traders.FlexTrader {
            minPrice = 10.USD
        }
        val asset = _root_ide_package_.org.robok.common.Stock("TEST123")
        val signals = listOf(Signal.buy(asset))

        val event1 = EventK(Instant.now(), listOf(TradePrice(asset, 5.0)))
        val account = TestData.usAccount()
        val orders1 = policy.createOrders(signals, event1, account)
        assertTrue(orders1.isEmpty())

        val event2 = EventK(Instant.now(), listOf(TradePrice(asset, 15.0)))
        val orders2 = policy.createOrders(signals, event2, account)
        assertTrue(orders2.isNotEmpty())
    }




    private fun run(policy: FlexTrader): List<Order> {
        val asset = _root_ide_package_.org.robok.common.Stock("TEST123")
        val signals = listOf(Signal.buy(asset))
        val event = EventK(Instant.now(), listOf(TradePrice(asset, 5.0)))
        val account = TestData.usAccount()
        return policy.createOrders(signals, event, account)
    }



    @Test
    fun chaining() {
        val policy = FlexTrader()
            .circuitBreaker(10, 1.days)
        val signals = mutableListOf<Signal>()
        val event = EventK(Instant.now(), emptyList())
        val account = InternalAccount(CurrencyK.USD).toAccount()
        val orders = policy.createOrders(signals, event, account)
        assertTrue(orders.isEmpty())
    }


}
