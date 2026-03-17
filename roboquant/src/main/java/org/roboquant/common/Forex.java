package org.roboquant.common;

import java.util.Objects;

/**
 * Forex asset representing a currency pair.
 */
public final class Forex implements Asset {

    private final String symbol;
    private final Currency currency;

    public Forex(String symbol, Currency currency) {
        this.symbol = Objects.requireNonNull(symbol);
        this.currency = Objects.requireNonNull(currency);
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
        return "Forex" + Asset.SEP + symbol + Asset.SEP + currency;
    }

    @Override
    public int compareTo(Asset other) {
        return this.symbol.compareTo(other.getSymbol());
    }

    @Override
    public String toString() {
        return "Forex(symbol=" + symbol + ", currency=" + currency + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forex forex = (Forex) o;
        return Objects.equals(symbol, forex.symbol) && Objects.equals(currency, forex.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, currency);
    }

    public Forex withSymbol(String newSymbol) {
        return new Forex(newSymbol, currency);
    }

    public Forex withCurrency(Currency newCurrency) {
        return new Forex(symbol, newCurrency);
    }

    public static Forex fromSymbol(String symbol) {
        Currency[] pair = ExtensionsKt.toCurrencyPair(symbol);
        return new Forex(symbol, pair[1]);
    }

}
