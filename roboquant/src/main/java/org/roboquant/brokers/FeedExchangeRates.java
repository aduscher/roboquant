package org.roboquant.brokers;

import org.roboquant.common.*;
import org.roboquant.common.Currency;
import org.roboquant.feeds.Feed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.*;
import java.util.Map.Entry;

/**
 * Use a feed with PriceItem to determine currency conversion rates.
 */
public class FeedExchangeRates implements ExchangeRates {

    private final String priceType;
    private final Map<Entry<Currency, Currency>, NavigableMap<Instant, Double>> exchangeRates = new LinkedHashMap<>();
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Create FeedExchangeRates from a feed.
     *
     * @param feed the feed to use
     * @param priceType the type of price, by default "DEFAULT"
     */
    public FeedExchangeRates(Feed feed, String priceType) {
        this.priceType = priceType;
        setRates(feed);
    }

    /**
     * Create FeedExchangeRates with default price type "DEFAULT".
     *
     * @param feed the feed to use
     */
    public FeedExchangeRates(Feed feed) {
        this(feed, "DEFAULT");
    }

    /**
     * Get the currencies that are part of these exchange rates
     */
    public Set<Currency> getCurrencies() {
        Set<Currency> result = new HashSet<>();
        for (Entry<Currency, Currency> entry : exchangeRates.keySet()) {
            result.add(entry.getKey());
            result.add(entry.getValue());
        }
        return result;
    }

    private void setRates(Feed feed) {
        for (var item : feed) {
            Instant now = item.getTime();
            Item.PriceItem priceItem = item.get(Item.PriceItem.class);
            if (priceItem != null) {
                Asset asset = priceItem.getAsset();
                double rate = priceItem.getPrice(priceType);
                try {
                    Entry<Currency, Currency> pair = toCurrencyPair(asset.getSymbol());
                    NavigableMap<Instant, Double> map = exchangeRates.getOrDefault(pair, new TreeMap<>());
                    map.put(now, rate);
                    exchangeRates.putIfAbsent(pair, map);
                } catch (RoboquantException e) {
                    logger.warn("could map asset to currency pair " + asset);
                }
            }
        }
    }

    private static Entry<Currency, Currency> toCurrencyPair(String symbol) {
        String[] parts = symbol.split("/");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Cannot convert symbol " + symbol + " to currency pair");
        }
        return new AbstractMap.SimpleEntry<>(Currency.getInstance(parts[0]), Currency.getInstance(parts[1]));
    }

    private Double find(Entry<Currency, Currency> pair, Instant time) {
        NavigableMap<Instant, Double> rates = exchangeRates.get(pair);
        if (rates != null) {
            Entry<Instant, Double> result = rates.floorEntry(time);
            if (result == null) {
                result = rates.firstEntry();
            }
            return result.getValue();
        }
        return null;
    }

    @Override
    public double getRate(Amount amount, Currency to, Instant time) {
        Currency from = amount.getCurrency();
        if (from == to || amount.getValue() == 0.0) return 1.0;

        Entry<Currency, Currency> pair = new AbstractMap.SimpleEntry<>(from, to);
        Double result = find(pair, time);
        if (result != null) return result;

        result = find(pair, time);
        if (result != null)
            return 1.0 / result;

        throw new IllegalArgumentException("No conversion for " + amount + " to " + to);
    }

}
