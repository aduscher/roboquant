package org.roboquant.common;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalUnit;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.random.Random;
import kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\b\u0018\u0000 D2\u00020\u0001:\u0001DB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012J\u0010\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0003H\u0002J\u0011\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0086\u0002J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\u0011\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u0003H\u0086\u0002J'\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u001f\u001a\u00020\u00062\b\u0010\u0018\u001a\u0004\u0018\u00010 HÖ\u0003J\u0018\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u00192\b\b\u0002\u0010#\u001a\u00020\u0019J\t\u0010$\u001a\u00020\u0017HÖ\u0001J\u0006\u0010%\u001a\u00020\u0006J\u0006\u0010&\u001a\u00020\u0006J\u0006\u0010'\u001a\u00020\u0006J\u000e\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020*J\u0010\u0010+\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H\u0002J\u0011\u0010,\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\u0019H\u0086\u0002J\u0011\u0010.\u001a\u00020\u00002\u0006\u0010-\u001a\u00020\u0019H\u0086\u0002J4\u0010/\u001a\b\u0012\u0004\u0012\u00020\u0000002\u0006\u0010-\u001a\u00020\u00192\b\b\u0002\u00101\u001a\u00020\u00172\n\b\u0002\u00102\u001a\u0004\u0018\u0001032\b\b\u0002\u00104\u001a\u000205J(\u00106\u001a\b\u0012\u0004\u0012\u00020\u0000002\u0006\u0010-\u001a\u00020\u00192\b\b\u0002\u00107\u001a\u00020\u00192\b\b\u0002\u00108\u001a\u00020\u0006J\u001a\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000:2\u0006\u0010;\u001a\u00020\u0012J$\u00109\u001a\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00000:2\u0006\u0010<\u001a\u00020\u00192\b\b\u0002\u00107\u001a\u00020\u0019J\u0006\u0010=\u001a\u00020\u0000J\u0006\u0010>\u001a\u00020?J\b\u0010@\u001a\u00020?H\u0016J\u0014\u0010A\u001a\b\u0012\u0004\u0012\u00020\u0003002\u0006\u0010B\u001a\u00020\u0019J\b\u0010C\u001a\u00020\u0012H\u0002R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006E"},
   d2 = {"Lorg/roboquant/common/Timeframe;", "Ljava/io/Serializable;", "start", "Ljava/time/Instant;", "end", "inclusive", "", "(Ljava/time/Instant;Ljava/time/Instant;Z)V", "duration", "Ljava/time/Duration;", "getDuration", "()Ljava/time/Duration;", "getEnd", "()Ljava/time/Instant;", "getInclusive", "()Z", "getStart", "annualize", "", "rate", "beforeEnd", "time", "compareTo", "", "other", "Lorg/roboquant/common/TimeSpan;", "component1", "component2", "component3", "contains", "copy", "equals", "", "extend", "before", "after", "hashCode", "isEmpty", "isFinite", "isInfinite", "isSingleDay", "zoneId", "Ljava/time/ZoneId;", "makeValid", "minus", "period", "plus", "sample", "", "samples", "resolution", "Ljava/time/temporal/TemporalUnit;", "random", "Lkotlin/random/Random;", "split", "overlap", "includeRemaining", "splitTwoWay", "Lkotlin/Pair;", "testSize", "offset", "toInclusive", "toPrettyString", "", "toString", "toTimeline", "step", "toYears", "Companion", "roboquant"}
)
@SourceDebugExtension({"SMAP\nTimeframe.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Timeframe.kt\norg/roboquant/common/Timeframe\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,446:1\n1#2:447\n*E\n"})
public final class Timeframe implements Serializable {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final Instant start;
   @NotNull
   private final Instant end;
   private final boolean inclusive;
   private static final long serialVersionUID = 129021321L;
   @NotNull
   private static final Instant MIN;
   @NotNull
   private static final Instant MAX;
   public static final double ONE_YEAR_MILLIS = 3.1536E10;
   @NotNull
   private static final Timeframe INFINITE;
   @NotNull
   private static final Timeframe EMPTY;
   private static final DateTimeFormatter dayFormatter;
   private static final DateTimeFormatter secondFormatter;
   private static final DateTimeFormatter milliFormatter;

   public Timeframe(@NotNull Instant start, @NotNull Instant end, boolean inclusive) {
      Intrinsics.checkNotNullParameter(start, "start");
      Intrinsics.checkNotNullParameter(end, "end");
      super();
      this.start = start;
      this.end = end;
      this.inclusive = inclusive;
      if (this.end.compareTo(this.start) < 0) {
         int var8 = 0;
         String var9 = "end time has to be larger or equal than start time, found " + this.start + " - " + this.end;
         throw new IllegalArgumentException(var9.toString());
      } else if (this.start.compareTo(MIN) < 0) {
         int var6 = 0;
         String var7 = "start time has to be larger or equal than " + MIN;
         throw new IllegalArgumentException(var7.toString());
      } else if (this.end.compareTo(MAX) > 0) {
         int var4 = 0;
         String var5 = "end time has to be smaller or equal than " + MAX;
         throw new IllegalArgumentException(var5.toString());
      }
   }

   // $FF: synthetic method
   public Timeframe(Instant var1, Instant var2, boolean var3, int var4, DefaultConstructorMarker var5) {
      if ((var4 & 4) != 0) {
         var3 = false;
      }

      this(var1, var2, var3);
   }

   @NotNull
   public final Instant getStart() {
      return this.start;
   }

   @NotNull
   public final Instant getEnd() {
      return this.end;
   }

   public final boolean getInclusive() {
      return this.inclusive;
   }

   @NotNull
   public final Duration getDuration() {
      Duration var10000 = Duration.between((Temporal)this.start, (Temporal)this.end);
      Intrinsics.checkNotNullExpressionValue(var10000, "between(...)");
      return var10000;
   }

   public final boolean isInfinite() {
      return Intrinsics.areEqual(this, INFINITE);
   }

   public final boolean isFinite() {
      return !Intrinsics.areEqual(this, INFINITE);
   }

   public final boolean isEmpty() {
      return Intrinsics.areEqual(this.start, this.end) && !this.inclusive;
   }

   private final boolean beforeEnd(Instant time) {
      return time.compareTo(this.end) < 0 || this.inclusive && Intrinsics.areEqual(time, this.end);
   }

   @NotNull
   public final Timeframe toInclusive() {
      return new Timeframe(this.start, this.end, true);
   }

   public final boolean contains(@NotNull Instant time) {
      Intrinsics.checkNotNullParameter(time, "time");
      if (this.isInfinite()) {
         return true;
      } else {
         return time.compareTo(this.start) >= 0 && this.beforeEnd(time);
      }
   }

   public final boolean isSingleDay(@NotNull ZoneId zoneId) {
      Intrinsics.checkNotNullParameter(zoneId, "zoneId");
      if (!Intrinsics.areEqual(this.start, Instant.MIN) && !Intrinsics.areEqual(this.end, Instant.MAX)) {
         Instant realEnd = this.inclusive ? this.end : this.end.minusNanos(1L);
         return Intrinsics.areEqual(this.start.atZone(zoneId).toLocalDate(), realEnd.atZone(zoneId).toLocalDate());
      } else {
         return false;
      }
   }

   @NotNull
   public final List toTimeline(@NotNull TimeSpan step) {
      Intrinsics.checkNotNullParameter(step, "step");
      List var2 = CollectionsKt.createListBuilder();
      List $this$toTimeline_u24lambda_u243 = var2;
      int var4 = 0;

      for(Instant time = this.start; this.contains(time); time = TimeSpanKt.plus(time, step)) {
         $this$toTimeline_u24lambda_u243.add(time);
      }

      return CollectionsKt.build(var2);
   }

   @NotNull
   public final Pair splitTwoWay(double testSize) {
      if (!((double)0.0F <= testSize ? testSize <= (double)1.0F : false)) {
         int var4 = 0;
         String var8 = "Test size has to between 0.0 and 1.0";
         throw new IllegalArgumentException(var8.toString());
      } else {
         long diff = this.getDuration().toMillis();
         long train = (long)((double)diff * ((double)1.0F - testSize));
         Instant border = this.start.plus(train, (TemporalUnit)ChronoUnit.MILLIS);
         Instant var10004 = this.start;
         Intrinsics.checkNotNull(border);
         return new Pair(new Timeframe(var10004, border, false, 4, (DefaultConstructorMarker)null), new Timeframe(border, this.end, this.inclusive));
      }
   }

   @NotNull
   public final Pair splitTwoWay(@NotNull TimeSpan offset, @NotNull TimeSpan overlap) {
      Intrinsics.checkNotNullParameter(offset, "offset");
      Intrinsics.checkNotNullParameter(overlap, "overlap");
      Instant border = TimeSpanKt.plus(this.start, offset);
      if (!this.contains(border)) {
         int var4 = 0;
         String var5 = "offset should be smaller than timeframe";
         throw new IllegalArgumentException(var5.toString());
      } else {
         return new Pair(new Timeframe(this.start, border, false, 4, (DefaultConstructorMarker)null), new Timeframe(TimeSpanKt.minus(border, overlap), this.end, this.inclusive));
      }
   }

   // $FF: synthetic method
   public static Pair splitTwoWay$default(Timeframe var0, TimeSpan var1, TimeSpan var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = TimeSpan.Companion.getZERO();
      }

      return var0.splitTwoWay(var1, var2);
   }

   @NotNull
   public final List split(@NotNull TimeSpan period, @NotNull TimeSpan overlap, boolean includeRemaining) {
      Intrinsics.checkNotNullParameter(period, "period");
      Intrinsics.checkNotNullParameter(overlap, "overlap");
      List var4 = CollectionsKt.createListBuilder();
      List $this$split_u24lambda_u246 = var4;
      int var6 = 0;
      Instant begin = this.start;

      Instant last;
      for(boolean done = false; !done; begin = TimeSpanKt.minus(last, overlap)) {
         last = TimeSpanKt.plus(begin, period);
         Timeframe tf = last.compareTo(this.end) < 0 ? new Timeframe(begin, last, false, 4, (DefaultConstructorMarker)null) : (Intrinsics.areEqual(last, this.end) && this.inclusive ? new Timeframe(begin, this.end, true) : (includeRemaining ? new Timeframe(begin, this.end, this.inclusive) : null));
         ExtensionsKt.addNotNull((Collection)$this$split_u24lambda_u246, tf);
         done = tf == null || Intrinsics.areEqual(tf.end, this.end);
      }

      return CollectionsKt.build(var4);
   }

   // $FF: synthetic method
   public static List split$default(Timeframe var0, TimeSpan var1, TimeSpan var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = TimeSpan.Companion.getZERO();
      }

      if ((var4 & 4) != 0) {
         var3 = true;
      }

      return var0.split(var1, var2, var3);
   }

   @NotNull
   public final List sample(@NotNull TimeSpan period, int samples, @Nullable TemporalUnit resolution, @NotNull Random random) {
      Intrinsics.checkNotNullParameter(period, "period");
      Intrinsics.checkNotNullParameter(random, "random");
      if (samples < 1) {
         int var13 = 0;
         String var14 = "samples need to be >= 1";
         throw new IllegalArgumentException(var14.toString());
      } else if (TimeSpanKt.minus(this.end, period).compareTo(this.start) <= 0) {
         int var6 = 0;
         String var12 = "period=" + period + " too large for timeframe=" + this;
         throw new IllegalArgumentException(var12.toString());
      } else {
         long duration = (new Timeframe(this.start, TimeSpanKt.minus(this.end, period), false, 4, (DefaultConstructorMarker)null)).getDuration().toMillis();
         if (duration <= (long)samples) {
            int offset = 0;
            String var16 = "not enough data to sample " + samples;
            throw new IllegalArgumentException(var16.toString());
         } else {
            Set result = (Set)(new LinkedHashSet());

            while(result.size() < samples) {
               long offset = random.nextLong(duration);
               Instant newStart = this.start.plusMillis(offset);
               if (resolution != null) {
                  newStart = newStart.truncatedTo(resolution);
               }

               Intrinsics.checkNotNull(newStart);
               Intrinsics.checkNotNull(newStart);
               result.add(new Timeframe(newStart, TimeSpanKt.plus(newStart, period), false, 4, (DefaultConstructorMarker)null));
            }

            return CollectionsKt.toList((Iterable)result);
         }
      }
   }

   // $FF: synthetic method
   public static List sample$default(Timeframe var0, TimeSpan var1, int var2, TemporalUnit var3, Random var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var2 = 1;
      }

      if ((var5 & 4) != 0) {
         var3 = (TemporalUnit)var1.resolution();
      }

      if ((var5 & 8) != 0) {
         var4 = Config.INSTANCE.getRandom();
      }

      return var0.sample(var1, var2, var3, var4);
   }

   @NotNull
   public String toString() {
      String var10000;
      if (Intrinsics.areEqual(this.start, MIN)) {
         var10000 = "MIN";
      } else if (Intrinsics.areEqual(this.start, MAX)) {
         var10000 = "MAX";
      } else {
         var10000 = this.start.toString();
         Intrinsics.checkNotNullExpressionValue(var10000, "toString(...)");
      }

      String s1 = var10000;
      if (Intrinsics.areEqual(this.end, MIN)) {
         var10000 = "MIN";
      } else if (Intrinsics.areEqual(this.end, MAX)) {
         var10000 = "MAX";
      } else {
         var10000 = this.end.toString();
         Intrinsics.checkNotNullExpressionValue(var10000, "toString(...)");
      }

      String s2 = var10000;
      char end = (char)(this.inclusive ? 93 : 62);
      return "[" + s1 + " - " + s2 + end;
   }

   @NotNull
   public final String toPrettyString() {
      long d = this.getDuration().toSeconds();
      DateTimeFormatter formatter = d < 10L ? milliFormatter : (d < 86400L ? secondFormatter : dayFormatter);
      DateTimeFormatter fmt = formatter.withZone((ZoneId)ZoneOffset.UTC);
      String s1 = Intrinsics.areEqual(this.start, MIN) ? "MIN" : (Intrinsics.areEqual(this.start, MAX) ? "MAX" : fmt.format((TemporalAccessor)this.start));
      String s2 = Intrinsics.areEqual(this.end, MIN) ? "MIN" : (Intrinsics.areEqual(this.end, MAX) ? "MAX" : fmt.format((TemporalAccessor)this.end));
      return s1 + " - " + s2;
   }

   private final Instant makeValid(Instant time) {
      return (Instant)RangesKt.coerceAtMost(RangesKt.coerceAtLeast((Comparable)time, (Comparable)MIN), (Comparable)MAX);
   }

   @NotNull
   public final Timeframe minus(@NotNull TimeSpan period) {
      Intrinsics.checkNotNullParameter(period, "period");
      Instant e = this.makeValid(TimeSpanKt.minus(this.end, period));
      boolean incl = Intrinsics.areEqual(this.end, MAX) || this.inclusive;
      return new Timeframe(this.makeValid(TimeSpanKt.minus(this.start, period)), e, incl);
   }

   @NotNull
   public final Timeframe plus(@NotNull TimeSpan period) {
      Intrinsics.checkNotNullParameter(period, "period");
      Instant e = this.makeValid(TimeSpanKt.plus(this.end, period));
      boolean incl = Intrinsics.areEqual(this.end, MAX) || this.inclusive;
      return new Timeframe(this.makeValid(TimeSpanKt.plus(this.start, period)), e, incl);
   }

   @NotNull
   public final Timeframe extend(@NotNull TimeSpan before, @NotNull TimeSpan after) {
      Intrinsics.checkNotNullParameter(before, "before");
      Intrinsics.checkNotNullParameter(after, "after");
      Instant e = this.makeValid(TimeSpanKt.plus(this.end, after));
      boolean incl = Intrinsics.areEqual(this.end, MAX) || this.inclusive;
      return new Timeframe(this.makeValid(TimeSpanKt.minus(this.start, before)), e, incl);
   }

   // $FF: synthetic method
   public static Timeframe extend$default(Timeframe var0, TimeSpan var1, TimeSpan var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var1;
      }

      return var0.extend(var1, var2);
   }

   public final double annualize(double rate) {
      return Math.pow((double)1.0F + rate, this.toYears()) - (double)1.0F;
   }

   private final double toYears() {
      long period = this.getDuration().toMillis();
      return 3.1536E10 / (double)period;
   }

   public final int compareTo(@NotNull TimeSpan other) {
      Intrinsics.checkNotNullParameter(other, "other");
      return this.getDuration().compareTo(other.getDuration$roboquant());
   }

   @NotNull
   public final Instant component1() {
      return this.start;
   }

   @NotNull
   public final Instant component2() {
      return this.end;
   }

   public final boolean component3() {
      return this.inclusive;
   }

   @NotNull
   public final Timeframe copy(@NotNull Instant start, @NotNull Instant end, boolean inclusive) {
      Intrinsics.checkNotNullParameter(start, "start");
      Intrinsics.checkNotNullParameter(end, "end");
      return new Timeframe(start, end, inclusive);
   }

   // $FF: synthetic method
   public static Timeframe copy$default(Timeframe var0, Instant var1, Instant var2, boolean var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = var0.start;
      }

      if ((var4 & 2) != 0) {
         var2 = var0.end;
      }

      if ((var4 & 4) != 0) {
         var3 = var0.inclusive;
      }

      return var0.copy(var1, var2, var3);
   }

   public int hashCode() {
      int result = this.start.hashCode();
      result = result * 31 + this.end.hashCode();
      result = result * 31 + Boolean.hashCode(this.inclusive);
      return result;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else if (!(other instanceof Timeframe)) {
         return false;
      } else {
         Timeframe var2 = (Timeframe)other;
         if (!Intrinsics.areEqual(this.start, var2.start)) {
            return false;
         } else if (!Intrinsics.areEqual(this.end, var2.end)) {
            return false;
         } else {
            return this.inclusive == var2.inclusive;
         }
      }
   }

   static {
      Instant var10000 = Instant.parse((CharSequence)"1900-01-01T00:00:00Z");
      Intrinsics.checkNotNullExpressionValue(var10000, "parse(...)");
      MIN = var10000;
      var10000 = Instant.parse((CharSequence)"2200-01-01T00:00:00Z");
      Intrinsics.checkNotNullExpressionValue(var10000, "parse(...)");
      MAX = var10000;
      INFINITE = new Timeframe(MIN, MAX, true);
      EMPTY = new Timeframe(MIN, MIN, false);
      dayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      secondFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      milliFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\b\b\u0002\u0010'\u001a\u00020(J\u000e\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020+J \u0010,\u001a\u00020\u00042\u0006\u0010$\u001a\u00020-2\u0006\u0010&\u001a\u00020-2\b\b\u0002\u0010.\u001a\u00020/J\u000e\u00100\u001a\u00020\u00042\u0006\u0010*\u001a\u00020+J\f\u00101\u001a\u00020\n*\u00020-H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u000e\u0010\u000f\u001a\u00020\u0010X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0006R\u0016\u0010\u0015\u001a\n \u0017*\u0004\u0018\u00010\u00160\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0006R\u0011\u0010\u001a\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0006R\u0016\u0010\u001c\u001a\n \u0017*\u0004\u0018\u00010\u00160\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\n \u0017*\u0004\u0018\u00010\u00160\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\u00020\u001fX\u0082T¢\u0006\b\n\u0000\u0012\u0004\b \u0010\u0002R\u0011\u0010!\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\"\u0010\u0006¨\u00062"},
      d2 = {"Lorg/roboquant/common/Timeframe$Companion;", "", "()V", "EMPTY", "Lorg/roboquant/common/Timeframe;", "getEMPTY", "()Lorg/roboquant/common/Timeframe;", "INFINITE", "getINFINITE", "MAX", "Ljava/time/Instant;", "getMAX", "()Ljava/time/Instant;", "MIN", "getMIN", "ONE_YEAR_MILLIS", "", "blackMonday1987", "getBlackMonday1987", "coronaCrash2020", "getCoronaCrash2020", "dayFormatter", "Ljava/time/format/DateTimeFormatter;", "kotlin.jvm.PlatformType", "financialCrisis2008", "getFinancialCrisis2008", "flashCrash2010", "getFlashCrash2010", "milliFormatter", "secondFormatter", "serialVersionUID", "", "getSerialVersionUID$annotations", "tenYearBullMarket2009", "getTenYearBullMarket2009", "fromYears", "first", "", "last", "zoneId", "Ljava/time/ZoneId;", "next", "period", "Lorg/roboquant/common/TimeSpan;", "parse", "", "inclusive", "", "past", "toInstant", "roboquant"}
   )
   public static final class Companion {
      private Companion() {
      }

      /** @deprecated */
      // $FF: synthetic method
      private static void getSerialVersionUID$annotations() {
      }

      @NotNull
      public final Instant getMIN() {
         return Timeframe.MIN;
      }

      @NotNull
      public final Instant getMAX() {
         return Timeframe.MAX;
      }

      @NotNull
      public final Timeframe getINFINITE() {
         return Timeframe.INFINITE;
      }

      @NotNull
      public final Timeframe getEMPTY() {
         return Timeframe.EMPTY;
      }

      @NotNull
      public final Timeframe getBlackMonday1987() {
         return parse$default(this, "1987-10-19T14:30:00Z", "1987-10-19T21:00:00Z", false, 4, (Object)null);
      }

      @NotNull
      public final Timeframe getFinancialCrisis2008() {
         return parse$default(this, "2008-09-08T00:00:00Z", "2009-03-10T00:00:00Z", false, 4, (Object)null);
      }

      @NotNull
      public final Timeframe getTenYearBullMarket2009() {
         return parse$default(this, "2009-03-10T00:00:00Z", "2019-03-10T00:00:00Z", false, 4, (Object)null);
      }

      @NotNull
      public final Timeframe getFlashCrash2010() {
         return parse$default(this, "2010-05-06T19:30:00Z", "2010-05-06T20:15:00Z", false, 4, (Object)null);
      }

      @NotNull
      public final Timeframe getCoronaCrash2020() {
         return parse$default(this, "2020-02-17T00:00:00Z", "2020-03-17T00:00:00Z", false, 4, (Object)null);
      }

      @NotNull
      public final Timeframe fromYears(int first, int last, @NotNull ZoneId zoneId) {
         Intrinsics.checkNotNullParameter(zoneId, "zoneId");
         ZonedDateTime start = ZonedDateTime.of(first, 1, 1, 0, 0, 0, 0, zoneId);
         ZonedDateTime stop = ZonedDateTime.of(last, 1, 1, 0, 0, 0, 0, zoneId);
         Instant var10002 = start.toInstant();
         Intrinsics.checkNotNullExpressionValue(var10002, "toInstant(...)");
         Instant var10003 = stop.toInstant();
         Intrinsics.checkNotNullExpressionValue(var10003, "toInstant(...)");
         return new Timeframe(var10002, var10003, false);
      }

      // $FF: synthetic method
      public static Timeframe fromYears$default(Companion var0, int var1, int var2, ZoneId var3, int var4, Object var5) {
         if ((var4 & 4) != 0) {
            ZoneOffset var10000 = ZoneOffset.UTC;
            Intrinsics.checkNotNullExpressionValue(var10000, "UTC");
            var3 = (ZoneId)var10000;
         }

         return var0.fromYears(var1, var2, var3);
      }

      private final Instant toInstant(String $this$toInstant) {
         String var10000;
         switch ($this$toInstant.length()) {
            case 4 -> var10000 = $this$toInstant + "-01-01T00:00:00Z";
            case 7 -> var10000 = $this$toInstant + "-01T00:00:00Z";
            case 10 -> var10000 = $this$toInstant + "T00:00:00Z";
            case 19 -> var10000 = $this$toInstant + "Z";
            default -> var10000 = $this$toInstant;
         }

         String fStr = var10000;
         Instant var3 = Instant.parse((CharSequence)fStr);
         Intrinsics.checkNotNullExpressionValue(var3, "parse(...)");
         return var3;
      }

      @NotNull
      public final Timeframe parse(@NotNull String first, @NotNull String last, boolean inclusive) {
         Intrinsics.checkNotNullParameter(first, "first");
         Intrinsics.checkNotNullParameter(last, "last");
         Instant start = this.toInstant(first);
         Instant stop = this.toInstant(last);
         return new Timeframe(start, stop, inclusive);
      }

      // $FF: synthetic method
      public static Timeframe parse$default(Companion var0, String var1, String var2, boolean var3, int var4, Object var5) {
         if ((var4 & 4) != 0) {
            var3 = false;
         }

         return var0.parse(var1, var2, var3);
      }

      @NotNull
      public final Timeframe past(@NotNull TimeSpan period) {
         Intrinsics.checkNotNullParameter(period, "period");
         Instant end = Instant.now();
         Intrinsics.checkNotNull(end);
         return new Timeframe(TimeSpanKt.minus(end, period), end, false, 4, (DefaultConstructorMarker)null);
      }

      @NotNull
      public final Timeframe next(@NotNull TimeSpan period) {
         Intrinsics.checkNotNullParameter(period, "period");
         Instant start = Instant.now();
         Intrinsics.checkNotNull(start);
         return new Timeframe(start, TimeSpanKt.plus(start, period), false, 4, (DefaultConstructorMarker)null);
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
