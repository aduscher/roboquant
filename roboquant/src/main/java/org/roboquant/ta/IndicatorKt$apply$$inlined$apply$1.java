package org.roboquant.ta;

import java.util.Map;
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
import org.roboquant.common.Asset;
import org.roboquant.common.Timeframe;
import org.roboquant.feeds.Feed;

@DebugMetadata(
   f = "Indicator.kt",
   l = {139},
   i = {0, 0},
   s = {"L$0", "L$1"},
   n = {"channel", "job"},
   m = "invokeSuspend",
   c = "org.roboquant.ta.IndicatorKt$apply$$inlined$apply$1"
)
@Metadata(
   mv = {1, 9, 0},
   k = 3,
   xi = 48,
   d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\u00020\u0004H\u008a@¨\u0006\u0005"},
   d2 = {"<anonymous>", "", "T", "Lorg/roboquant/common/Item;", "Lkotlinx/coroutines/CoroutineScope;", "org/roboquant/feeds/FeedKt$apply$1"}
)
@SourceDebugExtension({"SMAP\nFeed.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Feed.kt\norg/roboquant/feeds/FeedKt$apply$1\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Indicator.kt\norg/roboquant/ta/IndicatorKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,249:1\n800#2,11:250\n1855#2:261\n1856#2:278\n65#3,5:262\n70#3,4:274\n372#4,7:267\n*S KotlinDebug\n*F\n+ 1 Feed.kt\norg/roboquant/feeds/FeedKt$apply$1\n+ 2 Indicator.kt\norg/roboquant/ta/IndicatorKt\n*L\n140#1:250,11\n140#1:261\n140#1:278\n69#2:267,7\n*E\n"})
public final class IndicatorKt$apply$$inlined$apply$1 extends SuspendLambda implements Function2 {
   Object L$0;
   Object L$1;
   int label;
   // $FF: synthetic field
   final Timeframe $timeframe;
   // $FF: synthetic field
   final Feed $this_apply;
   // $FF: synthetic field
   final Asset $asset$inlined;
   // $FF: synthetic field
   final Indicator $indicator$inlined;
   // $FF: synthetic field
   final boolean $addSymbolPostfix$inlined;
   // $FF: synthetic field
   final String $postfix$inlined;
   // $FF: synthetic field
   final Map $result$inlined;

   public IndicatorKt$apply$$inlined$apply$1(Timeframe $timeframe, Feed $receiver, Continuation $completion, Asset var4, Indicator var5, boolean var6, String var7, Map var8) {
      super(2, $completion);
      this.$timeframe = $timeframe;
      this.$this_apply = $receiver;
      this.$asset$inlined = var4;
      this.$indicator$inlined = var5;
      this.$addSymbolPostfix$inlined = var6;
      this.$postfix$inlined = var7;
      this.$result$inlined = var8;
   }

   @Nullable
   public final Object invokeSuspend(@NotNull Object param1) {
      // $FF: Couldn't be decompiled
   }

   @NotNull
   public final Continuation create(@Nullable Object value, @NotNull Continuation $completion) {
      return (Continuation)(new IndicatorKt$apply$$inlined$apply$1(this.$timeframe, this.$this_apply, $completion, this.$asset$inlined, this.$indicator$inlined, this.$addSymbolPostfix$inlined, this.$postfix$inlined, this.$result$inlined));
   }

   @Nullable
   public final Object invoke(@NotNull CoroutineScope p1, @Nullable Continuation p2) {
      return ((IndicatorKt$apply$$inlined$apply$1)this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
   }
}
