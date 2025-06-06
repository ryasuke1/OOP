package ru.nsu.khubanov.task_2_3_1.model;

import org.junit.jupiter.api.Test;
import ru.nsu.khubanov.task_2_3_1.model.food.Bomb;
import ru.nsu.khubanov.task_2_3_1.model.food.SpeedBoost;
import ru.nsu.khubanov.task_2_3_1.model.gamefield.GameConfig;
import ru.nsu.khubanov.task_2_3_1.model.gamefield.GameField;

import static org.junit.jupiter.api.Assertions.*;

class FoodEffectsTest {

    @Test
    void testSpeedBoostIncreasesSpeed() {
        GameConfig config = new GameConfig(10, 10, 0, 10);
        GameField field = new GameField(config);
        int oldDelay = field.getTickDelay();

        Snake snake = field.getPlayerSnake();
        Cell pos = snake.nextHead(10, 10);
        field.getFoods().add(new SpeedBoost(pos));
        field.update();

        int newDelay = field.getTickDelay();
        assertTrue(newDelay < oldDelay); // скорость увеличилась
    }

    @Test
    void testBombReducesSnakeSize() {
        GameConfig config = new GameConfig(10, 10, 0, 10);
        GameField field = new GameField(config);

        Snake snake = field.getPlayerSnake();
        snake.move(true, 10, 10); // длина = 2
        snake.move(true, 10, 10); // длина = 3

        int before = snake.getBody().size();
        Cell pos = snake.nextHead(10, 10);
        field.getFoods().add(new Bomb(pos));
        field.update();

        assertTrue(snake.getBody().size() < before); // длина уменьшилась
    }
}
