package ru.nsu.khubanov.task_2_3_1.model.food;


import ru.nsu.khubanov.task_2_3_1.model.Cell;
import ru.nsu.khubanov.task_2_3_1.model.gamefield.GameField;
import ru.nsu.khubanov.task_2_3_1.model.Snake;

public class Bomb extends Food {
    public Bomb(Cell position) {
        super(position);
    }

    @Override
    public void applyEffect(Snake snake, GameField field) {
        // Отметим, что следующий ход змейка НЕ растёт, а наоборот — теряет хвост дважды
        snake.setShrink(1); // пользовательский флаг
    }
}

