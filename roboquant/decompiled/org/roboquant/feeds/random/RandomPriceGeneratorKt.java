package org.roboquant.feeds.random;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.CharRange;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Config;
import org.roboquant.common.Currency;
import org.roboquant.common.Stock;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\u001e\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00012\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004H\u0002¨\u0006\t"},
   d2 = {"randomAssets", "", "Lorg/roboquant/common/Asset;", "nAssets", "", "randomNames", "", "size", "len", "roboquant"}
)
@SourceDebugExtension({"SMAP\nRandomPriceGenerator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RandomPriceGenerator.kt\norg/roboquant/feeds/random/RandomPriceGeneratorKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,115:1\n1#2:116\n1549#3:117\n1620#3,3:118\n*S KotlinDebug\n*F\n+ 1 RandomPriceGenerator.kt\norg/roboquant/feeds/random/RandomPriceGeneratorKt\n*L\n110#1:117\n110#1:118,3\n*E\n"})
public final class RandomPriceGeneratorKt {
   private static final Set randomNames(int size, int len) {
      Set result = (Set)(new LinkedHashSet());
      CharRange charPool = new CharRange('A', 'Z');

      while(result.size() < size) {
         ArrayList var5 = new ArrayList(len);

         for(int var6 = 0; var6 < len; ++var6) {
            int var9 = 0;
            var5.add(RangesKt.random(charPool, Config.INSTANCE.getRandom()));
         }

         String name = CollectionsKt.joinToString$default((Iterable)((List)var5), (CharSequence)"", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null);
         result.add(name);
      }

      return result;
   }

   @NotNull
   public static final Set randomAssets(int nAssets) {
      Set uniqueNames = randomNames(nAssets, 5);
      Iterable $this$map$iv = (Iterable)uniqueNames;
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      int $i$f$mapTo = 0;

      for(Object item$iv$iv : $this$map$iv) {
         String it = (String)item$iv$iv;
         int var11 = 0;
         destination$iv$iv.add(new Stock(it, Currency.Companion.getUSD()));
      }

      List assets = (List)destination$iv$iv;
      return CollectionsKt.toSet((Iterable)assets);
   }
}
