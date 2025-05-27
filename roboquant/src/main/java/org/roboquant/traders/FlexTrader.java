package org.roboquant.traders;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Account;
import org.roboquant.common.Amount;
import org.roboquant.common.Asset;
import org.roboquant.common.Event;
import org.roboquant.common.ExtensionsKt;
import org.roboquant.common.Logging;
import org.roboquant.common.Order;
import org.roboquant.common.Position;
import org.roboquant.common.PriceItem;
import org.roboquant.common.Signal;
import org.roboquant.common.Size;
import org.roboquant.common.TIF;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0000\b\u0016\u0018\u00002\u00020\u0001B \u0012\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J-\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0016H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u001bJ,\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020 H\u0016ø\u0001\u0001¢\u0006\u0004\b!\u0010\"J,\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001d0$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00180$2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010&\u001a\u00020'H\u0016J*\u0010(\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010 2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0004J \u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u00101\u001a\u000202H\u0002J\u0018\u00103\u001a\u00020.2\u0006\u0010)\u001a\u00020*2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u001b\u00104\u001a\u00020.*\b\u0012\u0004\u0012\u00020\u001d052\u0006\u0010/\u001a\u000200H\u0082\u0002R\u0014\u0010\b\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u00066"},
   d2 = {"Lorg/roboquant/traders/FlexTrader;", "Lorg/roboquant/traders/Trader;", "configure", "Lkotlin/Function1;", "Lorg/roboquant/traders/FlexPolicyConfig;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)V", "config", "getConfig", "()Lorg/roboquant/traders/FlexPolicyConfig;", "logger", "Lorg/roboquant/common/Logging$Logger;", "getLogger", "()Lorg/roboquant/common/Logging$Logger;", "amountPerOrder", "Lorg/roboquant/common/Amount;", "account", "Lorg/roboquant/common/Account;", "calcSize", "Lorg/roboquant/common/Size;", "amount", "", "signal", "Lorg/roboquant/common/Signal;", "price", "calcSize-yeTYKiM", "(DLorg/roboquant/common/Signal;D)J", "createOrder", "Lorg/roboquant/common/Order;", "size", "priceItem", "Lorg/roboquant/common/PriceItem;", "createOrder-bPajvxg", "(Lorg/roboquant/common/Signal;JLorg/roboquant/common/PriceItem;)Lorg/roboquant/common/Order;", "createOrders", "", "signals", "event", "Lorg/roboquant/common/Event;", "log", "position", "Lorg/roboquant/common/Position;", "reason", "", "meetsMinPrice", "", "asset", "Lorg/roboquant/common/Asset;", "time", "Ljava/time/Instant;", "reducedPositionSignal", "contains", "", "roboquant"}
)
@SourceDebugExtension({"SMAP\nFlexTrader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FlexTrader.kt\norg/roboquant/traders/FlexTrader\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Logging.kt\norg/roboquant/common/Logging$Logger\n*L\n1#1,243:1\n1#2:244\n1747#3,3:245\n52#4,3:248\n45#4,3:251\n38#4,3:254\n45#4,3:257\n*S KotlinDebug\n*F\n+ 1 FlexTrader.kt\norg/roboquant/traders/FlexTrader\n*L\n166#1:245,3\n170#1:248,3\n194#1:251,3\n226#1:254,3\n234#1:257,3\n*E\n"})
public class FlexTrader implements Trader {
   @NotNull
   private final Logging.Logger logger;
   @NotNull
   private final FlexPolicyConfig config;

   public FlexTrader(@NotNull Function1 configure) {
      Intrinsics.checkNotNullParameter(configure, "configure");
      super();
      this.logger = Logging.INSTANCE.getLogger(Reflection.getOrCreateKotlinClass(FlexTrader.class));
      this.config = new FlexPolicyConfig((double)0.0F, false, (String)null, 0, false, (double)0.0F, (Amount)null, 127, (DefaultConstructorMarker)null);
      configure.invoke(this.config);
   }

   // $FF: synthetic method
   public FlexTrader(Function1 var1, int var2, DefaultConstructorMarker var3) {
      if ((var2 & 1) != 0) {
         var1 = null.INSTANCE;
      }

      this(var1);
   }

   @NotNull
   protected final Logging.Logger getLogger() {
      return this.logger;
   }

   @NotNull
   protected final FlexPolicyConfig getConfig() {
      return this.config;
   }

   private final boolean reducedPositionSignal(Position position, Signal signal) {
      int var4 = 0;
      if (position.getOpen() && signal.getExit()) {
         if (position.getLong() && signal.isSell()) {
            return true;
         }

         if (position.getShort() && signal.isBuy()) {
            return true;
         }
      }

      return false;
   }

   public long calcSize_yeTYKiM/* $FF was: calcSize-yeTYKiM*/(double amount, @NotNull Signal signal, double price) {
      Intrinsics.checkNotNullParameter(signal, "signal");
      if (this.config.getFractions() < 0) {
         int var7 = 0;
         String var9 = "factions has to be >= 0, found " + this.config.getFractions();
         throw new IllegalArgumentException(var9.toString());
      } else {
         double singleContractValue = signal.getAsset().value-u_zS35g(Size.Companion.getONE-vehRhPc(), price).getValue();
         BigDecimal size = (new BigDecimal(amount / singleContractValue)).setScale(this.config.getFractions(), RoundingMode.DOWN);
         Intrinsics.checkNotNull(size);
         return Size.constructor-impl(size);
      }
   }

   @Nullable
   public Order createOrder_bPajvxg/* $FF was: createOrder-bPajvxg*/(@NotNull Signal signal, long size, @NotNull PriceItem priceItem) {
      Intrinsics.checkNotNullParameter(signal, "signal");
      Intrinsics.checkNotNullParameter(priceItem, "priceItem");
      return new Order(signal.getAsset(), size, PriceItem.DefaultImpls.getPrice$default(priceItem, (String)null, 1, (Object)null), (TIF)null, (String)null, 24, (DefaultConstructorMarker)null);
   }

   @NotNull
   public Amount amountPerOrder(@NotNull Account account) {
      Intrinsics.checkNotNullParameter(account, "account");
      return account.equityAmount().times((Number)this.config.getOrderPercentage());
   }

   private final boolean meetsMinPrice(Asset asset, double price, Instant time) {
      Amount minPrice = this.config.getMinPrice();
      return minPrice == null || minPrice.convert(asset.getCurrency(), time).getValue() <= price;
   }

   private final boolean contains(Collection $this$contains, Asset asset) {
      Intrinsics.checkNotNullParameter($this$contains, "<this>");
      Iterable $this$any$iv = (Iterable)$this$contains;
      int $i$f$any = 0;
      boolean var10000;
      if (((Collection)$this$any$iv).isEmpty()) {
         var10000 = false;
      } else {
         Iterator var5 = $this$any$iv.iterator();

         while(true) {
            if (!var5.hasNext()) {
               var10000 = false;
               break;
            }

            Object element$iv = var5.next();
            Order it = (Order)element$iv;
            int var8 = 0;
            if (Intrinsics.areEqual(it.getAsset(), asset)) {
               var10000 = true;
               break;
            }
         }
      }

      return var10000;
   }

   protected final void log(@NotNull Signal signal, @Nullable PriceItem price, @NotNull Position position, @NotNull String reason) {
      Intrinsics.checkNotNullParameter(signal, "signal");
      Intrinsics.checkNotNullParameter(position, "position");
      Intrinsics.checkNotNullParameter(reason, "reason");
      Logging.Logger $this$iv = this.logger;
      Throwable throwable$iv = null;
      int $i$f$info = 0;
      if ($this$iv.isInfoEnabled()) {
         int var8 = 0;
         $this$iv.info("signal=" + signal + " price=" + price + ", position=" + position + ", reason=" + reason, throwable$iv);
      }

   }

   @NotNull
   public List createOrders(@NotNull List signals, @NotNull Account account, @NotNull Event event) {
      Intrinsics.checkNotNullParameter(signals, "signals");
      Intrinsics.checkNotNullParameter(account, "account");
      Intrinsics.checkNotNullParameter(event, "event");
      List instructions = (List)(new ArrayList());
      if (!((Collection)signals).isEmpty()) {
         Amount equityAmount = account.equityAmount();
         Amount safetyAmount = equityAmount.times((Number)this.config.getSafetyMargin());
         Instant time = event.getTime();
         Object buyingPower = null;
         buyingPower = account.getBuyingPower().minus((Number)safetyAmount.getValue());
         Amount amountPerOrder = this.amountPerOrder(account);

         for(Signal signal : signals) {
            Asset asset = signal.getAsset();
            Position position = (Position)account.getPositions().getOrDefault(asset, Position.Companion.empty());
            PriceItem priceItem = (PriceItem)event.getPrices().get(asset);
            Logging.Logger $this$iv = this.logger;
            Throwable throwable$iv = null;
            int $i$f$debug = 0;
            if ($this$iv.isDebugEnabled()) {
               int var18 = 0;
               $this$iv.debug("signal=" + signal + " position=" + position + " buyingPower=" + buyingPower + " amount=" + amountPerOrder + " item=" + priceItem, throwable$iv);
            }

            if (priceItem == null) {
               this.log(signal, (PriceItem)null, position, "no price");
            } else if (this.config.getOneOrderOnly() && this.contains((Collection)account.getOrders(), asset)) {
               this.log(signal, priceItem, position, "one order only");
            } else {
               double price = priceItem.getPrice(this.config.getPriceType());
               if (this.reducedPositionSignal(position, signal)) {
                  Order order = this.createOrder-bPajvxg(signal, Size.unaryMinus-vehRhPc(position.getSize-vehRhPc()), priceItem);
                  ExtensionsKt.addNotNull((Collection)instructions, order);
               } else if (!position.getOpen() && signal.getEntry() && !(amountPerOrder.getValue() > ((Amount)buyingPower).getValue())) {
                  double assetAmount = amountPerOrder.convert(asset.getCurrency(), time).getValue();
                  long size = this.calcSize-yeTYKiM(assetAmount, signal, price);
                  if (!Size.getIszero-impl(size) && (!Size.isNegative-impl(size) || this.config.getShorting()) && this.meetsMinPrice(asset, price, time)) {
                     Order order = this.createOrder-bPajvxg(signal, size, priceItem);
                     if (order == null) {
                        Logging.Logger $this$iv = this.logger;
                        Throwable throwable$iv = null;
                        int $i$f$trace = 0;
                        if ($this$iv.isTraceEnabled()) {
                           int var25 = 0;
                           $this$iv.trace("no order created time=" + time + " signal=" + signal, throwable$iv);
                        }
                     } else {
                        Amount assetExposure = asset.value-u_zS35g(size, price).getAbsoluteValue();
                        double exposure = assetExposure.convert(((Amount)buyingPower).getCurrency(), time).getValue();
                        instructions.add(order);
                        buyingPower = ((Amount)buyingPower).minus((Number)exposure);
                        Logging.Logger $this$iv = this.logger;
                        Throwable throwable$iv = null;
                        int $i$f$debug = 0;
                        if ($this$iv.isDebugEnabled()) {
                           int var28 = 0;
                           $this$iv.debug("buyingPower=" + buyingPower + " exposure=" + exposure + " order=" + order, throwable$iv);
                        }
                     }
                  }
               }
            }
         }
      }

      return instructions;
   }

   public FlexTrader() {
      this((Function1)null, 1, (DefaultConstructorMarker)null);
   }
}
