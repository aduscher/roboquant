package org.roboquant.common;

/**
 * Signal provides a rating for an {@link Asset} and is typically created by a strategy.
 *
 * It depends on the signalConverter how these signals are translated into actual orders, but possible scenarios are:
 *
 * - A BUY rating results in going long for that asset. A SELL rating results in going short
 * - The target price will become an additional take profit order
 * - The probability determines the stake (volume) of the order. The more confident, the larger the stake
 *
 * @property asset The asset for which this rating applies
 * @property rating The rating for the asset, typically between -1.0 and 1.0
 * @property type The type of signal, entry, exit or both. {@link SignalType#BOTH} is the default.
 * @property tag Optional extra tag, for example, the strategy name that generated the signal, default is empty String
 */
public class Signal {
    /**
     * Default value for a BUY rating.
     */
    public static final double BUY = 1.0;

    /**
     * Default value for a SELL rating.
     */
    public static final double SELL = -1.0;

    private final Asset asset;
    private final double rating;
    private final SignalType type;
    private final String tag;

    public Signal(Asset asset, double rating, SignalType type, String tag) {
        this.asset = asset;
        this.rating = rating;
        this.type = type != null ? type : SignalType.BOTH;
        this.tag = tag != null ? tag : "";
    }

    public Asset getAsset() {
        return asset;
    }

    public double getRating() {
        return rating;
    }

    public SignalType getType() {
        return type;
    }

    public String getTag() {
        return tag;
    }

    /**
     * Is this signal (also) an exit signal, so to close or decrease a position?
     */
    public boolean getExit() {
        return type == SignalType.EXIT || type == SignalType.BOTH;
    }

    /**
     * Returns true if this signal can function as an entry signal, false otherwise
     */
    public boolean getEntry() {
        return type == SignalType.ENTRY || type == SignalType.BOTH;
    }

    /**
     * Does this signal conflict with an other signal. Two signals conflict if they contain the same asset but
     * opposite ratings. So one signal has a positive outlook and the other one is negative.
     */
    public boolean conflicts(Signal other) {
        return asset.equals(other.getAsset()) && getDirection() != other.getDirection();
    }

    /**
     * Return the direction of the rating, -1 for negative ratings, 1 for positive ratings and 0 otherwise (HOLD rating)
     */
    public int getDirection() {
        if (isBuy()) return 1;
        if (isSell()) return -1;
        return 0;
    }

    /**
     * Is this a positive rating, so a BUY or an OUTPERFORM rating
     */
    public boolean isBuy() {
        return rating > 0.0;
    }

    /**
     * Is this a negative rating, so a SELL or UNDERPERFORM rating
     */
    public boolean isSell() {
        return rating < 0.0;
    }

    /**
     * Create a BUY signal.
     */
    public static Signal buy(Asset asset) {
        return new Signal(asset, BUY, SignalType.BOTH, "");
    }

    /**
     * Create a BUY signal with type and tag.
     */
    public static Signal buy(Asset asset, SignalType type, String tag) {
        return new Signal(asset, BUY, type, tag);
    }

    /**
     * Create a sell signal.
     */
    public static Signal sell(Asset asset) {
        return new Signal(asset, SELL, SignalType.BOTH, "");
    }

    /**
     * Create a sell signal with type and tag.
     */
    public static Signal sell(Asset asset, SignalType type, String tag) {
        return new Signal(asset, SELL, type, tag);
    }
}
