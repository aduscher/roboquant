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
   d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\b\u0010\u0014\u001a\u00020\u0003H\u0016J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0017"},
   d2 = {"Lorg/roboquant/common/Forex;", "Lorg/roboquant/common/Asset;", "symbol", "", "currency", "Lorg/roboquant/common/Currency;", "(Ljava/lang/String;Lorg/roboquant/common/Currency;)V", "getCurrency", "()Lorg/roboquant/common/Currency;", "getSymbol", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "serialize", "toString", "Companion", "roboquant"}
)
public final class Forex implements Asset {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final String symbol;
   @NotNull
   private final Currency currency;

   public Forex(@NotNull String symbol, @NotNull Currency currency) {
      Intrinsics.checkNotNullParameter(symbol, "symbol");
      Intrinsics.checkNotNullParameter(currency, "currency");
      super();
      this.symbol = symbol;
      this.currency = currency;
   }

   @NotNull
   public String getSymbol() {
      return this.symbol;
   }

   @NotNull
   public Currency getCurrency() {
      return this.currency;
   }

   @NotNull
   public String serialize() {
      String var10000 = this.getSymbol();
      return "Forex;" + var10000 + ";" + this.getCurrency();
   }

   @NotNull
   public Amount value_u_zS35g/* $FF was: value-u_zS35g*/(long size, double price) {
      return Asset.DefaultImpls.value-u_zS35g(this, size, price);
   }

   public int compareTo(@NotNull Asset other) {
      return Asset.DefaultImpls.compareTo(this, other);
   }

   @NotNull
   public final String component1() {
      return this.symbol;
   }

   @NotNull
   public final Currency component2() {
      return this.currency;
   }

   @NotNull
   public final Forex copy(@NotNull String symbol, @NotNull Currency currency) {
      Intrinsics.checkNotNullParameter(symbol, "symbol");
      Intrinsics.checkNotNullParameter(currency, "currency");
      return new Forex(symbol, currency);
   }

   // $FF: synthetic method
   public static Forex copy$default(Forex var0, String var1, Currency var2, int var3, Object var4) {
      if ((var3 & 1) != 0) {
         var1 = var0.symbol;
      }

      if ((var3 & 2) != 0) {
         var2 = var0.currency;
      }

      return var0.copy(var1, var2);
   }

   @NotNull
   public String toString() {
      return "Forex(symbol=" + this.symbol + ", currency=" + this.currency + ")";
   }

   public int hashCode() {
      int result = this.symbol.hashCode();
      result = result * 31 + this.currency.hashCode();
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof Forex)) {
         return false;
      } else {
         Forex var2 = (Forex)other;
         if (!Intrinsics.areEqual(this.symbol, var2.symbol)) {
            return false;
         } else {
            return Intrinsics.areEqual(this.currency, var2.currency);
         }
      }
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"},
      d2 = {"Lorg/roboquant/common/Forex$Companion;", "", "()V", "fromSymbol", "Lorg/roboquant/common/Forex;", "symbol", "", "roboquant"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final Forex fromSymbol(@NotNull String symbol) {
         Intrinsics.checkNotNullParameter(symbol, "symbol");
         return new Forex(symbol, Currency.Companion.getUSD());
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
