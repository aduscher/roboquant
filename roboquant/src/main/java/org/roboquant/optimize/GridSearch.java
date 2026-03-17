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

package org.roboquant.optimize;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Create a Grid Search Space.
 *
 * In Grid Search, we try every combination of the values of the [Params] and evaluate the trading strategy for
 * each combination.
 */
public class GridSearch implements SearchSpace {

    private final LinkedHashMap<String, List<Object>> params = new LinkedHashMap<>();
    private final List<Map.Entry<String, List<Object>>> entries = new ArrayList<>();
    private final List<Params> permutations = new ArrayList<>();

    public GridSearch() {
    }

    /**
     * Add a parameter [name] with [values] to the search space.
     */
    public void add(String name, Iterable<?> values) {
        List<Object> list = new ArrayList<>();
        for (Object v : values) {
            list.add(v);
        }
        params.put(name, list);
        entries.clear();
        permutations.clear();
    }

    /**
     * Add a parameter [name] with [samples] values drawn from the provided [fn].
     */
    public void add(String name, int samples, Supplier<?> fn) {
        List<Object> list = new ArrayList<>(samples);
        for (int i = 0; i < samples; i++) {
            list.add(fn.get());
        }
        params.put(name, list);
        entries.clear();
        permutations.clear();
    }

    private List<Map.Entry<String, List<Object>>> getEntries() {
        if (entries.isEmpty()) {
            entries.addAll(params.entrySet());
        }
        return entries;
    }

    private void calcPermutations() {
        if (!permutations.isEmpty()) return;
        calcPermutationsInternal(new Params(), 0);
    }

    private void calcPermutationsInternal(Params entry, int idx) {
        List<Map.Entry<String, List<Object>>> entriesList = getEntries();
        Map.Entry<String, List<Object>> e = entriesList.get(idx);
        String key = e.getKey();
        List<Object> values = e.getValue();
        for (Object value : values) {
            entry.put(key, value);
            if (idx == entriesList.size() - 1) {
                Params clone = new Params();
                clone.putAll(entry);
                permutations.add(clone);
            } else {
                calcPermutationsInternal(entry, idx + 1);
            }
        }
    }

    private List<Params> getList() {
        calcPermutations();
        return permutations;
    }

    @Override
    public int getSize() {
        int result = 1;
        for (List<?> v : params.values()) {
            result *= v.size();
        }
        return result;
    }

    @Override
    public java.util.Iterator<Params> iterator() {
        return getList().iterator();
    }

    @Override
    public void update(Params params, double score) {
    }
}
