package org.roboquant.feeds;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Timeframe;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\u0016\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH¦@¢\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u000e"},
   d2 = {"Lorg/roboquant/feeds/Feed;", "Ljava/lang/AutoCloseable;", "timeframe", "Lorg/roboquant/common/Timeframe;", "getTimeframe", "()Lorg/roboquant/common/Timeframe;", "close", "", "play", "channel", "Lorg/roboquant/feeds/EventChannel;", "(Lorg/roboquant/feeds/EventChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "playBackground", "Lkotlinx/coroutines/Job;", "roboquant"}
)
public interface Feed extends AutoCloseable {
   @NotNull
   Timeframe getTimeframe();

   @Nullable
   Object play(@NotNull EventChannel var1, @NotNull Continuation var2);

   void close();

   @NotNull
   Job playBackground(@NotNull EventChannel var1);

   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      @NotNull
      public static Timeframe getTimeframe(@NotNull Feed $this) {
         return Timeframe.Companion.getINFINITE();
      }

      public static void close(@NotNull Feed $this) {
      }

      @NotNull
      public static Job playBackground(@NotNull final Feed $this, @NotNull final EventChannel channel) {
         Intrinsics.checkNotNullParameter(channel, "channel");
         CoroutineScope scope = CoroutineScopeKt.CoroutineScope((CoroutineContext)Dispatchers.getDefault());
         return BuildersKt.launch$default(scope, (CoroutineContext)null, (CoroutineStart)null, new Function2((Continuation)null) {
            Object L$0;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object param1) {
               // $FF: Couldn't be decompiled
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
      }
   }
}
