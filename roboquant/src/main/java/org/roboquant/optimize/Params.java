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

import java.util.LinkedHashMap;

/**
 * This collection holds the (hyper-)parameters that can be used in a [SearchSpace] to define that space.
 */
public class Params extends LinkedHashMap<String, Object> {

    public Params() {
        super();
    }

    /**
     * Returns the [String] value for the provided parameter [name]
     */
    public String getString(String name) {
        return (String) get(name);
    }

    /**
     * Returns the [int] value for the provided parameter [name]
     */
    public int getInt(String name) {
        return (int) get(name);
    }

    /**
     * Returns the [double] value for the provided parameter [name]
     */
    public double getDouble(String name) {
        return (double) get(name);
    }

}
