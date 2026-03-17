package org.roboquant.journals.metrics;

import org.roboquant.common.Account;
import org.roboquant.common.Event;
import org.roboquant.common.Order;
import org.roboquant.common.Signal;

import java.util.List;
import java.util.Map;

/**
 * Metric represents a piece of information you want to capture during a run.
 */
public interface Metric {

    Map<String, Double> calculate(Event event, Account account, List<Signal> signals, List<Order> orders);

    default void reset() {
    }
}
