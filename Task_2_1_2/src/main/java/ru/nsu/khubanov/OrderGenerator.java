package ru.nsu.khubanov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class OrderGenerator extends Thread {
    private static final Logger logger = LogManager.getLogger(OrderGenerator.class);

    private final OrderQueue orderQueue;
    private final Manager manager;
    private int orderId = 0;
    private final Random random = new Random();

    public OrderGenerator(OrderQueue orderQueue, Manager manager) {
        this.orderQueue = orderQueue;
        this.manager = manager;
    }

    @Override
    public void run() {
        try {
            while (manager.isAcceptingOrders()) {
                Thread.sleep(1000 + random.nextInt(2000)); // Генерация заказов случайно (1-3 сек)
                Order order = new Order(orderId++);
                orderQueue.add(order);
                logger.info(" Поступил новый заказ [" + order.getId() + "]");
            }
        } catch (InterruptedException e) {
            logger.info("❌ Генератор заказов завершает работу.");
        }
    }
}
