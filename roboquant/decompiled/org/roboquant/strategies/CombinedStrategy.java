package org.roboquant.strategies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Event;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001BJ\b\u0016\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0003\"\u00020\u0001\u0012-\b\u0002\u0010\u0004\u001a'\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u00010\u0005j\u0004\u0018\u0001`\b¢\u0006\u0002\b\t¢\u0006\u0002\u0010\nBB\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b\u0012-\b\u0002\u0010\u0004\u001a'\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u00010\u0005j\u0004\u0018\u0001`\b¢\u0006\u0002\b\t¢\u0006\u0002\u0010\fJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R3\u0010\u0004\u001a'\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u00010\u0005j\u0004\u0018\u0001`\b¢\u0006\u0002\b\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0012"},
   d2 = {"Lorg/roboquant/strategies/CombinedStrategy;", "Lorg/roboquant/strategies/Strategy;", "strategies", "", "signalResolver", "Lkotlin/Function1;", "", "Lorg/roboquant/common/Signal;", "Lorg/roboquant/strategies/SignalResolver;", "Lkotlin/ExtensionFunctionType;", "([Lorg/roboquant/strategies/Strategy;Lkotlin/jvm/functions/Function1;)V", "", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)V", "getStrategies", "()Ljava/util/Collection;", "createSignals", "event", "Lorg/roboquant/common/Event;", "roboquant"}
)
@SourceDebugExtension({"SMAP\nCombinedStrategy.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CombinedStrategy.kt\norg/roboquant/strategies/CombinedStrategy\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,50:1\n1#2:51\n*E\n"})
public class CombinedStrategy implements Strategy {
   @NotNull
   private final Collection strategies;
   @Nullable
   private final Function1 signalResolver;

   public CombinedStrategy(@NotNull Collection strategies, @Nullable Function1 signalResolver) {
      Intrinsics.checkNotNullParameter(strategies, "strategies");
      super();
      this.strategies = strategies;
      this.signalResolver = signalResolver;
   }

   // $FF: synthetic method
   public CombinedStrategy(Collection var1, Function1 var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = null;
      }

      this(var1, var2);
   }

   @NotNull
   public final Collection getStrategies() {
      return this.strategies;
   }

   public CombinedStrategy(@NotNull Strategy[] strategies, @Nullable Function1 signalResolver) {
      Intrinsics.checkNotNullParameter(strategies, "strategies");
      this((Collection)ArraysKt.toList(strategies), signalResolver);
   }

   // $FF: synthetic method
   public CombinedStrategy(Strategy[] var1, Function1 var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = null;
      }

      this(var1, var2);
   }

   @NotNull
   public List createSignals(@NotNull Event event) {
      Intrinsics.checkNotNullParameter(event, "event");
      List signals = (List)(new ArrayList());

      for(Strategy strategy : this.strategies) {
         List s = strategy.createSignals(event);
         signals.addAll((Collection)s);
      }

      List var10000;
      if (this.signalResolver != null) {
         int var7 = 0;
         var10000 = signals;
      } else {
         var10000 = signals;
      }

      return var10000;
   }
}
