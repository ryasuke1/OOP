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
}
