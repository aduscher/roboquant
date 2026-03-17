package org.roboquant.common;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Objects;

/**
 * Currency implementation that supports regular currencies as well as cryptocurrencies. So the [currencyCode] for the
 * currency is not limited to ISO-4217 codes like the regular Java Currency class.
 *
 * This is a lightweight implementation since most of the functionality only relies on the currency code.
 *
 * When creating a new currency instance, use the [Currency.getInstance] method. This ensures only a single
 * instance of a currency exists for a given currency code and that allows for fast equality comparison.
 */
public final class Currency {

    private final String currencyCode;
    private int defaultFractionDigits;

    private Currency(String currencyCode) {
        this.currencyCode = currencyCode;
        this.defaultFractionDigits = lookupDefaultFractionDigits(currencyCode);
    }

    private static int lookupDefaultFractionDigits(String code) {
        try {
            return java.util.Currency.getInstance(code).getDefaultFractionDigits();
        } catch (IllegalArgumentException e) {
            // Falls keine ISO-Währung (z.B. Crypto), Standardwert 2
            return 2;
        }
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getDisplayName() {
        return currencyCode;
    }

    public int getDefaultFractionDigits() {
        return defaultFractionDigits;
    }

    public void setDefaultFractionDigits(int digits) {
        this.defaultFractionDigits = digits;
    }

    private static final ConcurrentHashMap<String, Currency> currencies = new ConcurrentHashMap<>();

    /**
     * Gibt eine Instanz für den Währungscode zurück.
     */
    public static Currency getInstance(String currencyCode) {
        return currencies.computeIfAbsent(currencyCode, Currency::new);
    }

    private static Currency getInstance(String currencyCode, int defaultFractionDigits) {
        Currency result = getInstance(currencyCode);
        result.setDefaultFractionDigits(defaultFractionDigits);
        return result;
    }

    // Häufig genutzte Währungen
    public static final Currency USD = getInstance("USD");
    public static final Currency EUR = getInstance("EUR");
    public static final Currency JPY = getInstance("JPY");
    public static final Currency GBP = getInstance("GBP");
    public static final Currency AUD = getInstance("AUD");
    public static final Currency CAD = getInstance("CAD");
    public static final Currency CHF = getInstance("CHF");
    public static final Currency CNY = getInstance("CNY");
    public static final Currency HKD = getInstance("HKD");
    public static final Currency NZD = getInstance("NZD");
    public static final Currency RUB = getInstance("RUB");
    public static final Currency INR = getInstance("INR");
    
    // Kryptowährungen mit spezifischen Nachkommastellen
    public static final Currency BTC = getInstance("BTC", 8);
    public static final Currency ETH = getInstance("ETH", 8);
    public static final Currency USDT = getInstance("USDT", 2);

    /**
     * Erhöht die Anzahl der Anzeige-Stellen für alle registrierten Währungen.
     */
    public static void increaseDigits(int extraDigits) {
        for (Currency c : currencies.values()) {
            c.setDefaultFractionDigits(c.getDefaultFractionDigits() + extraDigits);
        }
    }

    public static void increaseDigits() {
        increaseDigits(3);
    }

    @Override
    public String toString() {
        return getDisplayName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(currencyCode, currency.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyCode);
    }
}