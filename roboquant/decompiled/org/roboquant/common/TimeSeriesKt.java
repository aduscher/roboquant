package org.roboquant.common;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\u001a,\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001*\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"},
   d2 = {"index", "", "", "Lorg/roboquant/common/TimeSeries;", "start", "", "roboquant"}
)
@SourceDebugExtension({"SMAP\nTimeSeries.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TimeSeries.kt\norg/roboquant/common/TimeSeriesKt\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,258:1\n453#2:259\n403#2:260\n1238#3,4:261\n*S KotlinDebug\n*F\n+ 1 TimeSeries.kt\norg/roboquant/common/TimeSeriesKt\n*L\n242#1:259\n242#1:260\n242#1:261,4\n*E\n"})
public final class TimeSeriesKt {
   @NotNull
   public static final Map index(@NotNull Map $this$index, double start) {
      Intrinsics.checkNotNullParameter($this$index, "<this>");
      int $i$f$mapValues = 0;
      Map destination$iv$iv = (Map)(new LinkedHashMap(MapsKt.mapCapacity($this$index.size())));
      int $i$f$mapValuesTo = 0;
      Iterable $this$associateByTo$iv$iv$iv = (Iterable)$this$index.entrySet();
      int $i$f$associateByTo = 0;

      for(Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
         Map.Entry it$iv$iv = (Map.Entry)element$iv$iv$iv;
         int var14 = 0;
         Object var10001 = it$iv$iv.getKey();
         Map.Entry it = (Map.Entry)element$iv$iv$iv;
         Object var18 = var10001;
         int var16 = 0;
         TimeSeries var19 = ((TimeSeries)it.getValue()).index(start);
         destination$iv$iv.put(var18, var19);
      }

      return destination$iv$iv;
   }

   // $FF: synthetic method
   public static Map index$default(Map var0, double var1, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = (double)1.0F;
      }

      return index(var0, var1);
   }
}
