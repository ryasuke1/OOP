package ru.nsu.khubanov.task_2_3_1.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import ru.nsu.khubanov.task_2_3_1.util.SceneManager;

public class VictoryController {
    @FXML
    private Label scoreLabel;

    public void setScore(int score) {
        scoreLabel.setText("Счёт: " + score);
    }

    public void onRetryClicked(ActionEvent event) {
        SceneManager.switchScene("/start.fxml");
    }
}
