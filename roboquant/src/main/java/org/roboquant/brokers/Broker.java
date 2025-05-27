package org.roboquant.brokers;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.roboquant.common.Account;
import org.roboquant.common.Event;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0014\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\nH&¨\u0006\u000b"},
   d2 = {"Lorg/roboquant/brokers/Broker;", "", "placeOrders", "", "orders", "", "Lorg/roboquant/common/Order;", "sync", "Lorg/roboquant/common/Account;", "event", "Lorg/roboquant/common/Event;", "roboquant"}
)
public interface Broker {
   @NotNull
   Account sync(@Nullable Event var1);

   void placeOrders(@NotNull List var1);

   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      // $FF: synthetic method
      public static Account sync$default(Broker var0, Event var1, int var2, Object var3) {
         if (var3 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sync");
         } else {
            if ((var2 & 1) != 0) {
               var1 = null;
            }

            return var0.sync(var1);
         }
      }
   }
}
