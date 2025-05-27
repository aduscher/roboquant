package org.roboquant.brokers;

import java.time.Instant;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NavigableMap;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Amount;
import org.roboquant.common.Currency;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u000eH\u0002J \u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u000eH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR,\u0010\u000b\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r0\fX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0019"},
   d2 = {"Lorg/roboquant/brokers/TimedExchangeRates;", "Lorg/roboquant/brokers/ExchangeRates;", "baseCurrency", "Lorg/roboquant/common/Currency;", "(Lorg/roboquant/common/Currency;)V", "getBaseCurrency", "()Lorg/roboquant/common/Currency;", "currencies", "", "getCurrencies", "()Ljava/util/Collection;", "exchangeRates", "", "Ljava/util/NavigableMap;", "Ljava/time/Instant;", "", "getExchangeRates", "()Ljava/util/Map;", "find", "currency", "time", "getRate", "amount", "Lorg/roboquant/common/Amount;", "to", "roboquant"}
)
public abstract class TimedExchangeRates implements ExchangeRates {
   @NotNull
   private final Currency baseCurrency;
   @NotNull
   private final Map exchangeRates;

   public TimedExchangeRates(@NotNull Currency baseCurrency) {
      Intrinsics.checkNotNullParameter(baseCurrency, "baseCurrency");
      super();
      this.baseCurrency = baseCurrency;
      this.exchangeRates = (Map)(new LinkedHashMap());
   }

   @NotNull
   protected final Currency getBaseCurrency() {
      return this.baseCurrency;
   }

   @NotNull
   protected final Map getExchangeRates() {
      return this.exchangeRates;
   }

   @NotNull
   public final Collection getCurrencies() {
      return (Collection)SetsKt.plus(this.exchangeRates.keySet(), (Iterable)SetsKt.setOf(this.baseCurrency));
   }

   private final double find(Currency currency, Instant time) {
      NavigableMap rates = (NavigableMap)MapsKt.getValue(this.exchangeRates, currency);
      Map.Entry var10000 = rates.floorEntry(time);
      if (var10000 == null) {
         var10000 = rates.firstEntry();
      }

      Map.Entry result = var10000;
      Object var5 = result.getValue();
      Intrinsics.checkNotNullExpressionValue(var5, "<get-value>(...)");
      return ((Number)var5).doubleValue();
   }

   public double getRate(@NotNull Amount amount, @NotNull Currency to, @NotNull Instant time) {
      Intrinsics.checkNotNullParameter(amount, "amount");
      Intrinsics.checkNotNullParameter(to, "to");
      Intrinsics.checkNotNullParameter(time, "time");
      Currency from = amount.getCurrency();
      if (from == to) {
         return (double)1.0F;
      } else {
         return to == this.baseCurrency ? this.find(from, time) : (from == this.baseCurrency ? (double)1.0F / this.find(to, time) : this.find(from, time) * ((double)1.0F / this.find(to, time)));
      }
   }

   @NotNull
   public Amount convert(@NotNull Amount amount, @NotNull Currency to, @NotNull Instant time) {
      return ExchangeRates.DefaultImpls.convert(this, amount, to, time);
   }
}
