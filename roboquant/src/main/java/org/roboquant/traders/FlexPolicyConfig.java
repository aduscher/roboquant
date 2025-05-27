package org.roboquant.traders;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Amount;
import org.roboquant.common.ExtensionsKt;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001a\b\u0016\u0018\u00002\u00020\u0001BM\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\n\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010\u000b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001c\"\u0004\b$\u0010\u001eR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0018\"\u0004\b&\u0010\u001a¨\u0006'"},
   d2 = {"Lorg/roboquant/traders/FlexPolicyConfig;", "", "orderPercentage", "", "shorting", "", "priceType", "", "fractions", "", "oneOrderOnly", "safetyMargin", "minPrice", "Lorg/roboquant/common/Amount;", "(DZLjava/lang/String;IZDLorg/roboquant/common/Amount;)V", "getFractions", "()I", "setFractions", "(I)V", "getMinPrice", "()Lorg/roboquant/common/Amount;", "setMinPrice", "(Lorg/roboquant/common/Amount;)V", "getOneOrderOnly", "()Z", "setOneOrderOnly", "(Z)V", "getOrderPercentage", "()D", "setOrderPercentage", "(D)V", "getPriceType", "()Ljava/lang/String;", "setPriceType", "(Ljava/lang/String;)V", "getSafetyMargin", "setSafetyMargin", "getShorting", "setShorting", "roboquant"}
)
public class FlexPolicyConfig {
   private double orderPercentage;
   private boolean shorting;
   @NotNull
   private String priceType;
   private int fractions;
   private boolean oneOrderOnly;
   private double safetyMargin;
   @Nullable
   private Amount minPrice;

   public FlexPolicyConfig(double orderPercentage, boolean shorting, @NotNull String priceType, int fractions, boolean oneOrderOnly, double safetyMargin, @Nullable Amount minPrice) {
      Intrinsics.checkNotNullParameter(priceType, "priceType");
      super();
      this.orderPercentage = orderPercentage;
      this.shorting = shorting;
      this.priceType = priceType;
      this.fractions = fractions;
      this.oneOrderOnly = oneOrderOnly;
      this.safetyMargin = safetyMargin;
      this.minPrice = minPrice;
   }

   // $FF: synthetic method
   public FlexPolicyConfig(double var1, boolean var3, String var4, int var5, boolean var6, double var7, Amount var9, int var10, DefaultConstructorMarker var11) {
      if ((var10 & 1) != 0) {
         var1 = ExtensionsKt.getPercent((Number)1);
      }

      if ((var10 & 2) != 0) {
         var3 = false;
      }

      if ((var10 & 4) != 0) {
         var4 = "DEFAULT";
      }

      if ((var10 & 8) != 0) {
         var5 = 0;
      }

      if ((var10 & 16) != 0) {
         var6 = true;
      }

      if ((var10 & 32) != 0) {
         var7 = var1;
      }

      if ((var10 & 64) != 0) {
         var9 = null;
      }

      this(var1, var3, var4, var5, var6, var7, var9);
   }

   public final double getOrderPercentage() {
      return this.orderPercentage;
   }

   public final void setOrderPercentage(double var1) {
      this.orderPercentage = var1;
   }

   public final boolean getShorting() {
      return this.shorting;
   }

   public final void setShorting(boolean var1) {
      this.shorting = var1;
   }

   @NotNull
   public final String getPriceType() {
      return this.priceType;
   }

   public final void setPriceType(@NotNull String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.priceType = var1;
   }

   public final int getFractions() {
      return this.fractions;
   }

   public final void setFractions(int var1) {
      this.fractions = var1;
   }

   public final boolean getOneOrderOnly() {
      return this.oneOrderOnly;
   }

   public final void setOneOrderOnly(boolean var1) {
      this.oneOrderOnly = var1;
   }

   public final double getSafetyMargin() {
      return this.safetyMargin;
   }

   public final void setSafetyMargin(double var1) {
      this.safetyMargin = var1;
   }

   @Nullable
   public final Amount getMinPrice() {
      return this.minPrice;
   }

   public final void setMinPrice(@Nullable Amount var1) {
      this.minPrice = var1;
   }

   public FlexPolicyConfig() {
      this((double)0.0F, false, (String)null, 0, false, (double)0.0F, (Amount)null, 127, (DefaultConstructorMarker)null);
   }
}
