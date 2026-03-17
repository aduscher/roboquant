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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Metric that tracks account information including orders, positions, cash, and drawdown.
 */
public class AccountMetric implements Metric {

    private double peak = Double.MIN_VALUE;
    private double mdd = Double.MAX_VALUE;

    @Override
    public Map<String, Double> calculate(Event event, Account account, List<Signal> signals, List<Order> orders) {
        Map<String, Double> result = new LinkedHashMap<>();
        double equity = account.equityAmount().getValue();
        if (equity > peak) {
            peak = equity;
        }
        double dd = (equity - peak) / peak;
        if (dd < mdd) {
            mdd = dd;
        }
        result.put("account.orders", (double) account.getOrders().size());
        result.put("account.positions", (double) account.getPositions().size());
        result.put("account.cash", account.getCash().convert(account.getBaseCurrency(), event.getTime()).getValue());
        result.put("account.buyingpower", account.getBuyingPower().getValue());
        result.put("account.equity", equity);
        result.put("account.mdd", mdd);
        return result;
    }

    @Override
    public void reset() {
        peak = Double.MIN_VALUE;
        mdd = Double.MAX_VALUE;
    }
}
