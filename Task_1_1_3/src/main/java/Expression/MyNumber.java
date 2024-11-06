package Expression;
import ru.nsu.khubanov.Expression;

public class MyNumber extends Expression {
    private int num;

    public MyNumber(int num) {
        this.num = num;
    }

    @Override
    public void print() {
        System.out.println(Integer.toString(num));
    }

    @Override
    public Expression derivative() {
        return new MyNumber(0);
    }

    @Override
    public int eval(String context) {
        return num;
    }
}
