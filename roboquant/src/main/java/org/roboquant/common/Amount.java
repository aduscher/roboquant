package org.roboquant.common;

import org.roboquant.brokers.ExchangeRates;
import org.roboquant.brokers.NoExchangeRates;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.Instant;
import java.util.Locale;
import java.util.Objects;

/**
 * An amount holds the monetary [value] for a single [currency].
 *
 * For storing monetary amounts internally, it uses [Double], since it is accurate enough for trading while providing
 * performance benefits over types like BigDecimal.
 */
public final class Amount {

    private final Currency currency;
    private final double value;

    public Amount(Currency currency, double value) {
        this.currency = Objects.requireNonNull(currency);
        this.value = value;
    }

    public Amount(Currency currency, Number value) {
        this(currency, value.doubleValue());
    }

    public Amount(String currencyCode, Number value) {
        this(Currency.getInstance(currencyCode), value.doubleValue());
    }

    // --- Getters ---
    public Currency getCurrency() { return currency; }
    public double getValue() { return value; }

    // --- Logic Methods ---
    
    public Amount times(Number n) {
        return times(n.doubleValue());
    }

    public Amount times(double d) {
        return new Amount(currency, value * d);
    }

    public Amount plus(Number d) {
        return new Amount(currency, value + d.doubleValue());
    }

    public Amount div(Number d) {
        return new Amount(currency, value / d.doubleValue());
    }

    public Amount minus(Number d) {
        return new Amount(currency, value - d.doubleValue());
    }

    public Wallet plus(Amount other) {
        return new Wallet(this).plus(other);
    }

    public Wallet minus(Amount other) {
        return new Wallet(this).minus(other);
    }

    public Amount unaryMinus() {
        return new Amount(currency, -value);
    }

    public boolean isPositive() {
        return value > 0.0;
    }

    public Amount absoluteValue() {
        return new Amount(currency, Math.abs(value));
    }

    /**
     * Java lacks default parameters; we use Method Overloading.
     */
    public String formatValue() {
        return formatValue(currency.getDefaultFractionDigits(), Locale.getDefault());
    }

    public String formatValue(int fractionDigits, Locale locale) {
        NumberFormat formatEN = NumberFormat.getInstance(locale);
        formatEN.setMinimumFractionDigits(fractionDigits);
        formatEN.setMaximumFractionDigits(fractionDigits);
        return formatEN.format(value);
    }

    public BigDecimal toBigDecimal() {
        return toBigDecimal(currency.getDefaultFractionDigits());
    }

    public BigDecimal toBigDecimal(int fractionDigits) {
        return BigDecimal.valueOf(value).setScale(fractionDigits, RoundingMode.HALF_DOWN);
    }

    public Amount convert(Currency to, Instant time) {
        if (currency.equals(to))
            return this;
        if (value == 0.0)
            return new Amount(to, 0.0);

        return Amount.exchangeRates.convert(this, to, time);
    }

    public Wallet toWallet() {
        return new Wallet(this);
    }

    // --- Standard Overrides ---

    @Override
    public String toString() {
        return currency.getCurrencyCode() + " " + formatValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Amount)) return false;
        Amount amount = (Amount) o;
        return Double.compare(amount.value, value) == 0 && Objects.equals(currency, amount.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, value);
    }


    private static ExchangeRates exchangeRates = new NoExchangeRates();

    public static void registerConverter(ExchangeRates rates) {
        Objects.requireNonNull(rates, "ExchangeRates cannot be null");
        Amount.exchangeRates = rates;
    }
}
