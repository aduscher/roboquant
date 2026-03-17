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

package org.roboquant.common;

import java.util.Objects;

/**
 * Stock asset representing a stock.
 *
 * @param symbol the symbol of the stock, e.g. AAPL
 * @param currency the currency used for pricing, default is USD
 */
public final class Stock implements Asset {

    private final String symbol;
    private final Currency currency;

    public Stock(String symbol, Currency currency) {
        this.symbol = Objects.requireNonNull(symbol);
        this.currency = Objects.requireNonNull(currency);
    }

    public Stock(String symbol) {
        this(symbol, Currency.USD);
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String serialize() {
        return "Stock" + Asset.SEP + symbol + Asset.SEP + currency;
    }

    @Override
    public int compareTo(Asset other) {
        return this.symbol.compareTo(other.getSymbol());
    }

    @Override
    public String toString() {
        return "Stock(symbol=" + symbol + ", currency=" + currency + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(symbol, stock.symbol) && Objects.equals(currency, stock.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, currency);
    }

    public Stock withSymbol(String newSymbol) {
        return new Stock(newSymbol, currency);
    }

    public Stock withCurrency(Currency newCurrency) {
        return new Stock(symbol, newCurrency);
    }

}
