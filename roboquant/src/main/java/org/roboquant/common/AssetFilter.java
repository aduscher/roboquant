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

package org.roboquant.common;

import org.robok.common.Asset;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * Asset filter limits the assets that will be processed in certain operations at a given time. Filters can work
 * on a combination of assets and time.
 *
 * Common use case is strategies that are only interested in a subset of assets that are available in a feed.
 */
@FunctionalInterface
public interface AssetFilter {

    /**
     * Returns true if the provided asset should be processed at the provided time, false otherwise. The time can
     * be used by implementations to support asset collections that change over time, like, for example, the S&P 500
     * index.
     */
    boolean filter(Asset asset, Instant time);

    /**
     * Include all assets, so this filter always returns true.
     */
    static AssetFilter all() {
        return (asset, time) -> true;
    }

    private static String standardize(String str) {
        return str.toUpperCase().replaceAll("[^A-Z0-9]", ".");
    }

    /**
     * Include only the assets that match the provided symbols.
     */
    static AssetFilter includeSymbols(String... symbols) {
        Set<String> set = new HashSet<>();
        for (String symbol : symbols) {
            set.add(standardize(symbol));
        }
        return (asset, time) -> set.contains(standardize(asset.getSymbol()));
    }

    /**
     * Include only the assets that match the provided symbols.
     */
    static AssetFilter includeSymbols(java.util.Collection<String> symbols) {
        Set<String> set = new HashSet<>();
        for (String symbol : symbols) {
            set.add(standardize(symbol));
        }
        return (asset, time) -> set.contains(standardize(asset.getSymbol()));
    }

    /**
     * Exclude the assets that match the provided symbols.
     */
    static AssetFilter excludeSymbols(String... symbols) {
        Set<String> set = new HashSet<>();
        for (String symbol : symbols) {
            set.add(standardize(symbol));
        }
        return (asset, time) -> !set.contains(standardize(asset.getSymbol()));
    }

    /**
     * Exclude the assets that match the provided symbols.
     */
    static AssetFilter excludeSymbols(java.util.Collection<String> symbols) {
        Set<String> set = new HashSet<>();
        for (String symbol : symbols) {
            set.add(standardize(symbol));
        }
        return (asset, time) -> !set.contains(standardize(asset.getSymbol()));
    }
}
