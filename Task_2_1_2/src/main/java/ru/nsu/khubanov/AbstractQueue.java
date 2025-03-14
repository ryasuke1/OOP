package ru.nsu.khubanov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractQueue<T> {
    public final int capacity;
    protected static final Logger logger = LogManager.getLogger(AbstractQueue.class);

    public AbstractQueue(int capacity) {
        this.capacity = capacity;
    }

    public abstract void add(T item) throws InterruptedException;

    public abstract T take() throws InterruptedException;

    public abstract boolean isEmpty();
}
