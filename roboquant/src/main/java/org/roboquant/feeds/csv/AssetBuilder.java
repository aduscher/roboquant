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

package org.roboquant.feeds.csv;

import org.robok.common.Asset;
import org.roboquant.common.Currency;
import org.robok.common.CurrencyK;
import org.robok.common.Stock;

/**
 * Functional interface for building assets from file names.
 */
@FunctionalInterface
public interface AssetBuilder {

    /**
     * Based on a name, return an instance of Asset
     */
    Asset build(String name);
}

/**
 * The default asset builder uses a file name without its extension as the symbol name.
 */
public class StockBuilder implements org.robok.feeds.csv.AssetBuilder {

    private final CurrencyK currency;

    public StockBuilder(CurrencyK currency) {
        this.currency = currency != null ? currency : Currency.USD;
    }

    public StockBuilder() {
        this(Currency.USD);
    }

    @Override
    public Asset build(String name) {
        String symbol = name;
        if (symbol.endsWith(".csv")) {
            symbol = symbol.substring(0, symbol.length() - 4);
        } else if (symbol.endsWith(".txt")) {
            symbol = symbol.substring(0, symbol.length() - 4);
        }
        symbol = symbol.toUpperCase().replaceAll("[^A-Z]", ".");
        return new Stock(symbol, currency);
    }
}
