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

package org.roboquant.journals;

import org.roboquant.common.Account;
import org.roboquant.common.Event;
import org.roboquant.common.Order;
import org.roboquant.common.Signal;
import org.roboquant.common.TimeSeries;
import org.roboquant.journals.metrics.Metric;

import java.time.Instant;
import java.util.*;

/**
 * Journal that stores all events in memory and calculates metrics.
 */
public class MemoryJournal implements MetricsJournal {

    private final Metric[] metrics;
    private final TreeMap<Instant, Map<String, Double>> history;

    public MemoryJournal(Metric... metrics) {
        this.metrics = metrics;
        this.history = new TreeMap<>();
    }

    @Override
    public void track(Event event, Account account, List<Signal> signals, List<Order> orders) {
        Map<String, Double> result = new LinkedHashMap<>();
        for (Metric metric : metrics) {
            result.putAll(metric.calculate(event, account, signals, orders));
        }
        history.put(event.getTime(), result);
    }

    @Override
    public Set<String> getMetricNames() {
        Set<String> result = new LinkedHashSet<>();
        for (Map<String, Double> values : history.values()) {
            result.addAll(values.keySet());
        }
        return result;
    }

    @Override
    public TimeSeries getMetric(String name) {
        List<Instant> timeline = new ArrayList<>();
        List<Double> values = new ArrayList<>();

        for (Map.Entry<Instant, Map<String, Double>> entry : history.entrySet()) {
            Map<String, Double> data = entry.getValue();
            if (data.containsKey(name)) {
                timeline.add(entry.getKey());
                values.add(data.get(name));
            }
        }

        double[] valueArray = new double[values.size()];
        for (int i = 0; i < values.size(); i++) {
            valueArray[i] = values.get(i);
        }
        return new TimeSeries(timeline, valueArray);
    }
}
