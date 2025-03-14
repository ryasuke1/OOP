package ru.nsu.khubanov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BakerTest {

    @Test
    void testBaker_PreparesPizza() throws InterruptedException {
        OrderQueue orderQueue = new OrderQueue(5);
        Storage storage = new Storage(5);
        Manager manager = new Manager(new PizzeriaConfig(1, 1, 5, 5000));

        Baker baker = new Baker(1, 1000, orderQueue, storage, manager);
        baker.start();

        orderQueue.add(new Order(1));

        Thread.sleep(1500); // Даем время пекарю приготовить

        assertFalse(storage.isEmpty());
    }
}
