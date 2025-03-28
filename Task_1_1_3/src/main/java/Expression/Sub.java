package Expression;
import ru.nsu.khubanov.*;

public class Sub extends Expression {
    private Expression left;
    private Expression right;

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

    @Override
    public Expression simplify() {
        Expression simplifiedLeft = left.simplify();
        Expression simplifiedRight = right.simplify();

        int leftValue = simplifiedLeft.eval(""); // Используем eval для получения значения
        int rightValue = simplifiedRight.eval(""); // Используем eval для получения значения

        if (simplifiedLeft instanceof MyNumber && simplifiedRight instanceof MyNumber) {
            return new MyNumber(leftValue - rightValue);
        }

        if(simplifiedLeft.equals(simplifiedRight)){
            return new MyNumber(0);
        }

        return new Sub(simplifiedLeft, simplifiedRight);
    }
}
