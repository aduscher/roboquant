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

import org.robok.brokers.ExchangeRates
import org.robok.brokers.NoExchangeRates
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat
import java.time.Instant
import java.util.*
import kotlin.math.absoluteValue

/**
 * An amount holds the monetary [value] for a single [currency].
 *
 * For storing monetary amounts internally it uses [Double], since it is accurate enough for trading while providing
 * performance benefits over types like BigDecimal.
 *
 * @property currency the currency of the amount
 * @property value the value of the amount
 */
class Amount(val currency: CurrencyK, val value: Double)  {

    /**
     * Create an Amount instance based on provided [currency] and [value].
     * The value will be converted to a [Double], which might involve rounding.
     */
    constructor(currency: CurrencyK, value: Number) : this(currency, value.toDouble())

    /**
     * Create an Amount instance based on provided [currencyCode] and [value].
     * The value will be converted to a [Double], which might involve rounding.
     */
    constructor(currencyCode: String, value: Number) : this(CurrencyK.getInstance(currencyCode), value.toDouble())

    // Common operators that make working with Amounts more convenient

    /** @suppress */
    operator fun times(d: Number): Amount = Amount(currency, value * d.toDouble())

    /** @suppress */
    operator fun plus(d: Number): Amount = Amount(currency, value + d.toDouble())

    /** @suppress */
    operator fun div(d: Number): Amount = Amount(currency, value / d.toDouble())

    /** @suppress */
    operator fun minus(d: Number): Amount = Amount(currency, value - d.toDouble())

    /** @suppress */
    operator fun plus(other: Amount): Wallet = Wallet(this, other)

    /** @suppress */
    operator fun minus(other: Amount): Wallet = Wallet(this, -other)

    /** @suppress */
    operator fun unaryMinus(): Amount = Amount(currency, -value)

    /**
     * Does this amount contain a positive value.
     */
    val isPositive: Boolean
        get() = value > 0.0

    /**
     * Return a new amount containing the absolute value of this amount.
     */
    val absoluteValue: Amount
        get() = Amount(currency, value.absoluteValue)

    /**
     * Format the value hold in this amount based on the currency. For example, USD would have two fraction digits
     * by default while JPY would have none.
     */
    fun formatValue(fractionDigits: Int = currency.defaultFractionDigits): String {
        // We don't use default locale to make output more reproducible
        val formatEN = NumberFormat.getInstance(Locale.ENGLISH)
        formatEN.minimumFractionDigits = fractionDigits
        formatEN.maximumFractionDigits = fractionDigits
        return formatEN.format(value)
    }

    /**
     * Convert the value to BigDecimal using the number of digits defined for the currency. Internally, roboquant
     * doesn't use BigDecimals, but this method is used to enable a nicer display of currency amounts.
     */
    fun toBigDecimal(fractionDigits: Int = currency.defaultFractionDigits): BigDecimal =
        BigDecimal.valueOf(value).setScale(fractionDigits, RoundingMode.HALF_DOWN)

    /** @suppress **/
    override fun toString(): String = "${currency.currencyCode} ${formatValue()}"

    /**
     * Convert this amount [to] a different currency. Optional you can provide a [time] at which the conversion
     * should be calculated. If no time is provided, the current time is used.
     */
    fun convert(to: CurrencyK, time: Instant): Amount {
        if (currency == to) return this
        if (value == 0.0) return Amount(to, 0.0)
        return converter.convert(this, to, time)
    }

    /** @suppress **/
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Amount) return false
        if (currency != other.currency) return false
        return value == other.value
    }

    /** @suppress **/
    override fun hashCode(): Int = 31 * currency.hashCode() + value.hashCode()

    /**
     * Convert this amount to a [Wallet] instance.
     */
    fun toWallet(): Wallet {
        return Wallet(this)
    }

    /** @suppress */
    companion object {

        /**
         * Holds the currently active exchange convertor
         */
        private var converter: ExchangeRates = NoExchangeRates()

        fun registerConvertor(converter: ExchangeRates) {
            Amount.converter = converter

        }
    }


}

// Extensions to make it easier to create amounts for common currencies

/**
 * Amount in [CurrencyK.EUR]
 */
val Number.EUR: Amount
    get() = Amount(CurrencyK.EUR, toDouble())

/**
 * Amount in [CurrencyK.USD]
 */
val Number.USD: Amount
    get() = Amount(CurrencyK.USD, toDouble())

/**
 * Amount in [CurrencyK.JPY]
 */
val Number.JPY: Amount
    get() = Amount(CurrencyK.JPY, toDouble())

/**
 * Amount in [CurrencyK.GBP]
 */
val Number.GBP: Amount
    get() = Amount(CurrencyK.GBP, toDouble())

/**
 * Amount in [CurrencyK.CHF]
 */
val Number.CHF: Amount
    get() = Amount(CurrencyK.CHF, toDouble())

/**
 * Amount in [CurrencyK.AUD]
 */
val Number.AUD: Amount
    get() = Amount(CurrencyK.AUD, toDouble())

/**
 * Amount in [CurrencyK.CAD]
 */
val Number.CAD: Amount
    get() = Amount(CurrencyK.CAD, toDouble())

/**
 * Amount in [CurrencyK.CNY]
 */
val Number.CNY: Amount
    get() = Amount(CurrencyK.CNY, toDouble())

/**
 * Amount in [CurrencyK.HKD]
 */
val Number.HKD: Amount
    get() = Amount(CurrencyK.HKD, toDouble())

/**
 * Amount in [CurrencyK.NZD]
 */
val Number.NZD: Amount
    get() = Amount(CurrencyK.NZD, toDouble())

/**
 * Amount in [CurrencyK.RUB]
 */
val Number.RUB: Amount
    get() = Amount(CurrencyK.RUB, toDouble())

/**
 * Amount in [CurrencyK.INR]
 */
val Number.INR: Amount
    get() = Amount(CurrencyK.INR, toDouble())

/**
 * Amount in [CurrencyK.KRW]
 */
val Number.KRW: Amount
    get() = Amount(CurrencyK.KRW, toDouble())

// Extensions to make it easier to create amounts for common cryptocurrencies

/**
 * Amount in [CurrencyK.BTC]
 */
val Number.BTC: Amount
    get() = Amount(CurrencyK.BTC, toDouble())

/**
 * Amount in [CurrencyK.ETH]
 */
val Number.ETH: Amount
    get() = Amount(CurrencyK.ETH, toDouble())

/**
 * Amount in [CurrencyK.USDT]
 */
val Number.USDT: Amount
    get() = Amount(CurrencyK.USDT, toDouble())

/**
 * Add all the amounts together and return the resulting wallet.
 */
fun Collection<Amount>.toWallet(): Wallet {
    return sumOf { it }
}

