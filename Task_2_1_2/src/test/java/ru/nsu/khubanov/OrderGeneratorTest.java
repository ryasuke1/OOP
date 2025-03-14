package ru.nsu.khubanov;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderGeneratorTest {

    @Test
    void testOrderGenerator_CreatesOrders() throws InterruptedException {
        OrderQueue orderQueue = new OrderQueue(10);
        Manager manager = new Manager(new PizzeriaConfig(1, 1, 10, 5000));

        OrderGenerator orderGenerator = new OrderGenerator(orderQueue, manager);
        orderGenerator.start();

        Thread.sleep(3000); // Даем генератору поработать

        assertFalse(orderQueue.isEmpty(), "Очередь заказов не должна быть пустой");
        orderGenerator.interrupt();
    }
}
