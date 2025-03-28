package ru.nsu.khubanov;

import org.junit.jupiter.api.Test;
import ru.nsu.khubanov.config.PizzeriaConfig;
import ru.nsu.khubanov.core.AbstractQueue;
import ru.nsu.khubanov.core.Order;
import ru.nsu.khubanov.core.queue.OrderQueue;
import ru.nsu.khubanov.core.queue.Storage;
import ru.nsu.khubanov.management.Manager;
import ru.nsu.khubanov.order.RandomOrderGenerator;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    @Test
    void testManager_StartAndStop() throws InterruptedException {
        PizzeriaConfig config = new PizzeriaConfig(2, 2, 5, 5000);

        AbstractQueue<Order> orderQueue = new OrderQueue(config.getStorageCapacity());
        AbstractQueue<Order> storage = new Storage(config.getStorageCapacity());
        RandomOrderGenerator generator = new RandomOrderGenerator(orderQueue);

        Manager manager = new Manager(config, orderQueue, storage, generator);
        generator.setManager(manager);

        Thread managerThread = new Thread(() -> {
            try {
                manager.start();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        managerThread.start();

        Thread.sleep(2000); // пусть немного поработает

        manager.stop(); // корректно остановим

        assertFalse(manager.isRunning(), "Менеджер должен быть остановлен");
    }
}
