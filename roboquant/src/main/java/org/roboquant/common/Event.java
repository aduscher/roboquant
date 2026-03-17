package org.roboquant.common;

import org.roboquant.common.Item.PriceItem;

import java.time.Instant;
import java.util.*;

/**
 * An event contains a list of [items] that all happened at the same moment in [time]. An [Item]
 * can be anything, but a common use case is price items like candlesticks.
 */
public class Event implements Comparable<Event> {

    private final Instant time;
    private final List<Item> items;
    
    // Cache für das "by lazy" Verhalten
    private volatile Map<Asset, PriceItem> pricesCache = null;

    public Event(Instant time, List<Item> items) {
        this.time = Objects.requireNonNull(time);
        this.items = List.copyOf(items); // Immutable Copy wie in Kotlin
    }

    public Instant getTime() {
        return time;
    }

    public List<Item> getItems() {
        return items;
    }

    /**
     * Implementierung von 'by lazy' in Java.
     */
    public Map<Asset, PriceItem> getPrices() {
        Map<Asset, PriceItem> result = pricesCache;
        if (result == null) {
            synchronized (this) {
                result = pricesCache;
                if (result == null) {
                    result = new HashMap<>(items.size());
                    for (Item item : items) {
                        if (item instanceof PriceItem) {
                            PriceItem priceItem = (PriceItem) item;
                            result.put(priceItem.getAsset(), priceItem);
                        }
                    }
                    pricesCache = Collections.unmodifiableMap(result);
                }
            }
        }
        return pricesCache;
    }

    public static Event empty() {
        return empty(Instant.now());
    }

    public static Event empty(Instant time) {
        return new Event(time, Collections.emptyList());
    }

    /**
     * Überladene Methoden für Default-Parameter.
     */
    public Double getPrice(Asset asset) {
        return getPrice(asset, "DEFAULT");
    }

    public Double getPrice(Asset asset, String type) {
        PriceItem item = getPrices().get(asset);
        return (item != null) ? item.getPrice(type) : null;
    }

    @Override
    public int compareTo(Event other) {
        return this.time.compareTo(other.time);
    }

    public boolean isNotEmpty() {
        return !items.isEmpty();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public String toString() {
        return "Event(time=" + time + " actions=" + items.size() + ")";
    }

   }