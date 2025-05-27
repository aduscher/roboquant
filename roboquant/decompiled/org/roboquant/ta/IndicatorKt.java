package org.roboquant.ta;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Asset;
import org.roboquant.common.TimeSeries;
import org.roboquant.common.Timeframe;
import org.roboquant.feeds.Feed;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u00002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001aK\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b\"\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000e¨\u0006\u000f"},
   d2 = {"apply", "", "", "Lorg/roboquant/common/TimeSeries;", "Lorg/roboquant/feeds/Feed;", "indicator", "Lorg/roboquant/ta/Indicator;", "assets", "", "Lorg/roboquant/common/Asset;", "timeframe", "Lorg/roboquant/common/Timeframe;", "addSymbolPostfix", "", "(Lorg/roboquant/feeds/Feed;Lorg/roboquant/ta/Indicator;[Lorg/roboquant/common/Asset;Lorg/roboquant/common/Timeframe;Z)Ljava/util/Map;", "roboquant"}
)
@SourceDebugExtension({"SMAP\nIndicator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Indicator.kt\norg/roboquant/ta/IndicatorKt\n+ 2 Feed.kt\norg/roboquant/feeds/FeedKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,77:1\n132#2:78\n149#2:79\n453#3:80\n403#3:81\n1238#4,4:82\n*S KotlinDebug\n*F\n+ 1 Indicator.kt\norg/roboquant/ta/IndicatorKt\n*L\n64#1:78\n64#1:79\n75#1:80\n75#1:81\n75#1:82,4\n*E\n"})
public final class IndicatorKt {
   @NotNull
   public static final Map apply(@NotNull Feed $this$apply, @NotNull Indicator indicator, @NotNull Asset[] assets, @NotNull Timeframe timeframe, boolean addSymbolPostfix) {
      Intrinsics.checkNotNullParameter($this$apply, "<this>");
      Intrinsics.checkNotNullParameter(indicator, "indicator");
      Intrinsics.checkNotNullParameter(assets, "assets");
      Intrinsics.checkNotNullParameter(timeframe, "timeframe");
      Map result = (Map)(new LinkedHashMap());
      int var6 = 0;

      for(int var7 = assets.length; var6 < var7; ++var6) {
         Asset asset = assets[var6];
         indicator.clear();
         String var10000 = asset.getSymbol().toLowerCase(Locale.ROOT);
         Intrinsics.checkNotNullExpressionValue(var10000, "toLowerCase(...)");
         String postfix = var10000;
         int $i$f$apply = 0;
         BuildersKt.runBlocking$default((CoroutineContext)null, new IndicatorKt$apply$$inlined$apply$1(timeframe, $this$apply, (Continuation)null, asset, indicator, addSymbolPostfix, postfix, result), 1, (Object)null);
      }

      int $i$f$mapValues = 0;
      Map destination$iv$iv = (Map)(new LinkedHashMap(MapsKt.mapCapacity(result.size())));
      int $i$f$mapValuesTo = 0;
      Iterable $this$associateByTo$iv$iv$iv = (Iterable)result.entrySet();
      int $i$f$associateByTo = 0;

      for(Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
         Map.Entry it$iv$iv = (Map.Entry)element$iv$iv$iv;
         int var17 = 0;
         Object var10001 = it$iv$iv.getKey();
         Map.Entry it = (Map.Entry)element$iv$iv$iv;
         Object var21 = var10001;
         int var19 = 0;
         TimeSeries var22 = new TimeSeries((List)it.getValue());
         destination$iv$iv.put(var21, var22);
      }

      return destination$iv$iv;
   }

   // $FF: synthetic method
   public static Map apply$default(Feed var0, Indicator var1, Asset[] var2, Timeframe var3, boolean var4, int var5, Object var6) {
      if ((var5 & 4) != 0) {
         var3 = Timeframe.Companion.getINFINITE();
      }

      if ((var5 & 8) != 0) {
         var4 = true;
      }

      return apply(var0, var1, var2, var3, var4);
   }
}
