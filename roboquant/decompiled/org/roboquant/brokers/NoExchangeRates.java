package org.roboquant.brokers;

import java.time.Instant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Amount;
import org.roboquant.common.Currency;
import org.roboquant.common.UnsupportedException;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"},
   d2 = {"Lorg/roboquant/brokers/NoExchangeRates;", "Lorg/roboquant/brokers/ExchangeRates;", "()V", "getRate", "", "amount", "Lorg/roboquant/common/Amount;", "to", "Lorg/roboquant/common/Currency;", "time", "Ljava/time/Instant;", "roboquant"}
)
public final class NoExchangeRates implements ExchangeRates {
   public double getRate(@NotNull Amount amount, @NotNull Currency to, @NotNull Instant time) {
      Intrinsics.checkNotNullParameter(amount, "amount");
      Intrinsics.checkNotNullParameter(to, "to");
      Intrinsics.checkNotNullParameter(time, "time");
      if (amount.getCurrency() == to) {
         return (double)1.0F;
      } else {
         throw new UnsupportedException("Cannot convert " + amount + " to " + to);
      }
   }

   @NotNull
   public Amount convert(@NotNull Amount amount, @NotNull Currency to, @NotNull Instant time) {
      return ExchangeRates.DefaultImpls.convert(this, amount, to, time);
   }
}
