package Expression;
import ru.nsu.khubanov.*;

public class Mul extends Expression {
    private Expression left;
    private Expression right;

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

    @Override
    public Expression simplify() {
        Expression simplifiedLeft = left.simplify();
        Expression simplifiedRight = right.simplify();

        int leftValue = simplifiedLeft.eval(""); // Используем eval для получения значения
        int rightValue = simplifiedRight.eval(""); // Используем eval для получения значения

        if (simplifiedLeft instanceof MyNumber && leftValue==0 || simplifiedRight instanceof MyNumber && rightValue==0) {
            return new MyNumber(0);
        }

        if (simplifiedLeft instanceof MyNumber && leftValue==1) {
            return right;
        }

        if (simplifiedRight instanceof MyNumber && rightValue==1) {
            return left;
        }

        return new Mul(simplifiedLeft, simplifiedRight);
    }

}
