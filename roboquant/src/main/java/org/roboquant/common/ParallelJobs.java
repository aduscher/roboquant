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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * This is utility to make running jobs in parallel more convenient. Basic usage:
 *
 * <pre>
 * ParallelJobs jobs = new ParallelJobs();
 * jobs.add(() -&gt; runAsync(feed, SomeStrategy()));
 * jobs.joinAll();
 * </pre>
 *
 * Note that most feeds can be shared across runs, but that isn't true for the other components
 * like strategy, trader, metrics and trader. These are stateful and should not be shared.
 */
public class ParallelJobs {

    private static final Logger logger = LoggerFactory.getLogger(ParallelJobs.class);

    private final ExecutorService executor;
    private final List<Future<?>> jobs = new ArrayList<>();

    public ParallelJobs() {
        this.executor = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors()
        );
    }

    public ParallelJobs(int threads) {
        this.executor = Executors.newFixedThreadPool(threads);
    }

    public ParallelJobs(ExecutorService executor) {
        this.executor = executor;
    }

    /**
     * Wait for all the jobs to finish.
     */
    public void joinAll() {
        for (Future<?> job : jobs) {
            try {
                job.get();
            } catch (Exception e) {
                // Log but continue waiting for other jobs
                logger.error("Job failed: " + e.getMessage());
            }
        }
        jobs.clear();
    }

    /**
     * Wait for all the jobs to finish, using blocking mode. This is especially useful in Jupyter Notebooks and other
     * interactive development environments.
     */
    public void joinAllBlocking() {
        joinAll();
    }

    /**
     * The number of instantiated jobs.
     */
    public int getSize() {
        return jobs.size();
    }

    /**
     * Cancel all the jobs.
     */
    public void cancelAll() {
        for (Future<?> job : jobs) {
            job.cancel(true);
        }
        jobs.clear();
    }

    /**
     * Add a new job to the list and run it.
     */
    public Future<?> add(Runnable runnable) {
        Future<?> future = executor.submit(runnable);
        jobs.add(future);
        return future;
    }

    /**
     * Add a job.
     */
    public void add(Future<?> future) {
        jobs.add(future);
    }

    /**
     * Shutdown the executor service.
     */
    public void shutdown() {
        executor.shutdown();
    }

    /**
     * Shutdown now.
     */
    public List<Runnable> shutdownNow() {
        return executor.shutdownNow();
    }

    /**
     * Await termination.
     */
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return executor.awaitTermination(timeout, unit);
    }
}
