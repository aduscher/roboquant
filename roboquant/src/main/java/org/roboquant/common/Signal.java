package org.roboquant.common;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\u0018\u0000 !2\u00020\u0001:\u0001!B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u000e\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\u0017\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u0018\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006\""},
   d2 = {"Lorg/roboquant/common/Signal;", "", "asset", "Lorg/roboquant/common/Asset;", "rating", "", "type", "Lorg/roboquant/common/SignalType;", "tag", "", "(Lorg/roboquant/common/Asset;DLorg/roboquant/common/SignalType;Ljava/lang/String;)V", "getAsset", "()Lorg/roboquant/common/Asset;", "direction", "", "getDirection", "()I", "entry", "", "getEntry", "()Z", "exit", "getExit", "isBuy", "isSell", "getRating", "()D", "getTag", "()Ljava/lang/String;", "getType", "()Lorg/roboquant/common/SignalType;", "conflicts", "other", "Companion", "roboquant"}
)
public final class Signal {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final Asset asset;
   private final double rating;
   @NotNull
   private final SignalType type;
   @NotNull
   private final String tag;

   public Signal(@NotNull Asset asset, double rating, @NotNull SignalType type, @NotNull String tag) {
      Intrinsics.checkNotNullParameter(asset, "asset");
      Intrinsics.checkNotNullParameter(type, "type");
      Intrinsics.checkNotNullParameter(tag, "tag");
      super();
      this.asset = asset;
      this.rating = rating;
      this.type = type;
      this.tag = tag;
   }

   // $FF: synthetic method
   public Signal(Asset var1, double var2, SignalType var4, String var5, int var6, DefaultConstructorMarker var7) {
      if ((var6 & 4) != 0) {
         var4 = SignalType.BOTH;
      }

      if ((var6 & 8) != 0) {
         var5 = "";
      }

      this(var1, var2, var4, var5);
   }

   @NotNull
   public final Asset getAsset() {
      return this.asset;
   }

   public final double getRating() {
      return this.rating;
   }

   @NotNull
   public final SignalType getType() {
      return this.type;
   }

   @NotNull
   public final String getTag() {
      return this.tag;
   }

   public final boolean getExit() {
      return this.type == SignalType.EXIT || this.type == SignalType.BOTH;
   }

   public final boolean getEntry() {
      return this.type == SignalType.ENTRY || this.type == SignalType.BOTH;
   }

   public final boolean conflicts(@NotNull Signal other) {
      Intrinsics.checkNotNullParameter(other, "other");
      return Intrinsics.areEqual(this.asset, other.asset) && this.getDirection() != other.getDirection();
   }

   public final int getDirection() {
      return this.isBuy() ? 1 : (this.isSell() ? -1 : 0);
   }

   public final boolean isBuy() {
      return this.rating > (double)0.0F;
   }

   public final boolean isSell() {
      return this.rating < (double)0.0F;
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nJ\"\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n¨\u0006\f"},
      d2 = {"Lorg/roboquant/common/Signal$Companion;", "", "()V", "buy", "Lorg/roboquant/common/Signal;", "asset", "Lorg/roboquant/common/Asset;", "type", "Lorg/roboquant/common/SignalType;", "tag", "", "sell", "roboquant"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final Signal buy(@NotNull Asset asset, @NotNull SignalType type, @NotNull String tag) {
         Intrinsics.checkNotNullParameter(asset, "asset");
         Intrinsics.checkNotNullParameter(type, "type");
         Intrinsics.checkNotNullParameter(tag, "tag");
         return new Signal(asset, (double)1.0F, type, tag);
      }

      // $FF: synthetic method
      public static Signal buy$default(Companion var0, Asset var1, SignalType var2, String var3, int var4, Object var5) {
         if ((var4 & 2) != 0) {
            var2 = SignalType.BOTH;
         }

         if ((var4 & 4) != 0) {
            var3 = "";
         }

         return var0.buy(var1, var2, var3);
      }

      @NotNull
      public final Signal sell(@NotNull Asset asset, @NotNull SignalType type, @NotNull String tag) {
         Intrinsics.checkNotNullParameter(asset, "asset");
         Intrinsics.checkNotNullParameter(type, "type");
         Intrinsics.checkNotNullParameter(tag, "tag");
         return new Signal(asset, (double)-1.0F, type, tag);
      }

      // $FF: synthetic method
      public static Signal sell$default(Companion var0, Asset var1, SignalType var2, String var3, int var4, Object var5) {
         if ((var4 & 2) != 0) {
            var2 = SignalType.BOTH;
         }

         if ((var4 & 4) != 0) {
            var3 = "";
         }

         return var0.sell(var1, var2, var3);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
