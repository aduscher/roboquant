package org.roboquant.journals.metrics;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Utility methods for metrics.
 */
public final class MetricUtils {

    private MetricUtils() {
    }

    /**
     * Convert pairs of String and Number to metric results. Any Number will be converted to Double.
     */
    public static Map<String, Double> metricResultsOf(Map.Entry<String, ? extends Number>... metricResults) {
        Map<String, Double> result = new LinkedHashMap<>(Math.max(metricResults.length * 2, 16));
        for (Map.Entry<String, ? extends Number> entry : metricResults) {
            result.put(entry.getKey(), entry.getValue().doubleValue());
        }
        return result;
    }
}
