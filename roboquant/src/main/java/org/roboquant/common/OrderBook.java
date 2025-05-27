package org.roboquant.common;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001:\u0001+B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J3\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!HÖ\u0003J\u0010\u0010\"\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020$H\u0016J\t\u0010&\u001a\u00020\u0015HÖ\u0001J\t\u0010'\u001a\u00020$HÖ\u0001J\u0012\u0010(\u001a\u00020\u000e*\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\u0012\u0010)\u001a\u00020\u000e*\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\u0012\u0010*\u001a\u00020\u000e*\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\nR\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0010¨\u0006,"},
   d2 = {"Lorg/roboquant/common/OrderBook;", "Lorg/roboquant/common/PriceItem;", "asset", "Lorg/roboquant/common/Asset;", "asks", "", "Lorg/roboquant/common/OrderBook$OrderBookEntry;", "bids", "(Lorg/roboquant/common/Asset;Ljava/util/List;Ljava/util/List;)V", "getAsks", "()Ljava/util/List;", "getAsset", "()Lorg/roboquant/common/Asset;", "bestBid", "", "getBestBid", "()D", "bestOffer", "getBestOffer", "getBids", "entries", "", "getEntries", "()I", "spread", "getSpread", "component1", "component2", "component3", "copy", "equals", "", "other", "", "getPrice", "type", "", "getVolume", "hashCode", "toString", "max", "min", "volume", "OrderBookEntry", "roboquant"}
)
@SourceDebugExtension({"SMAP\nItem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Item.kt\norg/roboquant/common/OrderBook\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,410:1\n1#2:411\n*E\n"})
public final class OrderBook implements PriceItem {
   @NotNull
   private final Asset asset;
   @NotNull
   private final List asks;
   @NotNull
   private final List bids;

   public OrderBook(@NotNull Asset asset, @NotNull List asks, @NotNull List bids) {
      Intrinsics.checkNotNullParameter(asset, "asset");
      Intrinsics.checkNotNullParameter(asks, "asks");
      Intrinsics.checkNotNullParameter(bids, "bids");
      super();
      this.asset = asset;
      this.asks = asks;
      this.bids = bids;
   }

   @NotNull
   public Asset getAsset() {
      return this.asset;
   }

   @NotNull
   public final List getAsks() {
      return this.asks;
   }

   @NotNull
   public final List getBids() {
      return this.bids;
   }

   public final int getEntries() {
      return this.asks.size() + this.bids.size();
   }

   public double getPrice(@NotNull String type) {
      Intrinsics.checkNotNullParameter(type, "type");
      double var10000;
      switch (type) {
         case "ASK":
            var10000 = this.getBestOffer();
            return var10000;
         case "BID":
            var10000 = this.getBestBid();
            return var10000;
         case "WEIGHTED":
            double askVolume = this.volume(this.asks);
            double bidVolume = this.volume(this.bids);
            var10000 = (this.min(this.asks) * askVolume + this.max(this.bids) * bidVolume) / (askVolume + bidVolume);
            return var10000;
      }

      var10000 = (this.getBestBid() + this.getBestOffer()) / (double)2.0F;
      return var10000;
   }

   public final double getBestBid() {
      return this.max(this.bids);
   }

   public final double getBestOffer() {
      return this.min(this.asks);
   }

   public final double getSpread() {
      double ask = this.getPrice("ASK");
      double bid = this.getPrice("BID");
      return (ask - bid) / ask;
   }

   public double getVolume(@NotNull String type) {
      Intrinsics.checkNotNullParameter(type, "type");
      return this.volume(this.asks) + this.volume(this.bids);
   }

   private final double volume(List $this$volume) {
      Iterable var2 = (Iterable)$this$volume;
      double var3 = (double)0.0F;

      for(Object var6 : var2) {
         OrderBookEntry it = (OrderBookEntry)var6;
         int var8 = 0;
         double var11 = Math.abs(it.getSize());
         var3 += var11;
      }

      return var3;
   }

   private final double max(List $this$max) {
      Iterator var2 = ((Iterable)$this$max).iterator();
      if (!var2.hasNext()) {
         throw new NoSuchElementException();
      } else {
         OrderBookEntry it = (OrderBookEntry)var2.next();
         int var4 = 0;

         double var5;
         double var9;
         for(var5 = it.getLimit(); var2.hasNext(); var5 = Math.max(var5, var9)) {
            OrderBookEntry it = (OrderBookEntry)var2.next();
            int var8 = 0;
            var9 = it.getLimit();
         }

         return var5;
      }
   }

   private final double min(List $this$min) {
      Iterator var2 = ((Iterable)$this$min).iterator();
      if (!var2.hasNext()) {
         throw new NoSuchElementException();
      } else {
         OrderBookEntry it = (OrderBookEntry)var2.next();
         int var4 = 0;

         double var5;
         double var9;
         for(var5 = it.getLimit(); var2.hasNext(); var5 = Math.min(var5, var9)) {
            OrderBookEntry it = (OrderBookEntry)var2.next();
            int var8 = 0;
            var9 = it.getLimit();
         }

         return var5;
      }
   }

   @NotNull
   public Amount getPriceAmount(@NotNull String type) {
      return PriceItem.DefaultImpls.getPriceAmount(this, type);
   }

   @NotNull
   public final Asset component1() {
      return this.asset;
   }

   @NotNull
   public final List component2() {
      return this.asks;
   }

   @NotNull
   public final List component3() {
      return this.bids;
   }

   @NotNull
   public final OrderBook copy(@NotNull Asset asset, @NotNull List asks, @NotNull List bids) {
      Intrinsics.checkNotNullParameter(asset, "asset");
      Intrinsics.checkNotNullParameter(asks, "asks");
      Intrinsics.checkNotNullParameter(bids, "bids");
      return new OrderBook(asset, asks, bids);
   }

   // $FF: synthetic method
   public static OrderBook copy$default(OrderBook var0, Asset var1, List var2, List var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = var0.asset;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.asks;
      }

      if ((var4 & 4) != 0) {
         var3 = var0.bids;
      }

      return var0.copy(var1, var2, var3);
   }

   @NotNull
   public String toString() {
      return "OrderBook(asset=" + this.asset + ", asks=" + this.asks + ", bids=" + this.bids + ")";
   }

   public int hashCode() {
      int result = this.asset.hashCode();
      result = result * 31 + this.asks.hashCode();
      result = result * 31 + this.bids.hashCode();
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof OrderBook)) {
         return false;
      } else {
         OrderBook var2 = (OrderBook)other;
         if (!Intrinsics.areEqual(this.asset, var2.asset)) {
            return false;
         } else if (!Intrinsics.areEqual(this.asks, var2.asks)) {
            return false;
         } else {
            return Intrinsics.areEqual(this.bids, var2.bids);
         }
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"},
      d2 = {"Lorg/roboquant/common/OrderBook$OrderBookEntry;", "", "size", "", "limit", "(DD)V", "getLimit", "()D", "getSize", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "roboquant"}
   )
   public static final class OrderBookEntry {
      private final double size;
      private final double limit;

      public OrderBookEntry(double size, double limit) {
         this.size = size;
         this.limit = limit;
      }

      public final double getSize() {
         return this.size;
      }

      public final double getLimit() {
         return this.limit;
      }

      public final double component1() {
         return this.size;
      }

      public final double component2() {
         return this.limit;
      }

      @NotNull
      public final OrderBookEntry copy(double size, double limit) {
         return new OrderBookEntry(size, limit);
      }

      // $FF: synthetic method
      public static OrderBookEntry copy$default(OrderBookEntry var0, double var1, double var3, int var5, Object var6) {
         if ((var5 & 1) != 0) {
            var1 = var0.size;
         }

         if ((var5 & 2) != 0) {
            var3 = var0.limit;
         }

         return var0.copy(var1, var3);
      }

      @NotNull
      public String toString() {
         return "OrderBookEntry(size=" + this.size + ", limit=" + this.limit + ")";
      }

      public int hashCode() {
         int result = Double.hashCode(this.size);
         result = result * 31 + Double.hashCode(this.limit);
         return result;
      }

      public boolean equals(@Nullable Object other) {
         if (this == other) {
            return true;
         } else if (!(other instanceof OrderBookEntry)) {
            return false;
         } else {
            OrderBookEntry var2 = (OrderBookEntry)other;
            if (Double.compare(this.size, var2.size) != 0) {
               return false;
            } else {
               return Double.compare(this.limit, var2.limit) == 0;
            }
         }
      }
   }
}
