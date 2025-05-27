package org.roboquant.feeds;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlinx.coroutines.Job;
import org.jetbrains.annotations.NotNull;
import org.roboquant.common.Config;
import org.roboquant.common.TimeSpan;
import org.roboquant.common.Timeframe;
import org.roboquant.common.TimelineKt;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J*\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000e2\b\b\u0002\u0010\u0010\u001a\u00020\u0011H\u0016J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0016J \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\u00072\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0014H\u0016R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u001c\u0010\u0006\u001a\f\u0012\u0004\u0012\u00020\b0\u0007j\u0002`\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"},
   d2 = {"Lorg/roboquant/feeds/HistoricFeed;", "Lorg/roboquant/feeds/AssetFeed;", "timeframe", "Lorg/roboquant/common/Timeframe;", "getTimeframe", "()Lorg/roboquant/common/Timeframe;", "timeline", "", "Ljava/time/Instant;", "Lorg/roboquant/common/Timeline;", "getTimeline", "()Ljava/util/List;", "sample", "size", "", "samples", "random", "Lkotlin/random/Random;", "split", "period", "Lorg/roboquant/common/TimeSpan;", "overlap", "roboquant"}
)
public interface HistoricFeed extends AssetFeed {
   @NotNull
   List getTimeline();

   @NotNull
   Timeframe getTimeframe();

   @NotNull
   List sample(int var1, int var2, @NotNull Random var3);

   @NotNull
   List split(@NotNull TimeSpan var1, @NotNull TimeSpan var2);

   @NotNull
   List split(int var1);

   @Metadata(
      mv = {1, 9, 0},
      k = 3,
      xi = 48
   )
   public static final class DefaultImpls {
      @NotNull
      public static Timeframe getTimeframe(@NotNull HistoricFeed $this) {
         return TimelineKt.getTimeframe($this.getTimeline());
      }

      @NotNull
      public static List sample(@NotNull HistoricFeed $this, int size, int samples, @NotNull Random random) {
         Intrinsics.checkNotNullParameter(random, "random");
         return TimelineKt.sample($this.getTimeline(), size, samples, random);
      }

      // $FF: synthetic method
      public static List sample$default(HistoricFeed var0, int var1, int var2, Random var3, int var4, Object var5) {
         if (var5 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sample");
         } else {
            if ((var4 & 2) != 0) {
               var2 = 1;
            }

            if ((var4 & 4) != 0) {
               var3 = Config.INSTANCE.getRandom();
            }

            return var0.sample(var1, var2, var3);
         }
      }

      @NotNull
      public static List split(@NotNull HistoricFeed $this, @NotNull TimeSpan period, @NotNull TimeSpan overlap) {
         Intrinsics.checkNotNullParameter(period, "period");
         Intrinsics.checkNotNullParameter(overlap, "overlap");
         return Timeframe.split$default($this.getTimeframe(), period, overlap, false, 4, (Object)null);
      }

      // $FF: synthetic method
      public static List split$default(HistoricFeed var0, TimeSpan var1, TimeSpan var2, int var3, Object var4) {
         if (var4 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: split");
         } else {
            if ((var3 & 2) != 0) {
               var2 = TimeSpan.Companion.getZERO();
            }

            return var0.split(var1, var2);
         }
      }

      @NotNull
      public static List split(@NotNull HistoricFeed $this, int size) {
         return TimelineKt.split($this.getTimeline(), size);
      }

      public static void close(@NotNull HistoricFeed $this) {
         AssetFeed.DefaultImpls.close($this);
      }

      @NotNull
      public static Job playBackground(@NotNull HistoricFeed $this, @NotNull EventChannel channel) {
         Intrinsics.checkNotNullParameter(channel, "channel");
         return AssetFeed.DefaultImpls.playBackground($this, channel);
      }
   }
}
