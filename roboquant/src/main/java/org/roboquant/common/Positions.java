package org.roboquant.common;

import java.util.Map;
import java.util.stream.Collectors;

public final class Positions {
    
    private Positions() {} // Static utility class

    /**
     * Filters for long positions
     */
    public static Map<Asset, Position> getLong(Map<Asset, Position> positions) {
        return positions.entrySet().stream()
                .filter(e -> e.getValue().isLong())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Filters for short positions
     */
    public static Map<Asset, Position> getShort(Map<Asset, Position> positions) {
        return positions.entrySet().stream()
                .filter(e -> e.getValue().isShort())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Calculates total market value
     */
    public static Wallet marketValue(Map<Asset, Position> positions) {
        Wallet result = new Wallet();
        for (Map.Entry<Asset, Position> entry : positions.entrySet()) {
            Asset asset = entry.getKey();
            Position pos = entry.getValue();
            Amount positionValue = asset.value(pos.getSize(), pos.getMktPrice());
            result.deposit(positionValue);
        }
        return result;
    }

    /**
     * Calculates total P&L
     */
    public static Wallet pnl(Map<Asset, Position> positions) {
        Wallet result = new Wallet();
        for (Map.Entry<Asset, Position> entry : positions.entrySet()) {
            Asset asset = entry.getKey();
            Position pos = entry.getValue();
            Amount pnlValue = asset.value(pos.getSize(), pos.getMktPrice() - pos.getAvgPrice());
            result.deposit(pnlValue);
        }
        return result;
    }

    /**
     * Calculates total exposure
     */
    public static Wallet exposure(Map<Asset, Position> positions) {
        Wallet result = new Wallet();
        for (Map.Entry<Asset, Position> entry : positions.entrySet()) {
            Asset asset = entry.getKey();
            Position pos = entry.getValue();
            Amount exposureValue = asset.value(pos.getSize().absoluteValue(), pos.getMktPrice());
            result.deposit(exposureValue);
        }
        return result;
    }
}
