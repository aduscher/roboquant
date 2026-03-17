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

import org.roboquant.common.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Live feed represents a feed of live data. So data that comes in as it is observed with timestamps
 * close to the present. Since a live feed has no pre-defined end, it will continue to run unless you specify
 * a timeframe during the run.
 *
 * This default implementation generates heartbeat signals to ensure components still have an opportunity
 * to act even if no other data is incoming. A heartbeat is an empty event.
 *
 * Changing this value during the play of a feed will not impact the interval of the first coming heartbeat.
 */
public abstract class LiveFeed implements Feed {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final List<org.robok.feeds.EventChannel> channels = Collections.synchronizedList(new ArrayList<>());
    private ExecutorService executor;

    /**
     * Return true if this live feed is being used in one or more runs, false otherwise
     */
    public boolean isActive() {
        return !channels.isEmpty();
    }

    /**
     * Subclasses should call this method or sendAsync to send an event. If no channel is active, any event
     * sent will be dropped.
     */
    protected void send(Event event) {
        sendAsync(event);
    }

    /**
     * Subclasses should call this method or send to send an event. If no channel is active, any
     * event sent will be dropped.
     */
    protected void sendAsync(Event event) {
        List<EventChannel> channelsCopy;
        synchronized (this) {
            channelsCopy = new ArrayList<>(channels);
        }
        for (EventChannel channel : channelsCopy) {
            try {
                channel.send(event);
            } catch (InterruptedException e) {
                logger.trace("closed channel", e);
                Thread.currentThread().interrupt();
            }
        }
        synchronized (this) {
            channels.removeIf(EventChannel::isClosed);
        }
    }

    /**
     * (Re)play the events of the feed using the provided EventChannel
     */
    @Override
    public void play(EventChannel channel) throws InterruptedException {
        synchronized (this) {
            channels.add(channel);
        }
        channel.waitOnClose();
    }

    @Override
    public Future<?> playBackground(EventChannel channel) {
        ExecutorService exec = getExecutor();
        return exec.submit(() -> {
            try {
                play(channel);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    @Override
    public ExecutorService getExecutor() {
        if (executor == null) {
            executor = Executors.newCachedThreadPool();
        }
        return executor;
    }
}
