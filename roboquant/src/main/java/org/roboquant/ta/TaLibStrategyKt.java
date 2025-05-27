package org.roboquant.ta;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u001a$\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u001a$\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006¨\u0006\f"},
   d2 = {"recordHigh", "", "Lorg/roboquant/ta/TaLib;", "high", "", "period", "", "previous", "series", "Lorg/roboquant/ta/PriceBarSeries;", "recordLow", "low", "roboquant"}
)
public final class TaLibStrategyKt {
   public static final boolean recordLow(@NotNull TaLib $this$recordLow, @NotNull double[] low, int period, int previous) {
      Intrinsics.checkNotNullParameter($this$recordLow, "<this>");
      Intrinsics.checkNotNullParameter(low, "low");
      return $this$recordLow.minIndex(low, period, previous) == ArraysKt.getLastIndex(low) - previous;
   }

   // $FF: synthetic method
   public static boolean recordLow$default(TaLib var0, double[] var1, int var2, int var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return recordLow(var0, var1, var2, var3);
   }

   public static final boolean recordLow(@NotNull TaLib $this$recordLow, @NotNull PriceBarSeries series, int period, int previous) {
      Intrinsics.checkNotNullParameter($this$recordLow, "<this>");
      Intrinsics.checkNotNullParameter(series, "series");
      return recordLow($this$recordLow, series.getLow(), period, previous);
   }

   // $FF: synthetic method
   public static boolean recordLow$default(TaLib var0, PriceBarSeries var1, int var2, int var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return recordLow(var0, var1, var2, var3);
   }

   public static final boolean recordHigh(@NotNull TaLib $this$recordHigh, @NotNull double[] high, int period, int previous) {
      Intrinsics.checkNotNullParameter($this$recordHigh, "<this>");
      Intrinsics.checkNotNullParameter(high, "high");
      return $this$recordHigh.maxIndex(high, period, previous) == ArraysKt.getLastIndex(high) - previous;
   }

   // $FF: synthetic method
   public static boolean recordHigh$default(TaLib var0, double[] var1, int var2, int var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return recordHigh(var0, var1, var2, var3);
   }

   public static final boolean recordHigh(@NotNull TaLib $this$recordHigh, @NotNull PriceBarSeries series, int period, int previous) {
      Intrinsics.checkNotNullParameter($this$recordHigh, "<this>");
      Intrinsics.checkNotNullParameter(series, "series");
      return recordHigh($this$recordHigh, series.getHigh(), period, previous);
   }

   // $FF: synthetic method
   public static boolean recordHigh$default(TaLib var0, PriceBarSeries var1, int var2, int var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return recordHigh(var0, var1, var2, var3);
   }
}
