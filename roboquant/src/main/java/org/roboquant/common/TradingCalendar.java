package org.roboquant.common;

import java.time.LocalDate;
import java.time.LocalTime;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(
   mv = {1, 9, 0},
   k = 1,
   xi = 48,
   d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\t"},
   d2 = {"Lorg/roboquant/common/TradingCalendar;", "", "getClosingTime", "Ljava/time/LocalTime;", "date", "Ljava/time/LocalDate;", "getOpeningTime", "isTradingDay", "", "roboquant"}
)
public interface TradingCalendar {
   @Nullable
   LocalTime getOpeningTime(@NotNull LocalDate var1);

   @Nullable
   LocalTime getClosingTime(@NotNull LocalDate var1);

   boolean isTradingDay(@NotNull LocalDate var1);
}
