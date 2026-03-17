package org.roboquant.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * This file contains the extensions for classes that are part of standard Java and Kotlin libraries.
 * Extensions for classes that are part of roboquant should not be included in this file.
 */
public final class ExtensionsKt {

    private ExtensionsKt() {
    }

    /**
     * Compare an instant to a timeframe. This operator takes into account if the timeframe is inclusive or not of the
     * defined end date.
     */
    public static int compareTo(Instant instant, Timeframe timeframe) {
        if (timeframe.isInclusive()) {
            if (instant.isAfter(timeframe.getEnd())) return 1;
            if (instant.isBefore(timeframe.getStart())) return -1;
            return 0;
        } else {
            if (!instant.isBefore(timeframe.getEnd())) return 1;
            if (instant.isBefore(timeframe.getStart())) return -1;
            return 0;
        }
    }

    /**
     * Extension function to allow numpy-like indexing for lists. To stay close to Kotlin, the end value is
     * inclusive. Returns a view on the original list, so no copy is made.
     */
    public static <T> List<T> get(List<T> list, int fromIndex, int toIndex) {
        int start = Math.max(0, fromIndex);
        int end = Math.min(list.size(), toIndex + 1);
        return list.subList(start, end);
    }

    /**
     * Add an element to a mutable collection, but only if it is not null.
     *
     * @return true if the collection has been modified, false otherwise
     */
    public static <T> boolean addNotNull(Collection<T> collection, T elem) {
        if (elem != null) {
            return collection.add(elem);
        }
        return false;
    }

    /**
     * Divide all elements in the array by a number.
     */
    public static double[] div(double[] array, Number n) {
        double[] result = array.clone();
        double value = n.doubleValue();
        for (int i = 0; i < result.length; i++) {
            result[i] /= value;
        }
        return result;
    }

    /**
     * Multiply all elements in the array by a number.
     */
    public static double[] times(double[] array, Number n) {
        double[] result = array.clone();
        double value = n.doubleValue();
        for (int i = 0; i < result.length; i++) {
            result[i] *= value;
        }
        return result;
    }

    /**
     * Subtract a number from all elements in the array.
     */
    public static double[] minus(double[] array, Number n) {
        double[] result = array.clone();
        double value = n.doubleValue();
        for (int i = 0; i < result.length; i++) {
            result[i] -= value;
        }
        return result;
    }

    /**
     * Add a number to all elements in a double array and return the result.
     */
    public static double[] plus(double[] array, Number n) {
        double[] result = array.clone();
        double value = n.doubleValue();
        for (int i = 0; i < result.length; i++) {
            result[i] += value;
        }
        return result;
    }

    /**
     * Subtract two arrays.
     */
    public static double[] minus(double[] a, double[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Arrays have to be of equal size");
        }
        double[] result = a.clone();
        for (int i = 0; i < result.length; i++) {
            result[i] -= b[i];
        }
        return result;
    }

    /**
     * Multiply two arrays.
     */
    public static double[] times(double[] a, double[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Arrays have to be of equal size");
        }
        double[] result = a.clone();
        for (int i = 0; i < result.length; i++) {
            result[i] *= b[i];
        }
        return result;
    }

    /**
     * Divide two arrays.
     */
    public static double[] div(double[] a, double[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Arrays have to be of equal size");
        }
        double[] result = a.clone();
        for (int i = 0; i < result.length; i++) {
            result[i] /= b[i];
        }
        return result;
    }

    /**
     * Add two arrays.
     */
    public static double[] plus(double[] a, double[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Arrays have to be of equal size");
        }
        double[] result = a.clone();
        for (int i = 0; i < result.length; i++) {
            result[i] += b[i];
        }
        return result;
    }

    /**
     * Remove non-finite values from a double array and return this new array.
     * The removed values include Inf and NaN values.
     */
    public static double[] clean(double[] array) {
        List<Double> result = new ArrayList<>();
        for (double value : array) {
            if (Double.isFinite(value)) {
                result.add(value);
            }
        }
        double[] output = new double[result.size()];
        for (int i = 0; i < result.size(); i++) {
            output[i] = result.get(i);
        }
        return output;
    }

    /**
     * Return the returns over the provided period.
     * The resulting array size will be one smaller than the original.
     * Formula used is: return = new/old - 1.0
     */
    public static double[] returns(double[] array, int n) {
        if (array.length <= n) {
            return new double[0];
        }
        double[] result = new double[array.length - n];
        for (int i = n; i < array.length; i++) {
            result[i - n] = array[i] / array[i - n] - 1.0;
        }
        return result;
    }

    /**
     * Return the returns over 1 period.
     */
    public static double[] returns(double[] array) {
        return ExtensionsKt.returns(array, 1);
    }

    /**
     * Returns an array with the difference between the elements over the provided period.
     */
    public static double[] diff(double[] array, int n) {
        if (array.length <= n) {
            return new double[0];
        }
        double[] result = new double[array.length - n];
        for (int i = n; i < array.length; i++) {
            result[i - n] = array[i] - array[i - n];
        }
        return result;
    }

    /**
     * Returns an array with the difference between the elements over 1 period.
     */
    public static double[] diff(double[] array) {
        return ExtensionsKt.diff(array, 1);
    }

    /**
     * Returns a normalized array.
     */
    public static double[] index(double[] array, double start) {
        double first = 1.0;
        for (double value : array) {
            if (Double.isFinite(value)) {
                first = value;
                break;
            }
        }
        first = first / start;
        double[] result = array.clone();
        for (int i = 0; i < result.length; i++) {
            result[i] = result[i] / first;
        }
        return result;
    }

    /**
     * Returns a normalized array with default start of 1.0.
     */
    public static double[] index(double[] array) {
        return ExtensionsKt.index(array, 1.0);
    }

    /**
     * Returns a normalized array.
     */
    public static double[] normalize(double[] array) {
        double[] data = ExtensionsKt.clean(array);
        double mean = 0.0;
        for (double value : data) {
            mean += value;
        }
        mean /= data.length;
        double std = new org.hipparchus.stat.descriptive.moment.StandardDeviation().evaluate(data, mean) + 0.000001;
        return ExtensionsKt.minus(ExtensionsKt.div(array, std), mean);
    }

    /**
     * Returns the index of the first maximum value.
     */
    public static int indexOfMax(double[] array) {
        if (array.length == 0) return -1;
        int maxAt = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[maxAt]) maxAt = i;
        }
        return maxAt;
    }

    /**
     * Returns the index of the first minimum value.
     */
    public static int indexOfMin(double[] array) {
        if (array.length == 0) return -1;
        int minAt = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] < array[minAt]) minAt = i;
        }
        return minAt;
    }

    /**
     * Allows manipulating strings as if they were paths. It will automatically normalize the path if required.
     */
    public static String divide(String path, String other) {
        java.nio.file.Path p = java.nio.file.Path.of(path, other).normalize();
        return p.toString();
    }

    /**
     * Return the growth rates.
     * The resulting array size will be one smaller than the original.
     * Formula used is: growthRate = new/old
     */
    public static double[] growthRates(double[] array, int n) {
        if (array.length <= n) {
            return new double[0];
        }
        double[] result = new double[array.length - n];
        for (int i = n; i < array.length; i++) {
            result[i - n] = array[i] / array[i - n];
        }
        return result;
    }

    /**
     * Return the growth rates with default n=1.
     */
    public static double[] growthRates(double[] array) {
        return ExtensionsKt.growthRates(array, 1);
    }

    /**
     * Return the log growth rates.
     * The resulting array size will be one smaller than the original.
     * Formula used is: logGrowthRate = ln(new/old)
     */
    public static double[] logGrowthRates(double[] array, int n) {
        if (array.length <= n) {
            return new double[0];
        }
        double[] result = new double[array.length - n];
        for (int i = 1; i < array.length; i++) {
            result[i - n] = Math.log(array[i] / array[i - n]);
        }
        return result;
    }

    /**
     * Return the log growth rates with default n=1.
     */
    public static double[] logGrowthRates(double[] array) {
        return ExtensionsKt.logGrowthRates(array, 1);
    }

    /**
     * Is this value zero, this implementation allows for small rounding errors.
     */
    public static boolean isZero(double value) {
        return Math.abs(value) < Config.EPS;
    }

    /**
     * Return true if this is a non-zero number, allows for small rounding errors.
     */
    public static boolean isNonZero(double value) {
        return Math.abs(value) >= Config.EPS;
    }

    /**
     * The number as percentage. For example, 10.percent equals 0.01
     */
    public static double percent(Number number) {
        return number.doubleValue() / 100.0;
    }

    /**
     * The number as bips. For example, 10.bips equals 0.0001
     */
    public static double bips(Number number) {
        return number.doubleValue() / 10_000.0;
    }

    /**
     * Return a rounded number with the specified number of fractions as a BigDecimal.
     */
    public static BigDecimal round(Number number, int fractions) {
        return BigDecimal.valueOf(number.doubleValue()).setScale(fractions, RoundingMode.HALF_DOWN);
    }

    /**
     * Return a rounded number with default 2 fractions.
     */
    public static BigDecimal round(Number number) {
        return ExtensionsKt.round(number, 2);
    }

    /**
     * Convert a string to a currency pair. Returns null if it could not determine the currencies.
     */
    public static Currency[] toCurrencyPair(String str) {
        String[] separators = {"_", "-", " ", "/", ".", ":"};
        String[] codes = str.split("[_\\- /.:]");
        
        if (codes.length == 2) {
            Currency c1 = Currency.getInstance(codes[0].toUpperCase(Locale.ROOT));
            Currency c2 = Currency.getInstance(codes[1].toUpperCase(Locale.ROOT));
            return new Currency[]{c1, c2};
        } else if (codes.length == 1 && str.length() == 6) {
            Currency c1 = Currency.getInstance(str.substring(0, 3).toUpperCase(Locale.ROOT));
            Currency c2 = Currency.getInstance(str.substring(3, 6).toUpperCase(Locale.ROOT));
            return new Currency[]{c1, c2};
        } else {
            throw new IllegalArgumentException("Not a recognized format");
        }
    }

    /**
     * Extension to use sumOf for Amount values.
     * The result is of type Wallet, even if summing over a single currency.
     */
    public static <T> Wallet sumOf(Collection<T> collection, java.util.function.Function<T, Amount> selector) {
        Wallet result = new Wallet();
        if (collection.isEmpty()) return result;
        
        T first = collection.iterator().next();
        Amount firstAmount = selector.apply(first);
        Currency currency = firstAmount.getCurrency();
        double value = 0.0;
        boolean singleCurrency = true;
        
        for (T item : collection) {
            Amount amount = selector.apply(item);
            if (singleCurrency) {
                if (amount.getCurrency().equals(currency)) {
                    value += amount.getValue();
                } else {
                    singleCurrency = false;
                    result.deposit(new Amount(currency, value));
                    result.deposit(amount);
                }
            } else {
                result.deposit(amount);
            }
        }
        
        if (singleCurrency) {
            return new Amount(currency, value).toWallet();
        } else {
            return result;
        }
    }
}
