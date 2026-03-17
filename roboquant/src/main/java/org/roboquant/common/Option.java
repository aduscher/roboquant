package org.roboquant.common;

import java.util.Objects;

/**
 * Option asset representing an option contract.
 */
public final class Option implements Asset {

    private final String symbol;
    private final Currency currency;

    public Option(String symbol, Currency currency) {
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
        return "Option" + Asset.SEP + symbol + Asset.SEP + currency;
    }

    @Override
    public int compareTo(Asset other) {
        return this.symbol.compareTo(other.getSymbol());
    }

    @Override
    public String toString() {
        return "Option(symbol=" + symbol + ", currency=" + currency + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return Objects.equals(symbol, option.symbol) && Objects.equals(currency, option.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, currency);
    }

    public Option withSymbol(String newSymbol) {
        return new Option(newSymbol, currency);
    }

    public Option withCurrency(Currency newCurrency) {
        return new Option(symbol, newCurrency);
    }

}
