package org.roboquant.common;

import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 !2\u00020\u0001:\u0001!BM\b\u0016\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bB\u0017\b\u0000\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0018\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001a\u001a\u00020\u0003H\u0016J\u0011\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0000H\u0086\u0002J\u0011\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0000H\u0086\u0002J\u0006\u0010\u001d\u001a\u00020\u001eJ\b\u0010\u001f\u001a\u00020 H\u0016R\u0014\u0010\u000e\u001a\u00020\u000fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015R\u0014\u0010\f\u001a\u00020\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\""},
   d2 = {"Lorg/roboquant/common/TimeSpan;", "", "years", "", "months", "days", "hours", "minutes", "seconds", "nanos", "", "(IIIIIIJ)V", "period", "Ljava/time/Period;", "duration", "Ljava/time/Duration;", "(Ljava/time/Period;Ljava/time/Duration;)V", "getDuration$roboquant", "()Ljava/time/Duration;", "isZero", "", "()Z", "getPeriod$roboquant", "()Ljava/time/Period;", "equals", "other", "hashCode", "minus", "plus", "resolution", "Ljava/time/temporal/ChronoUnit;", "toString", "", "Companion", "roboquant"}
)
public final class TimeSpan {
   @NotNull
   public static final Companion Companion = new Companion((DefaultConstructorMarker)null);
   @NotNull
   private final Period period;
   @NotNull
   private final Duration duration;
   @NotNull
   private static final TimeSpan ZERO = new TimeSpan(0, 0, 0, 0, 0, 0, 0L, 127, (DefaultConstructorMarker)null);

   public TimeSpan(@NotNull Period period, @NotNull Duration duration) {
      Intrinsics.checkNotNullParameter(period, "period");
      Intrinsics.checkNotNullParameter(duration, "duration");
      super();
      this.period = period;
      this.duration = duration;
   }

   @NotNull
   public final Period getPeriod$roboquant() {
      return this.period;
   }

   @NotNull
   public final Duration getDuration$roboquant() {
      return this.duration;
   }

   public TimeSpan(int years, int months, int days, int hours, int minutes, int seconds, long nanos) {
      Period var10001 = Period.of(years, months, days).normalized();
      Intrinsics.checkNotNullExpressionValue(var10001, "normalized(...)");
      this(var10001, Companion.createDuration(hours, minutes, seconds, nanos));
   }

   // $FF: synthetic method
   public TimeSpan(int var1, int var2, int var3, int var4, int var5, int var6, long var7, int var9, DefaultConstructorMarker var10) {
      if ((var9 & 1) != 0) {
         var1 = 0;
      }

      if ((var9 & 2) != 0) {
         var2 = 0;
      }

      if ((var9 & 4) != 0) {
         var3 = 0;
      }

      if ((var9 & 8) != 0) {
         var4 = 0;
      }

      if ((var9 & 16) != 0) {
         var5 = 0;
      }

      if ((var9 & 32) != 0) {
         var6 = 0;
      }

      if ((var9 & 64) != 0) {
         var7 = 0L;
      }

      this(var1, var2, var3, var4, var5, var6, var7);
   }

   public final boolean isZero() {
      return this.period.isZero() && this.duration.isZero();
   }

   @NotNull
   public final TimeSpan plus(@NotNull TimeSpan other) {
      Intrinsics.checkNotNullParameter(other, "other");
      Period var10002 = this.period.plus((TemporalAmount)other.period).normalized();
      Intrinsics.checkNotNullExpressionValue(var10002, "normalized(...)");
      Duration var10003 = this.duration.plus(other.duration);
      Intrinsics.checkNotNullExpressionValue(var10003, "plus(...)");
      return new TimeSpan(var10002, var10003);
   }

   @NotNull
   public final TimeSpan minus(@NotNull TimeSpan other) {
      Intrinsics.checkNotNullParameter(other, "other");
      Period var10002 = this.period.minus((TemporalAmount)other.period).normalized();
      Intrinsics.checkNotNullExpressionValue(var10002, "normalized(...)");
      Duration var10003 = this.duration.minus(other.duration);
      Intrinsics.checkNotNullExpressionValue(var10003, "minus(...)");
      return new TimeSpan(var10002, var10003);
   }

   @NotNull
   public String toString() {
      Period var10000 = this.period;
      String var10001 = this.duration.toString();
      Intrinsics.checkNotNullExpressionValue(var10001, "toString(...)");
      String var1 = var10001;
      byte var2 = 1;
      var10001 = var1.substring(var2);
      Intrinsics.checkNotNullExpressionValue(var10001, "substring(...)");
      return var10000 + var10001;
   }

   public boolean equals(@Nullable Object other) {
      if (this == other) {
         return true;
      } else {
         return other instanceof TimeSpan ? Intrinsics.areEqual(this.period, ((TimeSpan)other).period) && Intrinsics.areEqual(this.duration, ((TimeSpan)other).duration) : false;
      }
   }

   public int hashCode() {
      return this.period.hashCode() + this.duration.hashCode();
   }

   @NotNull
   public final ChronoUnit resolution() {
      return this.duration.toNanosPart() != 0 ? ChronoUnit.NANOS : (this.duration.toMillisPart() != 0 ? ChronoUnit.MILLIS : (this.duration.toSecondsPart() != 0 ? ChronoUnit.SECONDS : (this.duration.toMinutesPart() != 0 ? ChronoUnit.MINUTES : (this.duration.toHoursPart() != 0 ? ChronoUnit.HOURS : ChronoUnit.DAYS))));
   }

   @Metadata(
      mv = {1, 9, 0},
      k = 1,
      xi = 48,
      d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0011R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"},
      d2 = {"Lorg/roboquant/common/TimeSpan$Companion;", "", "()V", "ZERO", "Lorg/roboquant/common/TimeSpan;", "getZERO", "()Lorg/roboquant/common/TimeSpan;", "createDuration", "Ljava/time/Duration;", "hours", "", "minutes", "seconds", "nanos", "", "parse", "text", "", "roboquant"}
   )
   public static final class Companion {
      private Companion() {
      }

      @NotNull
      public final TimeSpan getZERO() {
         return TimeSpan.ZERO;
      }

      @NotNull
      public final TimeSpan parse(@NotNull String text) {
         Intrinsics.checkNotNullParameter(text, "text");
         CharSequence var10000 = (CharSequence)text;
         char[] var3 = new char[]{'T'};
         List parts = StringsKt.split$default(var10000, var3, false, 0, 6, (Object)null);
         Period var10002 = Period.parse((CharSequence)parts.get(0));
         Intrinsics.checkNotNullExpressionValue(var10002, "parse(...)");
         Duration var10003 = Duration.parse((CharSequence)("PT" + parts.get(1)));
         Intrinsics.checkNotNullExpressionValue(var10003, "parse(...)");
         return new TimeSpan(var10002, var10003);
      }

      private final Duration createDuration(int hours, int minutes, int seconds, long nanos) {
         Duration result = Duration.ZERO;
         if (hours != 0) {
            result = result.plusHours((long)hours);
         }

         if (minutes != 0) {
            result = result.plusMinutes((long)minutes);
         }

         if (seconds != 0) {
            result = result.plusSeconds((long)seconds);
         }

         if (nanos != 0L) {
            result = result.plusNanos(nanos);
         }

         Intrinsics.checkNotNull(result);
         return result;
      }

      // $FF: synthetic method
      public Companion(DefaultConstructorMarker $constructor_marker) {
         this();
      }
   }
}
