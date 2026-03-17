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
import org.roboquant.common.Asset;
import org.roboquant.common.Event;
import org.roboquant.common.Order;
import org.roboquant.common.Item.*;
import org.roboquant.common.Signal;
import org.roboquant.common.Timeframe;
import org.roboquant.common.Wallet;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Metric that tracks profit and loss information.
 */
public class PNLMetric implements Metric {

    private String priceType;
    private double equity = Double.NaN;
    private Map<Asset, AssetReturn> assetReturns = new LinkedHashMap<>();

    public PNLMetric() {
        this("DEFAULT");
    }

    public PNLMetric(String priceType) {
        this.priceType = priceType;
    }

    @Override
    public Map<String, Double> calculate(Event event, Account account, List<Signal> signals, List<Order> orders) {
        if (Double.isNaN(equity)) {
            equity = account.equityAmount().getValue();
        }

        double pnl = account.equityAmount().getValue() - equity;
        Wallet unrealized = account.unrealizedPNL();
        double unrealizedPNL = unrealized.convert(account.getBaseCurrency(), event.getTime()).getValue();

        for (PriceItem item : event.getPrices().values()) {
            AssetReturn r = assetReturns.computeIfAbsent(item.getAsset(), k -> new AssetReturn(event.getTime(), item.getPrice(priceType)));
            r.update(event.getTime(), item.getPrice(priceType));
        }

        Map<String, Double> result = new LinkedHashMap<>();
        result.put("pnl.realized", pnl - unrealizedPNL);
        result.put("pnl.unrealized", unrealizedPNL);
        result.put("pnl.total", pnl);
        result.put("pnl.mkt", marketReturn());
        return result;
    }

    private double marketReturn() {
        double sum = 0.0;
        double totalWeights = 0.0;
        for (AssetReturn r : assetReturns.values()) {
            long weight = r.getDuration();
            sum += r.calcReturn() * weight;
            totalWeights += weight;
        }
        return totalWeights > 0 ? sum / totalWeights : 0.0;
    }

    @Override
    public void reset() {
        equity = Double.NaN;
        assetReturns.clear();
    }

    private static class AssetReturn {
        private final Instant start;
        private final double first;
        private Instant end;
        private double last;

        AssetReturn(Instant start, double first) {
            this.start = start;
            this.first = first;
            this.end = start;
            this.last = first;
        }

        void update(Instant time, double value) {
            this.end = time;
            this.last = value;
        }

        Instant getStart() {
            return start;
        }

        double getFirst() {
            return first;
        }

        Instant getEnd() {
            return end;
        }

        double getLast() {
            return last;
        }

        long getDuration() {
            return new Timeframe(start, end).getDuration().toMillis();
        }

        double calcReturn() {
            return last / first - 1.0;
        }
    }
}
