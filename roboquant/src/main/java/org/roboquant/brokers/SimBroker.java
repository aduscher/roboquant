package org.roboquant.brokers;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin._Assertions;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Account;
import org.roboquant.common.Amount;
import org.roboquant.common.AmountKt;
import org.roboquant.common.Asset;
import org.roboquant.common.Currency;
import org.roboquant.common.Event;
import org.roboquant.common.Logging;
import org.roboquant.common.Order;
import org.roboquant.common.Position;
import org.roboquant.common.Size;
import org.roboquant.common.TIF;
import org.roboquant.common.Wallet;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006BC\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001dH\u0002J\u0018\u0010!\u001a\u00020\"2\u0006\u0010 \u001a\u00020\u001d2\u0006\u0010#\u001a\u00020$H\u0002J\u0016\u0010%\u001a\u00020\u001f2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001d0'H\u0016J\u0006\u0010(\u001a\u00020\u001fJ\u0010\u0010)\u001a\u00020\u001f2\u0006\u0010*\u001a\u00020+H\u0002J\u0012\u0010,\u001a\u00020-2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J(\u0010.\u001a\u00020\u001f2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000204ø\u0001\u0000¢\u0006\u0004\b5\u00106R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00067"},
   d2 = {"Lorg/roboquant/brokers/SimBroker;", "Lorg/roboquant/brokers/Broker;", "deposit", "", "currencyCode", "", "(Ljava/lang/Number;Ljava/lang/String;)V", "initialDeposit", "Lorg/roboquant/common/Wallet;", "baseCurrency", "Lorg/roboquant/common/Currency;", "accountModel", "Lorg/roboquant/brokers/AccountModel;", "orderEntry", "", "Ljava/time/LocalDate;", "exchangeZoneId", "Ljava/time/ZoneId;", "(Lorg/roboquant/common/Wallet;Lorg/roboquant/common/Currency;Lorg/roboquant/brokers/AccountModel;Ljava/util/Map;Ljava/time/ZoneId;)V", "account", "Lorg/roboquant/brokers/InternalAccount;", "getInitialDeposit", "()Lorg/roboquant/common/Wallet;", "logger", "Lorg/roboquant/common/Logging$Logger;", "nextOrderId", "", "pendingOrders", "", "Lorg/roboquant/common/Order;", "deleteOrder", "", "order", "isExpired", "", "time", "Ljava/time/Instant;", "placeOrders", "orders", "", "reset", "simulateMarket", "event", "Lorg/roboquant/common/Event;", "sync", "Lorg/roboquant/common/Account;", "updatePosition", "asset", "Lorg/roboquant/common/Asset;", "size", "Lorg/roboquant/common/Size;", "price", "", "updatePosition-bPajvxg", "(Lorg/roboquant/common/Asset;JD)V", "roboquant"}
)
@SourceDebugExtension({"SMAP\nSimBroker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SimBroker.kt\norg/roboquant/brokers/SimBroker\n+ 2 Logging.kt\norg/roboquant/common/Logging$Logger\n*L\n1#1,184:1\n38#2,3:185\n*S KotlinDebug\n*F\n+ 1 SimBroker.kt\norg/roboquant/brokers/SimBroker\n*L\n168#1:185,3\n*E\n"})
public class SimBroker implements Broker {
   @NotNull
   private final Wallet initialDeposit;
   @NotNull
   private final AccountModel accountModel;
   @NotNull
   private final Map orderEntry;
   @NotNull
   private final ZoneId exchangeZoneId;
   @NotNull
   private final List pendingOrders;
   @NotNull
   private final InternalAccount account;
   @NotNull
   private final Logging.Logger logger;
   private int nextOrderId;

   public SimBroker(@NotNull Wallet initialDeposit, @NotNull Currency baseCurrency, @NotNull AccountModel accountModel, @NotNull Map orderEntry, @NotNull ZoneId exchangeZoneId) {
      Intrinsics.checkNotNullParameter(initialDeposit, "initialDeposit");
      Intrinsics.checkNotNullParameter(baseCurrency, "baseCurrency");
      Intrinsics.checkNotNullParameter(accountModel, "accountModel");
      Intrinsics.checkNotNullParameter(orderEntry, "orderEntry");
      Intrinsics.checkNotNullParameter(exchangeZoneId, "exchangeZoneId");
      super();
      this.initialDeposit = initialDeposit;
      this.accountModel = accountModel;
      this.orderEntry = orderEntry;
      this.exchangeZoneId = exchangeZoneId;
      this.pendingOrders = (List)(new ArrayList());
      this.account = new InternalAccount(baseCurrency);
      this.logger = Logging.INSTANCE.getLogger(Reflection.getOrCreateKotlinClass(SimBroker.class));
      this.reset();
   }

   // $FF: synthetic method
   public SimBroker(Wallet var1, Currency var2, AccountModel var3, Map var4, ZoneId var5, int var6, DefaultConstructorMarker var7) {
      if ((var6 & 1) != 0) {
         var1 = Wallet.Companion.invoke(AmountKt.getUSD((Number)(double)1000000.0F));
      }

      if ((var6 & 2) != 0) {
         var2 = (Currency)CollectionsKt.first((Iterable)var1.getCurrencies());
      }

      if ((var6 & 4) != 0) {
         var3 = new CashAccount((double)0.0F, 1, (DefaultConstructorMarker)null);
      }

      if ((var6 & 8) != 0) {
         var4 = (Map)(new LinkedHashMap());
      }

      if ((var6 & 16) != 0) {
         ZoneId var10000 = ZoneId.of("UTC");
         Intrinsics.checkNotNullExpressionValue(var10000, "of(...)");
         var5 = var10000;
      }

      this(var1, var2, var3, var4, var5);
   }

   @NotNull
   public final Wallet getInitialDeposit() {
      return this.initialDeposit;
   }

   public SimBroker(@NotNull Number deposit, @NotNull String currencyCode) {
      Intrinsics.checkNotNullParameter(deposit, "deposit");
      Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
      this((new Amount(Currency.Companion.getInstance(currencyCode), deposit)).toWallet(), (Currency)null, (AccountModel)null, (Map)null, (ZoneId)null, 30, (DefaultConstructorMarker)null);
   }

   // $FF: synthetic method
   public SimBroker(Number var1, String var2, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 2) != 0) {
         var2 = "USD";
      }

      this(var1, var2);
   }

   private final void deleteOrder(Order order) {
      this.account.deleteOrder(order);
   }

   public final void updatePosition_bPajvxg/* $FF was: updatePosition-bPajvxg*/(@NotNull Asset asset, long size, double price) {
      Intrinsics.checkNotNullParameter(asset, "asset");
      Position position = (Position)this.account.getPositions().get(asset);
      if (position == null) {
         this.account.getPositions().put(asset, new Position(size, price, price, (Instant)null, 8, (DefaultConstructorMarker)null));
      } else {
         long newSize = Size.plus-d9a907g(position.getSize-vehRhPc(), size);
         double avgPrice = Size.getSign-impl(size) != Size.getSign-impl(newSize) ? price : (Size.compareTo-ZWO118U(Size.getAbsoluteValue-vehRhPc(newSize), Size.getAbsoluteValue-vehRhPc(size)) > 0 ? (Size.toDouble-impl(size) * position.getAvgPrice() + Size.toDouble-impl(size) * price) / Size.toDouble-impl(newSize) : position.getAvgPrice());
         this.account.getPositions().put(asset, new Position(newSize, avgPrice, price, (Instant)null, 8, (DefaultConstructorMarker)null));
      }

   }

   private final boolean isExpired(Order order, Instant time) {
      if (order.getTif() == TIF.GTC) {
         return false;
      } else {
         LocalDate orderDate = (LocalDate)this.orderEntry.get(order.getId());
         if (orderDate != null) {
            LocalDate currentDate = LocalDate.ofInstant(time, this.exchangeZoneId);
            return currentDate.compareTo((ChronoLocalDate)orderDate) > 0;
         } else {
            Map currentDate = this.orderEntry;
            String var5 = order.getId();
            LocalDate var10000 = LocalDate.ofInstant(time, this.exchangeZoneId);
            Intrinsics.checkNotNullExpressionValue(var10000, "ofInstant(...)");
            LocalDate var6 = var10000;
            currentDate.put(var5, var6);
            return false;
         }
      }
   }

   private final void simulateMarket(Event event) {
      Instant time = event.getTime();

      for(Order order : CollectionsKt.toList((Iterable)this.account.getOrders())) {
         if (this.isExpired(order, time)) {
            this.deleteOrder(order);
         } else {
            Double price = Event.getPrice$default(event, order.getAsset(), (String)null, 2, (Object)null);
            if (price != null && order.isExecutable(price)) {
               this.updatePosition-bPajvxg(order.getAsset(), order.getSize-vehRhPc(), price);
               Amount value = order.getAsset().value-u_zS35g(order.getSize-vehRhPc(), price);
               this.account.getCash().withdraw(value);
               this.deleteOrder(order);
            }
         }
      }

   }

   @NotNull
   public synchronized Account sync(@Nullable Event event) {
      for(final Order order : this.pendingOrders) {
         if (order.isCancellation()) {
            boolean removed = CollectionsKt.removeAll(this.account.getOrders(), new Function1() {
               @NotNull
               public final Boolean invoke(@NotNull Order it) {
                  Intrinsics.checkNotNullParameter(it, "it");
                  return Intrinsics.areEqual(it.getId(), order.getId());
               }
            });
            if (!removed) {
               this.logger.warn("Skipping cancellation " + order);
            }
         } else if (order.isModify()) {
            boolean removed = this.account.getOrders().removeIf(SimBroker::sync$lambda$0);
            if (removed) {
               this.account.getOrders().add(order);
            } else {
               this.logger.warn("Skipping modify " + order);
            }
         } else {
            int var7 = StringsKt.isBlank((CharSequence)order.getId());
            if (_Assertions.ENABLED && !var7) {
               String var5 = "Assertion failed";
               throw new AssertionError(var5);
            }

            var7 = this.nextOrderId++;
            order.setId(String.valueOf(var7));
            this.account.getOrders().add(order);
         }
      }

      this.pendingOrders.clear();
      if (event != null) {
         this.simulateMarket(event);
         InternalAccount.updateMarketPrices$default(this.account, event, (String)null, 2, (Object)null);
         this.account.setLastUpdate(event.getTime());
         this.accountModel.updateAccount(this.account);
      }

      return this.account.toAccount();
   }

   public synchronized void placeOrders(@NotNull List orders) {
      Intrinsics.checkNotNullParameter(orders, "orders");
      Logging.Logger $this$iv = this.logger;
      Throwable throwable$iv = null;
      int $i$f$trace = 0;
      if ($this$iv.isTraceEnabled()) {
         int var5 = 0;
         $this$iv.trace("Received orders=" + orders.size(), throwable$iv);
      }

      this.pendingOrders.addAll((Collection)orders);
   }

   public final void reset() {
      this.account.clear();
      this.account.getCash().deposit(this.initialDeposit);
      this.accountModel.updateAccount(this.account);
   }

   private static final boolean sync$lambda$0(Function1 $tmp0, Object p0) {
      Intrinsics.checkNotNullParameter($tmp0, "$tmp0");
      return (Boolean)$tmp0.invoke(p0);
   }

   public SimBroker() {
      this((Wallet)null, (Currency)null, (AccountModel)null, (Map)null, (ZoneId)null, 31, (DefaultConstructorMarker)null);
   }
}
