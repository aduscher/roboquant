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

package org.roboquant.journals.metrics;

import org.roboquant.common.Account;
import org.roboquant.common.Event;
import org.roboquant.common.Order;
import org.roboquant.common.PriceItem;
import org.roboquant.common.Signal;
import org.roboquant.common.Timeframe;
import org.roboquant.common.TimelineKt;
import org.roboquant.feeds.AssetFeed;
import org.roboquant.feeds.EventChannel;

import java.time.Instant;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Records events that can be used to display them in a graph or perform post-run analysis.
 * This metric also implements the AssetFeed API, so recorded events can be replayed as a normal feed.
 *
 * This metric stores events internally and does not return them to a MetricsLogger. It resets its
 * state when reset() is invoked.
 *
 * @param timeSpan time span to record (default 1 year)
 */
public class EventRecorderMetric implements org.roboquant.journals.metrics.Metric, AssetFeed {

    private final org.roboquant.common.TimeSpan timeSpan;
    private final LinkedList<Event> events = new LinkedList<>();

    public EventRecorderMetric(org.roboquant.common.TimeSpan timeSpan) {
        this.timeSpan = timeSpan;
    }

    public EventRecorderMetric() {
        this(org.roboquant.common.TimeSpan.years(1));
    }

    @Override
    public Map<String, Double> calculate(Event event, Account account, List<Signal> signals, List<Order> orders) {
        synchronized (events) {
            events.add(event);
            Instant cutOff = event.getTime().minus(timeSpan);
            while (!events.isEmpty() && events.getFirst().getTime().isBefore(cutOff)) {
                events.removeFirst();
            }
        }
        return java.util.Collections.emptyMap();
    }

    @Override
    public void reset() {
        synchronized (events) {
            events.clear();
        }
    }

    @Override
    public Set<org.roboquant.common.Asset> getAssets() {
        synchronized (events) {
            Set<org.roboquant.common.Asset> result = new HashSet<>();
            for (Event event : events) {
                for (PriceItem item : event.getItems().filterIsInstance(PriceItem.class)) {
                    result.add(item.getAsset());
                }
            }
            return result;
        }
    }

    @Override
    public Timeframe getTimeframe() {
        List<Instant> timeline = new LinkedList<>();
        synchronized (events) {
            for (Event event : events) {
                timeline.add(event.getTime());
            }
        }
        return TimelineKt.getTimeframe(timeline);
    }

    /**
     * Return the timeline of the events captured.
     */
    public List<Instant> getTimeline() {
        List<Instant> result = new LinkedList<>();
        synchronized (events) {
            for (Event event : events) {
                result.add(event.getTime());
            }
        }
        return result;
    }

    @Override
    public CompletableFuture<Void> play(EventChannel channel) throws InterruptedException {
        List<Event> localEvents;
        synchronized (events) {
            localEvents = new LinkedList<>(events);
        }
        for (Event event : localEvents) {
            channel.send(event);
        }
        return CompletableFuture.completedFuture(null);
    }
}
