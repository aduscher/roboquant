package org.roboquant.journals.metrics;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Timeframe;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0007J\u0006\u0010\u0013\u001a\u00020\u0011J\u0006\u0010\u0014\u001a\u00020\u0015R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0016"},
   d2 = {"Lorg/roboquant/journals/metrics/TimeBuffer;", "", "size", "", "(I)V", "data", "", "Ljava/time/Instant;", "getData", "()Ljava/util/List;", "getSize", "()I", "timeframe", "Lorg/roboquant/common/Timeframe;", "getTimeframe", "()Lorg/roboquant/common/Timeframe;", "add", "", "time", "clear", "eventsPerYears", "", "roboquant"}
)
final class TimeBuffer {
   private final int size;
   @NotNull
   private final List data;

   public TimeBuffer(int size) {
      this.size = size;
      this.data = (List)(new ArrayList());
   }

   public final int getSize() {
      return this.size;
   }

   @NotNull
   public final List getData() {
      return this.data;
   }

   public final void add(@NotNull Instant time) {
      Intrinsics.checkNotNullParameter(time, "time");
      this.data.add(time);
      if (this.size >= 0 && this.data.size() > this.size) {
         CollectionsKt.removeFirst(this.data);
      }

   }

   public final double eventsPerYears() {
      long avgMillis = this.getTimeframe().getDuration().toMillis() / (long)CollectionsKt.getLastIndex(this.data);
      return 3.1536E10 / (double)avgMillis;
   }

   @NotNull
   public final Timeframe getTimeframe() {
      return new Timeframe((Instant)CollectionsKt.first(this.data), (Instant)CollectionsKt.last(this.data), false, 4, (DefaultConstructorMarker)null);
   }

   public final void clear() {
      this.data.clear();
   }
}
