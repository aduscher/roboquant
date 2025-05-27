package org.roboquant.common;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0013\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001BE\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fB!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\u000fJ\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0005J\u0010\u0010 \u001a\u00020\u00122\u0006\u0010!\u001a\u00020\"H\u0016J\u0010\u0010\u001c\u001a\u00020\u00122\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\"H\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0014R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\t\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0014¨\u0006$"},
   d2 = {"Lorg/roboquant/common/PriceBar;", "Lorg/roboquant/common/PriceItem;", "asset", "Lorg/roboquant/common/Asset;", "open", "", "high", "low", "close", "volume", "timeSpan", "Lorg/roboquant/common/TimeSpan;", "(Lorg/roboquant/common/Asset;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;Ljava/lang/Number;Lorg/roboquant/common/TimeSpan;)V", "ohlcv", "", "(Lorg/roboquant/common/Asset;[DLorg/roboquant/common/TimeSpan;)V", "getAsset", "()Lorg/roboquant/common/Asset;", "", "getClose", "()D", "getHigh", "getLow", "getOhlcv", "()[D", "getOpen", "getTimeSpan", "()Lorg/roboquant/common/TimeSpan;", "getVolume", "adjustClose", "", "price", "getPrice", "type", "", "toString", "roboquant"}
)
public final class PriceBar implements PriceItem {
   @NotNull
   private final Asset asset;
   @NotNull
   private final double[] ohlcv;
   @Nullable
   private final TimeSpan timeSpan;

   public PriceBar(@NotNull Asset asset, @NotNull double[] ohlcv, @Nullable TimeSpan timeSpan) {
      Intrinsics.checkNotNullParameter(asset, "asset");
      Intrinsics.checkNotNullParameter(ohlcv, "ohlcv");
      super();
      this.asset = asset;
      this.ohlcv = ohlcv;
      this.timeSpan = timeSpan;
   }

   // $FF: synthetic method
   public PriceBar(Asset var1, double[] var2, TimeSpan var3, int var4, DefaultConstructorMarker var5) {
      if ((var4 & 4) != 0) {
         var3 = null;
      }

      this(var1, var2, var3);
   }

   @NotNull
   public Asset getAsset() {
      return this.asset;
   }

   @NotNull
   public final double[] getOhlcv() {
      return this.ohlcv;
   }

   @Nullable
   public final TimeSpan getTimeSpan() {
      return this.timeSpan;
   }

   public PriceBar(@NotNull Asset asset, @NotNull Number open, @NotNull Number high, @NotNull Number low, @NotNull Number close, @NotNull Number volume, @Nullable TimeSpan timeSpan) {
      Intrinsics.checkNotNullParameter(asset, "asset");
      Intrinsics.checkNotNullParameter(open, "open");
      Intrinsics.checkNotNullParameter(high, "high");
      Intrinsics.checkNotNullParameter(low, "low");
      Intrinsics.checkNotNullParameter(close, "close");
      Intrinsics.checkNotNullParameter(volume, "volume");
      double[] var8 = new double[]{open.doubleValue(), high.doubleValue(), low.doubleValue(), close.doubleValue(), volume.doubleValue()};
      this(asset, var8, timeSpan);
   }

   // $FF: synthetic method
   public PriceBar(Asset var1, Number var2, Number var3, Number var4, Number var5, Number var6, TimeSpan var7, int var8, DefaultConstructorMarker var9) {
      if ((var8 & 32) != 0) {
         var6 = (Number)Double.NaN;
      }

      if ((var8 & 64) != 0) {
         var7 = null;
      }

      this(var1, var2, var3, var4, var5, var6, var7);
   }

   public final double getOpen() {
      return this.ohlcv[0];
   }

   public final double getHigh() {
      return this.ohlcv[1];
   }

   public final double getLow() {
      return this.ohlcv[2];
   }

   public final double getClose() {
      return this.ohlcv[3];
   }

   public final double getVolume() {
      return this.ohlcv[4];
   }

   public double getVolume(@NotNull String type) {
      Intrinsics.checkNotNullParameter(type, "type");
      return Math.abs(this.ohlcv[4]);
   }

   @NotNull
   public String toString() {
      String var10000 = this.getAsset().getSymbol();
      List var10001 = ArraysKt.toList(this.ohlcv);
      TimeSpan var10002 = this.timeSpan;
      String var1;
      if (var10002 != null) {
         var1 = var10002.toString();
         if (var1 != null) {
            return "symbol=" + var10000 + " ohlcv=" + var10001 + " timeSpan=" + var1;
         }
      }

      var1 = "unknown";
      return "symbol=" + var10000 + " ohlcv=" + var10001 + " timeSpan=" + var1;
   }

   public final void adjustClose(@NotNull Number price) {
      Intrinsics.checkNotNullParameter(price, "price");
      double adj = price.doubleValue() / this.getClose();
      double[] var4 = this.ohlcv;
      var4[0] *= adj;
      var4 = this.ohlcv;
      var4[1] *= adj;
      var4 = this.ohlcv;
      var4[2] *= adj;
      var4 = this.ohlcv;
      var4[3] *= adj;
      var4 = this.ohlcv;
      var4[4] /= adj;
   }

   public double getPrice(@NotNull String type) {
      Intrinsics.checkNotNullParameter(type, "type");
      double var10000;
      switch (type) {
         case "DEFAULT":
            var10000 = this.ohlcv[3];
            return var10000;
         case "TYPICAL":
            var10000 = (this.ohlcv[1] + this.ohlcv[2] + this.ohlcv[3]) / (double)3.0F;
            return var10000;
         case "LOW":
            var10000 = this.ohlcv[2];
            return var10000;
         case "HIGH":
            var10000 = this.ohlcv[1];
            return var10000;
         case "OPEN":
            var10000 = this.ohlcv[0];
            return var10000;
         case "CLOSE":
            var10000 = this.ohlcv[3];
            return var10000;
      }

      var10000 = this.ohlcv[3];
      return var10000;
   }

   @NotNull
   public Amount getPriceAmount(@NotNull String type) {
      return PriceItem.DefaultImpls.getPriceAmount(this, type);
   }
}
