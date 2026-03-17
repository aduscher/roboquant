package org.roboquant.common;

import org.robok.common.RoboquantException;

import java.time.LocalDate;

/**
 * No Trading exception is thrown when time information is requested for days that there is no trading.
 * For example, what is the closing time on a Sunday?
 */
public class NoTradingException extends RoboquantException {

    private final LocalDate date;

    public NoTradingException(LocalDate date) {
        super(date + " is not a trading day");
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }
}
