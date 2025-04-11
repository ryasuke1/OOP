package ru.nsu.khubanov.task_2_3_1.model;

import org.junit.jupiter.api.Test;
import ru.nsu.khubanov.task_2_3_1.model.food.Apple;

import static org.junit.jupiter.api.Assertions.*;

class GameFieldTest {

    @Test
    void testFoodGeneration() {
        GameField field = new GameField(10, 10, 3, 10);
        assertEquals(3, field.getFoods().size());
    }

    @Test
    void testVictoryCondition() {
        GameField field = new GameField(10, 10, 1, 3);
        Snake snake = field.getPlayerSnake();
        snake.move(true, 10, 10); // длина 2
        snake.move(true, 10, 10); // длина 3
        field.update(); // проверка победы
        assertTrue(field.isVictory());
    }

    @Test
    void testCollisionWithSelf() {
        GameField field = new GameField(10, 10, 0, 10);
        Snake snake = field.getPlayerSnake();

        // Строим U-образную змею с помощью еды
        Cell c1 = snake.nextHead(10, 10);
        field.getFoods().add(new Apple(c1)); field.update(); // вправо
        snake.setDirection(Direction.RIGHT);

        Cell c2 = snake.nextHead(10, 10);
        field.getFoods().add(new Apple(c2)); field.update(); // вправо
        snake.setDirection(Direction.DOWN);

        Cell c3 = snake.nextHead(10, 10);
        field.getFoods().add(new Apple(c3)); field.update(); // вниз
        snake.setDirection(Direction.LEFT);

        Cell c4 = snake.nextHead(10, 10);
        field.getFoods().add(new Apple(c4)); field.update(); // влево
        snake.setDirection(Direction.UP);

        // Теперь она должна врезаться
        field.update();

        assertTrue(field.isGameOver());
    }


    @Test
    void testFoodEffectApple() {
        GameField field = new GameField(10, 10, 0, 10);
        Snake snake = field.getPlayerSnake();
        Cell foodPos = snake.nextHead(10, 10);
        field.getFoods().add(new Apple(foodPos));
        field.update();
        assertEquals(2, snake.getBody().size());
    }
}
