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
import java.util.Random;
import java.util.function.Supplier;

/**
 * Random Search Space
 *
 * In Random Search, we try random combinations of the values of the [Params] and evaluate the trading strategy
 * for these selected combinations.
 *
 * @param size the total number of samples that will be drawn from this random search space
 */
public class RandomSearch implements SearchSpace {

    private final int size;
    private final LinkedHashMap<String, Entry> params = new LinkedHashMap<>();
    private final List<Params> permutations = new ArrayList<>();
    private final Random random = new Random();

    public RandomSearch(int size) {
        this.size = size;
    }

    /**
     * Add a parameter function.
     */
    public void add(String name, Supplier<?> fn) {
        params.put(name, new Entry(null, fn));
        permutations.clear();
    }

    /**
     * Add a parameter iterable.
     */
    public void add(String name, Iterable<?> values) {
        List<Object> list = new ArrayList<>();
        for (Object v : values) {
            list.add(v);
        }
        params.put(name, new Entry(list, null));
        permutations.clear();
    }

    private void calcPermutations() {
        if (!permutations.isEmpty()) return;
        for (int i = 0; i < size; i++) {
            Params p = new Params();
            for (Map.Entry<String, Entry> e : params.entrySet()) {
                Entry entry = e.getValue();
                Object value;
                if (entry.getList() != null) {
                    value = entry.getList().get(random.nextInt(entry.getList().size()));
                } else if (entry.getFn() != null) {
                    value = entry.getFn().get();
                } else {
                    continue;
                }
                p.put(e.getKey(), value);
            }
            permutations.add(p);
        }
    }

    private List<Params> getList() {
        calcPermutations();
        return permutations;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public java.util.Iterator<Params> iterator() {
        return getList().iterator();
    }

    @Override
    public void update(Params params, double score) {
    }

    private static class Entry {
        private final List<?> list;
        private final Supplier<?> fn;

        public Entry(List<?> list, Supplier<?> fn) {
            this.list = list;
            this.fn = fn;
        }

        public List<?> getList() {
            return list;
        }

        public Supplier<?> getFn() {
            return fn;
        }
    }
}
