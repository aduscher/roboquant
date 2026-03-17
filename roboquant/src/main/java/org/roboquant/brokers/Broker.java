package org.roboquant.brokers;

import org.roboquant.common.Account;
import org.roboquant.common.Event;
import org.roboquant.common.Order;

import java.util.List;

/**
 * Interface for any broker implementation, used for both simulated and real brokers.
 */
public interface Broker {

    /**
     * Sync the state of the roboquant with the broker.
     *
     * Typically, this method will invoke the underlying broker API to obtain the latest state of positions, orders,
     * trades, cash and buying power.
     *
     * Optionally an event can be provided, although normally only the SimBroker requires this to simulate
     * trade executions.
     *
     * A sync will return an instance of the account object.
     *
     * @param event optional event for simulating trade executions
     * @return the current account state
     */
    Account sync(Event event);

    /**
     * Place new orders at this broker.
     *
     * Typically, this method will invoke the underlying broker API to place the orders and set the corresponding order-id.
     * Place orders can also be used to update or cancel existing orders.
     *
     * @param orders the list of orders to place
     */
    void placeOrders(List<Order> orders);

}
