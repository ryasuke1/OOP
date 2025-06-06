package ru.nsu.khubanov.task_2_3_1.logic;

import ru.nsu.khubanov.task_2_3_1.model.Cell;
import ru.nsu.khubanov.task_2_3_1.model.Direction;
import ru.nsu.khubanov.task_2_3_1.model.Snake;
import ru.nsu.khubanov.task_2_3_1.model.food.Food;

import java.util.*;

public class RandomStrategy implements SnakeStrategy {
    private final Random rand = new Random();

    @Override
    public Direction decideDirection(Snake me, List<Snake> others, List<Food> foods, int width, int height) {
        List<Direction> safeDirs = new ArrayList<>();

        for (Direction dir : Direction.values()) {
            Cell head = me.getHead();
            Cell next = new Cell(head.getX() + dir.getDx(), head.getY() + dir.getDy());

            // Не выходить за пределы поля
            if (next.getX() < 0 || next.getY() < 0 || next.getX() >= width || next.getY() >= height)
                continue;

            // Не врезаться в себя
            if (me.getBody().contains(next))
                continue;

            // Не врезаться в других змей
            boolean collidesWithOthers = false;
            for (Snake other : others) {
                if (other != me && other.getBody().contains(next)) {
                    collidesWithOthers = true;
                    break;
                }
            }

            if (!collidesWithOthers) {
                safeDirs.add(dir);
            }
        }

        // Если безопасные направления есть — выбрать одно из них
        if (!safeDirs.isEmpty()) {
            return safeDirs.get(rand.nextInt(safeDirs.size()));
        }

        // Если совсем нет безопасных направлений — выбираем случайное (на авось)
        return Direction.values()[rand.nextInt(Direction.values().length)];
    }
}
