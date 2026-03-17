package org.roboquant.journals.metrics;

import org.roboquant.common.Timeframe;

import java.util.ArrayList;
import java.util.List;

/**
 * Buffer that stores time instants with a maximum size.
 */
public class TimeBuffer {

    private final int size;
    private final List<java.time.Instant> data;

    public TimeBuffer(int size) {
        this.size = size;
        this.data = new ArrayList<>();
    }

    public int getSize() {
        return size;
    }

    public List<java.time.Instant> getData() {
        return data;
    }

    public void add(java.time.Instant time) {
        data.add(time);
        while (data.size() > size) {
            data.remove(0);
        }
    }

    public double eventsPerYears() {
        if (data.isEmpty()) return 0.0;
        Timeframe tf = getTimeframe();
        long avgMillis = tf.getDuration().toMillis() / data.size();
        return 3.1536E10 / avgMillis;
    }

    public Timeframe getTimeframe() {
        if (data.isEmpty()) {
            return new Timeframe(java.time.Instant.MIN, java.time.Instant.MIN);
        }
        return new org.roboquant.common.Timeframe(data.get(0), data.get(data.size() - 1));
    }

    public void clear() {
        data.clear();
    }
}
