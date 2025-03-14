package ru.nsu.khubanov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TimerThread extends Thread {
    private static final Logger logger = LogManager.getLogger(TimerThread.class);
    private final Manager manager;
    private final int simulationTime;

    public TimerThread(Manager manager, int simulationTime) {
        this.manager = manager;
        this.simulationTime = simulationTime;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(simulationTime);
            manager.stop();
        } catch (InterruptedException e) {
            logger.error("Ошибка в таймере пиццерии", e);
        }
    }
}
