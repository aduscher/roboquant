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

package org.roboquant.ibkr

import org.robok.common.Config
import org.robok.common.CurrencyK
import org.robok.common.Logging
import org.roboquant.common.*
import org.robok.common.PriceItem
import org.robok.common.Stock
import org.robok.common.Timeframe
import org.robok.common.minutes
import org.robok.feeds.filter
import kotlin.test.Test
import kotlin.test.assertTrue

internal class IBKRLiveFeedTestIT {

    private val logger = Logging.getLogger(this::class)

    @Test
    fun ibkrFeed() {
        Config.getProperty("test.ibkr") ?: return

        val feed = IBKRLiveFeed()
        val assets = listOf(Stock("ABN", CurrencyK.EUR))
        feed.subscribe(assets, interval = 1)

        feed.subscribe(Stock("KPN", CurrencyK.EUR))

        val actions = feed.filter<PriceItem>(Timeframe.next(2.minutes)) {
            logger.info("received price $it")
            true
        }
        assertTrue(actions.isNotEmpty())
    }

}
