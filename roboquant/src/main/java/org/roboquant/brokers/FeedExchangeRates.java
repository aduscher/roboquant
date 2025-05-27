package org.roboquant.brokers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Amount;
import org.roboquant.common.Asset;
import org.roboquant.common.ConfigurationException;
import org.roboquant.common.Currency;
import org.roboquant.common.ExtensionsKt;
import org.roboquant.common.Logging;
import org.roboquant.common.PriceItem;
import org.roboquant.common.Timeframe;
import org.roboquant.feeds.Feed;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J+\u0010\u0014\u001a\u0004\u0018\u00010\u00112\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u000e2\u0006\u0010\u0016\u001a\u00020\u0010H\u0002¢\u0006\u0002\u0010\u0017J \u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0010H\u0016J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR2\u0010\f\u001a&\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u000e\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000f0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"},
   d2 = {"Lorg/roboquant/brokers/FeedExchangeRates;", "Lorg/roboquant/brokers/ExchangeRates;", "feed", "Lorg/roboquant/feeds/Feed;", "priceType", "", "(Lorg/roboquant/feeds/Feed;Ljava/lang/String;)V", "currencies", "", "Lorg/roboquant/common/Currency;", "getCurrencies", "()Ljava/util/Set;", "exchangeRates", "", "Lkotlin/Pair;", "Ljava/util/NavigableMap;", "Ljava/time/Instant;", "", "logger", "Lorg/roboquant/common/Logging$Logger;", "find", "pair", "time", "(Lkotlin/Pair;Ljava/time/Instant;)Ljava/lang/Double;", "getRate", "amount", "Lorg/roboquant/common/Amount;", "to", "setRates", "", "roboquant"}
)
@SourceDebugExtension({"SMAP\nFeedExchangeRates.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FeedExchangeRates.kt\norg/roboquant/brokers/FeedExchangeRates\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Feed.kt\norg/roboquant/feeds/FeedKt\n+ 4 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 5 Logging.kt\norg/roboquant/common/Logging$Logger\n*L\n1#1,91:1\n1549#2:92\n1620#2,3:93\n101#3,4:96\n124#3:100\n372#4,7:101\n59#5,3:108\n*S KotlinDebug\n*F\n+ 1 FeedExchangeRates.kt\norg/roboquant/brokers/FeedExchangeRates\n*L\n46#1:92\n46#1:93,3\n54#1:96,4\n54#1:100\n60#1:101,7\n63#1:108,3\n*E\n"})
public final class FeedExchangeRates implements ExchangeRates {
   @NotNull
   private final String priceType;
   @NotNull
   private final Map exchangeRates;
   @NotNull
   private final Logging.Logger logger;

   public FeedExchangeRates(@NotNull Feed feed, @NotNull String priceType) {
      Intrinsics.checkNotNullParameter(feed, "feed");
      Intrinsics.checkNotNullParameter(priceType, "priceType");
      super();
      this.priceType = priceType;
      this.exchangeRates = (Map)(new LinkedHashMap());
      this.logger = Logging.INSTANCE.getLogger(Reflection.getOrCreateKotlinClass(this.getClass()));
      this.setRates(feed);
   }

   // $FF: synthetic method
   public FeedExchangeRates(Feed var1, String var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = "DEFAULT";
      }

      this(var1, var2);
   }

   @NotNull
   public final Set getCurrencies() {
      Iterable $this$map$iv = (Iterable)this.exchangeRates.keySet();
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
      int $i$f$mapTo = 0;

      for(Object item$iv$iv : $this$map$iv) {
         Pair it = (Pair)item$iv$iv;
         int var9 = 0;
         Currency[] var10 = new Currency[]{(Currency)it.getFirst(), (Currency)it.getSecond()};
         destination$iv$iv.add(CollectionsKt.listOf(var10));
      }

      return CollectionsKt.toSet((Iterable)CollectionsKt.flatten((Iterable)((List)destination$iv$iv)));
   }

   private final void setRates(Feed feed) {
      Timeframe timeframe$iv = Timeframe.Companion.getINFINITE();
      int $i$f$filter = 0;

      for(Pair var17 : (List)BuildersKt.runBlocking$default((CoroutineContext)null, new FeedExchangeRates$setRates$$inlined$filter$default$1(timeframe$iv, feed, (Continuation)null), 1, (Object)null)) {
         Instant now = (Instant)var17.component1();
         PriceItem action = (PriceItem)var17.component2();
         Asset asset = action.getAsset();
         double rate = action.getPrice(this.priceType);
         Pair pair = ExtensionsKt.toCurrencyPair(asset.getSymbol());
         if (pair != null) {
            Map $this$getOrPut$iv = this.exchangeRates;
            int $i$f$getOrPut = 0;
            Object value$iv = $this$getOrPut$iv.get(pair);
            Object var10000;
            if (value$iv == null) {
               int var15 = 0;
               Object answer$iv = (NavigableMap)(new TreeMap());
               $this$getOrPut$iv.put(pair, answer$iv);
               var10000 = answer$iv;
            } else {
               var10000 = value$iv;
            }

            NavigableMap map = (NavigableMap)var10000;
            Double $i$f$warn = rate;
            ((Map)map).put(now, $i$f$warn);
         } else {
            Logging.Logger $this$iv = this.logger;
            Throwable throwable$iv = null;
            int $i$f$warn = 0;
            if ($this$iv.isWarnEnabled()) {
               int var23 = 0;
               $this$iv.warn("could map asset to currency pair " + asset, throwable$iv);
            }
         }
      }

   }

   private final Double find(Pair pair, Instant time) {
      NavigableMap rates = (NavigableMap)this.exchangeRates.get(pair);
      Double var5;
      if (rates != null) {
         Map.Entry var10000 = rates.floorEntry(time);
         if (var10000 == null) {
            var10000 = rates.firstEntry();
         }

         Map.Entry result = var10000;
         var5 = (Double)result.getValue();
      } else {
         var5 = null;
      }

      return var5;
   }

   public double getRate(@NotNull Amount amount, @NotNull Currency to, @NotNull Instant time) {
      Intrinsics.checkNotNullParameter(amount, "amount");
      Intrinsics.checkNotNullParameter(to, "to");
      Intrinsics.checkNotNullParameter(time, "time");
      Currency from = amount.getCurrency();
      if (from != to && amount.getValue() != (double)0.0F) {
         Double result = this.find(new Pair(amount.getCurrency(), to), time);
         if (result != null) {
            return result;
         } else {
            result = this.find(new Pair(amount.getCurrency(), to), time);
            if (result != null) {
               return (double)1.0F / result;
            } else {
               throw new ConfigurationException("No conversion for " + amount + " to " + to);
            }
         }
      } else {
         return (double)1.0F;
      }
   }

   @NotNull
   public Amount convert(@NotNull Amount amount, @NotNull Currency to, @NotNull Instant time) {
      return ExchangeRates.DefaultImpls.convert(this, amount, to, time);
   }
}
