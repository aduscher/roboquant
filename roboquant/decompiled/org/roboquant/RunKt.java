package org.roboquant;

import java.time.ZoneId;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.brokers.AccountModel;
import org.roboquant.brokers.Broker;
import org.roboquant.brokers.SimBroker;
import org.roboquant.common.Account;
import org.roboquant.common.Currency;
import org.roboquant.common.Timeframe;
import org.roboquant.common.Wallet;
import org.roboquant.feeds.EventChannel;
import org.roboquant.feeds.Feed;
import org.roboquant.journals.Journal;
import org.roboquant.strategies.Strategy;
import org.roboquant.traders.FlexTrader;
import org.roboquant.traders.Trader;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a^\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u001ah\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0086@¢\u0006\u0002\u0010\u0015¨\u0006\u0016"},
   d2 = {"run", "Lorg/roboquant/common/Account;", "feed", "Lorg/roboquant/feeds/Feed;", "strategy", "Lorg/roboquant/strategies/Strategy;", "journal", "Lorg/roboquant/journals/Journal;", "trader", "Lorg/roboquant/traders/Trader;", "timeframe", "Lorg/roboquant/common/Timeframe;", "broker", "Lorg/roboquant/brokers/Broker;", "channel", "Lorg/roboquant/feeds/EventChannel;", "timeOutMillis", "", "showProgressBar", "", "runAsync", "(Lorg/roboquant/feeds/Feed;Lorg/roboquant/strategies/Strategy;Lorg/roboquant/journals/Journal;Lorg/roboquant/traders/Trader;Lorg/roboquant/common/Timeframe;Lorg/roboquant/brokers/Broker;Lorg/roboquant/feeds/EventChannel;JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "roboquant"}
)
public final class RunKt {
   @NotNull
   public static final Account run(@NotNull final Feed feed, @NotNull final Strategy strategy, @Nullable final Journal journal, @NotNull final Trader trader, @NotNull final Timeframe timeframe, @NotNull final Broker broker, @NotNull final EventChannel channel, final long timeOutMillis, final boolean showProgressBar) {
      Intrinsics.checkNotNullParameter(feed, "feed");
      Intrinsics.checkNotNullParameter(strategy, "strategy");
      Intrinsics.checkNotNullParameter(trader, "trader");
      Intrinsics.checkNotNullParameter(timeframe, "timeframe");
      Intrinsics.checkNotNullParameter(broker, "broker");
      Intrinsics.checkNotNullParameter(channel, "channel");
      return (Account)BuildersKt.runBlocking$default((CoroutineContext)null, new Function2((Continuation)null) {
         int label;

         @Nullable
         public final Object invokeSuspend(@NotNull Object $result) {
            Object var2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            Object var10000;
            switch (this.label) {
               case 0:
                  ResultKt.throwOnFailure($result);
                  Feed var3 = feed;
                  Strategy var10001 = strategy;
                  Journal var10002 = journal;
                  Trader var10003 = trader;
                  Timeframe var10004 = timeframe;
                  Broker var10005 = broker;
                  EventChannel var10006 = channel;
                  long var10007 = timeOutMillis;
                  boolean var10008 = showProgressBar;
                  Continuation var10009 = (Continuation)this;
                  this.label = 1;
                  var10000 = RunKt.runAsync(var3, var10001, var10002, var10003, var10004, var10005, var10006, var10007, var10008, var10009);
                  if (var10000 == var2) {
                     return var2;
                  }
                  break;
               case 1:
                  ResultKt.throwOnFailure($result);
                  var10000 = $result;
                  break;
               default:
                  throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            return var10000;
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
   }

   // $FF: synthetic method
   public static Account run$default(Feed var0, Strategy var1, Journal var2, Trader var3, Timeframe var4, Broker var5, EventChannel var6, long var7, boolean var9, int var10, Object var11) {
      if ((var10 & 4) != 0) {
         var2 = null;
      }

      if ((var10 & 8) != 0) {
         var3 = new FlexTrader((Function1)null, 1, (DefaultConstructorMarker)null);
      }

      if ((var10 & 16) != 0) {
         var4 = Timeframe.Companion.getINFINITE();
      }

      if ((var10 & 32) != 0) {
         var5 = new SimBroker((Wallet)null, (Currency)null, (AccountModel)null, (Map)null, (ZoneId)null, 31, (DefaultConstructorMarker)null);
      }

      if ((var10 & 64) != 0) {
         var6 = new EventChannel(var4, 10, (BufferOverflow)null, 4, (DefaultConstructorMarker)null);
      }

      if ((var10 & 128) != 0) {
         var7 = -1L;
      }

      if ((var10 & 256) != 0) {
         var9 = false;
      }

      return run(var0, var1, var2, var3, var4, var5, var6, var7, var9);
   }

   @Nullable
   public static final Object runAsync(@NotNull Feed param0, @Nullable Strategy param1, @Nullable Journal param2, @NotNull Trader param3, @NotNull Timeframe param4, @NotNull Broker param5, @NotNull EventChannel param6, long param7, boolean param9, @NotNull Continuation param10) {
      // $FF: Couldn't be decompiled
   }

   // $FF: synthetic method
   public static Object runAsync$default(Feed var0, Strategy var1, Journal var2, Trader var3, Timeframe var4, Broker var5, EventChannel var6, long var7, boolean var9, Continuation var10, int var11, Object var12) {
      if ((var11 & 4) != 0) {
         var2 = null;
      }

      if ((var11 & 8) != 0) {
         var3 = new FlexTrader((Function1)null, 1, (DefaultConstructorMarker)null);
      }

      if ((var11 & 16) != 0) {
         var4 = Timeframe.Companion.getINFINITE();
      }

      if ((var11 & 32) != 0) {
         var5 = new SimBroker((Wallet)null, (Currency)null, (AccountModel)null, (Map)null, (ZoneId)null, 31, (DefaultConstructorMarker)null);
      }

      if ((var11 & 64) != 0) {
         var6 = new EventChannel(var4, 10, (BufferOverflow)null, 4, (DefaultConstructorMarker)null);
      }

      if ((var11 & 128) != 0) {
         var7 = -1L;
      }

      if ((var11 & 256) != 0) {
         var9 = false;
      }

      return runAsync(var0, var1, var2, var3, var4, var5, var6, var7, var9, var10);
   }
}
