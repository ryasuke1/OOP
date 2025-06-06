package ru.nsu.khubanov.task_2_3_1;

import javafx.application.Application;
import javafx.stage.Stage;
import ru.nsu.khubanov.task_2_3_1.util.SceneManager;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneManager.setStage(primaryStage);
        SceneManager.switchScene("/start.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
