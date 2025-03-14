package ru.nsu.khubanov;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    @Test
    void testManager_StartAndStop() throws InterruptedException {
        PizzeriaConfig config = new PizzeriaConfig(1, 1, 5, 5000);
        Manager manager = new Manager(config);

        Thread managerThread = new Thread(() -> {
            try {
                manager.start();
            } catch (InterruptedException e) {
                fail();
            }
        });
        managerThread.start();

        Thread.sleep(2000);
        manager.stop();

        assertFalse(manager.isRunning());
    }
}
