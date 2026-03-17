package org.robok.samples


import org.robok.common.Signal
import org.robok.run
import org.robok.feeds.random.RandomWalk
import org.robok.ta.PriceBarSeries
import org.robok.ta.TaLib
import org.robok.ta.TaLibSignalStrategy
import kotlin.test.Ignore
import kotlin.test.Test

internal class TaSamples {

    @Test
    @Ignore
    internal fun macd() {
        val strategy = TaLibSignalStrategy { asset, prices ->
            val (_, _, diff) = macd(prices)
            val (_, _, diff2) = macd(prices, slowPeriod = 1)
            when {
                diff < 0.0 && diff2 > 0.0 -> Signal.buy(asset)
                diff > 0.0 && diff2 < 0.0 -> Signal.sell(asset)
                else -> null
            }
        }

        val feed = RandomWalk.lastYears(5)
        val account = run(feed, strategy)
        println(account)
    }

    @Suppress("CyclomaticComplexMethod")
    @Test
    @Ignore
    internal fun tenkan() {

        /**
         * Midpoint Moving Average
         */
        fun TaLib.mma(priceBarSeries: PriceBarSeries, period: Int): Double {
            return (max(priceBarSeries.high, period) + min(priceBarSeries.low, period)) / 2.0
        }

        /**
         * Tenkan indicator
         */
        fun TaLib.tenkan(priceBarSeries: PriceBarSeries) = mma(priceBarSeries, 9)

        /**
         * Kijun indicator
         */
        fun TaLib.kijun(priceBarSeries: PriceBarSeries) = mma(priceBarSeries, 26)

        val strategy = TaLibSignalStrategy { asset, series ->
            val tenkan = tenkan(series)
            val kijun = kijun(series)
            when {
                tenkan > kijun && series.open.last() < tenkan && series.close.last() > tenkan -> Signal.buy(
                    asset
                )

                tenkan < kijun && series.close.last() < kijun -> Signal.sell(asset)
                else -> null
            }
        }


        val feed = RandomWalk.lastYears(5)

        println(feed.timeframe)
        val account = run(feed, strategy)
        println(account)

    }

}
