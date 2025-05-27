package org.roboquant.ta;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MAType;
import java.time.Instant;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Item;
import org.roboquant.common.PriceBar;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018BI\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u00128\u0010\u0004\u001a4\u0012\u0004\u0012\u00020\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b0\u0005¢\u0006\u0002\b\u000e¢\u0006\u0002\u0010\u000fJ$\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016R@\u0010\u0004\u001a4\u0012\u0004\u0012\u00020\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b0\u0005¢\u0006\u0002\b\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"},
   d2 = {"Lorg/roboquant/ta/TaLibIndicator;", "Lorg/roboquant/ta/Indicator;", "initialCapacity", "", "block", "Lkotlin/Function2;", "Lorg/roboquant/ta/TaLib;", "Lorg/roboquant/ta/PriceBarSeries;", "Lkotlin/ParameterName;", "name", "series", "", "", "", "Lkotlin/ExtensionFunctionType;", "(ILkotlin/jvm/functions/Function2;)V", "taLib", "calculate", "item", "Lorg/roboquant/common/Item;", "time", "Ljava/time/Instant;", "clear", "", "Companion", "roboquant"}
)
public final class TaLibIndicator implements Indicator {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final Function2 block;
   @NotNull
   private final TaLib taLib;
   @NotNull
   private final PriceBarSeries series;

   public TaLibIndicator(int initialCapacity, @NotNull Function2 block) {
      Intrinsics.checkNotNullParameter(block, "block");
      super();
      this.block = block;
      this.taLib = new TaLib((Core)null, 1, (DefaultConstructorMarker)null);
      this.series = new PriceBarSeries(initialCapacity);
   }

   // $FF: synthetic method
   public TaLibIndicator(int var1, Function2 var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 1) != 0) {
         var1 = 1;
      }

      this(var1, var2);
   }

   @NotNull
   public Map calculate(@NotNull Item item, @NotNull Instant time) {
      Intrinsics.checkNotNullParameter(item, "item");
      Intrinsics.checkNotNullParameter(time, "time");
      if (item instanceof PriceBar && this.series.add((PriceBar)item, time)) {
         try {
            return (Map)this.block.invoke(this.taLib, this.series);
         } catch (InsufficientData ex) {
            this.series.increaseCapacity(ex.getMinSize());
         }
      }

      return MapsKt.emptyMap();
   }

   public void clear() {
      this.series.clear();
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006J\u0010\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006J$\u0010\u000b\u001a\u00020\u00042\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u0006¨\u0006\u000f"},
      d2 = {"Lorg/roboquant/ta/TaLibIndicator$Companion;", "", "()V", "bbands", "Lorg/roboquant/ta/TaLibIndicator;", "barCount", "", "ema", "mfi", "rsi", "sma", "stochastic", "fastKPeriod", "slowKPeriod", "slowDPeriod", "roboquant"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final TaLibIndicator rsi(final int barCount) {
         return new TaLibIndicator(0, new Function2() {
            @NotNull
            public final Map invoke(@NotNull TaLib $this$$receiver, @NotNull PriceBarSeries it) {
               Intrinsics.checkNotNullParameter($this$$receiver, "$this$$receiver");
               Intrinsics.checkNotNullParameter(it, "it");
               return MapsKt.mapOf(TuplesKt.to("rsi" + barCount, TaLib.rsi$default($this$$receiver, (PriceBarSeries)it, barCount, 0, 4, (Object)null)));
            }
         }, 1, (DefaultConstructorMarker)null);
      }

      // $FF: synthetic method
      public static TaLibIndicator rsi$default(Companion var0, int var1, int var2, Object var3) {
         if ((var2 & 1) != 0) {
            var1 = 10;
         }

         return var0.rsi(var1);
      }

      @NotNull
      public final TaLibIndicator bbands(final int barCount) {
         return new TaLibIndicator(0, new Function2() {
            @NotNull
            public final Map invoke(@NotNull TaLib $this$$receiver, @NotNull PriceBarSeries it) {
               Intrinsics.checkNotNullParameter($this$$receiver, "$this$$receiver");
               Intrinsics.checkNotNullParameter(it, "it");
               Triple var3 = TaLib.bbands$default($this$$receiver, (PriceBarSeries)it, barCount, (double)0.0F, (double)0.0F, (MAType)null, 0, 60, (Object)null);
               double high = ((Number)var3.component1()).doubleValue();
               double mid = ((Number)var3.component2()).doubleValue();
               double low = ((Number)var3.component3()).doubleValue();
               String prefix = "bb" + barCount;
               Pair[] var11 = new Pair[]{TuplesKt.to(prefix + ".low", low), TuplesKt.to(prefix + ".high", high), TuplesKt.to(prefix + ".mid", mid)};
               return MapsKt.mapOf(var11);
            }
         }, 1, (DefaultConstructorMarker)null);
      }

      // $FF: synthetic method
      public static TaLibIndicator bbands$default(Companion var0, int var1, int var2, Object var3) {
         if ((var2 & 1) != 0) {
            var1 = 10;
         }

         return var0.bbands(var1);
      }

      @NotNull
      public final TaLibIndicator ema(final int barCount) {
         return new TaLibIndicator(0, new Function2() {
            @NotNull
            public final Map invoke(@NotNull TaLib $this$$receiver, @NotNull PriceBarSeries it) {
               Intrinsics.checkNotNullParameter($this$$receiver, "$this$$receiver");
               Intrinsics.checkNotNullParameter(it, "it");
               return MapsKt.mapOf(TuplesKt.to("ema" + barCount, TaLib.ema$default($this$$receiver, (PriceBarSeries)it, barCount, 0, 4, (Object)null)));
            }
         }, 1, (DefaultConstructorMarker)null);
      }

      // $FF: synthetic method
      public static TaLibIndicator ema$default(Companion var0, int var1, int var2, Object var3) {
         if ((var2 & 1) != 0) {
            var1 = 10;
         }

         return var0.ema(var1);
      }

      @NotNull
      public final TaLibIndicator sma(final int barCount) {
         return new TaLibIndicator(0, new Function2() {
            @NotNull
            public final Map invoke(@NotNull TaLib $this$$receiver, @NotNull PriceBarSeries it) {
               Intrinsics.checkNotNullParameter($this$$receiver, "$this$$receiver");
               Intrinsics.checkNotNullParameter(it, "it");
               return MapsKt.mapOf(TuplesKt.to("sma" + barCount, TaLib.sma$default($this$$receiver, (PriceBarSeries)it, barCount, 0, 4, (Object)null)));
            }
         }, 1, (DefaultConstructorMarker)null);
      }

      // $FF: synthetic method
      public static TaLibIndicator sma$default(Companion var0, int var1, int var2, Object var3) {
         if ((var2 & 1) != 0) {
            var1 = 10;
         }

         return var0.sma(var1);
      }

      @NotNull
      public final TaLibIndicator mfi(final int barCount) {
         return new TaLibIndicator(0, new Function2() {
            @NotNull
            public final Map invoke(@NotNull TaLib $this$$receiver, @NotNull PriceBarSeries it) {
               Intrinsics.checkNotNullParameter($this$$receiver, "$this$$receiver");
               Intrinsics.checkNotNullParameter(it, "it");
               return MapsKt.mapOf(TuplesKt.to("mfi" + barCount, TaLib.mfi$default($this$$receiver, it, barCount, 0, 4, (Object)null)));
            }
         }, 1, (DefaultConstructorMarker)null);
      }

      // $FF: synthetic method
      public static TaLibIndicator mfi$default(Companion var0, int var1, int var2, Object var3) {
         if ((var2 & 1) != 0) {
            var1 = 10;
         }

         return var0.mfi(var1);
      }

      @NotNull
      public final TaLibIndicator stochastic(final int fastKPeriod, final int slowKPeriod, final int slowDPeriod) {
         return new TaLibIndicator(0, new Function2() {
            @NotNull
            public final Map invoke(@NotNull TaLib $this$$receiver, @NotNull PriceBarSeries it) {
               Intrinsics.checkNotNullParameter($this$$receiver, "$this$$receiver");
               Intrinsics.checkNotNullParameter(it, "it");
               Pair var3 = TaLib.stoch$default($this$$receiver, it, fastKPeriod, slowKPeriod, (MAType)null, slowDPeriod, (MAType)null, 0, 104, (Object)null);
               double d = ((Number)var3.component1()).doubleValue();
               double k = ((Number)var3.component2()).doubleValue();
               Pair[] var8 = new Pair[]{TuplesKt.to("stochatic.d", d), TuplesKt.to("stochatic.k", k)};
               return MapsKt.mapOf(var8);
            }
         }, 1, (DefaultConstructorMarker)null);
      }

      // $FF: synthetic method
      public static TaLibIndicator stochastic$default(Companion var0, int var1, int var2, int var3, int var4, Object var5) {
         if ((var4 & 1) != 0) {
            var1 = 5;
         }

         if ((var4 & 2) != 0) {
            var2 = 3;
         }

         if ((var4 & 4) != 0) {
            var3 = var2;
         }

         return var0.stochastic(var1, var2, var3);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
