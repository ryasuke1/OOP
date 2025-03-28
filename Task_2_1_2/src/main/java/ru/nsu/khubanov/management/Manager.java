package ru.nsu.khubanov.management;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.nsu.khubanov.config.PizzeriaConfig;
import ru.nsu.khubanov.core.AbstractQueue;
import ru.nsu.khubanov.core.Order;
import ru.nsu.khubanov.employees.Baker;
import ru.nsu.khubanov.employees.Courier;
import ru.nsu.khubanov.order.OrderSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Manager {
    private static final Logger logger = LogManager.getLogger(Manager.class);

    private final PizzeriaConfig config;
    private final AbstractQueue<Order> orderQueue;
    private final AbstractQueue<Order> storage;
    private final OrderSource orderSource;
    private final List<Baker> bakers = new ArrayList<>();
    private final List<Courier> couriers = new ArrayList<>();

    private volatile boolean isAcceptingOrders = true;
    private volatile boolean isRunning = true;

    public Manager(PizzeriaConfig config,
                   AbstractQueue<Order> orderQueue,
                   AbstractQueue<Order> storage,
                   OrderSource orderSource) {
        this.config = config;
        this.orderQueue = orderQueue;
        this.storage = storage;
        this.orderSource = orderSource;
    }

    public void start() throws InterruptedException {
        logger.info(" Пиццерия запускается");

        orderSource.start();

        Random random = new Random();

        for (int i = 0; i < config.getNumBakers(); i++) {
            bakers.add(new Baker(i, 1000 + random.nextInt(2000), orderQueue, storage, this));
        }

        for (int i = 0; i < config.getNumCouriers(); i++) {
            couriers.add(new Courier(i, 3, storage, this));
        }

        bakers.forEach(Thread::start);
        couriers.forEach(Thread::start);

        new TimerThread(this, config.getSimulationTime()).start();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isAcceptingOrders() {
        return isAcceptingOrders;
    }

    public void stop() {
        isAcceptingOrders = false;
        isRunning = false;

        orderQueue.shutdown();
        storage.shutdown();

        for (Thread baker : bakers) {
            try {
                baker.join();
            } catch (InterruptedException ignored) {}
        }

        for (Thread courier : couriers) {
            try {
                courier.join();
            } catch (InterruptedException ignored) {}
        }

        logger.info("✅ Пиццерия закрыта.");
    }

}
