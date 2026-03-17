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


package org.robok

import org.robok.common.Account
import org.robok.common.Position
import org.robok.brokers.InternalAccount
import org.robok.common.Asset
import org.robok.common.CurrencyK
import org.robok.common.EventK
import org.robok.common.PriceBar
import org.robok.common.Size
import org.robok.common.Timeframe
import org.robok.common.TradePrice
import org.robok.common.USD
import org.robok.common.days
import org.robok.common.plus
import org.robok.feeds.Feed
import org.robok.feeds.HistoricFeed
import org.roboquant.common.*
import org.roboquant.feeds.*
import org.robok.feeds.random.RandomWalk
import org.robok.feeds.util.HistoricTestFeed
import org.robok.feeds.util.play
import org.roboquant.feeds.util.*
import java.io.File
import java.time.Instant
import kotlin.test.assertTrue

/**
 * Test data used in unit tests
 */
internal object TestData {

    fun usStock() = _root_ide_package_.org.robok.common.Stock("XYZ")

    fun internalAccount(): InternalAccount {
        val asset1 = _root_ide_package_.org.robok.common.Stock("AAA")
        val asset2 = _root_ide_package_.org.robok.common.Stock("AAB")
        val account = InternalAccount(CurrencyK.USD)
        account.cash.deposit(100_000.USD)
        account.setPosition(asset1, Position(Size(100), 10.0))
        account.setPosition(asset2, Position(Size(100), 10.0))

        val order =
            _root_ide_package_.org.robok.common.Order(asset1, _root_ide_package_.org.robok.common.Size(100), 100.0)
        // val state = MutableOrderState(order, OrderStatus.COMPLETED, Instant.now(), Instant.now())
        account.orders.add(order)
        // account.updateOrder(order, Instant.now(), OrderStatus.COMPLETED)
        return account
    }

    fun usAccount(): Account {
        val asset1 = _root_ide_package_.org.robok.common.Stock("AAA")
        val asset2 = _root_ide_package_.org.robok.common.Stock("AAB")
        val account = InternalAccount(CurrencyK.USD)
        account.cash.deposit(100_000.USD)
        account.setPosition(asset1, Position(Size(100), 10.0))
        account.setPosition(asset2, Position(Size(100), 10.0))
        account.buyingPower = 100_000.USD

        val order =
            _root_ide_package_.org.robok.common.Order(asset1, _root_ide_package_.org.robok.common.Size(100), 100.0)
        account.orders.add(order)
        return account.toAccount()
    }

    fun euStock() = _root_ide_package_.org.robok.common.Stock("ABC", CurrencyK.EUR)

    fun feed(): HistoricFeed {
        return HistoricTestFeed(90..110, 110 downTo 80, 80..125, priceBar = true, asset = usStock())
    }

    fun dataDir(): String {
        if (File("./roboquant/src/test/resources/data/").isDirectory)
            return "./roboquant/src/test/resources/data/"
        else if (File("../roboquant/src/test/resources/data/").isDirectory)
            return "../roboquant/src/test/resources/data/"
        throw _root_ide_package_.org.robok.common.ConfigurationException("cannot find data directory for testing")
    }

    fun euMarketOrder() =
        _root_ide_package_.org.robok.common.Order(euStock(), _root_ide_package_.org.robok.common.Size(10), 100.0)

    fun usMarketOrder() =
        _root_ide_package_.org.robok.common.Order(usStock(), _root_ide_package_.org.robok.common.Size(10), 100.0)

    private fun priceItem(asset: Asset = usStock()) =
        _root_ide_package_.org.robok.common.TradePrice(asset, 10.0)

    private fun priceBar(asset: Asset = usStock()) =
        _root_ide_package_.org.robok.common.PriceBar(asset, 10.0, 11.0, 9.0, 10.0, 1000.0)

    fun time(): Instant = Instant.parse("2020-01-03T12:00:00Z")

    fun event(time: Instant = time()) = _root_ide_package_.org.robok.common.EventK(time, listOf(priceItem()))

    fun event2(time: Instant = time()) = _root_ide_package_.org.robok.common.EventK(time, listOf(priceBar()))

    fun metricInput(time: Instant = time()): Pair<Account, EventK> {
        val account = usAccount()
        val asset1 = account.positions.keys.first()
        val moment = _root_ide_package_.org.robok.common.EventK(
            time,
            listOf(TradePrice(asset1, 11.0))
        )
        return Pair(account, moment)
    }

    fun events(n: Int = 100, asset: Asset = usStock()): List<EventK> {
        val start = time()
        val result = mutableListOf<EventK>()
        var price = 100.0
        repeat(n) {
            if (n % 2 == 0) price += 0.01 * n else price -= 0.01 * n
            val item = TradePrice(asset, price)
            val event = EventK(start + it.days, listOf(item))
            result.add(event)
        }
        return result
    }

    val feed = RandomWalk.lastYears(nAssets = 2)

}

suspend fun feedTest(feed: Feed, timeframe: Timeframe = Timeframe.INFINITE) {
    var prev = Instant.MIN
    for (event in play(feed, timeframe)) {
        assertTrue(event.time >= prev)
        prev = event.time

        for (price in event.prices.values) {
            if (price is PriceBar) {
                assertTrue(price.low <= price.high)
                assertTrue(price.close <= price.high)
                assertTrue(price.open <= price.high)

                assertTrue(price.open >= price.low)
                assertTrue(price.close >= price.low)
            }
        }
    }
}



