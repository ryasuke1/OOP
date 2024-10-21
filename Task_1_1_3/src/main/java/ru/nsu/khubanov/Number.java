package ru.nsu.khubanov;

public class Number extends Expression {
    int num;

    public Number(int num) {
        this.num = num;
    }

    @Override
    public void print() {
        System.out.println(Integer.toString(num));
    }

    @Override
    public Expression derivative() {
        return new Number(0);
    }

    @Override
    public int eval(String context) {
        return num;
    }
}
