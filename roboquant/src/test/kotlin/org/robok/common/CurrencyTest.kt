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

package org.robok.common

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class CurrencyTest {

    @Test
    fun test() {
        assertTrue {
            CurrencyK.EUR
            CurrencyK.USD
            CurrencyK.USD
            CurrencyK.CAD
            CurrencyK.CHF
            CurrencyK.JPY
            CurrencyK.HKD
            CurrencyK.GBP
            CurrencyK.AUD
            CurrencyK.CNY
            CurrencyK.NZD
            CurrencyK.RUB
            CurrencyK.INR
            CurrencyK.BTC
            CurrencyK.ETH
            CurrencyK.USDT
            true
        }
    }

    @Test
    fun test2() {
        val x = CurrencyK.USD
        val y = CurrencyK.getInstance("USD")
        assertEquals(x, y)
    }

    @Test
    fun test3() {
        val c = CurrencyK.getInstance("DUMMY")
        assertEquals(2, c.defaultFractionDigits)
        CurrencyK.increaseDigits(2)
        assertEquals(4, c.defaultFractionDigits)
        CurrencyK.increaseDigits(-2)
        assertEquals(2, c.defaultFractionDigits)

        CurrencyK.increaseDigits()
        assertEquals(5, c.defaultFractionDigits)
        CurrencyK.increaseDigits(-3)
        assertEquals(2, c.defaultFractionDigits)
    }

}
