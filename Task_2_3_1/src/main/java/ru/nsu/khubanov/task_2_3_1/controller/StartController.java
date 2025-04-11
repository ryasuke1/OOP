package ru.nsu.khubanov.task_2_3_1.controller;

import javafx.event.ActionEvent;
import ru.nsu.khubanov.task_2_3_1.util.SceneManager;

public class StartController {
    public void onStartClicked(ActionEvent event) {
        SceneManager.switchScene("/game.fxml");
    }
}
