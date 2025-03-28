package Expression;
import ru.nsu.khubanov.*;

public class Div extends Expression {
    private Expression left;
    private Expression right;

    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void print() {
        left.print();
        System.out.println("/");
        right.print();
    }

    @Override
    public Expression derivative() {
        return new Div(new Sub(new Mul(left.derivative(), right), new Mul(right.derivative(), left)), new Mul(right, right));
    }

    @Override
    public int eval(String context) {
        int rightValue = right.eval(context);
        if (rightValue == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return left.eval(context) / rightValue;
    }

    @Override
    public Expression simplify() {
        Expression simplifiedLeft = left.simplify();
        Expression simplifiedRight = right.simplify();
        int leftValue = simplifiedLeft.eval(""); // Используем eval для получения значения
        int rightValue = simplifiedRight.eval(""); // Используем eval для получения значения

        if (simplifiedLeft instanceof MyNumber && simplifiedRight instanceof MyNumber) {
            return new MyNumber(leftValue/rightValue);
        }

        return new Add(simplifiedLeft, simplifiedRight);
    }
}
