package ru.nsu.khubanov.task_2_3_1.model;

import org.junit.jupiter.api.Test;
import ru.nsu.khubanov.task_2_3_1.logic.RandomStrategy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BotSnakeTest {

    @Test
    void testBotSnakeInitialDirection() {
        BotSnake bot = new BotSnake(1, 1, new RandomStrategy());
        assertNotNull(bot.getDirection());
    }

    @Test
    void testBotSnakeChoosesDirection() {
        BotSnake bot = new BotSnake(1, 1, new RandomStrategy());
        Direction before = bot.getDirection();
        bot.updateDirection(List.of(bot), List.of(), 10, 10);
        Direction after = bot.getDirection();

        // Может остаться тем же, но направление должно быть допустимым
        assertNotNull(after);
    }

    @Test
    void testBotSnakeMoves() {
        BotSnake bot = new BotSnake(1, 1, new RandomStrategy());
        bot.move(false, 10, 10);
        assertEquals(1, bot.getBody().size()); // длина не меняется
        assertNotEquals(new Cell(1, 1), bot.getHead()); // позиция изменилась
    }
}
