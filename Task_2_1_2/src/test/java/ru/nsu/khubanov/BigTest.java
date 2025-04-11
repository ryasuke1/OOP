package ru.nsu.khubanov;

import org.junit.jupiter.api.Test;
import ru.nsu.khubanov.config.PizzeriaConfig;
import ru.nsu.khubanov.core.AbstractQueue;
import ru.nsu.khubanov.core.Order;
import ru.nsu.khubanov.core.queue.OrderQueue;
import ru.nsu.khubanov.core.queue.Storage;
import ru.nsu.khubanov.management.Manager;
import ru.nsu.khubanov.order.RandomOrderGenerator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BigTest {

    @Test
    void testAllOrdersArePreparedAndDelivered() throws InterruptedException {
        int totalOrders = 1500;

        PizzeriaConfig config = new PizzeriaConfig(100, 100, 15, 8000);


        AbstractQueue<Order> orderQueue = new OrderQueue(config.getStorageCapacity());
        AbstractQueue<Order> storage = new Storage(config.getStorageCapacity());

        List<Order> orders = new ArrayList<>();
        RandomOrderGenerator generator = new RandomOrderGenerator(orderQueue) {
            @Override
            public void run() {
                for (int i = 0; i < totalOrders; i++) {
                    Order order = new Order(i);
                    orders.add(order);
                    try {
                        orderQueue.add(order);
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }

            @Override
            public void shutdown() {
                this.interrupt();
            }
        };

        Manager manager = new Manager(config, orderQueue, storage, generator);
        generator.setManager(manager);
        manager.start();

        Thread.sleep(config.getSimulationTime() + 2000);
        manager.stop();

        assertTrue(orderQueue.isEmpty(), "Очередь заказов должна быть пуста");
        assertTrue(storage.isEmpty(), "Склад должен быть пуст");

        for (Order order : orders) {
            assertTrue(order.isPrepared(), "Заказ " + order.getId() + " не был приготовлен");
            assertTrue(order.isDelivered(), "Заказ " + order.getId() + " не был доставлен");
        }
    }
}
