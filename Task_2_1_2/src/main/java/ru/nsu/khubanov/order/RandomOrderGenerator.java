package ru.nsu.khubanov.order;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.khubanov.core.Order;
import ru.nsu.khubanov.core.AbstractQueue;
import ru.nsu.khubanov.management.Manager;

import java.util.Random;

public class RandomOrderGenerator extends Thread implements OrderSource {
    private static final Logger logger = LogManager.getLogger(RandomOrderGenerator.class);

    private final AbstractQueue<Order> queue;
    private Manager manager;
    private int orderId = 0;
    private boolean running = true;

    public RandomOrderGenerator(AbstractQueue<Order> queue) {
        this.queue = queue;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (running && manager != null && manager.isAcceptingOrders()) {
            try {
                Thread.sleep(1000 + random.nextInt(2000));
                Order order = new Order(orderId++);
                queue.add(order);
                logger.info(" Новый заказ поступил [" + order.getId() + "]");
            } catch (InterruptedException ignored) {}
        }
    }

    public void shutdown() {
        running = false;
        this.interrupt();
    }
}
