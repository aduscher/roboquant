package org.roboquant.brokers;

import java.time.Instant;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Amount;
import org.roboquant.common.Currency;
import org.roboquant.common.ExtensionsKt;
import org.roboquant.common.PositionKt;
import org.roboquant.common.Wallet;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B-\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"},
   d2 = {"Lorg/roboquant/brokers/MarginAccount;", "Lorg/roboquant/brokers/AccountModel;", "leverage", "", "minimum", "(DD)V", "initialMargin", "maintenanceMarginLong", "maintenanceMarginShort", "minimumEquity", "(DDDD)V", "updateAccount", "", "account", "Lorg/roboquant/brokers/InternalAccount;", "roboquant"}
)
@SourceDebugExtension({"SMAP\nMarginAccount.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MarginAccount.kt\norg/roboquant/brokers/MarginAccount\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,87:1\n1#2:88\n*E\n"})
public final class MarginAccount implements AccountModel {
   private final double initialMargin;
   private final double maintenanceMarginLong;
   private final double maintenanceMarginShort;
   private final double minimumEquity;

   public MarginAccount(double initialMargin, double maintenanceMarginLong, double maintenanceMarginShort, double minimumEquity) {
      this.initialMargin = initialMargin;
      this.maintenanceMarginLong = maintenanceMarginLong;
      this.maintenanceMarginShort = maintenanceMarginShort;
      this.minimumEquity = minimumEquity;
      double var9 = this.initialMargin;
      if (!((double)0.0F <= var9 ? var9 <= (double)1.0F : false)) {
         int var16 = 0;
         String var17 = "initialMargin between 0.0 and 1.0";
         throw new IllegalArgumentException(var17.toString());
      } else {
         var9 = this.maintenanceMarginLong;
         if (!((double)0.0F <= var9 ? var9 <= (double)1.0F : false)) {
            int var14 = 0;
            String var15 = "maintenanceMarginLong between 0.0 and 1.0";
            throw new IllegalArgumentException(var15.toString());
         } else {
            var9 = this.maintenanceMarginShort;
            if (!((double)0.0F <= var9 ? var9 <= (double)1.0F : false)) {
               int var10 = 0;
               String var13 = "maintenanceMarginShort between 0.0 and 1.0";
               throw new IllegalArgumentException(var13.toString());
            }
         }
      }
   }

   // $FF: synthetic method
   public MarginAccount(double var1, double var3, double var5, double var7, int var9, DefaultConstructorMarker var10) {
      if ((var9 & 1) != 0) {
         var1 = ExtensionsKt.getPercent((Number)50);
      }

      if ((var9 & 2) != 0) {
         var3 = ExtensionsKt.getPercent((Number)30);
      }

      if ((var9 & 4) != 0) {
         var5 = var3;
      }

      if ((var9 & 8) != 0) {
         var7 = (double)0.0F;
      }

      this(var1, var3, var5, var7);
   }

   public MarginAccount(double leverage, double minimum) {
      this((double)1.0F / leverage, (double)1.0F / leverage, (double)1.0F / leverage, minimum);
   }

   // $FF: synthetic method
   public MarginAccount(double var1, double var3, int var5, DefaultConstructorMarker var6) {
      if ((var5 & 2) != 0) {
         var3 = (double)0.0F;
      }

      this(var1, var3);
   }

   public void updateAccount(@NotNull InternalAccount account) {
      Intrinsics.checkNotNullParameter(account, "account");
      Instant time = account.getLastUpdate();
      Currency currency = account.getBaseCurrency();
      Map positions = account.getPositions();
      Wallet excessMargin = account.getCash().plus(PositionKt.marketValue(positions));
      excessMargin.withdraw(new Amount(currency, this.minimumEquity));
      Amount longExposure = PositionKt.exposure(PositionKt.getLong(positions)).convert(currency, time).times((Number)this.maintenanceMarginLong);
      excessMargin.withdraw(longExposure);
      Amount shortExposure = PositionKt.exposure(PositionKt.getShort(positions)).convert(currency, time).times((Number)this.maintenanceMarginShort);
      excessMargin.withdraw(shortExposure);
      Amount buyingPower = excessMargin.convert(currency, time).times((Number)(double)1.0F / this.initialMargin);
      account.setBuyingPower(buyingPower);
   }

   public MarginAccount() {
      this((double)0.0F, (double)0.0F, (double)0.0F, (double)0.0F, 15, (DefaultConstructorMarker)null);
   }
}
