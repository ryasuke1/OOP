package ru.nsu.khubanov;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @Test
    void testStorage_AddAndTake() throws InterruptedException {
        Storage storage = new Storage(3);
        Order order1 = new Order(1);
        Order order2 = new Order(2);

        storage.add(order1);
        storage.add(order2);

        assertEquals(order1, storage.take());
        assertEquals(order2, storage.take());
    }

    @Test
    void testStorage_WaitIfFull() throws InterruptedException {
        Storage storage = new Storage(1);
        Order order1 = new Order(1);
        Order order2 = new Order(2);

        storage.add(order1);

        Thread producer = new Thread(() -> {
            try {
                Thread.sleep(1000);
                storage.take();
            } catch (InterruptedException e) {
                fail();
            }
        });
        producer.start();

        long startTime = System.currentTimeMillis();
        storage.add(order2);
        long endTime = System.currentTimeMillis();

        assertTrue(endTime - startTime >= 1000);
    }
}
