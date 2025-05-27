package org.roboquant.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import org.jetbrains.annotations.NotNull;

@JvmInline
@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0010\u0004\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u001c\b\u0087@\u0018\u0000 E2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001EB\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005B\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0006¢\u0006\u0004\b\u0004\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\b¢\u0006\u0004\b\u0004\u0010\tB\u0011\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\n¢\u0006\u0004\b\u0004\u0010\u000bB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\f¢\u0006\u0004\b\u0004\u0010\rJ\u0018\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u0003H\u0086\u0002¢\u0006\u0004\b!\u0010\"J\u001b\u0010\u001f\u001a\u00020\u00032\u0006\u0010 \u001a\u00020\u0000H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b#\u0010$J\u001e\u0010%\u001a\u00020\u00002\u0006\u0010 \u001a\u00020&H\u0086\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b'\u0010(J\u001a\u0010)\u001a\u00020\u00112\b\u0010 \u001a\u0004\u0018\u00010*HÖ\u0003¢\u0006\u0004\b+\u0010,J\u0010\u0010-\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b.\u0010\u001eJ\u001b\u0010/\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b0\u00101J\u001b\u00102\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u0000H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b3\u00101J\u001b\u00104\u001a\u00020\u00002\u0006\u00105\u001a\u00020\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b6\u00107J\u001e\u00108\u001a\u00020\u00002\u0006\u0010 \u001a\u00020&H\u0086\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b9\u0010(J\r\u0010:\u001a\u00020\u0006¢\u0006\u0004\b;\u0010<J\r\u0010=\u001a\u00020\b¢\u0006\u0004\b>\u0010?J\u000f\u0010@\u001a\u00020\nH\u0016¢\u0006\u0004\bA\u0010BJ\u0016\u0010C\u001a\u00020\u0000H\u0086\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\bD\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00008Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0016\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\u0018\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\u001a\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0013R\u0011\u0010\u001c\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u0002\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006F"},
   d2 = {"Lorg/roboquant/common/Size;", "", "value", "", "constructor-impl", "(I)J", "Ljava/math/BigDecimal;", "(Ljava/math/BigDecimal;)J", "", "(D)J", "", "(Ljava/lang/String;)J", "", "(J)J", "absoluteValue", "getAbsoluteValue-vehRhPc", "isFractional", "", "isFractional-impl", "(J)Z", "isNegative", "isNegative-impl", "isPositive", "isPositive-impl", "iszero", "getIszero-impl", "nonzero", "getNonzero-impl", "sign", "getSign-impl", "(J)I", "compareTo", "other", "compareTo-impl", "(JI)I", "compareTo-ZWO118U", "(JJ)I", "div", "", "div-NKre9r8", "(JLjava/lang/Number;)J", "equals", "", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "minus", "minus-d9a907g", "(JJ)J", "plus", "plus-d9a907g", "round", "scale", "round-NKre9r8", "(JI)J", "times", "times-NKre9r8", "toBigDecimal", "toBigDecimal-impl", "(J)Ljava/math/BigDecimal;", "toDouble", "toDouble-impl", "(J)D", "toString", "toString-impl", "(J)Ljava/lang/String;", "unaryMinus", "unaryMinus-vehRhPc", "Companion", "roboquant"}
)
public final class Size implements Comparable {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   private final long value;
   private static final int SCALE = 8;
   private static final long FRACTION = 100000000L;
   private static final double DOUBLE_FRACTION = (double)1.0E8F;
   @NotNull
   private static final BigDecimal BD_FRACTION = new BigDecimal(100000000L);
   private static final long ZERO = constructor-impl(0);
   private static final long ONE = constructor-impl(1);

   public static long constructor_impl/* $FF was: constructor-impl*/(int value) {
      return constructor-impl((long)value * 100000000L);
   }

   public static long constructor_impl/* $FF was: constructor-impl*/(@NotNull BigDecimal value) {
      Intrinsics.checkNotNullParameter(value, "value");
      return constructor-impl(value.multiply(BD_FRACTION).longValueExact());
   }

   public static long constructor_impl/* $FF was: constructor-impl*/(double value) {
      return constructor-impl(BigDecimal.valueOf(value).multiply(BD_FRACTION).longValue());
   }

   public static long constructor_impl/* $FF was: constructor-impl*/(@NotNull String value) {
      Intrinsics.checkNotNullParameter(value, "value");
      return constructor-impl((new BigDecimal(value)).multiply(BD_FRACTION).longValueExact());
   }

   public static final double toDouble_impl/* $FF was: toDouble-impl*/(long arg0) {
      return (double)arg0 / (double)1.0E8F;
   }

   @NotNull
   public static final BigDecimal toBigDecimal_impl/* $FF was: toBigDecimal-impl*/(long arg0) {
      BigDecimal var10000 = (new BigDecimal(arg0)).setScale(8).divide(BD_FRACTION);
      Intrinsics.checkNotNullExpressionValue(var10000, "divide(...)");
      return var10000;
   }

   public static final boolean getIszero_impl/* $FF was: getIszero-impl*/(long arg0) {
      return arg0 == 0L;
   }

   public static final boolean getNonzero_impl/* $FF was: getNonzero-impl*/(long arg0) {
      return arg0 != 0L;
   }

   public static final boolean isPositive_impl/* $FF was: isPositive-impl*/(long arg0) {
      return arg0 > 0L;
   }

   public static final boolean isNegative_impl/* $FF was: isNegative-impl*/(long arg0) {
      return arg0 < 0L;
   }

   public static final boolean isFractional_impl/* $FF was: isFractional-impl*/(long arg0) {
      return arg0 % 100000000L != 0L;
   }

   public static final long getAbsoluteValue_vehRhPc/* $FF was: getAbsoluteValue-vehRhPc*/(long arg0) {
      return constructor-impl(Math.abs(arg0));
   }

   public static final int getSign_impl/* $FF was: getSign-impl*/(long arg0) {
      return MathKt.getSign(arg0);
   }

   public static final long round_NKre9r8/* $FF was: round-NKre9r8*/(long arg0, int scale) {
      BigDecimal var10000 = toBigDecimal-impl(arg0).setScale(scale, RoundingMode.DOWN);
      Intrinsics.checkNotNullExpressionValue(var10000, "setScale(...)");
      return constructor-impl(var10000);
   }

   public static final long times_NKre9r8/* $FF was: times-NKre9r8*/(long arg0, @NotNull Number other) {
      Intrinsics.checkNotNullParameter(other, "other");
      return constructor-impl(toDouble-impl(arg0) * other.doubleValue());
   }

   public static final long div_NKre9r8/* $FF was: div-NKre9r8*/(long arg0, @NotNull Number other) {
      Intrinsics.checkNotNullParameter(other, "other");
      return constructor-impl(toDouble-impl(arg0) / other.doubleValue());
   }

   public static final long plus_d9a907g/* $FF was: plus-d9a907g*/(long arg0, long other) {
      return constructor-impl(arg0 + other);
   }

   public static final long minus_d9a907g/* $FF was: minus-d9a907g*/(long arg0, long other) {
      return constructor-impl(arg0 - other);
   }

   public static final int compareTo_impl/* $FF was: compareTo-impl*/(long arg0, int other) {
      return compareTo-ZWO118U(arg0, constructor-impl(other));
   }

   public static int compareTo_ZWO118U/* $FF was: compareTo-ZWO118U*/(long arg0, long other) {
      return Intrinsics.compare(arg0, other);
   }

   public int compareTo_ZWO118U/* $FF was: compareTo-ZWO118U*/(long other) {
      return compareTo-ZWO118U(this.value, other);
   }

   public static final long unaryMinus_vehRhPc/* $FF was: unaryMinus-vehRhPc*/(long arg0) {
      return constructor-impl(-arg0);
   }

   @NotNull
   public static String toString_impl/* $FF was: toString-impl*/(long arg0) {
      String var10000 = toBigDecimal-impl(arg0).stripTrailingZeros().toPlainString();
      Intrinsics.checkNotNullExpressionValue(var10000, "toPlainString(...)");
      return var10000;
   }

   @NotNull
   public String toString() {
      return toString-impl(this.value);
   }

   public static int hashCode_impl/* $FF was: hashCode-impl*/(long arg0) {
      return Long.hashCode(arg0);
   }

   public int hashCode() {
      return hashCode-impl(this.value);
   }

   public static boolean equals_impl/* $FF was: equals-impl*/(long arg0, Object other) {
      if (!(other instanceof Size)) {
         return false;
      } else {
         long var3 = ((Size)other).unbox-impl();
         return arg0 == var3;
      }
   }

   public boolean equals(Object other) {
      return equals-impl(this.value, other);
   }

   // $FF: synthetic method
   private Size(long value) {
      this.value = value;
   }

   private static long constructor_impl/* $FF was: constructor-impl*/(long value) {
      return value;
   }

   // $FF: synthetic method
   public static final Size box_impl/* $FF was: box-impl*/(long v) {
      return new Size(v);
   }

   // $FF: synthetic method
   public final long unbox_impl/* $FF was: unbox-impl*/() {
      return this.value;
   }

   public static final boolean equals_impl0/* $FF was: equals-impl0*/(long p1, long p2) {
      return p1 == p2;
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001b\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\bø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u0019\u0010\t\u001a\u00020\nø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082T¢\u0006\u0002\n\u0000R\u0019\u0010\u0010\u001a\u00020\nø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u0011\u0010\f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0016"},
      d2 = {"Lorg/roboquant/common/Size$Companion;", "", "()V", "BD_FRACTION", "Ljava/math/BigDecimal;", "DOUBLE_FRACTION", "", "FRACTION", "", "ONE", "Lorg/roboquant/common/Size;", "getONE-vehRhPc", "()J", "J", "SCALE", "", "ZERO", "getZERO-vehRhPc", "fromUnderlyingValue", "x", "fromUnderlyingValue-NKre9r8", "(J)J", "roboquant"}
   )
   public static final class Companion {
      private Companion() {
      }

      public final long fromUnderlyingValue_NKre9r8/* $FF was: fromUnderlyingValue-NKre9r8*/(long x) {
         return Size.constructor-impl(x);
      }

      public final long getZERO_vehRhPc/* $FF was: getZERO-vehRhPc*/() {
         return Size.ZERO;
      }

      public final long getONE_vehRhPc/* $FF was: getONE-vehRhPc*/() {
         return Size.ONE;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
