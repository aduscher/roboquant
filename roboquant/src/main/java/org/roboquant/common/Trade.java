package org.roboquant.common;

import java.time.Instant;
import java.util.Objects;

/**
 * Represents a trade that happened as a consequence of the (partial) execution of an order.
 *
 * @property asset the underlying asset
 * @property time the time of the trade
 * @property size the size of the trade
 * @property price the average price paid
 * @property pnl the realized pnl
 */
public class Trade {

    private final Asset asset;
    private final Instant time;
    private final Size size;
    private final double price;
    private final double pnl;

    public Trade(Asset asset, Instant time, Size size, double price, double pnl) {
        this.asset = asset;
        this.time = time;
        this.size = size;
        this.price = price;
        this.pnl = pnl;
    }

    public Asset getAsset() {
        return asset;
    }

    public Instant getTime() {
        return time;
    }

    public Size getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public double getPnl() {
        return pnl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trade trade = (Trade) o;
        return Double.compare(trade.price, price) == 0 &&
                Double.compare(trade.pnl, pnl) == 0 &&
                Objects.equals(asset, trade.asset) &&
                Objects.equals(time, trade.time) &&
                Objects.equals(size, trade.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asset, time, size, price, pnl);
    }

    @Override
    public String toString() {
        return "Trade{" +
                "asset=" + asset +
                ", time=" + time +
                ", size=" + size +
                ", price=" + price +
                ", pnl=" + pnl +
                '}';
    }
}
