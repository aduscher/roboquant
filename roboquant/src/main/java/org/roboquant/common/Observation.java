package org.roboquant.common;

import java.time.Instant;
import java.util.Objects;

/**
 * An observation represents a single value of the type Double at a precise moment in time.
 * Observations can be compared by their time.
 */
public class Observation implements Comparable<Observation> {

    private final Instant time;
    private final double value;

    public Observation(Instant time, double value) {
        this.time = time;
        this.value = value;
    }

    public Instant getTime() {
        return time;
    }

    public double getValue() {
        return value;
    }

    /**
     * Compare to other observation based on their time, and NOT their value.
     */
    @Override
    public int compareTo(Observation other) {
        return time.compareTo(other.time);
    }

    public Observation copy() {
        return new Observation(time, value);
    }

    public Observation copy(Instant time, double value) {
        return new Observation(time, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Observation that = (Observation) o;
        return Double.compare(that.value, value) == 0 &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, value);
    }

    @Override
    public String toString() {
        return "Observation{" +
                "time=" + time +
                ", value=" + value +
                '}';
    }
}
