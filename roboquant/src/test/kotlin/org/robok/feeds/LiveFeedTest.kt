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

import kotlinx.coroutines.*
import org.junit.jupiter.api.assertDoesNotThrow
import org.robok.common.EventK
import org.robok.common.Stock
import org.robok.common.Timeframe
import org.robok.common.TradePrice
import org.robok.common.millis
import org.robok.common.seconds
import org.roboquant.common.*
import org.robok.journals.MemoryJournal
import org.robok.journals.metrics.ProgressMetric
import org.robok.runAsync
import org.robok.strategies.EMACrossover
import java.time.Instant
import java.time.temporal.ChronoUnit
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class LiveFeedTest {

    @Test
    fun combined() = runBlocking {

        class MyLiveFeed : LiveFeed()

        val feed1 = MyLiveFeed()
        assertFalse { feed1.isActive }
        val feed2 = MyLiveFeed()
        val feed = CombinedLiveFeed(feed1, feed2)

        val job = feed.playBackground(EventChannel())

        assertDoesNotThrow {
            feed.close()
            feed.close()
        }
        if (job.isActive) job.cancel()

    }

    @Test
    fun concurrency() {

        class MyLiveFeed : LiveFeed() {

            var stop = false

            fun start(delayInMillis: Long) {
                val scope = CoroutineScope(Dispatchers.Default + Job())

                scope.launch {
                    val asset = Stock("ABC")
                    val items = listOf(TradePrice(asset, 100.0))

                    while (true) {
                        try {
                            sendAsync(event = EventK(Instant.now(), items))
                            delay(delayInMillis)
                            if (stop) break
                        } catch (e: Exception) {
                            println(e)
                        }
                    }
                }

            }


        }


        val jobs = _root_ide_package_.org.robok.common.ParallelJobs()
        val feed = MyLiveFeed()
        feed.start(delayInMillis = 10)

        val tf = Timeframe.next(1.seconds)
        tf.sample(200.millis, 10, resolution = ChronoUnit.MILLIS).forEach {
            // register multiple runs
            jobs.add {
                val j = MemoryJournal(ProgressMetric())
                runAsync(feed, EMACrossover(), journal = j, timeframe = it)
                val items = j.getMetric("progress.items").values.last()
                assertTrue(items > 2)
            }
        }

        jobs.joinAllBlocking()
        feed.stop = true
    }


}
