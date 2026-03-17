package org.roboquant.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Represents the size of orders, positions, and trades. This implementation is precise up to 8 decimals, ensuring that
 * order and position sizes are accurate enough when dealing with most fractional orders.
 */
public final class Size implements Comparable<Size> {

    private static final int SCALE = 8;
    private static final long FRACTION = 100_000_000L;
    private static final double DOUBLE_FRACTION = 100_000_000.0;
    private static final BigDecimal BD_FRACTION = BigDecimal.valueOf(FRACTION);

    public static final Size ZERO = new Size(0L);
    public static final Size ONE = new Size(FRACTION);

    /**
     * Equivalent to the companion object's fromUnderlyingValue
     */
    public static Size fromUnderlyingValue(long value) {
        return new Size(value);
    }

    // --- Instance Field ---
    private final long value;

    
    private Size(long value) {
        this.value = value;
    }

    public Size(int value) {
        this(value * FRACTION);
    }

    public Size(BigDecimal value) {
      this(value.multiply(BD_FRACTION).longValueExact());
    }

    public Size(double value) {
        this(BigDecimal.valueOf(value).multiply(BD_FRACTION).longValue());
    }

    public Size(String value) {
        this(new BigDecimal(value));
    }

    // --- Properties / Methods ---

    public double toDouble() {
        return value / DOUBLE_FRACTION;
    }

    public int toInt() {
        return (int) (value / FRACTION);
    }

    public BigDecimal toBigDecimal() {
        return BigDecimal.valueOf(value).setScale(SCALE, RoundingMode.UNNECESSARY).divide(BD_FRACTION, RoundingMode.UNNECESSARY);
    }

    public boolean isZero() {
        return value == 0L;
    }

    public boolean isNonZero() {
        return value != 0L;
    }

    public boolean isPositive() {
        return value > 0;
    }

    public boolean isNegative() {
        return value < 0;
    }

    public boolean isFractional() {
        return (value % FRACTION) != 0L;
    }

    public Size absoluteValue() {
        return new Size(Math.abs(value));
    }

    public int getSign() {
        return Long.signum(value);
    }

    public Size round(int scale) {
        return new Size(this.toBigDecimal().setScale(scale, RoundingMode.DOWN));
    }

    // --- Operator Overloads (as standard methods) ---

    public Size multiply(Number other) {
        return new Size(this.toDouble() * other.doubleValue());
    }

    public Size divide(Number other) {
        return new Size(this.toDouble() / other.doubleValue());
    }

    public Size plus(Size other) {
        return new Size(this.value + other.value);
    }

    public Size minus(Size other) {
        return new Size(this.value - other.value);
    }

    public Size unaryMinus() {
        return new Size(-this.value);
    }

    // --- Comparison and Equality ---

    public int compareTo(int other) {
        return this.compareTo(new Size(other));
    }

    @Override
    public int compareTo(Size other) {
        return Long.compare(this.value, other.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return value == size.value;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(value);
    }

    @Override
    public String toString() {
        return toBigDecimal().stripTrailingZeros().toPlainString();
    }
}
