package org.roboquant.common;

import java.util.Objects;

/**
 * Crypto asset representing a cryptocurrency.
 */
public final class Crypto implements Asset {

    private final String symbol;
    private final Currency currency;

    public Crypto(String symbol, Currency currency) {
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
        return "Crypto" + Asset.SEP + symbol + Asset.SEP + currency;
    }

    @Override
    public int compareTo(Asset other) {
        return this.symbol.compareTo(other.getSymbol());
    }

    @Override
    public String toString() {
        return "Crypto(symbol=" + symbol + ", currency=" + currency + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crypto crypto = (Crypto) o;
        return Objects.equals(symbol, crypto.symbol) && Objects.equals(currency, crypto.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, currency);
    }

    public Crypto withSymbol(String newSymbol) {
        return new Crypto(newSymbol, currency);
    }

    public Crypto withCurrency(Currency newCurrency) {
        return new Crypto(symbol, newCurrency);
    }

    public static Crypto fromSymbol(String symbol) {
        Currency[] pair = ExtensionsKt.toCurrencyPair(symbol);
        return new Crypto(symbol, pair[1]);
    }

}
