package org.roboquant.common;

import java.time.Instant;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\fH\u0016J\u0010\u0010#\u001a\u00020\f2\u0006\u0010%\u001a\u00020\u0010H\u0016J\b\u0010&\u001a\u00020\u0010H\u0016J\b\u0010'\u001a\u00020\fH\u0016J!\u0010(\u001a\u00020\u00102\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040)\"\u00020\u0004H\u0016¢\u0006\u0002\u0010*J\u001d\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0004H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b.\u0010/J!\u00100\u001a\u00020\u00102\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040)\"\u00020\u0004H\u0016¢\u0006\u0002\u0010*R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u000eR\u0012\u0010\u0015\u001a\u00020\u0016X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u001e\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020 0\u001fX¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u00061"},
   d2 = {"Lorg/roboquant/common/Account;", "", "assets", "", "Lorg/roboquant/common/Asset;", "getAssets", "()Ljava/util/Set;", "baseCurrency", "Lorg/roboquant/common/Currency;", "getBaseCurrency", "()Lorg/roboquant/common/Currency;", "buyingPower", "Lorg/roboquant/common/Amount;", "getBuyingPower", "()Lorg/roboquant/common/Amount;", "cash", "Lorg/roboquant/common/Wallet;", "getCash", "()Lorg/roboquant/common/Wallet;", "cashAmount", "getCashAmount", "lastUpdate", "Ljava/time/Instant;", "getLastUpdate", "()Ljava/time/Instant;", "orders", "", "Lorg/roboquant/common/Order;", "getOrders", "()Ljava/util/List;", "positions", "", "Lorg/roboquant/common/Position;", "getPositions", "()Ljava/util/Map;", "convert", "amount", "wallet", "equity", "equityAmount", "marketValue", "", "([Lorg/roboquant/common/Asset;)Lorg/roboquant/common/Wallet;", "positionSize", "Lorg/roboquant/common/Size;", "asset", "positionSize-NKre9r8", "(Lorg/roboquant/common/Asset;)J", "unrealizedPNL", "roboquant"}
)
public interface Account {
   @NotNull
   Currency getBaseCurrency();

   @NotNull
   Instant getLastUpdate();

   @NotNull
   Wallet getCash();

   @NotNull
   List getOrders();

   @NotNull
   Map getPositions();

   @NotNull
   Amount getBuyingPower();

   @NotNull
   Amount getCashAmount();

   @NotNull
   Amount equityAmount();

   @NotNull
   Wallet equity();

   @NotNull
   Set getAssets();

   @NotNull
   Wallet marketValue(@NotNull Asset... var1);

   long positionSize_NKre9r8/* $FF was: positionSize-NKre9r8*/(@NotNull Asset var1);

   @NotNull
   Wallet unrealizedPNL(@NotNull Asset... var1);

   @NotNull
   Amount convert(@NotNull Amount var1);

   @NotNull
   Amount convert(@NotNull Wallet var1);

   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   @SourceDebugExtension({"SMAP\nAccount.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Account.kt\norg/roboquant/common/Account$DefaultImpls\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,105:1\n478#2,7:106\n478#2,7:113\n*S KotlinDebug\n*F\n+ 1 Account.kt\norg/roboquant/common/Account$DefaultImpls\n*L\n65#1:106,7\n85#1:113,7\n*E\n"})
   public static final class DefaultImpls {
      @NotNull
      public static Amount getCashAmount(@NotNull Account $this) {
         return $this.convert($this.getCash());
      }

      @NotNull
      public static Amount equityAmount(@NotNull Account $this) {
         return $this.convert($this.equity());
      }

      @NotNull
      public static Wallet equity(@NotNull Account $this) {
         return $this.getCash().plus($this.marketValue());
      }

      @NotNull
      public static Set getAssets(@NotNull Account $this) {
         return $this.getPositions().keySet();
      }

      @NotNull
      public static Wallet marketValue(@NotNull Account $this, @NotNull Asset... assets) {
         Intrinsics.checkNotNullParameter(assets, "assets");
         Map $this$filterKeys$iv = $this.getPositions();
         int $i$f$filterKeys = 0;
         LinkedHashMap result$iv = new LinkedHashMap();

         for(Map.Entry entry$iv : $this$filterKeys$iv.entrySet()) {
            Asset it = (Asset)entry$iv.getKey();
            int var9 = 0;
            if (assets.length == 0 || ArraysKt.contains(assets, it)) {
               result$iv.put(entry$iv.getKey(), entry$iv.getValue());
            }
         }

         Map v = (Map)result$iv;
         Wallet result = new Wallet((IdentityHashMap)null, 1, (DefaultConstructorMarker)null);

         for(Map.Entry var12 : v.entrySet()) {
            Asset asset = (Asset)var12.getKey();
            Position position = (Position)var12.getValue();
            Amount positionValue = asset.value-u_zS35g(position.getSize-vehRhPc(), position.getMktPrice());
            result.deposit(positionValue);
         }

         return result;
      }

      public static long positionSize_NKre9r8/* $FF was: positionSize-NKre9r8*/(@NotNull Account $this, @NotNull Asset asset) {
         Intrinsics.checkNotNullParameter(asset, "asset");
         Position var10000 = (Position)$this.getPositions().get(asset);
         return var10000 != null ? var10000.getSize-vehRhPc() : Size.Companion.getZERO-vehRhPc();
      }

      @NotNull
      public static Wallet unrealizedPNL(@NotNull Account $this, @NotNull Asset... assets) {
         Intrinsics.checkNotNullParameter(assets, "assets");
         Map $this$filterKeys$iv = $this.getPositions();
         int $i$f$filterKeys = 0;
         LinkedHashMap result$iv = new LinkedHashMap();

         for(Map.Entry entry$iv : $this$filterKeys$iv.entrySet()) {
            Asset it = (Asset)entry$iv.getKey();
            int var9 = 0;
            if (assets.length == 0 || ArraysKt.contains(assets, it)) {
               result$iv.put(entry$iv.getKey(), entry$iv.getValue());
            }
         }

         Map v = (Map)result$iv;
         Wallet result = new Wallet((IdentityHashMap)null, 1, (DefaultConstructorMarker)null);

         for(Map.Entry var12 : v.entrySet()) {
            Asset asset = (Asset)var12.getKey();
            Position position = (Position)var12.getValue();
            Amount positionValue = asset.value-u_zS35g(position.getSize-vehRhPc(), position.getMktPrice() - position.getAvgPrice());
            result.deposit(positionValue);
         }

         return result;
      }

      @NotNull
      public static Amount convert(@NotNull Account $this, @NotNull Amount amount) {
         Intrinsics.checkNotNullParameter(amount, "amount");
         return amount.convert($this.getBaseCurrency(), $this.getLastUpdate());
      }

      @NotNull
      public static Amount convert(@NotNull Account $this, @NotNull Wallet wallet) {
         Intrinsics.checkNotNullParameter(wallet, "wallet");
         return wallet.convert($this.getBaseCurrency(), $this.getLastUpdate());
      }
   }
}
