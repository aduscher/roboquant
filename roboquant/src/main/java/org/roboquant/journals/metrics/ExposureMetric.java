package org.roboquant.journals.metrics;

import org.roboquant.common.*;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Metric that tracks exposure information (long, short, net, gross).
 */
public class ExposureMetric implements Metric {

    @Override
    public Map<String, Double> calculate(Event event, Account account, List<Signal> signals, List<Order> orders) {
        Wallet longExposure = new Wallet();
        Wallet shortExposure = new Wallet();
        Instant now = event.getTime();

        for (Map.Entry<org.roboquant.common.Asset, Position> entry : account.getPositions().entrySet()) {
            org.roboquant.common.Asset asset = entry.getKey();
            Position position = entry.getValue();
            Amount v = asset.value(position.getSize(), position.getMktPrice());
            if (position.isLong()) {
                longExposure.deposit(v);
            } else {
                shortExposure.deposit(v);
            }
        }

        Currency currency = account.getBaseCurrency();
        double longExposureValue = longExposure.convert(currency, now).getValue();
        double shortExposureValue = shortExposure.convert(currency, now).getValue();
        double netExposureValue = longExposureValue + shortExposureValue;
        double grossExposureValue = Math.abs(longExposureValue) + Math.abs(shortExposureValue);
        double total = account.getCash().convert(currency, now).getValue() + netExposureValue;

        Map<String, Double> result = new LinkedHashMap<>();
        result.put("exposure.net", netExposureValue / total);
        result.put("exposure.gross", grossExposureValue / total);
        result.put("exposure.long", longExposureValue);
        result.put("exposure.short", shortExposureValue);
        return result;
    }

}
