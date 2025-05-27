package org.roboquant.ta;

import com.tictactec.ta.lib.Core;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Account;
import org.roboquant.common.Asset;
import org.roboquant.common.AssetFilter;
import org.roboquant.common.Event;
import org.roboquant.common.PriceBar;
import org.roboquant.journals.metrics.Metric;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001BS\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00128\u0010\u0006\u001a4\u0012\u0004\u0012\u00020\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r0\u0007¢\u0006\u0002\b\u0010¢\u0006\u0002\u0010\u0011J@\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001cH\u0016J\b\u0010 \u001a\u00020!H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R@\u0010\u0006\u001a4\u0012\u0004\u0012\u00020\b\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r0\u0007¢\u0006\u0002\b\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\t0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""},
   d2 = {"Lorg/roboquant/ta/TaLibMetric;", "Lorg/roboquant/journals/metrics/Metric;", "initialCapacity", "", "assetFilter", "Lorg/roboquant/common/AssetFilter;", "block", "Lkotlin/Function2;", "Lorg/roboquant/ta/TaLib;", "Lorg/roboquant/ta/PriceBarSeries;", "Lkotlin/ParameterName;", "name", "series", "", "", "", "Lkotlin/ExtensionFunctionType;", "(ILorg/roboquant/common/AssetFilter;Lkotlin/jvm/functions/Function2;)V", "history", "", "Lorg/roboquant/common/Asset;", "taLib", "calculate", "event", "Lorg/roboquant/common/Event;", "account", "Lorg/roboquant/common/Account;", "signals", "", "Lorg/roboquant/common/Signal;", "orders", "Lorg/roboquant/common/Order;", "reset", "", "roboquant"}
)
@SourceDebugExtension({"SMAP\nTaLibMetric.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TaLibMetric.kt\norg/roboquant/ta/TaLibMetric\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,82:1\n800#2,11:83\n766#2:94\n857#2,2:95\n1238#2,4:106\n372#3,7:97\n468#3:104\n414#3:105\n*S KotlinDebug\n*F\n+ 1 TaLibMetric.kt\norg/roboquant/ta/TaLibMetric\n*L\n54#1:83,11\n54#1:94\n54#1:95,2\n62#1:106,4\n58#1:97,7\n62#1:104\n62#1:105\n*E\n"})
public final class TaLibMetric implements Metric {
   private final int initialCapacity;
   @NotNull
   private final AssetFilter assetFilter;
   @NotNull
   private Function2 block;
   @NotNull
   private final Map history;
   @NotNull
   private final TaLib taLib;

   public TaLibMetric(int initialCapacity, @NotNull AssetFilter assetFilter, @NotNull Function2 block) {
      Intrinsics.checkNotNullParameter(assetFilter, "assetFilter");
      Intrinsics.checkNotNullParameter(block, "block");
      super();
      this.initialCapacity = initialCapacity;
      this.assetFilter = assetFilter;
      this.block = block;
      this.history = (Map)(new LinkedHashMap());
      this.taLib = new TaLib((Core)null, 1, (DefaultConstructorMarker)null);
   }

   // $FF: synthetic method
   public TaLibMetric(int var1, AssetFilter var2, Function2 var3, int var4, DefaultConstructorMarker var5) {
      if ((var4 & 1) != 0) {
         var1 = 1;
      }

      if ((var4 & 2) != 0) {
         var2 = AssetFilter.Companion.all();
      }

      this(var1, var2, var3);
   }

   @NotNull
   public Map calculate(@NotNull Event event, @NotNull Account account, @NotNull List signals, @NotNull List orders) {
      Intrinsics.checkNotNullParameter(event, "event");
      Intrinsics.checkNotNullParameter(account, "account");
      Intrinsics.checkNotNullParameter(signals, "signals");
      Intrinsics.checkNotNullParameter(orders, "orders");
      Map metrics = (Map)(new LinkedHashMap());
      Iterable $this$filterIsInstance$iv = (Iterable)event.getItems();
      int $i$f$filterIsInstance = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList());
      int $i$f$filterIsInstanceTo = 0;

      for(Object element$iv$iv : $this$filterIsInstance$iv) {
         if (element$iv$iv instanceof PriceBar) {
            destination$iv$iv.add(element$iv$iv);
         }
      }

      $this$filterIsInstance$iv = (Iterable)((List)destination$iv$iv);
      $i$f$filterIsInstance = 0;
      destination$iv$iv = (Collection)(new ArrayList());
      $i$f$filterIsInstanceTo = 0;

      for(Object element$iv$iv : $this$filterIsInstance$iv) {
         PriceBar it = (PriceBar)element$iv$iv;
         int var15 = 0;
         if (this.assetFilter.filter(it.getAsset(), event.getTime())) {
            destination$iv$iv.add(element$iv$iv);
         }
      }

      List actions = (List)destination$iv$iv;
      Instant time = event.getTime();

      for(PriceBar priceAction : actions) {
         Asset asset = priceAction.getAsset();
         Map $this$getOrPut$iv = this.history;
         int $i$f$getOrPut = 0;
         Object value$iv = $this$getOrPut$iv.get(asset);
         Object var10000;
         if (value$iv == null) {
            int var47 = 0;
            Object answer$iv = new PriceBarSeries(this.initialCapacity);
            $this$getOrPut$iv.put(asset, answer$iv);
            var10000 = answer$iv;
         } else {
            var10000 = value$iv;
         }

         PriceBarSeries buffer = (PriceBarSeries)var10000;
         if (buffer.add(priceAction, time)) {
            try {
               String var50 = asset.getSymbol().toLowerCase(Locale.ROOT);
               Intrinsics.checkNotNullExpressionValue(var50, "toLowerCase(...)");
               String postFix = var50;
               Map $this$mapKeys$iv = (Map)this.block.invoke(this.taLib, buffer);
               int $i$f$mapKeys = 0;
               Map destination$iv$iv = (Map)(new LinkedHashMap(MapsKt.mapCapacity($this$mapKeys$iv.size())));
               int $i$f$mapKeysTo = 0;
               Iterable $this$associateByTo$iv$iv$iv = (Iterable)$this$mapKeys$iv.entrySet();
               int $i$f$associateByTo = 0;

               for(Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
                  Map.Entry it = (Map.Entry)element$iv$iv$iv;
                  int var24 = 0;
                  String var10001 = it.getKey() + "." + postFix;
                  Map.Entry it$iv$iv = (Map.Entry)element$iv$iv$iv;
                  String var26 = var10001;
                  int var28 = 0;
                  Object var29 = it$iv$iv.getValue();
                  destination$iv$iv.put(var26, var29);
               }

               metrics.putAll(destination$iv$iv);
            } catch (InsufficientData ex) {
               buffer.increaseCapacity(ex.getMinSize());
            }
         }
      }

      return metrics;
   }

   public void reset() {
      Metric.DefaultImpls.reset(this);
      this.history.clear();
   }
}
