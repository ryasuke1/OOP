package ru.nsu.khubanov.task_2_3_1.model.gamefield;

import ru.nsu.khubanov.task_2_3_1.logic.*;
import ru.nsu.khubanov.task_2_3_1.model.*;
import ru.nsu.khubanov.task_2_3_1.model.food.Food;

import java.util.*;

public class GameField {
    private final GameConfig config;
    private final List<Snake> snakes = new ArrayList<>();
    private final List<Food> foods = new ArrayList<>();
    private boolean gameOver = false;
    private boolean victory = false;
    private final Snake playerSnake;
    private int tickDelay = 200;

    private final FoodSpawner foodSpawner;

    public GameField(GameConfig config) {
        this.config = config;
        this.playerSnake = new Snake(config.width / 2, config.height / 2, "Игрок");
        snakes.add(playerSnake);
        snakes.add(new BotSnake(1, 1, new RandomStrategy(), "Тупой бот"));
        snakes.add(new BotSnake(1, 1, new VerySmartStrategy(), "Умный бот"));

        this.foodSpawner = new FoodSpawner(config.width, config.height, snakes, foods);
        foodSpawner.generateFoods(config.foodCount);
    }

    public void update() {
        GameUpdater.update(this);
    }

    public void increaseSpeed(int delta) {
        tickDelay = Math.max(80, tickDelay - delta);
    }

    public void setTemporarySpeed(int newDelay) {
        this.tickDelay = newDelay;
    }

    public int getTickDelay() {
        return tickDelay;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public Snake getPlayerSnake() {
        return playerSnake;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isVictory() {
        return victory;
    }

    public GameConfig getConfig() {
        return config;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

    public FoodSpawner getFoodSpawner() {
        return foodSpawner;
    }
}
