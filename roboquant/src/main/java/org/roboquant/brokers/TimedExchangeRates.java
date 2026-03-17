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

import org.roboquant.common.Amount;
import org.roboquant.common.Currency;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.NavigableMap;
import java.util.Set;
import java.util.Map;
import java.util.LinkedHashMap;

/**
 * Exchange Rates implementation that supports different rates at different times. This abstract class is used
 * by other exchange rates implementations like FeedCurrencyConverter and ECBCurrencyConverter.
 */
public abstract class TimedExchangeRates implements ExchangeRates {

    protected final Currency baseCurrency;
    protected final Map<Currency, NavigableMap<Instant, Double>> exchangeRates = new LinkedHashMap<>();

    protected TimedExchangeRates(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    protected Currency getBaseCurrency() {
        return baseCurrency;
    }

    protected Map<Currency, NavigableMap<Instant, Double>> getExchangeRates() {
        return exchangeRates;
    }

    /**
     * Currencies available
     */
    public Collection<Currency> getCurrencies() {
        Set<Currency> result = new HashSet<>(exchangeRates.keySet());
        result.add(baseCurrency);
        return result;
    }

    private double find(Currency currency, Instant time) {
        NavigableMap<Instant, Double> rates = exchangeRates.get(currency);
        Map.Entry<Instant, Double> result = rates.floorEntry(time);
        if (result == null) {
            result = rates.firstEntry();
        }
        return result.getValue();
    }

    /**
     * Convert between two currencies.
     * @see ExchangeRates#getRate
     */
    @Override
    public double getRate(Amount amount, Currency to, Instant time) {
        Currency from = amount.getCurrency();
        if (from == to) return 1.0;

        if (to == baseCurrency) return find(from, time);
        if (from == baseCurrency) return 1.0 / find(to, time);
        return find(from, time) * (1.0 / find(to, time));
    }

}
