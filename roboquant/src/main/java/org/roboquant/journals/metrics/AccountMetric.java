package org.roboquant.journals.metrics;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Account;
import org.roboquant.common.Event;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J@\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00040\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000eH\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"},
   d2 = {"Lorg/roboquant/journals/metrics/AccountMetric;", "Lorg/roboquant/journals/metrics/Metric;", "()V", "mdd", "", "peak", "calculate", "", "", "event", "Lorg/roboquant/common/Event;", "account", "Lorg/roboquant/common/Account;", "signals", "", "Lorg/roboquant/common/Signal;", "orders", "Lorg/roboquant/common/Order;", "reset", "", "roboquant"}
)
public final class AccountMetric implements Metric {
   private double peak = Double.MIN_VALUE;
   private double mdd = Double.MAX_VALUE;

   @NotNull
   public Map calculate(@NotNull Event event, @NotNull Account account, @NotNull List signals, @NotNull List orders) {
      Intrinsics.checkNotNullParameter(event, "event");
      Intrinsics.checkNotNullParameter(account, "account");
      Intrinsics.checkNotNullParameter(signals, "signals");
      Intrinsics.checkNotNullParameter(orders, "orders");
      Map $this$calculate_u24lambda_u240 = MapsKt.createMapBuilder();
      int var7 = 0;
      double equity = account.convert(account.equity()).getValue();
      if (equity > this.peak) {
         this.peak = equity;
      }

      double dd = (equity - this.peak) / this.peak;
      if (dd < this.mdd) {
         this.mdd = dd;
      }

      $this$calculate_u24lambda_u240.put("account.orders", (double)account.getOrders().size());
      $this$calculate_u24lambda_u240.put("account.positions", (double)account.getPositions().size());
      $this$calculate_u24lambda_u240.put("account.cash", account.convert(account.getCash()).getValue());
      $this$calculate_u24lambda_u240.put("account.buyingpower", account.getBuyingPower().getValue());
      $this$calculate_u24lambda_u240.put("account.equity", equity);
      $this$calculate_u24lambda_u240.put("account.mdd", this.mdd);
      return MapsKt.build($this$calculate_u24lambda_u240);
   }

   public void reset() {
      this.peak = Double.MIN_VALUE;
      this.mdd = Double.MAX_VALUE;
   }
}
