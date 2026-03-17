package org.roboquant.common;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Optimized implementation of time series data that allows for easy and fast calculations.
 * The times are stored as a timeline and the values are stored as a double array.
 */
public class TimeSeries implements Iterable<Observation> {

    private final List<Instant> timeline;
    private final double[] values;

    public TimeSeries(List<Instant> timeline, double[] values) {
        if (timeline.size() != values.length) {
            throw new IllegalArgumentException("timeline and values should be of equal size");
        }
        this.timeline = timeline;
        this.values = values;
    }

    public TimeSeries(List<Observation> observations) {
        List<Instant> times = new ArrayList<>();
        double[] vals = new double[observations.size()];
        int i = 0;
        for (Observation obs : observations) {
            times.add(obs.getTime());
            vals[i++] = obs.getValue();
        }
        this.timeline = times;
        this.values = vals;
    }

    /**
     * Return the timeframe of this time-series.
     */
    public Timeframe getTimeframe() {
        return TimelineKt.getTimeframe(timeline);
    }

    /**
     * Return the size of this time-series.
     */
    public int getSize() {
        return values.length;
    }

    /**
     * Iterate over this time-series.
     */
    @Override
    public Iterator<Observation> iterator() {
        return new ObservationIterator();
    }

    /**
     * Iterate with callback.
     */
    public void forEach(BiConsumer<Instant, Double> consumer) {
        for (int i = 0; i < values.length; i++) {
            consumer.accept(timeline.get(i), values[i]);
        }
    }

    /**
     * Add a number to all values in this timeseries.
     */
    public TimeSeries plus(Number n) {
        return new TimeSeries(timeline, ExtensionsKt.plus(values, n));
    }

    /**
     * Subtract a number from all values in this timeseries.
     */
    public TimeSeries minus(Number n) {
        return new TimeSeries(timeline, ExtensionsKt.minus(values, n));
    }

    /**
     * Multiply a number to all values in this timeseries.
     */
    public TimeSeries times(Number n) {
        return new TimeSeries(timeline, ExtensionsKt.times(values, n));
    }

    /**
     * Divide a number from all values in this timeseries.
     */
    public TimeSeries div(Number n) {
        return new TimeSeries(timeline, ExtensionsKt.div(values, n));
    }

    /**
     * Create the n returns for all values.
     */
    public TimeSeries returns(int n) {
        List<Instant> newTimeline = timeline.subList(n, timeline.size());
        return new TimeSeries(newTimeline, ExtensionsKt.returns(values, n));
    }

    /**
     * Create the 1 returns for all values.
     */
    public TimeSeries returns() {
        return returns(1);
    }

    /**
     * Index the values by dividing all the values by the first value that is finite.
     */
    public TimeSeries index(double start) {
        return new TimeSeries(timeline, ExtensionsKt.index(values, start));
    }

    /**
     * Index with default start of 1.0.
     */
    public TimeSeries index() {
        return index(1.0);
    }

    /**
     * Normalize the values.
     */
    public TimeSeries normalize() {
        return new TimeSeries(timeline, ExtensionsKt.normalize(values));
    }

    /**
     * Return the observation that contains the maximum value.
     */
    public Observation max() {
        int idx = ExtensionsKt.indexOfMax(values);
        return new Observation(timeline.get(idx), values[idx]);
    }

    /**
     * Return the observation that contains the minimum value.
     */
    public Observation min() {
        int idx = ExtensionsKt.indexOfMin(values);
        return new Observation(timeline.get(idx), values[idx]);
    }

    /**
     * Return a clean time-series in which all values are finite.
     */
    public TimeSeries clean() {
        List<Instant> x = new ArrayList<>();
        double[] y = new double[values.length];
        int j = 0;
        for (int i = 0; i < values.length; i++) {
            double value = values[i];
            if (Double.isFinite(value)) {
                x.add(timeline.get(i));
                y[j++] = value;
            }
        }
        double[] trimmed = new double[j];
        System.arraycopy(y, 0, trimmed, 0, j);
        return new TimeSeries(x, trimmed);
    }

    /**
     * Return the average of all values.
     */
    public double average() {
        double sum = 0.0;
        for (double v : values) {
            sum += v;
        }
        return sum / values.length;
    }

    /**
     * Return the difference for all values.
     */
    public TimeSeries diff(int n) {
        List<Instant> newTimeline = timeline.subList(n, timeline.size());
        return new TimeSeries(newTimeline, ExtensionsKt.diff(values, n));
    }

    /**
     * Return the difference for 1 period.
     */
    public TimeSeries diff() {
        return diff(1);
    }

    /**
     * Return the sum over all values.
     */
    public double sum() {
        double sum = 0.0;
        for (double v : values) {
            sum += v;
        }
        return sum;
    }

    /**
     * Returns this timeseries grouped by the provided period and optional, the provided zoneId.
     */
    public Map<String, TimeSeries> groupBy(ChronoUnit period, ZoneId zoneId) {
        if (zoneId == null) {
            zoneId = ZoneOffset.UTC;
        }

        SimpleDateFormat formatter;
        switch (period) {
            case YEARS:
                formatter = new SimpleDateFormat("yyyy");
                break;
            case MONTHS:
                formatter = new SimpleDateFormat("yyyy-MM");
                break;
            case WEEKS:
                formatter = new SimpleDateFormat("yyyy-ww");
                break;
            case DAYS:
                formatter = new SimpleDateFormat("yyyy-DDD");
                break;
            case HOURS:
                formatter = new SimpleDateFormat("yyyy-DDD-HH");
                break;
            case MINUTES:
                formatter = new SimpleDateFormat("yyyy-DDD-HH-mm");
                break;
            default:
                throw new IllegalArgumentException("Unsupported value for period: " + period);
        }
        formatter.setTimeZone(TimeZone.getTimeZone(zoneId));

        Map<String, List<Observation>> grouped = new LinkedHashMap<>();
        for (int i = 0; i < timeline.size(); i++) {
            Instant instant = timeline.get(i);
            String key = formatter.format(Date.from(instant));
            grouped.computeIfAbsent(key, k -> new ArrayList<>())
                   .add(new Observation(instant, values[i]));
        }

        Map<String, TimeSeries> result = new LinkedHashMap<>();
        for (Map.Entry<String, List<Observation>> entry : grouped.entrySet()) {
            result.put(entry.getKey(), new TimeSeries(entry.getValue()));
        }
        return result;
    }

    /**
     * Returns the values as a double array.
     */
    public double[] toDoubleArray() {
        return values;
    }

    /**
     * Returns a list containing the observations.
     */
    public List<Observation> toList() {
        List<Observation> result = new ArrayList<>(values.length);
        for (int i = 0; i < values.length; i++) {
            result.add(new Observation(timeline.get(i), values[i]));
        }
        return result;
    }

    /**
     * Returns true if not empty, false otherwise.
     */
    public boolean isNotEmpty() {
        return values.length > 0;
    }

    /**
     * Get timeline.
     */
    public List<Instant> getTimeline() {
        return timeline;
    }

    /**
     * Get values.
     */
    public double[] getValues() {
        return values;
    }

    private class ObservationIterator implements Iterator<Observation> {
        private int count = 0;

        @Override
        public boolean hasNext() {
            return count < values.length;
        }

        @Override
        public Observation next() {
            if (count >= values.length) {
                throw new NoSuchElementException();
            }
            Observation result = new Observation(timeline.get(count), values[count]);
            count++;
            return result;
        }
    }

    /**
     * Functional interface for bi-consumer.
     */
    @FunctionalInterface
    public interface BiConsumer<T, U> {
        void accept(T t, U u);
    }
}
