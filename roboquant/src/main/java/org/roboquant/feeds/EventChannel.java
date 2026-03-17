package org.roboquant.feeds;

import org.roboquant.common.Event;
import org.roboquant.common.Timeframe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Wrapper around a BlockingQueue for communicating the events of a Feed.
 * It uses asynchronous communication so the producing and consuming parts are decoupled.
 * An EventChannel has limited capacity to prevent memory problems when using large data feeds.
 *
 * It has built in support to limit the events that are being sent to a certain timeframe. It is guaranteed that
 * no events outside that timeframe can be delivered to the channel.
 */
public class EventChannel implements AutoCloseable, Cloneable, Iterable<Event> {

    private final Logger logger = LoggerFactory.getLogger(EventChannel.class);

    private final Timeframe timeframe;
    private final int capacity;
    private final BlockingQueue<Event> channel;
    private final ReentrantLock mutex = new ReentrantLock();
    private volatile boolean closed = false;

    public EventChannel(Timeframe timeframe, int capacity) {
        this.timeframe = timeframe != null ? timeframe : Timeframe.INFINITE;
        this.capacity = capacity > 0 ? capacity : 10;
        this.channel = new LinkedBlockingQueue<>(this.capacity);
    }

    public EventChannel() {
        this(Timeframe.INFINITE, 10);
    }

    public EventChannel(Timeframe timeframe) {
        this(timeframe, 10);
    }

    public Timeframe getTimeframe() {
        return timeframe;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isClosed() {
        return closed;
    }

    /**
     * Iterate over the events in this channel
     */
    @Override
    public Iterator<Event> iterator() {
        return channel.iterator();
    }

    /**
     * Send an event on this channel. If the time of event is before the timeframe of this channel, it will be
     * silently ignored. And if the event is after the timeframe, the channel will be closed.
     */
    public void send(Event event) throws InterruptedException {
        if (event.getTime().isAfter(timeframe.getEnd())) {
            logger.debug("send time={} timeframe={}, closing channel", event.getTime(), timeframe);
            close();
            return;
        }
        
        if (timeframe.contains(event.getTime())) {
            channel.put(event);
        }
    }

    /**
     * Send an event on this channel if it is not an empty event. If the time of event is before the timeframe
     * of this channel, it will be silently ignored. And if the event is after the timeframe, the channel
     * will be closed.
     */
    public void sendNotEmpty(Event event) throws InterruptedException {
        if (!event.isEmpty()) {
            send(event);
        }
    }

    /**
     * Receive an event from the channel with an optional timeout. If the timeout occurs within or before the timeframe,
     * a heartbeat (empty event) will be sent.
     *
     * This will throw an exception if the channel is closed.
     */
    public Event receive(long timeOutMillis) throws InterruptedException, ExecutionException {
        if (timeOutMillis <= 0) {
            Event event = channel.take();
            if (event.getTime().isAfter(timeframe.getEnd())) {
                close();
                throw new ExecutionException("channel closed", null);
            }
            return event;
        }

        Event event = channel.poll(timeOutMillis, TimeUnit.MILLISECONDS);
        if (event == null) {
            logger.debug("timeout occurred");
            Instant now = Instant.now();
            if (now.isAfter(timeframe.getEnd())) {
                close();
                throw new ExecutionException("channel closed", null);
            }
            return Event.empty(now);
        }
        
        if (event.getTime().isAfter(timeframe.getEnd())) {
            close();
            throw new java.util.concurrent.ExecutionException("channel closed", null);
        }
        return event;
    }

    /**
     * Receive an event from the channel without timeout.
     */
    public Event receive() throws InterruptedException, ExecutionException {
        return receive(-1);
    }

    /**
     * Close this EventChannel and mark it as closed
     */
    @Override
    public synchronized void close() {
        if (closed) return;
        closed = true;
        channel.clear();
        mutex.unlock();
    }

    /**
     * Wait for the channel to be closed.
     * If the channel is already closed when invoking this method, it will return immediately.
     */
    public void waitOnClose() throws InterruptedException {
        if (closed) return;
        mutex.lock();
        mutex.unlock();
    }

    /**
     * Make a copy. Events on the channel will not be copied, and the new channel will be open by default.
     */
    @Override
    public EventChannel clone() {
        return new EventChannel(timeframe, capacity);
    }

    /**
     * Check if the channel is empty
     */
    public boolean isEmpty() {
        return channel.isEmpty();
    }

    /**
     * Get the remaining capacity
     */
    public int remainingCapacity() {
        return channel.remainingCapacity();
    }

    /**
     * Get the current size of the channel
     */
    public int size() {
        return channel.size();
    }
}
