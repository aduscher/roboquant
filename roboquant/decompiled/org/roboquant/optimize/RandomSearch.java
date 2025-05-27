package org.roboquant.optimize;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002 !B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000e2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019J\u001c\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000e2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001cJ\b\u0010\u001d\u001a\u00020\u0016H\u0002J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00070\u001fH\u0096\u0002R!\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR*\u0010\f\u001a\u001e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rj\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f`\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\""},
   d2 = {"Lorg/roboquant/optimize/RandomSearch;", "Lorg/roboquant/optimize/SearchSpace;", "size", "", "(I)V", "list", "", "Lorg/roboquant/optimize/Params;", "getList", "()Ljava/util/List;", "list$delegate", "Lkotlin/Lazy;", "params", "Ljava/util/LinkedHashMap;", "", "Lorg/roboquant/optimize/RandomSearch$Entry;", "Lkotlin/collections/LinkedHashMap;", "permutations", "", "getSize", "()I", "add", "", "name", "fn", "Lkotlin/Function0;", "", "values", "", "calcPermutations", "iterator", "", "DoneException", "Entry", "roboquant"}
)
@SourceDebugExtension({"SMAP\nSearchSpace.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SearchSpace.kt\norg/roboquant/optimize/RandomSearch\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,186:1\n1#2:187\n*E\n"})
public final class RandomSearch implements SearchSpace {
   private final int size;
   @NotNull
   private final LinkedHashMap params;
   @NotNull
   private final List permutations;
   @NotNull
   private final Lazy list$delegate;

   public RandomSearch(int size) {
      this.size = size;
      this.params = new LinkedHashMap();
      this.permutations = (List)(new ArrayList());
      this.list$delegate = LazyKt.lazy(new Function0() {
         @NotNull
         public final List invoke() {
            try {
               RandomSearch.this.calcPermutations();
            } catch (DoneException var2) {
            }

            return RandomSearch.this.permutations;
         }
      });
   }

   public int getSize() {
      return this.size;
   }

   private final List getList() {
      Lazy var1 = this.list$delegate;
      return (List)var1.getValue();
   }

   private final void calcPermutations() {
      int var1 = this.getSize();

      for(int var2 = 0; var2 < var1; ++var2) {
         int var4 = 0;
         Params p = new Params();

         for(Map.Entry var7 : ((Map)this.params).entrySet()) {
            String key = (String)var7.getKey();
            Entry values = (Entry)var7.getValue();
            Object var10000;
            if (values.getList() != null) {
               var10000 = CollectionsKt.random((Collection)values.getList(), (Random)Random.Default);
            } else {
               Function0 var13 = values.getFn();
               if (var13 != null) {
                  Function0 it = var13;
                  int var11 = 0;
                  var10000 = it.invoke();
               } else {
                  var10000 = null;
               }
            }

            Object value = var10000;
            Map var14 = (Map)p;
            Intrinsics.checkNotNull(value);
            var14.put(key, value);
         }

         this.permutations.add(p);
      }

   }

   @NotNull
   public Iterator iterator() {
      return (Iterator)this.getList().listIterator();
   }

   public final void add(@NotNull String name, @NotNull Function0 fn) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(fn, "fn");
      ((Map)this.params).put(name, new Entry((List)null, fn));
   }

   public final void add(@NotNull String name, @NotNull Iterable values) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(values, "values");
      ((Map)this.params).put(name, new Entry(CollectionsKt.toList(values), (Function0)null));
   }

   public void update(@NotNull Params params, double score) {
      SearchSpace.DefaultImpls.update(this, params, score);
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"},
      d2 = {"Lorg/roboquant/optimize/RandomSearch$Entry;", "", "list", "", "fn", "Lkotlin/Function0;", "(Ljava/util/List;Lkotlin/jvm/functions/Function0;)V", "getFn", "()Lkotlin/jvm/functions/Function0;", "getList", "()Ljava/util/List;", "roboquant"}
   )
   private static final class Entry {
      @Nullable
      private final List list;
      @Nullable
      private final Function0 fn;

      public Entry(@Nullable List list, @Nullable Function0 fn) {
         this.list = list;
         this.fn = fn;
      }

      @Nullable
      public final List getList() {
         return this.list;
      }

      @Nullable
      public final Function0 getFn() {
         return this.fn;
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"},
      d2 = {"Lorg/roboquant/optimize/RandomSearch$DoneException;", "", "()V", "roboquant"}
   )
   private static final class DoneException extends Throwable {
      public DoneException() {
      }
   }
}
