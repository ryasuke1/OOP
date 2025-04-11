package ru.nsu.khubanov.task_2_3_1.model;

import ru.nsu.khubanov.task_2_3_1.logic.SnakeStrategy;
import ru.nsu.khubanov.task_2_3_1.model.food.Food;

import java.util.List;

public class BotSnake extends Snake {
    private final SnakeStrategy strategy;

    public BotSnake(int startX, int startY, SnakeStrategy strategy) {
        super(startX, startY);
        this.strategy = strategy;
    }

    public void updateDirection(List<Snake> allSnakes, List<Food> foods, int width, int height) {
        Direction dir = strategy.decideDirection(this, allSnakes, foods, width, height);
        setDirection(dir);
    }
}
