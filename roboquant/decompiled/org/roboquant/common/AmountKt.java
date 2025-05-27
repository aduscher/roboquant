package org.roboquant.common;

import java.util.Collection;
import java.util.IdentityHashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0004\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0000\u001a\u0010\u0010!\u001a\u00020\"*\b\u0012\u0004\u0012\u00020\u00010#\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004\"\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\n\u0010\u0004\"\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\f\u0010\u0004\"\u0015\u0010\r\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0004\"\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0004\"\u0015\u0010\u0011\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0004\"\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0004\"\u0015\u0010\u0015\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0004\"\u0015\u0010\u0017\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0004\"\u0015\u0010\u0019\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u0004\"\u0015\u0010\u001b\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0004\"\u0015\u0010\u001d\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0004\"\u0015\u0010\u001f\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b \u0010\u0004¨\u0006$"},
   d2 = {"AUD", "Lorg/roboquant/common/Amount;", "", "getAUD", "(Ljava/lang/Number;)Lorg/roboquant/common/Amount;", "BTC", "getBTC", "CAD", "getCAD", "CHF", "getCHF", "CNY", "getCNY", "ETH", "getETH", "EUR", "getEUR", "GBP", "getGBP", "HKD", "getHKD", "INR", "getINR", "JPY", "getJPY", "NZD", "getNZD", "RUB", "getRUB", "USD", "getUSD", "USDT", "getUSDT", "toWallet", "Lorg/roboquant/common/Wallet;", "", "roboquant"}
)
@SourceDebugExtension({"SMAP\nAmount.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Amount.kt\norg/roboquant/common/AmountKt\n+ 2 extensions.kt\norg/roboquant/common/ExtensionsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,237:1\n331#2,6:238\n337#2,13:245\n350#2:259\n1855#3:244\n1856#3:258\n*S KotlinDebug\n*F\n+ 1 Amount.kt\norg/roboquant/common/AmountKt\n*L\n234#1:238,6\n234#1:245,13\n234#1:259\n234#1:244\n234#1:258\n*E\n"})
public final class AmountKt {
   @NotNull
   public static final Amount getEUR(@NotNull Number $this$EUR) {
      Intrinsics.checkNotNullParameter($this$EUR, "<this>");
      return new Amount(Currency.Companion.getEUR(), $this$EUR.doubleValue());
   }

   @NotNull
   public static final Amount getUSD(@NotNull Number $this$USD) {
      Intrinsics.checkNotNullParameter($this$USD, "<this>");
      return new Amount(Currency.Companion.getUSD(), $this$USD.doubleValue());
   }

   @NotNull
   public static final Amount getJPY(@NotNull Number $this$JPY) {
      Intrinsics.checkNotNullParameter($this$JPY, "<this>");
      return new Amount(Currency.Companion.getJPY(), $this$JPY.doubleValue());
   }

   @NotNull
   public static final Amount getGBP(@NotNull Number $this$GBP) {
      Intrinsics.checkNotNullParameter($this$GBP, "<this>");
      return new Amount(Currency.Companion.getGBP(), $this$GBP.doubleValue());
   }

   @NotNull
   public static final Amount getCHF(@NotNull Number $this$CHF) {
      Intrinsics.checkNotNullParameter($this$CHF, "<this>");
      return new Amount(Currency.Companion.getCHF(), $this$CHF.doubleValue());
   }

   @NotNull
   public static final Amount getAUD(@NotNull Number $this$AUD) {
      Intrinsics.checkNotNullParameter($this$AUD, "<this>");
      return new Amount(Currency.Companion.getAUD(), $this$AUD.doubleValue());
   }

   @NotNull
   public static final Amount getCAD(@NotNull Number $this$CAD) {
      Intrinsics.checkNotNullParameter($this$CAD, "<this>");
      return new Amount(Currency.Companion.getCAD(), $this$CAD.doubleValue());
   }

   @NotNull
   public static final Amount getCNY(@NotNull Number $this$CNY) {
      Intrinsics.checkNotNullParameter($this$CNY, "<this>");
      return new Amount(Currency.Companion.getCNY(), $this$CNY.doubleValue());
   }

   @NotNull
   public static final Amount getHKD(@NotNull Number $this$HKD) {
      Intrinsics.checkNotNullParameter($this$HKD, "<this>");
      return new Amount(Currency.Companion.getHKD(), $this$HKD.doubleValue());
   }

   @NotNull
   public static final Amount getNZD(@NotNull Number $this$NZD) {
      Intrinsics.checkNotNullParameter($this$NZD, "<this>");
      return new Amount(Currency.Companion.getNZD(), $this$NZD.doubleValue());
   }

   @NotNull
   public static final Amount getRUB(@NotNull Number $this$RUB) {
      Intrinsics.checkNotNullParameter($this$RUB, "<this>");
      return new Amount(Currency.Companion.getRUB(), $this$RUB.doubleValue());
   }

   @NotNull
   public static final Amount getINR(@NotNull Number $this$INR) {
      Intrinsics.checkNotNullParameter($this$INR, "<this>");
      return new Amount(Currency.Companion.getINR(), $this$INR.doubleValue());
   }

   @NotNull
   public static final Amount getBTC(@NotNull Number $this$BTC) {
      Intrinsics.checkNotNullParameter($this$BTC, "<this>");
      return new Amount(Currency.Companion.getBTC(), $this$BTC.doubleValue());
   }

   @NotNull
   public static final Amount getETH(@NotNull Number $this$ETH) {
      Intrinsics.checkNotNullParameter($this$ETH, "<this>");
      return new Amount(Currency.Companion.getETH(), $this$ETH.doubleValue());
   }

   @NotNull
   public static final Amount getUSDT(@NotNull Number $this$USDT) {
      Intrinsics.checkNotNullParameter($this$USDT, "<this>");
      return new Amount(Currency.Companion.getUSDT(), $this$USDT.doubleValue());
   }

   @NotNull
   public static final Wallet toWallet(@NotNull Collection $this$toWallet) {
      Intrinsics.checkNotNullParameter($this$toWallet, "<this>");
      int $i$f$sumOf = 0;
      Wallet result$iv = new Wallet((IdentityHashMap)null, 1, (DefaultConstructorMarker)null);
      Wallet var10000;
      if ($this$toWallet.isEmpty()) {
         var10000 = result$iv;
      } else {
         Amount it = (Amount)CollectionsKt.first((Iterable)$this$toWallet);
         int var5 = 0;
         Currency currency$iv = it.getCurrency();
         double value$iv = (double)0.0F;
         boolean singleCurrency$iv = false;
         singleCurrency$iv = true;
         Iterable $this$forEach$iv$iv = (Iterable)$this$toWallet;
         int $i$f$forEach = 0;

         for(Object element$iv$iv : $this$forEach$iv$iv) {
            int var13 = 0;
            it = (Amount)element$iv$iv;
            var5 = 0;
            if (singleCurrency$iv) {
               if (it.getCurrency() == currency$iv) {
                  value$iv += it.getValue();
               } else {
                  singleCurrency$iv = false;
                  result$iv.deposit(new Amount(currency$iv, value$iv));
                  result$iv.deposit(it);
               }
            } else {
               result$iv.deposit(it);
            }
         }

         var10000 = singleCurrency$iv ? (new Amount(currency$iv, value$iv)).toWallet() : result$iv;
      }

      return var10000;
   }
}
