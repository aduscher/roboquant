package org.roboquant.common;

/**
 * Base class for all roboquant exceptions.
 */
public class RoboquantException extends RuntimeException {

    public RoboquantException(String msg) {
        super(msg);
    }

    public RoboquantException(String message, Throwable cause) {
        super(message, cause);
    }
}
