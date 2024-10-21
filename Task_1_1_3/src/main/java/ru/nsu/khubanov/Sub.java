package ru.nsu.khubanov;

public class Sub extends Expression {
    Expression left;
    Expression right;

    public Sub(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void print() {
        left.print();
        System.out.println("-");
        right.print();
    }

    @Override
    public Expression derivative() {
        return new Sub(left.derivative(), right.derivative());
    }

    @Override
    public int eval(String context) {
        return left.eval(context) - right.eval(context);
    }
}
