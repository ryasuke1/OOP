package Expression;
import ru.nsu.khubanov.*;

public class Add extends Expression {
    private Expression left;
    private Expression right;

    public Add(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void print() {
        left.print();
        System.out.println("+");
        right.print();
    }

    @Override
    public Expression derivative() {
        return new Add(left.derivative(), right.derivative());
    }

    @Override
    public int eval(String context) {
        return left.eval(context) + right.eval(context);
    }

    @Override
    public Expression simplify() {
        Expression simplifiedLeft = left.simplify();
        Expression simplifiedRight = right.simplify();

        if (simplifiedLeft instanceof MyNumber && simplifiedRight instanceof MyNumber) {
            int leftValue = simplifiedLeft.eval(""); // Используем eval для получения значения
            int rightValue = simplifiedRight.eval(""); // Используем eval для получения значения
            return new MyNumber(leftValue + rightValue);
        }

        return new Add(simplifiedLeft, simplifiedRight);
    }

}


