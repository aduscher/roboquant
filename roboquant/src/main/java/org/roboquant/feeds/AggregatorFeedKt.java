package org.roboquant.feeds;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Item;
import org.roboquant.common.OrderBook;
import org.roboquant.common.PriceBar;
import org.roboquant.common.PriceItem;
import org.roboquant.common.PriceQuote;
import org.roboquant.common.TimeSpan;
import org.roboquant.common.TradePrice;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0000¨\u0006\u0006"},
   d2 = {"getPriceBar", "Lorg/roboquant/common/PriceBar;", "item", "Lorg/roboquant/common/Item;", "timeSpan", "Lorg/roboquant/common/TimeSpan;", "roboquant"}
)
public final class AggregatorFeedKt {
   @Nullable
   public static final PriceBar getPriceBar(@NotNull Item item, @Nullable TimeSpan timeSpan) {
      Intrinsics.checkNotNullParameter(item, "item");
      PriceBar var10000;
      if (item instanceof PriceBar $this$getPriceBar_u24lambda_u240) {
         int var5 = 0;
         var10000 = new PriceBar(((PriceBar)item).getAsset(), (Number)$this$getPriceBar_u24lambda_u240.getOpen(), (Number)$this$getPriceBar_u24lambda_u240.getHigh(), (Number)$this$getPriceBar_u24lambda_u240.getLow(), (Number)$this$getPriceBar_u24lambda_u240.getClose(), (Number)$this$getPriceBar_u24lambda_u240.getVolume(), timeSpan);
      } else if (item instanceof TradePrice) {
         double price = ((TradePrice)item).getPrice();
         double volume = PriceItem.DefaultImpls.getVolume$default((PriceItem)item, (String)null, 1, (Object)null);
         var10000 = new PriceBar(((TradePrice)item).getAsset(), (Number)price, (Number)price, (Number)price, (Number)price, (Number)volume, timeSpan);
      } else if (item instanceof PriceQuote) {
         double price = ((PriceQuote)item).getPrice("MIDPOINT");
         double volume = PriceItem.DefaultImpls.getVolume$default((PriceItem)item, (String)null, 1, (Object)null);
         var10000 = new PriceBar(((PriceQuote)item).getAsset(), (Number)price, (Number)price, (Number)price, (Number)price, (Number)volume, timeSpan);
      } else if (item instanceof OrderBook) {
         double price = ((OrderBook)item).getPrice("MIDPOINT");
         double volume = PriceItem.DefaultImpls.getVolume$default((PriceItem)item, (String)null, 1, (Object)null);
         var10000 = new PriceBar(((OrderBook)item).getAsset(), (Number)price, (Number)price, (Number)price, (Number)price, (Number)volume, timeSpan);
      } else {
         var10000 = null;
      }

      return var10000;
   }
}
