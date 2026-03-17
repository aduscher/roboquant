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

package org.robok

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.assertDoesNotThrow
import org.robok.common.ParallelJobs
import org.robok.common.months
import org.robok.common.years
import org.robok.feeds.random.RandomWalk
import org.robok.journals.BasicJournal
import org.robok.journals.MemoryJournal
import org.robok.journals.MultiRunJournal
import org.robok.journals.metrics.PNLMetric
import org.robok.strategies.EMACrossover
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class RunTest {

    @Test
    fun simpleRun() {
        assertDoesNotThrow {
            run(TestData.feed, EMACrossover())
        }
    }



    @Test
    fun runAsyncTest()  {

        assertDoesNotThrow {
            runBlocking {
                runAsync(TestData.feed, EMACrossover())
            }
        }
    }



    @Test
    fun walkforward()  {
        val feed = RandomWalk.lastYears(10, 2)
        val tfs = feed.timeframe.split(2.years)
        for (tf in tfs) {
            val strategy = EMACrossover()
            val journal = BasicJournal()
            _root_ide_package_.org.robok.run(TestData.feed, strategy, journal = journal, timeframe = tf)
        }
    }

    @Test
    fun run_with_pb()  {
        assertDoesNotThrow {
            val strategy = EMACrossover()
            val journal = BasicJournal()
            run(TestData.feed, strategy, journal = journal, showProgressBar = true)
        }
    }



    @Test
    fun run3()  {
        val mrj = MultiRunJournal { MemoryJournal(PNLMetric()) }
        val feed = TestData.feed
        val timeframes  = feed.timeframe.split(1.years)
        for (tf in timeframes) {
            val strategy = EMACrossover()
            val run = tf.toString()
            _root_ide_package_.org.robok.run(TestData.feed, strategy, journal = mrj.getJournal(run), timeframe = tf)
        }
        val m = mrj.getMetric("pnl.equity")
        assertEquals(timeframes.size, m.size)
    }

    @Test
    fun massiveParallel() {
        val feed = TestData.feed
        val jobs = ParallelJobs()

        repeat(50) {
            jobs.add {
                runAsync(feed, EMACrossover())
            }
        }
        jobs.joinAllBlocking()

    }


    @Test
    fun parallelTimeframes() {
        val feed = TestData.feed
        val jobs = ParallelJobs()

        feed.timeframe.sample(3.months).forEach {
            jobs.add {
                val acc = runAsync(feed, EMACrossover(), timeframe = it)
                println(acc.lastUpdate)
                println(it)
                assertTrue(acc.lastUpdate in it)
            }
        }
        jobs.joinAllBlocking()

    }



}
