package ru.nsu.khubanov;

public class Variable extends Expression {
    String varl;

    public Variable(String varl) {
        this.varl = varl;
    }

    @Override
    public void print() {
        System.out.println(varl);
    }

    @Override
    public Expression derivative() {
        return new Number(1);
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
