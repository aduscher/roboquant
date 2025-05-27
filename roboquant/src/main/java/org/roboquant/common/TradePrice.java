package org.roboquant.common;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\f\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0016HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u001a"},
   d2 = {"Lorg/roboquant/common/TradePrice;", "Lorg/roboquant/common/PriceItem;", "asset", "Lorg/roboquant/common/Asset;", "price", "", "volume", "(Lorg/roboquant/common/Asset;DD)V", "getAsset", "()Lorg/roboquant/common/Asset;", "getPrice", "()D", "getVolume", "component1", "component2", "component3", "copy", "equals", "", "other", "", "type", "", "hashCode", "", "toString", "roboquant"}
)
public final class TradePrice implements PriceItem {
   @NotNull
   private final Asset asset;
   private final double price;
   private final double volume;

   public TradePrice(@NotNull Asset asset, double price, double volume) {
      Intrinsics.checkNotNullParameter(asset, "asset");
      super();
      this.asset = asset;
      this.price = price;
      this.volume = volume;
   }

   // $FF: synthetic method
   public TradePrice(Asset var1, double var2, double var4, int var6, DefaultConstructorMarker var7) {
      if ((var6 & 4) != 0) {
         var4 = Double.NaN;
      }

      this(var1, var2, var4);
   }

   @NotNull
   public Asset getAsset() {
      return this.asset;
   }

   public final double getPrice() {
      return this.price;
   }

   public final double getVolume() {
      return this.volume;
   }

   public double getPrice(@NotNull String type) {
      Intrinsics.checkNotNullParameter(type, "type");
      return this.price;
   }

   public double getVolume(@NotNull String type) {
      Intrinsics.checkNotNullParameter(type, "type");
      return this.volume;
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
      return this.price;
   }

   public final double component3() {
      return this.volume;
   }

   @NotNull
   public final TradePrice copy(@NotNull Asset asset, double price, double volume) {
      Intrinsics.checkNotNullParameter(asset, "asset");
      return new TradePrice(asset, price, volume);
   }

   // $FF: synthetic method
   public static TradePrice copy$default(TradePrice var0, Asset var1, double var2, double var4, int var6, Object var7) {
      if ((var6 & 1) != 0) {
         var1 = var0.asset;
      }

      if ((var6 & 2) != 0) {
         var2 = var0.price;
      }

      if ((var6 & 4) != 0) {
         var4 = var0.volume;
      }

      return var0.copy(var1, var2, var4);
   }

   @NotNull
   public String toString() {
      return "TradePrice(asset=" + this.asset + ", price=" + this.price + ", volume=" + this.volume + ")";
   }

   public int hashCode() {
      int result = this.asset.hashCode();
      result = result * 31 + Double.hashCode(this.price);
      result = result * 31 + Double.hashCode(this.volume);
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof TradePrice)) {
         return false;
      } else {
         TradePrice var2 = (TradePrice)other;
         if (!Intrinsics.areEqual(this.asset, var2.asset)) {
            return false;
         } else if (Double.compare(this.price, var2.price) != 0) {
            return false;
         } else {
            return Double.compare(this.volume, var2.volume) == 0;
         }
      }
   }
}
