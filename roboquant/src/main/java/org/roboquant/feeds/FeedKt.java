package org.roboquant.feeds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.Job.DefaultImpls;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ClosedReceiveChannelException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Event;
import org.roboquant.common.Item;
import org.roboquant.common.PriceItem;
import org.roboquant.common.Timeframe;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000X\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0013\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\u001aB\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u001a\b\u0004\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\bH\u0086\bø\u0001\u0000\u001a0\u0010\n\u001a\u00020\u0001*\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0014\b\u0004\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\u000bH\u0086\bø\u0001\u0000\u001aN\u0010\r\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u0002H\u00020\u000f0\u000e\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0014\b\u0006\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00100\u000bH\u0086\bø\u0001\u0000\u001a\u001a\u0010\u0011\u001a\u00020\u0012*\b\u0012\u0004\u0012\u00020\u00140\u00132\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u001a\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u000e*\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u001a:\u0010\u0018\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00140\u000f0\u000e*\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u0016\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001c"},
   d2 = {"apply", "", "T", "Lorg/roboquant/common/Item;", "Lorg/roboquant/feeds/Feed;", "timeframe", "Lorg/roboquant/common/Timeframe;", "block", "Lkotlin/Function2;", "Ljava/time/Instant;", "applyEvents", "Lkotlin/Function1;", "Lorg/roboquant/common/Event;", "filter", "", "Lkotlin/Pair;", "", "toDoubleArray", "", "", "Lorg/roboquant/common/PriceItem;", "type", "", "toList", "validate", "maxDiff", "", "priceType", "roboquant"}
)
@SourceDebugExtension({"SMAP\nFeed.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Feed.kt\norg/roboquant/feeds/FeedKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,249:1\n1549#2:250\n1620#2,3:251\n*S KotlinDebug\n*F\n+ 1 Feed.kt\norg/roboquant/feeds/FeedKt\n*L\n247#1:250\n247#1:251,3\n*E\n"})
public final class FeedKt {
   // $FF: synthetic method
   public static final List filter(Feed $this$filter, Timeframe timeframe, Function1 filter) {
      Intrinsics.checkNotNullParameter($this$filter, "<this>");
      Intrinsics.checkNotNullParameter(timeframe, "timeframe");
      Intrinsics.checkNotNullParameter(filter, "filter");
      int $i$f$filter = 0;
      Intrinsics.needClassReification();
      return (List)BuildersKt.runBlocking$default((CoroutineContext)null, new Function2((Continuation)null) {
         Object L$0;
         Object L$1;
         Object L$2;
         int label;

         @Nullable
         public final Object invokeSuspend(@NotNull Object param1) {
            // $FF: Couldn't be decompiled
         }

         @Nullable
         public final Object invokeSuspend$$forInline(@NotNull Object $result) {
            EventChannel channel = new EventChannel(timeframe, 0, (BufferOverflow)null, 6, (DefaultConstructorMarker)null);
            List result = (List)(new ArrayList());
            Job job = $this$filter_u24default.playBackground(channel);

            try {
               while(true) {
                  Continuation var10002 = (Continuation)this;
                  InlineMarker.mark(0);
                  Object var10000 = EventChannel.receive$default(channel, 0L, var10002, 1, (Object)null);
                  InlineMarker.mark(1);
                  Event o = (Event)var10000;
                  Iterable $this$filterIsInstance$iv = (Iterable)o.getItems();
                  int $i$f$filterIsInstance = 0;
                  Collection destination$iv$iv = (Collection)(new ArrayList());
                  int $i$f$filterIsInstanceTo = 0;

                  for(Object element$iv$iv : $this$filterIsInstance$iv) {
                     Intrinsics.reifiedOperationMarker(3, "T");
                     if (element$iv$iv instanceof Object) {
                        destination$iv$iv.add(element$iv$iv);
                     }
                  }

                  $this$filterIsInstance$iv = (Iterable)((List)destination$iv$iv);
                  Function1 predicate$iv = filter;
                  boolean $this$filterIsInstanceTo$iv$iv = false;
                  Collection destination$iv$iv = (Collection)(new ArrayList());
                  boolean var29 = false;

                  for(Object element$iv$iv : $this$filterIsInstance$iv) {
                     if ((Boolean)predicate$iv.invoke(element$iv$iv)) {
                        destination$iv$iv.add(element$iv$iv);
                     }
                  }

                  $this$filterIsInstance$iv = (Iterable)((List)destination$iv$iv);
                  boolean $i$f$map = false;
                  destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$filterIsInstance$iv, 10)));
                  boolean $i$f$mapTo = false;

                  for(Object item$iv$iv : $this$filterIsInstance$iv) {
                     Item it = (Item)item$iv$iv;
                     int var15 = 0;
                     Pair var17 = new Pair(o.getTime(), it);
                     destination$iv$iv.add(var17);
                  }

                  List newResults = (List)destination$iv$iv;
                  result.addAll((Collection)newResults);
               }
            } catch (ClosedReceiveChannelException var20) {
            } finally {
               channel.close();
               if (job.isActive()) {
                  DefaultImpls.cancel$default(job, (CancellationException)null, 1, (Object)null);
               }

            }

            return result;
         }

         @NotNull
         public final Continuation create(@Nullable Object value, @NotNull Continuation $completion) {
            Intrinsics.needClassReification();
            return (Continuation)(new <anonymous constructor>($completion));
         }

         @Nullable
         public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      }, 1, (Object)null);
   }

   // $FF: synthetic method
   public static List filter$default(final Feed $this$filter_u24default, final Timeframe timeframe, final Function1 filter, int $i$f$filter, Object var4) {
      if (($i$f$filter & 1) != 0) {
         timeframe = Timeframe.Companion.getINFINITE();
      }

      if (($i$f$filter & 2) != 0) {
         Intrinsics.needClassReification();
         filter = null.INSTANCE;
      }

      Intrinsics.checkNotNullParameter($this$filter_u24default, "<this>");
      Intrinsics.checkNotNullParameter(timeframe, "timeframe");
      Intrinsics.checkNotNullParameter(filter, "filter");
      $i$f$filter = 0;
      Intrinsics.needClassReification();
      return (List)BuildersKt.runBlocking$default((CoroutineContext)null, new Function2((Continuation)null) {
         Object L$0;
         Object L$1;
         Object L$2;
         int label;

         @Nullable
         public final Object invokeSuspend(@NotNull Object param1) {
            // $FF: Couldn't be decompiled
         }

         @Nullable
         public final Object invokeSuspend$$forInline(@NotNull Object $result) {
            EventChannel channel = new EventChannel(timeframe, 0, (BufferOverflow)null, 6, (DefaultConstructorMarker)null);
            List result = (List)(new ArrayList());
            Job job = $this$filter_u24default.playBackground(channel);

            try {
               while(true) {
                  Continuation var10002 = (Continuation)this;
                  InlineMarker.mark(0);
                  Object var10000 = EventChannel.receive$default(channel, 0L, var10002, 1, (Object)null);
                  InlineMarker.mark(1);
                  Event o = (Event)var10000;
                  Iterable $this$filterIsInstance$iv = (Iterable)o.getItems();
                  int $i$f$filterIsInstance = 0;
                  Collection destination$iv$iv = (Collection)(new ArrayList());
                  int $i$f$filterIsInstanceTo = 0;

                  for(Object element$iv$iv : $this$filterIsInstance$iv) {
                     Intrinsics.reifiedOperationMarker(3, "T");
                     if (element$iv$iv instanceof Object) {
                        destination$iv$iv.add(element$iv$iv);
                     }
                  }

                  $this$filterIsInstance$iv = (Iterable)((List)destination$iv$iv);
                  Function1 predicate$iv = filter;
                  boolean $this$filterIsInstanceTo$iv$iv = false;
                  Collection destination$iv$iv = (Collection)(new ArrayList());
                  boolean var29 = false;

                  for(Object element$iv$iv : $this$filterIsInstance$iv) {
                     if ((Boolean)predicate$iv.invoke(element$iv$iv)) {
                        destination$iv$iv.add(element$iv$iv);
                     }
                  }

                  $this$filterIsInstance$iv = (Iterable)((List)destination$iv$iv);
                  boolean $i$f$map = false;
                  destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$filterIsInstance$iv, 10)));
                  boolean $i$f$mapTo = false;

                  for(Object item$iv$iv : $this$filterIsInstance$iv) {
                     Item it = (Item)item$iv$iv;
                     int var15 = 0;
                     Pair var17 = new Pair(o.getTime(), it);
                     destination$iv$iv.add(var17);
                  }

                  List newResults = (List)destination$iv$iv;
                  result.addAll((Collection)newResults);
               }
            } catch (ClosedReceiveChannelException var20) {
            } finally {
               channel.close();
               if (job.isActive()) {
                  DefaultImpls.cancel$default(job, (CancellationException)null, 1, (Object)null);
               }

            }

            return result;
         }

         @NotNull
         public final Continuation create(@Nullable Object value, @NotNull Continuation $completion) {
            Intrinsics.needClassReification();
            return (Continuation)(new <anonymous constructor>($completion));
         }

         @Nullable
         public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation p2) {
            return ((<undefinedtype>)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
         }
      }, 1, (Object)null);
   }

   // $FF: synthetic method
   public static final void apply(Feed $this$apply, Timeframe timeframe, Function2 block) {
      Intrinsics.checkNotNullParameter($this$apply, "<this>");
      Intrinsics.checkNotNullParameter(timeframe, "timeframe");
      Intrinsics.checkNotNullParameter(block, "block");
      int $i$f$apply = 0;
      Intrinsics.needClassReification();
      BuildersKt.runBlocking$default((CoroutineContext)null, new Function2((Continuation)null) {
         Object L$0;
         Object L$1;
         int label;

         @Nullable
         public final Object invokeSuspend(@NotNull Object param1) {
            // $FF: Couldn't be decompiled
         }

         @Nullable
         public final Object invokeSuspend$$forInline(@NotNull Object $result) {
            EventChannel channel = new EventChannel(timeframe, 0, (BufferOverflow)null, 6, (DefaultConstructorMarker)null);
            Job job = $this$apply_u24default.playBackground(channel);

            try {
               while(true) {
                  Continuation var10002 = (Continuation)this;
                  InlineMarker.mark(0);
                  Object var10000 = EventChannel.receive$default(channel, 0L, var10002, 1, (Object)null);
                  InlineMarker.mark(1);
                  Event o = (Event)var10000;
                  Iterable $this$filterIsInstance$iv = (Iterable)o.getItems();
                  int $i$f$filterIsInstance = 0;
                  Collection destination$iv$iv = (Collection)(new ArrayList());
                  int $i$f$filterIsInstanceTo = 0;

                  for(Object element$iv$iv : $this$filterIsInstance$iv) {
                     Intrinsics.reifiedOperationMarker(3, "T");
                     if (element$iv$iv instanceof Object) {
                        destination$iv$iv.add(element$iv$iv);
                     }
                  }

                  $this$filterIsInstance$iv = (Iterable)((List)destination$iv$iv);
                  Function2 var17 = block;
                  boolean $this$filterIsInstanceTo$iv$iv = false;

                  for(Object element$iv : $this$filterIsInstance$iv) {
                     Item it = (Item)element$iv;
                     int var21 = 0;
                     var17.invoke(it, o.getTime());
                     Unit var22 = Unit.INSTANCE;
                  }
               }
            } catch (ClosedReceiveChannelException var14) {
            } finally {
               if (job.isActive()) {
                  DefaultImpls.cancel$default(job, (CancellationException)null, 1, (Object)null);
               }

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
   }

   // $FF: synthetic method
   public static void apply$default(final Feed $this$apply_u24default, final Timeframe timeframe, final Function2 block, int $i$f$apply, Object var4) {
      if (($i$f$apply & 1) != 0) {
         timeframe = Timeframe.Companion.getINFINITE();
      }

      Intrinsics.checkNotNullParameter($this$apply_u24default, "<this>");
      Intrinsics.checkNotNullParameter(timeframe, "timeframe");
      Intrinsics.checkNotNullParameter(block, "block");
      $i$f$apply = 0;
      Intrinsics.needClassReification();
      BuildersKt.runBlocking$default((CoroutineContext)null, new Function2((Continuation)null) {
         Object L$0;
         Object L$1;
         int label;

         @Nullable
         public final Object invokeSuspend(@NotNull Object param1) {
            // $FF: Couldn't be decompiled
         }

         @Nullable
         public final Object invokeSuspend$$forInline(@NotNull Object $result) {
            EventChannel channel = new EventChannel(timeframe, 0, (BufferOverflow)null, 6, (DefaultConstructorMarker)null);
            Job job = $this$apply_u24default.playBackground(channel);

            try {
               while(true) {
                  Continuation var10002 = (Continuation)this;
                  InlineMarker.mark(0);
                  Object var10000 = EventChannel.receive$default(channel, 0L, var10002, 1, (Object)null);
                  InlineMarker.mark(1);
                  Event o = (Event)var10000;
                  Iterable $this$filterIsInstance$iv = (Iterable)o.getItems();
                  int $i$f$filterIsInstance = 0;
                  Collection destination$iv$iv = (Collection)(new ArrayList());
                  int $i$f$filterIsInstanceTo = 0;

                  for(Object element$iv$iv : $this$filterIsInstance$iv) {
                     Intrinsics.reifiedOperationMarker(3, "T");
                     if (element$iv$iv instanceof Object) {
                        destination$iv$iv.add(element$iv$iv);
                     }
                  }

                  $this$filterIsInstance$iv = (Iterable)((List)destination$iv$iv);
                  Function2 var17 = block;
                  boolean $this$filterIsInstanceTo$iv$iv = false;

                  for(Object element$iv : $this$filterIsInstance$iv) {
                     Item it = (Item)element$iv;
                     int var21 = 0;
                     var17.invoke(it, o.getTime());
                     Unit var22 = Unit.INSTANCE;
                  }
               }
            } catch (ClosedReceiveChannelException var14) {
            } finally {
               if (job.isActive()) {
                  DefaultImpls.cancel$default(job, (CancellationException)null, 1, (Object)null);
               }

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
   }

   public static final void applyEvents(@NotNull Feed $this$applyEvents, @NotNull Timeframe timeframe, @NotNull Function1 block) {
      Intrinsics.checkNotNullParameter($this$applyEvents, "<this>");
      Intrinsics.checkNotNullParameter(timeframe, "timeframe");
      Intrinsics.checkNotNullParameter(block, "block");
      int $i$f$applyEvents = 0;
      BuildersKt.runBlocking$default((CoroutineContext)null, new Function2((Continuation)null) {
         Object L$0;
         Object L$1;
         int label;

         @Nullable
         public final Object invokeSuspend(@NotNull Object param1) {
            // $FF: Couldn't be decompiled
         }

         @Nullable
         public final Object invokeSuspend$$forInline(@NotNull Object $result) {
            EventChannel channel = new EventChannel(timeframe, 0, (BufferOverflow)null, 6, (DefaultConstructorMarker)null);
            Job job = $this$applyEvents_u24default.playBackground(channel);

            try {
               while(true) {
                  Continuation var10002 = (Continuation)this;
                  InlineMarker.mark(0);
                  Object var10000 = EventChannel.receive$default(channel, 0L, var10002, 1, (Object)null);
                  InlineMarker.mark(1);
                  Event o = (Event)var10000;
                  block.invoke(o);
               }
            } catch (ClosedReceiveChannelException var7) {
            } finally {
               if (job.isActive()) {
                  DefaultImpls.cancel$default(job, (CancellationException)null, 1, (Object)null);
               }

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
   }

   // $FF: synthetic method
   public static void applyEvents$default(final Feed $this$applyEvents_u24default, final Timeframe timeframe, final Function1 block, int $i$f$applyEvents, Object var4) {
      if (($i$f$applyEvents & 1) != 0) {
         timeframe = Timeframe.Companion.getINFINITE();
      }

      Intrinsics.checkNotNullParameter($this$applyEvents_u24default, "<this>");
      Intrinsics.checkNotNullParameter(timeframe, "timeframe");
      Intrinsics.checkNotNullParameter(block, "block");
      $i$f$applyEvents = 0;
      BuildersKt.runBlocking$default((CoroutineContext)null, new Function2((Continuation)null) {
         Object L$0;
         Object L$1;
         int label;

         @Nullable
         public final Object invokeSuspend(@NotNull Object param1) {
            // $FF: Couldn't be decompiled
         }

         @Nullable
         public final Object invokeSuspend$$forInline(@NotNull Object $result) {
            EventChannel channel = new EventChannel(timeframe, 0, (BufferOverflow)null, 6, (DefaultConstructorMarker)null);
            Job job = $this$applyEvents_u24default.playBackground(channel);

            try {
               while(true) {
                  Continuation var10002 = (Continuation)this;
                  InlineMarker.mark(0);
                  Object var10000 = EventChannel.receive$default(channel, 0L, var10002, 1, (Object)null);
                  InlineMarker.mark(1);
                  Event o = (Event)var10000;
                  block.invoke(o);
               }
            } catch (ClosedReceiveChannelException var7) {
            } finally {
               if (job.isActive()) {
                  DefaultImpls.cancel$default(job, (CancellationException)null, 1, (Object)null);
               }

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
   }

   @NotNull
   public static final List toList(@NotNull final Feed $this$toList, @NotNull final Timeframe timeframe) {
      Intrinsics.checkNotNullParameter($this$toList, "<this>");
      Intrinsics.checkNotNullParameter(timeframe, "timeframe");
      return (List)BuildersKt.runBlocking$default((CoroutineContext)null, new Function2((Continuation)null) {
         Object L$0;
         Object L$1;
         Object L$2;
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
      }, 1, (Object)null);
   }

   // $FF: synthetic method
   public static List toList$default(Feed var0, Timeframe var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = Timeframe.Companion.getINFINITE();
      }

      return toList(var0, var1);
   }

   @NotNull
   public static final List validate(@NotNull final Feed $this$validate, @NotNull final Timeframe timeframe, final double maxDiff, @NotNull final String priceType) {
      Intrinsics.checkNotNullParameter($this$validate, "<this>");
      Intrinsics.checkNotNullParameter(timeframe, "timeframe");
      Intrinsics.checkNotNullParameter(priceType, "priceType");
      return (List)BuildersKt.runBlocking$default((CoroutineContext)null, new Function2((Continuation)null) {
         Object L$0;
         Object L$1;
         Object L$2;
         Object L$3;
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
      }, 1, (Object)null);
   }

   // $FF: synthetic method
   public static List validate$default(Feed var0, Timeframe var1, double var2, String var4, int var5, Object var6) {
      if ((var5 & 1) != 0) {
         var1 = Timeframe.Companion.getINFINITE();
      }

      if ((var5 & 2) != 0) {
         var2 = (double)0.5F;
      }

      if ((var5 & 4) != 0) {
         var4 = "DEFAULT";
      }

      return validate(var0, var1, var2, var4);
   }

   @NotNull
   public static final double[] toDoubleArray(@NotNull Collection $this$toDoubleArray, @NotNull String type) {
      Intrinsics.checkNotNullParameter($this$toDoubleArray, "<this>");
      Intrinsics.checkNotNullParameter(type, "type");
      Iterable $this$map$iv = (Iterable)$this$toDoubleArray;
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      int $i$f$mapTo = 0;

      for(Object item$iv$iv : $this$map$iv) {
         PriceItem it = (PriceItem)item$iv$iv;
         int var10 = 0;
         destination$iv$iv.add(it.getPrice(type));
      }

      return CollectionsKt.toDoubleArray((Collection)((List)destination$iv$iv));
   }

   // $FF: synthetic method
   public static double[] toDoubleArray$default(Collection var0, String var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = "DEFAULT";
      }

      return toDoubleArray(var0, var1);
   }
}
