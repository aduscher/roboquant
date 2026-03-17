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

package org.roboquant.feeds;

import org.roboquant.common.Timeframe;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;

/**
 * Interface that any data feed needs to implement. A feed can deliver any type of information, ranging from
 * stock prices to social media content.
 *
 * Feeds can represent historic data, for example, during back testing, and live feeds during live trading.
 */
public interface Feed extends AutoCloseable {

    /**
     * Timeframe of the feed. In case the timeframe is not known upfront, the default is to return Timeframe.INFINITE
     */
    default Timeframe getTimeframe() {
        return Timeframe.INFINITE;
    }

    /**
     * A feed may hold resources (such as file or socket handles) until it is closed. Calling the close() method
     * on a feed ensures prompt release of these resources, avoiding resource exhaustion.
     *
     * This is part of the AutoCloseable interface that any feed can override. If not implemented, the default
     * behavior is to do nothing.
     */
    default void close() {
        // default is to do nothing
    }

    /**
     * Get the executor service used for background play. Override this to provide custom executor.
     */
    default ExecutorService getExecutor() {
        return FeedDefaults.getExecutor();
    }

    /**
     * (Re)play the events of the feed. In Java, we return a CompletableFuture
     * to represent the asynchronous execution of this task (mapping the `suspend` keyword).
     */
    CompletableFuture<Void> play(EventChannel channel) throws InterruptedException;

    /**
     * (Re)play the events of the feed in the background without blocking the current thread.
     */
    default CompletableFuture<Void> playBackground(EventChannel channel) {
        return CompletableFuture.runAsync(() -> {
            try (EventChannel c = channel) {
                play(c).join(); // wait for the play method to finish
            } catch (Exception e) {
                throw new RuntimeException("Error playing feed", e);
            }
        });
    }

}
