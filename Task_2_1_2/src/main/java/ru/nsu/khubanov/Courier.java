package ru.nsu.khubanov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Courier extends Thread {
    private static final Logger logger = LogManager.getLogger(Courier.class);

    private final int id;
    private final int capacity;
    private final Storage storage;
    private final Manager manager;

    public Courier(int id, int capacity, Storage storage, Manager manager) {
        this.id = id;
        this.capacity = capacity;
        this.storage = storage;
        this.manager = manager;
    }

    @Override
    public void run() {
        try {
            while (manager.isRunning() || !storage.isEmpty()) {
                List<Order> pizzas = new ArrayList<>();

                for (int i = 0; i < capacity; i++) {
                    Order order = storage.take();
                    if (order != null) {
                        pizzas.add(order);
                    }
                }

                if (!pizzas.isEmpty()) {
                    logger.info(" Курьер " + id + " доставляет " + pizzas.size() + " пицц");
                    Thread.sleep(2000);
                    logger.info("📦 Курьер " + id + " доставил заказ");
                } else {
                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException e) {
            logger.info(" Курьер " + id + " завершает работу.");
        }
    }
}
