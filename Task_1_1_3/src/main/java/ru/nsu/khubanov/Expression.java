package ru.nsu.khubanov;


public abstract class Expression {
    public abstract void print();

    public abstract Expression derivative();

    public abstract int eval(String context);
}
