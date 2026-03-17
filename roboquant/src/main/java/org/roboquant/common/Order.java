package org.roboquant.common;

import java.util.Objects;

public class Order {

    private final Asset asset;
    private final Size size;
    private final double limit;
    private final TIF tif;
    private final String tag;

    // Mutable fields (equivalent to 'var' in Kotlin)
    private String id = "";
    private Size fill = Size.ZERO;

    /**
     * Primary Constructor
     */
    public Order(Asset asset, Size size, double limit, TIF tif, String tag) {
        this.asset = Objects.requireNonNull(asset);
        this.size = Objects.requireNonNull(size);
        this.limit = limit;
        this.tif = Objects.requireNonNull(tif);
        this.tag = Objects.requireNonNull(tag);
    }

    /**
     * Overloaded constructors to simulate Kotlin default parameters
     */
    public Order(Asset asset, Size size, double limit) {
        this(asset, size, limit, TIF.DAY, "");
    }

    public Order(Asset asset, Size size, double limit, TIF tif) {
        this(asset, size, limit, tif, "");
    }

    // --- Computed Properties (Custom Getters) ---

    public Size getRemaining() {
        return size.minus(fill);
    }

    public boolean isBuy() {
        return size.compareTo(Size.ZERO) > 0;
    }

    public boolean isSell() {
        return size.compareTo(Size.ZERO) < 0;
    }

    // --- Getters and Setters ---

    public Asset getAsset() { return asset; }
    public Size getSize() { return size; }
    public double getLimit() { return limit; }
    public TIF getTif() { return tif; }
    public String getTag() { return tag; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Size getFill() { return fill; }
    public void setFill(Size fill) { this.fill = fill; }

    // --- Business Logic ---

    public Order cancel() {
        if (this.id.isEmpty()) {
            throw new IllegalArgumentException("Cannot cancel an order without an ID");
        }
        return copy(this.asset, Size.ZERO, this.limit, this.tif, this.tag);
    }

    public Order modify(Size size, double limit) {
        if (this.id.isEmpty()) {
            throw new IllegalArgumentException("Cannot modify an order without an ID");
        }
        return copy(this.asset, size, limit, this.tif, this.tag);
    }

    public boolean isCancellation() {
        return size.equals(Size.ZERO) && !id.isEmpty();
    }

    public boolean isExecutable(double price) {
        if (isBuy()) return price <= limit;
        if (isSell()) return price >= limit;
        return false;
    }

    public Amount value(double price) {
        return asset.value(size, price);
    }

    /**
     * Helper to simulate Kotlin's 'copy' functionality
     */
    public Order copy(Asset asset, Size size, double limit, TIF tif, String tag) {
        Order newOrder = new Order(asset, size, limit, tif, tag);
        newOrder.setId(this.id);
        newOrder.setFill(this.fill);
        return newOrder;
    }

    @Override
    public String toString() {
        return String.format("asset=%s id=%s tag=%s", asset, id, tag);
    }

    public enum TIF {
        DAY,
        GTC
    }

}