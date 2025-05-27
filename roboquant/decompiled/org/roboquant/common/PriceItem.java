package org.roboquant.common;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH&J\u0012\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\b\u001a\u00020\tH\u0016J\u0012\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\r"},
   d2 = {"Lorg/roboquant/common/PriceItem;", "Lorg/roboquant/common/Item;", "asset", "Lorg/roboquant/common/Asset;", "getAsset", "()Lorg/roboquant/common/Asset;", "getPrice", "", "type", "", "getPriceAmount", "Lorg/roboquant/common/Amount;", "getVolume", "roboquant"}
)
public interface PriceItem extends Item {
   @NotNull
   Asset getAsset();

   double getPrice(@NotNull String var1);

   @NotNull
   Amount getPriceAmount(@NotNull String var1);

   double getVolume(@NotNull String var1);

   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      // $FF: synthetic method
      public static double getPrice$default(PriceItem var0, String var1, int var2, Object var3) {
         if (var3 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getPrice");
         } else {
            if ((var2 & 1) != 0) {
               var1 = "DEFAULT";
            }

            return var0.getPrice(var1);
         }
      }

      @NotNull
      public static Amount getPriceAmount(@NotNull PriceItem $this, @NotNull String type) {
         Intrinsics.checkNotNullParameter(type, "type");
         return new Amount($this.getAsset().getCurrency(), $this.getPrice(type));
      }

      // $FF: synthetic method
      public static Amount getPriceAmount$default(PriceItem var0, String var1, int var2, Object var3) {
         if (var3 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getPriceAmount");
         } else {
            if ((var2 & 1) != 0) {
               var1 = "DEFAULT";
            }

            return var0.getPriceAmount(var1);
         }
      }

      // $FF: synthetic method
      public static double getVolume$default(PriceItem var0, String var1, int var2, Object var3) {
         if (var3 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getVolume");
         } else {
            if ((var2 & 1) != 0) {
               var1 = "DEFAULT";
            }

            return var0.getVolume(var1);
         }
      }
   }
}
