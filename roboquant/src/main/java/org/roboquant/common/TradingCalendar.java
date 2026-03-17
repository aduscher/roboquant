package org.roboquant.common;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.EnumSet;
import java.util.Set;

/**
 * Trading calendar defines when an Exchange is open for trading.
 */
public interface TradingCalendar {

   /**
    * Returns the opening time for the provided local date or null if it is not a trading day.
    */
   LocalTime getOpeningTime(LocalDate date);

   /**
    * Returns the closing time for the provided local date or null if it is not a trading day.
    */
   LocalTime getClosingTime(LocalDate date);

   /**
    * Returns true if the provided date is a trading day, false otherwise.
    */
   boolean isTradingDay(LocalDate date);


   class SimpleTradingCalendar implements TradingCalendar {

      private final LocalTime opening;
      private final LocalTime closing;
      private final Set<DayOfWeek> excludeDays;

      /**
       * Hauptkonstruktor mit allen Parametern.
       */
      public SimpleTradingCalendar(LocalTime opening, LocalTime closing, Set<DayOfWeek> excludeDays) {
         this.opening = opening;
         this.closing = closing;
         this.excludeDays = excludeDays;
      }

      /**
       * Standardkonstruktor mit Default-Werten (analog zu Kotlins Default-Argumenten).
       */
      public SimpleTradingCalendar() {
         this(LocalTime.parse("09:30"), LocalTime.parse("16:00"), 
               EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
      }

      /**
       * Hilfskonstruktor für Strings (wie im Kotlin-Code).
       */
      public SimpleTradingCalendar(String opening, String closing) {
         this(LocalTime.parse(opening), LocalTime.parse(closing), 
               EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY));
      }

      @Override
      public LocalTime getOpeningTime(LocalDate date) {
         return isTradingDay(date) ? opening : null;
      }

      @Override
      public LocalTime getClosingTime(LocalDate date) {
         return isTradingDay(date) ? closing : null;
      }

      @Override
      public boolean isTradingDay(LocalDate date) {
         return !excludeDays.contains(date.getDayOfWeek());
      }
   }

}