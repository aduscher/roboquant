package org.roboquant.ta;

import com.tictactec.ta.lib.Core;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Asset;
import org.roboquant.common.Event;
import org.roboquant.common.ExtensionsKt;
import org.roboquant.common.PriceBar;
import org.roboquant.common.Signal;
import org.roboquant.common.SignalType;
import org.roboquant.strategies.Strategy;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bBT\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012C\u0010\u0004\u001a?\u0012\u0004\u0012\u00020\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0005¢\u0006\u0002\b\u000e¢\u0006\u0002\u0010\u000fJ\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016RK\u0010\u0004\u001a?\u0012\u0004\u0012\u00020\u0006\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0005¢\u0006\u0002\b\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000b0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u001c"},
   d2 = {"Lorg/roboquant/ta/TaLibSignalStrategy;", "Lorg/roboquant/strategies/Strategy;", "initialCapacity", "", "block", "Lkotlin/Function3;", "Lorg/roboquant/ta/TaLib;", "Lorg/roboquant/common/Asset;", "Lkotlin/ParameterName;", "name", "asset", "Lorg/roboquant/ta/PriceBarSeries;", "series", "Lorg/roboquant/common/Signal;", "Lkotlin/ExtensionFunctionType;", "(ILkotlin/jvm/functions/Function3;)V", "history", "", "taLib", "getTaLib", "()Lorg/roboquant/ta/TaLib;", "createSignals", "", "event", "Lorg/roboquant/common/Event;", "toString", "", "Companion", "roboquant"}
)
@SourceDebugExtension({"SMAP\nTaLibSignalStrategy.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TaLibSignalStrategy.kt\norg/roboquant/ta/TaLibSignalStrategy\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,148:1\n800#2,11:149\n372#3,7:160\n*S KotlinDebug\n*F\n+ 1 TaLibSignalStrategy.kt\norg/roboquant/ta/TaLibSignalStrategy\n*L\n125#1:149,11\n127#1:160,7\n*E\n"})
public final class TaLibSignalStrategy implements Strategy {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private final int initialCapacity;
   @NotNull
   private Function3 block;
   @NotNull
   private final Map history;
   @NotNull
   private final TaLib taLib;

   public TaLibSignalStrategy(int initialCapacity, @NotNull Function3 block) {
      Intrinsics.checkNotNullParameter(block, "block");
      super();
      this.initialCapacity = initialCapacity;
      this.block = block;
      this.history = (Map)(new LinkedHashMap());
      this.taLib = new TaLib((Core)null, 1, (DefaultConstructorMarker)null);
   }

   // $FF: synthetic method
   public TaLibSignalStrategy(int var1, Function3 var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 1) != 0) {
         var1 = 1;
      }

      this(var1, var2);
   }

   @NotNull
   public final TaLib getTaLib() {
      return this.taLib;
   }

   @NotNull
   public List createSignals(@NotNull Event event) {
      Intrinsics.checkNotNullParameter(event, "event");
      List signals = (List)(new ArrayList());
      Instant time = event.getTime();
      Iterable $this$filterIsInstance$iv = (Iterable)event.getPrices().values();
      int $i$f$filterIsInstance = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList());
      int $i$f$filterIsInstanceTo = 0;

      for(Object element$iv$iv : $this$filterIsInstance$iv) {
         if (element$iv$iv instanceof PriceBar) {
            destination$iv$iv.add(element$iv$iv);
         }
      }

      for(PriceBar priceAction : (List)destination$iv$iv) {
         Asset asset = priceAction.getAsset();
         Map $this$getOrPut$iv = this.history;
         $i$f$filterIsInstanceTo = 0;
         Object value$iv = $this$getOrPut$iv.get(asset);
         Object var10000;
         if (value$iv == null) {
            int var19 = 0;
            Object answer$iv = new PriceBarSeries(this.initialCapacity);
            $this$getOrPut$iv.put(asset, answer$iv);
            var10000 = answer$iv;
         } else {
            var10000 = value$iv;
         }

         PriceBarSeries buffer = (PriceBarSeries)var10000;
         if (buffer.add(priceAction, time)) {
            try {
               Signal signal = (Signal)this.block.invoke(this.taLib, asset, buffer);
               ExtensionsKt.addNotNull((Collection)signals, signal);
            } catch (InsufficientData ex) {
               buffer.increaseCapacity(ex.getMinSize());
            }
         }
      }

      return signals;
   }

   @NotNull
   public String toString() {
      return "TaLibSignalStrategy";
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006J\u0006\u0010\b\u001a\u00020\u0004J\u001a\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f¨\u0006\r"},
      d2 = {"Lorg/roboquant/ta/TaLibSignalStrategy$Companion;", "", "()V", "breakout", "Lorg/roboquant/ta/TaLibSignalStrategy;", "entryPeriod", "", "exitPeriod", "macd", "superTrend", "period", "multiplier", "", "roboquant"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final TaLibSignalStrategy breakout(final int entryPeriod, final int exitPeriod) {
         return new TaLibSignalStrategy(0, new Function3() {
            @Nullable
            public final Signal invoke(@NotNull TaLib $this$$receiver, @NotNull Asset asset, @NotNull PriceBarSeries series) {
               Intrinsics.checkNotNullParameter($this$$receiver, "$this$$receiver");
               Intrinsics.checkNotNullParameter(asset, "asset");
               Intrinsics.checkNotNullParameter(series, "series");
               return TaLibStrategyKt.recordHigh$default($this$$receiver, (double[])series.getHigh(), entryPeriod, 0, 4, (Object)null) ? Signal.Companion.buy$default(Signal.Companion, asset, SignalType.BOTH, (String)null, 4, (Object)null) : (TaLibStrategyKt.recordLow$default($this$$receiver, (double[])series.getLow(), entryPeriod, 0, 4, (Object)null) ? Signal.Companion.sell$default(Signal.Companion, asset, SignalType.BOTH, (String)null, 4, (Object)null) : (TaLibStrategyKt.recordLow$default($this$$receiver, (double[])series.getLow(), exitPeriod, 0, 4, (Object)null) ? Signal.Companion.sell$default(Signal.Companion, asset, SignalType.EXIT, (String)null, 4, (Object)null) : (TaLibStrategyKt.recordHigh$default($this$$receiver, (double[])series.getHigh(), exitPeriod, 0, 4, (Object)null) ? Signal.Companion.buy$default(Signal.Companion, asset, SignalType.EXIT, (String)null, 4, (Object)null) : null)));
            }
         }, 1, (DefaultConstructorMarker)null);
      }

      // $FF: synthetic method
      public static TaLibSignalStrategy breakout$default(Companion var0, int var1, int var2, int var3, Object var4) {
         if ((var3 & 1) != 0) {
            var1 = 100;
         }

         if ((var3 & 2) != 0) {
            var2 = 50;
         }

         return var0.breakout(var1, var2);
      }

      @NotNull
      public final TaLibSignalStrategy macd() {
         TaLibSignalStrategy strategy = new TaLibSignalStrategy(0, null.INSTANCE, 1, (DefaultConstructorMarker)null);
         return strategy;
      }

      @NotNull
      public final TaLibSignalStrategy superTrend(final int period, final double multiplier) {
         TaLibSignalStrategy strategy = new TaLibSignalStrategy(0, new Function3() {
            @Nullable
            public final Signal invoke(@NotNull TaLib $this$$receiver, @NotNull Asset asset, @NotNull PriceBarSeries prices) {
               Intrinsics.checkNotNullParameter($this$$receiver, "$this$$receiver");
               Intrinsics.checkNotNullParameter(asset, "asset");
               Intrinsics.checkNotNullParameter(prices, "prices");
               double atr = multiplier * TaLib.atr$default($this$$receiver, prices, period, 0, 4, (Object)null);
               double mid = (ArraysKt.last(prices.getHigh()) + ArraysKt.last(prices.getLow())) / (double)2.0F;
               double curr = ArraysKt.last(prices.getClose());
               return mid + atr > curr ? Signal.Companion.buy$default(Signal.Companion, asset, (SignalType)null, (String)null, 6, (Object)null) : (mid - atr < curr ? Signal.Companion.sell$default(Signal.Companion, asset, (SignalType)null, (String)null, 6, (Object)null) : null);
            }
         }, 1, (DefaultConstructorMarker)null);
         return strategy;
      }

      // $FF: synthetic method
      public static TaLibSignalStrategy superTrend$default(Companion var0, int var1, double var2, int var4, Object var5) {
         if ((var4 & 1) != 0) {
            var1 = 14;
         }

         if ((var4 & 2) != 0) {
            var2 = (double)1.0F;
         }

         return var0.superTrend(var1, var2);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
