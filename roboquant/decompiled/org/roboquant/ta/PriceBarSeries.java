package org.roboquant.ta;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.ExtensionsKt;
import org.roboquant.common.PriceBar;
import org.roboquant.common.PriceSeries;
import org.roboquant.common.Timeframe;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u001eH\u0004J\u0018\u0010+\u001a\u00020,2\u0006\u0010/\u001a\u0002002\u0006\u0010.\u001a\u00020\u001eH\u0016J\u000e\u00101\u001a\u00020\u00002\u0006\u00102\u001a\u00020\u0003J\u0006\u00103\u001a\u000204J\u0011\u00105\u001a\u00020\u00062\u0006\u0010.\u001a\u00020\u001eH\u0086\u0002J\u0011\u00105\u001a\u00020\u00062\u0006\u00106\u001a\u00020\u0003H\u0086\u0002J\u0011\u00105\u001a\u00020\u00002\u0006\u00107\u001a\u000208H\u0086\u0002J\u000e\u00109\u001a\u0002042\u0006\u0010:\u001a\u00020\u0003J\u0006\u0010;\u001a\u00020,J\u0006\u0010<\u001a\u00020\u001eR\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\bR\u0014\u0010\u000f\u001a\u00020\nX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0011\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\bR\u0014\u0010\u0013\u001a\u00020\nX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\fR\u0011\u0010\u0015\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\bR\u0014\u0010\u0017\u001a\u00020\nX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\fR\u0011\u0010\u0019\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001b\u0010!\u001a\f\u0012\u0004\u0012\u00020\u001e0\"j\u0002`#8F¢\u0006\u0006\u001a\u0004\b$\u0010 R\u0011\u0010%\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b&\u0010\bR\u0011\u0010'\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b(\u0010\bR\u0014\u0010)\u001a\u00020\nX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\f¨\u0006="},
   d2 = {"Lorg/roboquant/ta/PriceBarSeries;", "", "capacity", "", "(I)V", "close", "", "getClose", "()[D", "closeBuffer", "Lorg/roboquant/common/PriceSeries;", "getCloseBuffer", "()Lorg/roboquant/common/PriceSeries;", "high", "getHigh", "highBuffer", "getHighBuffer", "low", "getLow", "lowBuffer", "getLowBuffer", "open", "getOpen", "openBuffer", "getOpenBuffer", "size", "getSize", "()I", "timeBuffer", "", "Ljava/time/Instant;", "getTimeBuffer", "()Ljava/util/List;", "timeline", "", "Lorg/roboquant/common/Timeline;", "getTimeline", "typical", "getTypical", "volume", "getVolume", "volumeBuffer", "getVolumeBuffer", "add", "", "ohlcv", "time", "priceBar", "Lorg/roboquant/common/PriceBar;", "aggregate", "n", "clear", "", "get", "index", "timeframe", "Lorg/roboquant/common/Timeframe;", "increaseCapacity", "newCapacity", "isFull", "now", "roboquant"}
)
@SourceDebugExtension({"SMAP\nPriceBarSeries.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PriceBarSeries.kt\norg/roboquant/ta/PriceBarSeries\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,214:1\n1#2:215\n*E\n"})
public class PriceBarSeries {
   @NotNull
   private final PriceSeries openBuffer;
   @NotNull
   private final PriceSeries highBuffer;
   @NotNull
   private final PriceSeries lowBuffer;
   @NotNull
   private final PriceSeries closeBuffer;
   @NotNull
   private final PriceSeries volumeBuffer;
   @NotNull
   private final List timeBuffer;

   public PriceBarSeries(int capacity) {
      this.openBuffer = new PriceSeries(capacity);
      this.highBuffer = new PriceSeries(capacity);
      this.lowBuffer = new PriceSeries(capacity);
      this.closeBuffer = new PriceSeries(capacity);
      this.volumeBuffer = new PriceSeries(capacity);
      this.timeBuffer = (List)(new ArrayList());
   }

   @NotNull
   protected final PriceSeries getOpenBuffer() {
      return this.openBuffer;
   }

   @NotNull
   protected final PriceSeries getHighBuffer() {
      return this.highBuffer;
   }

   @NotNull
   protected final PriceSeries getLowBuffer() {
      return this.lowBuffer;
   }

   @NotNull
   protected final PriceSeries getCloseBuffer() {
      return this.closeBuffer;
   }

   @NotNull
   protected final PriceSeries getVolumeBuffer() {
      return this.volumeBuffer;
   }

   @NotNull
   protected final List getTimeBuffer() {
      return this.timeBuffer;
   }

   @NotNull
   public final double[] getOpen() {
      return this.openBuffer.toDoubleArray();
   }

   @NotNull
   public final double[] getHigh() {
      return this.highBuffer.toDoubleArray();
   }

   @NotNull
   public final double[] getLow() {
      return this.lowBuffer.toDoubleArray();
   }

   @NotNull
   public final double[] getClose() {
      return this.closeBuffer.toDoubleArray();
   }

   @NotNull
   public final double[] getVolume() {
      return this.volumeBuffer.toDoubleArray();
   }

   @NotNull
   public final List getTimeline() {
      return this.timeBuffer;
   }

   @NotNull
   public final double[] getTypical() {
      return ExtensionsKt.div(ExtensionsKt.plus(ExtensionsKt.plus(this.highBuffer.toDoubleArray(), this.lowBuffer.toDoubleArray()), this.closeBuffer.toDoubleArray()), (Number)(double)3.0F);
   }

   @NotNull
   public final Instant now() {
      return (Instant)CollectionsKt.last(this.timeBuffer);
   }

   public boolean add(@NotNull PriceBar priceBar, @NotNull Instant time) {
      Intrinsics.checkNotNullParameter(priceBar, "priceBar");
      Intrinsics.checkNotNullParameter(time, "time");
      return this.add(priceBar.getOhlcv(), time);
   }

   protected final boolean add(@NotNull double[] ohlcv, @NotNull Instant time) {
      Intrinsics.checkNotNullParameter(ohlcv, "ohlcv");
      Intrinsics.checkNotNullParameter(time, "time");
      boolean var3 = ohlcv.length == 5;
      if (_Assertions.ENABLED && !var3) {
         String var4 = "Assertion failed";
         throw new AssertionError(var4);
      } else {
         this.openBuffer.add(ohlcv[0]);
         this.highBuffer.add(ohlcv[1]);
         this.lowBuffer.add(ohlcv[2]);
         this.closeBuffer.add(ohlcv[3]);
         this.volumeBuffer.add(ohlcv[4]);
         this.timeBuffer.add(time);

         while(this.timeBuffer.size() > this.openBuffer.getSize()) {
            CollectionsKt.removeFirst(this.timeBuffer);
         }

         return this.isFull();
      }
   }

   @NotNull
   public final double[] get(int index) {
      double[] var2 = new double[]{this.getOpen()[index], this.getHigh()[index], this.getLow()[index], this.getClose()[index], this.getVolume()[index]};
      return var2;
   }

   @NotNull
   public final PriceBarSeries get(@NotNull Timeframe timeframe) {
      Intrinsics.checkNotNullParameter(timeframe, "timeframe");
      PriceBarSeries result = new PriceBarSeries(this.getSize());
      Instant start = timeframe.getStart();
      int idx = 0;

      for(int var5 = this.getTimeline().size(); idx < var5; ++idx) {
         Instant time = (Instant)this.getTimeline().get(idx);
         if (time.compareTo(start) >= 0) {
            if (!timeframe.contains(time)) {
               break;
            }

            result.add(this.get(idx), time);
         }
      }

      return result;
   }

   @NotNull
   public final double[] get(@NotNull Instant time) {
      Intrinsics.checkNotNullParameter(time, "time");
      int index = CollectionsKt.binarySearch$default(this.timeBuffer, (Comparable)time, 0, 0, 6, (Object)null);
      if (index == -1) {
         throw new NoSuchElementException("time not found");
      } else {
         double[] var3 = new double[]{this.getOpen()[index], this.getHigh()[index], this.getLow()[index], this.getClose()[index], this.getVolume()[index]};
         return var3;
      }
   }

   public final boolean isFull() {
      return this.openBuffer.isFull();
   }

   public final int getSize() {
      return this.openBuffer.getSize();
   }

   public final void clear() {
      this.openBuffer.clear();
      this.highBuffer.clear();
      this.lowBuffer.clear();
      this.closeBuffer.clear();
      this.volumeBuffer.clear();
      this.timeBuffer.clear();
   }

   @NotNull
   public final PriceBarSeries aggregate(int n) {
      if (n <= 0) {
         int var18 = 0;
         String var19 = "number should be larger than 0";
         throw new IllegalArgumentException(var19.toString());
      } else {
         PriceBarSeries result = new PriceBarSeries(this.getSize() / n);
         IntProgression var3 = RangesKt.step((IntProgression)RangesKt.until(0, this.getSize()), n);
         int i = var3.getFirst();
         int var5 = var3.getLast();
         int var6 = var3.getStep();
         if (var6 > 0 && i <= var5 || var6 < 0 && var5 <= i) {
            while(i + n <= this.getSize()) {
               double open = this.getOpen()[i];
               double total = (double)0.0F;
               double lowest = this.getLow()[i];
               double highest = this.getHigh()[i];
               int j = i;

               for(int var16 = i + n; j < var16; ++j) {
                  if (this.getLow()[j] < lowest) {
                     lowest = this.getLow()[j];
                  }

                  if (this.getHigh()[j] > highest) {
                     highest = this.getHigh()[j];
                  }

                  total += this.getVolume()[j];
               }

               j = i + n - 1;
               double[] ohlcv = new double[]{open, highest, lowest, this.getClose()[j], total};
               result.add(ohlcv, (Instant)this.getTimeline().get(j));
               if (i == var5) {
                  break;
               }

               i += var6;
            }
         }

         return result;
      }
   }

   public final void increaseCapacity(int newCapacity) {
      this.openBuffer.increaseCapacity(newCapacity);
      this.highBuffer.increaseCapacity(newCapacity);
      this.lowBuffer.increaseCapacity(newCapacity);
      this.closeBuffer.increaseCapacity(newCapacity);
      this.volumeBuffer.increaseCapacity(newCapacity);
   }
}
