package org.robok.journals

import org.robok.common.Account
import org.robok.common.EventK
import org.robok.common.Order
import org.robok.common.Signal

/**
 * Interface for tracking progress during a run
 */
interface Journal {

    /**
     * Track the progress of a particular run. This method is invoked at each step during a run.
     *
     * The passed instructions are only those instructions that were generated during this step.
     */
    fun track(event: EventK, account: Account, signals: List<Signal>, orders: List<Order>)

}


