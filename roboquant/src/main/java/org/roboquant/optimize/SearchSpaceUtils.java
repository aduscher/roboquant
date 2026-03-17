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

package org.roboquant.optimize;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods for search spaces.
 */
public final class SearchSpaceUtils {

    private SearchSpaceUtils() {
    }

    /**
     * Generate an iterable of doubles over a closed range with a given step.
     *
     * @param start the start of the range (inclusive)
     * @param endInclusive the end of the range (inclusive)
     * @param step the step size (must be positive)
     * @return a list of doubles from start to endInclusive, incrementing by step
     */
    public static List<Double> step(double start, double endInclusive, double step) {
        if (!Double.isFinite(start)) {
            throw new IllegalArgumentException("Start must be finite");
        }
        if (!Double.isFinite(endInclusive)) {
            throw new IllegalArgumentException("End must be finite");
        }
        if (step <= 0.0) {
            throw new IllegalArgumentException("Step must be positive, was: " + step + ".");
        }
        List<Double> result = new ArrayList<>();
        double current = start;
        while (current <= endInclusive) {
            result.add(current);
            current += step;
        }
        return result;
    }
}
