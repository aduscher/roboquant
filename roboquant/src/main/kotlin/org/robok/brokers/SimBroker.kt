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

package org.robok.brokers

import org.roboquant.common.*
import org.robok.common.CurrencyK
import org.robok.common.EventK
import org.robok.common.TIF
import org.robok.common.USD
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

/**
 * Simulated Broker that is used as the broker during back testing and live testing. It simulates both broker and
 * exchange behavior. It can be configured with various plug-ins during initiation time that determine its behavior.
 *
 * @property initialDeposit initial deposit, default is 1 million USD
 * @param baseCurrency the base currency to use for reporting amounts, default is the (first) currency found in the
 * initial deposit
 * @param slippage the price slippage (0.001 = 0.1%). Default is 0.0, so no splippage
 * @property accountModel the account model (like cash or margin) to use, default is [CashAccountModel]
 * @property exchangeZoneId the tiemzone of the exchange, used for GTD time in force calculations. Default is UTC
 * @constructor Create a new instance of SimBroker
 */
open class SimBroker(
    val initialDeposit: org.robok.common.Wallet = _root_ide_package_.org.robok.common.Wallet(1_000_000.00.USD),
    baseCurrency: CurrencyK = initialDeposit.currencies.first(),
    val slippage: Double = 0.0,
    private val accountModel: AccountModel = CashAccountModel(),
    private val exchangeZoneId: ZoneId = ZoneId.of("UTC"),
) : Broker {

    /**
     * Create a new SimBroker with a [deposit] denoted in a single [currencyCode].
     */
    constructor(deposit: Number, currencyCode: String = "USD") : this(
        _root_ide_package_.org.robok.common.Amount(CurrencyK.getInstance(currencyCode), deposit).toWallet()
    )

    private val orderEntry: MutableMap<String, LocalDate> = mutableMapOf()

    private val pendingOrders = mutableListOf<org.robok.common.Order>()

    // Internally used account to store the state
    private val account = InternalAccount(baseCurrency)

    // Logger to use
    private val logger = _root_ide_package_.org.robok.common.Logging.getLogger(SimBroker::class)

    private var nextOrderId = 0


    init {
        this.reset()
    }

    private fun deleteOrder(order: org.robok.common.Order) {
        account.deleteOrder(order)
    }

    /**
     * Get the execution price for an open order. The default implementation is:
     *
     * - For quotes return the ask or bid price depending on BUY or SELL.
     * - For price bars return the open price
     * - For other price types, return the default price
     *
     * Prices are optionally corrected for [slippage], where buy orders will be priced higher
     * and sell orders will be priced lower.
     */
    open fun getExecutionPrice(order: org.robok.common.Order, item: org.robok.common.PriceItem) : Double {
        val corr = if (order.buy) (1.0 + slippage) else (1.0 - slippage)
        return when (item) {
            is org.robok.common.PriceQuote -> if (order.buy) item.askPrice * corr else item.bidPrice * corr
            is org.robok.common.PriceBar -> item.open * corr
            else -> item.getPrice() * corr
        }
    }

    /**
     * Calculate the fee (commision) for the order fill and return the monetary value
     * denoted in the currency of the underlying asset. You can overwrite this method
     * if your broker uses commisions, since the default implementation returns 0.0
     */
    open fun getFee(order: org.robok.common.Order, fill: org.robok.common.Size, price: Double) : Double {
        return 0.0
    }

    /**
     * Return the fill size for the order to be used. Default implementation to
     * fill the complete remaining order size, so thre are no partial fills.
     */
    open fun getFill(order: org.robok.common.Order, price: Double) : org.robok.common.Size {
        return order.remaining
    }

    /**
     * Update the position of an asset based on a fill and return the realized PNL
     */
    private fun updatePosition(asset: org.robok.common.Asset, fill: org.robok.common.Size, price: Double): Double {
        val position = account.positions[asset]

        // Open Position
        if (position == null) {
            account.positions[asset] = _root_ide_package_.org.robok.common.Position(fill, price, price)
            return 0.0
        }

        val newSize = position.size + fill

        // Close position
        if (newSize.iszero) {
            account.positions.remove(asset)
            return asset.value(fill, position.avgPrice - price).value
        }

        // Increase position
        if (position.size.sign == fill.sign) {
            val avgPrice = (fill.toDouble() * position.avgPrice + fill.toDouble() * price) / newSize.toDouble()
            account.positions[asset] =
                _root_ide_package_.org.robok.common.Position(newSize, avgPrice = avgPrice, mktPrice = price)
            return 0.0
        }

        // Decrease position
        if (fill.absoluteValue <= position.size.absoluteValue) {
            account.positions[asset] = _root_ide_package_.org.robok.common.Position(newSize, position.avgPrice, price)
            return asset.value(fill, position.avgPrice - price).value
        }

        // Switch position side
        account.positions[asset] = _root_ide_package_.org.robok.common.Position(newSize, price, price)
        return asset.value(position.size, price - position.avgPrice).value

    }

    /**
     * Is the order already expired.
     */
    open fun isExpired(order: org.robok.common.Order, time: Instant): Boolean {
        if (order.tif == TIF.GTC) return false
        val orderDate = orderEntry[order.id]
        if (orderDate != null) {
            val currentDate = LocalDate.ofInstant(time, exchangeZoneId)
            return currentDate > orderDate
        }
        orderEntry[order.id] = LocalDate.ofInstant(time, exchangeZoneId)
        return false
    }


    private fun simulateMarket(event: EventK) {
        // Add new orders to the execution engine and run it with the latest events
        val time = event.time
        for (order in account.orders.toList()) {
            if (isExpired(order, time)) {
                deleteOrder(order)
                continue
            }
            val priceItem = event.prices.get(order.asset)

            // Only execute if there is a known market price for the asset
            if (priceItem != null) {
                val price = getExecutionPrice(order, priceItem)
                if (order.isExecutable(price)) {
                    val fill = getFill(order, price)
                    val fee = getFee(order, fill, price)
                    val pnl = updatePosition(order.asset, fill, price) - fee
                    val cost = order.asset.value(fill, price) + fee
                    account.cash.withdraw(cost)
                    val trade =
                        _root_ide_package_.org.robok.common.Trade(order.asset, event.time, order.size, price, pnl)
                    order.fill += fill
                    account.trades.add(trade)
                    if (order.remaining.iszero) deleteOrder(order)
                }
            }
        }
    }

    /**
     * Run the simulation given the provided [event].
     */
    @Synchronized
    override fun sync(event: EventK?): org.robok.common.Account {

        for (order in pendingOrders) {
            when {
                order.size.iszero -> {
                    // Cancelation Order
                    assert(order.id.isNotEmpty())
                    val removed = account.orders.removeAll { it.id == order.id }
                    if (! removed) logger.warn("Skipping cancellation $order")
                }

                order.id.isNotEmpty() -> {
                    // Modify order
                    val removed = account.orders.removeIf { it.id == order.id }
                    if (removed)
                        account.orders.add(order)
                    else
                        logger.warn("Skipping modify $order")
                }

                else -> {
                    // Regular create order
                    order.id = nextOrderId++.toString()
                    account.orders.add(order)
                }
            }
        }
        pendingOrders.clear()
        if (event != null) {
            simulateMarket(event)
            account.updateMarketPrices(event)
            account.lastUpdate = event.time
            accountModel.updateAccount(account)
        }
        // account.orders.removeAll(newlyClosed)
        return account.toAccount()
    }

    /**
     * Place the [orders] at the broker.
     */
    @Synchronized
    override fun placeOrders(orders: List<org.robok.common.Order>) {
        logger.trace { "Received orders=${orders.size}" }
        pendingOrders.addAll(orders)
    }


    /**
     * Reset all the state and set the cash balance back to the [initialDeposit].
     */
    fun reset() {
        account.clear()
        account.cash.deposit(initialDeposit)
        accountModel.updateAccount(account)
    }

}

