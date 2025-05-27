package org.roboquant.journals.metrics;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Account;
import org.roboquant.common.Event;
import org.roboquant.common.PriceItem;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J@\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"},
   d2 = {"Lorg/roboquant/journals/metrics/PriceMetric;", "Lorg/roboquant/journals/metrics/Metric;", "priceType", "", "(Ljava/lang/String;)V", "calculate", "", "", "event", "Lorg/roboquant/common/Event;", "account", "Lorg/roboquant/common/Account;", "signals", "", "Lorg/roboquant/common/Signal;", "orders", "Lorg/roboquant/common/Order;", "roboquant"}
)
public final class PriceMetric implements Metric {
   @NotNull
   private final String priceType;

   public PriceMetric(@NotNull String priceType) {
      Intrinsics.checkNotNullParameter(priceType, "priceType");
      super();
      this.priceType = priceType;
   }

   // $FF: synthetic method
   public PriceMetric(String var1, int var2, DefaultConstructorMarker var3) {
      if ((var2 & 1) != 0) {
         var1 = "DEFAULT";
      }

      this(var1);
   }

   @NotNull
   public Map calculate(@NotNull Event event, @NotNull Account account, @NotNull List signals, @NotNull List orders) {
      Intrinsics.checkNotNullParameter(event, "event");
      Intrinsics.checkNotNullParameter(account, "account");
      Intrinsics.checkNotNullParameter(signals, "signals");
      Intrinsics.checkNotNullParameter(orders, "orders");
      Map var5 = MapsKt.createMapBuilder();
      Map $this$calculate_u24lambda_u240 = var5;
      int var7 = 0;

      for(PriceItem action : event.getPrices().values()) {
         String var10000 = ("price." + this.priceType + "." + action.getAsset().getSymbol()).toLowerCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var10000, "toLowerCase(...)");
         String name = var10000;
         $this$calculate_u24lambda_u240.put(name, action.getPrice(this.priceType));
      }

      return MapsKt.build(var5);
   }

   public void reset() {
      Metric.DefaultImpls.reset(this);
   }

   public PriceMetric() {
      this((String)null, 1, (DefaultConstructorMarker)null);
   }
}
