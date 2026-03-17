package org.roboquant.common;

import java.util.Collection;

public final class Amounts {

    private Amounts() {} // Prevent instantiation

    public static Amount EUR(Number n) { return new Amount(Currency.getInstance("EUR"), n); }
    public static Amount USD(Number n) { return new Amount(Currency.getInstance("USD"), n); }
    public static Amount JPY(Number n) { return new Amount(Currency.getInstance("JPY"), n); }
    public static Amount GBP(Number n) { return new Amount(Currency.getInstance("GBP"), n); }
    public static Amount BTC(Number n) { return new Amount(Currency.getInstance("BTC"), n); }
    // ... add other currencies as needed ...

    /**
     * Transpilation of the Collection<Amount>.toWallet() extension
     */
    public static Wallet toWallet(Collection<Amount> amounts) {
        Wallet wallet = new Wallet();
        for (Amount a : amounts) {
            wallet.deposit(a); // Or use the specific sum logic from your Wallet class
        }
        return wallet;
    }
}