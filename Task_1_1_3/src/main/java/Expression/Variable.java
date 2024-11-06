package Expression;
import ru.nsu.khubanov.Expression;


public class Variable extends Expression {
    private String varl;

    public Variable(String varl) {
        this.varl = varl;
    }

    @Override
    public void print() {
        System.out.println(varl);
    }

    @Override
    public Expression derivative() {
        return new MyNumber(1);
    }

    @Override
    public int eval(String context) {
        String[] vars = context.split("; ");
        for (String var : vars) {
            String[] parts = var.split(" = ");
            if (parts[0].trim().equals(varl)) {
                return Integer.parseInt(parts[1].trim());
            }
        }
        return 0;
    }
}
