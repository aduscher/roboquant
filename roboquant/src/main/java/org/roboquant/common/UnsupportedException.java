package org.roboquant.common;

import org.robok.common.RoboquantException;

/**
 * Unsupported exception.
 */
public class UnsupportedException extends RoboquantException {

    public UnsupportedException(String msg) {
        super(msg);
    }
}
