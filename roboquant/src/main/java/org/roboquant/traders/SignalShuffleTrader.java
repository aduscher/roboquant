package org.roboquant.traders;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Account;
import org.roboquant.common.Event;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J,\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"},
   d2 = {"Lorg/roboquant/traders/SignalShuffleTrader;", "Lorg/roboquant/traders/Trader;", "trader", "random", "Lkotlin/random/Random;", "(Lorg/roboquant/traders/Trader;Lkotlin/random/Random;)V", "createOrders", "", "Lorg/roboquant/common/Order;", "signals", "Lorg/roboquant/common/Signal;", "account", "Lorg/roboquant/common/Account;", "event", "Lorg/roboquant/common/Event;", "roboquant"}
)
final class SignalShuffleTrader implements Trader {
   @NotNull
   private final Trader trader;
   @NotNull
   private final Random random;

   public SignalShuffleTrader(@NotNull Trader trader, @NotNull Random random) {
      Intrinsics.checkNotNullParameter(trader, "trader");
      Intrinsics.checkNotNullParameter(random, "random");
      super();
      this.trader = trader;
      this.random = random;
   }

   @NotNull
   public List createOrders(@NotNull List signals, @NotNull Account account, @NotNull Event event) {
      Intrinsics.checkNotNullParameter(signals, "signals");
      Intrinsics.checkNotNullParameter(account, "account");
      Intrinsics.checkNotNullParameter(event, "event");
      return this.trader.createOrders(CollectionsKt.shuffled((Iterable)signals, this.random), account, event);
   }
}
