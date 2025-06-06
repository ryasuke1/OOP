package ru.nsu.khubanov.task_2_3_1.model.gamefield;

import ru.nsu.khubanov.task_2_3_1.model.*;
import ru.nsu.khubanov.task_2_3_1.model.food.Food;

import java.util.*;

public class GameUpdater {

    public static void update(GameField field) {
        chooseBotDirections(field);
        applyQueuedDirections(field);
        Map<Snake, Cell> nextHeads = predictNextHeads(field);

        List<Snake> dead = CollisionChecker.check(field, nextHeads);
        removeDeadSnakes(field, dead);
        if (abortIfEveryoneDead(field)) return;

        processFoodAndMove(field, nextHeads);
        checkWinLose(field);
    }

    private static void chooseBotDirections(GameField field) {
        int w = field.getConfig().width, h = field.getConfig().height;
        List<Snake> ss = field.getSnakes(); List<Food> fd = field.getFoods();
        for (Snake s : ss)
            if (s instanceof BotSnake bot)
                bot.updateDirection(ss, fd, w, h);
    }

    private static void applyQueuedDirections(GameField field) {
        for (Snake s : field.getSnakes()) s.applyNextDirection();
    }

    private static Map<Snake, Cell> predictNextHeads(GameField field) {
        int w = field.getConfig().width, h = field.getConfig().height;
        Map<Snake, Cell> map = new HashMap<>();
        for (Snake s : field.getSnakes())
            map.put(s, s.nextHead(w, h));
        return map;
    }

    private static void removeDeadSnakes(GameField f, List<Snake> dead) {
        f.getSnakes().removeAll(dead);
    }

    private static boolean abortIfEveryoneDead(GameField f) {
        if (f.getSnakes().isEmpty()) {
            System.out.println("[GameField] Все змеи мертвы. Игра окончена.");
            f.setGameOver(true);
            return true;
        }
        return false;
    }

    private static void processFoodAndMove(GameField f, Map<Snake,Cell> heads) {
        int w = f.getConfig().width, h = f.getConfig().height;
        List<Food> foods = f.getFoods();
        for (Snake s : f.getSnakes()) {
            Cell head = heads.get(s);
            boolean grow = false;

            for (Iterator<Food> it = foods.iterator(); it.hasNext();) {
                Food food = it.next();
                if (food.getPosition().equals(head)) {
                    grow = true;
                    food.applyEffect(s, f);
                    it.remove();
                    f.getFoodSpawner().generateFood();
                    break;
                }
            }
            s.move(grow, w, h);
        }
    }

    private static void checkWinLose(GameField f) {
        Snake player = f.getPlayerSnake();
        int winLen = f.getConfig().winLength;

        if (!f.getSnakes().contains(player)) {            // игрок погиб
            f.setGameOver(true);
            return;
        }
        for (Snake s : f.getSnakes())                     // победил бот?
            if (s != player && s.getBody().size() >= winLen) {
                f.setGameOver(true);
                player.setBotWin(true);
                return;
            }
        if (player.getBody().size() >= winLen)            // победа игрока
            f.setVictory(true);
    }
}
