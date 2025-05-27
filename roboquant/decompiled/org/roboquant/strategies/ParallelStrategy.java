package org.roboquant.strategies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Event;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BJ\b\u0016\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u0003\"\u00020\u0001\u0012-\b\u0002\u0010\u0004\u001a'\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u00010\u0005j\u0004\u0018\u0001`\b¢\u0006\u0002\b\t¢\u0006\u0002\u0010\nBB\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b\u0012-\b\u0002\u0010\u0004\u001a'\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u00010\u0005j\u0004\u0018\u0001`\b¢\u0006\u0002\b\t¢\u0006\u0002\u0010\fJ\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R3\u0010\u0004\u001a'\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0018\u00010\u0005j\u0004\u0018\u0001`\b¢\u0006\u0002\b\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0014"},
   d2 = {"Lorg/roboquant/strategies/ParallelStrategy;", "Lorg/roboquant/strategies/Strategy;", "strategies", "", "signalResolver", "Lkotlin/Function1;", "", "Lorg/roboquant/common/Signal;", "Lorg/roboquant/strategies/SignalResolver;", "Lkotlin/ExtensionFunctionType;", "([Lorg/roboquant/strategies/Strategy;Lkotlin/jvm/functions/Function1;)V", "", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "getStrategies", "()Ljava/util/Collection;", "createSignals", "event", "Lorg/roboquant/common/Event;", "roboquant"}
)
@SourceDebugExtension({"SMAP\nParallelStrategy.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ParallelStrategy.kt\norg/roboquant/strategies/ParallelStrategy\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,70:1\n1#2:71\n*E\n"})
public final class ParallelStrategy implements Strategy {
   @NotNull
   private final Collection strategies;
   @Nullable
   private final Function1 signalResolver;
   @NotNull
   private final CoroutineScope scope;

   public ParallelStrategy(@NotNull Collection strategies, @Nullable Function1 signalResolver) {
      Intrinsics.checkNotNullParameter(strategies, "strategies");
      super();
      this.strategies = strategies;
      this.signalResolver = signalResolver;
      this.scope = CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault().plus((CoroutineContext)JobKt.Job$default((Job)null, 1, (Object)null)));
   }

   // $FF: synthetic method
   public ParallelStrategy(Collection var1, Function1 var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = null;
      }

      this(var1, var2);
   }

   @NotNull
   public final Collection getStrategies() {
      return this.strategies;
   }

   public ParallelStrategy(@NotNull Strategy[] strategies, @Nullable Function1 signalResolver) {
      Intrinsics.checkNotNullParameter(strategies, "strategies");
      this((Collection)ArraysKt.toList(strategies), signalResolver);
   }

   // $FF: synthetic method
   public ParallelStrategy(Strategy[] var1, Function1 var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = null;
      }

      this(var1, var2);
   }

   @NotNull
   public List createSignals(@NotNull final Event event) {
      Intrinsics.checkNotNullParameter(event, "event");
      final List signals = (List)(new ArrayList());
      BuildersKt.runBlocking$default((CoroutineContext)null, new Function2((Continuation)null) {
         Object L$0;
         Object L$1;
         Object L$2;
         int label;

         @Nullable
         public final Object invokeSuspend(@NotNull Object $result) {
            Object var11 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            List strategy;
            Iterator var6;
            switch (this.label) {
               case 0:
                  ResultKt.throwOnFailure($result);
                  List deferredList = (List)(new ArrayList());

                  for(final Strategy strategy : ParallelStrategy.this.getStrategies()) {
                     Deferred deferred = BuildersKt.async$default(ParallelStrategy.this.scope, (CoroutineContext)null, (CoroutineStart)null, new Function2((Continuation)null) {
                        int label;

                        @Nullable
                        public final Object invokeSuspend(@NotNull Object $result) {
                           IntrinsicsKt.getCOROUTINE_SUSPENDED();
                           switch (this.label) {
                              case 0:
                                 ResultKt.throwOnFailure($result);
                                 return strategy.createSignals(event);
                              default:
                                 throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                           }
                        }

                        @NotNull
                        public final Continuation create(@Nullable Object value, @NotNull Continuation $completion) {
                           return (Continuation)(new <anonymous constructor>($completion));
                        }

                        @Nullable
                        public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation p2) {
                           return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
                        }
                     }, 3, (Object)null);
                     deferredList.add(deferred);
                  }

                  Iterable var12 = (Iterable)deferredList;
                  strategy = signals;
                  int $i$f$forEach = 0;
                  var6 = var12.iterator();
                  break;
               case 1:
                  int $i$f$forEach = 0;
                  int var9 = 0;
                  List var10 = (List)this.L$2;
                  var6 = (Iterator)this.L$1;
                  strategy = (List)this.L$0;
                  ResultKt.throwOnFailure($result);
                  var10.addAll((Collection)$result);
                  break;
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            while(var6.hasNext()) {
               Object element$iv = var6.next();
               Deferred it = (Deferred)element$iv;
               int var16 = 0;
               this.L$0 = strategy;
               this.L$1 = var6;
               this.L$2 = strategy;
               this.label = 1;
               Object var10000 = it.await(this);
               if (var10000 == var11) {
                  return var11;
               }

               strategy.addAll((Collection)var10000);
            }

            return Unit.INSTANCE;
         }

         @NotNull
         public final Continuation create(@Nullable Object value, @NotNull Continuation $completion) {
            return (Continuation)(new <anonymous constructor>($completion));
         }

         @Nullable
         public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      }, 1, (Object)null);
      List var10000;
      if (this.signalResolver != null) {
         int var4 = 0;
         var10000 = signals;
      } else {
         var10000 = signals;
      }

      return var10000;
   }
}
