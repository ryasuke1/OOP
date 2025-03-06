package ru.nsu.khubanov;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int numBakers = 3;
        int numCouriers = 2;
        int storageCapacity = 5;
        int totalOrders = 10;
        int simulationTime = 15000;

        OrderQueue orderQueue = new OrderQueue();
        Storage storage = new Storage(storageCapacity);
        List<Baker> bakers = new ArrayList<>();
        List<Courier> couriers = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < numBakers; i++) {
            bakers.add(new Baker(i, 1000 + random.nextInt(2000), orderQueue, storage));
        }

        for (int i = 0; i < numCouriers; i++) {
            couriers.add(new Courier(i, 3, storage));
        }
        bakers.forEach(Thread::start);
        couriers.forEach(Thread::start);

        for (int i = 0; i < totalOrders; i++) {
            Thread.sleep(500);
            orderQueue.addOrder(new Order(i));
            System.out.println("[" + i + "] Новый заказ поступил");
        }

        Thread.sleep(simulationTime);

        bakers.forEach(Thread::interrupt);
        couriers.forEach(Thread::interrupt);

        System.out.println("Пиццерия закрывается!");
    }
}