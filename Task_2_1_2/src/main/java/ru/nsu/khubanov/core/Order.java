package ru.nsu.khubanov.core;


public class Order {
    private final int id;
    private volatile boolean prepared = false;
    private volatile boolean delivered = false;

    public Order(int id) {
        this.id = id;
    }

    public int getId() { return id; }

    public boolean isPrepared() { return prepared; }

    public boolean isDelivered() { return delivered; }

    public void markPrepared() {
        this.prepared = true;
    }

    public void markDelivered() {
        this.delivered = true;
    }
}

