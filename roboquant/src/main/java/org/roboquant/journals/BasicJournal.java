/*
 * Copyright 2020-2026 Neural Layer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.roboquant.journals;

import org.roboquant.common.Account;
import org.roboquant.common.Event;
import org.roboquant.common.Order;
import org.roboquant.common.Signal;

import java.time.Instant;
import java.util.List;

/**
 * Basic journal implementation that tracks simple statistics.
 */
public class BasicJournal implements Journal {

    private boolean printToConsole;
    private long nItems;
    private long nOrders;
    private long nEvents;
    private int maxPositions;
    private long nSignals;
    private Instant lastTime;

    public BasicJournal() {
        this(false);
    }

    public BasicJournal(boolean printToConsole) {
        this.printToConsole = printToConsole;
    }

    public long getNItems() {
        return nItems;
    }

    public void setNItems(long nItems) {
        this.nItems = nItems;
    }

    public long getNOrders() {
        return nOrders;
    }

    public void setNOrders(long nOrders) {
        this.nOrders = nOrders;
    }

    public long getNEvents() {
        return nEvents;
    }

    public void setNEvents(long nEvents) {
        this.nEvents = nEvents;
    }

    public int getMaxPositions() {
        return maxPositions;
    }

    public void setMaxPositions(int maxPositions) {
        this.maxPositions = maxPositions;
    }

    public long getNSignals() {
        return nSignals;
    }

    public void setNSignals(long nSignals) {
        this.nSignals = nSignals;
    }

    public Instant getLastTime() {
        return lastTime;
    }

    public void setLastTime(Instant lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public void track(Event event, Account account, List<Signal> signals, List<Order> orders) {
        nItems += event.getItems().size();
        nOrders += orders.size();
        nSignals += signals.size();
        nEvents++;
        lastTime = event.getTime();
        maxPositions = Math.max(maxPositions, account.getPositions().size());
        if (printToConsole) {
            System.out.println(this);
        }
    }

    @Override
    public String toString() {
        return "time=" + lastTime + " items=" + nItems + " signals=" + nSignals + " orders=" + nOrders + " max-positions=" + maxPositions;
    }
}
