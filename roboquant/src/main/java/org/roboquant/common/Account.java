package org.roboquant.common;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;

import java.util.HashSet;
import java.util.Arrays;

/**
 * An account represents a brokerage trading account and is unified across all broker implementations.
 */
public interface Account {

   // --- Core State Accessors ---

   Currency getBaseCurrency();

   Instant getLastUpdate();

   Wallet getCash();

   List<Order> getOrders();

   Map<Asset, Position> getPositions();

   Amount getBuyingPower();

   List<Trade> getTrades();

   // --- Default Implementations (Logic) ---

   /**
    * Cash balances converted to a single amount denoted in the base currency.
    */
   default Amount getCashAmount() {
      return convert(getCash());
   }

   /**
    * Equity converted to a single amount denoted in the base currency.
    */
   default Amount equityAmount() {
      return convert(equity());
   }

   /**
    * Calculate total equity as a Wallet (cash balances + market value of positions).
    */
   default Wallet equity() {
      return getCash().plus(marketValue());
   }

   /**
    * The unique set of assets held in the positions.
    */
   default Set<Asset> getAssets() {
      return getPositions().keySet();
   }

   /**
    * Return the market value of the open positions.
    * Uses Java varargs to support the optional asset filter.
    */
   default Wallet marketValue(Asset... assets) {
      Wallet result = new Wallet();
      Map<Asset, Position> positions = getPositions();

      Set<Asset> filterSet = (assets.length > 0) ? new HashSet<>(Arrays.asList(assets)) : null;

      for (Map.Entry<Asset, Position> entry : positions.entrySet()) {
         Asset asset = entry.getKey();
         if (filterSet == null || filterSet.contains(asset)) {
            Position position = entry.getValue();
            Amount positionValue = asset.value(position.getSize(), position.getMktPrice());
            result.deposit(positionValue);
         }
      }
      return result;
   }

   /**
    * Return the position size for the provided asset. Returns Size.ZERO if no position exists.
    */
   default Size positionSize(Asset asset) {
      Position p = getPositions().get(asset);
      return (p != null) ? p.getSize() : Size.ZERO;
   }

   /**
    * Return the unrealized PNL of the open positions.
    */
   default Wallet unrealizedPNL(Asset... assets) {
      Wallet result = new Wallet();
      Map<Asset, Position> positions = getPositions();

      Set<Asset> filterSet = (assets.length > 0) ? new HashSet<>(Arrays.asList(assets)) : null;

      for (Map.Entry<Asset, Position> entry : positions.entrySet()) {
         Asset asset = entry.getKey();
         if (filterSet == null || filterSet.contains(asset)) {
            Position position = entry.getValue();
            // PNL Calculation: asset.value(size, mktPrice - avgPrice)
            Amount pnlValue = asset.value(position.getSize(), position.getMktPrice() - position.getAvgPrice());
            result.deposit(pnlValue);
         }
      }
      return result;
   }

   /**
    * Convert an amount to the account base currency using the last update timestamp.
    */
   default Amount convert(Amount amount) {
      return amount.convert(getBaseCurrency(), getLastUpdate());
   }

   /**
    * Convert a wallet to the account base currency using the last update timestamp.
    */
   default Amount convert(Wallet wallet) {
      return wallet.convert(getBaseCurrency(), getLastUpdate());
   }

}