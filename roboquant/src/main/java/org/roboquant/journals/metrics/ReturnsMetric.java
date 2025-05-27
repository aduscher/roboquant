package org.roboquant.journals.metrics;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.hipparchus.stat.descriptive.DescriptiveStatistics;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Account;
import org.roboquant.common.Event;
import org.roboquant.common.TimeSpan;
import org.roboquant.common.TimeSpanKt;
import org.roboquant.common.Timeframe;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ@\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00030\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0019H\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"},
   d2 = {"Lorg/roboquant/journals/metrics/ReturnsMetric;", "Lorg/roboquant/journals/metrics/Metric;", "riskFreeRate", "", "minPeriods", "", "period", "Lorg/roboquant/common/TimeSpan;", "annualize", "", "(DILorg/roboquant/common/TimeSpan;Z)V", "lastTime", "Ljava/time/Instant;", "lastValue", "nextTime", "stats", "Lorg/hipparchus/stat/descriptive/DescriptiveStatistics;", "calculate", "", "", "event", "Lorg/roboquant/common/Event;", "account", "Lorg/roboquant/common/Account;", "signals", "", "Lorg/roboquant/common/Signal;", "orders", "Lorg/roboquant/common/Order;", "reset", "", "roboquant"}
)
public final class ReturnsMetric implements Metric {
   private final double riskFreeRate;
   private final int minPeriods;
   @NotNull
   private final TimeSpan period;
   private final boolean annualize;
   @NotNull
   private DescriptiveStatistics stats;
   private double lastValue;
   @NotNull
   private Instant lastTime;
   @NotNull
   private Instant nextTime;

   public ReturnsMetric(double riskFreeRate, int minPeriods, @NotNull TimeSpan period, boolean annualize) {
      Intrinsics.checkNotNullParameter(period, "period");
      super();
      this.riskFreeRate = riskFreeRate;
      this.minPeriods = minPeriods;
      this.period = period;
      this.annualize = annualize;
      this.stats = new DescriptiveStatistics();
      this.lastValue = Double.NaN;
      Instant var10001 = Instant.MIN;
      Intrinsics.checkNotNullExpressionValue(var10001, "MIN");
      this.lastTime = var10001;
      var10001 = Instant.MIN;
      Intrinsics.checkNotNullExpressionValue(var10001, "MIN");
      this.nextTime = var10001;
   }

   // $FF: synthetic method
   public ReturnsMetric(double var1, int var3, TimeSpan var4, boolean var5, int var6, DefaultConstructorMarker var7) {
      if ((var6 & 1) != 0) {
         var1 = (double)0.0F;
      }

      if ((var6 & 2) != 0) {
         var3 = 2;
      }

      if ((var6 & 4) != 0) {
         var4 = TimeSpanKt.getYears(1);
      }

      if ((var6 & 8) != 0) {
         var5 = true;
      }

      this(var1, var3, var4, var5);
   }

   @NotNull
   public Map calculate(@NotNull Event event, @NotNull Account account, @NotNull List signals, @NotNull List orders) {
      Intrinsics.checkNotNullParameter(event, "event");
      Intrinsics.checkNotNullParameter(account, "account");
      Intrinsics.checkNotNullParameter(signals, "signals");
      Intrinsics.checkNotNullParameter(orders, "orders");
      Instant time = event.getTime();
      if (Intrinsics.areEqual(this.nextTime, Instant.MIN)) {
         this.nextTime = TimeSpanKt.plus(time, this.period);
         this.lastTime = time;
         this.lastValue = account.equity().convert(account.getBaseCurrency(), event.getTime()).getValue();
      }

      if (time.compareTo(this.nextTime) >= 0) {
         double value = account.equity().convert(account.getBaseCurrency(), event.getTime()).getValue();
         double periodReturn = (value - this.lastValue) / this.lastValue;
         Timeframe tf = new Timeframe(this.lastTime, time, false, 4, (DefaultConstructorMarker)null);
         double returns = this.annualize ? tf.annualize(periodReturn) : periodReturn;
         this.stats.addValue(returns);
         this.nextTime = TimeSpanKt.plus(time, this.period);
         this.lastTime = time;
         this.lastValue = value;
         if (this.stats.getN() >= (long)this.minPeriods) {
            double mean = this.stats.getMean();
            double std = this.stats.getStandardDeviation();
            double sharpeRatio = (mean - this.riskFreeRate) / (std + 1.0E-10);
            Pair[] var19 = new Pair[]{TuplesKt.to("returns.mean", mean), TuplesKt.to("returns.std", std), TuplesKt.to("returns.sharperatio", sharpeRatio), TuplesKt.to("returns.min", this.stats.getMin()), TuplesKt.to("returns.max", this.stats.getMax()), TuplesKt.to("returns.last", returns)};
            return MapsKt.mapOf(var19);
         }
      }

      return MapsKt.emptyMap();
   }

   public void reset() {
      this.stats.clear();
      Instant var10001 = Instant.MIN;
      Intrinsics.checkNotNullExpressionValue(var10001, "MIN");
      this.lastTime = var10001;
      var10001 = Instant.MIN;
      Intrinsics.checkNotNullExpressionValue(var10001, "MIN");
      this.nextTime = var10001;
      this.lastValue = Double.NaN;
   }

   public ReturnsMetric() {
      this((double)0.0F, 0, (TimeSpan)null, false, 15, (DefaultConstructorMarker)null);
   }
}
