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

package org.roboquant.charts

import kotlinx.coroutines.channels.ClosedReceiveChannelException
import kotlinx.coroutines.runBlocking
import org.hipparchus.stat.correlation.PearsonsCorrelation
import org.icepear.echarts.Heatmap
import org.icepear.echarts.Option
import org.icepear.echarts.charts.heatmap.HeatmapSeries
import org.icepear.echarts.components.coord.cartesian.CategoryAxis
import org.icepear.echarts.components.series.SeriesLabel
import org.roboquant.common.Asset
import org.roboquant.common.Logging
import org.roboquant.common.Timeframe
import org.roboquant.feeds.EventChannel
import org.roboquant.feeds.Feed
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

/**
 * Shows the Pearson correlation matrix between the prices of two or more [assets] that are present in the
 * provided [feed].
 */
class CorrelationChart(
    private val feed: Feed,
    private val assets: Collection<Asset>,
    private val timeframe: Timeframe = Timeframe.INFINITE,
    private val priceType: String = "DEFAULT",
    private val scale: Int = 2,
    private val minObservations: Int = 3
) : Chart() {

    private val logger = Logging.getLogger(this::class)

    init {
        require(assets.size > 1) { "Minimum of 2 assets are required, found ${assets.size}" }
    }

    private fun Pair<List<Double>, List<Double>>.clean(): Pair<DoubleArray, DoubleArray> {
        val max = Integer.min(first.size, second.size)
        val r1 = mutableListOf<Double>()
        val r2 = mutableListOf<Double>()
        for (i in 0 until max) {
            if (first[i].isFinite() && second[i].isFinite()) {
                r1.add(first[i])
                r2.add(second[i])
            }
        }
        return Pair(r1.toDoubleArray(), r2.toDoubleArray())
    }

    private fun collectPrices(): Map<Asset, List<Double>> = runBlocking {
        val channel = EventChannel(timeframe = timeframe)
        val result = TreeMap<Asset, MutableList<Double>>()

        val job = feed.playBackground(channel)
        try {
            while (true) {
                val o = channel.receive()
                val prices = o.prices
                for (asset in assets) {
                    val price = prices[asset]?.getPrice(priceType) ?: Double.NaN
                    val list = result.getOrPut(asset) { mutableListOf() }
                    list.add(price)
                }
            }
        } catch (e: ClosedReceiveChannelException) {
            logger.debug(e) { "channel closed" }
        } finally {
            channel.close()
            if (job.isActive) job.cancel()
        }
        return@runBlocking result
    }

    private fun getMatrix(prices: Map<Asset, List<Double>>): List<Triple<Int, Int, BigDecimal>> {
        val calc = PearsonsCorrelation()
        val result = mutableListOf<Triple<Int, Int, BigDecimal>>()
        for ((x, data1) in prices.values.withIndex()) {
            for ((y, data2) in prices.values.withIndex()) {
                if (y < x) continue
                val data = Pair(data1, data2).clean()
                if (data.first.size >= minObservations) {
                    val corr = calc.correlation(data.first, data.second)
                    val corrBD = BigDecimal(corr).setScale(scale, RoundingMode.HALF_DOWN)
                    result.add((Triple(x, y, corrBD)))
                    if (x != y) result.add((Triple(y, x, corrBD)))
                }
            }
        }
        return result
    }

    /** @suppress */
    override fun getOption(): Option {
        val prices = collectPrices()
        val labels = prices.keys.map { it.symbol }.toTypedArray()
        val data = getMatrix(prices)

        val label = SeriesLabel()
            .setColor("black")
            .setWidth(1)
            .setShow(true)

        val series = HeatmapSeries()
            .setData(data)
            .setLabel(label)

        val chart = Heatmap()
            .setTitle(title ?: "Price correlations")
            .addSeries(series)
            .setVisualMap(getVisualMap(-1.0, 1.0).setPrecision(2))
            .addXAxis(CategoryAxis().setData(labels))
            .addYAxis(CategoryAxis().setData(labels))

        val option = chart.option
        option.setToolbox(getBasicToolbox())

        return option
    }
}
