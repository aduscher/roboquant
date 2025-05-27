package org.roboquant.ta;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.markers.KMutableMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Asset;
import org.roboquant.common.Event;
import org.roboquant.common.PriceBar;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\u0010'\n\u0002\b\b\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010$\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B#\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\u0010\bJ\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 J\t\u0010!\u001a\u00020\u001eH\u0096\u0001J\u0011\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u0002H\u0096\u0001J\u0011\u0010$\u001a\u00020\u00182\u0006\u0010%\u001a\u00020\u0003H\u0096\u0001J\u0013\u0010&\u001a\u0004\u0018\u00010\u00032\u0006\u0010#\u001a\u00020\u0002H\u0096\u0003J\t\u0010'\u001a\u00020\u0018H\u0096\u0001J\u001b\u0010(\u001a\u0004\u0018\u00010\u00032\u0006\u0010#\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u0003H\u0096\u0001J\u001f\u0010)\u001a\u00020\u001e2\u0014\u0010*\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030+H\u0096\u0001J\u0013\u0010,\u001a\u0004\u0018\u00010\u00032\u0006\u0010#\u001a\u00020\u0002H\u0096\u0001R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u000b0\nX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\nX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u00020\u0005X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00030\u0014X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006-"},
   d2 = {"Lorg/roboquant/ta/AssetPriceBarSeries;", "", "Lorg/roboquant/common/Asset;", "Lorg/roboquant/ta/PriceBarSeries;", "capacity", "", "(I)V", "map", "(ILjava/util/Map;)V", "entries", "", "", "getEntries", "()Ljava/util/Set;", "keys", "getKeys", "size", "getSize", "()I", "values", "", "getValues", "()Ljava/util/Collection;", "add", "", "priceBar", "Lorg/roboquant/common/PriceBar;", "time", "Ljava/time/Instant;", "addAll", "", "event", "Lorg/roboquant/common/Event;", "clear", "containsKey", "key", "containsValue", "value", "get", "isEmpty", "put", "putAll", "from", "", "remove", "roboquant"}
)
@SourceDebugExtension({"SMAP\nAssetPriceBarSeries.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AssetPriceBarSeries.kt\norg/roboquant/ta/AssetPriceBarSeries\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,61:1\n372#2,7:62\n372#2,7:80\n800#3,11:69\n*S KotlinDebug\n*F\n+ 1 AssetPriceBarSeries.kt\norg/roboquant/ta/AssetPriceBarSeries\n*L\n45#1:62,7\n55#1:80,7\n54#1:69,11\n*E\n"})
public final class AssetPriceBarSeries implements Map, KMutableMap {
   private final int capacity;
   @NotNull
   private final Map map;

   private AssetPriceBarSeries(int capacity, Map map) {
      this.capacity = capacity;
      this.map = map;
   }

   @NotNull
   public Set getEntries() {
      return this.map.entrySet();
   }

   @NotNull
   public Set getKeys() {
      return this.map.keySet();
   }

   public int getSize() {
      return this.map.size();
   }

   @NotNull
   public Collection getValues() {
      return this.map.values();
   }

   public void clear() {
      this.map.clear();
   }

   public boolean containsKey(@NotNull Asset key) {
      Intrinsics.checkNotNullParameter(key, "key");
      return this.map.containsKey(key);
   }

   public boolean containsValue(@NotNull PriceBarSeries value) {
      Intrinsics.checkNotNullParameter(value, "value");
      return this.map.containsValue(value);
   }

   @Nullable
   public PriceBarSeries get(@NotNull Asset key) {
      Intrinsics.checkNotNullParameter(key, "key");
      return (PriceBarSeries)this.map.get(key);
   }

   public boolean isEmpty() {
      return this.map.isEmpty();
   }

   @Nullable
   public PriceBarSeries put(@NotNull Asset key, @NotNull PriceBarSeries value) {
      Intrinsics.checkNotNullParameter(key, "key");
      Intrinsics.checkNotNullParameter(value, "value");
      return (PriceBarSeries)this.map.put(key, value);
   }

   public void putAll(@NotNull Map from) {
      Intrinsics.checkNotNullParameter(from, "from");
      this.map.putAll(from);
   }

   @Nullable
   public PriceBarSeries remove(@NotNull Asset key) {
      Intrinsics.checkNotNullParameter(key, "key");
      return (PriceBarSeries)this.map.remove(key);
   }

   public AssetPriceBarSeries(int capacity) {
      this(capacity, (Map)(new LinkedHashMap()));
   }

   public final boolean add(@NotNull PriceBar priceBar, @NotNull Instant time) {
      Intrinsics.checkNotNullParameter(priceBar, "priceBar");
      Intrinsics.checkNotNullParameter(time, "time");
      Map $this$getOrPut$iv = this.map;
      Object key$iv = priceBar.getAsset();
      int $i$f$getOrPut = 0;
      Object value$iv = $this$getOrPut$iv.get(key$iv);
      Object var10000;
      if (value$iv == null) {
         int var8 = 0;
         Object answer$iv = new PriceBarSeries(this.capacity);
         $this$getOrPut$iv.put(key$iv, answer$iv);
         var10000 = answer$iv;
      } else {
         var10000 = value$iv;
      }

      PriceBarSeries series = (PriceBarSeries)var10000;
      series.add(priceBar, time);
      return series.isFull();
   }

   // $FF: synthetic method
   public static boolean add$default(AssetPriceBarSeries var0, PriceBar var1, Instant var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         Instant var10000 = Instant.MIN;
         Intrinsics.checkNotNullExpressionValue(var10000, "MIN");
         var2 = var10000;
      }

      return var0.add(var1, var2);
   }

   public final void addAll(@NotNull Event event) {
      Intrinsics.checkNotNullParameter(event, "event");
      Iterable $this$filterIsInstance$iv = (Iterable)event.getItems();
      int $i$f$filterIsInstance = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList());
      int $i$f$filterIsInstanceTo = 0;

      for(Object element$iv$iv : $this$filterIsInstance$iv) {
         if (element$iv$iv instanceof PriceBar) {
            destination$iv$iv.add(element$iv$iv);
         }
      }

      for(PriceBar action : (List)destination$iv$iv) {
         Map $this$filterIsInstanceTo$iv$iv = this.map;
         Object key$iv = action.getAsset();
         $i$f$filterIsInstanceTo = 0;
         Object value$iv = $this$filterIsInstanceTo$iv$iv.get(key$iv);
         Object var10000;
         if (value$iv == null) {
            int var15 = 0;
            Object answer$iv = new PriceBarSeries(this.capacity);
            $this$filterIsInstanceTo$iv$iv.put(key$iv, answer$iv);
            var10000 = answer$iv;
         } else {
            var10000 = value$iv;
         }

         PriceBarSeries series = (PriceBarSeries)var10000;
         series.add(action, event.getTime());
      }

   }
}
