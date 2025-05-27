package org.roboquant.ta;

import java.time.Instant;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Item;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"},
   d2 = {"Lorg/roboquant/ta/Indicator;", "", "calculate", "", "", "", "item", "Lorg/roboquant/common/Item;", "time", "Ljava/time/Instant;", "clear", "", "roboquant"}
)
public interface Indicator {
   @NotNull
   Map calculate(@NotNull Item var1, @NotNull Instant var2);

   void clear();

   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      public static void clear(@NotNull Indicator $this) {
      }
   }
}
