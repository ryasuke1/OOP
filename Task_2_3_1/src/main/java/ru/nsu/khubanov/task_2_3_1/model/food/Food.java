package ru.nsu.khubanov.task_2_3_1.model.food;

import ru.nsu.khubanov.task_2_3_1.model.Cell;
import ru.nsu.khubanov.task_2_3_1.model.gamefield.GameField;
import ru.nsu.khubanov.task_2_3_1.model.Snake;

public abstract class Food {
    private final Cell position;

    public Food(Cell position) {
        this.position = position;
    }

    public Cell getPosition() {
        return position;
    }

    public abstract void applyEffect(Snake snake, GameField field);
}
