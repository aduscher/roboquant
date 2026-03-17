package org.roboquant.common;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

/**
 * Simple trading calendar that supports a fixed open and close time and optionally exclude certain days of the week.
 * There is no support for public holidays or bank holidays.
 */
public class SimpleTradingCalendar implements TradingCalendar {

    private LocalTime opening;
    private LocalTime closing;
    private Set<DayOfWeek> excludeDays;

    public SimpleTradingCalendar() {
        this.opening = LocalTime.parse("09:30");
        this.closing = LocalTime.parse("16:00");
        this.excludeDays = Set.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
    }

    public SimpleTradingCalendar(LocalTime opening, LocalTime closing, Set<DayOfWeek> excludeDays) {
        this.opening = opening;
        this.closing = closing;
        this.excludeDays = excludeDays;
    }

    public SimpleTradingCalendar(String opening, String closing) {
        this.opening = LocalTime.parse(opening);
        this.closing = LocalTime.parse(closing);
        this.excludeDays = Set.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
    }

    @Override
    public LocalTime getOpeningTime(LocalDate date) {
        return !isTradingDay(date) ? null : opening;
    }

    @Override
    public LocalTime getClosingTime(LocalDate date) {
        return !isTradingDay(date) ? null : closing;
    }

    @Override
    public boolean isTradingDay(LocalDate date) {
        return !excludeDays.contains(date.getDayOfWeek());
    }

}
