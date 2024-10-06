package ru.nsu.khubanov;

public class Mul extends Expression {
    Expression left;
    Expression right;

    public Mul(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void print() {
        left.print();
        System.out.println("*");
        right.print();
    }

    @Override
    public Expression derivative() {
        return new Add(new Mul(left.derivative(), right), new Mul(right.derivative(), left));
    }

    @Override
    public int eval(String context) {
        return left.eval(context) * right.eval(context);
    }
}
