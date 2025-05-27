package org.roboquant.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\u0006\u0010\u0007\u001a\u00020\b\u001a\u0018\u0010\t\u001a\u00020\u0004*\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\n\u001a\u00020\u0002\"!\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00040\u00038F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"},
   d2 = {"symbols", "", "", "", "Lorg/roboquant/common/Asset;", "getSymbols", "(Ljava/util/Collection;)[Ljava/lang/String;", "main", "", "getBySymbol", "symbol", "roboquant"}
)
@SourceDebugExtension({"SMAP\nAsset.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Asset.kt\norg/roboquant/common/AssetKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,186:1\n223#2,2:187\n1549#2:189\n1620#2,3:190\n37#3,2:193\n*S KotlinDebug\n*F\n+ 1 Asset.kt\norg/roboquant/common/AssetKt\n*L\n168#1:187,2\n174#1:189\n174#1:190,3\n174#1:193,2\n*E\n"})
public final class AssetKt {
   @NotNull
   public static final Asset getBySymbol(@NotNull Collection $this$getBySymbol, @NotNull String symbol) {
      Intrinsics.checkNotNullParameter($this$getBySymbol, "<this>");
      Intrinsics.checkNotNullParameter(symbol, "symbol");
      Iterable $this$first$iv = (Iterable)$this$getBySymbol;
      int $i$f$first = 0;

      for(Object element$iv : $this$first$iv) {
         Asset it = (Asset)element$iv;
         int var7 = 0;
         if (Intrinsics.areEqual(it.getSymbol(), symbol)) {
            return (Asset)element$iv;
         }
      }

      throw new NoSuchElementException("Collection contains no element matching the predicate.");
   }

   @NotNull
   public static final String[] getSymbols(@NotNull Collection $this$symbols) {
      Intrinsics.checkNotNullParameter($this$symbols, "<this>");
      Iterable $this$map$iv = (Iterable)$this$symbols;
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      int $i$f$mapTo = 0;

      for(Object item$iv$iv : $this$map$iv) {
         Asset it = (Asset)item$iv$iv;
         int var9 = 0;
         destination$iv$iv.add(it.getSymbol());
      }

      Collection $this$toTypedArray$iv = (Collection)CollectionsKt.distinct((Iterable)((List)destination$iv$iv));
      $i$f$map = 0;
      return (String[])$this$toTypedArray$iv.toArray(new String[0]);
   }

   public static final void main() {
      Stock apple = new Stock("AAPL", (Currency)null, 2, (DefaultConstructorMarker)null);
      String appleSer = apple.serialize();
      System.out.println(Asset.Companion.deserialize(appleSer));
      Stock abn = new Stock("ABNA", Currency.Companion.getEUR());
      System.out.println(abn.serialize());
   }

   // $FF: synthetic method
   public static void main(String[] args) {
      main();
   }
}
