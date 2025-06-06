package ru.nsu.khubanov.task_2_3_1.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ru.nsu.khubanov.task_2_3_1.model.food.Apple;
import ru.nsu.khubanov.task_2_3_1.model.food.Bomb;
import ru.nsu.khubanov.task_2_3_1.model.food.Food;
import ru.nsu.khubanov.task_2_3_1.model.food.SpeedBoost;
import ru.nsu.khubanov.task_2_3_1.model.gamefield.GameConfig;
import ru.nsu.khubanov.task_2_3_1.util.GameLoopThread;
import ru.nsu.khubanov.task_2_3_1.util.SceneManager;
import ru.nsu.khubanov.task_2_3_1.model.Cell;
import ru.nsu.khubanov.task_2_3_1.model.Direction;
import ru.nsu.khubanov.task_2_3_1.model.gamefield.GameField;
import ru.nsu.khubanov.task_2_3_1.model.Snake;
import ru.nsu.khubanov.task_2_3_1.view.GameRenderer;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;



public class GameController {
    @FXML
    private Pane gamePane;

    private GameField gameField;
    private GameLoopThread gameThread;
    private GameRenderer renderer;

    private Set<Cell> previousSnakeCells = new HashSet<>();
    private Set<Cell> previousFoodCells = new HashSet<>();
    private boolean gameRunning = false;

    private final int CELL_SIZE = 20;
    private final int FIELD_WIDTH = 20;
    private final int FIELD_HEIGHT = 20;
    private final int FOOD_COUNT = 5;
    private final int WIN_LENGTH = 15;

    public void initialize() {
        renderer = new GameRenderer(gamePane, CELL_SIZE);

        GameConfig config = new GameConfig(FIELD_WIDTH, FIELD_HEIGHT, FOOD_COUNT, WIN_LENGTH);
        gameField = new GameField(config);

        gamePane.sceneProperty().addListener((obs, oldScene, newScene) -> {
            newScene.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case UP -> gameField.getPlayerSnake().queueDirection(Direction.UP);
                    case DOWN -> gameField.getPlayerSnake().queueDirection(Direction.DOWN);
                    case LEFT -> gameField.getPlayerSnake().queueDirection(Direction.LEFT);
                    case RIGHT -> gameField.getPlayerSnake().queueDirection(Direction.RIGHT);
                }
            });

        });

        startGame();
    }

    public void startGame() {
        if (gameRunning) return;
        gameRunning = true;

        GameConfig config = new GameConfig(FIELD_WIDTH, FIELD_HEIGHT, FOOD_COUNT, WIN_LENGTH);
        gameField = new GameField(config);
        drawGame();

        gameThread = new GameLoopThread(this);
        gameThread.setDaemon(true);   // ← поток-демон!
        gameThread.start();
    }

    public GameField getGameField() {
        return gameField;
    }

    public void checkGameEnd() {
        if (gameField.isGameOver()) {
            gameThread.stopLoop();
            boolean botWon = gameField.getPlayerSnake().isBotWin();
            SceneManager.switchScene("/gameover.fxml", gameField.getPlayerSnake().getBody().size(), false, botWon);
        } else if (gameField.isVictory()) {
            gameThread.stopLoop();
            SceneManager.switchScene("/victory.fxml", gameField.getPlayerSnake().getBody().size(), true, false);
        }
    }




    public void drawGame() {
        renderer.render(gameField);
    }

}
