package ru.nsu.khubanov.task_2_3_1.model;

import ru.nsu.khubanov.task_2_3_1.logic.SnakeStrategy;
import ru.nsu.khubanov.task_2_3_1.model.food.Food;

import java.util.List;

public class BotSnake extends Snake {
    private final SnakeStrategy strategy;

    public BotSnake(int x, int y, SnakeStrategy strategy, String name) {
        super(x, y, name); // вызываем Snake с именем
        this.strategy = strategy;
    }

    public void updateDirection(List<Snake> snakes, List<Food> foods, int width, int height) {
        Direction newDir = strategy.decideDirection(this, snakes, foods, width, height);
        this.queueDirection(newDir);
    }
}

