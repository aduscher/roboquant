package org.roboquant.feeds;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

/**
 * Default implementations for Feed-related functionality.
 */
public final class FeedDefaults {

    private static final ExecutorService executor = Executors.newCachedThreadPool();

    private FeedDefaults() { }

    public static ExecutorService getExecutor() {
        return executor;
    }

    public static void shutdown() {
        executor.shutdown();
    }
}
