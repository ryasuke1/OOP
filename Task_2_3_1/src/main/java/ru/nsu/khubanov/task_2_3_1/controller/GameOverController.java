package ru.nsu.khubanov.task_2_3_1.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import ru.nsu.khubanov.task_2_3_1.util.SceneManager;

public class GameOverController {

    @FXML
    private Label scoreLabel;

    @FXML
    private Label messageLabel; // 👈 новый элемент для причины проигрыша

    public void setScore(int score) {
        scoreLabel.setText("Счёт: " + score);
    }

    public void setBotWin(boolean botWin) {
        if (messageLabel != null) {
            if (botWin) {
                messageLabel.setText("❌ Ты проиграл! Победила вражеская змея!");
            } else {
                messageLabel.setText("❌ Ты проиграл! Ты врезался.");
            }
        }
    }

    public void onRetryClicked(ActionEvent event) {
        SceneManager.switchScene("/start.fxml");
    }
}
