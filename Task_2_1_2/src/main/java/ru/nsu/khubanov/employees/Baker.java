package ru.nsu.khubanov.employees;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.khubanov.core.AbstractQueue;
import ru.nsu.khubanov.core.Order;
import ru.nsu.khubanov.core.queue.OrderQueue;
import ru.nsu.khubanov.core.queue.Storage;
import ru.nsu.khubanov.management.Manager;

public class Baker extends Thread {
    private static final Logger logger = LogManager.getLogger(Baker.class);

    private final int id;
    private final int speed;
    private final AbstractQueue<Order> orderQueue;
    private final AbstractQueue<Order> storage;
    private final Manager manager;

    public Baker(int id, int speed, AbstractQueue<Order> orderQueue, AbstractQueue<Order> storage, Manager manager) {
        this.id = id;
        this.speed = speed;
        this.orderQueue = orderQueue;
        this.storage = storage;
        this.manager = manager;
    }

    @Override
    public void run() {
        try {
            while (manager.isRunning() || !orderQueue.isEmpty()) {
                Order order = orderQueue.take();
                if (order == null) continue; // Если заказов нет, ждём
                logger.info("[" + order.getId() + "]  Пекарь " + id + " начал готовить пиццу");
                Thread.sleep(speed);
                logger.info("[" + order.getId() + "]  Пекарь " + id + " приготовил пиццу");
                order.markPrepared();
                storage.add(order);
            }
        } catch (InterruptedException e) {
            logger.info(" Пекарь " + id + " завершает работу.");
        }
    }
}
