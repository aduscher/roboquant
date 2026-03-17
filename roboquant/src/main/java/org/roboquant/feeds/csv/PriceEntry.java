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

package org.roboquant.feeds.csv;

import org.robok.common.PriceItem;

import java.time.Instant;

/**
 * Internal class to hold a time & price entry
 */
public class PriceEntry implements Comparable<org.robok.feeds.csv.PriceEntry> {

    private final Instant time;
    private final PriceItem price;

    public PriceEntry(Instant time, PriceItem price) {
        this.time = time;
        this.price = price;
    }

    public Instant getTime() {
        return time;
    }

    public PriceItem getPrice() {
        return price;
    }

    @Override
    public int compareTo(org.robok.feeds.csv.PriceEntry other) {
        return time.compareTo(other.time);
    }

    @Override
    public String toString() {
        return "PriceEntry{" + "time=" + time + ", price=" + price + '}';
    }
}
