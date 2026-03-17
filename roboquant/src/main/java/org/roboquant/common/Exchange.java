package org.roboquant.common;

import java.time.*;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Exchange contains the metadata of a marketplace or exchange.
 *
 * When creating a new Exchange instance, use the [Exchange.getInstance] method. This ensures only a single instance
 * of an exchange exists for a given exchange code and that allows for fast equality comparison.
 */
public final class Exchange {

    private final String exchangeCode;
    private final ZoneId zoneId;
    private final TradingCalendar tradingCalendar;

    private Exchange(String exchangeCode, ZoneId zoneId, TradingCalendar tradingCalendar) {
        this.exchangeCode = exchangeCode;
        this.zoneId = zoneId;
        this.tradingCalendar = tradingCalendar;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public ZoneId getZoneId() {
        return zoneId;
    }

    public boolean sameDay(Instant first, Instant second) {
        LocalDate dt1 = LocalDate.ofInstant(first, zoneId);
        LocalDate dt2 = LocalDate.ofInstant(second, zoneId);
        return dt1.equals(dt2);
    }

    public LocalDate getLocalDate(Instant time) {
        return LocalDate.ofInstant(time, zoneId);
    }

    public Instant getOpeningTime(LocalDate date) throws NoTradingException {
        LocalTime opening = tradingCalendar.getOpeningTime(date);
        if (opening == null)
            throw new NoTradingException(date);
        return ZonedDateTime.of(date, opening, zoneId).toInstant();
    }

    public Instant getClosingTime(LocalDate date) throws NoTradingException {
        LocalTime closing = tradingCalendar.getClosingTime(date);
        if (closing == null)
            throw new NoTradingException(date);
        return ZonedDateTime.of(date, closing, zoneId).toInstant();
    }

    private Timeframe getTradingHours(LocalDate date) throws NoTradingException {
        return new Timeframe(getOpeningTime(date), getClosingTime(date));
    }

    public boolean isTrading(Instant time) {
        LocalDate date = LocalDate.from(time.atZone(zoneId));
        try {
            return tradingCalendar.isTradingDay(date) && getTradingHours(date).contains(time);
        } catch (NoTradingException e) {
            return false;
        }
    }

    public Instant getInstant(LocalDateTime dateTime) {
        return ZonedDateTime.of(dateTime, zoneId).toInstant();
    }

    @Override
    public String toString() {
        return exchangeCode;
    }

    // --- Statischer Bereich (Companion Object Ersatz) ---

    private static final ConcurrentHashMap<String, Exchange> instances = new ConcurrentHashMap<>();
    private static final String NY_TIMEZONE = "America/New_York";

    public static Collection<Exchange> getExchanges() {
        return instances.values();
    }

    public static Exchange getInstance(String exchangeCode) {
        return instances.computeIfAbsent(exchangeCode, code -> 
            new Exchange(code, DEFAULT.zoneId, DEFAULT.tradingCalendar)
        );
    }

    /**
     * Java Overload für Default-Parameter (opening/closing).
     */
    public static Exchange addInstance(String exchangeCode, String zone) {
        return addInstance(exchangeCode, zone, "09:30", "16:00");
    }

    public static Exchange addInstance(String exchangeCode, String zone, String opening, String closing) {
        ZoneId zoneId = ZoneId.of(zone);
        TradingCalendar tradingCalendar = new SimpleTradingCalendar(opening, closing);
        Exchange instance = new Exchange(exchangeCode, zoneId, tradingCalendar);
        instances.put(exchangeCode, instance);
        return instance;
    }

    // Statische Instanzen
    public static final Exchange DEFAULT = addInstance("", NY_TIMEZONE);
    public static final Exchange US = addInstance("US", NY_TIMEZONE);
    public static final Exchange NYSE = addInstance("NYSE", NY_TIMEZONE);
    public static final Exchange NASDAQ = addInstance("NASDAQ", NY_TIMEZONE);
    public static final Exchange BATS = addInstance("BATS", NY_TIMEZONE);
    public static final Exchange CBOE = addInstance("CBOE", NY_TIMEZONE);
    public static final Exchange ARCA = addInstance("ARCA", NY_TIMEZONE);
    public static final Exchange AMEX = addInstance("AMEX", NY_TIMEZONE);
    
    public static final Exchange TSX = addInstance("TSX", "America/Toronto");
    public static final Exchange AEB = addInstance("AEB", "Europe/Amsterdam", "09:00", "17:30");
    public static final Exchange LSE = addInstance("LSE", "Europe/London", "08:00", "16:30");
    public static final Exchange DEX = addInstance("DEX", "Europe/Berlin", "09:00", "17:30");
    public static final Exchange SIX = addInstance("SIX", "Europe/Zurich", "09:00", "17:20");
    public static final Exchange PAR = addInstance("PAR", "Europe/Paris", "09:00", "17:30");
    public static final Exchange JPX = addInstance("JPX", "Asia/Tokyo", "09:00", "15:00");
    public static final Exchange SSE = addInstance("SSE", "Asia/Shanghai", "09:30", "15:00");
    public static final Exchange SEKH = addInstance("SEHK", "Asia/Hong_Kong", "09:30", "16:00");
    public static final Exchange SSX = addInstance("SSX", "Australia/Sydney", "10:00", "16:00");
    
    public static final Exchange CRYPTO = addInstance("CRYPTO", "UTC", "00:00", "23:59:59.999");
    public static final Exchange FOREX = addInstance("FOREX", "UTC", "00:00", "23:59:59.999");

}
