package ru.nsu.khubanov.task_2_3_1.model.gamefield;

import ru.nsu.khubanov.task_2_3_1.model.*;

import java.util.*;

public class CollisionChecker {

    public static List<Snake> check(GameField field, Map<Snake, Cell> nextHeads) {
        List<Snake> toRemove = new ArrayList<>();
        List<Snake> snakes = field.getSnakes();

        for (Snake snake : snakes) {
            Cell nextHead = nextHeads.get(snake);

            List<Cell> bodyWithoutHead = new ArrayList<>(snake.getBody());
            if (bodyWithoutHead.size() > 1) bodyWithoutHead.removeLast();

            if (bodyWithoutHead.contains(nextHead)) {
                System.out.println("[Collision] " + snake.getName() + " врезался сам в себя в " + nextHead);
                toRemove.add(snake);
                continue;
            }

            for (Snake other : snakes) {
                if (other != snake && other.getBody().contains(nextHead)) {
                    System.out.println("[Collision] " + snake.getName() + " столкнулся с телом " + other.getName() + " в " + nextHead);
                    toRemove.add(snake);
                    break;
                }
            }

            for (Map.Entry<Snake, Cell> entry : nextHeads.entrySet()) {
                Snake other = entry.getKey();
                if (other != snake && entry.getValue().equals(nextHead)) {
                    System.out.println("[Collision] " + snake.getName() + " и " + other.getName() + " идут в одну клетку " + nextHead);
                    toRemove.add(snake);
                    break;
                }
            }
        }

        return toRemove;
    }
}
