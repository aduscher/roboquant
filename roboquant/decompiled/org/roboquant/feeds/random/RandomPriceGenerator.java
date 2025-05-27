package org.roboquant.feeds.random;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SplittableRandom;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Asset;
import org.roboquant.common.PriceBar;
import org.roboquant.common.PriceItem;
import org.roboquant.common.PriceItemType;
import org.roboquant.common.PriceQuote;
import org.roboquant.common.TimeSpan;
import org.roboquant.common.TradePrice;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B;\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\b¢\u0006\u0002\u0010\u000eJ\u0013\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0003H\u0000¢\u0006\u0002\b\u0015J\u0018\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0006H\u0002J\u0018\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0006H\u0002J\u0018\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0006H\u0002J\f\u0010\u001b\u001a\u00020\u0006*\u00020\u0006H\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"},
   d2 = {"Lorg/roboquant/feeds/random/RandomPriceGenerator;", "", "assets", "", "Lorg/roboquant/common/Asset;", "priceChange", "", "volumeRange", "", "timeSpan", "Lorg/roboquant/common/TimeSpan;", "priceType", "Lorg/roboquant/common/PriceItemType;", "seed", "(Ljava/util/List;DILorg/roboquant/common/TimeSpan;Lorg/roboquant/common/PriceItemType;I)V", "prices", "", "random", "Ljava/util/SplittableRandom;", "next", "Lorg/roboquant/common/PriceItem;", "next$roboquant", "priceBar", "asset", "price", "priceQupte", "tradePrice", "nextPrice", "roboquant"}
)
@SourceDebugExtension({"SMAP\nRandomPriceGenerator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RandomPriceGenerator.kt\norg/roboquant/feeds/random/RandomPriceGenerator\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,115:1\n1549#2:116\n1620#2,3:117\n*S KotlinDebug\n*F\n+ 1 RandomPriceGenerator.kt\norg/roboquant/feeds/random/RandomPriceGenerator\n*L\n45#1:116\n45#1:117,3\n*E\n"})
public final class RandomPriceGenerator {
   @NotNull
   private final List assets;
   private final double priceChange;
   private final int volumeRange;
   @NotNull
   private final TimeSpan timeSpan;
   @NotNull
   private final PriceItemType priceType;
   @NotNull
   private final SplittableRandom random;
   @NotNull
   private final List prices;

   public RandomPriceGenerator(@NotNull List assets, double priceChange, int volumeRange, @NotNull TimeSpan timeSpan, @NotNull PriceItemType priceType, int seed) {
      Intrinsics.checkNotNullParameter(assets, "assets");
      Intrinsics.checkNotNullParameter(timeSpan, "timeSpan");
      Intrinsics.checkNotNullParameter(priceType, "priceType");
      super();
      this.assets = assets;
      this.priceChange = priceChange;
      this.volumeRange = volumeRange;
      this.timeSpan = timeSpan;
      this.priceType = priceType;
      this.random = new SplittableRandom((long)seed);
      Iterable $this$map$iv = (Iterable)this.assets;
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      int $i$f$mapTo = 0;

      for(Object item$iv$iv : $this$map$iv) {
         Asset var15 = (Asset)item$iv$iv;
         int var16 = 0;
         destination$iv$iv.add(this.random.nextDouble((double)50.0F, (double)500.0F));
      }

      this.prices = CollectionsKt.toMutableList((Collection)((List)destination$iv$iv));
   }

   private final double nextPrice(double $this$nextPrice) {
      return $this$nextPrice * ((double)1.0F + this.random.nextDouble(-this.priceChange, this.priceChange));
   }

   private final PriceItem priceBar(Asset asset, double price) {
      int volume = 0;

      double[] v;
      for(v = new double[4]; volume < 4; ++volume) {
         v[volume] = this.nextPrice(price);
      }

      ArraysKt.sort(v);
      volume = this.random.nextInt(this.volumeRange / 2, this.volumeRange * 2);
      return this.random.nextBoolean() ? (PriceItem)(new PriceBar(asset, (Number)v[1], (Number)v[3], (Number)v[0], (Number)v[2], (Number)volume, this.timeSpan)) : (PriceItem)(new PriceBar(asset, (Number)v[2], (Number)v[3], (Number)v[0], (Number)v[1], (Number)volume, this.timeSpan));
   }

   private final PriceItem priceQupte(Asset asset, double price) {
      double midPoint = this.nextPrice(price);
      double volume = (double)this.random.nextInt(this.volumeRange / 2, this.volumeRange * 2);
      return new PriceQuote(asset, midPoint * 0.99, volume, midPoint * 1.01, volume);
   }

   private final PriceItem tradePrice(Asset asset, double price) {
      int volume = this.random.nextInt(this.volumeRange / 2, this.volumeRange * 2);
      return new TradePrice(asset, price, (double)volume);
   }

   @NotNull
   public final List next$roboquant() {
      List var1 = CollectionsKt.createListBuilder();
      List $this$next_u24lambda_u241 = var1;
      int var3 = 0;
      Iterator var4 = this.assets.iterator();
      int var5 = 0;

      while(var4.hasNext()) {
         int idx = var5++;
         Asset asset = (Asset)var4.next();
         double lastPrice = ((Number)this.prices.get(idx)).doubleValue();
         double price = RangesKt.coerceAtLeast(this.nextPrice(lastPrice), this.priceChange * (double)2.0F);
         PriceItem var10000;
         switch (RandomPriceGenerator.WhenMappings.$EnumSwitchMapping$0[this.priceType.ordinal()]) {
            case 1 -> var10000 = this.priceBar(asset, price);
            case 2 -> var10000 = this.tradePrice(asset, price);
            case 3 -> var10000 = this.priceQupte(asset, price);
            default -> throw new UnsupportedOperationException("Unknown price type: " + this.priceType);
         }

         PriceItem action = var10000;
         $this$next_u24lambda_u241.add(action);
         this.prices.set(idx, price);
      }

      return CollectionsKt.build(var1);
   }

   // $FF: synthetic class
   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public class WhenMappings {
      // $FF: synthetic field
      public static final int[] $EnumSwitchMapping$0;

      static {
         int[] var0 = new int[PriceItemType.values().length];

         try {
            var0[PriceItemType.BAR.ordinal()] = 1;
         } catch (NoSuchFieldError var4) {
         }

         try {
            var0[PriceItemType.TRADE.ordinal()] = 2;
         } catch (NoSuchFieldError var3) {
         }

         try {
            var0[PriceItemType.QUOTE.ordinal()] = 3;
         } catch (NoSuchFieldError var2) {
         }

         $EnumSwitchMapping$0 = var0;
      }
   }
}
