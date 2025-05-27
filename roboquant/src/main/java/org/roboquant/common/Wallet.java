package org.roboquant.common;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0004\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 /2\u00020\u0001:\u0001/B\u001b\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\r\u001a\u00020\u0000H\u0016J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u000fJ\u0016\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0005J\u000e\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0000J\u0011\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019H\u0086\u0002J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u001cH\u0096\u0002J\u0011\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0004H\u0086\u0002J\u000e\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0004J\b\u0010\u001f\u001a\u00020 H\u0016J\u0006\u0010!\u001a\u00020\u001bJ\u0006\u0010\"\u001a\u00020\u001bJ\u0006\u0010#\u001a\u00020\u001bJ\u0011\u0010$\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u000fH\u0086\u0002J\u0011\u0010$\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0000H\u0086\u0002J\u0011\u0010%\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u000fH\u0086\u0002J\u0011\u0010%\u001a\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u0000H\u0086\u0002J\u0016\u0010&\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0005J\u0011\u0010'\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019H\u0086\u0002J\u000e\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000f0)H\u0002J\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050+J\b\u0010,\u001a\u00020-H\u0016J\u000e\u0010.\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u000fJ\u000e\u0010.\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"},
   d2 = {"Lorg/roboquant/common/Wallet;", "", "data", "Ljava/util/IdentityHashMap;", "Lorg/roboquant/common/Currency;", "", "(Ljava/util/IdentityHashMap;)V", "currencies", "", "getCurrencies", "()Ljava/util/Set;", "clear", "", "clone", "convert", "Lorg/roboquant/common/Amount;", "currency", "time", "Ljava/time/Instant;", "deposit", "amount", "value", "other", "div", "n", "", "equals", "", "", "get", "getAmount", "hashCode", "", "isEmpty", "isMultiCurrency", "isNotEmpty", "minus", "plus", "set", "times", "toAmounts", "", "toMap", "", "toString", "", "withdraw", "Companion", "roboquant"}
)
@SourceDebugExtension({"SMAP\nWallet.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Wallet.kt\norg/roboquant/common/Wallet\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,277:1\n125#2:278\n152#2,3:279\n1045#3:282\n*S KotlinDebug\n*F\n+ 1 Wallet.kt\norg/roboquant/common/Wallet\n*L\n227#1:278\n227#1:279,3\n240#1:282\n*E\n"})
public final class Wallet implements Cloneable {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final IdentityHashMap data;

   public Wallet(@NotNull IdentityHashMap data) {
      Intrinsics.checkNotNullParameter(data, "data");
      super();
      this.data = data;
   }

   // $FF: synthetic method
   public Wallet(IdentityHashMap var1, int var2, DefaultConstructorMarker var3) {
      if ((var2 & 1) != 0) {
         var1 = new IdentityHashMap(1);
      }

      this(var1);
   }

   @NotNull
   public final Set getCurrencies() {
      Set var10000 = this.data.keySet();
      Intrinsics.checkNotNullExpressionValue(var10000, "<get-keys>(...)");
      return var10000;
   }

   @NotNull
   public final Amount getAmount(@NotNull Currency currency) {
      Intrinsics.checkNotNullParameter(currency, "currency");
      return new Amount(currency, this.get(currency));
   }

   public final double get(@NotNull Currency currency) {
      Intrinsics.checkNotNullParameter(currency, "currency");
      Double var10000 = (Double)this.data.get(currency);
      return var10000 == null ? (double)0.0F : var10000;
   }

   public final boolean isEmpty() {
      return this.data.isEmpty();
   }

   public final boolean isNotEmpty() {
      return !((Map)this.data).isEmpty();
   }

   @NotNull
   public final Wallet plus(@NotNull Wallet other) {
      Intrinsics.checkNotNullParameter(other, "other");
      Wallet result = this.clone();
      result.deposit(other);
      return result;
   }

   @NotNull
   public final Wallet minus(@NotNull Wallet other) {
      Intrinsics.checkNotNullParameter(other, "other");
      Wallet result = this.clone();
      result.withdraw(other);
      return result;
   }

   @NotNull
   public final Wallet plus(@NotNull Amount amount) {
      Intrinsics.checkNotNullParameter(amount, "amount");
      Wallet result = this.clone();
      result.deposit(amount);
      return result;
   }

   @NotNull
   public final Wallet minus(@NotNull Amount amount) {
      Intrinsics.checkNotNullParameter(amount, "amount");
      Wallet result = this.clone();
      result.withdraw(amount);
      return result;
   }

   @NotNull
   public final Wallet times(@NotNull Number n) {
      Intrinsics.checkNotNullParameter(n, "n");
      Wallet result = this.clone();

      for(Map.Entry var4 : ((Map)result.data).entrySet()) {
         Currency k = (Currency)var4.getKey();
         Double v = (Double)var4.getValue();
         ((Map)result.data).put(k, v * n.doubleValue());
      }

      return result;
   }

   @NotNull
   public final Wallet div(@NotNull Number n) {
      Intrinsics.checkNotNullParameter(n, "n");
      Wallet result = this.clone();

      for(Map.Entry var4 : ((Map)result.data).entrySet()) {
         Currency k = (Currency)var4.getKey();
         Double v = (Double)var4.getValue();
         ((Map)result.data).put(k, v / n.doubleValue());
      }

      return result;
   }

   public final void set(@NotNull Currency currency, double value) {
      Intrinsics.checkNotNullParameter(currency, "currency");
      if (Math.abs(value) < 1.0E-10) {
         this.data.remove(currency);
      } else {
         Double var4 = value;
         ((Map)this.data).put(currency, var4);
      }

   }

   public final void deposit(@NotNull Amount amount) {
      Intrinsics.checkNotNullParameter(amount, "amount");
      Currency ccy = amount.getCurrency();
      Double var10000 = (Double)this.data.get(ccy);
      if (var10000 == null) {
         var10000 = (double)0.0F;
      }

      double oldValue = ((Number)var10000).doubleValue();
      this.set(ccy, amount.getValue() + oldValue);
   }

   public final void deposit(@NotNull Currency currency, double value) {
      Intrinsics.checkNotNullParameter(currency, "currency");
      Double var10000 = (Double)this.data.get(currency);
      if (var10000 == null) {
         var10000 = (double)0.0F;
      }

      double oldValue = ((Number)var10000).doubleValue();
      double newValue = value + oldValue;
      if (Math.abs(newValue) < 1.0E-10) {
         this.data.remove(currency);
      } else {
         Double var8 = newValue;
         ((Map)this.data).put(currency, var8);
      }

   }

   public final void deposit(@NotNull Wallet other) {
      Intrinsics.checkNotNullParameter(other, "other");
      boolean var2 = other != this;
      if (_Assertions.ENABLED && !var2) {
         String var7 = "Assertion failed";
         throw new AssertionError(var7);
      } else {
         for(Map.Entry var3 : ((Map)other.data).entrySet()) {
            Currency c = (Currency)var3.getKey();
            Double v = (Double)var3.getValue();
            Intrinsics.checkNotNull(c);
            Intrinsics.checkNotNull(v);
            this.deposit(c, v);
         }

      }
   }

   public final void withdraw(@NotNull Amount amount) {
      Intrinsics.checkNotNullParameter(amount, "amount");
      this.deposit(amount.getCurrency(), -amount.getValue());
   }

   public final void withdraw(@NotNull Wallet other) {
      Intrinsics.checkNotNullParameter(other, "other");
      boolean var2 = other != this;
      if (_Assertions.ENABLED && !var2) {
         String var7 = "Assertion failed";
         throw new AssertionError(var7);
      } else {
         for(Map.Entry var3 : ((Map)other.data).entrySet()) {
            Currency c = (Currency)var3.getKey();
            Double v = (Double)var3.getValue();
            Intrinsics.checkNotNull(c);
            this.deposit(c, -v);
         }

      }
   }

   public final boolean isMultiCurrency() {
      return this.getCurrencies().size() > 1;
   }

   @NotNull
   public Wallet clone() {
      Object var10002 = this.data.clone();
      Intrinsics.checkNotNull(var10002, "null cannot be cast to non-null type java.util.IdentityHashMap<org.roboquant.common.Currency, kotlin.Double>");
      return new Wallet((IdentityHashMap)var10002);
   }

   public final void clear() {
      this.data.clear();
   }

   private final List toAmounts() {
      Map $this$map$iv = (Map)this.data;
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList($this$map$iv.size()));
      int $i$f$mapTo = 0;

      for(Map.Entry item$iv$iv : $this$map$iv.entrySet()) {
         int var9 = 0;
         Object var10002 = item$iv$iv.getKey();
         Intrinsics.checkNotNullExpressionValue(var10002, "<get-key>(...)");
         Currency var11 = (Currency)var10002;
         Object var10003 = item$iv$iv.getValue();
         Intrinsics.checkNotNullExpressionValue(var10003, "<get-value>(...)");
         destination$iv$iv.add(new Amount(var11, ((Number)var10003).doubleValue()));
      }

      return (List)destination$iv$iv;
   }

   @NotNull
   public final Map toMap() {
      return MapsKt.toMap((Map)this.data);
   }

   @NotNull
   public String toString() {
      Iterable $this$sortedBy$iv = (Iterable)this.toAmounts();
      int $i$f$sortedBy = 0;
      return CollectionsKt.joinToString$default((Iterable)CollectionsKt.sortedWith($this$sortedBy$iv, new Wallet$toString$$inlined$sortedBy$1()), (CharSequence)" + ", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null);
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else {
         return !(other instanceof Wallet) ? false : Intrinsics.areEqual(this.toMap(), ((Wallet)other).toMap());
      }
   }

   public int hashCode() {
      return this.data.hashCode();
   }

   @NotNull
   public final Amount convert(@NotNull Currency currency, @NotNull Instant time) {
      Intrinsics.checkNotNullParameter(currency, "currency");
      Intrinsics.checkNotNullParameter(time, "time");
      if (this.data.size() == 1 && ((Map)this.data).containsKey(currency)) {
         Object var10003 = MapsKt.getValue((Map)this.data, currency);
         Intrinsics.checkNotNullExpressionValue(var10003, "getValue(...)");
         return new Amount(currency, ((Number)var10003).doubleValue());
      } else {
         double sum = (double)0.0F;

         for(Amount amount : this.toAmounts()) {
            sum += amount.convert(currency, time).getValue();
         }

         return new Amount(currency, sum);
      }
   }

   public Wallet() {
      this((IdentityHashMap)null, 1, (DefaultConstructorMarker)null);
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\"\u00020\u0007H\u0086\u0002¢\u0006\u0002\u0010\bJ\u0011\u0010\u0003\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0007H\u0086\u0002¨\u0006\n"},
      d2 = {"Lorg/roboquant/common/Wallet$Companion;", "", "()V", "invoke", "Lorg/roboquant/common/Wallet;", "amounts", "", "Lorg/roboquant/common/Amount;", "([Lorg/roboquant/common/Amount;)Lorg/roboquant/common/Wallet;", "amount", "roboquant"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final Wallet invoke(@NotNull Amount amount) {
         Intrinsics.checkNotNullParameter(amount, "amount");
         IdentityHashMap data = new IdentityHashMap(1);
         ((Map)data).put(amount.getCurrency(), amount.getValue());
         return new Wallet(data);
      }

      @NotNull
      public final Wallet invoke(@NotNull Amount... amounts) {
         Intrinsics.checkNotNullParameter(amounts, "amounts");
         Wallet wallet = new Wallet((IdentityHashMap)null, 1, (DefaultConstructorMarker)null);
         int var3 = 0;

         for(int var4 = amounts.length; var3 < var4; ++var3) {
            Amount amount = amounts[var3];
            wallet.deposit(amount);
         }

         return wallet;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
