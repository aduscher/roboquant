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

import org.robok.brokers.ExchangeRates;
import org.robok.common.Amount;
import org.robok.common.CurrencyK;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Currency converter that supports fixed exchange rates between currencies, so rates that don't change over the
 * duration of a run. It provides logic to convert between two currencies given this map of
 * exchange rates. It is smart in the sense that is able to convert between currencies even if there is no direct
 * exchange rate defined in the map for a given currency pair.
 *
 * It will throw an exception if a conversion is required for an unknown currency.
 */
public class FixedExchangeRates implements org.robok.brokers.ExchangeRates {

    private CurrencyK baseCurrency;
    private final ConcurrentHashMap<CurrencyK, Double> exchangeRates;

    /**
     * Create a fixed currency converter with a map of exchange rates.
     *
     * @param baseCurrency the base currency for which the exchange rates are provided
     * @param exchangeRates the map of currency to exchange rate
     */
    public FixedExchangeRates(CurrencyK baseCurrency, Map<CurrencyK, Double> exchangeRates) {
        this.baseCurrency = baseCurrency;
        this.exchangeRates = new ConcurrentHashMap<>(exchangeRates);
    }

    /**
     * Create a fixed currency converter with vararg pairs.
     *
     * @param baseCurrency the base currency
     * @param rates the currency-rate pairs
     */
    public FixedExchangeRates(CurrencyK baseCurrency, Map.Entry<CurrencyK, Double>... rates) {
        this(baseCurrency, Map.ofEntries(rates));
    }

    public CurrencyK getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(CurrencyK baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    /**
     * Returns the exchange rate
     * @see ExchangeRates#getRate
     */
    @Override
    public double getRate(Amount amount, CurrencyK to, Instant time) {
        CurrencyK from = amount.getCurrency();
        if (from == to) return 1.0;
        if (to == baseCurrency) return exchangeRates.get(from);
        if (from == baseCurrency) return 1.0 / exchangeRates.get(to);
        return exchangeRates.get(from) * (1.0 / exchangeRates.get(to));
    }

    /**
     * Set the exchange rate for the currency
     *
     * @param currency the currency to set the rate for
     * @param rate the exchange rate
     */
    public void setRate(CurrencyK currency, double rate) {
        exchangeRates.put(currency, rate);
    }

}
