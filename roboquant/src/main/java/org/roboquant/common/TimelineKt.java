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

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Timeline is an ordered list of Instant instances, sorted from old to new. 
 * Every Instant entry in the list is unique.
 * 
 * This class provides utility methods for working with timelines.
 */
public final class TimelineKt {

    private TimelineKt() {
    }

    /**
     * Timeline is an ordered list of Instant instances.
     */
    public interface Timeline extends List<Instant> {
    }

    /**
     * Draw random sampled timeframes of a certain size from the timeline and return
     * the list of timeframes. The default is to return one sample.
     */
    public static List<Timeframe> sample(List<Instant> timeline, int size, int samples, Random random) {
        List<Timeframe> result = new ArrayList<>();
        int maxInt = timeline.size() - size;
        for (int i = 0; i < samples; i++) {
            int start = random.nextInt(maxInt);
            Timeframe sampleTimeframe = new Timeframe(timeline.get(start), timeline.get(start + size));
            result.add(sampleTimeframe);
        }
        return result;
    }

    /**
     * Draw one random sampled timeframe.
     */
    public static List<Timeframe> sample(List<Instant> timeline, int size, Random random) {
        return sample(timeline, size, 1, random);
    }

    /**
     * Draw one random sampled timeframe with default random.
     */
    public static List<Timeframe> sample(List<Instant> timeline, int size) {
        return sample(timeline, size, 1, Config.getRandom());
    }

    /**
     * Return the index of the time that is closest to the provided time but doesn't exceed it.
     * So it is the most recent time but without looking into the future.
     * If no such time is found, return null.
     */
    public static Integer latestNotAfter(List<Instant> timeline, Instant time) {
        int idx = Collections.binarySearch(timeline, time);
        if (idx < 0) {
            idx = -idx - 2;
        }
        return idx >= 0 ? idx : null;
    }

    /**
     * Return the index of the time that is closest to the provided time but is not before it.
     * If no such time is found, this method returns null.
     */
    public static Integer earliestNotBefore(List<Instant> timeline, Instant time) {
        int idx = Collections.binarySearch(timeline, time);
        if (idx < 0) {
            idx = -idx - 1;
        }
        return idx < timeline.size() ? idx : null;
    }

    /**
     * Return the Timeframe matching the list of events.
     * The collection has to be chronologically ordered.
     */
    public static Timeframe getTimeframe(List<Instant> timeline) {
        if (timeline.isEmpty()) {
            return Timeframe.EMPTY;
        }
        Instant first = timeline.get(0);
        Instant last = timeline.get(timeline.size() - 1);
        return new Timeframe(first, last, true);
    }

    /**
     * Split the timeline in chunks of size and return the corresponding timeframes.
     * The last timeframe can be smaller than the requested size if there aren't enough entries remaining.
     */
    public static List<Timeframe> split(List<Instant> timeline, int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("Minimum requires 2 elements in timeline");
        }
        
        List<Timeframe> result = new ArrayList<>();
        List<Instant> chunk = new ArrayList<>();
        
        for (Instant instant : timeline) {
            chunk.add(instant);
            if (chunk.size() == size) {
                result.add(new Timeframe(chunk.get(0), chunk.get(chunk.size() - 1)));
                chunk = new ArrayList<>();
            }
        }
        
        // Add remaining items if any
        if (chunk.size() > 1) {
            result.add(new Timeframe(chunk.get(0), chunk.get(chunk.size() - 1)));
        }
        
        // Make the last timeframe inclusive
        if (!result.isEmpty()) {
            Timeframe last = result.get(result.size() - 1);
            result.set(result.size() - 1, new Timeframe(last.getStart(), last.getEnd(), true));
        }
        
        return result;
    }
}
