package ru.nsu.khubanov;

import ru.nsu.khubanov.config.PizzeriaConfig;
import ru.nsu.khubanov.core.AbstractQueue;
import ru.nsu.khubanov.core.Order;
import ru.nsu.khubanov.core.queue.OrderQueue;
import ru.nsu.khubanov.core.queue.Storage;
import ru.nsu.khubanov.management.Manager;
import ru.nsu.khubanov.order.OrderSource;
import ru.nsu.khubanov.order.RandomOrderGenerator;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        PizzeriaConfig config = PizzeriaConfig.loadConfig("src/main/resources/config.json");

        AbstractQueue<Order> orderQueue = new OrderQueue(config.getStorageCapacity());
        AbstractQueue<Order> storage = new Storage(config.getStorageCapacity());

        RandomOrderGenerator generator = new RandomOrderGenerator(orderQueue);
        OrderSource orderSource = generator;

        Manager manager = new Manager(config, orderQueue, storage, orderSource);
        generator.setManager(manager);

        manager.start();
    }
}
