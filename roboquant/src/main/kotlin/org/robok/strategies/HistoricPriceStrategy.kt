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

package org.robok.strategies

import org.roboquant.common.*
import org.robok.common.EventK
import org.robok.common.addAll

/**
 * The base class for strategies that are interested in historic prices. Subclasses should override one of the
 * two following methods:
 *
 * [generateSignal] - You have full control over the generated Signal
 * [generateRating] - You only need to provide a Rating
 *
 * @property period period to keep track of historic data
 * @property priceType the type of price to store, default is "DEFAULT"
 */
abstract class HistoricPriceStrategy(
    private val period: Int,
    private val priceType: String = "DEFAULT",
) : Strategy {

    /**
     * Contain the history of all assets
     */
    private val history = mutableMapOf<org.robok.common.Asset, org.robok.common.PriceSeries>()

    override fun createSignals(event: EventK): List<org.robok.common.Signal> {
        val assets = history.addAll(event, period, priceType)
        return assets
            .mapNotNull { asset ->
                generateSignal(
                    asset = asset,
                    data = history.getValue(asset).toDoubleArray(),
                )
            }
    }

    /**
     * Generate a signal based on the provided [asset] and [data]. Default implementation is to call [generateRating]
     * to get the rating.
     */
    open fun generateSignal(asset: org.robok.common.Asset, data: DoubleArray): org.robok.common.Signal? {
        return generateRating(data)
            ?.let { _root_ide_package_.org.robok.common.Signal(asset, rating = it) }
    }

    /**
     * Generate a rating based on the provided [data]. If no rating can be provided, this method should return null.
     */
    open fun generateRating(data: DoubleArray): Double? =
        throw _root_ide_package_.org.robok.common.RoboquantException("Should override generateSignal or generateRating")

    /**
     * Reset the state
     */
    fun reset() {
        history.clear()
    }
}
