package org.roboquant.feeds.csv;

import java.time.Instant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.PriceItem;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0011\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0000H\u0096\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000e"},
   d2 = {"Lorg/roboquant/feeds/csv/PriceEntry;", "", "time", "Ljava/time/Instant;", "price", "Lorg/roboquant/common/PriceItem;", "(Ljava/time/Instant;Lorg/roboquant/common/PriceItem;)V", "getPrice", "()Lorg/roboquant/common/PriceItem;", "getTime", "()Ljava/time/Instant;", "compareTo", "", "other", "roboquant"}
)
public final class PriceEntry implements Comparable {
   @NotNull
   private final Instant time;
   @NotNull
   private final PriceItem price;

   public PriceEntry(@NotNull Instant time, @NotNull PriceItem price) {
      Intrinsics.checkNotNullParameter(time, "time");
      Intrinsics.checkNotNullParameter(price, "price");
      super();
      this.time = time;
      this.price = price;
   }

   @NotNull
   public final Instant getTime() {
      return this.time;
   }

   @NotNull
   public final PriceItem getPrice() {
      return this.price;
   }

   public int compareTo(@NotNull PriceEntry other) {
      Intrinsics.checkNotNullParameter(other, "other");
      return this.time.compareTo(other.time);
   }
}
