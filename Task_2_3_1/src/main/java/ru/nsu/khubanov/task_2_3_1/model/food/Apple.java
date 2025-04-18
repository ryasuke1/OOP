package ru.nsu.khubanov.task_2_3_1.model.food;

import ru.nsu.khubanov.task_2_3_1.model.Cell;
import ru.nsu.khubanov.task_2_3_1.model.gamefield.GameField;
import ru.nsu.khubanov.task_2_3_1.model.Snake;

public class Apple extends Food {
    public Apple(Cell position) {
        super(position);
    }


    @Override
    public void applyEffect(Snake snake, GameField field) {
        // ничего не делаем — змея вырастает по логике move(grow = true)
    }
}