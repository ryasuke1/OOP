package ru.nsu.khubanov.task_2_3_1.model.gamefield;

import ru.nsu.khubanov.task_2_3_1.model.*;

import java.util.*;

public class CollisionChecker {

    public static List<Snake> check(GameField field, Map<Snake, Cell> nextHeads) {

        Set<Snake> dead = new HashSet<>();

        selfHits(dead, nextHeads);               // 1) самоврезания
        bodyHits(dead, nextHeads);               // 2) в чужое тело
        headOnHits(dead, nextHeads);             // 3) лоб-в-лоб

        return new ArrayList<>(dead);
    }


    private static void selfHits(Set<Snake> dead, Map<Snake, Cell> heads) {
        heads.forEach((snake, nextHead) -> {
            List<Cell> body = new ArrayList<>(snake.getBody());
            if (body.size() > 1) body.removeLast();   // исключаем текущую голову
            if (body.contains(nextHead)) {
                logAndCollect(dead, snake,
                        "врезался сам в себя", nextHead);
            }
        });
    }

    private static void bodyHits(Set<Snake> dead, Map<Snake, Cell> heads) {
        for (Map.Entry<Snake, Cell> e : heads.entrySet()) {
            Snake snake = e.getKey();         Cell head = e.getValue();
            for (Snake other : heads.keySet()) {
                if (snake != other && other.getBody().contains(head)) {
                    logAndCollect(dead, snake,
                            "столкнулся с телом " + other.getName(), head);
                }
            }
        }
    }

    private static void headOnHits(Set<Snake> dead, Map<Snake, Cell> heads) {
        for (Map.Entry<Snake, Cell> e1 : heads.entrySet()) {
            for (Map.Entry<Snake, Cell> e2 : heads.entrySet()) {
                if (e1.getKey() == e2.getKey()) continue;
                if (e1.getValue().equals(e2.getValue())) {          // одна клетка
                    logAndCollect(dead, e1.getKey(),
                            "лоб в лоб с " + e2.getKey().getName(), e1.getValue());
                }
            }
        }
    }

    private static void logAndCollect(Set<Snake> dead, Snake snake,
                                      String cause, Cell pos) {
        if (dead.add(snake)) {   // чтобы не логировать дубли
            System.out.printf("[Collision] %s %s в %s%n",
                    snake.getName(), cause, pos);
        }
    }
}
