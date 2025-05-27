package org.roboquant.optimize;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010'\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0004\n\u0002\u0010(\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J$\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u00162\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00070\u001eJ\u001c\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00062\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00070 J\u001c\u0010!\u001a\u00020\u001a2\b\b\u0002\u0010\"\u001a\u00020\r2\b\b\u0002\u0010#\u001a\u00020\u0016H\u0002J\u000f\u0010$\u001a\b\u0012\u0004\u0012\u00020\r0%H\u0096\u0002R3\u0010\u0003\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u00050\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR!\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u000b\u001a\u0004\b\u000e\u0010\tR6\u0010\u0010\u001a*\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00040\u0011j\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0004`\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006&"},
   d2 = {"Lorg/roboquant/optimize/GridSearch;", "Lorg/roboquant/optimize/SearchSpace;", "()V", "entries", "", "", "", "", "getEntries", "()Ljava/util/List;", "entries$delegate", "Lkotlin/Lazy;", "list", "Lorg/roboquant/optimize/Params;", "getList", "list$delegate", "params", "Ljava/util/LinkedHashMap;", "Lkotlin/collections/LinkedHashMap;", "permutations", "", "size", "", "getSize", "()I", "add", "", "name", "samples", "fn", "Lkotlin/Function0;", "values", "", "calcPermutations", "entry", "idx", "iterator", "", "roboquant"}
)
@SourceDebugExtension({"SMAP\nSearchSpace.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SearchSpace.kt\norg/roboquant/optimize/GridSearch\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,186:1\n1549#2:187\n1620#2,3:188\n2661#2,7:195\n125#3:191\n152#3,3:192\n*S KotlinDebug\n*F\n+ 1 SearchSpace.kt\norg/roboquant/optimize/GridSearch\n*L\n155#1:187\n155#1:188,3\n162#1:195,7\n162#1:191\n162#1:192,3\n*E\n"})
public final class GridSearch implements SearchSpace {
   @NotNull
   private final LinkedHashMap params = new LinkedHashMap();
   @NotNull
   private final Lazy entries$delegate = LazyKt.lazy(new Function0() {
      @NotNull
      public final List invoke() {
         Set var10000 = GridSearch.this.params.entrySet();
         Intrinsics.checkNotNullExpressionValue(var10000, "<get-entries>(...)");
         return CollectionsKt.toList((Iterable)var10000);
      }
   });
   @NotNull
   private final List permutations = (List)(new ArrayList());
   @NotNull
   private final Lazy list$delegate = LazyKt.lazy(new Function0() {
      @NotNull
      public final List invoke() {
         GridSearch.calcPermutations$default(GridSearch.this, (Params)null, 0, 3, (Object)null);
         return GridSearch.this.permutations;
      }
   });

   private final List getEntries() {
      Lazy var1 = this.entries$delegate;
      return (List)var1.getValue();
   }

   private final void calcPermutations(Params entry, int idx) {
      Map.Entry var3 = (Map.Entry)this.getEntries().get(idx);
      Intrinsics.checkNotNull(var3);
      String key = (String)var3.getKey();

      for(Object value : (List)var3.getValue()) {
         Map var10000 = (Map)entry;
         Intrinsics.checkNotNull(key);
         var10000.put(key, value);
         if (idx == CollectionsKt.getLastIndex(this.getEntries())) {
            List var8 = this.permutations;
            Object var10001 = entry.clone();
            Intrinsics.checkNotNull(var10001, "null cannot be cast to non-null type org.roboquant.optimize.Params");
            var8.add((Params)var10001);
         } else {
            this.calcPermutations(entry, idx + 1);
         }
      }

   }

   // $FF: synthetic method
   static void calcPermutations$default(GridSearch var0, Params var1, int var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = new Params();
      }

      if ((var3 & 2) != 0) {
         var2 = 0;
      }

      var0.calcPermutations(var1, var2);
   }

   private final List getList() {
      Lazy var1 = this.list$delegate;
      return (List)var1.getValue();
   }

   public final void add(@NotNull String name, @NotNull Iterable values) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(values, "values");
      ((Map)this.params).put(name, CollectionsKt.toList(values));
   }

   public final void add(@NotNull String name, int samples, @NotNull Function0 fn) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(fn, "fn");
      Map var4 = (Map)this.params;
      Iterable $this$map$iv = (Iterable)(new IntRange(1, samples));
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      int $i$f$mapTo = 0;
      Iterator var10 = $this$map$iv.iterator();

      while(var10.hasNext()) {
         int item$iv$iv = ((IntIterator)var10).nextInt();
         int var13 = 0;
         destination$iv$iv.add(fn.invoke());
      }

      List var15 = (List)destination$iv$iv;
      var4.put(name, var15);
   }

   public int getSize() {
      Map $this$map$iv = (Map)this.params;
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList($this$map$iv.size()));
      int $i$f$mapTo = 0;

      for(Map.Entry item$iv$iv : $this$map$iv.entrySet()) {
         int var9 = 0;
         destination$iv$iv.add(((List)item$iv$iv.getValue()).size());
      }

      Iterable $this$reduce$iv = (Iterable)((List)destination$iv$iv);
      $i$f$map = 0;
      Iterator iterator$iv = $this$reduce$iv.iterator();
      if (!iterator$iv.hasNext()) {
         throw new UnsupportedOperationException("Empty collection can't be reduced.");
      } else {
         int p0;
         for(accumulator$iv = iterator$iv.next(); iterator$iv.hasNext(); accumulator$iv = p0 * $i$f$mapTo) {
            $i$f$mapTo = ((Number)iterator$iv.next()).intValue();
            p0 = ((Number)accumulator$iv).intValue();
            int var16 = 0;
         }

         return ((Number)accumulator$iv).intValue();
      }
   }

   @NotNull
   public Iterator iterator() {
      return (Iterator)this.getList().listIterator();
   }

   public void update(@NotNull Params params, double score) {
      SearchSpace.DefaultImpls.update(this, params, score);
   }
}
