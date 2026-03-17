package org.roboquant.common;

import org.robok.common.RoboquantException;

/**
 * Does not compute exception is thrown when a certain computation cannot deliver a result,
 * for example, an optimization doesn't converge.
 */
public class DoesNotComputeException extends RoboquantException {

    public DoesNotComputeException(String msg) {
        super(msg);
    }
}
