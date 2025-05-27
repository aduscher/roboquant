package org.roboquant.brokers;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Amount;
import org.roboquant.common.PositionKt;
import org.roboquant.common.Wallet;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"},
   d2 = {"Lorg/roboquant/brokers/CashAccount;", "Lorg/roboquant/brokers/AccountModel;", "minimum", "", "(D)V", "updateAccount", "", "account", "Lorg/roboquant/brokers/InternalAccount;", "roboquant"}
)
public final class CashAccount implements AccountModel {
   private final double minimum;

   public CashAccount(double minimum) {
      this.minimum = minimum;
   }

   // $FF: synthetic method
   public CashAccount(double var1, int var3, DefaultConstructorMarker var4) {
      if ((var3 & 1) != 0) {
         var1 = (double)0.0F;
      }

      this(var1);
   }

   public void updateAccount(@NotNull InternalAccount account) {
      Intrinsics.checkNotNullParameter(account, "account");
      Wallet remaining = account.getCash().minus(PositionKt.exposure(PositionKt.getShort(account.getPositions())));
      Amount buyingPower = remaining.convert(account.getBaseCurrency(), account.getLastUpdate()).minus((Number)this.minimum);
      account.setBuyingPower(buyingPower);
   }

   public CashAccount() {
      this((double)0.0F, 1, (DefaultConstructorMarker)null);
   }
}
