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
}
