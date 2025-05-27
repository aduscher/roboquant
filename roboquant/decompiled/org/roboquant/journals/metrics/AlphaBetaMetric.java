package org.roboquant.journals.metrics;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.hipparchus.stat.correlation.Covariance;
import org.hipparchus.stat.descriptive.moment.Variance;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Account;
import org.roboquant.common.Asset;
import org.roboquant.common.Event;
import org.roboquant.common.ExtensionsKt;
import org.roboquant.common.PriceItem;
import org.roboquant.common.PriceSeries;
import org.roboquant.common.Timeframe;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0013\n\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ@\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00070\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0 H\u0016J\u001c\u0010$\u001a\u00020\u00072\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\u001aH\u0002J\b\u0010&\u001a\u00020'H\u0016J\f\u0010(\u001a\u00020\u0007*\u00020)H\u0002R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00070\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"},
   d2 = {"Lorg/roboquant/journals/metrics/AlphaBetaMetric;", "Lorg/roboquant/journals/metrics/Metric;", "period", "", "priceType", "", "riskFreeReturn", "", "(ILjava/lang/String;D)V", "equityData", "Lorg/roboquant/common/PriceSeries;", "initialized", "", "marketData", "oldEquity", "oldPrices", "", "Lorg/roboquant/common/Asset;", "timeframe", "Lorg/roboquant/common/Timeframe;", "getTimeframe", "()Lorg/roboquant/common/Timeframe;", "times", "Ljava/util/LinkedList;", "Ljava/time/Instant;", "calculate", "", "event", "Lorg/roboquant/common/Event;", "account", "Lorg/roboquant/common/Account;", "signals", "", "Lorg/roboquant/common/Signal;", "orders", "Lorg/roboquant/common/Order;", "getMarketReturn", "prices", "reset", "", "product", "", "roboquant"}
)
@SourceDebugExtension({"SMAP\nAlphaBetaMetric.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AlphaBetaMetric.kt\norg/roboquant/journals/metrics/AlphaBetaMetric\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,135:1\n453#2:136\n403#2:137\n1238#3,4:138\n*S KotlinDebug\n*F\n+ 1 AlphaBetaMetric.kt\norg/roboquant/journals/metrics/AlphaBetaMetric\n*L\n92#1:136\n92#1:137\n92#1:138,4\n*E\n"})
public final class AlphaBetaMetric implements Metric {
   private final int period;
   @NotNull
   private final String priceType;
   private final double riskFreeReturn;
   @NotNull
   private final PriceSeries marketData;
   @NotNull
   private final PriceSeries equityData;
   @NotNull
   private Map oldPrices;
   private boolean initialized;
   private double oldEquity;
   @NotNull
   private LinkedList times;

   public AlphaBetaMetric(int period, @NotNull String priceType, double riskFreeReturn) {
      Intrinsics.checkNotNullParameter(priceType, "priceType");
      super();
      this.period = period;
      this.priceType = priceType;
      this.riskFreeReturn = riskFreeReturn;
      this.marketData = new PriceSeries(this.period);
      this.equityData = new PriceSeries(this.period);
      this.oldPrices = (Map)(new LinkedHashMap());
      this.times = new LinkedList();
   }

   // $FF: synthetic method
   public AlphaBetaMetric(int var1, String var2, double var3, int var5, DefaultConstructorMarker var6) {
      if ((var5 & 2) != 0) {
         var2 = "DEFAULT";
      }

      if ((var5 & 4) != 0) {
         var3 = ExtensionsKt.getPercent((Number)0);
      }

      this(var1, var2, var3);
   }

   private final double getMarketReturn(Map prices) {
      double sum = (double)0.0F;
      int cnt = 0;

      for(Asset asset : prices.keySet()) {
         if (this.oldPrices.containsKey(asset)) {
            ++cnt;
            sum += ((Number)MapsKt.getValue(prices, asset)).doubleValue() / ((Number)MapsKt.getValue(this.oldPrices, asset)).doubleValue() - (double)1.0F;
         }
      }

      return sum / (double)cnt;
   }

   private final Timeframe getTimeframe() {
      return new Timeframe((Instant)CollectionsKt.first((List)this.times), (Instant)CollectionsKt.last((List)this.times), true);
   }

   private final double product(double[] $this$product) {
      double result = (double)1.0F;
      int i = 0;

      for(int var5 = $this$product.length; i < var5; ++i) {
         result *= $this$product[i] + (double)1.0F;
      }

      return result - (double)1.0F;
   }

   @NotNull
   public Map calculate(@NotNull Event event, @NotNull Account account, @NotNull List signals, @NotNull List orders) {
      Intrinsics.checkNotNullParameter(event, "event");
      Intrinsics.checkNotNullParameter(account, "account");
      Intrinsics.checkNotNullParameter(signals, "signals");
      Intrinsics.checkNotNullParameter(orders, "orders");
      if (event.getPrices().isEmpty()) {
         return MapsKt.emptyMap();
      } else {
         Map $this$mapValues$iv = event.getPrices();
         int $i$f$mapValues = 0;
         Map destination$iv$iv = (Map)(new LinkedHashMap(MapsKt.mapCapacity($this$mapValues$iv.size())));
         int $i$f$mapValuesTo = 0;
         Iterable $this$associateByTo$iv$iv$iv = (Iterable)$this$mapValues$iv.entrySet();
         int $i$f$associateByTo = 0;

         for(Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
            Map.Entry it$iv$iv = (Map.Entry)element$iv$iv$iv;
            int var17 = 0;
            Object var10001 = it$iv$iv.getKey();
            Map.Entry it = (Map.Entry)element$iv$iv$iv;
            Object var21 = var10001;
            int var19 = 0;
            Double var22 = ((PriceItem)it.getValue()).getPrice(this.priceType);
            destination$iv$iv.put(var21, var22);
         }

         double equity = account.equityAmount().getValue();
         if (this.initialized) {
            double mr = this.getMarketReturn(destination$iv$iv);
            this.marketData.add(mr);
            this.equityData.add(equity / this.oldEquity - (double)1);
            this.times.add(event.getTime());
            if (this.times.size() > this.period) {
               this.times.removeFirst();
            }

            $i$f$mapValuesTo = this.times.size() == this.marketData.getSize();
            if (_Assertions.ENABLED && !$i$f$mapValuesTo) {
               String var28 = "Assertion failed";
               throw new AssertionError(var28);
            }
         }

         this.oldPrices.putAll(destination$iv$iv);
         this.oldEquity = equity;
         this.initialized = true;
         if (this.marketData.isFull() && this.equityData.isFull()) {
            double[] mr = this.marketData.toDoubleArray();
            double[] pr = this.equityData.toDoubleArray();
            double beta = (new Covariance()).covariance(mr, pr) / (new Variance()).evaluate(mr);
            double totalMr = this.getTimeframe().annualize(this.product(mr));
            double totalPr = this.getTimeframe().annualize(this.product(pr));
            double alpha = totalPr - this.riskFreeReturn - beta * (totalMr - this.riskFreeReturn);
            Pair[] var31 = new Pair[]{TuplesKt.to("account.alpha", alpha), TuplesKt.to("account.beta", beta)};
            return MapsKt.mapOf(var31);
         } else {
            return MapsKt.emptyMap();
         }
      }
   }

   public void reset() {
      this.equityData.clear();
      this.marketData.clear();
      this.oldPrices.clear();
      this.oldEquity = (double)0.0F;
      this.times.clear();
      this.initialized = false;
   }
}
