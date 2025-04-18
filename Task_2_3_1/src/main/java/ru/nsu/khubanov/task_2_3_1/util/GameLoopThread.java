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
        long lastUpdate = System.nanoTime();

        while (running && !controller.getGameField().isGameOver() && !controller.getGameField().isVictory()) {
            long delay = controller.getGameField().getTickDelay() * 1_000_000L;
            long now = System.nanoTime();

            if (now - lastUpdate >= delay) {
                Platform.runLater(() -> {
                    controller.getGameField().update();
                    controller.drawGame();
                    controller.checkGameEnd();
                });

                lastUpdate += delay;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                running = false;
                break;
            }
        }
    }


    public void stopLoop() {
        running = false;
    }
}
