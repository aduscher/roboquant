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

/**
 * Interface for all types of search spaces.
 * A search space contains the parameters that define the space as well as how these parameters are accessed,
 * for example, grid or random.
 */
public interface SearchSpace extends Iterable<Params> {

    /**
     * Update the search space based on an observation.
     * The observation is a combination of the selected [params] and the resulting [score].
     *
     * This is not used by current search spaces, but is required for future search spaces like Bayesian search that
     * update their behavior based on observations.
     *
     * The default implementation is to do nothing.
     */
    default void update(Params params, double score) {
    }

    /**
     * Returns the total number of parameter combinations in this search space.
     * This will determine how many back tests are required to find the optimum parameter combination.
     */
    int getSize();

}
