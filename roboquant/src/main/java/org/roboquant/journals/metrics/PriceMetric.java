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

import java.util.List;
import java.util.Map;

/**
 * Metric that tracks price information for all assets in the event.
 */
public class PriceMetric implements Metric {

    private String priceType;

    public PriceMetric() {
        this("DEFAULT");
    }

    public PriceMetric(String priceType) {
        this.priceType = priceType;
    }

    @Override
    public Map<String, Double> calculate(Event event, Account account, List<Signal> signals, List<Order> orders) {
        Map<String, Double> result = new java.util.LinkedHashMap<>();
        for (var entry : event.getPrices().entrySet()) {
            var item = entry.getValue();
            String name = "price." + priceType + "." + item.getAsset().getSymbol();
            result.put(name.toLowerCase(), item.getPrice(priceType));
        }
        return result;
    }

}
