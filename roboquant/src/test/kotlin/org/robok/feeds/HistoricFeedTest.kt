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

package org.robok.feeds


import org.robok.common.Asset
import org.robok.common.Timeframe
import org.robok.common.Timeline
import org.robok.common.days
import org.robok.common.months
import org.roboquant.common.*
import org.robok.feeds.random.RandomWalk
import org.robok.feeds.toList
import java.util.*
import kotlin.test.*

internal class HistoricFeedTest {

    @Test
    fun test() {
        val feed = RandomWalk.lastYears()
        val tfs = feed.timeframe.split(1.months)
        assertEquals(12, tfs.size)
        assertEquals(10, feed.assets.size)
        assertFalse(feed.toList().isEmpty())

    }

    @Test
    fun custom() {
        val tf = Timeframe.fromYears(2020, 2021)
        val asset = _root_ide_package_.org.robok.common.Stock("ABC")

        class MyFeed : HistoricFeed {
            override val timeline: Timeline
                get() = tf.toTimeline(1.days)
            override val assets: SortedSet<Asset>
                get() = sortedSetOf(asset)

            override suspend fun play(channel: EventChannel) {
                // NOP
            }

        }

        val feed = MyFeed()
        assertTrue(tf.end >= feed.timeframe.end)
        assertTrue(feed.timeframe.inclusive)
        assertContains(feed.assets, asset)
    }

}
