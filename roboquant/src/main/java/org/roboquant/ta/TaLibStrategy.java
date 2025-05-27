package org.roboquant.ta;

import com.tictactec.ta.lib.Core;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Asset;
import org.roboquant.common.Event;
import org.roboquant.common.PriceBar;
import org.roboquant.common.Signal;
import org.roboquant.common.SignalType;
import org.roboquant.strategies.Strategy;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J4\u0010\u0014\u001a\u00020\u00152,\u0010\u0016\u001a(\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\b¢\u0006\u0002\b\u000fJ\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J4\u0010\u001c\u001a\u00020\u00152,\u0010\u0016\u001a(\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\b¢\u0006\u0002\b\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R4\u0010\u0007\u001a(\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\b¢\u0006\u0002\b\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R4\u0010\u0010\u001a(\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\b¢\u0006\u0002\b\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001e"},
   d2 = {"Lorg/roboquant/ta/TaLibStrategy;", "Lorg/roboquant/strategies/Strategy;", "initialCapacity", "", "(I)V", "assetPriceBarSeries", "Lorg/roboquant/ta/AssetPriceBarSeries;", "buyFn", "Lkotlin/Function2;", "Lorg/roboquant/ta/TaLib;", "Lorg/roboquant/ta/PriceBarSeries;", "Lkotlin/ParameterName;", "name", "series", "", "Lkotlin/ExtensionFunctionType;", "sellFn", "taLib", "getTaLib", "()Lorg/roboquant/ta/TaLib;", "buy", "", "block", "createSignals", "", "Lorg/roboquant/common/Signal;", "event", "Lorg/roboquant/common/Event;", "sell", "Factory", "roboquant"}
)
@SourceDebugExtension({"SMAP\nTaLibStrategy.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TaLibStrategy.kt\norg/roboquant/ta/TaLibStrategy\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,238:1\n800#2,11:239\n*S KotlinDebug\n*F\n+ 1 TaLibStrategy.kt\norg/roboquant/ta/TaLibStrategy\n*L\n198#1:239,11\n*E\n"})
public final class TaLibStrategy implements Strategy {
   @NotNull
   public static final Factory Factory = new Factory((DefaultConstructorMarker)null);
   @NotNull
   private Function2 sellFn;
   @NotNull
   private Function2 buyFn;
   @NotNull
   private final AssetPriceBarSeries assetPriceBarSeries;
   @NotNull
   private final TaLib taLib;

   public TaLibStrategy(int initialCapacity) {
      this.sellFn = null.INSTANCE;
      this.buyFn = null.INSTANCE;
      this.assetPriceBarSeries = new AssetPriceBarSeries(initialCapacity);
      this.taLib = new TaLib((Core)null, 1, (DefaultConstructorMarker)null);
   }

   // $FF: synthetic method
   public TaLibStrategy(int var1, int var2, DefaultConstructorMarker var3) {
      if ((var2 & 1) != 0) {
         var1 = 1;
      }

      this(var1);
   }

   @NotNull
   public final TaLib getTaLib() {
      return this.taLib;
   }

   public final void buy(@NotNull Function2 block) {
      Intrinsics.checkNotNullParameter(block, "block");
      this.buyFn = block;
   }

   public final void sell(@NotNull Function2 block) {
      Intrinsics.checkNotNullParameter(block, "block");
      this.sellFn = block;
   }

   @NotNull
   public List createSignals(@NotNull Event event) {
      Intrinsics.checkNotNullParameter(event, "event");
      List results = (List)(new ArrayList());
      Instant time = event.getTime();
      Iterable $this$filterIsInstance$iv = (Iterable)event.getItems();
      int $i$f$filterIsInstance = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList());
      int $i$f$filterIsInstanceTo = 0;

      for(Object element$iv$iv : $this$filterIsInstance$iv) {
         if (element$iv$iv instanceof PriceBar) {
            destination$iv$iv.add(element$iv$iv);
         }
      }

      for(PriceBar priceBar : (List)destination$iv$iv) {
         if (this.assetPriceBarSeries.add(priceBar, time)) {
            Asset asset = priceBar.getAsset();
            PriceBarSeries priceSerie = (PriceBarSeries)MapsKt.getValue(this.assetPriceBarSeries, asset);

            try {
               if ((Boolean)this.buyFn.invoke(this.taLib, priceSerie)) {
                  results.add(Signal.Companion.buy$default(Signal.Companion, asset, (SignalType)null, (String)null, 6, (Object)null));
               }

               if ((Boolean)this.sellFn.invoke(this.taLib, priceSerie)) {
                  results.add(Signal.Companion.sell$default(Signal.Companion, asset, (SignalType)null, (String)null, 6, (Object)null));
               }
            } catch (InsufficientData ex) {
               priceSerie.increaseCapacity(ex.getMinSize());
            }
         }
      }

      return results;
   }

   public TaLibStrategy() {
      this(0, 1, (DefaultConstructorMarker)null);
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006J\u0016\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006J\u0012\u0010\u000b\u001a\u00020\u00042\n\u0010\f\u001a\u00020\r\"\u00020\u0006J\"\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0011J\u0016\u0010\u0013\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006¨\u0006\u0014"},
      d2 = {"Lorg/roboquant/ta/TaLibStrategy$Factory;", "", "()V", "breakout", "Lorg/roboquant/ta/TaLibStrategy;", "highPeriod", "", "lowPeriod", "emaCrossover", "slow", "fast", "recordHighLow", "timePeriods", "", "rsi", "timePeriod", "lowThreshold", "", "highThreshold", "smaCrossover", "roboquant"}
   )
   @SourceDebugExtension({"SMAP\nTaLibStrategy.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TaLibStrategy.kt\norg/roboquant/ta/TaLibStrategy$Factory\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,238:1\n1#2:239\n12313#3,2:240\n*S KotlinDebug\n*F\n+ 1 TaLibStrategy.kt\norg/roboquant/ta/TaLibStrategy$Factory\n*L\n70#1:240,2\n*E\n"})
   public static final class Factory {
      private Factory() {
      }

      @NotNull
      public final TaLibStrategy recordHighLow(@NotNull final int... timePeriods) {
         Intrinsics.checkNotNullParameter(timePeriods, "timePeriods");
         if (timePeriods.length == 0) {
            int var12 = 0;
            String var13 = "At least one period needs to be provided";
            throw new IllegalArgumentException(var13.toString());
         } else {
            int[] $this$all$iv = timePeriods;
            int $i$f$all = 0;
            int var4 = 0;
            int var5 = timePeriods.length;

            boolean var10000;
            while(true) {
               if (var4 >= var5) {
                  var10000 = true;
                  break;
               }

               int element$iv = $this$all$iv[var4];
               int var8 = 0;
               if (element$iv <= 1) {
                  var10000 = false;
                  break;
               }

               ++var4;
            }

            if (!var10000) {
               $i$f$all = 0;
               String var11 = "Any provided period needs to be at least of size 2";
               throw new IllegalArgumentException(var11.toString());
            } else {
               TaLibStrategy strategy = new TaLibStrategy(ArraysKt.maxOrThrow(timePeriods));
               strategy.buy(new Function2() {
                  @NotNull
                  public final Boolean invoke(@NotNull TaLib $this$buy, @NotNull PriceBarSeries it) {
                     Intrinsics.checkNotNullParameter($this$buy, "$this$buy");
                     Intrinsics.checkNotNullParameter(it, "it");
                     double[] data = it.getHigh();
                     int[] $this$any$iv = timePeriods;
                     int $i$f$any = 0;
                     int var6 = 0;
                     int var7 = $this$any$iv.length;

                     boolean var10000;
                     while(true) {
                        if (var6 >= var7) {
                           var10000 = false;
                           break;
                        }

                        int element$iv = $this$any$iv[var6];
                        int var10 = 0;
                        if (TaLibStrategyKt.recordHigh$default($this$buy, (double[])data, element$iv, 0, 4, (Object)null)) {
                           var10000 = true;
                           break;
                        }

                        ++var6;
                     }

                     return var10000;
                  }
               });
               strategy.sell(new Function2() {
                  @NotNull
                  public final Boolean invoke(@NotNull TaLib $this$sell, @NotNull PriceBarSeries it) {
                     Intrinsics.checkNotNullParameter($this$sell, "$this$sell");
                     Intrinsics.checkNotNullParameter(it, "it");
                     double[] data = it.getLow();
                     int[] $this$any$iv = timePeriods;
                     int $i$f$any = 0;
                     int var6 = 0;
                     int var7 = $this$any$iv.length;

                     boolean var10000;
                     while(true) {
                        if (var6 >= var7) {
                           var10000 = false;
                           break;
                        }

                        int element$iv = $this$any$iv[var6];
                        int var10 = 0;
                        if (TaLibStrategyKt.recordLow$default($this$sell, (double[])data, element$iv, 0, 4, (Object)null)) {
                           var10000 = true;
                           break;
                        }

                        ++var6;
                     }

                     return var10000;
                  }
               });
               return strategy;
            }
         }
      }

      @NotNull
      public final TaLibStrategy breakout(final int highPeriod, final int lowPeriod) {
         if (highPeriod <= 0 || lowPeriod <= 0) {
            int var4 = 0;
            String var5 = "Periods have to be larger than 0";
            throw new IllegalArgumentException(var5.toString());
         } else {
            TaLibStrategy strategy = new TaLibStrategy(Integer.max(highPeriod, lowPeriod));
            strategy.buy(new Function2() {
               @NotNull
               public final Boolean invoke(@NotNull TaLib $this$buy, @NotNull PriceBarSeries it) {
                  Intrinsics.checkNotNullParameter($this$buy, "$this$buy");
                  Intrinsics.checkNotNullParameter(it, "it");
                  return TaLibStrategyKt.recordHigh$default($this$buy, (double[])it.getHigh(), highPeriod, 0, 4, (Object)null);
               }
            });
            strategy.sell(new Function2() {
               @NotNull
               public final Boolean invoke(@NotNull TaLib $this$sell, @NotNull PriceBarSeries it) {
                  Intrinsics.checkNotNullParameter($this$sell, "$this$sell");
                  Intrinsics.checkNotNullParameter(it, "it");
                  return TaLibStrategyKt.recordLow$default($this$sell, (double[])it.getLow(), lowPeriod, 0, 4, (Object)null);
               }
            });
            return strategy;
         }
      }

      // $FF: synthetic method
      public static TaLibStrategy breakout$default(Factory var0, int var1, int var2, int var3, Object var4) {
         if ((var3 & 1) != 0) {
            var1 = 100;
         }

         if ((var3 & 2) != 0) {
            var2 = 50;
         }

         return var0.breakout(var1, var2);
      }

      @NotNull
      public final TaLibStrategy smaCrossover(final int slow, final int fast) {
         if (slow <= 0 || fast <= 0) {
            int var6 = 0;
            String var7 = "Periods have to be larger than 0";
            throw new IllegalArgumentException(var7.toString());
         } else if (slow <= fast) {
            int var4 = 0;
            String var5 = "Slow period have to be larger than fast period";
            throw new IllegalArgumentException(var5.toString());
         } else {
            TaLibStrategy strategy = new TaLibStrategy(slow);
            strategy.buy(new Function2() {
               @NotNull
               public final Boolean invoke(@NotNull TaLib $this$buy, @NotNull PriceBarSeries it) {
                  Intrinsics.checkNotNullParameter($this$buy, "$this$buy");
                  Intrinsics.checkNotNullParameter(it, "it");
                  return TaLib.sma$default($this$buy, (double[])it.getClose(), fast, 0, 4, (Object)null) > TaLib.sma$default($this$buy, (double[])it.getClose(), slow, 0, 4, (Object)null);
               }
            });
            strategy.sell(new Function2() {
               @NotNull
               public final Boolean invoke(@NotNull TaLib $this$sell, @NotNull PriceBarSeries it) {
                  Intrinsics.checkNotNullParameter($this$sell, "$this$sell");
                  Intrinsics.checkNotNullParameter(it, "it");
                  return TaLib.sma$default($this$sell, (double[])it.getClose(), fast, 0, 4, (Object)null) < TaLib.sma$default($this$sell, (double[])it.getClose(), slow, 0, 4, (Object)null);
               }
            });
            return strategy;
         }
      }

      @NotNull
      public final TaLibStrategy emaCrossover(final int slow, final int fast) {
         if (slow <= 0 || fast <= 0) {
            int var6 = 0;
            String var7 = "Periods have to be larger than 0";
            throw new IllegalArgumentException(var7.toString());
         } else if (slow <= fast) {
            int var4 = 0;
            String var5 = "Slow period have to be larger than fast period";
            throw new IllegalArgumentException(var5.toString());
         } else {
            TaLibStrategy strategy = new TaLibStrategy(slow);
            strategy.buy(new Function2() {
               @NotNull
               public final Boolean invoke(@NotNull TaLib $this$buy, @NotNull PriceBarSeries it) {
                  Intrinsics.checkNotNullParameter($this$buy, "$this$buy");
                  Intrinsics.checkNotNullParameter(it, "it");
                  return TaLib.ema$default($this$buy, (double[])it.getClose(), fast, 0, 4, (Object)null) > TaLib.ema$default($this$buy, (double[])it.getClose(), slow, 0, 4, (Object)null);
               }
            });
            strategy.sell(new Function2() {
               @NotNull
               public final Boolean invoke(@NotNull TaLib $this$sell, @NotNull PriceBarSeries it) {
                  Intrinsics.checkNotNullParameter($this$sell, "$this$sell");
                  Intrinsics.checkNotNullParameter(it, "it");
                  return TaLib.ema$default($this$sell, (double[])it.getClose(), fast, 0, 4, (Object)null) < TaLib.ema$default($this$sell, (double[])it.getClose(), slow, 0, 4, (Object)null);
               }
            });
            return strategy;
         }
      }

      @NotNull
      public final TaLibStrategy rsi(final int timePeriod, final double lowThreshold, final double highThreshold) {
         if (!((double)0.0F <= lowThreshold ? lowThreshold <= (double)100.0F : false) || !((double)0.0F <= highThreshold ? highThreshold <= (double)100.0F : false)) {
            int var9 = 0;
            String var10 = "Thresholds have to be in the range 0..100";
            throw new IllegalArgumentException(var10.toString());
         } else if (!(highThreshold > lowThreshold)) {
            int var7 = 0;
            String var8 = "High threshold has to be larger than low threshold";
            throw new IllegalArgumentException(var8.toString());
         } else {
            TaLibStrategy strategy = new TaLibStrategy(timePeriod + 1);
            strategy.buy(new Function2() {
               @NotNull
               public final Boolean invoke(@NotNull TaLib $this$buy, @NotNull PriceBarSeries it) {
                  Intrinsics.checkNotNullParameter($this$buy, "$this$buy");
                  Intrinsics.checkNotNullParameter(it, "it");
                  return TaLib.rsi$default($this$buy, (double[])it.getClose(), timePeriod, 0, 4, (Object)null) < lowThreshold;
               }
            });
            strategy.sell(new Function2() {
               @NotNull
               public final Boolean invoke(@NotNull TaLib $this$sell, @NotNull PriceBarSeries it) {
                  Intrinsics.checkNotNullParameter($this$sell, "$this$sell");
                  Intrinsics.checkNotNullParameter(it, "it");
                  return TaLib.rsi$default($this$sell, (double[])it.getClose(), timePeriod, 0, 4, (Object)null) > highThreshold;
               }
            });
            return strategy;
         }
      }

      // $FF: synthetic method
      public static TaLibStrategy rsi$default(Factory var0, int var1, double var2, double var4, int var6, Object var7) {
         if ((var6 & 2) != 0) {
            var2 = (double)30.0F;
         }

         if ((var6 & 4) != 0) {
            var4 = (double)70.0F;
         }

         return var0.rsi(var1, var2, var4);
      }

      // $FF: synthetic method
      public Factory(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
