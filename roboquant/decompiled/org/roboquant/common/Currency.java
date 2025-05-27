package org.roboquant.common;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\t\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0006¨\u0006\u0011"},
   d2 = {"Lorg/roboquant/common/Currency;", "", "currencyCode", "", "(Ljava/lang/String;)V", "getCurrencyCode", "()Ljava/lang/String;", "defaultFractionDigits", "", "getDefaultFractionDigits", "()I", "setDefaultFractionDigits", "(I)V", "displayName", "getDisplayName", "toString", "Companion", "roboquant"}
)
public final class Currency {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final String currencyCode;
   private int defaultFractionDigits;
   @NotNull
   private static final ConcurrentHashMap currencies = new ConcurrentHashMap();
   @NotNull
   private static final Currency USD;
   @NotNull
   private static final Currency EUR;
   @NotNull
   private static final Currency JPY;
   @NotNull
   private static final Currency GBP;
   @NotNull
   private static final Currency AUD;
   @NotNull
   private static final Currency CAD;
   @NotNull
   private static final Currency CHF;
   @NotNull
   private static final Currency CNY;
   @NotNull
   private static final Currency HKD;
   @NotNull
   private static final Currency NZD;
   @NotNull
   private static final Currency RUB;
   @NotNull
   private static final Currency INR;
   @NotNull
   private static final Currency BTC;
   @NotNull
   private static final Currency ETH;
   @NotNull
   private static final Currency USDT;

   private Currency(String currencyCode) {
      this.currencyCode = currencyCode;
      Currency var4 = this;

      int var2;
      Currency var10000;
      try {
         var10000 = var4;
         var2 = java.util.Currency.getInstance(this.currencyCode).getDefaultFractionDigits();
      } catch (IllegalArgumentException var5) {
         var10000 = this;
         var2 = 2;
      }

      var10000.defaultFractionDigits = var2;
   }

   @NotNull
   public final String getCurrencyCode() {
      return this.currencyCode;
   }

   @NotNull
   public final String getDisplayName() {
      return this.currencyCode;
   }

   public final int getDefaultFractionDigits() {
      return this.defaultFractionDigits;
   }

   public final void setDefaultFractionDigits(int var1) {
      this.defaultFractionDigits = var1;
   }

   @NotNull
   public String toString() {
      return this.getDisplayName();
   }

   // $FF: synthetic method
   public Currency(String currencyCode, DefaultConstructorMarker $constructor_marker) {
      this(currencyCode);
   }

   static {
      USD = Companion.getInstance("USD");
      EUR = Companion.getInstance("EUR");
      JPY = Companion.getInstance("JPY");
      GBP = Companion.getInstance("GBP");
      AUD = Companion.getInstance("AUD");
      CAD = Companion.getInstance("CAD");
      CHF = Companion.getInstance("CHF");
      CNY = Companion.getInstance("CNY");
      HKD = Companion.getInstance("HKD");
      NZD = Companion.getInstance("NZD");
      RUB = Companion.getInstance("RUB");
      INR = Companion.getInstance("INR");
      BTC = Companion.getInstance("BTC", 8);
      ETH = Companion.getInstance("ETH", 8);
      USDT = Companion.getInstance("USDT", 2);
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020%J\u0018\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020%2\u0006\u0010(\u001a\u00020)H\u0002J\u0010\u0010*\u001a\u00020+2\b\b\u0002\u0010,\u001a\u00020)R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0011\u0010\u0015\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0011\u0010\u0017\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0011\u0010\u0019\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0011\u0010\u001b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006R\u0011\u0010\u001d\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0006R\u0011\u0010\u001f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0006R\u0011\u0010!\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0006R\u001a\u0010#\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00040$X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"},
      d2 = {"Lorg/roboquant/common/Currency$Companion;", "", "()V", "AUD", "Lorg/roboquant/common/Currency;", "getAUD", "()Lorg/roboquant/common/Currency;", "BTC", "getBTC", "CAD", "getCAD", "CHF", "getCHF", "CNY", "getCNY", "ETH", "getETH", "EUR", "getEUR", "GBP", "getGBP", "HKD", "getHKD", "INR", "getINR", "JPY", "getJPY", "NZD", "getNZD", "RUB", "getRUB", "USD", "getUSD", "USDT", "getUSDT", "currencies", "Ljava/util/concurrent/ConcurrentHashMap;", "", "getInstance", "currencyCode", "defaultFractionDigits", "", "increaseDigits", "", "extraDigits", "roboquant"}
   )
   @SourceDebugExtension({"SMAP\nCurrency.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Currency.kt\norg/roboquant/common/Currency$Companion\n+ 2 MapsJVM.kt\nkotlin/collections/MapsKt__MapsJVMKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,170:1\n72#2,2:171\n72#2,2:174\n1#3:173\n1#3:176\n*S KotlinDebug\n*F\n+ 1 Currency.kt\norg/roboquant/common/Currency$Companion\n*L\n63#1:171,2\n73#1:174,2\n63#1:173\n73#1:176\n*E\n"})
   public static final class Companion {
      private Companion() {
      }

      private final Currency getInstance(String currencyCode, int defaultFractionDigits) {
         ConcurrentMap $this$getOrPut$iv = (ConcurrentMap)Currency.currencies;
         int $i$f$getOrPut = 0;
         Object var10000 = $this$getOrPut$iv.get(currencyCode);
         if (var10000 == null) {
            int var6 = 0;
            Object default$iv = new Currency(currencyCode, (DefaultConstructorMarker)null);
            int var8 = 0;
            var10000 = $this$getOrPut$iv.putIfAbsent(currencyCode, default$iv);
            if (var10000 == null) {
               var10000 = default$iv;
            }
         }

         Currency result = (Currency)var10000;
         result.setDefaultFractionDigits(defaultFractionDigits);
         Intrinsics.checkNotNull(result);
         return result;
      }

      @NotNull
      public final Currency getInstance(@NotNull String currencyCode) {
         Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
         ConcurrentMap $this$getOrPut$iv = (ConcurrentMap)Currency.currencies;
         int $i$f$getOrPut = 0;
         Object var10000 = $this$getOrPut$iv.get(currencyCode);
         if (var10000 == null) {
            int var4 = 0;
            Object default$iv = new Currency(currencyCode, (DefaultConstructorMarker)null);
            int var6 = 0;
            var10000 = $this$getOrPut$iv.putIfAbsent(currencyCode, default$iv);
            if (var10000 == null) {
               var10000 = default$iv;
            }
         }

         Intrinsics.checkNotNullExpressionValue(var10000, "getOrPut(...)");
         return (Currency)var10000;
      }

      @NotNull
      public final Currency getUSD() {
         return Currency.USD;
      }

      @NotNull
      public final Currency getEUR() {
         return Currency.EUR;
      }

      @NotNull
      public final Currency getJPY() {
         return Currency.JPY;
      }

      @NotNull
      public final Currency getGBP() {
         return Currency.GBP;
      }

      @NotNull
      public final Currency getAUD() {
         return Currency.AUD;
      }

      @NotNull
      public final Currency getCAD() {
         return Currency.CAD;
      }

      @NotNull
      public final Currency getCHF() {
         return Currency.CHF;
      }

      @NotNull
      public final Currency getCNY() {
         return Currency.CNY;
      }

      @NotNull
      public final Currency getHKD() {
         return Currency.HKD;
      }

      @NotNull
      public final Currency getNZD() {
         return Currency.NZD;
      }

      @NotNull
      public final Currency getRUB() {
         return Currency.RUB;
      }

      @NotNull
      public final Currency getINR() {
         return Currency.INR;
      }

      @NotNull
      public final Currency getBTC() {
         return Currency.BTC;
      }

      @NotNull
      public final Currency getETH() {
         return Currency.ETH;
      }

      @NotNull
      public final Currency getUSDT() {
         return Currency.USDT;
      }

      public final void increaseDigits(int extraDigits) {
         for(Currency currency : Currency.currencies.values()) {
            currency.setDefaultFractionDigits(currency.getDefaultFractionDigits() + extraDigits);
         }

      }

      // $FF: synthetic method
      public static void increaseDigits$default(Companion var0, int var1, int var2, Object var3) {
         if ((var2 & 1) != 0) {
            var1 = 3;
         }

         var0.increaseDigits(var1);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
