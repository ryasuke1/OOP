package ru.nsu.khubanov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Manager {
    private static final Logger logger = LogManager.getLogger(Manager.class);

    private final PizzeriaConfig config;
    private final OrderQueue orderQueue;
    private final Storage storage;
    private final List<Baker> bakers;
    private final List<Courier> couriers;
    private final OrderGenerator orderGenerator;
    private boolean isAcceptingOrders = true;
    private boolean isRunning = true;

    public Manager(PizzeriaConfig config) {
        this.config = config;
        this.orderQueue = new OrderQueue(config.getStorageCapacity());
        this.storage = new Storage(config.getStorageCapacity());
        this.bakers = new ArrayList<>();
        this.couriers = new ArrayList<>();
        this.orderGenerator = new OrderGenerator(orderQueue, this);
    }

    public void start() throws InterruptedException {
        Random random = new Random();

        // Запуск генерации заказов
        orderGenerator.start();

        // Инициализация пекарей
        for (int i = 0; i < config.getNumBakers(); i++) {
            bakers.add(new Baker(i, 1000 + random.nextInt(2000), orderQueue, storage, this));
        }

        // Инициализация курьеров
        for (int i = 0; i < config.getNumCouriers(); i++) {
            couriers.add(new Courier(i, 3, storage, this));
        }

        // Запуск потоков
        bakers.forEach(Thread::start);
        couriers.forEach(Thread::start);

        // Запуск таймера на завершение работы
        new TimerThread(this, config.getSimulationTime()).start();
    }

    public synchronized boolean isRunning() {
        return isRunning;
    }

    public synchronized boolean isAcceptingOrders() {
        return isAcceptingOrders;
    }

    public synchronized void stop() {
        logger.info("⏳ Пиццерия прекращает принимать заказы! ⏳");
        isAcceptingOrders = false;
        orderGenerator.interrupt(); // Останавливаем генератор заказов

        // Ждём, пока все заказы будут обработаны
        while (!orderQueue.isEmpty() || !storage.isEmpty()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }

        logger.info("🏁 Все заказы обработаны. Закрываем пиццерию...");

        isRunning = false;

        // Останавливаем всех пекарей и курьеров
        bakers.forEach(Thread::interrupt);
        couriers.forEach(Thread::interrupt);

        // Дожидаемся завершения всех потоков
        try {
            orderGenerator.join();
            for (Thread thread : bakers) {
                thread.join();
            }
            for (Thread thread : couriers) {
                thread.join();
            }
        } catch (InterruptedException e) {
            logger.error("Ошибка при остановке пиццерии", e);
        }

        logger.info(" Пиццерия полностью закрыта.");
    }
}
