package org.roboquant.brokers;

import org.roboquant.common.Amount;
import org.roboquant.common.Positions;
import org.roboquant.common.Wallet;

/**
 * AccountModel that models a plain cash account. No additional leverage or margin is available for trading.
 * This is the default AccountModel if none is specified during instantiation of a SimBroker
 *
 * You should typically not short positions when using the CashAccount since that is almost never allowed in the real
 * world and also not supported.
 *
 * If you want to do it anyway, then the short exposures are for the full 100% deducted from the buying power.
 *
 * So the used calculation is:
 *
 *      Buying power = cash - short exposure - minimum
 *
 * Note: currently open orders are not taken into consideration when calculating the total buying power
 */
public class CashAccount implements AccountModel {

    private final double minimum;

    /**
     * Create a CashAccount with the specified minimum cash balance.
     *
     * @param minimum the minimum amount of cash balance required to maintain in the account, defaults to 0.0.
     *                 It is denoted in the base currency of the account.
     */
    public CashAccount(double minimum) {
        this.minimum = minimum;
    }

    /**
     * Create a CashAccount with default minimum of 0.0
     */
    public CashAccount() {
        this(0.0);
    }

    @Override
    public void updateAccount(InternalAccount account) {
        Wallet remaining = account.getCash().minus(Positions.exposure(Positions.getShort(account.getPositions())));
        Amount buyingPower = remaining.convert(account.getBaseCurrency(), account.getLastUpdate()).minus(minimum);
        account.setBuyingPower(buyingPower);
    }

}
