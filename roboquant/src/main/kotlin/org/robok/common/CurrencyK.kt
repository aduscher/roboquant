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

import java.util.concurrent.ConcurrentHashMap

/**
 * Currency implementation that supports regular currencies as well as cryptocurrencies. So the [currencyCode] for the
 * currency is not limited to ISO-4217 codes like the regular Java Currency class.
 *
 * This is a lightweight implementation since most of the roboquant functionality relies soley on the currency code.
 *
 * When creating a new currency instance, use the [CurrencyK.getInstance] method. This ensures only a single
 * instance of a currency exists for a given currency code and that allows for fast equality comparison.
 *
 *  @property currencyCode The currency code for the currency.
 **/
class CurrencyK private constructor(val currencyCode: String) {

    /**
     * The name to use when displaying this currency, default is the currency code
     */
    val displayName: String
        get() = currencyCode

    /**
     * The number of digits to use when formatting amounts denominated in this currency
     */
    var defaultFractionDigits: Int =
        try {
            java.util.Currency.getInstance(currencyCode).defaultFractionDigits
        } catch (_: IllegalArgumentException) {
            // If we cannot find the currency, use 2
            2
        }


    /**
     * @suppress
     */
    companion object {

        /**
         * Holds all the instantiated currencies
         */
        private val currencies = ConcurrentHashMap<String, CurrencyK>()

        private fun getInstance(currencyCode: String, defaultFractionDigits: Int): CurrencyK {
            val result = currencies.getOrPut(currencyCode) { CurrencyK(currencyCode) }
            result.defaultFractionDigits = defaultFractionDigits
            return result
        }



        /**
         * Returns a Currency instance for the provided [currencyCode].
         */
        fun getInstance(currencyCode: String): CurrencyK = currencies.getOrPut(currencyCode) { CurrencyK(currencyCode) }

        // Commonly used currencies in trading

        /**
         * United States Dollar
         */
        val USD: CurrencyK = getInstance("USD")

        /**
         * European Euro
         */
        val EUR: CurrencyK = getInstance("EUR")

        /**
         * Japanese Yen
         */
        val JPY: CurrencyK = getInstance("JPY")

        /**
         * British Pound Sterling
         */
        val GBP: CurrencyK = getInstance("GBP")

        /**
         * Australian Dollar
         */
        val AUD: CurrencyK = getInstance("AUD")

        /**
         * Canadian Dollar
         */
        val CAD: CurrencyK = getInstance("CAD")

        /**
         * Swiss Franc
         */
        val CHF: CurrencyK = getInstance("CHF")

        /**
         * Chinese Yuan Renminbi
         */
        val CNY: CurrencyK = getInstance("CNY")

        /**
         * Hong Kong Dollar
         */
        val HKD: CurrencyK = getInstance("HKD")

        /**
         * New Zealand Dollar
         */
        val NZD: CurrencyK = getInstance("NZD")

        /**
         * Russian Ruble
         */
        val RUB: CurrencyK = getInstance("RUB")

        /**
         * Indian Rupee
         */
        val INR: CurrencyK = getInstance("INR")

        /**
         * South Korean won
         */
        val KRW: CurrencyK = getInstance("KRW")

        /**
         * Bitcoin
         */
        val BTC: CurrencyK = getInstance("BTC", 8)

        /**
         * Ether
         */
        val ETH: CurrencyK = getInstance("ETH", 8)

        /**
         * Tether (stable coin)
         */
        val USDT: CurrencyK = getInstance("USDT", 2)

        /**
         * For all already registered currencies increase the number of display digits with [extraDigits]. This doesn't
         * change calculations, only the way currency amounts are displayed.
         *
         * TIP: For Forex trading this is often required since otherwise small differences cannot be seen in charts
         * and reports.
         */
        fun increaseDigits(extraDigits: Int = 3) {
            for (currency in currencies.values) currency.defaultFractionDigits += extraDigits
        }
    }

    /** @suppress */
    override fun toString(): String = displayName

}
