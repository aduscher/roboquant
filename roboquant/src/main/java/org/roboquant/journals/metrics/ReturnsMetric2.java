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
import org.hipparchus.stat.descriptive.moment.Mean;
import org.hipparchus.stat.descriptive.moment.Skewness;
import org.hipparchus.stat.descriptive.moment.StandardDeviation;
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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Metric that calculates returns and performance statistics compared to a market benchmark.
 * Includes alpha, beta, Sharpe ratio, skewness, and other performance metrics.
 *
 * @param minSize minimum number of data points before metrics are calculated (default 750)
 * @param riskFreeRate annualized risk-free rate (default 0.0)
 * @param priceType price type to use (default "DEFAULT")
 */
public class ReturnsMetric2 implements org.roboquant.journals.metrics.Metric {

    private static final double EPS = 1.0E-10;

    private final int minSize;
    private final double riskFreeRate;
    private final String priceType;
    private final Map<Asset, Double> prices = new LinkedHashMap<>();
    private double equity = Double.NaN;
    private final PriceSeries benchmarkReturns;
    private final PriceSeries accountReturns;
    private final TimeBuffer times;

    public ReturnsMetric2(int minSize, int maxSize, double riskFreeRate, String priceType) {
        if (minSize <= 1) {
            throw new IllegalArgumentException("minSize should be larger than 1");
        }
        if (maxSize < minSize) {
            throw new IllegalArgumentException("maxSize should be larger or equal than minSize");
        }
        this.minSize = minSize;
        this.riskFreeRate = riskFreeRate;
        this.priceType = priceType;
        this.benchmarkReturns = new PriceSeries(maxSize);
        this.accountReturns = new PriceSeries(maxSize);
        this.times = new TimeBuffer(maxSize);
    }

    public ReturnsMetric2() {
        this(750, 750, 0.0, "DEFAULT");
    }

    private void updateBenchmark(Event event) {
        double benchmarkReturn = 0.0;
        int n = 0;
        for (Map.Entry<Asset, PriceItem> entry : event.getPrices().entrySet()) {
            Asset asset = entry.getKey();
            PriceItem item = entry.getValue();
            double price = item.getPrice(priceType);
            Double lastPrice = prices.get(asset);
            if (lastPrice != null) {
                double r = (price - lastPrice) / lastPrice;
                benchmarkReturn += r;
                n++;
            }
            prices.put(asset, price);
        }
        if (n == 0) {
            benchmarkReturns.add(0.0);
        } else {
            benchmarkReturns.add(benchmarkReturn / n);
        }
    }

    private void updateAccount(Account account) {
        double newEquity = account.equityAmount().getValue();
        if (Double.isFinite(this.equity)) {
            double result = (newEquity - this.equity) / this.equity;
            accountReturns.add(result);
        } else {
            accountReturns.add(0.0);
        }
        this.equity = newEquity;
    }

    private double sharpRatio2(double[] portfolioReturns, double[] benchmarkReturns) {
        double t = times.eventsPerYears();
        double[] excessReturns = ExtensionsKt.minus(portfolioReturns, benchmarkReturns);
        double stdExcessReturns = new StandardDeviation().evaluate(excessReturns) * Math.sqrt(t);
        double mean = new Mean().evaluate(excessReturns) * t;
        return mean / (stdExcessReturns + EPS);
    }

    private double beta(double[] portfolioReturns, double[] benchmarkReturns) {
        double covariance = new Covariance().covariance(portfolioReturns, benchmarkReturns);
        double variance = new Variance().evaluate(benchmarkReturns);
        return covariance / variance;
    }

    private static double cumReturns(double[] returns) {
        double result = 1.0;
        for (double r : returns) {
            result *= (r + 1.0);
        }
        return result - 1.0;
    }

    @Override
    public Map<String, Double> calculate(Event event, Account account, List<Signal> signals, List<Order> orders) {
        times.add(event.getTime());
        updateBenchmark(event);
        updateAccount(account);

        if (benchmarkReturns.getSize() >= minSize) {
            double[] mr = benchmarkReturns.toDoubleArray();
            double[] ar = accountReturns.toDoubleArray();
            Timeframe tf = times.getTimeframe();

            double annualPortfolioReturn = tf.annualize(cumReturns(ar));
            double annualMarketReturn = tf.annualize(cumReturns(mr));
            double t = times.eventsPerYears();

            double beta = beta(ar, mr);
            double alpha = annualPortfolioReturn - riskFreeRate - beta * (annualMarketReturn - riskFreeRate);

            double stdReturns = new StandardDeviation().evaluate(ar) * Math.sqrt(t);
            double sharpeRatio = (annualPortfolioReturn - riskFreeRate) / (stdReturns + EPS);
            double sharpeRatio2 = sharpRatio2(ar, mr);

            double excess = annualPortfolioReturn - annualMarketReturn;

            Map<String, Double> result = new LinkedHashMap<>();
            result.put("returns.market", annualMarketReturn);
            result.put("returns.account", annualPortfolioReturn);
            result.put("returns.excess", excess);
            result.put("returns.beta", beta);
            result.put("returns.alpha", alpha);
            result.put("returns.skewness", new Skewness().evaluate(ar));
            result.put("returns.std", stdReturns);
            result.put("returns.sharperatio", sharpeRatio);
            result.put("returns.sharperatio2", sharpeRatio2);
            return result;
        }

        return java.util.Collections.emptyMap();
    }

    @Override
    public void reset() {
        prices.clear();
        equity = Double.NaN;
        benchmarkReturns.clear();
        accountReturns.clear();
        times.clear();
    }
}
