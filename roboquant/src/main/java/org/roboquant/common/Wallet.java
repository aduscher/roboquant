package org.roboquant.common;

import java.time.Instant;
import java.util.*;

/**
 * A wallet holds the amounts of different currencies. For example, a single instance of a Wallet can
 * hold both USD and EUR amounts.
 *
 * You can add other currencies to a Wallet instance. If the currency is already present in the Wallet, it
 * will be added to the existing amount, otherwise the currency and amount will be added.
 *
 * It is used throughout roboquant to support trading in assets with different currency denominations.
 *
 * Wallet by itself will never convert currencies when depositing or withdrawing amounts. But you can invoke
 * the convert method if you want to do so.
 */
public class Wallet implements Cloneable {

    private final IdentityHashMap<Currency, Double> data;

    public Wallet() {
        this.data = new IdentityHashMap<>(1);
    }

    public Wallet(Amount amount) {
        this();
        this.data.put(amount.getCurrency(), amount.getValue());
    }

    private Wallet(IdentityHashMap<Currency, Double> data) {
        this.data = data;
    }

    /**
     * Return the currencies that are hold in this wallet.
     */
    public Set<Currency> getCurrencies() {
        return data.keySet();
    }

    /**
     * Get the amount for a certain currency. If the currency is not found, a zero Amount will be returned.
     */
    public Amount getAmount(Currency currency) {
        return new Amount(currency, get(currency));
    }

    /**
     * Return the value for a certain currency. If the currency is not found, 0.0 will be returned.
     */
    public double get(Currency currency) {
        Double value = data.get(currency);
        return value != null ? value : 0.0;
    }

    /**
     * Is this wallet instance empty.
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * Is this wallet instance not empty.
     */
    public boolean isNotEmpty() {
        return !data.isEmpty();
    }

    /**
     * Add operator + to allow for wallet + wallet.
     */
    public Wallet plus(Wallet other) {
        Wallet result = clone();
        result.deposit(other);
        return result;
    }

    /**
     * Minus operator to allow for wallet - wallet.
     */
    public Wallet minus(Wallet other) {
        Wallet result = clone();
        result.withdraw(other);
        return result;
    }

    /**
     * Plus operator to allow for wallet + amount.
     */
    public Wallet plus(Amount amount) {
        Wallet result = clone();
        result.deposit(amount);
        return result;
    }

    /**
     * Minus operator to allow for wallet - amount.
     */
    public Wallet minus(Amount amount) {
        Wallet result = clone();
        result.withdraw(amount);
        return result;
    }

    /**
     * Times operator to allow for wallet * number.
     */
    public Wallet times(Number n) {
        Wallet result = clone();
        double value = n.doubleValue();
        for (Map.Entry<Currency, Double> entry : result.data.entrySet()) {
            result.data.put(entry.getKey(), entry.getValue() * value);
        }
        return result;
    }

    /**
     * Div operator to allow for wallet / number.
     */
    public Wallet div(Number n) {
        Wallet result = clone();
        double value = n.doubleValue();
        for (Map.Entry<Currency, Double> entry : result.data.entrySet()) {
            result.data.put(entry.getKey(), entry.getValue() / value);
        }
        return result;
    }

    /**
     * Set a monetary value. If the currency already exist, its value will be overwritten, otherwise a new entry
     * will be created. If the new value is zero, the entry will be removed since a wallet doesn't contain zero values.
     */
    public void set(Currency currency, double value) {
        if (Math.abs(value) < Config.EPS) {
            data.remove(currency);
        } else {
            data.put(currency, value);
        }
    }

    /**
     * Deposit a monetary amount. If the currency already exists, it will be added to the existing value,
     * otherwise a new entry will be created.
     */
    public void deposit(Amount amount) {
        Currency ccy = amount.getCurrency();
        double oldValue = get(ccy);
        set(ccy, amount.getValue() + oldValue);
    }

    /**
     * Deposit a value of a certain currency.
     */
    public void deposit(Currency currency, double value) {
        double oldValue = get(currency);
        double newValue = value + oldValue;
        if (Math.abs(newValue) < Config.EPS) {
            data.remove(currency);
        } else {
            data.put(currency, newValue);
        }
    }

    /**
     * Deposit the amount held in another Wallet instance into this one.
     */
    public void deposit(Wallet other) {
        assert other != this;
        for (Map.Entry<Currency, Double> entry : other.data.entrySet()) {
            deposit(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Withdraw a monetary amount.
     */
    public void withdraw(Amount amount) {
        deposit(amount.getCurrency(), -amount.getValue());
    }

    /**
     * Withdraw the amount held in another Wallet instance into this one.
     */
    public void withdraw(Wallet other) {
        assert other != this;
        for (Map.Entry<Currency, Double> entry : other.data.entrySet()) {
            deposit(entry.getKey(), -entry.getValue());
        }
    }


    /**
     * Convert this Wallet into a single currency amount. Optionally a time can be provided,
     * the default is Instant.now().
     */
    public Amount convert(Currency currency, Instant time) {
        if (time == null) {
            time = Instant.now();
        }

        // Optimization for single currency trading
        if (data.size() == 1 && data.containsKey(currency)) {
            return new Amount(currency, data.get(currency));
        }

        double sum = 0.0;
        for (Amount amount : toAmounts()) {
            sum += amount.convert(currency, time).getValue();
        }
        return new Amount(currency, sum);
    }

    /**
     * Does the wallet contain multiple currencies.
     */
    public boolean isMultiCurrency() {
        return getCurrencies().size() > 1;
    }

    /**
     * Create a clone of this wallet.
     */
    @SuppressWarnings("unchecked")
    public Wallet clone() {
        return new Wallet((IdentityHashMap<Currency, Double>) data.clone());
    }

    /**
     * Clear this Wallet instance, removing all the amounts it is holding.
     */
    public void clear() {
        data.clear();
    }

    /**
     * Provide a list representation of the amount held in this wallet.
     */
    private List<Amount> toAmounts() {
        List<Amount> result = new ArrayList<>();
        for (Map.Entry<Currency, Double> entry : data.entrySet()) {
            result.add(new Amount(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    /**
     * Returns a map where the key is the Currency and the value is the amount.
     */
    public Map<Currency, Double> toMap() {
        return new HashMap<>(data);
    }

    /**
     * Create a string representation of this wallet.
     */
    @Override
    public String toString() {
        List<Amount> amounts = toAmounts();
        amounts.sort(Comparator.comparing(a -> a.getCurrency().getCurrencyCode()));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < amounts.size(); i++) {
            if (i > 0) sb.append(" + ");
            sb.append(amounts.get(i));
        }
        return sb.toString();
    }

    /**
     * A wallet equals another wallet if they hold the same currencies and corresponding amounts.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        org.robok.common.Wallet wallet = (org.robok.common.Wallet) o;
        return toMap().equals(wallet.toMap());
    }

    /**
     * The hashcode of the wallet.
     */
    @Override
    public int hashCode() {
        return data.hashCode();
    }

    /**
     * Create a Wallet based on the amount.
     */
    public static Wallet fromAmount(Amount amount) {
        IdentityHashMap<Currency, Double> map = new IdentityHashMap<>(1);
        map.put(amount.getCurrency(), amount.getValue());
        return new Wallet(map);
    }

    /**
     * Create a Wallet based on the amounts.
     */
    public static Wallet fromAmounts(Amount... amounts) {
        Wallet wallet = new Wallet();
        for (Amount amount : amounts) {
            wallet.deposit(amount);
        }
        return wallet;
    }
}
