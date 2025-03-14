package ru.nsu.khubanov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourierTest {

    @Test
    void testCourier_DeliversPizza() throws InterruptedException {
        Storage storage = new Storage(5);
        Manager manager = new Manager(new PizzeriaConfig(1, 1, 5, 5000));

        Courier courier = new Courier(1, 3, storage, manager);
        courier.start();

        storage.add(new Order(1));
        storage.add(new Order(2));

        Thread.sleep(3000); // Даем время курьеру забрать пиццы

        assertTrue(storage.isEmpty());
    }
}
