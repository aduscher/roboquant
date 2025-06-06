/*
 * Copyright 2020-2025 Neural Layer
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

package org.roboquant.samples

import org.roboquant.brokers.MarginAccount
import org.roboquant.brokers.SimBroker
import org.roboquant.common.*
import org.roboquant.feeds.*
import org.roboquant.feeds.random.RandomWalk
import org.roboquant.run
import org.roboquant.runAsync
import org.roboquant.strategies.CombinedStrategy
import org.roboquant.strategies.EMACrossover
import org.roboquant.common.Signal
import org.roboquant.strategies.Strategy
import org.roboquant.traders.FlexTrader
import java.time.Instant
import kotlin.system.exitProcess
import kotlin.system.measureTimeMillis

/**
 * Simple fast strategy.
 *
 * Not realistic, but with minimal overhead to ensure we can measure the performance of the engine and not the strategy.
 */
private class FastStrategy(private val skip: Int) : Strategy {

    var steps = 0
    var buy = true

    override fun createSignals(event: Event): List<Signal> {
        val signals = mutableListOf<Signal>()
        for (action in event.items.filterIsInstance<PriceItem>()) {
            steps++
            if ((steps % (skip + 1)) == 0) {
                val signal = if (buy) Signal.buy(action.asset) else Signal.sell(action.asset)
                signals.add(signal)
                buy = !buy
            }
        }
        return signals
    }

}

/**
 * Fast feed.
 *
 * Not realistic, but with minimal overhead to ensure we can measure the performance of the engine and are not
 * impacted by the feed.
 */
private class FastFeed(nAssets: Int, val events: Int) : Feed {

    private val assets = mutableListOf<Asset>()
    private val start = Instant.parse("2000-01-01T00:00:00Z")
    val actions = ArrayList<PriceBar>(nAssets)
    val size = nAssets * events

    init {
        repeat(nAssets) { assets.add(Stock("TEST-$it")) }
        val data = doubleArrayOf(100.0, 101.0, 99.0, 100.0, 10000.0)
        for (asset in assets) {
            val action = PriceBar(asset, data)
            actions.add(action)
        }
    }

    override suspend fun play(channel: EventChannel) {
        repeat(events) {
            channel.send(Event(start + it.millis, actions))
        }
    }

}

/**
 * Performance test that runs a number of back-tests scenarios against different feed sizes to measure performance
 * and detect possible performance regressions.
 * Each test is run three times to minimize fluctuations caused by outside events like virus scanners.
 *
 * The main purpose is to validate the performance, throughput and stability of the back test engine, not any particular
 * feed, strategy or metric. So the used feed and strategy are optimized for this test and not realistic at all.
 */
private object Performance {

    private const val SKIP = 999 // create signal in 1 out of 999 price-action
    private fun getStrategy(skip: Int): Strategy = FastStrategy(skip)

    /**
     * Try to make the results more reproducible by running the code multiple times and take the best time.
     */
    private inline fun measure(block: () -> Unit): Long {
        var best = Long.MAX_VALUE
        repeat(3) {
            System.gc()
            val t = measureTimeMillis(block)
            if (t < best) best = t
        }
        return best
    }

    /**
     * Basic test with minimal overhead
     */
    private fun seqRun(feed: FastFeed, backTests: Int): Long {
        val t = measure {
            // sequential runs
            repeat(backTests) {
                val broker = SimBroker()
                run(feed, getStrategy(SKIP), broker = broker)
            }

        }
        return t
    }

    /**
     * Test iterating over the feed while filtering
     */
    private fun feedFilter(feed: FastFeed): Long {
        val asset = Stock("UNKNOWN")
        return measure {
            feed.filter<PriceBar> {
                it.asset == asset
            }
        }
    }

    /**
     * Test with three strategies, margin account, shorting, metrics and some logging overhead included
     */
    private fun extendedRun(feed: FastFeed): Long {
        return measure {

            val strategy = CombinedStrategy(
                getStrategy(SKIP - 1),
                getStrategy(SKIP),
                getStrategy(SKIP + 1),
            )

            val broker = SimBroker(accountModel = MarginAccount())
            val trader = FlexTrader {
                shorting = true
            }

            run(feed, strategy, trader = trader, broker = broker)
        }
    }

    /**
     * Run parallel back tests
     */
    private fun parRun(feed: Feed, backTests: Int): Long {

        return measure {
            val jobs = ParallelJobs()
            repeat(backTests) {
                jobs.add {
                    runAsync(feed, getStrategy(SKIP), broker = SimBroker())
                }
            }
            jobs.joinAllBlocking()
        }
    }

    @Suppress("ImplicitDefaultLocale")
    fun test() {
        Config.printInfo()
        data class Combination(val events: Int, val assets: Int, val backTests: Int)

        val combinations = listOf(
            Combination(1_000, 10, 100),
            Combination(1_000, 50, 100),
            Combination(2_000, 50, 100),
            Combination(5_000, 100, 100),

            Combination(5_000, 200, 100),
            Combination(10_000, 500, 100),
            Combination(20_000, 500, 100),
        )

        val header = String.format(
            "\n%8S %6S %6S %4S %7S %7S %10S %7S %9S",
            "candles", "assets", "events", "runs", "feed", "full",
            "sequential", "parallel", "candles/s"
        )

        println(header)
        println(" " + "━".repeat(header.length - 2))
        for ((events, assets, backTests) in combinations) {
            // single run
            val feed = FastFeed(assets, events)
            val t1 = feedFilter(feed) // Test iterating over feed
            val t2 = extendedRun(feed) // Test a more complete back test

            // multi-run to test engine scalability
            val t3 = seqRun(feed, backTests)
            val t4 = parRun(feed, backTests)

            // Calculate the total number of candles processed in millions
            val candles = assets * events * backTests / 1_000_000
            val line = String.format(
                "%6dM %7d %6d %4d %5dms %5dms %7dms %7dms %8dM",
                candles, assets, events, backTests,
                t1, t2, t3, t4, feed.size * backTests / (t4 * 1000)
            )
            println(line)
        }
        println()
        exitProcess(0)
    }
}

private object Memory {

    fun test() {
        Config.printInfo()
        val feed = RandomWalk.lastYears(5, nAssets = 500)
        run(feed, EMACrossover())
        exitProcess(0)
    }

}

/**
 * Run the performance test
 */
fun main() {
    if (Config.getProperty("memory") == "true")
        Memory.test()
    else
        Performance.test()
}
