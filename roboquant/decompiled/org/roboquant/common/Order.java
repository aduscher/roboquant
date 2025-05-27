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
   d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\"\n\u0002\u0010\b\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0006\u0010&\u001a\u00020\u0000J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\u0016\u0010(\u001a\u00020\u0005HÆ\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b)\u0010\u0015J\t\u0010*\u001a\u00020\u0007HÆ\u0003J\t\u0010+\u001a\u00020\tHÆ\u0003J\t\u0010,\u001a\u00020\u000bHÆ\u0003JE\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001ø\u0001\u0000¢\u0006\u0004\b.\u0010/J\u0013\u00100\u001a\u00020\u00102\b\u00101\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00102\u001a\u000203HÖ\u0001J\u0006\u00104\u001a\u00020\u0010J\u000e\u00105\u001a\u00020\u00102\u0006\u00106\u001a\u00020\u0007J\u0006\u00107\u001a\u00020\u0010J$\u00108\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007ø\u0001\u0000¢\u0006\u0004\b9\u0010:J\b\u0010;\u001a\u00020\u000bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\u00020\u0005X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0019\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010 \u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b!\u0010\u0012R\u0019\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\"\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001bR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006<"},
   d2 = {"Lorg/roboquant/common/Order;", "", "asset", "Lorg/roboquant/common/Asset;", "size", "Lorg/roboquant/common/Size;", "limit", "", "tif", "Lorg/roboquant/common/TIF;", "tag", "", "(Lorg/roboquant/common/Asset;JDLorg/roboquant/common/TIF;Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAsset", "()Lorg/roboquant/common/Asset;", "buy", "", "getBuy", "()Z", "fill", "getFill-vehRhPc", "()J", "setFill-ZWO118U", "(J)V", "J", "id", "getId", "()Ljava/lang/String;", "setId", "(Ljava/lang/String;)V", "getLimit", "()D", "sell", "getSell", "getSize-vehRhPc", "getTag", "getTif", "()Lorg/roboquant/common/TIF;", "cancel", "component1", "component2", "component2-vehRhPc", "component3", "component4", "component5", "copy", "copy-8-SH2jU", "(Lorg/roboquant/common/Asset;JDLorg/roboquant/common/TIF;Ljava/lang/String;)Lorg/roboquant/common/Order;", "equals", "other", "hashCode", "", "isCancellation", "isExecutable", "price", "isModify", "modify", "modify-u_zS35g", "(JD)Lorg/roboquant/common/Order;", "toString", "roboquant"}
)
public final class Order {
   @NotNull
   private final Asset asset;
   private final long size;
   private final double limit;
   @NotNull
   private final TIF tif;
   @NotNull
   private final String tag;
   @NotNull
   private String id;
   private long fill;

   private Order(Asset asset, long size, double limit, TIF tif, String tag) {
      Intrinsics.checkNotNullParameter(asset, "asset");
      Intrinsics.checkNotNullParameter(tif, "tif");
      Intrinsics.checkNotNullParameter(tag, "tag");
      super();
      this.asset = asset;
      this.size = size;
      this.limit = limit;
      this.tif = tif;
      this.tag = tag;
      this.id = "";
      this.fill = Size.Companion.getZERO-vehRhPc();
   }

   // $FF: synthetic method
   public Order(Asset var1, long var2, double var4, TIF var6, String var7, int var8, DefaultConstructorMarker var9) {
      if ((var8 & 8) != 0) {
         var6 = TIF.DAY;
      }

      if ((var8 & 16) != 0) {
         var7 = "";
      }

      this(var1, var2, var4, var6, var7, (DefaultConstructorMarker)null);
   }

   @NotNull
   public final Asset getAsset() {
      return this.asset;
   }

   public final long getSize_vehRhPc/* $FF was: getSize-vehRhPc*/() {
      return this.size;
   }

   public final double getLimit() {
      return this.limit;
   }

   @NotNull
   public final TIF getTif() {
      return this.tif;
   }

   @NotNull
   public final String getTag() {
      return this.tag;
   }

   public final boolean getBuy() {
      return Size.compareTo-impl(this.size, 0) > 0;
   }

   public final boolean getSell() {
      return Size.compareTo-impl(this.size, 0) < 0;
   }

   @NotNull
   public final String getId() {
      return this.id;
   }

   public final void setId(@NotNull String var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.id = var1;
   }

   public final long getFill_vehRhPc/* $FF was: getFill-vehRhPc*/() {
      return this.fill;
   }

   public final void setFill_ZWO118U/* $FF was: setFill-ZWO118U*/(long var1) {
      this.fill = var1;
   }

   @NotNull
   public final Order cancel() {
      if (Intrinsics.areEqual(this.id, "")) {
         String var1 = "Failed requirement.";
         throw new IllegalArgumentException(var1.toString());
      } else {
         return copy-8-SH2jU$default(this, (Asset)null, Size.Companion.getZERO-vehRhPc(), (double)0.0F, (TIF)null, (String)null, 29, (Object)null);
      }
   }

   @NotNull
   public final Order modify_u_zS35g/* $FF was: modify-u_zS35g*/(long size, double limit) {
      if (Intrinsics.areEqual(this.id, "")) {
         String var5 = "Failed requirement.";
         throw new IllegalArgumentException(var5.toString());
      } else {
         return copy-8-SH2jU$default(this, (Asset)null, size, limit, (TIF)null, (String)null, 25, (Object)null);
      }
   }

   // $FF: synthetic method
   public static Order modify_u_zS35g$default/* $FF was: modify-u_zS35g$default*/(Order var0, long var1, double var3, int var5, Object var6) {
      if ((var5 & 1) != 0) {
         var1 = var0.size;
      }

      if ((var5 & 2) != 0) {
         var3 = var0.limit;
      }

      return var0.modify-u_zS35g(var1, var3);
   }

   public final boolean isCancellation() {
      return Size.equals-impl0(this.size, Size.Companion.getZERO-vehRhPc()) && !Intrinsics.areEqual(this.id, "");
   }

   public final boolean isExecutable(double price) {
      return this.getBuy() & price <= this.limit || this.getSell() & price >= this.limit;
   }

   public final boolean isModify() {
      return !Size.equals-impl0(this.size, Size.Companion.getZERO-vehRhPc()) && !Intrinsics.areEqual(this.id, "");
   }

   @NotNull
   public String toString() {
      return "asset=" + this.asset + " id=" + this.id + " tag=" + this.tag;
   }

   @NotNull
   public final Asset component1() {
      return this.asset;
   }

   public final long component2_vehRhPc/* $FF was: component2-vehRhPc*/() {
      return this.size;
   }

   public final double component3() {
      return this.limit;
   }

   @NotNull
   public final TIF component4() {
      return this.tif;
   }

   @NotNull
   public final String component5() {
      return this.tag;
   }

   @NotNull
   public final Order copy_8_SH2jU/* $FF was: copy-8-SH2jU*/(@NotNull Asset asset, long size, double limit, @NotNull TIF tif, @NotNull String tag) {
      Intrinsics.checkNotNullParameter(asset, "asset");
      Intrinsics.checkNotNullParameter(tif, "tif");
      Intrinsics.checkNotNullParameter(tag, "tag");
      return new Order(asset, size, limit, tif, tag, (DefaultConstructorMarker)null);
   }

   // $FF: synthetic method
   public static Order copy_8_SH2jU$default/* $FF was: copy-8-SH2jU$default*/(Order var0, Asset var1, long var2, double var4, TIF var6, String var7, int var8, Object var9) {
      if ((var8 & 1) != 0) {
         var1 = var0.asset;
      }

      if ((var8 & 2) != 0) {
         var2 = var0.size;
      }

      if ((var8 & 4) != 0) {
         var4 = var0.limit;
      }

      if ((var8 & 8) != 0) {
         var6 = var0.tif;
      }

      if ((var8 & 16) != 0) {
         var7 = var0.tag;
      }

      return var0.copy-8-SH2jU(var1, var2, var4, var6, var7);
   }

   public int hashCode() {
      int result = this.asset.hashCode();
      result = result * 31 + Size.hashCode-impl(this.size);
      result = result * 31 + Double.hashCode(this.limit);
      result = result * 31 + this.tif.hashCode();
      result = result * 31 + this.tag.hashCode();
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof Order)) {
         return false;
      } else {
         Order var2 = (Order)other;
         if (!Intrinsics.areEqual(this.asset, var2.asset)) {
            return false;
         } else if (!Size.equals-impl0(this.size, var2.size)) {
            return false;
         } else if (Double.compare(this.limit, var2.limit) != 0) {
            return false;
         } else if (this.tif != var2.tif) {
            return false;
         } else {
            return Intrinsics.areEqual(this.tag, var2.tag);
         }
      }
   }

   // $FF: synthetic method
   public Order(Asset asset, long size, double limit, TIF tif, String tag, DefaultConstructorMarker $constructor_marker) {
      this(asset, size, limit, tif, tag);
   }
}
