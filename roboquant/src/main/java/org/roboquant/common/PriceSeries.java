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

package org.roboquant.common;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Holds a fix amount of historic prices. When adding a new value while the buffer is full, the oldest one will be
 * removed (aka a circular buffer). This is typically used by strategies to track rolling windows or replay buffers.
 *
 * Internally, it uses a double[] to hold the price values. Instances of this class are not thread safe during
 * updates.
 */
public class PriceSeries {

    private int capacity;
    private double[] data;
    private long counter;

    public PriceSeries(int capacity) {
        this.capacity = capacity;
        this.data = new double[capacity];
    }

    /**
     * Append a new price to the end of the buffer. If the buffer is full, the first element will be removed to make
     * room.
     *
     * @param price the price to add
     * @return true if the buffer is full, false otherwise
     */
    public boolean add(double price) {
        int index = (int) (counter % capacity);
        data[index] = price;
        counter++;
        return isFull();
    }

    /**
     * Update the last price with a new price.
     *
     * @param price the new price
     * @return true if the buffer is full, false otherwise
     */
    public boolean update(double price) {
        if (counter == 0L) throw new NoSuchElementException();
        int index = (int) ((counter - 1) % capacity);
        data[index] = price;
        return isFull();
    }

    /**
     * Return the last added price, even if the buffer isn't filled yet.
     *
     * @return the last price
     */
    public double last() {
        if (counter == 0L) throw new NoSuchElementException();
        int index = (int) ((counter - 1) % capacity);
        return data[index];
    }

    /**
     * Return true if the rolling window is fully filled, so it is ready to be used.
     *
     * @return true if full
     */
    public boolean isFull() {
        return counter >= capacity;
    }

    /**
     * Return the size of this price series.
     *
     * @return the size
     */
    public int getSize() {
        return counter > capacity ? capacity : (int) counter;
    }

    /**
     * Return the stored values as a double[]. If this method is invoked before the buffer is full, it will
     * return a smaller array of length PriceSeries.getSize().
     *
     * @return the data as double array
     */
    public double[] toDoubleArray() {
        double[] result = new double[getSize()];
        if (counter > capacity) {
            int offset = (int) (counter % capacity);
            System.arraycopy(data, offset, result, 0, capacity - offset);
            System.arraycopy(data, 0, result, capacity - offset, offset);
        } else {
            System.arraycopy(data, 0, result, 0, result.length);
        }
        return result;
    }

    /**
     * Increase the capacity to the newCapacity.
     *
     * @param newCapacity the new capacity
     */
    public void increaseCapacity(int newCapacity) {
        if (newCapacity <= capacity) {
            throw new IllegalArgumentException("new capacity should be larger than old one, new=" + newCapacity + " old=" + capacity);
        }
        double[] oldData = toDoubleArray();
        data = new double[newCapacity];
        System.arraycopy(oldData, 0, data, 0, getSize());
        capacity = newCapacity;
    }

    /**
     * Clear the buffer and reset the capacity to the initial capacity.
     */
    public void clear() {
        counter = 0L;
        data = new double[capacity];
    }

    public static Set<Asset> addAll(Map<Asset, PriceSeries> map, Event event, int capacity, String priceType) {
        Set<Asset> result = new LinkedHashSet<>();
        for (Map.Entry<Asset, Item.PriceItem> entry : event.getPrices().entrySet()) {
            Asset asset = entry.getKey();
            Item.PriceItem item = entry.getValue();
            PriceSeries priceSeries = map.computeIfAbsent(asset, k -> new PriceSeries(capacity));
            priceSeries.add(item.getPrice(priceType));
            if (priceSeries.isFull()) {
                result.add(asset);
            }
        }
        return result;
    }

    public static Set<Asset> addAll(Map<Asset, PriceSeries> map, Event event, int capacity) {
        return addAll(map, event, capacity, "DEFAULT");
    }

}
