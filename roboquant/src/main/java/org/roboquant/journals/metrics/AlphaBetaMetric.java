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

import org.hipparchus.stat.correlation.Covariance;
import org.hipparchus.stat.descriptive.moment.Variance;
import org.roboquant.common.Account;
import org.roboquant.common.Asset;
import org.roboquant.common.Event;
import org.roboquant.common.ExtensionsKt;
import org.roboquant.common.Order;
import org.roboquant.common.PriceItem;
import org.roboquant.common.PriceSeries;
import org.roboquant.common.Signal;
import org.roboquant.common.Timeframe;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Calculates the Alpha and Beta of the account.
 *
 * Alpha measures the performance compared to the market (universe).
 * Beta measures the volatility (systematic risk) compared to the market.
 *
 * Market is defined as all the assets in the feed, so no reference asset is needed.
 *
 * @param period number of events over which to calculate alpha and beta
 * @param priceType price type to use for market performance calculation
 * @param riskFreeReturn annualized risk-free return (e.g., 0.01 for 1%)
 */
public class AlphaBetaMetric implements org.roboquant.journals.metrics.Metric {

    private final int period;
    private final String priceType;
    private final double riskFreeReturn;
    private final PriceSeries marketData;
    private final PriceSeries equityData;
    private final Map<Asset, Double> oldPrices = new LinkedHashMap<>();
    private boolean initialized = false;
    private double oldEquity = 0.0;
    private final LinkedList<Instant> times = new LinkedList<>();

    public AlphaBetaMetric(int period, String priceType, double riskFreeReturn) {
        this.period = period;
        this.priceType = priceType;
        this.riskFreeReturn = riskFreeReturn;
        this.marketData = new PriceSeries(period);
        this.equityData = new PriceSeries(period);
    }

    public AlphaBetaMetric() {
        this(252, "DEFAULT", ExtensionsKt.percent(0));
    }

    private double getMarketReturn(Map<Asset, Double> prices) {
        double sum = 0.0;
        int cnt = 0;
        for (Asset asset : prices.keySet()) {
            if (oldPrices.containsKey(asset)) {
                cnt++;
                sum += prices.get(asset) / oldPrices.get(asset) - 1.0;
            }
        }
        return cnt > 0 ? sum / cnt : 0.0;
    }

    private Timeframe getTimeframe() {
        if (times.isEmpty()) {
            return Timeframe.EMPTY;
        }
        return new Timeframe(times.getFirst(), times.getLast(), true);
    }

    private static double product(double[] returns) {
        double result = 1.0;
        for (double r : returns) {
            result *= (r + 1.0);
        }
        return result - 1.0;
    }

    @Override
    public Map<String, Double> calculate(Event event, Account account, List<Signal> signals, List<Order> orders) {
        if (event.getPrices().isEmpty()) {
            return java.util.Collections.emptyMap();
        }

        Map<Asset, Double> prices = new LinkedHashMap<>();
        for (Map.Entry<Asset, PriceItem> entry : event.getPrices().entrySet()) {
            prices.put(entry.getKey(), entry.getValue().getPrice(priceType));
        }

        double equity = account.equityAmount().getValue();

        if (initialized) {
            double mr = getMarketReturn(prices);
            marketData.add(mr);
            equityData.add(equity / oldEquity - 1.0);
            times.add(event.getTime());
            if (times.size() > period) {
                times.removeFirst();
            }
        }

        oldPrices.putAll(prices);
        oldEquity = equity;
        initialized = true;

        if (marketData.isFull() && equityData.isFull()) {
            double[] mr = marketData.toDoubleArray();
            double[] pr = equityData.toDoubleArray();

            double beta = new Covariance().covariance(mr, pr) / new Variance().evaluate(mr);
            double totalMr = getTimeframe().annualize(product(mr));
            double totalPr = getTimeframe().annualize(product(pr));
            double alpha = totalPr - riskFreeReturn - beta * (totalMr - riskFreeReturn);

            Map<String, Double> result = new LinkedHashMap<>();
            result.put("account.alpha", alpha);
            result.put("account.beta", beta);
            return result;
        }

        return java.util.Collections.emptyMap();
    }

    @Override
    public void reset() {
        equityData.clear();
        marketData.clear();
        oldPrices.clear();
        oldEquity = 0.0;
        times.clear();
        initialized = false;
    }
}
