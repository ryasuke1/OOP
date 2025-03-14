package ru.nsu.khubanov;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            PizzeriaConfig config = PizzeriaConfig.loadConfig("src/main/resources/config.json");

            Manager manager = new Manager(config);
            manager.start();
        } catch (Exception e) {
            logger.error("Ошибка запуска пиццерии", e);
        }
    }
}
