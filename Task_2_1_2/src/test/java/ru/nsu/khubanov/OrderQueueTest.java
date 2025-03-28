package ru.nsu.khubanov;

import org.junit.jupiter.api.Test;
import ru.nsu.khubanov.core.Order;
import ru.nsu.khubanov.core.queue.OrderQueue;

import static org.junit.jupiter.api.Assertions.*;

class OrderQueueTest {

    @Test
    void testOrderQueue_AddAndTake() throws InterruptedException {
        OrderQueue orderQueue = new OrderQueue(5);
        Order order1 = new Order(1);
        Order order2 = new Order(2);

        orderQueue.add(order1);
        orderQueue.add(order2);

        assertEquals(order1, orderQueue.take());
        assertEquals(order2, orderQueue.take());
    }

    @Test
    void testOrderQueue_WaitForOrder() throws InterruptedException {
        OrderQueue orderQueue = new OrderQueue(5);

        Thread producer = new Thread(() -> {
            try {
                Thread.sleep(1000);
                orderQueue.add(new Order(1));
            } catch (InterruptedException e) {
                fail();
            }
        });
        producer.start();

        long startTime = System.currentTimeMillis();
        Order order = orderQueue.take();
        long endTime = System.currentTimeMillis();

        assertNotNull(order);
        assertTrue(endTime - startTime >= 1000);
    }
}
