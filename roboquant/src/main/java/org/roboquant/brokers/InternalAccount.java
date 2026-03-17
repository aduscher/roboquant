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

package org.roboquant.brokers;

import org.roboquant.common.*;
import org.roboquant.common.Currency;

import java.time.Instant;
import java.util.*;

/**
 * Internal Account is meant to be used by broker implementations, like the SimBroker. The broker is the only one with
 * a reference to the InternalAccount and will communicate the state to the outside world (SignalConverter and Metrics) using
 * the Account object.
 */
public class InternalAccount implements Account {

    private Currency baseCurrency;
    private Instant lastUpdate = Instant.MIN;
    private final List<Order> orders = new ArrayList<>();
    private final Wallet cash = new Wallet();
    private Amount buyingPower;
    private final Map<Asset, Position> positions = new LinkedHashMap<>();

    public InternalAccount(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
        this.buyingPower = new Amount(baseCurrency, 0.0);
    }

    @Override
    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    @Override
    public Instant getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Instant lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public Wallet getCash() {
        return cash;
    }

    @Override
    public Amount getBuyingPower() {
        return buyingPower;
    }

    @Override
    public List<Trade> getTrades() {
        return List.of();
    }

    public void setBuyingPower(Amount buyingPower) {
        this.buyingPower = buyingPower;
    }

    @Override
    public Map<Asset, Position> getPositions() {
        return positions;
    }

    @Override
    public Set<Asset> getAssets() {
        return positions.keySet();
    }

    /**
     * Clear all the state in this account.
     */
    public synchronized void clear() {
        lastUpdate = Instant.MIN;
        orders.clear();
        positions.clear();
        cash.clear();
    }

    /**
     * Delete orders with the same id as the one provided.
     */
    public void deleteOrder(Order order) {
        orders.removeIf(o -> o.getId().equals(order.getId()));
    }

    /**
     * Set the position. If the position is closed, it is removed all together from the positions.
     */
    public synchronized void setPosition(Asset asset, Position position) {
        if (position.isClosed()) {
            positions.remove(asset);
        } else {
            positions.put(asset, position);
        }
    }

    /**
     * Update the open positions in the portfolio with the current market prices as found in the event
     */
    public void updateMarketPrices(Event event, String priceType) {
        if (positions.isEmpty()) return;

        Map<?, ?> prices = event.getPrices();
        for (Map.Entry<Asset, Position> entry : positions.entrySet()) {
            Asset asset = entry.getKey();
            Position position = entry.getValue();
            Object priceItem = prices.get(asset);
            if (priceItem instanceof Item.PriceItem item) {
                double price = item.getPrice(priceType);
                Position newPosition = new Position(position.getSize(), position.getAvgPrice(), price, position.getLastUpdate());
                positions.put(asset, newPosition);
            }
        }
    }

    /**
     * Update the open positions in the portfolio with the current market prices using default price type.
     */
    public void updateMarketPrices(Event event) {
        updateMarketPrices(event, "DEFAULT");
    }

    /**
     * Create an Account instance
     */
    public synchronized Account toAccount() {
        return this;
    }

    @Override
    public String toString() {
        String pString = positions.values().stream()
                .map(p -> p.getSize() + "@" + positions.entrySet().stream()
                        .filter(e -> e.getValue() == p)
                        .findFirst().map(e -> e.getKey().getSymbol()).orElse(""))
                .reduce((a, b) -> a + ", " + b).orElse("");

        String oString = orders.stream()
                .map(o -> o.getSize() + "@" + o.getAsset().getSymbol())
                .reduce((a, b) -> a + ", " + b).orElse("");

        return String.format(
                "last update  : %s\ncash         : %s\nbuying Power : %s\nequity       : %s\npositions    : %s\nopen orders  : %s\ntrades       : %d",
                lastUpdate, cash, buyingPower, equity(), pString, oString, 0
        );
    }

}
