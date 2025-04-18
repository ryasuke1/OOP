package ru.nsu.khubanov.task_2_3_1.model;

import org.junit.jupiter.api.Test;
import ru.nsu.khubanov.task_2_3_1.model.food.Apple;
import ru.nsu.khubanov.task_2_3_1.model.gamefield.GameConfig;
import ru.nsu.khubanov.task_2_3_1.model.gamefield.GameField;

import static org.junit.jupiter.api.Assertions.*;

class GameFieldTest {

    @Test
    void testFoodGeneration() {
        GameConfig config = new GameConfig(10, 10, 3, 10);
        GameField field = new GameField(config);
        assertEquals(3, field.getFoods().size());
    }

    @Test
    void testVictoryCondition() {
        GameConfig config = new GameConfig(10, 10, 0, 3);
        GameField field = new GameField(config);
        Snake snake = field.getPlayerSnake();
        snake.move(true, 10, 10); // длина 2
        snake.move(true, 10, 10); // длина 3
        field.update(); // проверка победы
        assertTrue(field.isVictory());
    }

    @Test
    void testCollisionWithSelf() {
        GameConfig config = new GameConfig(10, 10, 0, 10);
        GameField field = new GameField(config);
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
        GameConfig config = new GameConfig(10, 10, 0, 10);
        GameField field = new GameField(config);
        Snake snake = field.getPlayerSnake();
        Cell foodPos = snake.nextHead(10, 10);
        field.getFoods().add(new Apple(foodPos));
        field.update();
        assertEquals(2, snake.getBody().size());
    }
    @Test
    void testDoubleTurnSelfCollisionFix() {
        GameConfig config = new GameConfig(10, 10, 0, 10);
        GameField field = new GameField(config);
        Snake snake = field.getPlayerSnake();

        // Строим змейку длиной 4 клетки вправо
        snake.setDirection(Direction.RIGHT);
        snake.move(true, 10, 10); // (6,5)
        snake.move(true, 10, 10); // (7,5)
        snake.move(true, 10, 10); // (8,5)

        // Игрок быстро нажимает DOWN и затем LEFT (два поворота за тик)
        snake.queueDirection(Direction.DOWN);
        snake.queueDirection(Direction.LEFT);

        // Вызываем update → применяется только один поворот
        field.update();

        // Второй update → применится второй поворот
        field.update();

        // Если змея не умерла — всё работает правильно
        assertFalse(field.isGameOver(), "Змея не должна врезаться, если направления ставятся в очередь корректно");
    }
}
