package org.roboquant.brokers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Account;
import org.roboquant.common.Amount;
import org.roboquant.common.Asset;
import org.roboquant.common.Currency;
import org.roboquant.common.Event;
import org.roboquant.common.Order;
import org.roboquant.common.Position;
import org.roboquant.common.PriceItem;
import org.roboquant.common.Size;
import org.roboquant.common.Wallet;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010#\u001a\u00020$J\u000e\u0010%\u001a\u00020$2\u0006\u0010&\u001a\u00020\u001aJ\u0016\u0010'\u001a\u00020$2\u0006\u0010(\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020 J\u0006\u0010*\u001a\u00020\u0001J\b\u0010+\u001a\u00020,H\u0016J\u0018\u0010-\u001a\u00020$2\u0006\u0010.\u001a\u00020/2\b\b\u0002\u00100\u001a\u00020,R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004R\u001a\u0010\b\u001a\u00020\tX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR \u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 0\u001eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u00061"},
   d2 = {"Lorg/roboquant/brokers/InternalAccount;", "Lorg/roboquant/common/Account;", "baseCurrency", "Lorg/roboquant/common/Currency;", "(Lorg/roboquant/common/Currency;)V", "getBaseCurrency", "()Lorg/roboquant/common/Currency;", "setBaseCurrency", "buyingPower", "Lorg/roboquant/common/Amount;", "getBuyingPower", "()Lorg/roboquant/common/Amount;", "setBuyingPower", "(Lorg/roboquant/common/Amount;)V", "cash", "Lorg/roboquant/common/Wallet;", "getCash", "()Lorg/roboquant/common/Wallet;", "lastUpdate", "Ljava/time/Instant;", "getLastUpdate", "()Ljava/time/Instant;", "setLastUpdate", "(Ljava/time/Instant;)V", "orders", "", "Lorg/roboquant/common/Order;", "getOrders", "()Ljava/util/List;", "positions", "", "Lorg/roboquant/common/Asset;", "Lorg/roboquant/common/Position;", "getPositions", "()Ljava/util/Map;", "clear", "", "deleteOrder", "order", "setPosition", "asset", "position", "toAccount", "toString", "", "updateMarketPrices", "event", "Lorg/roboquant/common/Event;", "priceType", "roboquant"}
)
@SourceDebugExtension({"SMAP\nInternalAccount.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InternalAccount.kt\norg/roboquant/brokers/InternalAccount\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,136:1\n125#2:137\n152#2,3:138\n*S KotlinDebug\n*F\n+ 1 InternalAccount.kt\norg/roboquant/brokers/InternalAccount\n*L\n120#1:137\n120#1:138,3\n*E\n"})
public final class InternalAccount implements Account {
   @NotNull
   private Currency baseCurrency;
   @NotNull
   private Instant lastUpdate;
   @NotNull
   private final List orders;
   @NotNull
   private final Wallet cash;
   @NotNull
   private Amount buyingPower;
   @NotNull
   private final Map positions;

   public InternalAccount(@NotNull Currency baseCurrency) {
      Intrinsics.checkNotNullParameter(baseCurrency, "baseCurrency");
      super();
      this.baseCurrency = baseCurrency;
      Instant var10001 = Instant.MIN;
      Intrinsics.checkNotNullExpressionValue(var10001, "MIN");
      this.lastUpdate = var10001;
      this.orders = (List)(new ArrayList());
      this.cash = new Wallet((IdentityHashMap)null, 1, (DefaultConstructorMarker)null);
      this.buyingPower = new Amount(this.getBaseCurrency(), (double)0.0F);
      this.positions = (Map)(new LinkedHashMap());
   }

   @NotNull
   public Currency getBaseCurrency() {
      return this.baseCurrency;
   }

   public void setBaseCurrency(@NotNull Currency var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.baseCurrency = var1;
   }

   @NotNull
   public Instant getLastUpdate() {
      return this.lastUpdate;
   }

   public void setLastUpdate(@NotNull Instant var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.lastUpdate = var1;
   }

   @NotNull
   public List getOrders() {
      return this.orders;
   }

   @NotNull
   public Wallet getCash() {
      return this.cash;
   }

   @NotNull
   public Amount getBuyingPower() {
      return this.buyingPower;
   }

   public void setBuyingPower(@NotNull Amount var1) {
      Intrinsics.checkNotNullParameter(var1, "<set-?>");
      this.buyingPower = var1;
   }

   @NotNull
   public Map getPositions() {
      return this.positions;
   }

   public final synchronized void clear() {
      Instant var10001 = Instant.MIN;
      Intrinsics.checkNotNullExpressionValue(var10001, "MIN");
      this.setLastUpdate(var10001);
      this.getOrders().clear();
      this.getPositions().clear();
      this.getCash().clear();
   }

   public final void deleteOrder(@NotNull final Order order) {
      Intrinsics.checkNotNullParameter(order, "order");
      this.getOrders().removeIf(InternalAccount::deleteOrder$lambda$0);
   }

   public final synchronized void setPosition(@NotNull Asset asset, @NotNull Position position) {
      Intrinsics.checkNotNullParameter(asset, "asset");
      Intrinsics.checkNotNullParameter(position, "position");
      if (position.getClosed()) {
         this.getPositions().remove(asset);
      } else {
         this.getPositions().put(asset, position);
      }

   }

   public final void updateMarketPrices(@NotNull Event event, @NotNull String priceType) {
      Intrinsics.checkNotNullParameter(event, "event");
      Intrinsics.checkNotNullParameter(priceType, "priceType");
      if (!this.getPositions().isEmpty()) {
         Map prices = event.getPrices();

         for(Map.Entry var5 : this.getPositions().entrySet()) {
            Asset asset = (Asset)var5.getKey();
            Position position = (Position)var5.getValue();
            PriceItem priceItem = (PriceItem)prices.get(asset);
            if (priceItem != null) {
               double price = priceItem.getPrice(priceType);
               Position newPosition = Position.copy-9NLspic$default(position, 0L, (double)0.0F, price, event.getTime(), 3, (Object)null);
               this.getPositions().put(asset, newPosition);
            }
         }

      }
   }

   // $FF: synthetic method
   public static void updateMarketPrices$default(InternalAccount var0, Event var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = "DEFAULT";
      }

      var0.updateMarketPrices(var1, var2);
   }

   @NotNull
   public final synchronized Account toAccount() {
      return this;
   }

   @NotNull
   public String toString() {
      Map $this$map$iv = this.getPositions();
      int $i$f$map = 0;
      Collection destination$iv$iv = (Collection)(new ArrayList($this$map$iv.size()));
      int $i$f$mapTo = 0;

      for(Map.Entry item$iv$iv : $this$map$iv.entrySet()) {
         int var10 = 0;
         destination$iv$iv.add(Size.toString-impl(((Position)item$iv$iv.getValue()).getSize-vehRhPc()) + "@" + ((Asset)item$iv$iv.getKey()).getSymbol());
      }

      String pString = CollectionsKt.joinToString$default((Iterable)((List)destination$iv$iv), (CharSequence)", ", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, (Function1)null, 62, (Object)null);
      String oString = CollectionsKt.joinToString$default((Iterable)this.getOrders(), (CharSequence)", ", (CharSequence)null, (CharSequence)null, 0, (CharSequence)null, null.INSTANCE, 30, (Object)null);
      Instant var10000 = this.getLastUpdate();
      return StringsKt.trimIndent("\n            last update  : " + var10000 + "\n            cash         : " + this.getCash() + "\n            buying Power : " + this.getBuyingPower() + "\n            equity       : " + this.equity() + "\n            positions    : " + pString + "\n            open orders  : " + oString + "\n        ");
   }

   @NotNull
   public Amount getCashAmount() {
      return Account.DefaultImpls.getCashAmount(this);
   }

   @NotNull
   public Amount equityAmount() {
      return Account.DefaultImpls.equityAmount(this);
   }

   @NotNull
   public Wallet equity() {
      return Account.DefaultImpls.equity(this);
   }

   @NotNull
   public Set getAssets() {
      return Account.DefaultImpls.getAssets(this);
   }

   @NotNull
   public Wallet marketValue(@NotNull Asset... assets) {
      return Account.DefaultImpls.marketValue(this, assets);
   }

   public long positionSize_NKre9r8/* $FF was: positionSize-NKre9r8*/(@NotNull Asset asset) {
      return Account.DefaultImpls.positionSize-NKre9r8(this, asset);
   }

   @NotNull
   public Wallet unrealizedPNL(@NotNull Asset... assets) {
      return Account.DefaultImpls.unrealizedPNL(this, assets);
   }

   @NotNull
   public Amount convert(@NotNull Amount amount) {
      return Account.DefaultImpls.convert(this, (Amount)amount);
   }

   @NotNull
   public Amount convert(@NotNull Wallet wallet) {
      return Account.DefaultImpls.convert(this, (Wallet)wallet);
   }

   private static final boolean deleteOrder$lambda$0(Function1 $tmp0, Object p0) {
      Intrinsics.checkNotNullParameter($tmp0, "$tmp0");
      return (Boolean)$tmp0.invoke(p0);
   }
}
