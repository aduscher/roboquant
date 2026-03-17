package org.roboquant.common;

import java.util.Collection;

/**
 * Asset is used to uniquely identify a financial instrument. So it can represent a stock, a future or a
 * cryptocurrency.
 *
 * For asset types that require additional information (like options or futures), the symbol name is expected to
 * contain this information.
 *
 * All of its properties are read-only, and assets are ideally only created once and reused thereafter. An asset
 * instance is immutable.
 */
public interface Asset extends Comparable<Asset> {
   
   String getSymbol();

   Currency getCurrency();

   String serialize();

   default Amount value(Size size, double price) {
      if (size.isZero())
         return new Amount(getCurrency(), 0.0);
      return new Amount(getCurrency(), size.toDouble() * price);
    }

    static final String SEP = ";";

    static Asset getBySymbol(Collection<Asset> assets, String symbol) {
        return assets.stream()
                .filter(a -> a.getSymbol().equals(symbol))
                .findFirst()
                .orElseThrow();
    }

    static String[] getSymbols(Collection<Asset> assets) {
        return assets.stream()
                .map(Asset::getSymbol)
                .distinct()
                .toArray(String[]::new);
    }

}
