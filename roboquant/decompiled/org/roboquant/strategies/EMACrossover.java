package org.roboquant.strategies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Asset;
import org.roboquant.common.Event;
import org.roboquant.common.PriceItem;
import org.roboquant.common.Signal;
import org.roboquant.common.SignalType;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001a\u001bB7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u001a\u0010\u0017\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u0006H\u0002R2\u0010\u000b\u001a&\u0012\u0004\u0012\u00020\r\u0012\b\u0012\u00060\u000eR\u00020\u00000\fj\u0012\u0012\u0004\u0012\u00020\r\u0012\b\u0012\u00060\u000eR\u00020\u0000`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"},
   d2 = {"Lorg/roboquant/strategies/EMACrossover;", "Lorg/roboquant/strategies/Strategy;", "fastPeriod", "", "slowPeriod", "smoothing", "", "minEvents", "priceType", "", "(IIDILjava/lang/String;)V", "calculators", "Ljava/util/HashMap;", "Lorg/roboquant/common/Asset;", "Lorg/roboquant/strategies/EMACrossover$EMACalculator;", "Lkotlin/collections/HashMap;", "fast", "slow", "createSignals", "", "Lorg/roboquant/common/Signal;", "event", "Lorg/roboquant/common/Event;", "generate", "asset", "price", "EMACalculator", "Factory", "roboquant"}
)
public final class EMACrossover implements Strategy {
   @NotNull
   public static final Factory Factory = new Factory((DefaultConstructorMarker)null);
   private final int minEvents;
   @NotNull
   private final String priceType;
   private final double fast;
   private final double slow;
   @NotNull
   private final HashMap calculators;

   public EMACrossover(int fastPeriod, int slowPeriod, double smoothing, int minEvents, @NotNull String priceType) {
      Intrinsics.checkNotNullParameter(priceType, "priceType");
      super();
      this.minEvents = minEvents;
      this.priceType = priceType;
      this.fast = (double)1.0F - smoothing / (double)(fastPeriod + 1);
      this.slow = (double)1.0F - smoothing / (double)(slowPeriod + 1);
      this.calculators = new HashMap();
   }

   // $FF: synthetic method
   public EMACrossover(int var1, int var2, double var3, int var5, String var6, int var7, DefaultConstructorMarker var8) {
      if ((var7 & 1) != 0) {
         var1 = 12;
      }

      if ((var7 & 2) != 0) {
         var2 = 26;
      }

      if ((var7 & 4) != 0) {
         var3 = (double)2.0F;
      }

      if ((var7 & 8) != 0) {
         var5 = var2;
      }

      if ((var7 & 16) != 0) {
         var6 = "DEFAULT";
      }

      this(var1, var2, var3, var5, var6);
   }

   @NotNull
   public List createSignals(@NotNull Event event) {
      Intrinsics.checkNotNullParameter(event, "event");
      List signals = (List)(new ArrayList());

      for(Map.Entry var4 : event.getPrices().entrySet()) {
         Asset asset = (Asset)var4.getKey();
         PriceItem priceItem = (PriceItem)var4.getValue();
         double price = priceItem.getPrice(this.priceType);
         Signal signal = this.generate(asset, price);
         if (signal != null) {
            signals.add(signal);
         }
      }

      return signals;
   }

   private final Signal generate(Asset asset, double price) {
      EMACalculator calculator = (EMACalculator)this.calculators.get(asset);
      if (calculator == null) {
         ((Map)this.calculators).put(asset, new EMACalculator(price));
         return null;
      } else {
         double oldDirection = calculator.getDirection();
         calculator.addPrice(price);
         if (calculator.addPrice(price)) {
            double newDirection = calculator.getDirection();
            if (oldDirection != newDirection) {
               return new Signal(asset, newDirection, (SignalType)null, (String)null, 12, (DefaultConstructorMarker)null);
            }
         }

         return null;
      }
   }

   public EMACrossover() {
      this(0, 0, (double)0.0F, 0, (String)null, 31, (DefaultConstructorMarker)null);
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\n\u0010\u0006¨\u0006\u000b"},
      d2 = {"Lorg/roboquant/strategies/EMACrossover$Factory;", "", "()V", "PERIODS_12_26", "Lorg/roboquant/strategies/EMACrossover;", "getPERIODS_12_26", "()Lorg/roboquant/strategies/EMACrossover;", "PERIODS_50_200", "getPERIODS_50_200", "PERIODS_5_15", "getPERIODS_5_15", "roboquant"}
   )
   public static final class Factory {
      private Factory() {
      }

      @NotNull
      public final EMACrossover getPERIODS_50_200() {
         return new EMACrossover(50, 200, (double)0.0F, 0, (String)null, 28, (DefaultConstructorMarker)null);
      }

      @NotNull
      public final EMACrossover getPERIODS_12_26() {
         return new EMACrossover(12, 26, (double)0.0F, 0, (String)null, 28, (DefaultConstructorMarker)null);
      }

      @NotNull
      public final EMACrossover getPERIODS_5_15() {
         return new EMACrossover(5, 15, (double)0.0F, 0, (String)null, 28, (DefaultConstructorMarker)null);
      }

      // $FF: synthetic method
      public Factory(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0003J\u0006\u0010\u0012\u001a\u00020\u0003R\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"},
      d2 = {"Lorg/roboquant/strategies/EMACrossover$EMACalculator;", "", "initialPrice", "", "(Lorg/roboquant/strategies/EMACrossover;D)V", "emaFast", "getEmaFast", "()D", "setEmaFast", "(D)V", "emaSlow", "getEmaSlow", "setEmaSlow", "step", "", "addPrice", "", "price", "getDirection", "roboquant"}
   )
   private final class EMACalculator {
      private long step = 1L;
      private double emaFast;
      private double emaSlow;

      public EMACalculator(double initialPrice) {
         this.emaFast = initialPrice;
         this.emaSlow = initialPrice;
      }

      public final double getEmaFast() {
         return this.emaFast;
      }

      public final void setEmaFast(double var1) {
         this.emaFast = var1;
      }

      public final double getEmaSlow() {
         return this.emaSlow;
      }

      public final void setEmaSlow(double var1) {
         this.emaSlow = var1;
      }

      public final boolean addPrice(double price) {
         this.emaFast = this.emaFast * EMACrossover.this.fast + ((double)1 - EMACrossover.this.fast) * price;
         this.emaSlow = this.emaSlow * EMACrossover.this.slow + ((double)1 - EMACrossover.this.slow) * price;
         ++this.step;
         return this.step >= (long)EMACrossover.this.minEvents;
      }

      public final double getDirection() {
         return this.emaFast > this.emaSlow ? (double)1.0F : (double)-1.0F;
      }
   }
}
