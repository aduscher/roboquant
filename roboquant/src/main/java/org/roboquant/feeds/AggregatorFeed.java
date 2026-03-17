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

import org.roboquant.common.*;
import org.roboquant.common.Event;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Aggregate prices in a feed to a PriceBar. The aggregation period is configurable.
 * Right now there is support for aggregating the following types of price items:
 *
 * 1. PriceBar
 * 2. TradePrice
 * 3. PriceQuote (midpoint)
 * 4. OrderBook (midpoint)
 *
 * If an item is not recognized, it is ignored.
 */
public class AggregatorFeed implements Feed {

    private final Feed feed;
    private final TimeSpan aggregationPeriod;
    private final boolean remaining;

    public AggregatorFeed(Feed feed, TimeSpan aggregationPeriod) {
        this(feed, aggregationPeriod, true);
    }

    public AggregatorFeed(Feed feed, TimeSpan aggregationPeriod, boolean remaining) {
        this.feed = feed;
        this.aggregationPeriod = aggregationPeriod;
        this.remaining = remaining;
    }

    @Override
    public Timeframe getTimeframe() {
        return feed.getTimeframe().extend(aggregationPeriod);
    }

    @Override
    public CompletableFuture<Void> play(EventChannel channel) throws InterruptedException {
        EventChannel c = new EventChannel(channel.getTimeframe(), channel.getCapacity());
        Future<?> job = feed.playBackground(c);

        Map<Asset, Item.PriceBar> history = new HashMap<>();
        Instant expiration = null;
        try {
            while (true) {
                Event event = c.receive();
                Instant time = event.getTime();

                if (expiration == null) {
                    expiration = expirationTime(time);
                } else if (!time.isBefore(expiration)) {
                    Event newEvent = new Event(expiration, new ArrayList<>(history.values()));
                    channel.sendNotEmpty(newEvent);
                    history.clear();
                    do {
                        expiration = expiration.plusMillis(aggregationPeriod.toMillis());
                    } while (expiration.isBefore(time));
                }

                for (Item item : event.getItems()) {
                    Item.PriceBar pb = getPriceBar(item, aggregationPeriod);
                    if (pb == null) continue;
                    Asset asset = pb.getAsset();
                    Item.PriceBar entry = history.get(asset);
                    if (entry == null) {
                        history.put(asset, pb);
                    } else {
                        history.put(asset, addPriceBars(entry, pb));
                    }
                }
            }
        } catch (java.util.concurrent.ExecutionException e) {
            // Channel closed
        } catch (java.util.concurrent.CancellationException e) {
            // Job cancelled
        } finally {
            if (remaining && expiration != null) {
                Event newEvent = new Event(expiration, new ArrayList<>(history.values()));
                try {
                    channel.sendNotEmpty(newEvent);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            if (job != null) job.cancel(true);
        }
    }

    private Instant expirationTime(Instant time) {
        long intervalMillis = time.until(time.plus(aggregationPeriod), ChronoUnit.MILLIS);
        long adjustedInstantMillis = time.toEpochMilli() / intervalMillis * intervalMillis;
        return Instant.ofEpochMilli(adjustedInstantMillis).plusMillis(aggregationPeriod.toMillis());
    }

    private Item.PriceBar addPriceBars(Item.PriceBar a, Item.PriceBar b) {
        double high = Math.max(a.getHigh(), b.getHigh());
        double low = Math.min(a.getLow(), b.getLow());
        return new Item.PriceBar(a.getAsset(), a.getOpen(), high, low, b.getClose(), a.getVolume() + b.getVolume(), aggregationPeriod);
    }

    static Item.PriceBar getPriceBar(Item item, TimeSpan timeSpan) {
        if (item instanceof Item.PriceBar pb) {
            return new Item.PriceBar(pb.getAsset(), pb.getOpen(), pb.getHigh(), pb.getLow(), pb.getClose(), pb.getVolume(), timeSpan);
        } else if (item instanceof TradePrice) {
            TradePrice tp = (TradePrice) item;
            double price = tp.getPrice();
            double volume = tp.getVolume();
            return new PriceBar(tp.getAsset(), price, price, price, price, volume, timeSpan);
        } else if (item instanceof PriceQuote) {
            PriceQuote pq = (PriceQuote) item;
            double price = pq.getPrice("MIDPOINT");
            double volume = pq.getVolume();
            return new PriceBar(pq.getAsset(), price, price, price, price, volume, timeSpan);
        } else if (item instanceof OrderBook) {
            OrderBook ob = (OrderBook) item;
            double price = ob.getPrice("MIDPOINT");
            double volume = ob.getVolume();
            return new PriceBar(ob.getAsset(), price, price, price, price, volume, timeSpan);
        }
        return null;
    }

    @Override
    public void close() {
        feed.close();
    }

    @Override
    public CompletableFuture<Void> playBackground(EventChannel channel) {
        return feed.playBackground(channel);
    }

    @Override
    public ExecutorService getExecutor() {
        return feed.getExecutor();
    }
}
