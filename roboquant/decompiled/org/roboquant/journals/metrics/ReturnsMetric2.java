package org.roboquant.journals.metrics;

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
import org.hipparchus.stat.correlation.Covariance;
import org.hipparchus.stat.descriptive.moment.Mean;
import org.hipparchus.stat.descriptive.moment.Skewness;
import org.hipparchus.stat.descriptive.moment.StandardDeviation;
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
   d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B-\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0018\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\u0015H\u0002J@\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00060\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001dH\u0016J\b\u0010!\u001a\u00020\"H\u0016J\u0018\u0010#\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\f\u001a\u00020\u0015H\u0002J\u0010\u0010$\u001a\u00020\"2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010%\u001a\u00020\"2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\f\u0010&\u001a\u00020\u0006*\u00020\u0015H\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00060\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"},
   d2 = {"Lorg/roboquant/journals/metrics/ReturnsMetric2;", "Lorg/roboquant/journals/metrics/Metric;", "minSize", "", "maxSize", "riskFreeRate", "", "priceType", "", "(IIDLjava/lang/String;)V", "accountReturns", "Lorg/roboquant/common/PriceSeries;", "benchmarkReturns", "equity", "prices", "", "Lorg/roboquant/common/Asset;", "times", "Lorg/roboquant/journals/metrics/TimeBuffer;", "beta", "portfolioReturns", "", "calculate", "", "event", "Lorg/roboquant/common/Event;", "account", "Lorg/roboquant/common/Account;", "signals", "", "Lorg/roboquant/common/Signal;", "orders", "Lorg/roboquant/common/Order;", "reset", "", "sharpRatio2", "updateAccount", "updateBenchmark", "cumReturns", "roboquant"}
)
@SourceDebugExtension({"SMAP\nReturnsMetric2.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ReturnsMetric2.kt\norg/roboquant/journals/metrics/ReturnsMetric2\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,178:1\n1#2:179\n12804#3,3:180\n*S KotlinDebug\n*F\n+ 1 ReturnsMetric2.kt\norg/roboquant/journals/metrics/ReturnsMetric2\n*L\n128#1:180,3\n*E\n"})
public final class ReturnsMetric2 implements Metric {
   private final int minSize;
   private final double riskFreeRate;
   @NotNull
   private final String priceType;
   @NotNull
   private final Map prices;
   private double equity;
   @NotNull
   private final PriceSeries benchmarkReturns;
   @NotNull
   private final PriceSeries accountReturns;
   @NotNull
   private TimeBuffer times;

   public ReturnsMetric2(int minSize, int maxSize, double riskFreeRate, @NotNull String priceType) {
      Intrinsics.checkNotNullParameter(priceType, "priceType");
      super();
      this.minSize = minSize;
      this.riskFreeRate = riskFreeRate;
      this.priceType = priceType;
      this.prices = (Map)(new LinkedHashMap());
      this.equity = Double.NaN;
      this.benchmarkReturns = new PriceSeries(maxSize);
      this.accountReturns = new PriceSeries(maxSize);
      this.times = new TimeBuffer(maxSize);
      if (this.minSize <= 1) {
         int var8 = 0;
         String var9 = "minSize should be a larger than 1";
         throw new IllegalArgumentException(var9.toString());
      } else if (maxSize < this.minSize) {
         int var6 = 0;
         String var7 = "maxSize should be larger or equal than minSize";
         throw new IllegalArgumentException(var7.toString());
      }
   }

   // $FF: synthetic method
   public ReturnsMetric2(int var1, int var2, double var3, String var5, int var6, DefaultConstructorMarker var7) {
      if ((var6 & 1) != 0) {
         var1 = 750;
      }

      if ((var6 & 2) != 0) {
         var2 = var1;
      }

      if ((var6 & 4) != 0) {
         var3 = (double)0.0F;
      }

      if ((var6 & 8) != 0) {
         var5 = "DEFAULT";
      }

      this(var1, var2, var3, var5);
   }

   private final void updateBenchmark(Event event) {
      double benchmarkReturn = (double)0.0F;
      int n = 0;

      for(Map.Entry var6 : event.getPrices().entrySet()) {
         Asset asset = (Asset)var6.getKey();
         PriceItem action = (PriceItem)var6.getValue();
         double price = action.getPrice(this.priceType);
         Double lastPrice = (Double)this.prices.get(asset);
         if (lastPrice != null) {
            double r = (price - lastPrice) / lastPrice;
            benchmarkReturn += r;
            ++n;
         }

         Double var13 = price;
         this.prices.put(asset, var13);
      }

      if (n == 0) {
         this.benchmarkReturns.add((double)0.0F);
      } else {
         this.benchmarkReturns.add(benchmarkReturn / (double)n);
      }

   }

   private final void updateAccount(Account account) {
      double newEquity = account.equity().convert(account.getBaseCurrency(), account.getLastUpdate()).getValue();
      double result = this.equity;
      if (!Double.isInfinite(result) && !Double.isNaN(result)) {
         result = (newEquity - this.equity) / this.equity;
         this.accountReturns.add(result);
      } else {
         this.accountReturns.add((double)0.0F);
      }

      this.equity = newEquity;
   }

   private final double sharpRatio2(double[] portfolioReturns, double[] benchmarkReturns) {
      double t = this.times.eventsPerYears();
      double[] accessReturns = ExtensionsKt.minus(portfolioReturns, benchmarkReturns);
      double stdExcessReturns = (new StandardDeviation()).evaluate(accessReturns) * Math.sqrt(t);
      double mean = (new Mean()).evaluate(accessReturns) * t;
      return mean / (stdExcessReturns + 1.0E-10);
   }

   private final double beta(double[] portfolioReturns, double[] benchmarkReturns) {
      double covariance = (new Covariance()).covariance(portfolioReturns, benchmarkReturns);
      double variance = (new Variance()).evaluate(benchmarkReturns);
      return covariance / variance;
   }

   private final double cumReturns(double[] $this$cumReturns) {
      double[] $this$fold$iv = $this$cumReturns;
      double initial$iv = (double)1.0F;
      int $i$f$fold = 0;
      double accumulator$iv = initial$iv;
      int var8 = 0;

      for(int var9 = $this$cumReturns.length; var8 < var9; ++var8) {
         double element$iv = $this$fold$iv[var8];
         int var16 = 0;
         accumulator$iv *= element$iv + (double)1.0F;
      }

      return accumulator$iv - (double)1.0F;
   }

   @NotNull
   public Map calculate(@NotNull Event event, @NotNull Account account, @NotNull List signals, @NotNull List orders) {
      Intrinsics.checkNotNullParameter(event, "event");
      Intrinsics.checkNotNullParameter(account, "account");
      Intrinsics.checkNotNullParameter(signals, "signals");
      Intrinsics.checkNotNullParameter(orders, "orders");
      this.times.add(event.getTime());
      this.updateBenchmark(event);
      this.updateAccount(account);
      if (this.benchmarkReturns.getSize() >= this.minSize) {
         double[] mr = this.benchmarkReturns.toDoubleArray();
         double[] ar = this.accountReturns.toDoubleArray();
         Timeframe tf = this.times.getTimeframe();
         double annualPortfolioReturn = tf.annualize(this.cumReturns(ar));
         double annualMarketReturn = tf.annualize(this.cumReturns(mr));
         double t = this.times.eventsPerYears();
         double beta = this.beta(ar, mr);
         double alpha = annualPortfolioReturn - this.riskFreeRate - beta * (annualMarketReturn - this.riskFreeRate);
         double stdReturns = (new StandardDeviation()).evaluate(ar) * Math.sqrt(t);
         double sharpeRatio = (annualPortfolioReturn - this.riskFreeRate) / (stdReturns + 1.0E-10);
         double sharpeRatio2 = this.sharpRatio2(ar, mr);
         double excess = annualPortfolioReturn - annualMarketReturn;
         Pair[] var26 = new Pair[]{TuplesKt.to("returns.market", annualMarketReturn), TuplesKt.to("returns.account", annualPortfolioReturn), TuplesKt.to("returns.excess", excess), TuplesKt.to("returns.beta", beta), TuplesKt.to("returns.alpha", alpha), TuplesKt.to("returns.skewness", (new Skewness()).evaluate(ar)), TuplesKt.to("returns.std", stdReturns), TuplesKt.to("returns.sharperatio", sharpeRatio), TuplesKt.to("returns.sharperatio2", sharpeRatio2)};
         return MapsKt.mapOf(var26);
      } else {
         return MapsKt.emptyMap();
      }
   }

   public void reset() {
      this.prices.clear();
      this.equity = Double.NaN;
      this.benchmarkReturns.clear();
      this.accountReturns.clear();
      this.times.clear();
   }

   public ReturnsMetric2() {
      this(0, 0, (double)0.0F, (String)null, 15, (DefaultConstructorMarker)null);
   }
}
