package org.roboquant.journals.metrics;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000 \n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u0004\n\u0002\b\u0002\u001aC\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012*\u0010\u0004\u001a\u0016\u0012\u0012\b\u0001\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u00060\u0005\"\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\b¨\u0006\t"},
   d2 = {"metricResultsOf", "", "", "", "metricResults", "", "Lkotlin/Pair;", "", "([Lkotlin/Pair;)Ljava/util/Map;", "roboquant"}
)
@SourceDebugExtension({"SMAP\nMetric.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Metric.kt\norg/roboquant/journals/metrics/MetricKt\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,67:1\n8406#2,2:68\n9088#2,4:70\n*S KotlinDebug\n*F\n+ 1 Metric.kt\norg/roboquant/journals/metrics/MetricKt\n*L\n33#1:68,2\n33#1:70,4\n*E\n"})
public final class MetricKt {
   @NotNull
   public static final Map metricResultsOf(@NotNull Pair... metricResults) {
      Intrinsics.checkNotNullParameter(metricResults, "metricResults");
      int $i$f$associate = 0;
      int capacity$iv = RangesKt.coerceAtLeast(MapsKt.mapCapacity(metricResults.length), 16);
      Pair[] $this$associateTo$iv$iv = metricResults;
      Map destination$iv$iv = (Map)(new LinkedHashMap(capacity$iv));
      int $i$f$associateTo = 0;
      int var7 = 0;

      for(int var8 = metricResults.length; var7 < var8; ++var7) {
         Object element$iv$iv = $this$associateTo$iv$iv[var7];
         int var12 = 0;
         Pair it = new Pair(((Pair)element$iv$iv).getFirst(), ((Number)((Pair)element$iv$iv).getSecond()).doubleValue());
         destination$iv$iv.put(it.getFirst(), it.getSecond());
      }

      return destination$iv$iv;
   }
}
