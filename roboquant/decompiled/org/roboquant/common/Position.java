package org.roboquant.common;

import java.time.Instant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 *2\u00020\u0001:\u0001*B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0016\u0010\u001c\u001a\u00020\u0003HÆ\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001aJ\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\bHÆ\u0003J;\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J\u0013\u0010$\u001a\u00020\r2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020'HÖ\u0001J\t\u0010(\u001a\u00020)HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000bR\u0011\u0010\u0015\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u0017\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u000fR\u0019\u0010\u0002\u001a\u00020\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001a\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006+"},
   d2 = {"Lorg/roboquant/common/Position;", "", "size", "Lorg/roboquant/common/Size;", "avgPrice", "", "mktPrice", "lastUpdate", "Ljava/time/Instant;", "(JDDLjava/time/Instant;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAvgPrice", "()D", "closed", "", "getClosed", "()Z", "getLastUpdate", "()Ljava/time/Instant;", "long", "getLong", "getMktPrice", "open", "getOpen", "short", "getShort", "getSize-vehRhPc", "()J", "J", "component1", "component1-vehRhPc", "component2", "component3", "component4", "copy", "copy-9NLspic", "(JDDLjava/time/Instant;)Lorg/roboquant/common/Position;", "equals", "other", "hashCode", "", "toString", "", "Companion", "roboquant"}
)
public final class Position {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private final long size;
   private final double avgPrice;
   private final double mktPrice;
   @NotNull
   private final Instant lastUpdate;

   private Position(long size, double avgPrice, double mktPrice, Instant lastUpdate) {
      Intrinsics.checkNotNullParameter(lastUpdate, "lastUpdate");
      super();
      this.size = size;
      this.avgPrice = avgPrice;
      this.mktPrice = mktPrice;
      this.lastUpdate = lastUpdate;
   }

   // $FF: synthetic method
   public Position(long var1, double var3, double var5, Instant var7, int var8, DefaultConstructorMarker var9) {
      if ((var8 & 2) != 0) {
         var3 = (double)0.0F;
      }

      if ((var8 & 4) != 0) {
         var5 = var3;
      }

      if ((var8 & 8) != 0) {
         Instant var10000 = Instant.MIN;
         Intrinsics.checkNotNullExpressionValue(var10000, "MIN");
         var7 = var10000;
      }

      this(var1, var3, var5, var7, (DefaultConstructorMarker)null);
   }

   public final long getSize_vehRhPc/* $FF was: getSize-vehRhPc*/() {
      return this.size;
   }

   public final double getAvgPrice() {
      return this.avgPrice;
   }

   public final double getMktPrice() {
      return this.mktPrice;
   }

   @NotNull
   public final Instant getLastUpdate() {
      return this.lastUpdate;
   }

   public final boolean getClosed() {
      return Size.getIszero-impl(this.size);
   }

   public final boolean getShort() {
      return Size.isNegative-impl(this.size);
   }

   public final boolean getLong() {
      return Size.isPositive-impl(this.size);
   }

   public final boolean getOpen() {
      return !Size.getIszero-impl(this.size);
   }

   public final long component1_vehRhPc/* $FF was: component1-vehRhPc*/() {
      return this.size;
   }

   public final double component2() {
      return this.avgPrice;
   }

   public final double component3() {
      return this.mktPrice;
   }

   @NotNull
   public final Instant component4() {
      return this.lastUpdate;
   }

   @NotNull
   public final Position copy_9NLspic/* $FF was: copy-9NLspic*/(long size, double avgPrice, double mktPrice, @NotNull Instant lastUpdate) {
      Intrinsics.checkNotNullParameter(lastUpdate, "lastUpdate");
      return new Position(size, avgPrice, mktPrice, lastUpdate, (DefaultConstructorMarker)null);
   }

   // $FF: synthetic method
   public static Position copy_9NLspic$default/* $FF was: copy-9NLspic$default*/(Position var0, long var1, double var3, double var5, Instant var7, int var8, Object var9) {
      if ((var8 & 1) != 0) {
         var1 = var0.size;
      }

      if ((var8 & 2) != 0) {
         var3 = var0.avgPrice;
      }

      if ((var8 & 4) != 0) {
         var5 = var0.mktPrice;
      }

      if ((var8 & 8) != 0) {
         var7 = var0.lastUpdate;
      }

      return var0.copy-9NLspic(var1, var3, var5, var7);
   }

   @NotNull
   public String toString() {
      String var10000 = Size.toString-impl(this.size);
      return "Position(size=" + var10000 + ", avgPrice=" + this.avgPrice + ", mktPrice=" + this.mktPrice + ", lastUpdate=" + this.lastUpdate + ")";
   }

   public int hashCode() {
      int result = Size.hashCode-impl(this.size);
      result = result * 31 + Double.hashCode(this.avgPrice);
      result = result * 31 + Double.hashCode(this.mktPrice);
      result = result * 31 + this.lastUpdate.hashCode();
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof Position)) {
         return false;
      } else {
         Position var2 = (Position)other;
         if (!Size.equals-impl0(this.size, var2.size)) {
            return false;
         } else if (Double.compare(this.avgPrice, var2.avgPrice) != 0) {
            return false;
         } else if (Double.compare(this.mktPrice, var2.mktPrice) != 0) {
            return false;
         } else {
            return Intrinsics.areEqual(this.lastUpdate, var2.lastUpdate);
         }
      }
   }

   // $FF: synthetic method
   public Position(long size, double avgPrice, double mktPrice, Instant lastUpdate, DefaultConstructorMarker $constructor_marker) {
      this(size, avgPrice, mktPrice, lastUpdate);
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"},
      d2 = {"Lorg/roboquant/common/Position$Companion;", "", "()V", "empty", "Lorg/roboquant/common/Position;", "roboquant"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final Position empty() {
         return new Position(Size.Companion.getZERO-vehRhPc(), (double)0.0F, (double)0.0F, (Instant)null, 8, (DefaultConstructorMarker)null);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
