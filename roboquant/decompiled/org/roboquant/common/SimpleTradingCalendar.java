package org.roboquant.common;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B)\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\nJ\u0012\u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\rH\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u00062\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0004\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"},
   d2 = {"Lorg/roboquant/common/SimpleTradingCalendar;", "Lorg/roboquant/common/TradingCalendar;", "opening", "", "closing", "(Ljava/lang/String;Ljava/lang/String;)V", "Ljava/time/LocalTime;", "excludeDays", "", "Ljava/time/DayOfWeek;", "(Ljava/time/LocalTime;Ljava/time/LocalTime;Ljava/util/Set;)V", "getClosingTime", "date", "Ljava/time/LocalDate;", "getOpeningTime", "isTradingDay", "", "roboquant"}
)
public final class SimpleTradingCalendar implements TradingCalendar {
   @NotNull
   private final LocalTime opening;
   @NotNull
   private final LocalTime closing;
   @NotNull
   private final Set excludeDays;

   public SimpleTradingCalendar(@NotNull LocalTime opening, @NotNull LocalTime closing, @NotNull Set excludeDays) {
      Intrinsics.checkNotNullParameter(opening, "opening");
      Intrinsics.checkNotNullParameter(closing, "closing");
      Intrinsics.checkNotNullParameter(excludeDays, "excludeDays");
      super();
      this.opening = opening;
      this.closing = closing;
      this.excludeDays = excludeDays;
   }

   // $FF: synthetic method
   public SimpleTradingCalendar(LocalTime var1, LocalTime var2, Set var3, int var4, DefaultConstructorMarker var5) {
      if ((var4 & 1) != 0) {
         LocalTime var10000 = LocalTime.parse((CharSequence)"09:30");
         Intrinsics.checkNotNullExpressionValue(var10000, "parse(...)");
         var1 = var10000;
      }

      if ((var4 & 2) != 0) {
         LocalTime var7 = LocalTime.parse((CharSequence)"16:00");
         Intrinsics.checkNotNullExpressionValue(var7, "parse(...)");
         var2 = var7;
      }

      if ((var4 & 4) != 0) {
         DayOfWeek[] var6 = new DayOfWeek[]{DayOfWeek.SATURDAY, DayOfWeek.SUNDAY};
         var3 = SetsKt.setOf(var6);
      }

      this(var1, var2, var3);
   }

   public SimpleTradingCalendar(@NotNull String opening, @NotNull String closing) {
      Intrinsics.checkNotNullParameter(opening, "opening");
      Intrinsics.checkNotNullParameter(closing, "closing");
      LocalTime var10001 = LocalTime.parse((CharSequence)opening);
      Intrinsics.checkNotNullExpressionValue(var10001, "parse(...)");
      LocalTime var10002 = LocalTime.parse((CharSequence)closing);
      Intrinsics.checkNotNullExpressionValue(var10002, "parse(...)");
      this(var10001, var10002, (Set)null, 4, (DefaultConstructorMarker)null);
   }

   @Nullable
   public LocalTime getOpeningTime(@NotNull LocalDate date) {
      Intrinsics.checkNotNullParameter(date, "date");
      return !this.isTradingDay(date) ? null : this.opening;
   }

   @Nullable
   public LocalTime getClosingTime(@NotNull LocalDate date) {
      Intrinsics.checkNotNullParameter(date, "date");
      return !this.isTradingDay(date) ? null : this.closing;
   }

   public boolean isTradingDay(@NotNull LocalDate date) {
      Intrinsics.checkNotNullParameter(date, "date");
      return !this.excludeDays.contains(date.getDayOfWeek());
   }

   public SimpleTradingCalendar() {
      this((LocalTime)null, (LocalTime)null, (Set)null, 7, (DefaultConstructorMarker)null);
   }
}
