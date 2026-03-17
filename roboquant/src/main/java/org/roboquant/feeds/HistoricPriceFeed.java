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

import org.robok.common.Asset;
import org.robok.feeds.EventChannel;
import org.robok.feeds.HistoricFeed;
import org.roboquant.common.Event;
import org.robok.common.PriceItem;
import org.robok.common.Timeframe;
import org.roboquant.common.Timeline;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Base class that provides a foundation for data feeds that provide historic prices. It uses a sorted map to store
 * for each time one or more priceItems in memory.
 */
public class HistoricPriceFeed implements HistoricFeed {

    private final SortedMap<Instant, List<PriceItem>> events = new TreeMap<>();

    @Override
    public List<Instant> getTimeline() {
        return new ArrayList<>(events.keySet());
    }

    @Override
    public Timeframe getTimeframe() {
        if (events.isEmpty()) return Timeframe.INFINITE;
        return new Timeframe(events.firstKey(), events.lastKey(), true);
    }

    @Override
    public Set<Asset> getAssets() {
        return events.values().stream()
            .flatMap(list -> list.stream().map(PriceItem::getAsset))
            .collect(Collectors.toSet());
    }

    /**
     * Return the first event in this feed
     */
    public Event first() {
        Instant time = events.firstKey();
        return new Event(time, events.get(time));
    }

    /**
     * Return the last event in this feed
     */
    public Event last() {
        Instant time = events.lastKey();
        return new Event(time, events.get(time));
    }

    /**
     * Remove all events from this feed, releasing claimed memory.
     */
    @Override
    public void close() {
        events.clear();
    }

    /**
     * (Re)play the events of the feed using the provided EventChannel
     */
    @Override
    public void play(EventChannel channel) throws InterruptedException {
        for (Map.Entry<Instant, List<PriceItem>> entry : events.entrySet()) {
            Event event = new Event(entry.getKey(), entry.getValue());
            channel.send(event);
        }
    }

    /**
     * Add a new item to this feed at the provided time
     */
    protected synchronized void add(Instant time, PriceItem item) {
        events.computeIfAbsent(time, k -> new ArrayList<>()).add(item);
    }

    /**
     * Add all new items to this feed at the provided time
     */
    protected synchronized void addAll(Instant time, List<PriceItem> items) {
        events.computeIfAbsent(time, k -> new ArrayList<>()).addAll(items);
    }

    /**
     * Merge the events in another historic feed into this feed.
     */
    public void merge(org.robok.feeds.HistoricPriceFeed feed) {
        for (Map.Entry<Instant, List<PriceItem>> entry : feed.events.entrySet()) {
            addAll(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Provide a string representation of this feed
     */
    @Override
    public String toString() {
        if (events.isEmpty()) {
            return "events=0 assets=0";
        }
        return "events=" + events.size() + " start=" + events.firstKey() + " end=" + events.lastKey() + " assets=" + getAssets().size();
    }
}
