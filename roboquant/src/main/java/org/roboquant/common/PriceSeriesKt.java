package org.roboquant.common;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000&\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a6\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n¨\u0006\u000b"},
   d2 = {"addAll", "", "Lorg/roboquant/common/Asset;", "", "Lorg/roboquant/common/PriceSeries;", "event", "Lorg/roboquant/common/Event;", "capacity", "", "priceType", "", "roboquant"}
)
@SourceDebugExtension({"SMAP\nPriceSeries.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PriceSeries.kt\norg/roboquant/common/PriceSeriesKt\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,137:1\n372#2,7:138\n*S KotlinDebug\n*F\n+ 1 PriceSeries.kt\norg/roboquant/common/PriceSeriesKt\n*L\n130#1:138,7\n*E\n"})
public final class PriceSeriesKt {
   @NotNull
   public static final Set addAll(@NotNull Map $this$addAll, @NotNull Event event, int capacity, @NotNull String priceType) {
      Intrinsics.checkNotNullParameter($this$addAll, "<this>");
      Intrinsics.checkNotNullParameter(event, "event");
      Intrinsics.checkNotNullParameter(priceType, "priceType");
      Set result = (Set)(new LinkedHashSet());

      for(Map.Entry var6 : event.getPrices().entrySet()) {
         Asset asset = (Asset)var6.getKey();
         PriceItem action = (PriceItem)var6.getValue();
         int $i$f$getOrPut = 0;
         Object value$iv = $this$addAll.get(asset);
         Object var10000;
         if (value$iv == null) {
            int var13 = 0;
            Object answer$iv = new PriceSeries(capacity);
            $this$addAll.put(asset, answer$iv);
            var10000 = answer$iv;
         } else {
            var10000 = value$iv;
         }

         PriceSeries priceSeries = (PriceSeries)var10000;
         priceSeries.add(action.getPrice(priceType));
         if (priceSeries.isFull()) {
            result.add(asset);
         }
      }

      return result;
   }

   // $FF: synthetic method
   public static Set addAll$default(Map var0, Event var1, int var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = "DEFAULT";
      }

      return addAll(var0, var1, var2, var3);
   }
}
