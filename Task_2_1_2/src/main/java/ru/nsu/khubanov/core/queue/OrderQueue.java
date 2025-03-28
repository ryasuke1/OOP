package ru.nsu.khubanov.core.queue;

import ru.nsu.khubanov.core.AbstractQueue;
import ru.nsu.khubanov.core.Order;

import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue extends AbstractQueue<Order> {
    private final Queue<Order> queue = new LinkedList<>();

    public OrderQueue(int capacity) {
        super(capacity);
    }

    @Override
    public synchronized void add(Order order) throws InterruptedException {
        while(queue.size() >= capacity) {

            logger.warn("Очередь заказов заполнена, ожидание...");
            wait();
        }
        queue.add(order);
        logger.info("Добавлен заказ: " + order.getId());
        notifyAll();
    }

    @Override
    public synchronized Order take() throws InterruptedException {
        while (queue.isEmpty()) {
            if (shutdown) {
                return null;
            }
            logger.warn("Очередь заказов пуста, ожидание...");
            wait();
        }
        Order order = queue.poll();
        logger.info("Заказ взят в обработку: " + order.getId());
        notifyAll();
        return order;
    }

    @Override
    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
