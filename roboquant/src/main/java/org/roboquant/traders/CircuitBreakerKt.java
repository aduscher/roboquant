package org.roboquant.traders;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.TimeSpan;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"},
   d2 = {"circuitBreaker", "Lorg/roboquant/traders/Trader;", "maxOrders", "", "period", "Lorg/roboquant/common/TimeSpan;", "roboquant"}
)
public final class CircuitBreakerKt {
   @NotNull
   public static final Trader circuitBreaker(@NotNull Trader $this$circuitBreaker, int maxOrders, @NotNull TimeSpan period) {
      Intrinsics.checkNotNullParameter($this$circuitBreaker, "<this>");
      Intrinsics.checkNotNullParameter(period, "period");
      return new CircuitBreaker($this$circuitBreaker, maxOrders, period);
   }
}
