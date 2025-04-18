package ru.nsu.khubanov.task_2_3_1.model.food;

import ru.nsu.khubanov.task_2_3_1.model.Cell;
import ru.nsu.khubanov.task_2_3_1.model.gamefield.GameField;
import ru.nsu.khubanov.task_2_3_1.model.Snake;

public class SpeedBoost extends Food {
    public SpeedBoost(Cell position) {
        super(position);
    }

    @Override
    public void applyEffect(Snake snake, GameField field) {
        field.increaseSpeed(20); // каждая еда ускоряет на 20 мс
    }

}