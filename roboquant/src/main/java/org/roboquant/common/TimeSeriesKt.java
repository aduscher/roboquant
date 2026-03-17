package org.roboquant.common;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Extension functions for TimeSeries operations.
 */
public final class TimeSeriesKt {

    private TimeSeriesKt() {
    }

    public static Map<String, TimeSeries> index(Map<String, TimeSeries> map, double start) {
        Map<String, TimeSeries> result = new LinkedHashMap<>();
        for (Map.Entry<String, TimeSeries> entry : map.entrySet()) {
            result.put(entry.getKey(), entry.getValue().index(start));
        }
        return result;
    }

    public static Map<String, TimeSeries> index(Map<String, TimeSeries> map) {
        return index(map, 1.0);
    }

}
