package ru.nsu.khubanov.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractQueue<T> {
    protected final int capacity;
    protected static final Logger logger = LogManager.getLogger(AbstractQueue.class);

    protected AbstractQueue(int capacity) {
        this.capacity = capacity;
    }

    protected boolean shutdown = false;

    public synchronized void shutdown() {
        shutdown = true;
        notifyAll(); // Разбудим ожидающих
    }

    public abstract void add(T item) throws InterruptedException;

    public abstract T take() throws InterruptedException;

    public abstract boolean isEmpty();
}
