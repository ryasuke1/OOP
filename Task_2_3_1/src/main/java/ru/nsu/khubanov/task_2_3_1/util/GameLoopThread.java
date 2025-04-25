package ru.nsu.khubanov.task_2_3_1.util;

import javafx.application.Platform;
import ru.nsu.khubanov.task_2_3_1.controller.GameController;

public class GameLoopThread extends Thread {
    private final GameController controller;
    private volatile boolean running = true;

    public GameLoopThread(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void run() {
        long lastUpdate = System.nanoTime();               // момент последнего тика

        while (running &&
                !controller.getGameField().isGameOver() &&
                !controller.getGameField().isVictory()) {

            long delayNs = controller.getGameField().getTickDelay() * 1_000_000L;
            long now     = System.nanoTime();

            /* --------------------------------------------------------- *
             *  catch-up-loop: выполняем столько update, сколько нужно  *
             *  чтобы «догнать» текущее время                            *
             * --------------------------------------------------------- */
            while (now - lastUpdate >= delayNs) {

                Platform.runLater(() -> {
                    controller.getGameField().update();     // логика
                    controller.drawGame();                  // отрисовка
                    controller.checkGameEnd();              // финал
                });

                lastUpdate += delayNs;                      // следующий запланированный тик
                now = System.nanoTime();                    // пересчитываем «сейчас»
            }


        }
    }

    public void stopLoop() { running = false; }
}
