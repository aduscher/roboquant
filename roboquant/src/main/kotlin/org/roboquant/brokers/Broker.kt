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

package org.roboquant.brokers

import org.roboquant.common.Account
import org.roboquant.common.Event
import org.roboquant.common.Order

/**
 * Interface for any broker implementation, used for both simulated and real brokers.
 */
interface Broker {

    /**
     * Sync the state of the roboquant with the broker.
     *
     * Typically, this method will invoke the underlying broker API to obtain the latest state of positions, orders,
     * trades, cash and buying power.
     *
     * Optionally an [event] can be provided, although normally only the SimBroker requires this to simulate
     * trade executions.
     *
     * A sync will return an instance of the account object.
     */
    fun sync(event: Event? = null) : Account

    /**
     * Place new [orders] at this broker.
     *
     * Typically, this method will invoke the underlying broker API to place the orders and set the corresponding order-id.
     * Place orders can also be used to update or cancel orders.
     */
    fun placeOrders(orders: List<Order>)

}
