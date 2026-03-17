package org.roboquant.journals.metrics;

import org.roboquant.common.Account;
import org.roboquant.common.Event;
import org.roboquant.common.Order;
import org.roboquant.common.Signal;

import java.util.List;
import java.util.Map;

/**
 * Metric that tracks progress information like actions, events, and wall time.
 */
public class ProgressMetric implements Metric {

    private long startTime = System.currentTimeMillis();
    private long actions;
    private long events;

    @Override
    public Map<String, Double> calculate(Event event, Account account, List<Signal> signals, List<Order> orders) {
        actions += event.getItems().size();
        events++;
        Map<String, Double> result = new java.util.LinkedHashMap<>();
        result.put("progress.actions", (double) actions);
        result.put("progress.events", (double) events);
        result.put("progress.walltime", (double) (System.currentTimeMillis() - startTime));
        return result;
    }

    @Override
    public void reset() {
        startTime = System.currentTimeMillis();
        actions = 0L;
        events = 0L;
    }

}
