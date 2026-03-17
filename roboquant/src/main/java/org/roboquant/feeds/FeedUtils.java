package org.roboquant.feeds;

import org.roboquant.common.*;
import org.roboquant.common.Item.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class FeedUtils {

    private FeedUtils() {
        // Prevent instantiation
    }

    // --- FILTER ---

    // Overload for default arguments
    public static <T extends Item> List<Pair<Instant, T>> filter(Feed feed, Class<T> type) {
        return filter(feed, type, Timeframe.INFINITE, -1, t -> true);
    }

    public static <T extends Item> List<Pair<Instant, T>> filter(
            Feed feed, Class<T> type,
            Timeframe timeframe, long timeOutMillis, Predicate<T> filter) {
        EventChannel channel = new EventChannel(timeframe);
        List<Pair<Instant, T>> result = new ArrayList<>();
        CompletableFuture<Void> job = feed.playBackground(channel);

        try {
            while (true) {
                Event o = channel.receive(timeOutMillis);
                for (Item item : o.getItems()) {
                    if (type.isInstance(item)) {
                        T typedItem = type.cast(item);
                        if (filter.test(typedItem)) {
                            result.add(new Pair<>(o.getTime(), typedItem));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            channel.close();
            if (!job.isDone()) job.cancel(true);
        }
        return result;
    }

    // --- APPLY ---

    public static <T extends Item> void apply(Feed feed, Class<T> type, BiConsumer<T, Instant> block) {
        apply(feed, type, Timeframe.INFINITE, block);
    }

    public static <T extends Item> void apply(
            Feed feed,
            Class<T> type, Timeframe timeframe, BiConsumer<T, Instant> block) {
        EventChannel channel = new EventChannel(timeframe);
        CompletableFuture<Void> job = feed.playBackground(channel);

        try {
            while (true) {
                Event o = channel.receive();
                for (Item item : o.getItems()) {
                    if (type.isInstance(item)) {
                        block.accept(type.cast(item), o.getTime());
                    }
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            if (!job.isDone()) job.cancel(true);
        }
    }

    // --- APPLY EVENTS ---

    public static void applyEvents(Feed feed, Timeframe timeframe, Consumer<Event> block) {
        EventChannel channel = new EventChannel(timeframe);
        CompletableFuture<Void> job = feed.playBackground(channel);

        try {
            while (true) {
                Event o = channel.receive();
                block.accept(o);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            if (!job.isDone()) job.cancel(true);
        }
    }

    // --- TO LIST ---

    public static List<Event> toList(Feed feed) {
        return toList(feed, Timeframe.INFINITE);
    }

    public static List<Event> toList(Feed feed, Timeframe timeframe) {
        EventChannel channel = new EventChannel(timeframe);
        List<Event> result = new ArrayList<>();
        CompletableFuture<Void> job = feed.playBackground(channel);

        try {
            while (true) {
                result.add(channel.receive());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            channel.close();
            if (!job.isDone()) job.cancel(true);
        }
        return result;
    }

    // --- VALIDATE ---

    public static List<Pair<Instant, PriceItem>> validate(Feed feed) {
        return validate(feed, Timeframe.INFINITE, 0.5, "DEFAULT");
    }

    public static List<Pair<Instant, PriceItem>> validate(Feed feed, Timeframe timeframe, double maxDiff, String priceType) {
        EventChannel channel = new EventChannel(timeframe);
        CompletableFuture<Void> job = feed.playBackground(channel);

        Map<Asset, Double> lastPrices = new HashMap<>();
        List<Pair<Instant, PriceItem>> errors = new ArrayList<>();

        try {
            while (true) {
                Event o = channel.receive();
                for (Map.Entry<Asset, PriceItem> entry : o.getPrices().entrySet()) {
                    Asset asset = entry.getKey();
                    PriceItem priceItem = entry.getValue();

                    double price = priceItem.getPrice(priceType);
                    Double prev = lastPrices.get(asset);

                    if (prev != null) {
                        double diff = (price - prev) / prev;
                        if (Math.abs(diff) > maxDiff) {
                            errors.add(new Pair<>(o.getTime(), priceItem));
                        }
                    }
                    lastPrices.put(asset, price);
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException("Interrupted exception: " + e);
        } finally {
            channel.close();
            if (!job.isDone()) job.cancel(true);
        }
        return errors;
    }

    // --- TO DOUBLE ARRAY ---

    public static double[] toDoubleArray(Collection<Item.PriceItem> items) {
        return toDoubleArray(items, "DEFAULT");
    }

    public static double[] toDoubleArray(Collection<Item.PriceItem> items, String type) {
        return items.stream()
                .mapToDouble(item -> item.getPrice(type))
                .toArray();
    }

    /**
     * Simple Pair class for key-value pairs
     */
    public static class Pair<K, V> {
        private final K key;
        private final V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Pair{" + key + "=" + value + "}";
        }
    }

}