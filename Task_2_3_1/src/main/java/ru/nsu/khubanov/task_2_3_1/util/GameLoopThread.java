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
        while (running && !controller.getGameField().isGameOver() && !controller.getGameField().isVictory()) {
            Platform.runLater(() -> {
                controller.getGameField().update();
                controller.drawGame();
                controller.checkGameEnd();
            });

            try {
                Thread.sleep(controller.getGameField().getTickDelay());
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
