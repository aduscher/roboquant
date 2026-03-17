package org.roboquant.ta;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.jetbrains.annotations.NotNull;
import org.robok.common.ExtensionsKt;
import org.robok.common.PriceBar;
import org.robok.common.PriceSeries;
import org.robok.common.Timeframe;

public class PriceBarSeries {

    private static final int OHLCV_LENGTH = 5;

    private final PriceSeries openBuffer;
    private final PriceSeries highBuffer;
    private final PriceSeries lowBuffer;
    private final PriceSeries closeBuffer;
    private final PriceSeries volumeBuffer;
    private final List<Instant> timeBuffer;

    public PriceBarSeries(int capacity) {
        this.openBuffer = new PriceSeries(capacity);
        this.highBuffer = new PriceSeries(capacity);
        this.lowBuffer = new PriceSeries(capacity);
        this.closeBuffer = new PriceSeries(capacity);
        this.volumeBuffer = new PriceSeries(capacity);
        this.timeBuffer = new ArrayList<>();
    }

    protected PriceSeries getOpenBuffer() {
        return openBuffer;
    }

    protected PriceSeries getHighBuffer() {
        return highBuffer;
    }

    protected PriceSeries getLowBuffer() {
        return lowBuffer;
    }

    protected PriceSeries getCloseBuffer() {
        return closeBuffer;
    }

    protected PriceSeries getVolumeBuffer() {
        return volumeBuffer;
    }

    protected List<Instant> getTimeBuffer() {
        return timeBuffer;
    }

    public double[] getOpen() {
        return openBuffer.toDoubleArray();
    }

    public double[] getHigh() {
        return highBuffer.toDoubleArray();
    }

    public double[] getLow() {
        return lowBuffer.toDoubleArray();
    }

    public double[] getClose() {
        return closeBuffer.toDoubleArray();
    }

    public double[] getVolume() {
        return volumeBuffer.toDoubleArray();
    }

    public List<Instant> getTimeline() {
        return Collections.unmodifiableList(timeBuffer);
    }

    public double[] getTypical() {
        return org.robok.common.ExtensionsKt.div(
                org.robok.common.ExtensionsKt.plus(
                        ExtensionsKt.plus(highBuffer.toDoubleArray(), lowBuffer.toDoubleArray()),
                        closeBuffer.toDoubleArray()
                ),
                3.0
        );
    }

    public Instant now() {
        if (timeBuffer.isEmpty()) {
            throw new NoSuchElementException("series is empty");
        }
        return timeBuffer.get(timeBuffer.size() - 1);
    }

    public boolean add(@NotNull PriceBar priceBar, @NotNull Instant time) {
        return add(priceBar.getOhlcv(), time);
    }

    protected boolean add(@NotNull double[] ohlcv, @NotNull Instant time) {
        if (ohlcv.length != OHLCV_LENGTH) {
            throw new IllegalArgumentException(
                    "ohlcv array must contain exactly 5 values: open, high, low, close, volume"
            );
        }

        openBuffer.add(ohlcv[0]);
        highBuffer.add(ohlcv[1]);
        lowBuffer.add(ohlcv[2]);
        closeBuffer.add(ohlcv[3]);
        volumeBuffer.add(ohlcv[4]);
        timeBuffer.add(time);

        while (timeBuffer.size() > openBuffer.getSize()) {
            timeBuffer.remove(0);
        }

        return isFull();
    }

    public double[] get(int index) {
        return new double[] {
                getOpen()[index],
                getHigh()[index],
                getLow()[index],
                getClose()[index],
                getVolume()[index]
        };
    }

    public org.robok.ta.PriceBarSeries get(@NotNull Timeframe timeframe) {
        org.robok.ta.PriceBarSeries result = new org.robok.ta.PriceBarSeries(getSize());
        Instant start = timeframe.getStart();

        for (int i = 0; i < timeBuffer.size(); i++) {
            Instant time = timeBuffer.get(i);

            if (time.compareTo(start) < 0) {
                continue;
            }

            if (!timeframe.contains(time)) {
                break;
            }

            result.add(get(i), time);
        }

        return result;
    }

    public double[] get(@NotNull Instant time) {
        int index = Collections.binarySearch(timeBuffer, time);
        if (index < 0) {
            throw new NoSuchElementException("time not found");
        }
        return get(index);
    }

    public boolean isFull() {
        return openBuffer.isFull();
    }

    public int getSize() {
        return openBuffer.getSize();
    }

    public void clear() {
        openBuffer.clear();
        highBuffer.clear();
        lowBuffer.clear();
        closeBuffer.clear();
        volumeBuffer.clear();
        timeBuffer.clear();
    }

    public org.robok.ta.PriceBarSeries aggregate(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("number should be larger than 0");
        }

        org.robok.ta.PriceBarSeries result = new org.robok.ta.PriceBarSeries(getSize() / n);

        for (int i = 0; i + n <= getSize(); i += n) {
            double[] open = getOpen();
            double[] high = getHigh();
            double[] low = getLow();
            double[] close = getClose();
            double[] volume = getVolume();

            double aggregatedOpen = open[i];
            double lowest = low[i];
            double highest = high[i];
            double totalVolume = 0.0;

            for (int j = i; j < i + n; j++) {
                if (low[j] < lowest) {
                    lowest = low[j];
                }
                if (high[j] > highest) {
                    highest = high[j];
                }
                totalVolume += volume[j];
            }

            int last = i + n - 1;
            double[] ohlcv = {
                    aggregatedOpen,
                    highest,
                    lowest,
                    close[last],
                    totalVolume
            };

            result.add(ohlcv, timeBuffer.get(last));
        }

        return result;
    }

    public void increaseCapacity(int newCapacity) {
        openBuffer.increaseCapacity(newCapacity);
        highBuffer.increaseCapacity(newCapacity);
        lowBuffer.increaseCapacity(newCapacity);
        closeBuffer.increaseCapacity(newCapacity);
        volumeBuffer.increaseCapacity(newCapacity);
    }
}
