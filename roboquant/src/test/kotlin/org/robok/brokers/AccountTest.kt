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

package org.robok.brokers


import org.robok.TestData
import org.robok.common.Amount
import org.robok.common.CurrencyK
import org.robok.common.Size
import org.robok.common.Stock
import org.robok.common.long
import org.robok.common.short
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class AccountTest {

    @Test
    fun basis() {
        val iAccount = _root_ide_package_.org.robok.brokers.InternalAccount(CurrencyK.USD)
        val account = iAccount.toAccount()
        val amount = account.equityAmount().value
        assertEquals(0.00, amount)
        assertEquals(Amount(CurrencyK.USD, 0.0), account.cashAmount)

        assertEquals(CurrencyK.USD, account.baseCurrency)
        assertTrue(account.positions.isEmpty())

        val e = account.equity()
        assertTrue(e.isEmpty())

        assertEquals(Size.ZERO, account.positionSize(Stock("Dummy")))

    }


    @Test
    fun extensions() {
        val account = TestData.usAccount()
        assertEquals(account.positions.size, account.positions.long.size + account.positions.short.size)
    }

}
