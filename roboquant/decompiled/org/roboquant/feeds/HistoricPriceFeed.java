package org.roboquant.feeds;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Event;
import org.roboquant.common.PriceItem;
import org.roboquant.common.TimeSpan;
import org.roboquant.common.Timeframe;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\fH\u0004J\u001e\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\n2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u0012H\u0004J\b\u0010\u001c\u001a\u00020\u0017H\u0016J\u0006\u0010\u001d\u001a\u00020\u001eJ\u0006\u0010\u001f\u001a\u00020\u001eJ\u000e\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u0000J\u0016\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020$H\u0096@¢\u0006\u0002\u0010%J\b\u0010&\u001a\u00020'H\u0016R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R \u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\f\u0012\u0004\u0012\u00020\n0\u0012j\u0002`\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006("},
   d2 = {"Lorg/roboquant/feeds/HistoricPriceFeed;", "Lorg/roboquant/feeds/HistoricFeed;", "()V", "assets", "", "Lorg/roboquant/common/Asset;", "getAssets", "()Ljava/util/Set;", "events", "Ljava/util/SortedMap;", "Ljava/time/Instant;", "", "Lorg/roboquant/common/PriceItem;", "timeframe", "Lorg/roboquant/common/Timeframe;", "getTimeframe", "()Lorg/roboquant/common/Timeframe;", "timeline", "", "Lorg/roboquant/common/Timeline;", "getTimeline", "()Ljava/util/List;", "add", "", "time", "action", "addAll", "actions", "close", "first", "Lorg/roboquant/common/Event;", "last", "merge", "feed", "play", "channel", "Lorg/roboquant/feeds/EventChannel;", "(Lorg/roboquant/feeds/EventChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toString", "", "roboquant"}
)
@SourceDebugExtension({"SMAP\nHistoricPriceFeed.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HistoricPriceFeed.kt\norg/roboquant/feeds/HistoricPriceFeed\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,106:1\n1549#2:107\n1620#2,3:108\n215#3,2:111\n372#4,7:113\n372#4,7:120\n*S KotlinDebug\n*F\n+ 1 HistoricPriceFeed.kt\norg/roboquant/feeds/HistoricPriceFeed\n*L\n41#1:107\n41#1:108,3\n67#1:111,2\n78#1:113,7\n87#1:120,7\n*E\n"})
public class HistoricPriceFeed implements HistoricFeed {
   @NotNull
   private final SortedMap events = MapsKt.sortedMapOf(new Pair[0]);

   @NotNull
   public List getTimeline() {
      Set var10000 = this.events.keySet();
      Intrinsics.checkNotNullExpressionValue(var10000, "<get-keys>(...)");
      return CollectionsKt.toList((Iterable)var10000);
   }

   @NotNull
   public Timeframe getTimeframe() {
      Timeframe var10000;
      if (this.events.isEmpty()) {
         var10000 = Timeframe.Companion.getINFINITE();
      } else {
         Object var10002 = this.events.firstKey();
         Intrinsics.checkNotNullExpressionValue(var10002, "firstKey(...)");
         Instant var1 = (Instant)var10002;
         Object var10003 = this.events.lastKey();
         Intrinsics.checkNotNullExpressionValue(var10003, "lastKey(...)");
         var10000 = new Timeframe(var1, (Instant)var10003, true);
      }

      return var10000;
   }

   @NotNull
   public Set getAssets() {
      Collection var10000 = this.events.values();
      Intrinsics.checkNotNullExpressionValue(var10000, "<get-values>(...)");
      Iterable $this$map$iv = (Iterable)var10000;
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      int $i$f$mapTo = 0;

      for(Object item$iv$iv : $this$map$iv) {
         List it = (List)item$iv$iv;
         int var9 = 0;
         Intrinsics.checkNotNull(it);
         Iterable $this$map$iv = (Iterable)it;
         int $i$f$map = 0;
         Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
         int $i$f$mapTo = 0;

         for(Object item$iv$iv : $this$map$iv) {
            PriceItem a = (PriceItem)item$iv$iv;
            int var19 = 0;
            destination$iv$iv.add(a.getAsset());
         }

         destination$iv$iv.add((List)destination$iv$iv);
      }

      return CollectionsKt.toSet((Iterable)CollectionsKt.flatten((Iterable)((List)destination$iv$iv)));
   }

   @NotNull
   public final Event first() {
      Object var10002 = this.events.firstKey();
      Intrinsics.checkNotNullExpressionValue(var10002, "firstKey(...)");
      Instant var1 = (Instant)var10002;
      Object var10003 = MapsKt.getValue((Map)this.events, this.events.firstKey());
      Intrinsics.checkNotNullExpressionValue(var10003, "getValue(...)");
      return new Event(var1, (List)var10003);
   }

   @NotNull
   public final Event last() {
      Object var10002 = this.events.lastKey();
      Intrinsics.checkNotNullExpressionValue(var10002, "lastKey(...)");
      Instant var1 = (Instant)var10002;
      Object var10003 = MapsKt.getValue((Map)this.events, this.events.lastKey());
      Intrinsics.checkNotNullExpressionValue(var10003, "getValue(...)");
      return new Event(var1, (List)var10003);
   }

   public void close() {
      this.events.clear();
   }

   @Nullable
   public Object play(@NotNull EventChannel channel, @NotNull Continuation $completion) {
      return play$suspendImpl(this, channel, $completion);
   }

   // $FF: synthetic method
   static Object play$suspendImpl(final HistoricPriceFeed $this, EventChannel channel, Continuation var2) {
      label35: {
         if (var2 instanceof Continuation $continuation) {
            if (($continuation.label & Integer.MIN_VALUE) != 0) {
               $continuation.label -= Integer.MIN_VALUE;
               break label35;
            }
         }

         $continuation = new ContinuationImpl(var2) {
            Object L$0;
            Object L$1;
            // $FF: synthetic field
            Object result;
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
               this.result = $result;
               this.label |= Integer.MIN_VALUE;
               return HistoricPriceFeed.play$suspendImpl($this, (EventChannel)null, (Continuation)this);
            }
         };
      }

      Object $result = $continuation.result;
      Object var13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      Iterator var5;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure($result);
            Map $this$forEach$iv = (Map)$this.events;
            int $i$f$forEach = 0;
            var5 = $this$forEach$iv.entrySet().iterator();
            break;
         case 1:
            int $i$f$forEach = 0;
            int var8 = 0;
            var5 = (Iterator)$continuation.L$1;
            channel = (EventChannel)$continuation.L$0;
            ResultKt.throwOnFailure($result);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      while(var5.hasNext()) {
         Map.Entry element$iv = (Map.Entry)var5.next();
         int var15 = 0;
         Object var10002 = element$iv.getKey();
         Intrinsics.checkNotNullExpressionValue(var10002, "<get-key>(...)");
         Instant var16 = (Instant)var10002;
         Object var9 = element$iv.getValue();
         Intrinsics.checkNotNullExpressionValue(var9, "<get-value>(...)");
         Event event = new Event(var16, (List)var9);
         $continuation.L$0 = channel;
         $continuation.L$1 = var5;
         $continuation.label = 1;
         if (channel.send(event, $continuation) == var13) {
            return var13;
         }
      }

      return Unit.INSTANCE;
   }

   protected final synchronized void add(@NotNull Instant time, @NotNull PriceItem action) {
      Intrinsics.checkNotNullParameter(time, "time");
      Intrinsics.checkNotNullParameter(action, "action");
      Map $this$getOrPut$iv = (Map)this.events;
      int $i$f$getOrPut = 0;
      Object value$iv = $this$getOrPut$iv.get(time);
      Object var10000;
      if (value$iv == null) {
         int var7 = 0;
         Object answer$iv = (List)(new ArrayList());
         $this$getOrPut$iv.put(time, answer$iv);
         var10000 = answer$iv;
      } else {
         var10000 = value$iv;
      }

      List actions = (List)var10000;
      actions.add(action);
   }

   protected final synchronized void addAll(@NotNull Instant time, @NotNull List actions) {
      Intrinsics.checkNotNullParameter(time, "time");
      Intrinsics.checkNotNullParameter(actions, "actions");
      Map $this$getOrPut$iv = (Map)this.events;
      int $i$f$getOrPut = 0;
      Object value$iv = $this$getOrPut$iv.get(time);
      Object var10000;
      if (value$iv == null) {
         int var7 = 0;
         Object answer$iv = (List)(new ArrayList());
         $this$getOrPut$iv.put(time, answer$iv);
         var10000 = answer$iv;
      } else {
         var10000 = value$iv;
      }

      List existing = (List)var10000;
      existing.addAll((Collection)actions);
   }

   public final void merge(@NotNull HistoricPriceFeed feed) {
      Intrinsics.checkNotNullParameter(feed, "feed");

      for(Map.Entry event : ((Map)feed.events).entrySet()) {
         Object var10001 = event.getKey();
         Intrinsics.checkNotNullExpressionValue(var10001, "<get-key>(...)");
         Instant var4 = (Instant)var10001;
         Object var10002 = event.getValue();
         Intrinsics.checkNotNullExpressionValue(var10002, "<get-value>(...)");
         this.addAll(var4, (List)var10002);
      }

   }

   @NotNull
   public String toString() {
      return this.events.isEmpty() ? "events=0 assets=0" : "events=" + this.events.size() + " start=" + this.events.firstKey() + " end=" + this.events.lastKey() + " assets=" + this.getAssets().size();
   }

   @NotNull
   public List sample(int size, int samples, @NotNull Random random) {
      return HistoricFeed.DefaultImpls.sample(this, size, samples, random);
   }

   @NotNull
   public List split(@NotNull TimeSpan period, @NotNull TimeSpan overlap) {
      return HistoricFeed.DefaultImpls.split(this, period, overlap);
   }

   @NotNull
   public List split(int size) {
      return HistoricFeed.DefaultImpls.split(this, size);
   }

   @NotNull
   public Job playBackground(@NotNull EventChannel channel) {
      return HistoricFeed.DefaultImpls.playBackground(this, channel);
   }
}
