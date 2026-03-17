package org.roboquant.common;

import java.time.Instant;
import java.util.Objects;

/**
 * This class holds the position of an asset in the portfolio. The implementation makes no assumptions about the
 * asset class, so it supports any type of asset class, ranging from stocks and options to cryptocurrencies.
 *
 * Position instances are immutable, so updating a position requires creating a new instance. The actual [size] of the
 * position is precise (doesn't lose precision like is the case with double) using the [Size] class.
 */
public final class Position {

    private final Size size;
    private final double avgPrice;
    private final double mktPrice;
    private final Instant lastUpdate;

    // --- Static Factory (Companion Object) ---

    public static Position empty() {
        return new Position(Size.ZERO, 0.0, 0.0, Instant.MIN);
    }

    // --- Constructors (Overloaded for Default Arguments) ---

    public Position(Size size, double avgPrice, double mktPrice, Instant lastUpdate) {
        this.size = Objects.requireNonNull(size);
        this.avgPrice = avgPrice;
        this.mktPrice = mktPrice;
        this.lastUpdate = Objects.requireNonNull(lastUpdate);
    }

    public Position(Size size, double avgPrice, double mktPrice) {
        this(size, avgPrice, mktPrice, Instant.MIN);
    }

    public Position(Size size, double avgPrice) {
        this(size, avgPrice, avgPrice, Instant.MIN);
    }

    public Position(Size size) {
        this(size, 0.0, 0.0, Instant.MIN);
    }

    public Size getSize() { return size; }

    public double getAvgPrice() { return avgPrice; }

    public double getMktPrice() { return mktPrice; }

    public Instant getLastUpdate() { return lastUpdate; }

    public boolean isClosed() {
        return size.isZero();
    }

    public boolean isShort() {
        return size.isNegative();
    }

    public boolean isLong() {
        return size.isPositive();
    }

    public boolean isOpen() {
        return !size.isZero();
    }

    // --- Data Class Overrides ---

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return Double.compare(position.avgPrice, avgPrice) == 0 &&
               Double.compare(position.mktPrice, mktPrice) == 0 &&
               Objects.equals(size, position.size) &&
               Objects.equals(lastUpdate, position.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, avgPrice, mktPrice, lastUpdate);
    }

    @Override
    public String toString() {
        return "Position(size=" + size + ", avgPrice=" + avgPrice + 
               ", mktPrice=" + mktPrice + ", lastUpdate=" + lastUpdate + ")";
    }
}
