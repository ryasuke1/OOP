package ru.nsu.khubanov.task_2_3_1.model.gamefield;

import ru.nsu.khubanov.task_2_3_1.model.*;
import ru.nsu.khubanov.task_2_3_1.model.food.Food;

import java.util.*;

public class GameUpdater {

    public static void update(GameField field) {
        List<Snake> snakes = field.getSnakes();
        List<Food> foods = field.getFoods();
        int width = field.getConfig().width;
        int height = field.getConfig().height;
        int winLength = field.getConfig().winLength;

        for (Snake snake : snakes) {
            if (snake instanceof BotSnake bot) {
                bot.updateDirection(snakes, foods, width, height);
            }
        }

        for (Snake snake : snakes) {
            snake.applyNextDirection();
        }

        Map<Snake, Cell> nextHeads = new HashMap<>();
        for (Snake snake : snakes) {
            nextHeads.put(snake, snake.nextHead(width, height));
        }

        List<Snake> toRemove = CollisionChecker.check(field, nextHeads);

        snakes.removeAll(toRemove);
        if (snakes.isEmpty()) {
            System.out.println("[GameField] Все змеи мертвы. Игра окончена.");
            field.setGameOver(true);
            return;
        }

        for (Snake snake : snakes) {
            Cell nextHead = nextHeads.get(snake);
            boolean grow = false;

            Iterator<Food> it = foods.iterator();
            while (it.hasNext()) {
                Food food = it.next();
                if (food.getPosition().equals(nextHead)) {
                    grow = true;
                    food.applyEffect(snake, field);
                    it.remove();
                    field.getFoodSpawner().generateFood();
                    break;
                }
            }

            snake.move(grow, width, height);
        }

        Snake player = field.getPlayerSnake();
        if (!snakes.contains(player)) {
            field.setGameOver(true);
            return;
        }

        for (Snake snake : snakes) {
            if (snake != player && snake.getBody().size() >= winLength) {
                field.setGameOver(true);
                player.setBotWin(true);
                return;
            }
        }

        if (player.getBody().size() >= winLength) {
            field.setVictory(true);
        }
    }
}
