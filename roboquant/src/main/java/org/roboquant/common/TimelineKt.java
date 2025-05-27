package org.roboquant.common;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a#\u0010\u0007\u001a\u0004\u0018\u00010\b*\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\n\u001a#\u0010\u000b\u001a\u0004\u0018\u00010\b*\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0002\u0010\n\u001a6\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0002*\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0006\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000e\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u001a\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u0002*\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00042\u0006\u0010\r\u001a\u00020\b\"\u001f\u0010\u0000\u001a\u00020\u0001*\f\u0012\u0004\u0012\u00020\u00030\u0002j\u0002`\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006*\u0016\u0010\u0012\"\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u00020\u00030\u0002¨\u0006\u0013"},
   d2 = {"timeframe", "Lorg/roboquant/common/Timeframe;", "", "Ljava/time/Instant;", "Lorg/roboquant/common/Timeline;", "getTimeframe", "(Ljava/util/List;)Lorg/roboquant/common/Timeframe;", "earliestNotBefore", "", "time", "(Ljava/util/List;Ljava/time/Instant;)Ljava/lang/Integer;", "latestNotAfter", "sample", "size", "samples", "random", "Lkotlin/random/Random;", "split", "Timeline", "roboquant"}
)
@SourceDebugExtension({"SMAP\nTimeline.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Timeline.kt\norg/roboquant/common/TimelineKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,89:1\n1#2:90\n*E\n"})
public final class TimelineKt {
   @NotNull
   public static final List sample(@NotNull List $this$sample, int size, int samples, @NotNull Random random) {
      Intrinsics.checkNotNullParameter($this$sample, "<this>");
      Intrinsics.checkNotNullParameter(random, "random");
      List result = (List)(new ArrayList());
      int maxInt = $this$sample.size() - size;

      for(int var6 = 0; var6 < samples; ++var6) {
         int var8 = 0;
         int start = random.nextInt(maxInt);
         Timeframe sample = new Timeframe((Instant)$this$sample.get(start), (Instant)$this$sample.get(start + size), false, 4, (DefaultConstructorMarker)null);
         result.add(sample);
      }

      return result;
   }

   // $FF: synthetic method
   public static List sample$default(List var0, int var1, int var2, Random var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 1;
      }

      if ((var4 & 4) != 0) {
         var3 = Config.INSTANCE.getRandom();
      }

      return sample(var0, var1, var2, var3);
   }

   @Nullable
   public static final Integer latestNotAfter(@NotNull List $this$latestNotAfter, @NotNull Instant time) {
      Intrinsics.checkNotNullParameter($this$latestNotAfter, "<this>");
      Intrinsics.checkNotNullParameter(time, "time");
      int idx = CollectionsKt.binarySearch$default($this$latestNotAfter, (Comparable)time, 0, 0, 6, (Object)null);
      idx = idx < 0 ? -idx - 2 : idx;
      return idx >= 0 ? idx : null;
   }

   @Nullable
   public static final Integer earliestNotBefore(@NotNull List $this$earliestNotBefore, @NotNull Instant time) {
      Intrinsics.checkNotNullParameter($this$earliestNotBefore, "<this>");
      Intrinsics.checkNotNullParameter(time, "time");
      int idx = CollectionsKt.binarySearch$default($this$earliestNotBefore, (Comparable)time, 0, 0, 6, (Object)null);
      idx = idx < 0 ? -idx - 1 : idx;
      return idx < $this$earliestNotBefore.size() ? idx : null;
   }

   @NotNull
   public static final Timeframe getTimeframe(@NotNull List $this$timeframe) {
      Intrinsics.checkNotNullParameter($this$timeframe, "<this>");
      return $this$timeframe.isEmpty() ? Timeframe.Companion.getEMPTY() : new Timeframe((Instant)CollectionsKt.first($this$timeframe), (Instant)CollectionsKt.last($this$timeframe), true);
   }

   @NotNull
   public static final List split(@NotNull List $this$split, int size) {
      Intrinsics.checkNotNullParameter($this$split, "<this>");
      if (size <= 1) {
         int result = 0;
         String var7 = "Minimum requires 2 elements in timeline";
         throw new IllegalArgumentException(var7.toString());
      } else {
         List chunks = CollectionsKt.chunked((Iterable)$this$split, size);
         List result = (List)(new ArrayList());

         for(List chunk : chunks) {
            if (chunk.size() > 1) {
               result.add(new Timeframe((Instant)CollectionsKt.first(chunk), (Instant)CollectionsKt.last(chunk), false, 4, (DefaultConstructorMarker)null));
            }
         }

         result.set(CollectionsKt.getLastIndex(result), ((Timeframe)CollectionsKt.last(result)).toInclusive());
         return result;
      }
   }
}
