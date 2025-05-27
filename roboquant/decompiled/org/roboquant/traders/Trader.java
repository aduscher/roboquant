package org.roboquant.traders;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Account;
import org.roboquant.common.Event;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J,\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"},
   d2 = {"Lorg/roboquant/traders/Trader;", "", "createOrders", "", "Lorg/roboquant/common/Order;", "signals", "Lorg/roboquant/common/Signal;", "account", "Lorg/roboquant/common/Account;", "event", "Lorg/roboquant/common/Event;", "roboquant"}
)
public interface Trader {
   @NotNull
   List createOrders(@NotNull List var1, @NotNull Account var2, @NotNull Event var3);
}
