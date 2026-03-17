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

import org.roboquant.common.ParallelJobs;
import org.roboquant.common.Timeframe;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Combines several live feeds into a single new feed. It assumes the feeds are delivering
 * the events in the right order. If any feed sends an event past the timeframe as configured
 * by the channel, the channel closes for all feeds.
 */
public class CombinedLiveFeed implements Feed {

    private final LiveFeed[] feeds;
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public CombinedLiveFeed(LiveFeed... feeds) {
        this.feeds = feeds;
    }

    public LiveFeed[] getFeeds() {
        return feeds;
    }

    @Override
    public CompletableFuture<Void> play(EventChannel channel) throws InterruptedException {
        ParallelJobs jobs = new ParallelJobs(executor);
        for (LiveFeed feed : feeds) {
            jobs.add(() -> {
                try {
                    feed.play(channel);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        jobs.joinAll();
    }

    @Override
    public void close() {
        for (LiveFeed feed : feeds) {
            feed.close();
        }
    }

    @Override
    public Timeframe getTimeframe() {
        return Timeframe.INFINITE;
    }

    @Override
    public Future<Void> playBackground(EventChannel channel) {
        return executor.submit(() -> {
            try {
               return play(channel);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    @Override
    public ExecutorService getExecutor() {
        return executor;
    }
}
