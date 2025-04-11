package ru.nsu.khubanov.task_2_3_1.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import ru.nsu.khubanov.task_2_3_1.model.food.Apple;
import ru.nsu.khubanov.task_2_3_1.model.food.Bomb;
import ru.nsu.khubanov.task_2_3_1.model.food.Food;
import ru.nsu.khubanov.task_2_3_1.model.food.SpeedBoost;
import ru.nsu.khubanov.task_2_3_1.util.GameLoopThread;
import ru.nsu.khubanov.task_2_3_1.util.SceneManager;
import ru.nsu.khubanov.task_2_3_1.model.Cell;
import ru.nsu.khubanov.task_2_3_1.model.Direction;
import ru.nsu.khubanov.task_2_3_1.model.GameField;
import ru.nsu.khubanov.task_2_3_1.model.Snake;

public class GameController {
    @FXML
    private Pane gamePane;

    private GameField gameField;
    private GameLoopThread gameThread;

    private final int CELL_SIZE = 20;
    private final int FIELD_WIDTH = 20;
    private final int FIELD_HEIGHT = 20;
    private final int FOOD_COUNT = 3;
    private final int WIN_LENGTH = 15;

    public void initialize() {
        gameField = new GameField(FIELD_WIDTH, FIELD_HEIGHT, FOOD_COUNT, WIN_LENGTH);

        gamePane.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case UP -> gameField.getPlayerSnake().setDirection(Direction.UP);
                        case DOWN -> gameField.getPlayerSnake().setDirection(Direction.DOWN);
                        case LEFT -> gameField.getPlayerSnake().setDirection(Direction.LEFT);
                        case RIGHT -> gameField.getPlayerSnake().setDirection(Direction.RIGHT);
                    }
                });
            }
        });

        startGame();
    }

    public void startGame() {
        gameField = new GameField(FIELD_WIDTH, FIELD_HEIGHT, FOOD_COUNT, WIN_LENGTH);
        drawGame(); // рисуем стартовую картинку

        gameThread = new GameLoopThread(this);
        gameThread.start();
    }
    public GameField getGameField() {
        return gameField;
    }

    public void checkGameEnd() {
        if (gameField.isGameOver()) {
            gameThread.stopLoop();
            SceneManager.switchScene("/gameover.fxml", gameField.getPlayerSnake().getBody().size(), false);
        } else if (gameField.isVictory()) {
            gameThread.stopLoop();
            SceneManager.switchScene("/victory.fxml", gameField.getPlayerSnake().getBody().size(), true);
        }
    }




    public void drawGame() {
        gamePane.getChildren().clear();

        for (Snake snake : gameField.getSnakes()) {
            for (Cell cell : snake.getBody()) {
                Rectangle r = new Rectangle(cell.getX() * CELL_SIZE, cell.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                r.setFill(snake == gameField.getPlayerSnake() ? Color.GREEN : Color.BLUE);
                gamePane.getChildren().add(r);
            }
        }

        for (Food food : gameField.getFoods()) {
            Cell cell = food.getPosition();
            Rectangle r = new Rectangle(cell.getX() * CELL_SIZE, cell.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);

            if (food instanceof Apple) r.setFill(Color.RED);
            else if (food instanceof Bomb) r.setFill(Color.BLACK);
            else if (food instanceof SpeedBoost) r.setFill(Color.YELLOW);

            gamePane.getChildren().add(r);
        }

    }

}
