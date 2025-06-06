package ru.nsu.khubanov.task_2_3_1.logic;

import ru.nsu.khubanov.task_2_3_1.model.*;
import ru.nsu.khubanov.task_2_3_1.model.food.Food;

import java.util.*;

public class VerySmartStrategy implements SnakeStrategy {

    @Override
    public Direction decideDirection(Snake me, List<Snake> others, List<Food> foods, int width, int height) {
        Set<Cell> occupied = new HashSet<>();
        for (Snake s : others) {
            occupied.addAll(s.getBody());
        }
        occupied.addAll(me.getBody().subList(1, me.getBody().size())); // тело змеи, кроме головы

        Direction best = null;
        int bestScore = Integer.MIN_VALUE;

        for (Direction dir : Direction.values()) {
            Cell next = simulateMove(me.getHead(), dir, width, height);
            if (occupied.contains(next)) continue; // опасно

            int score = 0;

            // Ближайшая еда
            Food closest = findClosestFood(next, foods);
            if (closest != null) {
                int dist = manhattan(next, closest.getPosition());
                score -= dist * 2; // ближе — лучше
            }

            // Избегать других голов
            for (Snake s : others) {
                Cell head = s.getHead();
                if (manhattan(next, head) <= 1) {
                    score -= 50;
                }
            }

            // Бонус за безопасное пространство
            int space = countFreeCells(next, occupied, width, height, 10);
            score += space;

            if (score > bestScore) {
                bestScore = score;
                best = dir;
            }
        }

        return best != null ? best : me.getDirection(); // fallback — текущее направление
    }

    private Cell simulateMove(Cell from, Direction dir, int width, int height) {
        int x = (from.getX() + dir.getDx() + width) % width;
        int y = (from.getY() + dir.getDy() + height) % height;
        return new Cell(x, y);
    }

    private Food findClosestFood(Cell from, List<Food> foods) {
        return foods.stream()
                .min(Comparator.comparingInt(f -> manhattan(from, f.getPosition())))
                .orElse(null);
    }

    private int manhattan(Cell a, Cell b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    private int countFreeCells(Cell start, Set<Cell> occupied, int width, int height, int limit) {
        Set<Cell> visited = new HashSet<>();
        Queue<Cell> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        int count = 0;

        while (!queue.isEmpty() && count < limit) {
            Cell curr = queue.poll();
            count++;

            for (Direction dir : Direction.values()) {
                Cell next = simulateMove(curr, dir, width, height);
                if (!visited.contains(next) && !occupied.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }

        return count;
    }
}
