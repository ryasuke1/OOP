package ru.nsu.khubanov.employees;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.khubanov.core.AbstractQueue;
import ru.nsu.khubanov.core.Order;
import ru.nsu.khubanov.management.Manager;

import java.util.ArrayList;
import java.util.List;

public class Courier extends Thread {
    private static final Logger logger = LogManager.getLogger(Courier.class);
    private final int id;
    private final int capacity;
    private final AbstractQueue<Order> storage;
    private final Manager manager;

    public Courier(int id, int capacity, AbstractQueue<Order> storage, Manager manager) {
        this.id = id;
        this.capacity = capacity;
        this.storage = storage;
        this.manager = manager;
    }

    @Override
    public void run() {
        try {
            List<Order> pizzas = new ArrayList<>();
            while (true) {
                // Собираем заказы до достижения вместимости
                while (pizzas.size() < capacity) {
                    Order order = storage.take();
                    if (order == null) { // если очередь завершена и пуста
                        break;
                    }
                    pizzas.add(order);
                }
                if (!pizzas.isEmpty()) {
                    logger.info(" Курьер " + id + " доставляет " + pizzas.size() + " пицц");
                    Thread.sleep(2000); // время доставки
                    logger.info(" Курьер " + id + " доставил заказ");
                    for (Order order : pizzas) {
                        order.markDelivered();
                    }
                    pizzas.clear();
                } else {
                    // Если менеджер завершил работу и склад пуст – завершаем работу курьера
                    if (!manager.isRunning() && storage.isEmpty()) {
                        break;
                    }
                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException e) {
            logger.info(" Курьер " + id + " завершает работу.");
        }
    }
}
