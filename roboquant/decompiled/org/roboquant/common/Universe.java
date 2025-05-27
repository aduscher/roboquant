package org.roboquant.common;

import java.time.Instant;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\b"},
   d2 = {"Lorg/roboquant/common/Universe;", "", "getAssets", "", "Lorg/roboquant/common/Asset;", "time", "Ljava/time/Instant;", "Factory", "roboquant"}
)
public interface Universe {
   @NotNull
   Factory Factory = Universe.Factory.$$INSTANCE;

   @NotNull
   List getAssets(@NotNull Instant var1);

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"},
      d2 = {"Lorg/roboquant/common/Universe$Factory;", "", "()V", "sp500", "Lorg/roboquant/common/Universe;", "getSp500", "()Lorg/roboquant/common/Universe;", "sp500$delegate", "Lkotlin/Lazy;", "roboquant"}
   )
   public static final class Factory {
      // $FF: synthetic field
      static final Factory $$INSTANCE = new Factory();
      @NotNull
      private static final Lazy sp500$delegate;

      private Factory() {
      }

      @NotNull
      public final Universe getSp500() {
         Lazy var1 = sp500$delegate;
         return (Universe)var1.getValue();
      }

      static {
         sp500$delegate = LazyKt.lazy(null.INSTANCE);
      }
   }
}
