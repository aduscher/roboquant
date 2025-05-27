package org.roboquant.common;

import java.time.Instant;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAmount;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(
   mv = {1, 9, 0},
   k = 2,
   xi = 48,
   d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0015\u0010\u0015\u001a\u00020\u0016*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0001H\u0086\u0002\u001a\u001a\u0010\u0015\u001a\u00020\u0016*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0019\u001a\u0015\u0010\u0015\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u0001H\u0086\u0002\u001a\u0015\u0010\u001b\u001a\u00020\u0016*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0001H\u0086\u0002\u001a\u001a\u0010\u001b\u001a\u00020\u0016*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0019\u001a\u0015\u0010\u001b\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010\u0017\u001a\u00020\u0001H\u0086\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004\"\u0015\u0010\t\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\n\u0010\u0004\"\u0015\u0010\u000b\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\f\u0010\u0004\"\u0015\u0010\r\u001a\u00020\u0001*\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\"\u0015\u0010\u0011\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0004\"\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0004*>\b\u0007\u0010\u001c\"\u00020\u00012\u00020\u0001B0\b\u001d\u0012\b\b\u001e\u0012\u0004\b\b(\u001f\u0012\"\b \u0012\u001e\b\u000bB\u001a\b!\u0012\f\b\"\u0012\b\b\fJ\u0004\b\b(#\u0012\b\b$\u0012\u0004\b\b(%¨\u0006&"},
   d2 = {"days", "Lorg/roboquant/common/TimeSpan;", "", "getDays", "(I)Lorg/roboquant/common/TimeSpan;", "hours", "getHours", "millis", "getMillis", "minutes", "getMinutes", "months", "getMonths", "nanos", "", "getNanos", "(J)Lorg/roboquant/common/TimeSpan;", "seconds", "getSeconds", "years", "getYears", "minus", "Ljava/time/Instant;", "period", "zoneId", "Ljava/time/ZoneId;", "Ljava/time/ZonedDateTime;", "plus", "TradingPeriod", "Lkotlin/Deprecated;", "message", "Renamed to TimeSpan", "replaceWith", "Lkotlin/ReplaceWith;", "imports", "org.roboquant.common.TimeSpan", "expression", "TimeSpan", "roboquant"}
)
public final class TimeSpanKt {
   @NotNull
   public static final TimeSpan getYears(int $this$years) {
      return new TimeSpan($this$years, 0, 0, 0, 0, 0, 0L, 126, (DefaultConstructorMarker)null);
   }

   @NotNull
   public static final TimeSpan getMonths(int $this$months) {
      return new TimeSpan(0, $this$months, 0, 0, 0, 0, 0L, 124, (DefaultConstructorMarker)null);
   }

   @NotNull
   public static final TimeSpan getDays(int $this$days) {
      return new TimeSpan(0, 0, $this$days, 0, 0, 0, 0L, 120, (DefaultConstructorMarker)null);
   }

   @NotNull
   public static final TimeSpan getHours(int $this$hours) {
      return new TimeSpan(0, 0, 0, $this$hours, 0, 0, 0L, 112, (DefaultConstructorMarker)null);
   }

   @NotNull
   public static final TimeSpan getMinutes(int $this$minutes) {
      return new TimeSpan(0, 0, 0, 0, $this$minutes, 0, 0L, 96, (DefaultConstructorMarker)null);
   }

   @NotNull
   public static final TimeSpan getSeconds(int $this$seconds) {
      return new TimeSpan(0, 0, 0, 0, 0, $this$seconds, 0L);
   }

   @NotNull
   public static final TimeSpan getMillis(int $this$millis) {
      return getNanos((long)$this$millis * 1000000L);
   }

   @NotNull
   public static final TimeSpan getNanos(long $this$nanos) {
      return new TimeSpan(0, 0, 0, 0, 0, 0, $this$nanos);
   }

   @NotNull
   public static final Instant plus(@NotNull Instant $this$plus, @NotNull TimeSpan period, @NotNull ZoneId zoneId) {
      Intrinsics.checkNotNullParameter($this$plus, "<this>");
      Intrinsics.checkNotNullParameter(period, "period");
      Intrinsics.checkNotNullParameter(zoneId, "zoneId");
      if (Intrinsics.areEqual(period, TimeSpan.Companion.getZERO())) {
         return $this$plus;
      } else {
         Instant result = Intrinsics.areEqual(period.getPeriod$roboquant(), Period.ZERO) ? $this$plus : $this$plus.atZone(zoneId).plus((TemporalAmount)period.getPeriod$roboquant()).toInstant();
         Instant var10000 = result.plus((TemporalAmount)period.getDuration$roboquant());
         Intrinsics.checkNotNullExpressionValue(var10000, "plus(...)");
         return var10000;
      }
   }

   @NotNull
   public static final Instant minus(@NotNull Instant $this$minus, @NotNull TimeSpan period, @NotNull ZoneId zoneId) {
      Intrinsics.checkNotNullParameter($this$minus, "<this>");
      Intrinsics.checkNotNullParameter(period, "period");
      Intrinsics.checkNotNullParameter(zoneId, "zoneId");
      if (Intrinsics.areEqual(period, TimeSpan.Companion.getZERO())) {
         return $this$minus;
      } else {
         Instant result = Intrinsics.areEqual(period.getPeriod$roboquant(), Period.ZERO) ? $this$minus : $this$minus.atZone(zoneId).minus((TemporalAmount)period.getPeriod$roboquant()).toInstant();
         Instant var10000 = result.minus((TemporalAmount)period.getDuration$roboquant());
         Intrinsics.checkNotNullExpressionValue(var10000, "minus(...)");
         return var10000;
      }
   }

   @NotNull
   public static final Instant plus(@NotNull Instant $this$plus, @NotNull TimeSpan period) {
      Intrinsics.checkNotNullParameter($this$plus, "<this>");
      Intrinsics.checkNotNullParameter(period, "period");
      ZoneOffset var10002 = ZoneOffset.UTC;
      Intrinsics.checkNotNullExpressionValue(var10002, "UTC");
      return plus($this$plus, period, (ZoneId)var10002);
   }

   @NotNull
   public static final ZonedDateTime plus(@NotNull ZonedDateTime $this$plus, @NotNull TimeSpan period) {
      Intrinsics.checkNotNullParameter($this$plus, "<this>");
      Intrinsics.checkNotNullParameter(period, "period");
      ZonedDateTime var10000 = $this$plus.plus((TemporalAmount)period.getPeriod$roboquant()).plus((TemporalAmount)period.getDuration$roboquant());
      Intrinsics.checkNotNullExpressionValue(var10000, "plus(...)");
      return var10000;
   }

   @NotNull
   public static final Instant minus(@NotNull Instant $this$minus, @NotNull TimeSpan period) {
      Intrinsics.checkNotNullParameter($this$minus, "<this>");
      Intrinsics.checkNotNullParameter(period, "period");
      ZoneOffset var10002 = ZoneOffset.UTC;
      Intrinsics.checkNotNullExpressionValue(var10002, "UTC");
      return minus($this$minus, period, (ZoneId)var10002);
   }

   @NotNull
   public static final ZonedDateTime minus(@NotNull ZonedDateTime $this$minus, @NotNull TimeSpan period) {
      Intrinsics.checkNotNullParameter($this$minus, "<this>");
      Intrinsics.checkNotNullParameter(period, "period");
      ZonedDateTime var10000 = $this$minus.minus((TemporalAmount)period.getPeriod$roboquant()).minus((TemporalAmount)period.getDuration$roboquant());
      Intrinsics.checkNotNullExpressionValue(var10000, "minus(...)");
      return var10000;
   }

   /** @deprecated */
   // $FF: synthetic method
   @Deprecated(
      message = "Renamed to TimeSpan",
      replaceWith = @ReplaceWith(
   expression = "TimeSpan",
   imports = {"org.roboquant.common.TimeSpan"}
)
   )
   public static void TradingPeriod$annotations() {
   }
}
