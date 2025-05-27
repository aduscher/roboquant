package org.roboquant.strategies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Asset;
import org.roboquant.common.Event;
import org.roboquant.common.ExtensionsKt;
import org.roboquant.common.PriceSeries;
import org.roboquant.common.PriceSeriesKt;
import org.roboquant.common.RoboquantException;
import org.roboquant.common.Signal;
import org.roboquant.common.SignalType;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0013\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0017\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016¢\u0006\u0002\u0010\u0014J\u001a\u0010\u0015\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0006\u0010\u0017\u001a\u00020\u0018R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"},
   d2 = {"Lorg/roboquant/strategies/HistoricPriceStrategy;", "Lorg/roboquant/strategies/Strategy;", "period", "", "priceType", "", "(ILjava/lang/String;)V", "history", "", "Lorg/roboquant/common/Asset;", "Lorg/roboquant/common/PriceSeries;", "createSignals", "", "Lorg/roboquant/common/Signal;", "event", "Lorg/roboquant/common/Event;", "generateRating", "", "data", "", "([D)Ljava/lang/Double;", "generateSignal", "asset", "reset", "", "Companion", "roboquant"}
)
public abstract class HistoricPriceStrategy implements Strategy {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private final int period;
   @NotNull
   private final String priceType;
   @NotNull
   private final Map history;
   public static final double BUY = (double)1.0F;
   public static final double SELL = (double)-1.0F;

   public HistoricPriceStrategy(int period, @NotNull String priceType) {
      Intrinsics.checkNotNullParameter(priceType, "priceType");
      super();
      this.period = period;
      this.priceType = priceType;
      this.history = (Map)(new LinkedHashMap());
   }

   // $FF: synthetic method
   public HistoricPriceStrategy(int var1, String var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = "DEFAULT";
      }

      this(var1, var2);
   }

   @NotNull
   public List createSignals(@NotNull Event event) {
      Intrinsics.checkNotNullParameter(event, "event");
      Set assets = PriceSeriesKt.addAll(this.history, event, this.period, this.priceType);
      List result = (List)(new ArrayList());

      for(Asset asset : assets) {
         double[] data = ((PriceSeries)MapsKt.getValue(this.history, asset)).toDoubleArray();
         Signal signal = this.generateSignal(asset, data);
         ExtensionsKt.addNotNull((Collection)result, signal);
      }

      return result;
   }

   @Nullable
   public Signal generateSignal(@NotNull Asset asset, @NotNull double[] data) {
      Intrinsics.checkNotNullParameter(asset, "asset");
      Intrinsics.checkNotNullParameter(data, "data");
      Double rating = this.generateRating(data);
      return rating == null ? null : new Signal(asset, rating, (SignalType)null, (String)null, 12, (DefaultConstructorMarker)null);
   }

   @Nullable
   public Double generateRating(@NotNull double[] data) {
      Intrinsics.checkNotNullParameter(data, "data");
      throw new RoboquantException("Should override generateSignal or generateRating");
   }

   public final void reset() {
      this.history.clear();
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"},
      d2 = {"Lorg/roboquant/strategies/HistoricPriceStrategy$Companion;", "", "()V", "BUY", "", "SELL", "roboquant"}
   )
   public static final class Companion {
      private Companion() {
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
