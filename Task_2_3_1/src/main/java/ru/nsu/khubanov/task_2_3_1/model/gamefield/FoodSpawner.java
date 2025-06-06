package ru.nsu.khubanov.task_2_3_1.model.gamefield;

import ru.nsu.khubanov.task_2_3_1.model.*;
import ru.nsu.khubanov.task_2_3_1.model.food.*;

import java.util.*;

public class FoodSpawner {
    private final int width;
    private final int height;
    private final List<Snake> snakes;
    private final List<Food> foods;

    public FoodSpawner(int width, int height, List<Snake> snakes, List<Food> foods) {
        this.width = width;
        this.height = height;
        this.snakes = snakes;
        this.foods = foods;
    }

    public void generateFoods(int totalFoods) {
        while (foods.size() < totalFoods) {
            generateFood();
        }
    }

    public void generateFood() {
        List<Cell> freeCells = new ArrayList<>();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Cell c = new Cell(x, y);
                boolean occupied = foods.stream().anyMatch(f -> f.getPosition().equals(c)) ||
                        snakes.stream().anyMatch(s -> s.getBody().contains(c));
                if (!occupied) {
                    freeCells.add(c);
                }
            }
        }

        if (freeCells.isEmpty()) return;

        Random r = new Random();
        Cell pos = freeCells.get(r.nextInt(freeCells.size()));
        int type = r.nextInt(3);

        Food food;
        if (type == 1 && foods.stream().noneMatch(f -> f instanceof Bomb)) {
            food = new Bomb(pos);
        } else if (type == 2) {
            food = new SpeedBoost(pos);
        } else {
            food = new Apple(pos);
        }

        foods.add(food);
    }
}
