package org.roboquant.brokers;

/**
 * Interface for modeling different types of Accounts used in the SimBroker, like a CashAccountModel or MarginAccountModel.
 *
 * Currently, the main functionality is that at the end of each step the buying power is re-calculated and made
 * available in the attribute buying power.
 *
 * But in the future, an implementation could make other updates to the account.
 * For example, calculate borrowing fees or interest payments on the loan value that might apply.
 */
public interface AccountModel {

    /**
     * Update the account based on the rules within the account model.
     *
     * @param account the internal account to update
     */
    void updateAccount(InternalAccount account);

}
