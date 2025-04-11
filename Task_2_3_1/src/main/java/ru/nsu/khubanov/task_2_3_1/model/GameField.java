package ru.nsu.khubanov.task_2_3_1.model;

import ru.nsu.khubanov.task_2_3_1.logic.RandomStrategy;
import ru.nsu.khubanov.task_2_3_1.model.food.Apple;
import ru.nsu.khubanov.task_2_3_1.model.food.Bomb;
import ru.nsu.khubanov.task_2_3_1.model.food.Food;
import ru.nsu.khubanov.task_2_3_1.model.food.SpeedBoost;

import java.util.*;

public class GameField {
    private final int width;
    private final int height;
    private final List<Snake> snakes = new ArrayList<>();
    private final List<Food> foods = new ArrayList<>();
    private final int totalFoods;
    private final int winLength;

    private boolean gameOver = false;
    private boolean victory = false;

    private final Snake playerSnake;

    public GameField(int width, int height, int totalFoods, int winLength) {
        this.width = width;
        this.height = height;
        this.totalFoods = totalFoods;
        this.winLength = winLength;

        this.playerSnake = new Snake(width / 2, height / 2);
        snakes.add(playerSnake);

        // Добавим простого бота
        snakes.add(new BotSnake(1, 1, new RandomStrategy()));

        generateFoods();
    }

    private int tickDelay = 200; // стартовая задержка

    public void increaseSpeed(int delta) {
        tickDelay = Math.max(80, tickDelay - delta); // ограничим минимумом 50мс
    }

    public int getTickDelay() {
        return tickDelay;
    }

    public void setTemporarySpeed(int newDelay) {
        this.tickDelay = newDelay;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public Snake getPlayerSnake() {
        return playerSnake;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isVictory() {
        return victory;
    }

    public void update() {
        // 1. Боты выбирают направление
        for (Snake snake : snakes) {
            if (snake instanceof BotSnake bot) {
                bot.updateDirection(snakes, foods, width, height);
            }
        }

        // 2. Собираем предполагаемые головы
        Map<Snake, Cell> nextHeads = new HashMap<>();
        for (Snake snake : snakes) {
            Cell next = snake.nextHead(width, height);
            nextHeads.put(snake, next);
        }

        // 3. Проверка столкновений
        List<Snake> toRemove = new ArrayList<>();

        for (Snake snake : snakes) {
            Cell nextHead = nextHeads.get(snake);

            // 3.1 Столкновение с собой (исключаем хвост, если не растёт)
            List<Cell> bodyWithoutHead = new ArrayList<>(snake.getBody());
            bodyWithoutHead.removeLast();
            if (bodyWithoutHead.contains(nextHead)) {
                toRemove.add(snake);
                continue;
            }

            // 3.2 Столкновение с другими змеями
            for (Snake other : snakes) {
                if (other != snake && other.getBody().contains(nextHead)) {
                    toRemove.add(snake);
                    break;
                }
            }

            // 3.3 Лоб в лоб (две змеи хотят пойти в одну клетку)
            for (Map.Entry<Snake, Cell> entry : nextHeads.entrySet()) {
                Snake other = entry.getKey();
                if (other != snake && entry.getValue().equals(nextHead)) {
                    toRemove.add(snake);
                    break;
                }
            }
        }

        // 4. Удаляем проигравших
        snakes.removeAll(toRemove);

        // 5. Двигаем всех, кто остался
        for (Snake snake : snakes) {
            Cell nextHead = nextHeads.get(snake);
            boolean grow = false;

            Iterator<Food> it = foods.iterator();
            while (it.hasNext()) {
                Food food = it.next();
                if (food.getPosition().equals(nextHead)) {
                    grow = true;
                    food.applyEffect(snake, this);
                    it.remove();
                    generateFood();
                    break;
                }
            }

            snake.move(grow, width, height);
        }

        // 6. Проверка победы/поражения игрока
        if (!snakes.contains(playerSnake)) {
            gameOver = true;
        } else if (playerSnake.getBody().size() >= winLength) {
            victory = true;
        }
    }


    private boolean isOutOfBounds(Cell c) {
        return c.getX() < 0 || c.getY() < 0 || c.getX() >= width || c.getY() >= height;
    }

    private void generateFoods() {
        while (foods.size() < totalFoods) {
            generateFood();
        }
    }

    private void generateFood() {
        Random r = new Random();
        while (true) {
            Cell pos = new Cell(r.nextInt(width), r.nextInt(height));

            boolean occupied = foods.stream().anyMatch(f -> f.getPosition().equals(pos)) ||
                    snakes.stream().anyMatch(s -> s.getBody().contains(pos));
            if (occupied) continue;

            // Генерируем тип
            int type = r.nextInt(3);
            Food food;

            if (type == 1) {
                // Если это бомба — проверяем, есть ли уже
                boolean bombExists = foods.stream().anyMatch(f -> f instanceof Bomb);
                if (bombExists) continue; // пропускаем и пробуем ещё раз
                food = new Bomb(pos);
            } else if (type == 2) {
                food = new SpeedBoost(pos);
            } else {
                food = new Apple(pos);
            }

            foods.add(food);
            break;
        }
    }

}
