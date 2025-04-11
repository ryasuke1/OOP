package ru.nsu.khubanov.task_2_3_1.model;

import java.util.Objects;

public class Cell {
    private final int x;
    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell c = (Cell) o;
        return x == c.x && y == c.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
