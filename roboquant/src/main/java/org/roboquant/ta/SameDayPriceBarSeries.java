package org.roboquant.ta;

import java.time.Instant;
import kotlin.Metadata;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Exchange;
import org.roboquant.common.PriceBar;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"},
   d2 = {"Lorg/roboquant/ta/SameDayPriceBarSeries;", "Lorg/roboquant/ta/PriceBarSeries;", "capacity", "", "exchange", "Lorg/roboquant/common/Exchange;", "(ILorg/roboquant/common/Exchange;)V", "add", "", "priceBar", "Lorg/roboquant/common/PriceBar;", "time", "Ljava/time/Instant;", "update", "ohlcv", "", "roboquant"}
)
public final class SameDayPriceBarSeries extends PriceBarSeries {
   @NotNull
   private final Exchange exchange;

   public SameDayPriceBarSeries(int capacity, @NotNull Exchange exchange) {
      Intrinsics.checkNotNullParameter(exchange, "exchange");
      super(capacity);
      this.exchange = exchange;
   }

   // $FF: synthetic method
   public SameDayPriceBarSeries(int var1, Exchange var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = Exchange.Companion.getUS();
      }

      this(var1, var2);
   }

   public boolean add(@NotNull PriceBar priceBar, @NotNull Instant time) {
      Intrinsics.checkNotNullParameter(priceBar, "priceBar");
      Intrinsics.checkNotNullParameter(time, "time");
      return this.getSize() == 0 ? this.add(priceBar.getOhlcv(), time) : (this.exchange.sameDay((Instant)CollectionsKt.last(this.getTimeline()), time) ? this.update(priceBar.getOhlcv(), time) : this.add(priceBar.getOhlcv(), time));
   }

   private final boolean update(double[] ohlcv, Instant time) {
      boolean var3 = ohlcv.length == 5;
      if (_Assertions.ENABLED && !var3) {
         String var4 = "Assertion failed";
         throw new AssertionError(var4);
      } else {
         if (Double.isNaN(this.getOpenBuffer().last())) {
            this.getOpenBuffer().update(ohlcv[0]);
         }

         if (ohlcv[1] > this.getHighBuffer().last()) {
            this.getHighBuffer().update(ohlcv[1]);
         }

         if (ohlcv[2] < this.getLowBuffer().last()) {
            this.getLowBuffer().update(ohlcv[2]);
         }

         this.getCloseBuffer().update(ohlcv[3]);
         this.getVolumeBuffer().update(this.getVolumeBuffer().last() + ohlcv[4]);
         this.getTimeBuffer().set(CollectionsKt.getLastIndex(this.getTimeBuffer()), time);
         return this.isFull();
      }
   }
}
