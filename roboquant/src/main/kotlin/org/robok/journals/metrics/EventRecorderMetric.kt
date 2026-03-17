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

package org.robok.journals.metrics

import org.robok.common.Account
import org.robok.common.minus
import org.robok.common.timeframe
import org.robok.common.years
import org.roboquant.common.*
import org.roboquant.feeds.*
import java.util.*

/**
 * Record events that can then be used to later display them in a graph or perform another post-run analysis.
 * This metric also implements the [org.robok.feeds.Feed] API, so recorded events can be replayed afterwards as a normal feed.
 *
 * This metric works differently from most other metrics. It stores the events internally in memory and does not
 * return them to a MetricsLogger. However, just like other metrics, it will reset its state when `reset()` is invoked.
 *
 * @property timeSpan the timeSpan to record, default is 1 year.
 */
class EventRecorderMetric(val timeSpan: org.robok.common.TimeSpan = 1.years) : Metric, org.robok.feeds.AssetFeed {

    private val events = Collections.synchronizedList(LinkedList<org.robok.common.EventK>())

    override fun calculate(event: org.robok.common.EventK, account: Account, signals: List<org.robok.common.Signal>, orders: List<org.robok.common.Order>): Map<String, Double> {
        events.add(event)
        val cutOff = event.time - timeSpan
        synchronized(events) {
            for (entry in events.toList()) {
                if (entry.time < cutOff) events.removeFirst() else break
            }
        }
        return emptyMap()
    }

    /**
     * Reset the state
     */
    override fun reset() {
        synchronized(events) {
            events.clear()
        }
    }

    override val assets: Set<org.robok.common.Asset>
        get() = synchronized(events) {
            events.map { it.items.filterIsInstance<org.robok.common.PriceItem>().map { item -> item.asset } }.flatten()
                .toSet()
        }

    /**
     * The actual timeframe recorded so far.
     */
    override val timeframe: org.robok.common.Timeframe
        get() = events.map { it.time }.timeframe

    /**
     * Return the timeline of the events captured
     */
    val timeline: org.robok.common.Timeline
        get() = events.map { it.time }

    /**
     * Play the events recorded so far
     */
    override suspend fun play(channel: org.robok.feeds.EventChannel) {
        val localEvents = synchronized(events) { events.toList() }
        for (event in localEvents) {
            channel.send(event)
        }

    }
}
