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

package org.roboquant.journals.metrics;

import org.roboquant.common.Account;
import org.roboquant.common.Event;
import org.roboquant.common.Order;
import org.roboquant.common.Signal;
import org.roboquant.common.Timeframe;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Metric that tracks returns statistics including mean, std, and Sharpe ratio.
 */
public class ReturnsMetric implements Metric {

    private double riskFreeRate = 0.0;
    private int minPeriods = 2;
    private org.roboquant.common.TimeSpan period = new org.roboquant.common.TimeSpan(365, 0, 0, 0, 0);
    private boolean annualize = true;
    private org.hipparchus.stat.descriptive.DescriptiveStatistics stats = new org.hipparchus.stat.descriptive.DescriptiveStatistics();
    private double lastValue = Double.NaN;
    private Instant lastTime = Instant.MIN;
    private Instant nextTime = Instant.MIN;

    public ReturnsMetric() {
    }

    public ReturnsMetric(double riskFreeRate, int minPeriods, org.roboquant.common.TimeSpan period, boolean annualize) {
        this.riskFreeRate = riskFreeRate;
        this.minPeriods = minPeriods;
        this.period = period;
        this.annualize = annualize;
    }

    @Override
    public Map<String, Double> calculate(Event event, Account account, List<Signal> signals, List<Order> orders) {
        Instant time = event.getTime();
        if (nextTime.equals(Instant.MIN)) {
            nextTime = period.plus(time);
            lastTime = time;
            lastValue = account.equityAmount().getValue();
        }

        if (time.compareTo(nextTime) >= 0) {
            double value = account.equityAmount().getValue();
            double periodReturn = (value - lastValue) / lastValue;
            Timeframe tf = new Timeframe(lastTime, time);
            double returns = annualize ? tf.annualize(periodReturn) : periodReturn;
            stats.addValue(returns);
            nextTime = period.plus(time);
            lastTime = time;
            lastValue = value;
            if (stats.getN() >= minPeriods) {
                double mean = stats.getMean();
                double std = stats.getStandardDeviation();
                double sharpeRatio = (mean - riskFreeRate) / (std + 1e-10);
                Map<String, Double> result = new LinkedHashMap<>();
                result.put("returns.mean", mean);
                result.put("returns.std", std);
                result.put("returns.sharperatio", sharpeRatio);
                result.put("returns.min", stats.getMin());
                result.put("returns.max", stats.getMax());
                result.put("returns.last", returns);
                return result;
            }
        }
        return java.util.Collections.emptyMap();
    }

    @Override
    public void reset() {
        stats.clear();
        lastTime = Instant.MIN;
        nextTime = Instant.MIN;
        lastValue = Double.NaN;
    }
}
