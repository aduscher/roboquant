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

package org.roboquant.brokers;

import org.roboquant.common.*;

import java.time.Instant;
import java.util.Map;

/**
 * An account model that supports trading with margin. The buying power is calculated using the following steps:
 *
 *      1. Long value = long positions * maintenance margin long
 *      2. Short value = short positions * maintenance margin short
 *      3. Excess margin = equity - long value - short value - minimum equity
 *      4. Buying power = excess margin * (1 / initial margin)
 *
 * Note: currently open orders are not taken into consideration when calculating the total buying power.
 */
public class MarginAccount implements AccountModel {

    private final double initialMargin;
    private final double maintenanceMarginLong;
    private final double maintenanceMarginShort;
    private final double minimumEquity;

    /**
     * Create a MarginAccount with specified margin parameters.
     *
     * @param initialMargin the initial margin requirements, default to 50% (0.50)
     * @param maintenanceMarginLong the maintenance margin requirement for long positions, defaults to 30% (0.3)
     * @param maintenanceMarginShort the maintenance margin requirement for short positions, defaults to same value as maintenanceMarginLong
     * @param minimumEquity the minimum equity requirement, defaults to 0.0 (denoted in Account.baseCurrency)
     */
    public MarginAccount(
            double initialMargin,
            double maintenanceMarginLong,
            double maintenanceMarginShort,
            double minimumEquity
    ) {
        if (initialMargin < 0.0 || initialMargin > 1.0) {
            throw new IllegalArgumentException("initialMargin between 0.0 and 1.0");
        }
        if (maintenanceMarginLong < 0.0 || maintenanceMarginLong > 1.0) {
            throw new IllegalArgumentException("maintenanceMarginLong between 0.0 and 1.0");
        }
        if (maintenanceMarginShort < 0.0 || maintenanceMarginShort > 1.0) {
            throw new IllegalArgumentException("maintenanceMarginShort between 0.0 and 1.0");
        }
        this.initialMargin = initialMargin;
        this.maintenanceMarginLong = maintenanceMarginLong;
        this.maintenanceMarginShort = maintenanceMarginShort;
        this.minimumEquity = minimumEquity;
    }

    /**
     * Create a margin account based on a leverage. Effectively, all margins values will be set to 1/leverage.
     * Optional can provide a minimum cash amount that needs to remain in the account.
     *
     * @param leverage the leverage ratio
     * @param minimum the minimum cash amount
     */
    public MarginAccount(double leverage, double minimum) {
        this(1.0 / leverage, 1.0 / leverage, 1.0 / leverage, minimum);
    }

    /**
     * Create a MarginAccount with default values (50% initial, 30% maintenance)
     */
    public MarginAccount() {
        this(ExtensionsKt.percent(50), ExtensionsKt.percent(30), ExtensionsKt.percent(30), 0.0);
    }

    @Override
    public void updateAccount(InternalAccount account) {
        Instant time = account.getLastUpdate();
        Currency currency = account.getBaseCurrency();
        Map<Asset, Position> positions = account.getPositions();

        Wallet excessMargin = account.getCash().plus(Positions.marketValue(positions));
        excessMargin.withdraw(new Amount(currency, minimumEquity));

        Amount longExposure = Positions.exposure(Positions.getLong(positions)).convert(currency, time).times(maintenanceMarginLong);
        excessMargin.withdraw(longExposure);

        Amount shortExposure = Positions.exposure(Positions.getShort(positions)).convert(currency, time).times(maintenanceMarginShort);
        excessMargin.withdraw(shortExposure);

        Amount buyingPower = excessMargin.convert(currency, time).times(1.0 / initialMargin);
        account.setBuyingPower(buyingPower);
    }

}
