package org.roboquant.strategies;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Asset;
import org.roboquant.common.ExtensionsKt;
import org.roboquant.common.Signal;
import org.roboquant.common.SignalType;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00020\u0001\u001a\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00020\u0001\u001a\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00020\u0001\u001a\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00020\u0001*D\u0010\u0006\"\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00010\u0007¢\u0006\u0002\b\b2\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00010\u0007¢\u0006\u0002\b\b¨\u0006\t"},
   d2 = {"averageSignals", "", "Lorg/roboquant/common/Signal;", "firstSignals", "lastSignals", "sumSignals", "SignalResolver", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "roboquant"}
)
@SourceDebugExtension({"SMAP\nSignalResolver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SignalResolver.kt\norg/roboquant/strategies/SignalResolverKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,44:1\n1477#2:45\n1502#2,3:46\n1505#2,3:56\n1238#2,2:61\n1241#2:64\n766#2:65\n857#2,2:66\n1477#2:68\n1502#2,3:69\n1505#2,3:79\n1238#2,4:84\n766#2:88\n857#2,2:89\n1477#2:91\n1502#2,3:92\n1505#2,3:102\n1238#2,4:107\n1477#2:111\n1502#2,3:112\n1505#2,3:122\n1238#2,4:127\n372#3,7:49\n453#3:59\n403#3:60\n372#3,7:72\n453#3:82\n403#3:83\n372#3,7:95\n453#3:105\n403#3:106\n372#3,7:115\n453#3:125\n403#3:126\n1#4:63\n*S KotlinDebug\n*F\n+ 1 SignalResolver.kt\norg/roboquant/strategies/SignalResolverKt\n*L\n27#1:45\n27#1:46,3\n27#1:56,3\n27#1:61,2\n27#1:64\n28#1:65\n28#1:66,2\n33#1:68\n33#1:69,3\n33#1:79,3\n33#1:84,4\n34#1:88\n34#1:89,2\n38#1:91\n38#1:92,3\n38#1:102,3\n38#1:107,4\n42#1:111\n42#1:112,3\n42#1:122,3\n42#1:127,4\n27#1:49,7\n27#1:59\n27#1:60\n33#1:72,7\n33#1:82\n33#1:83\n38#1:95,7\n38#1:105\n38#1:106\n42#1:115,7\n42#1:125\n42#1:126\n*E\n"})
public final class SignalResolverKt {
   @NotNull
   public static final List sumSignals(@NotNull List $this$sumSignals) {
      Intrinsics.checkNotNullParameter($this$sumSignals, "<this>");
      Iterable $this$groupBy$iv = (Iterable)$this$sumSignals;
      int $i$f$groupBy = 0;
      Map destination$iv$iv = (Map)(new LinkedHashMap());
      int $i$f$groupByTo = 0;

      for(Object element$iv$iv : $this$groupBy$iv) {
         Signal it = (Signal)element$iv$iv;
         int var10 = 0;
         Object key$iv$iv = it.getAsset();
         int $i$f$getOrPut = 0;
         Object value$iv$iv$iv = destination$iv$iv.get(key$iv$iv);
         Object var10000;
         if (value$iv$iv$iv == null) {
            int var15 = 0;
            Object answer$iv$iv$iv = (List)(new ArrayList());
            destination$iv$iv.put(key$iv$iv, answer$iv$iv$iv);
            var10000 = answer$iv$iv$iv;
         } else {
            var10000 = value$iv$iv$iv;
         }

         List list$iv$iv = (List)var10000;
         list$iv$iv.add(element$iv$iv);
      }

      $i$f$groupBy = 0;
      Map $this$groupByTo$iv$iv = destination$iv$iv;
      destination$iv$iv = (Map)(new LinkedHashMap(MapsKt.mapCapacity(destination$iv$iv.size())));
      $i$f$groupByTo = 0;
      Iterable $this$associateByTo$iv$iv$iv = (Iterable)$this$groupByTo$iv$iv.entrySet();
      int $i$f$associateByTo = 0;

      for(Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
         Map.Entry it$iv$iv = (Map.Entry)element$iv$iv$iv;
         int var55 = 0;
         Object var10001 = it$iv$iv.getKey();
         Map.Entry entry = (Map.Entry)element$iv$iv$iv;
         Object var29 = var10001;
         int var58 = 0;
         Asset var59 = (Asset)entry.getKey();
         Iterable var16 = (Iterable)entry.getValue();
         Asset var17 = var59;
         double var18 = (double)0.0F;

         for(Object var21 : var16) {
            Signal it = (Signal)var21;
            int var25 = 0;
            double var26 = it.getRating();
            var18 += var26;
         }

         Object var31 = null;
         byte var32 = 12;
         Object var33 = null;
         Object var34 = null;
         Signal var30 = new Signal(var17, var18, (SignalType)var34, (String)var33, var32, (DefaultConstructorMarker)var31);
         destination$iv$iv.put(var29, var30);
      }

      $this$groupBy$iv = (Iterable)destination$iv$iv.values();
      $i$f$groupBy = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList());
      $i$f$groupByTo = 0;

      for(Object element$iv$iv : $this$groupBy$iv) {
         Signal it = (Signal)element$iv$iv;
         int var53 = 0;
         if (!ExtensionsKt.getIszero(it.getRating())) {
            destination$iv$iv.add(element$iv$iv);
         }
      }

      return (List)destination$iv$iv;
   }

   @NotNull
   public static final List averageSignals(@NotNull List $this$averageSignals) {
      Intrinsics.checkNotNullParameter($this$averageSignals, "<this>");
      Iterable $this$groupBy$iv = (Iterable)$this$averageSignals;
      int $i$f$groupBy = 0;
      Map destination$iv$iv = (Map)(new LinkedHashMap());
      int $i$f$groupByTo = 0;

      for(Object element$iv$iv : $this$groupBy$iv) {
         Signal it = (Signal)element$iv$iv;
         int var10 = 0;
         Object key$iv$iv = it.getAsset();
         int $i$f$getOrPut = 0;
         Object value$iv$iv$iv = destination$iv$iv.get(key$iv$iv);
         Object var10000;
         if (value$iv$iv$iv == null) {
            int var15 = 0;
            Object answer$iv$iv$iv = (List)(new ArrayList());
            destination$iv$iv.put(key$iv$iv, answer$iv$iv$iv);
            var10000 = answer$iv$iv$iv;
         } else {
            var10000 = value$iv$iv$iv;
         }

         List list$iv$iv = (List)var10000;
         list$iv$iv.add(element$iv$iv);
      }

      $i$f$groupBy = 0;
      Map $this$groupByTo$iv$iv = destination$iv$iv;
      destination$iv$iv = (Map)(new LinkedHashMap(MapsKt.mapCapacity(destination$iv$iv.size())));
      $i$f$groupByTo = 0;
      Iterable $this$associateByTo$iv$iv$iv = (Iterable)$this$groupByTo$iv$iv.entrySet();
      int $i$f$associateByTo = 0;

      for(Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
         Map.Entry it$iv$iv = (Map.Entry)element$iv$iv$iv;
         int var55 = 0;
         Object var10001 = it$iv$iv.getKey();
         Map.Entry entry = (Map.Entry)element$iv$iv$iv;
         Object var29 = var10001;
         int var58 = 0;
         Asset var59 = (Asset)entry.getKey();
         Iterable var16 = (Iterable)entry.getValue();
         Asset var17 = var59;
         double var18 = (double)0.0F;

         for(Object var21 : var16) {
            Signal it = (Signal)var21;
            int var25 = 0;
            double var26 = it.getRating();
            var18 += var26;
         }

         double var60 = var18 / (double)((List)entry.getValue()).size();
         Object var31 = null;
         byte var32 = 12;
         Object var33 = null;
         Object var34 = null;
         double var35 = var60;
         Signal var30 = new Signal(var17, var35, (SignalType)var34, (String)var33, var32, (DefaultConstructorMarker)var31);
         destination$iv$iv.put(var29, var30);
      }

      $this$groupBy$iv = (Iterable)destination$iv$iv.values();
      $i$f$groupBy = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList());
      $i$f$groupByTo = 0;

      for(Object element$iv$iv : $this$groupBy$iv) {
         Signal it = (Signal)element$iv$iv;
         int var53 = 0;
         if (!ExtensionsKt.getIszero(it.getRating())) {
            destination$iv$iv.add(element$iv$iv);
         }
      }

      return (List)destination$iv$iv;
   }

   @NotNull
   public static final List firstSignals(@NotNull List $this$firstSignals) {
      Intrinsics.checkNotNullParameter($this$firstSignals, "<this>");
      Iterable $this$groupBy$iv = (Iterable)$this$firstSignals;
      int $i$f$groupBy = 0;
      Map destination$iv$iv = (Map)(new LinkedHashMap());
      int $i$f$groupByTo = 0;

      for(Object element$iv$iv : $this$groupBy$iv) {
         Signal it = (Signal)element$iv$iv;
         int var9 = 0;
         Object key$iv$iv = it.getAsset();
         int $i$f$getOrPut = 0;
         Object value$iv$iv$iv = destination$iv$iv.get(key$iv$iv);
         Object var10000;
         if (value$iv$iv$iv == null) {
            int var14 = 0;
            Object answer$iv$iv$iv = (List)(new ArrayList());
            destination$iv$iv.put(key$iv$iv, answer$iv$iv$iv);
            var10000 = answer$iv$iv$iv;
         } else {
            var10000 = value$iv$iv$iv;
         }

         List list$iv$iv = (List)var10000;
         list$iv$iv.add(element$iv$iv);
      }

      $i$f$groupBy = 0;
      Map $this$groupByTo$iv$iv = destination$iv$iv;
      destination$iv$iv = (Map)(new LinkedHashMap(MapsKt.mapCapacity(destination$iv$iv.size())));
      $i$f$groupByTo = 0;
      Iterable $this$associateByTo$iv$iv$iv = (Iterable)$this$groupByTo$iv$iv.entrySet();
      int $i$f$associateByTo = 0;

      for(Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
         Map.Entry it$iv$iv = (Map.Entry)element$iv$iv$iv;
         int var27 = 0;
         Object var10001 = it$iv$iv.getKey();
         Map.Entry it = (Map.Entry)element$iv$iv$iv;
         Object var16 = var10001;
         int var30 = 0;
         Signal var17 = (Signal)CollectionsKt.first((List)it.getValue());
         destination$iv$iv.put(var16, var17);
      }

      return CollectionsKt.toList((Iterable)destination$iv$iv.values());
   }

   @NotNull
   public static final List lastSignals(@NotNull List $this$lastSignals) {
      Intrinsics.checkNotNullParameter($this$lastSignals, "<this>");
      Iterable $this$groupBy$iv = (Iterable)$this$lastSignals;
      int $i$f$groupBy = 0;
      Map destination$iv$iv = (Map)(new LinkedHashMap());
      int $i$f$groupByTo = 0;

      for(Object element$iv$iv : $this$groupBy$iv) {
         Signal it = (Signal)element$iv$iv;
         int var9 = 0;
         Object key$iv$iv = it.getAsset();
         int $i$f$getOrPut = 0;
         Object value$iv$iv$iv = destination$iv$iv.get(key$iv$iv);
         Object var10000;
         if (value$iv$iv$iv == null) {
            int var14 = 0;
            Object answer$iv$iv$iv = (List)(new ArrayList());
            destination$iv$iv.put(key$iv$iv, answer$iv$iv$iv);
            var10000 = answer$iv$iv$iv;
         } else {
            var10000 = value$iv$iv$iv;
         }

         List list$iv$iv = (List)var10000;
         list$iv$iv.add(element$iv$iv);
      }

      $i$f$groupBy = 0;
      Map $this$groupByTo$iv$iv = destination$iv$iv;
      destination$iv$iv = (Map)(new LinkedHashMap(MapsKt.mapCapacity(destination$iv$iv.size())));
      $i$f$groupByTo = 0;
      Iterable $this$associateByTo$iv$iv$iv = (Iterable)$this$groupByTo$iv$iv.entrySet();
      int $i$f$associateByTo = 0;

      for(Object element$iv$iv$iv : $this$associateByTo$iv$iv$iv) {
         Map.Entry it$iv$iv = (Map.Entry)element$iv$iv$iv;
         int var27 = 0;
         Object var10001 = it$iv$iv.getKey();
         Map.Entry it = (Map.Entry)element$iv$iv$iv;
         Object var16 = var10001;
         int var30 = 0;
         Signal var17 = (Signal)CollectionsKt.last((List)it.getValue());
         destination$iv$iv.put(var16, var17);
      }

      return CollectionsKt.toList((Iterable)destination$iv$iv.values());
   }
}
