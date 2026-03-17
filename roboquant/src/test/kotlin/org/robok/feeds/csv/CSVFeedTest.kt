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

package org.robok.feeds.csv

import org.robok.TestData
import org.robok.common.CurrencyK
import org.robok.common.Forex
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

internal class CSVFeedTest {

    @Test
    fun getAssets() {
        val feed = _root_ide_package_.org.robok.feeds.csv.CSVFeed(TestData.dataDir() + "US")
        val assets = feed.assets
        assertEquals(3, assets.size)
        assertEquals(199, feed.timeline.size)
    }



    @Test
    fun testIntra() {
        val feed = _root_ide_package_.org.robok.feeds.csv.CSVFeed(TestData.dataDir() + "INTRA")
        val assets = feed.assets
        assertEquals(2, assets.size)
    }

    @Test
    fun getForexCSV() {
        val feed = _root_ide_package_.org.robok.feeds.csv.CSVFeed(TestData.dataDir() + "FX1") {
            assetBuilder = _root_ide_package_.org.robok.feeds.csv.AssetBuilder {
                Forex.fromSymbol(it)
            }
        }
        val assets = feed.assets
        assertEquals(2, assets.size)
        assertEquals(CurrencyK.USD, assets.first().currency)
    }

    @Test
    fun getMinutesCSV() {
        val feed = _root_ide_package_.org.robok.feeds.csv.CSVFeed(TestData.dataDir() + "FX2") {
            assetBuilder = _root_ide_package_.org.robok.feeds.csv.AssetBuilder {
                Forex.fromSymbol(it)
            }
        }
        val assets = feed.assets
        assertEquals(1, assets.size)
        assertEquals(CurrencyK.USD, assets.first().currency)
        assertEquals(9, feed.timeline.size)
    }

    @Test
    fun noAssets() {
        assertFailsWith<IllegalArgumentException> {
            CSVFeed(TestData.dataDir() + "NON_Existing_DIR")
        }

        val feed1 = _root_ide_package_.org.robok.feeds.csv.CSVFeed(TestData.dataDir() + "US") {
            filePattern = ".*non_existing_ext"
        }

        assertEquals(".*non_existing_ext", feed1.config.filePattern)
        assertTrue(feed1.assets.isEmpty())
    }


}
