package org.roboquant.journals.metrics;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Account;
import org.roboquant.common.Event;
import org.roboquant.common.PriceItem;
import org.roboquant.common.Timeframe;
import org.roboquant.common.Wallet;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u0019B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J@\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u00122\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0012H\u0016J\b\u0010\u0016\u001a\u00020\nH\u0002J\b\u0010\u0017\u001a\u00020\u0018H\u0016R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"},
   d2 = {"Lorg/roboquant/journals/metrics/PNLMetric;", "Lorg/roboquant/journals/metrics/Metric;", "priceType", "", "(Ljava/lang/String;)V", "assetReturns", "", "Lorg/roboquant/common/Asset;", "Lorg/roboquant/journals/metrics/PNLMetric$AssetReturn;", "equity", "", "calculate", "", "event", "Lorg/roboquant/common/Event;", "account", "Lorg/roboquant/common/Account;", "signals", "", "Lorg/roboquant/common/Signal;", "orders", "Lorg/roboquant/common/Order;", "marketReturn", "reset", "", "AssetReturn", "roboquant"}
)
@SourceDebugExtension({"SMAP\nPNLMetric.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PNLMetric.kt\norg/roboquant/journals/metrics/PNLMetric\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,115:1\n1855#2:116\n1856#2:124\n372#3,7:117\n*S KotlinDebug\n*F\n+ 1 PNLMetric.kt\norg/roboquant/journals/metrics/PNLMetric\n*L\n96#1:116\n96#1:124\n97#1:117,7\n*E\n"})
public final class PNLMetric implements Metric {
   @NotNull
   private final String priceType;
   private double equity;
   @NotNull
   private final Map assetReturns;

   public PNLMetric(@NotNull String priceType) {
      Intrinsics.checkNotNullParameter(priceType, "priceType");
      super();
      this.priceType = priceType;
      this.equity = Double.NaN;
      this.assetReturns = (Map)(new LinkedHashMap());
   }

   // $FF: synthetic method
   public PNLMetric(String var1, int var2, DefaultConstructorMarker var3) {
      if ((var2 & 1) != 0) {
         var1 = "DEFAULT";
      }

      this(var1);
   }

   private final double marketReturn() {
      double sum = (double)0.0F;
      double totalWeights = (double)0.0F;

      for(AssetReturn r : this.assetReturns.values()) {
         long weight = r.getDuration();
         sum += r.calcReturn() * (double)weight;
         totalWeights += (double)weight;
      }

      return totalWeights > (double)0.0F ? sum / totalWeights : (double)0.0F;
   }

   @NotNull
   public Map calculate(@NotNull Event event, @NotNull Account account, @NotNull List signals, @NotNull List orders) {
      Intrinsics.checkNotNullParameter(event, "event");
      Intrinsics.checkNotNullParameter(account, "account");
      Intrinsics.checkNotNullParameter(signals, "signals");
      Intrinsics.checkNotNullParameter(orders, "orders");
      if (Double.isNaN(this.equity)) {
         this.equity = account.equityAmount().getValue();
      }

      double pnl = account.equityAmount().getValue() - this.equity;
      Wallet pnl2 = account.unrealizedPNL();
      double unrealizedPNL = pnl2.convert(account.getBaseCurrency(), event.getTime()).getValue();
      Iterable $this$forEach$iv = (Iterable)event.getPrices().values();
      int $i$f$forEach = 0;

      for(Object element$iv : $this$forEach$iv) {
         PriceItem it = (PriceItem)element$iv;
         int var15 = 0;
         Map $this$getOrPut$iv = this.assetReturns;
         Object key$iv = it.getAsset();
         int $i$f$getOrPut = 0;
         Object value$iv = $this$getOrPut$iv.get(key$iv);
         Object var10000;
         if (value$iv == null) {
            int var20 = 0;
            Object answer$iv = new AssetReturn(event.getTime(), it.getPrice(this.priceType), (Instant)null, (double)0.0F, 12, (DefaultConstructorMarker)null);
            $this$getOrPut$iv.put(key$iv, answer$iv);
            var10000 = answer$iv;
         } else {
            var10000 = value$iv;
         }

         AssetReturn r = (AssetReturn)var10000;
         r.update(event.getTime(), PriceItem.DefaultImpls.getPrice$default(it, (String)null, 1, (Object)null));
      }

      Pair[] var23 = new Pair[]{TuplesKt.to("pnl.realized", pnl - unrealizedPNL), TuplesKt.to("pnl.unrealized", unrealizedPNL), TuplesKt.to("pnl.total", pnl), TuplesKt.to("pnl.mkt", this.marketReturn())};
      return MapsKt.mapOf(var23);
   }

   public void reset() {
      this.equity = Double.NaN;
      this.assetReturns.clear();
   }

   public PNLMetric() {
      this((String)null, 1, (DefaultConstructorMarker)null);
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\u0006\u0010\u0017\u001a\u00020\u0005J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0005R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0012\"\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006\u001c"},
      d2 = {"Lorg/roboquant/journals/metrics/PNLMetric$AssetReturn;", "", "start", "Ljava/time/Instant;", "first", "", "end", "last", "(Ljava/time/Instant;DLjava/time/Instant;D)V", "duration", "", "getDuration", "()J", "getEnd", "()Ljava/time/Instant;", "setEnd", "(Ljava/time/Instant;)V", "getFirst", "()D", "getLast", "setLast", "(D)V", "getStart", "calcReturn", "update", "", "time", "value", "roboquant"}
   )
   private static final class AssetReturn {
      @NotNull
      private final Instant start;
      private final double first;
      @NotNull
      private Instant end;
      private double last;

      public AssetReturn(@NotNull Instant start, double first, @NotNull Instant end, double last) {
         Intrinsics.checkNotNullParameter(start, "start");
         Intrinsics.checkNotNullParameter(end, "end");
         super();
         this.start = start;
         this.first = first;
         this.end = end;
         this.last = last;
      }

      // $FF: synthetic method
      public AssetReturn(Instant var1, double var2, Instant var4, double var5, int var7, DefaultConstructorMarker var8) {
         if ((var7 & 4) != 0) {
            var4 = var1;
         }

         if ((var7 & 8) != 0) {
            var5 = var2;
         }

         this(var1, var2, var4, var5);
      }

      @NotNull
      public final Instant getStart() {
         return this.start;
      }

      public final double getFirst() {
         return this.first;
      }

      @NotNull
      public final Instant getEnd() {
         return this.end;
      }

      public final void setEnd(@NotNull Instant var1) {
         Intrinsics.checkNotNullParameter(var1, "<set-?>");
         this.end = var1;
      }

      public final double getLast() {
         return this.last;
      }

      public final void setLast(double var1) {
         this.last = var1;
      }

      public final long getDuration() {
         return (new Timeframe(this.start, this.end, true)).getDuration().toMillis();
      }

      public final double calcReturn() {
         return this.last / this.first - (double)1.0F;
      }

      public final void update(@NotNull Instant time, double value) {
         Intrinsics.checkNotNullParameter(time, "time");
         this.end = time;
         this.last = value;
      }
   }
}
