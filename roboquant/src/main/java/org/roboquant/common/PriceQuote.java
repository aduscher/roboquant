package org.roboquant.common;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\u0010\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000bR\u0011\u0010\u0011\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000b¨\u0006$"},
   d2 = {"Lorg/roboquant/common/PriceQuote;", "Lorg/roboquant/common/PriceItem;", "asset", "Lorg/roboquant/common/Asset;", "askPrice", "", "askSize", "bidPrice", "bidSize", "(Lorg/roboquant/common/Asset;DDDD)V", "getAskPrice", "()D", "getAskSize", "getAsset", "()Lorg/roboquant/common/Asset;", "getBidPrice", "getBidSize", "spread", "getSpread", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "getPrice", "type", "", "getVolume", "hashCode", "", "toString", "roboquant"}
)
public final class PriceQuote implements PriceItem {
   @NotNull
   private final Asset asset;
   private final double askPrice;
   private final double askSize;
   private final double bidPrice;
   private final double bidSize;

   public PriceQuote(@NotNull Asset asset, double askPrice, double askSize, double bidPrice, double bidSize) {
      Intrinsics.checkNotNullParameter(asset, "asset");
      super();
      this.asset = asset;
      this.askPrice = askPrice;
      this.askSize = askSize;
      this.bidPrice = bidPrice;
      this.bidSize = bidSize;
   }

   @NotNull
   public Asset getAsset() {
      return this.asset;
   }

   public final double getAskPrice() {
      return this.askPrice;
   }

   public final double getAskSize() {
      return this.askSize;
   }

   public final double getBidPrice() {
      return this.bidPrice;
   }

   public final double getBidSize() {
      return this.bidSize;
   }

   public double getPrice(@NotNull String type) {
      double var10000;
      label23: {
         Intrinsics.checkNotNullParameter(type, "type");
         switch (type) {
            case "ASK":
               var10000 = this.askPrice;
               break label23;
            case "BID":
               var10000 = this.bidPrice;
               break label23;
            case "WEIGHTED":
               var10000 = (this.askPrice * this.askSize + this.bidPrice * this.bidSize) / (this.askSize + this.bidSize);
               break label23;
         }

         var10000 = (this.askPrice + this.bidPrice) / (double)2;
      }

      double result = var10000;
      return result;
   }

   public double getVolume(@NotNull String type) {
      Intrinsics.checkNotNullParameter(type, "type");
      return Intrinsics.areEqual(type, "ASK") ? this.askSize : (Intrinsics.areEqual(type, "BID") ? this.bidSize : (Math.abs(this.askSize) + Math.abs(this.bidSize)) / (double)2);
   }

   public final double getSpread() {
      return (this.askPrice - this.bidPrice) / this.askPrice;
   }

   @NotNull
   public Amount getPriceAmount(@NotNull String type) {
      return PriceItem.DefaultImpls.getPriceAmount(this, type);
   }

   @NotNull
   public final Asset component1() {
      return this.asset;
   }

   public final double component2() {
      return this.askPrice;
   }

   public final double component3() {
      return this.askSize;
   }

   public final double component4() {
      return this.bidPrice;
   }

   public final double component5() {
      return this.bidSize;
   }

   @NotNull
   public final PriceQuote copy(@NotNull Asset asset, double askPrice, double askSize, double bidPrice, double bidSize) {
      Intrinsics.checkNotNullParameter(asset, "asset");
      return new PriceQuote(asset, askPrice, askSize, bidPrice, bidSize);
   }

   // $FF: synthetic method
   public static PriceQuote copy$default(PriceQuote var0, Asset var1, double var2, double var4, double var6, double var8, int var10, Object var11) {
      if ((var10 & 1) != 0) {
         var1 = var0.asset;
      }

      if ((var10 & 2) != 0) {
         var2 = var0.askPrice;
      }

      if ((var10 & 4) != 0) {
         var4 = var0.askSize;
      }

      if ((var10 & 8) != 0) {
         var6 = var0.bidPrice;
      }

      if ((var10 & 16) != 0) {
         var8 = var0.bidSize;
      }

      return var0.copy(var1, var2, var4, var6, var8);
   }

   @NotNull
   public String toString() {
      return "PriceQuote(asset=" + this.asset + ", askPrice=" + this.askPrice + ", askSize=" + this.askSize + ", bidPrice=" + this.bidPrice + ", bidSize=" + this.bidSize + ")";
   }

   public int hashCode() {
      int result = this.asset.hashCode();
      result = result * 31 + Double.hashCode(this.askPrice);
      result = result * 31 + Double.hashCode(this.askSize);
      result = result * 31 + Double.hashCode(this.bidPrice);
      result = result * 31 + Double.hashCode(this.bidSize);
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof PriceQuote)) {
         return false;
      } else {
         PriceQuote var2 = (PriceQuote)other;
         if (!Intrinsics.areEqual(this.asset, var2.asset)) {
            return false;
         } else if (Double.compare(this.askPrice, var2.askPrice) != 0) {
            return false;
         } else if (Double.compare(this.askSize, var2.askSize) != 0) {
            return false;
         } else if (Double.compare(this.bidPrice, var2.bidPrice) != 0) {
            return false;
         } else {
            return Double.compare(this.bidSize, var2.bidSize) == 0;
         }
      }
   }
}
