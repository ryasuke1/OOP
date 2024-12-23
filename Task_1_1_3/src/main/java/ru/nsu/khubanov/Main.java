package ru.nsu.khubanov;
import Expression.*;

public class Main {
    public static void main(String[] args) {
        Expression e = new Add(new MyNumber(3), new Mul(new MyNumber(2), new Variable("x"))); // (3+(2*x))
        int result = e.eval("x = 10; y = 13");
        System.out.println(result);

        String input = "(3 + 2 * x";
        Expression expression = Parser.parse(input);
        System.out.println("Original: " + expression.eval("x = 10; y = 13"));
        System.out.println("Simplified: " + expression.simplify().eval("x = 10; y = 13"));

    }
}



