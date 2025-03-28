package ru.nsu.khubanov;

import org.junit.jupiter.api.Test;
import ru.nsu.khubanov.config.PizzeriaConfig;
import ru.nsu.khubanov.core.AbstractQueue;
import ru.nsu.khubanov.core.Order;
import ru.nsu.khubanov.core.queue.OrderQueue;
import ru.nsu.khubanov.core.queue.Storage;
import ru.nsu.khubanov.management.Manager;
import ru.nsu.khubanov.order.OrderSource;
import ru.nsu.khubanov.order.RandomOrderGenerator;

import static org.junit.jupiter.api.Assertions.*;

class OrderGeneratorTest {

    @Test
    void testOrderGenerator_CreatesOrders() throws InterruptedException {
        PizzeriaConfig config = new PizzeriaConfig(1, 1, 10, 5000);

        AbstractQueue<Order> orderQueue = new OrderQueue(config.getStorageCapacity());
        AbstractQueue<Order> storage = new Storage(config.getStorageCapacity());
        RandomOrderGenerator orderSource = new RandomOrderGenerator(orderQueue);

        Manager manager = new Manager(config, orderQueue, storage, orderSource);
        orderSource.setManager(manager);

        orderSource.start();

        Thread.sleep(3000); // Даем генератору поработать

        assertFalse(orderQueue.isEmpty(), "Очередь заказов не должна быть пустой");
        orderSource.shutdown();
    }
}
