package org.roboquant.brokers;

import java.time.Instant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Amount;
import org.roboquant.common.Currency;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J \u0010\t\u001a\u00020\n2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&¨\u0006\u000b"},
   d2 = {"Lorg/roboquant/brokers/ExchangeRates;", "", "convert", "Lorg/roboquant/common/Amount;", "amount", "to", "Lorg/roboquant/common/Currency;", "time", "Ljava/time/Instant;", "getRate", "", "roboquant"}
)
public interface ExchangeRates {
   double getRate(@NotNull Amount var1, @NotNull Currency var2, @NotNull Instant var3);

   @NotNull
   Amount convert(@NotNull Amount var1, @NotNull Currency var2, @NotNull Instant var3);

   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      @NotNull
      public static Amount convert(@NotNull ExchangeRates $this, @NotNull Amount amount, @NotNull Currency to, @NotNull Instant time) {
         Intrinsics.checkNotNullParameter(amount, "amount");
         Intrinsics.checkNotNullParameter(to, "to");
         Intrinsics.checkNotNullParameter(time, "time");
         if (Intrinsics.areEqual(amount.getCurrency(), to)) {
            return amount;
         } else {
            double rate = $this.getRate(amount, to, time);
            return new Amount(to, amount.getValue() * rate);
         }
      }
   }
}
