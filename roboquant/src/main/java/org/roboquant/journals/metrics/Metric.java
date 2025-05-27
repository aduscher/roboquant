package org.roboquant.journals.metrics;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Account;
import org.roboquant.common.Event;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001JD\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bH&J\b\u0010\u000f\u001a\u00020\u0010H\u0016¨\u0006\u0011"},
   d2 = {"Lorg/roboquant/journals/metrics/Metric;", "", "calculate", "", "", "", "event", "Lorg/roboquant/common/Event;", "account", "Lorg/roboquant/common/Account;", "signals", "", "Lorg/roboquant/common/Signal;", "orders", "Lorg/roboquant/common/Order;", "reset", "", "roboquant"}
)
public interface Metric {
   @NotNull
   Map calculate(@NotNull Event var1, @NotNull Account var2, @NotNull List var3, @NotNull List var4);

   void reset();

   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      // $FF: synthetic method
      public static Map calculate$default(Metric var0, Event var1, Account var2, List var3, List var4, int var5, Object var6) {
         if (var6 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: calculate");
         } else {
            if ((var5 & 4) != 0) {
               var3 = CollectionsKt.emptyList();
            }

            if ((var5 & 8) != 0) {
               var4 = CollectionsKt.emptyList();
            }

            return var0.calculate(var1, var2, var3, var4);
         }
      }

      public static void reset(@NotNull Metric $this) {
      }
   }
}
