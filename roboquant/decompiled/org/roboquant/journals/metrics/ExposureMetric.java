package org.roboquant.journals.metrics;

import java.time.Instant;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Account;
import org.roboquant.common.Amount;
import org.roboquant.common.Asset;
import org.roboquant.common.Currency;
import org.roboquant.common.Event;
import org.roboquant.common.Position;
import org.roboquant.common.Wallet;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J@\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fH\u0016¨\u0006\u0010"},
   d2 = {"Lorg/roboquant/journals/metrics/ExposureMetric;", "Lorg/roboquant/journals/metrics/Metric;", "()V", "calculate", "", "", "", "event", "Lorg/roboquant/common/Event;", "account", "Lorg/roboquant/common/Account;", "signals", "", "Lorg/roboquant/common/Signal;", "orders", "Lorg/roboquant/common/Order;", "roboquant"}
)
public final class ExposureMetric implements Metric {
   @NotNull
   public Map calculate(@NotNull Event event, @NotNull Account account, @NotNull List signals, @NotNull List orders) {
      Intrinsics.checkNotNullParameter(event, "event");
      Intrinsics.checkNotNullParameter(account, "account");
      Intrinsics.checkNotNullParameter(signals, "signals");
      Intrinsics.checkNotNullParameter(orders, "orders");
      Wallet longExposure = new Wallet((IdentityHashMap)null, 1, (DefaultConstructorMarker)null);
      Wallet shortExposure = new Wallet((IdentityHashMap)null, 1, (DefaultConstructorMarker)null);
      Instant now = event.getTime();

      for(Map.Entry var9 : account.getPositions().entrySet()) {
         Asset asset = (Asset)var9.getKey();
         Position position = (Position)var9.getValue();
         Amount v = asset.value-u_zS35g(position.getSize-vehRhPc(), position.getMktPrice());
         if (position.getLong()) {
            longExposure.deposit(v);
         } else {
            shortExposure.deposit(v);
         }
      }

      Currency currency = account.getBaseCurrency();
      double longExposureValue = longExposure.convert(currency, now).getValue();
      double shortExposureValue = shortExposure.convert(currency, now).getValue();
      double netExposureValue = longExposureValue + shortExposureValue;
      double grossExposureValue = Math.abs(longExposureValue) + Math.abs(shortExposureValue);
      double total = account.getCash().convert(currency, now).getValue() + netExposureValue;
      Pair[] var19 = new Pair[]{TuplesKt.to("exposure.net", netExposureValue / total), TuplesKt.to("exposure.gross", grossExposureValue / total), TuplesKt.to("exposure.long", longExposureValue), TuplesKt.to("exposure.short", shortExposureValue)};
      return MapsKt.mapOf(var19);
   }

   public void reset() {
      Metric.DefaultImpls.reset(this);
   }
}
