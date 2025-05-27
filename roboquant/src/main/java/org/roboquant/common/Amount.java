package org.roboquant.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.Instant;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\b\u0016\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\tB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0016\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0019J\u0011\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0005H\u0086\u0002J\u0013\u0010\u001c\u001a\u00020\u00122\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\u0010\u0010\u001e\u001a\u00020\b2\b\b\u0002\u0010\u001f\u001a\u00020 J\b\u0010!\u001a\u00020 H\u0016J\u0011\u0010\"\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0005H\u0086\u0002J\u0011\u0010\"\u001a\u00020#2\u0006\u0010\u001d\u001a\u00020\u0000H\u0086\u0002J\u0011\u0010$\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0005H\u0086\u0002J\u0011\u0010$\u001a\u00020#2\u0006\u0010\u001d\u001a\u00020\u0000H\u0086\u0002J\u0011\u0010%\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0005H\u0086\u0002J\u0010\u0010&\u001a\u00020'2\b\b\u0002\u0010\u001f\u001a\u00020 J\b\u0010(\u001a\u00020\bH\u0016J\u0006\u0010)\u001a\u00020#J\t\u0010*\u001a\u00020\u0000H\u0086\u0002R\u0011\u0010\f\u001a\u00020\u00008F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006+"},
   d2 = {"Lorg/roboquant/common/Amount;", "", "currency", "Lorg/roboquant/common/Currency;", "value", "", "(Lorg/roboquant/common/Currency;Ljava/lang/Number;)V", "currencyCode", "", "(Ljava/lang/String;Ljava/lang/Number;)V", "", "(Lorg/roboquant/common/Currency;D)V", "absoluteValue", "getAbsoluteValue", "()Lorg/roboquant/common/Amount;", "getCurrency", "()Lorg/roboquant/common/Currency;", "isPositive", "", "()Z", "getValue", "()D", "convert", "to", "time", "Ljava/time/Instant;", "div", "d", "equals", "other", "formatValue", "fractionDigits", "", "hashCode", "minus", "Lorg/roboquant/common/Wallet;", "plus", "times", "toBigDecimal", "Ljava/math/BigDecimal;", "toString", "toWallet", "unaryMinus", "roboquant"}
)
public final class Amount {
   @NotNull
   private final Currency currency;
   private final double value;

   public Amount(@NotNull Currency currency, double value) {
      Intrinsics.checkNotNullParameter(currency, "currency");
      super();
      this.currency = currency;
      this.value = value;
   }

   @NotNull
   public final Currency getCurrency() {
      return this.currency;
   }

   public final double getValue() {
      return this.value;
   }

   public Amount(@NotNull Currency currency, @NotNull Number value) {
      Intrinsics.checkNotNullParameter(currency, "currency");
      Intrinsics.checkNotNullParameter(value, "value");
      this(currency, value.doubleValue());
   }

   public Amount(@NotNull String currencyCode, @NotNull Number value) {
      Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
      Intrinsics.checkNotNullParameter(value, "value");
      this(Currency.Companion.getInstance(currencyCode), value.doubleValue());
   }

   @NotNull
   public final Amount times(@NotNull Number d) {
      Intrinsics.checkNotNullParameter(d, "d");
      return new Amount(this.currency, this.value * d.doubleValue());
   }

   @NotNull
   public final Amount plus(@NotNull Number d) {
      Intrinsics.checkNotNullParameter(d, "d");
      return new Amount(this.currency, this.value + d.doubleValue());
   }

   @NotNull
   public final Amount div(@NotNull Number d) {
      Intrinsics.checkNotNullParameter(d, "d");
      return new Amount(this.currency, this.value / d.doubleValue());
   }

   @NotNull
   public final Amount minus(@NotNull Number d) {
      Intrinsics.checkNotNullParameter(d, "d");
      return new Amount(this.currency, this.value - d.doubleValue());
   }

   @NotNull
   public final Wallet plus(@NotNull Amount other) {
      Intrinsics.checkNotNullParameter(other, "other");
      Wallet.Companion var10000 = Wallet.Companion;
      Amount[] var2 = new Amount[]{this, other};
      return var10000.invoke(var2);
   }

   @NotNull
   public final Wallet minus(@NotNull Amount other) {
      Intrinsics.checkNotNullParameter(other, "other");
      Wallet.Companion var10000 = Wallet.Companion;
      Amount[] var2 = new Amount[]{this, other.unaryMinus()};
      return var10000.invoke(var2);
   }

   @NotNull
   public final Amount unaryMinus() {
      return new Amount(this.currency, -this.value);
   }

   public final boolean isPositive() {
      return this.value > (double)0.0F;
   }

   @NotNull
   public final Amount getAbsoluteValue() {
      return new Amount(this.currency, Math.abs(this.value));
   }

   @NotNull
   public final String formatValue(int fractionDigits) {
      NumberFormat formatEN = NumberFormat.getInstance(Locale.ENGLISH);
      formatEN.setMinimumFractionDigits(fractionDigits);
      formatEN.setMaximumFractionDigits(fractionDigits);
      String var10000 = formatEN.format(this.value);
      Intrinsics.checkNotNullExpressionValue(var10000, "format(...)");
      return var10000;
   }

   // $FF: synthetic method
   public static String formatValue$default(Amount var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.currency.getDefaultFractionDigits();
      }

      return var0.formatValue(var1);
   }

   @NotNull
   public final BigDecimal toBigDecimal(int fractionDigits) {
      BigDecimal var10000 = BigDecimal.valueOf(this.value).setScale(fractionDigits, RoundingMode.HALF_DOWN);
      Intrinsics.checkNotNullExpressionValue(var10000, "setScale(...)");
      return var10000;
   }

   // $FF: synthetic method
   public static BigDecimal toBigDecimal$default(Amount var0, int var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.currency.getDefaultFractionDigits();
      }

      return var0.toBigDecimal(var1);
   }

   @NotNull
   public String toString() {
      String var10000 = this.currency.getCurrencyCode();
      return var10000 + " " + formatValue$default(this, 0, 1, (Object)null);
   }

   @NotNull
   public final Amount convert(@NotNull Currency to, @NotNull Instant time) {
      Intrinsics.checkNotNullParameter(to, "to");
      Intrinsics.checkNotNullParameter(time, "time");
      if (Intrinsics.areEqual(this.currency, to)) {
         return this;
      } else {
         return this.value == (double)0.0F ? new Amount(to, (double)0.0F) : Config.INSTANCE.getExchangeRates().convert(this, to, time);
      }
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof Amount)) {
         return false;
      } else if (!Intrinsics.areEqual(this.currency, ((Amount)other).currency)) {
         return false;
      } else {
         return this.value == ((Amount)other).value;
      }
   }

   public int hashCode() {
      return 31 * this.currency.hashCode() + Double.hashCode(this.value);
   }

   @NotNull
   public final Wallet toWallet() {
      return Wallet.Companion.invoke(this);
   }
}
