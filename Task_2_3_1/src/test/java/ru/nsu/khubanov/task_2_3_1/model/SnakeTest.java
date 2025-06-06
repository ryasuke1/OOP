package ru.nsu.khubanov.task_2_3_1.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnakeTest {

    @Test
    void testInitialLength() {
        Snake snake = new Snake(5, 5,"паша");
        assertEquals(1, snake.getBody().size());
        assertEquals(new Cell(5, 5), snake.getHead());
    }

    @Test
    void testMoveWithoutGrowth() {
        Snake snake = new Snake(0, 0 ,"паша");
        snake.setDirection(Direction.RIGHT);
        snake.move(false, 10, 10);
        assertEquals(new Cell(1, 0), snake.getHead());
        assertEquals(1, snake.getBody().size());
    }

    @Test
    void testMoveWithGrowth() {
        Snake snake = new Snake(0, 0,"паша");
        snake.setDirection(Direction.RIGHT);
        snake.move(true, 10, 10);
        assertEquals(2, snake.getBody().size());
        assertEquals(new Cell(1, 0), snake.getHead());
    }

    @Test
    void testWrapAround() {
        Snake snake = new Snake(9, 0,"паша");
        snake.setDirection(Direction.RIGHT);
        snake.move(false, 10, 10);
        assertEquals(new Cell(0, 0), snake.getHead());
    }

    @Test
    void testShrink() {
        Snake snake = new Snake(0, 0,"паша");
        snake.setDirection(Direction.RIGHT);
        snake.move(true, 10, 10); // length = 2
        snake.setShrink(1);
        snake.move(false, 10, 10);
        assertEquals(1, snake.getBody().size());
    }
}
