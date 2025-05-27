package org.roboquant.feeds;

import java.time.Instant;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.TimeoutKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.channels.ClosedReceiveChannelException;
import kotlinx.coroutines.channels.SendChannel;
import kotlinx.coroutines.channels.SendChannel.DefaultImpls;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Event;
import org.roboquant.common.ExtensionsKt;
import org.roboquant.common.Logging;
import org.roboquant.common.Timeframe;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B#\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u001a\u001a\u00020\u0000H\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001eH\u0086\u0002J\u0018\u0010\u001f\u001a\u00020\u000e2\b\b\u0002\u0010 \u001a\u00020!H\u0086@¢\u0006\u0002\u0010\"J\u0016\u0010#\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020\u000eH\u0086@¢\u0006\u0002\u0010%J\u0016\u0010&\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020\u000eH\u0086@¢\u0006\u0002\u0010%J\u000e\u0010'\u001a\u00020\u001cH\u0086@¢\u0006\u0002\u0010(R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006)"},
   d2 = {"Lorg/roboquant/feeds/EventChannel;", "Ljava/lang/AutoCloseable;", "", "timeframe", "Lorg/roboquant/common/Timeframe;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(Lorg/roboquant/common/Timeframe;ILkotlinx/coroutines/channels/BufferOverflow;)V", "getCapacity", "()I", "channel", "Lkotlinx/coroutines/channels/Channel;", "Lorg/roboquant/common/Event;", "<set-?>", "", "closed", "getClosed", "()Z", "logger", "Lorg/roboquant/common/Logging$Logger;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "getTimeframe", "()Lorg/roboquant/common/Timeframe;", "clone", "close", "", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "receive", "timeOutMillis", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "send", "event", "(Lorg/roboquant/common/Event;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sendNotEmpty", "waitOnClose", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "roboquant"}
)
@SourceDebugExtension({"SMAP\nEventChannel.kt\nKotlin\n*S Kotlin\n*F\n+ 1 EventChannel.kt\norg/roboquant/feeds/EventChannel\n+ 2 Logging.kt\norg/roboquant/common/Logging$Logger\n*L\n1#1,147:1\n45#2,3:148\n*S KotlinDebug\n*F\n+ 1 EventChannel.kt\norg/roboquant/feeds/EventChannel\n*L\n76#1:148,3\n*E\n"})
public final class EventChannel implements AutoCloseable, Cloneable {
   @NotNull
   private final Timeframe timeframe;
   private final int capacity;
   @NotNull
   private final BufferOverflow onBufferOverflow;
   @NotNull
   private final Channel channel;
   @NotNull
   private final Logging.Logger logger;
   @NotNull
   private final Mutex mutex;
   private boolean closed;

   public EventChannel(@NotNull Timeframe timeframe, int capacity, @NotNull BufferOverflow onBufferOverflow) {
      Intrinsics.checkNotNullParameter(timeframe, "timeframe");
      Intrinsics.checkNotNullParameter(onBufferOverflow, "onBufferOverflow");
      super();
      this.timeframe = timeframe;
      this.capacity = capacity;
      this.onBufferOverflow = onBufferOverflow;
      this.channel = ChannelKt.Channel$default(this.capacity, this.onBufferOverflow, (Function1)null, 4, (Object)null);
      this.logger = Logging.INSTANCE.getLogger(Reflection.getOrCreateKotlinClass(EventChannel.class));
      this.mutex = MutexKt.Mutex(true);
   }

   // $FF: synthetic method
   public EventChannel(Timeframe var1, int var2, BufferOverflow var3, int var4, DefaultConstructorMarker var5) {
      if ((var4 & 1) != 0) {
         var1 = Timeframe.Companion.getINFINITE();
      }

      if ((var4 & 2) != 0) {
         var2 = 10;
      }

      if ((var4 & 4) != 0) {
         var3 = BufferOverflow.SUSPEND;
      }

      this(var1, var2, var3);
   }

   @NotNull
   public final Timeframe getTimeframe() {
      return this.timeframe;
   }

   public final int getCapacity() {
      return this.capacity;
   }

   public final boolean getClosed() {
      return this.closed;
   }

   @NotNull
   public final ChannelIterator iterator() {
      return this.channel.iterator();
   }

   @Nullable
   public final Object send(@NotNull Event event, @NotNull Continuation $completion) {
      if (this.timeframe.contains(event.getTime())) {
         Object var10000 = this.channel.send(event, $completion);
         return var10000 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var10000 : Unit.INSTANCE;
      } else {
         if (ExtensionsKt.compareTo(event.getTime(), this.timeframe) > 0) {
            Logging.Logger $this$iv = this.logger;
            Throwable throwable$iv = null;
            int $i$f$debug = 0;
            if ($this$iv.isDebugEnabled()) {
               int var6 = 0;
               $this$iv.debug("send time=" + event.getTime() + " timeframe=" + this.timeframe + ", closing channel", throwable$iv);
            }

            this.close();
         }

         return Unit.INSTANCE;
      }
   }

   @Nullable
   public final Object sendNotEmpty(@NotNull Event event, @NotNull Continuation $completion) {
      if (event.isNotEmpty()) {
         Object var10000 = this.send(event, $completion);
         return var10000 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var10000 : Unit.INSTANCE;
      } else {
         return Unit.INSTANCE;
      }
   }

   @Nullable
   public final Object receive(long timeOutMillis, @NotNull Continuation var3) {
      label56: {
         if (var3 instanceof Continuation $continuation) {
            if (($continuation.label & Integer.MIN_VALUE) != 0) {
               $continuation.label -= Integer.MIN_VALUE;
               break label56;
            }
         }

         $continuation = new ContinuationImpl(var3) {
            Object L$0;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return EventChannel.this.receive(0L, (Continuation)this);
            }
         };
      }

      TimeoutCancellationException var10000;
      label49: {
         Object $result = $continuation.result;
         Object var8 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         Object var12;
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure($result);
               if (timeOutMillis <= 0L) {
                  Channel var14 = this.channel;
                  $continuation.label = 1;
                  var12 = var14.receive($continuation);
                  if (var12 == var8) {
                     return var8;
                  }

                  return var12;
               }

               try {
                  Function2 var16 = new Function2((Continuation)null) {
                     int label;

                     @Nullable
                     public final Object invokeSuspend(@NotNull Object $result) {
                        Object var2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        Object var10000;
                        switch (this.label) {
                           case 0:
                              ResultKt.throwOnFailure($result);
                              Channel var3 = EventChannel.this.channel;
                              Continuation var10001 = (Continuation)this;
                              this.label = 1;
                              var10000 = var3.receive(var10001);
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
                  };
                  $continuation.L$0 = this;
                  $continuation.label = 2;
                  var12 = TimeoutKt.withTimeout(timeOutMillis, var16, $continuation);
               } catch (TimeoutCancellationException var10) {
                  var10000 = var10;
                  boolean var15 = false;
                  break label49;
               }

               if (var12 == var8) {
                  return var8;
               }
               break;
            case 1:
               ResultKt.throwOnFailure($result);
               var12 = $result;
               return var12;
            case 2:
               this = (EventChannel)$continuation.L$0;

               try {
                  ResultKt.throwOnFailure($result);
                  var12 = $result;
                  break;
               } catch (TimeoutCancellationException var11) {
                  var10000 = var11;
                  boolean var10001 = false;
                  break label49;
               }
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         try {
            return var12;
         } catch (TimeoutCancellationException var9) {
            var10000 = var9;
            boolean var17 = false;
         }
      }

      TimeoutCancellationException err = var10000;
      this.logger.debug("timeout occured", (Throwable)err);
      Instant now = Instant.now();
      Intrinsics.checkNotNull(now);
      if (ExtensionsKt.compareTo(now, this.timeframe) > 0) {
         this.close();
         throw new ClosedReceiveChannelException("channel closed");
      } else {
         return Event.Companion.empty(now);
      }
   }

   // $FF: synthetic method
   public static Object receive$default(EventChannel var0, long var1, Continuation var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = -1L;
      }

      return var0.receive(var1, var3);
   }

   public synchronized void close() {
      if (!this.closed) {
         this.closed = true;
         DefaultImpls.close$default((SendChannel)this.channel, (Throwable)null, 1, (Object)null);
         kotlinx.coroutines.sync.Mutex.DefaultImpls.unlock$default(this.mutex, (Object)null, 1, (Object)null);
      }
   }

   @Nullable
   public final Object waitOnClose(@NotNull Continuation $completion) {
      if (this.closed) {
         return Unit.INSTANCE;
      } else {
         Object var10000 = kotlinx.coroutines.sync.Mutex.DefaultImpls.lock$default(this.mutex, (Object)null, $completion, 1, (Object)null);
         return var10000 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? var10000 : Unit.INSTANCE;
      }
   }

   @NotNull
   public EventChannel clone() {
      return new EventChannel(this.timeframe, this.capacity, this.onBufferOverflow);
   }

   public EventChannel() {
      this((Timeframe)null, 0, (BufferOverflow)null, 7, (DefaultConstructorMarker)null);
   }
}
