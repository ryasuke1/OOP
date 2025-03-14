package ru.nsu.khubanov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Baker extends Thread {
    private static final Logger logger = LogManager.getLogger(Baker.class);

    private final int id;
    private final int speed;
    private final OrderQueue orderQueue;
    private final Storage storage;
    private final Manager manager;

    public Baker(int id, int speed, OrderQueue orderQueue, Storage storage, Manager manager) {
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
                storage.add(order);
            }
        } catch (InterruptedException e) {
            logger.info(" Пекарь " + id + " завершает работу.");
        }
    }
}
