package org.roboquant.brokers;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Amount;
import org.roboquant.common.Currency;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B;\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012*\u0010\u0004\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u00060\u0005\"\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00070\n¢\u0006\u0002\u0010\u000bJ \u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0016\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u0007R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR*\u0010\t\u001a\u001e\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00030\u0003\u0012\f\u0012\n \u0011*\u0004\u0018\u00010\u00070\u00070\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"},
   d2 = {"Lorg/roboquant/brokers/FixedExchangeRates;", "Lorg/roboquant/brokers/ExchangeRates;", "baseCurrency", "Lorg/roboquant/common/Currency;", "rates", "", "Lkotlin/Pair;", "", "(Lorg/roboquant/common/Currency;[Lkotlin/Pair;)V", "exchangeRates", "", "(Lorg/roboquant/common/Currency;Ljava/util/Map;)V", "getBaseCurrency", "()Lorg/roboquant/common/Currency;", "setBaseCurrency", "(Lorg/roboquant/common/Currency;)V", "Ljava/util/concurrent/ConcurrentHashMap;", "kotlin.jvm.PlatformType", "getRate", "amount", "Lorg/roboquant/common/Amount;", "to", "time", "Ljava/time/Instant;", "setRate", "", "currency", "rate", "roboquant"}
)
public final class FixedExchangeRates implements ExchangeRates {
   @NotNull
   private Currency baseCurrency;
   @NotNull
   private final ConcurrentHashMap exchangeRates;

   public FixedExchangeRates(@NotNull Currency baseCurrency, @NotNull Map exchangeRates) {
      Intrinsics.checkNotNullParameter(baseCurrency, "baseCurrency");
      Intrinsics.checkNotNullParameter(exchangeRates, "exchangeRates");
      super();
      this.baseCurrency = baseCurrency;
      this.exchangeRates = new ConcurrentHashMap(exchangeRates);
   }

   @NotNull
   public final Currency getBaseCurrency() {
      return this.baseCurrency;
   }

   public final void setBaseCurrency(@NotNull Currency var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.baseCurrency = var1;
   }

   public FixedExchangeRates(@NotNull Currency baseCurrency, @NotNull Pair... rates) {
      Intrinsics.checkNotNullParameter(baseCurrency, "baseCurrency");
      Intrinsics.checkNotNullParameter(rates, "rates");
      this(baseCurrency, MapsKt.toMap(rates));
   }

   public double getRate(@NotNull Amount amount, @NotNull Currency to, @NotNull Instant time) {
      Intrinsics.checkNotNullParameter(amount, "amount");
      Intrinsics.checkNotNullParameter(to, "to");
      Intrinsics.checkNotNullParameter(time, "time");
      Currency from = amount.getCurrency();
      double var10000;
      if (from == to) {
         var10000 = (double)1.0F;
      } else if (to == this.baseCurrency) {
         Object var5 = MapsKt.getValue((Map)this.exchangeRates, from);
         Intrinsics.checkNotNullExpressionValue(var5, "getValue(...)");
         var10000 = ((Number)var5).doubleValue();
      } else if (from == this.baseCurrency) {
         Object var10001 = MapsKt.getValue((Map)this.exchangeRates, to);
         Intrinsics.checkNotNullExpressionValue(var10001, "getValue(...)");
         var10000 = (double)1.0F / ((Number)var10001).doubleValue();
      } else {
         var10000 = ((Number)MapsKt.getValue((Map)this.exchangeRates, from)).doubleValue() * (double)1.0F;
         Object var7 = MapsKt.getValue((Map)this.exchangeRates, to);
         Intrinsics.checkNotNullExpressionValue(var7, "getValue(...)");
         var10000 /= ((Number)var7).doubleValue();
      }

      return var10000;
   }

   public final void setRate(@NotNull Currency currency, double rate) {
      Intrinsics.checkNotNullParameter(currency, "currency");
      Double var4 = rate;
      ((Map)this.exchangeRates).put(currency, var4);
   }

   @NotNull
   public Amount convert(@NotNull Amount amount, @NotNull Currency to, @NotNull Instant time) {
      return ExchangeRates.DefaultImpls.convert(this, amount, to, time);
   }
}
