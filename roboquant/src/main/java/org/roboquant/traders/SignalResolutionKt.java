package org.roboquant.traders;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Config;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¨\u0006\u0004"},
   d2 = {"shuffleSignals", "Lorg/roboquant/traders/Trader;", "random", "Lkotlin/random/Random;", "roboquant"}
)
public final class SignalResolutionKt {
   @NotNull
   public static final Trader shuffleSignals(@NotNull Trader $this$shuffleSignals, @NotNull Random random) {
      Intrinsics.checkNotNullParameter($this$shuffleSignals, "<this>");
      Intrinsics.checkNotNullParameter(random, "random");
      return new SignalShuffleTrader($this$shuffleSignals, random);
   }

   // $FF: synthetic method
   public static Trader shuffleSignals$default(Trader var0, Random var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = Config.INSTANCE.getRandom();
      }

      return shuffleSignals(var0, var1);
   }
}
