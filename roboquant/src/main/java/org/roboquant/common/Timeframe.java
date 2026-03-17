/*
 * Copyright 2020-2026 Neural Layer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.roboquant.common;

import java.io.Serializable;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;

/**
 * A timeframe represents a period of time defined by a start time (inclusive) and end time.
 * If inclusive is set to true the end time is inclusive, exclusive otherwise.
 * The default is that the end time is exclusive.
 *
 * A timeframe instance is immutable. Like all time related logic in roboquant, it uses the Instant type to define
 * a moment in time, to avoid potential timezone inconsistencies.
 *
 * All internal trading logic uses nanoseconds as the smallest difference between two times. However, some
 * visualizations and charts might use milliseconds and the smallest time differences.
 *
 * It can be used to limit the duration of a run to that specific timeframe, for example, in a walk-forward. It can also
 * serve to limit a live-feed to a certain duration.
 */
public class Timeframe implements Serializable {

    private final Instant start;
    private final Instant end;
    private final boolean inclusive;

    /**
     * The minimum start date of a timeframe, being 1900-01-01T00:00:00Z
     */
    public static final Instant MIN = Instant.parse("1900-01-01T00:00:00Z");

    /**
     * The maximum end date of a timeframe, being 2200-01-01T00:00:00Z
     */
    public static final Instant MAX = Instant.parse("2200-01-01T00:00:00Z");

    /**
     * 365 days expressed in milliseconds.
     */
    public static final double ONE_YEAR_MILLIS = 365.0 * 24.0 * 3600.0 * 1000.0;

    /**
     * Infinite timeframe that matches any time and is typically used when no filtering is required or the
     * exact timeframe is yet unknown.
     */
    public static final Timeframe INFINITE = new Timeframe(MIN, MAX, true);

    /**
     * The empty timeframe doesn't contain any time.
     */
    public static final Timeframe EMPTY = new Timeframe(MIN, MIN);

    // Different formatters used when displaying a timeframe
    private static final DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter secondFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter milliFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public Timeframe(Instant start, Instant end) {
        this(start, end, false);
    }

    public Timeframe(Instant start, Instant end, boolean inclusive) {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("end time has to be larger or equal than start time, found " + start + " - " + end);
        }
        if (start.isBefore(MIN)) {
            throw new IllegalArgumentException("start time has to be larger or equal than " + MIN);
        }
        if (end.isAfter(MAX)) {
            throw new IllegalArgumentException("end time has to be smaller or equal than " + MAX);
        }
        this.start = start;
        this.end = end;
        this.inclusive = inclusive;
    }

    public Instant getStart() {
        return start;
    }

    public Instant getEnd() {
        return end;
    }

    public boolean isInclusive() {
        return inclusive;
    }

    /**
     * Duration of timeframe.
     */
    public Duration getDuration() {
        return Duration.between(start, end);
    }

    /**
     * Return true is this is an infinite timeframe, false otherwise.
     */
    public boolean isInfinite() {
        return this == INFINITE;
    }

    /**
     * Return true is this being a finite timeframe, false otherwise.
     */
    public boolean isFinite() {
        return this != INFINITE;
    }

    /**
     * Return true is this is an empty timeframe, false otherwise.
     */
    public boolean isEmpty() {
        return start.equals(end) && !inclusive;
    }

    /**
     * Return a new timeframe inclusive of the end value.
     */
    public Timeframe toInclusive() {
        return new Timeframe(start, end, true);
    }

    /**
     * Does the timeframe contain the provided time.
     */
    public boolean contains(Instant time) {
        if (isInfinite()) return true;
        return !time.isBefore(start) && beforeEnd(time);
    }

    private boolean beforeEnd(Instant time) {
        return time.isBefore(end) || (inclusive && time.equals(end));
    }

    /**
     * Is this timeframe within a single day given the provided zoneId.
     */
    public boolean isSingleDay(ZoneId zoneId) {
        if (start.equals(Instant.MIN) || end.equals(Instant.MAX)) return false;
        Instant realEnd = inclusive ? end : end.minusNanos(1);
        return start.atZone(zoneId).toLocalDate().equals(realEnd.atZone(zoneId).toLocalDate());
    }

    /**
     * Convert this timeframe to a timeline where each time separated by a step amount.
     */
    public List<Instant> toTimeline(TimeSpan step) {
        List<Instant> result = new ArrayList<>();
        Instant time = start;
        while (contains(time)) {
            result.add(time);
            time = TimeSpan.plus(time, step);
        }
        return result;
    }

    /**
     * Split a timeframe into two parts, one for training and one for test using the provided testSize
     * for determining the size of test. testSize should be a number between 0.0 and 1.0, for example,
     * 0.25 means use last 25% as a test timeframe.
     */
    public Timeframe[] splitTwoWay(double testSize) {
        if (testSize < 0.0 || testSize > 1.0) {
            throw new IllegalArgumentException("Test size has to between 0.0 and 1.0");
        }
        long diff = getDuration().toMillis();
        long train = (long) (diff * (1.0 - testSize));
        Instant border = start.plus(train, ChronoUnit.MILLIS);
        return new Timeframe[]{
            new Timeframe(start, border),
            new Timeframe(border, end, inclusive)
        };
    }

    /**
     * Split a timeframe into two parts, using the offset as split.
     */
    public Timeframe[] splitTwoWay(TimeSpan offset, TimeSpan overlap) {
        Instant border = TimeSpan.plus(start, offset);
        if (!contains(border)) {
            throw new IllegalArgumentException("offset should be smaller than timeframe");
        }
        return new Timeframe[]{
            new Timeframe(start, border),
            new Timeframe(TimeSpan.minus(border, overlap), end, inclusive)
        };
    }

    /**
     * Split a timeframe into two parts, using the offset as split with no overlap.
     */
    public Timeframe[] splitTwoWay(TimeSpan offset) {
        return splitTwoWay(offset, TimeSpan.ZERO);
    }

    /**
     * Split a timeframe in multiple individual timeframes each of the fixed period length.
     */
    public List<Timeframe> split(TimeSpan period, TimeSpan overlap, boolean includeRemaining) {
        List<Timeframe> result = new ArrayList<>();
        Instant begin = start;
        boolean done = false;
        while (!done) {
            Instant last = TimeSpan.plus(begin, period);
            Timeframe tf;
            if (last.isBefore(end)) {
                tf = new Timeframe(begin, last);
            } else if (last.equals(end) && inclusive) {
                tf = new Timeframe(begin, end, true);
            } else if (includeRemaining) {
                tf = new Timeframe(begin, end, inclusive);
            } else {
                tf = null;
            }
            if (tf != null) result.add(tf);
            done = tf == null || tf.end.equals(end);
            begin = TimeSpan.minus(last, overlap);
        }
        return result;
    }

    /**
     * Split with default overlap of zero and includeRemaining true.
     */
    public List<Timeframe> split(TimeSpan period) {
        return split(period, TimeSpan.ZERO, true);
    }

    /**
     * Sample one or more timeframes each of a period length.
     */
    public List<Timeframe> sample(TimeSpan period, int samples, TemporalUnit resolution, Random random) {
        if (samples < 1) {
            throw new IllegalArgumentException("samples need to be >= 1");
        }
        Instant periodEnd = TimeSpan.minus(end, period);
        if (!periodEnd.isAfter(start)) {
            throw new IllegalArgumentException("period=" + period + " too large for timeframe=" + this);
        }
        long duration = new Timeframe(start, periodEnd).getDuration().toMillis();
        if (duration <= samples) {
            throw new IllegalArgumentException("not enough data to sample " + samples);
        }
        Set<Timeframe> result = new HashSet<>();
        while (result.size() < samples) {
            long offset = random.nextLong(duration);
            Instant newStart = start.plusMillis(offset);
            if (resolution != null) {
                newStart = newStart.truncatedTo(resolution);
            }
            Instant newEnd = TimeSpan.plus(newStart, period);
            result.add(new Timeframe(newStart, newEnd));
        }
        return new ArrayList<>(result);
    }

    /**
     * Sample with default random and resolution.
     */
    public List<Timeframe> sample(TimeSpan period, int samples) {
        return sample(period, samples, null, Config.getRandom());
    }

    /**
     * Return a string representation of the timeframe.
     */
    @Override
    public String toString() {
        String s1 = start.equals(MIN) ? "MIN" : (start.equals(MAX) ? "MAX" : start.toString());
        String s2 = end.equals(MIN) ? "MIN" : (end.equals(MAX) ? "MAX" : end.toString());
        char endChar = inclusive ? ']' : '>';
        return "[" + s1 + " - " + s2 + endChar;
    }

    /**
     * Format the timeframe with appropriate resolution.
     */
    public String toPrettyString() {
        long d = getDuration().toSeconds();
        DateTimeFormatter formatter;
        if (d < 10) {
            formatter = milliFormatter;
        } else if (d < 3600 * 24) {
            formatter = secondFormatter;
        } else {
            formatter = dayFormatter;
        }

        DateTimeFormatter fmt = formatter.withZone(ZoneOffset.UTC);
        String s1 = start.equals(MIN) ? "MIN" : (start.equals(MAX) ? "MAX" : fmt.format(start));
        String s2 = end.equals(MIN) ? "MIN" : (end.equals(MAX) ? "MAX" : fmt.format(end));
        return s1 + " - " + s2;
    }

    private Instant makeValid(Instant time) {
        if (time.isBefore(MIN)) return MIN;
        if (time.isAfter(MAX)) return MAX;
        return time;
    }

    /**
     * Subtract a period from start and end time.
     */
    public Timeframe minus(TimeSpan period) {
        Instant newEnd = makeValid(TimeSpan.minus(end, period));
        boolean incl = end.equals(MAX) || inclusive;
        return new Timeframe(makeValid(TimeSpan.minus(start, period)), newEnd, incl);
    }

    /**
     * Add a period to start and end time.
     */
    public Timeframe plus(TimeSpan period) {
        Instant newEnd = makeValid(TimeSpan.plus(end, period));
        boolean incl = end.equals(MAX) || inclusive;
        return new Timeframe(makeValid(TimeSpan.plus(start, period)), newEnd, incl);
    }

    /**
     * Extend this period with a before and after period.
     */
    public Timeframe extend(TimeSpan before, TimeSpan after) {
        Instant newEnd = makeValid(TimeSpan.plus(end, after));
        boolean incl = end.equals(MAX) || inclusive;
        return new Timeframe(makeValid(TimeSpan.minus(start, before)), newEnd, incl);
    }

    /**
     * Extend with same before and after.
     */
    public Timeframe extend(TimeSpan before) {
        return extend(before, before);
    }

    /**
     * Annualize a rate based on the duration of this timeframe.
     */
    public double annualize(double rate) {
        return Math.pow(1.0 + rate, toYears()) - 1.0;
    }

    private double toYears() {
        long period = getDuration().toMillis();
        return ONE_YEAR_MILLIS / period;
    }

    /**
     * Compare timeframes based on their duration.
     */
    public int compareTo(TimeSpan other) {
        return getDuration().compareTo(other.getDuration());
    }

    // ========== Static Factory Methods ==========

    /**
     * Create a timeframe starting from 1 january of the first year until 1 january of the last year.
     */
    public static Timeframe fromYears(int first, int last, ZoneId zoneId) {
        ZonedDateTime startDate = ZonedDateTime.of(first, 1, 1, 0, 0, 0, 0, zoneId);
        ZonedDateTime stop = ZonedDateTime.of(last, 1, 1, 0, 0, 0, 0, zoneId);
        return new Timeframe(startDate.toInstant(), stop.toInstant());
    }

    /**
     * Create a timeframe from years with default UTC.
     */
    public static Timeframe fromYears(int first, int last) {
        return fromYears(first, last, ZoneOffset.UTC);
    }

    private static Instant toInstant(String str) {
        String fStr;
        switch (str.length()) {
            case 4:
                fStr = str + "-01-01T00:00:00Z";
                break;
            case 7:
                fStr = str + "-01T00:00:00Z";
                break;
            case 10:
                fStr = str + "T00:00:00Z";
                break;
            case 19:
                fStr = str + "Z";
                break;
            default:
                fStr = str;
        }
        return Instant.parse(fStr);
    }

    /**
     * Create a timeframe based on the first and last time provided.
     */
    public static Timeframe parse(String first, String last, boolean inclusive) {
        Instant start = toInstant(first);
        Instant stop = toInstant(last);
        return new Timeframe(start, stop, inclusive);
    }

    /**
     * Parse with inclusive false by default.
     */
    public static Timeframe parse(String first, String last) {
        return parse(first, last, false);
    }

    /**
     * Create a timeframe till now minus the provided period.
     */
    public static Timeframe past(TimeSpan period) {
        Instant end = Instant.now();
        return new Timeframe(TimeSpan.minus(end, period), end);
    }

    /**
     * Create a timeframe from now for the provided period.
     */
    public static Timeframe next(TimeSpan period) {
        Instant start = Instant.now();
        return new Timeframe(start, TimeSpan.plus(start, period));
    }

    /**
     * Black Monday - October 19, 1987.
     */
    public static Timeframe getBlackMonday1987() {
        return parse("1987-10-19T14:30:00Z", "1987-10-19T21:00:00Z");
    }

    /**
     * Financial Crisis of 2008.
     */
    public static Timeframe getFinancialCrisis2008() {
        return parse("2008-09-08T00:00:00Z", "2009-03-10T00:00:00Z");
    }

    /**
     * Ten Year Bull Market 2009.
     */
    public static Timeframe getTenYearBullMarket2009() {
        return parse("2009-03-10T00:00:00Z", "2019-03-10T00:00:00Z");
    }

    /**
     * Flash Crash 2010.
     */
    public static Timeframe getFlashCrash2010() {
        return parse("2010-05-06T19:30:00Z", "2010-05-06T20:15:00Z");
    }

    /**
     * Corona Crash 2020.
     */
    public static Timeframe getCoronaCrash2020() {
        return parse("2020-02-17T00:00:00Z", "2020-03-17T00:00:00Z");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timeframe timeframe = (Timeframe) o;
        return inclusive == timeframe.inclusive &&
                Objects.equals(start, timeframe.start) &&
                Objects.equals(end, timeframe.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, inclusive);
    }
}
