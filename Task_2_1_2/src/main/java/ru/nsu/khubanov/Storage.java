package ru.nsu.khubanov;

import java.util.LinkedList;
import java.util.Queue;

public class Storage extends AbstractQueue<Order> {
    private final Queue<Order> queue = new LinkedList<>();

    public Storage(int capacity) {
        super(capacity);
    }

    @Override
    public synchronized void add(Order order) throws InterruptedException {
        while (queue.size() >= capacity) {
            logger.warn("Склад заполнен, ожидание...");
            wait();
        }
        queue.add(order);
        logger.info("Пицца добавлена на склад: " + order.getId());
        notifyAll();
    }

    @Override
    public synchronized Order take() throws InterruptedException {
        while (queue.isEmpty()) {
            logger.warn("Склад пуст, ожидание...");
            wait();
        }
        Order order = queue.poll();
        logger.info("Пицца взята курьером: " + order.getId());
        notifyAll();
        return order;
    }

    @Override
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
