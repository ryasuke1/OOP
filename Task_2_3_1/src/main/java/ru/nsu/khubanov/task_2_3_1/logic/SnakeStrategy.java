package ru.nsu.khubanov.task_2_3_1.logic;

import ru.nsu.khubanov.task_2_3_1.model.Cell;
import ru.nsu.khubanov.task_2_3_1.model.Direction;
import ru.nsu.khubanov.task_2_3_1.model.Snake;
import ru.nsu.khubanov.task_2_3_1.model.food.Food;

import java.util.List;

public interface SnakeStrategy {
    Direction decideDirection(Snake me, List<Snake> others, List<Food> foods, int width, int height);
}
