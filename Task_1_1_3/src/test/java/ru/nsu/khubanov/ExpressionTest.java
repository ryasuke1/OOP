package ru.nsu.khubanov;
import Expression.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.spec.ECField;

public class ExpressionTest {

    @Test
    public void testNumber() {
        MyNumber number = new MyNumber(5);
        Assertions.assertEquals(5, number.eval(""));
        Assertions.assertEquals(0, number.derivative().eval(""));
    }

    @Test
    public void testVariable() {
        Variable variable = new Variable("x");
        Assertions.assertEquals(10, variable.eval("x = 10"));
        Assertions.assertEquals(1, variable.derivative().eval(""));
    }

    @Test
    public void testAdd() {
        Expression addExpr = new Add(new MyNumber(3), new MyNumber(4));
        Assertions.assertEquals(7, addExpr.eval(""));
    }

    @Test
    public void testMul() {
        Expression mulExpr = new Mul(new MyNumber(2), new Variable("x"));
        Assertions.assertEquals(20, mulExpr.eval("x = 10"));
    }

    @Test
    public void testDiv() {
        Expression divExpr = new Div(new MyNumber(20), new MyNumber(4));
        Assertions.assertEquals(5, divExpr.eval(""));
    }
    @Test
    public void testDivByZero() {
        Expression left = new MyNumber(10);
        Expression right = new MyNumber(0);
        Div div = new Div(left, right);

        try {
            div.eval("");
            Assertions.fail("Expected ArithmeticException was not thrown");
        } catch (ArithmeticException e) {
            Assertions.assertEquals("Division by zero", e.getMessage());
        }
    }

    @Test
    public void testDerivative() {
        // Тестируем производные для сложного выражения
        Expression e = new Add(new MyNumber(3), new Mul(new MyNumber(2), new Variable("x"))); // 3 + 2*x
        Expression derivative = e.derivative(); // 0 + 2
        Assertions.assertEquals(2, derivative.eval(""));
    }

    @Test
    public void testComplexExpression() {
        //(3 + 2*x) / (4 - x)
        Expression complexExpr = new Div(
                new Add(new MyNumber(3), new Mul(new MyNumber(2), new Variable("x"))),
                new Sub(new MyNumber(4), new Variable("x"))
        );
        Assertions.assertEquals(1, complexExpr.eval("x = 1"));
    }
    @Test
    public void ThreeVariableEval(){
        Expression expr =  new Add(
                new Mul(new Variable("x"), new Variable("y")),
                new Sub(new Variable("z"), new MyNumber(5)));//x * y + (z-5)
        Assertions.assertEquals(15,expr.eval("x = 2; y = 5; z = 10"));
    }
    @Test
    public void testHighDerivative(){
        Expression expr = new Mul(new MyNumber(3), new Mul(new Variable("x"), new Variable("x"))); // 3 * x^2
        Expression firstDerivative = expr.derivative(); // 6 * x
        Expression secondDerivative = firstDerivative.derivative(); // 6
        Assertions.assertEquals(6,secondDerivative.eval(""));
    }
}