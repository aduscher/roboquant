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
import org.roboquant.common.Amount;
import org.roboquant.common.Event;
import org.roboquant.common.Order;
import org.roboquant.common.Position;
import org.roboquant.common.Signal;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Metric that tracks position information for all positions.
 */
public class PositionMetric implements Metric {

    @Override
    public Map<String, Double> calculate(Event event, Account account, List<Signal> signals, List<Order> orders) {
        Map<String, Double> result = new LinkedHashMap<>();
        for (Map.Entry<org.roboquant.common.Asset, Position> entry : account.getPositions().entrySet()) {
            org.roboquant.common.Asset asset = entry.getKey();
            Position position = entry.getValue();
            String name = "position." + asset.getSymbol();
            result.put(name + ".size", position.getSize().toDouble());
            Amount value = asset.value(position.getSize(), position.getMktPrice());
            result.put(name + ".value", value.getValue());
            Amount cost = asset.value(position.getSize(), position.getAvgPrice());
            result.put(name + ".cost", cost.getValue());
            Amount pnl = asset.value(position.getSize(), position.getMktPrice() - position.getAvgPrice());
            result.put(name + ".pnl", pnl.getValue());
        }
        return result;
    }

    @Override
    public void reset() {
    }
}
