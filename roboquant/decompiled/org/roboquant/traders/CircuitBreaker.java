package org.roboquant.traders;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Account;
import org.roboquant.common.Event;
import org.roboquant.common.Logging;
import org.roboquant.common.TimeSpan;
import org.roboquant.common.TimeSpanKt;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J,\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u00112\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u000bH\u0002R \u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00040\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"},
   d2 = {"Lorg/roboquant/traders/CircuitBreaker;", "Lorg/roboquant/traders/Trader;", "trader", "maxOrders", "", "period", "Lorg/roboquant/common/TimeSpan;", "(Lorg/roboquant/traders/Trader;ILorg/roboquant/common/TimeSpan;)V", "history", "Ljava/util/LinkedList;", "Lkotlin/Pair;", "Ljava/time/Instant;", "logger", "Lorg/roboquant/common/Logging$Logger;", "getTrader", "()Lorg/roboquant/traders/Trader;", "createOrders", "", "Lorg/roboquant/common/Order;", "signals", "Lorg/roboquant/common/Signal;", "account", "Lorg/roboquant/common/Account;", "event", "Lorg/roboquant/common/Event;", "exceeds", "", "newOrders", "time", "roboquant"}
)
@SourceDebugExtension({"SMAP\nCircuitBreaker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CircuitBreaker.kt\norg/roboquant/traders/CircuitBreaker\n+ 2 Logging.kt\norg/roboquant/common/Logging$Logger\n*L\n1#1,79:1\n52#2,3:80\n*S KotlinDebug\n*F\n+ 1 CircuitBreaker.kt\norg/roboquant/traders/CircuitBreaker\n*L\n56#1:80,3\n*E\n"})
public final class CircuitBreaker implements Trader {
   @NotNull
   private final Trader trader;
   private final int maxOrders;
   @NotNull
   private final TimeSpan period;
   @NotNull
   private final LinkedList history;
   @NotNull
   private final Logging.Logger logger;

   public CircuitBreaker(@NotNull Trader trader, int maxOrders, @NotNull TimeSpan period) {
      Intrinsics.checkNotNullParameter(trader, "trader");
      Intrinsics.checkNotNullParameter(period, "period");
      super();
      this.trader = trader;
      this.maxOrders = maxOrders;
      this.period = period;
      this.history = new LinkedList();
      this.logger = Logging.INSTANCE.getLogger(Reflection.getOrCreateKotlinClass(this.getClass()));
   }

   @NotNull
   public final Trader getTrader() {
      return this.trader;
   }

   private final boolean exceeds(int newOrders, Instant time) {
      if (newOrders > this.maxOrders) {
         return false;
      } else {
         Instant lookbackTime = TimeSpanKt.minus(time, this.period);
         int orders = newOrders;

         for(Pair entry : this.history) {
            if (((Instant)entry.getFirst()).compareTo(lookbackTime) < 0) {
               return false;
            }

            orders += ((Number)entry.getSecond()).intValue();
            if (orders > this.maxOrders) {
               return true;
            }
         }

         return false;
      }
   }

   @NotNull
   public List createOrders(@NotNull List signals, @NotNull Account account, @NotNull Event event) {
      Intrinsics.checkNotNullParameter(signals, "signals");
      Intrinsics.checkNotNullParameter(account, "account");
      Intrinsics.checkNotNullParameter(event, "event");
      List orders = this.trader.createOrders(signals, account, event);
      if (orders.isEmpty()) {
         return CollectionsKt.emptyList();
      } else {
         List var10000;
         if (this.exceeds(orders.size(), event.getTime())) {
            Logging.Logger $this$iv = this.logger;
            Throwable throwable$iv = null;
            int $i$f$info = 0;
            if ($this$iv.isInfoEnabled()) {
               int var8 = 0;
               $this$iv.info("trottling orders, not sending " + orders.size() + " orders", throwable$iv);
            }

            var10000 = CollectionsKt.emptyList();
         } else {
            this.history.addFirst(new Pair(event.getTime(), orders.size()));
            var10000 = orders;
         }

         return var10000;
      }
   }
}
