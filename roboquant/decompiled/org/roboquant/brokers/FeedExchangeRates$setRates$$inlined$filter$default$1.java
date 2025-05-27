package org.roboquant.brokers;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Timeframe;
import org.roboquant.feeds.Feed;

@DebugMetadata(
   f = "FeedExchangeRates.kt",
   l = {112},
   i = {0, 0, 0},
   s = {"L$0", "L$1", "L$2"},
   n = {"channel", "result", "job"},
   m = "invokeSuspend",
   c = "org.roboquant.brokers.FeedExchangeRates$setRates$$inlined$filter$default$1"
)
@Metadata(
   mv = {1, 9, 0},
   k = 3,
   xi = 48,
   d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u00040\u00020\u0001\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0005*\u00020\u0006H\u008a@¨\u0006\u0007"},
   d2 = {"<anonymous>", "", "Lkotlin/Pair;", "Ljava/time/Instant;", "T", "Lorg/roboquant/common/Item;", "Lkotlinx/coroutines/CoroutineScope;", "org/roboquant/feeds/FeedKt$filter$2"}
)
@SourceDebugExtension({"SMAP\nFeed.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Feed.kt\norg/roboquant/feeds/FeedKt$filter$2\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Feed.kt\norg/roboquant/feeds/FeedKt$filter$1\n*L\n1#1,249:1\n800#2,11:250\n766#2:261\n857#2:262\n858#2:264\n1549#2:265\n1620#2,3:266\n103#3:263\n*S KotlinDebug\n*F\n+ 1 Feed.kt\norg/roboquant/feeds/FeedKt$filter$2\n*L\n113#1:250,11\n113#1:261\n113#1:262\n113#1:264\n113#1:265\n113#1:266,3\n*E\n"})
public final class FeedExchangeRates$setRates$$inlined$filter$default$1 extends SuspendLambda implements Function2 {
   Object L$0;
   Object L$1;
   Object L$2;
   int label;
   // $FF: synthetic field
   final Timeframe $timeframe;
   // $FF: synthetic field
   final Feed $this_filter;

   public FeedExchangeRates$setRates$$inlined$filter$default$1(Timeframe $timeframe, Feed $receiver, Continuation $completion) {
      super(2, $completion);
      this.$timeframe = $timeframe;
      this.$this_filter = $receiver;
   }

   @Nullable
   public final Object invokeSuspend(@NotNull Object param1) {
      // $FF: Couldn't be decompiled
   }

   @NotNull
   public final Continuation create(@Nullable Object value, @NotNull Continuation $completion) {
      return (Continuation)(new FeedExchangeRates$setRates$$inlined$filter$default$1(this.$timeframe, this.$this_filter, $completion));
   }

   @Nullable
   public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation p2) {
      return ((FeedExchangeRates$setRates$$inlined$filter$default$1)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
   }
}
