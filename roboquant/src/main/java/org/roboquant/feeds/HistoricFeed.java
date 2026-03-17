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

package org.roboquant.feeds;

import org.roboquant.common.Timeframe;
import org.roboquant.common.Config;
import org.roboquant.common.TimeSpan;
import org.roboquant.common.TimelineKt;

import java.util.List;
import java.util.Random;

/**
 * Historic feed represents a feed of historic data, useful for back testing. Examples are CSV files with
 * stock data of the last years.
 *
 * This interface defines common functionality that can be used by subclasses implementing the Feed interface;
 * it contains access to the timeline, timeframe and assets of the feed.
 */
public interface HistoricFeed extends AssetFeed {

    /**
     * Timeline of this feed
     */
    List<java.time.Instant> getTimeline();

    /**
     * Timeframe of this feed
     */
    @Override
    default Timeframe getTimeframe() {
        return Timeframe.getTimeframe(getTimeline());
    }

    /**
     * Draw a random sampled timeframe of a certain size from the historic feed and return a timeframe that
     * represents this sample.
     */
    default List<Timeframe> sample(int size, int samples, Random random) {
        return TimelineKt.sample(getTimeline(), size, samples, random);
    }

    /**
     * Draw a random sampled timeframe of a certain size from the historic feed using the default random generator.
     */
    default List<Timeframe> sample(int size, int samples) {
        return sample(size, samples, Config.getRandom());
    }

    /**
     * Draw a single random sample from the historic feed.
     */
    default List<Timeframe> sample(int size) {
        return sample(size, 1);
    }

    /**
     * Split the timeframe of this feed in number of timeframes of equal period.
     */
    default List<Timeframe> split(TimeSpan period, TimeSpan overlap) {
        return Timeframe.split(getTimeframe(), period, overlap);
    }

    /**
     * Split the timeframe of this feed in number of timeframes of equal period with no overlap.
     */
    default List<Timeframe> split(TimeSpan period) {
        return split(period, TimeSpan.ZERO);
    }

    /**
     * Split the timeline of the feed in number of timeframes equal size.
     */
    default List<List<java.time.Instant>> split(int size) {
        return Timeline.split(getTimeline(), size);
    }

}
