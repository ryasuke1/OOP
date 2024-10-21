package ru.nsu.khubanov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExpressionTest {

    @Test
    public void testNumber() {
        Number number = new Number(5);
        assertEquals(5, number.eval(""));
        assertEquals(0, number.derivative().eval(""));
    }

    @Test
    public void testVariable() {
        Variable variable = new Variable("x");
        assertEquals(10, variable.eval("x = 10"));
        assertEquals(1, variable.derivative().eval(""));
    }

    @Test
    public void testAdd() {
        Expression addExpr = new Add(new Number(3), new Number(4));
        assertEquals(7, addExpr.eval(""));
    }

    @Test
    public void testMul() {
        Expression mulExpr = new Mul(new Number(2), new Variable("x"));
        assertEquals(20, mulExpr.eval("x = 10"));
    }

    @Test
    public void testDiv() {
        Expression divExpr = new Div(new Number(20), new Number(4));
        assertEquals(5, divExpr.eval(""));
    }
    @Test
    public void testDivByZero() {
        Expression left = new Number(10); // Предположим, что у вас есть класс Const для констант
        Expression right = new Number(0);
        Div div = new Div(left, right);

        try {
            div.eval("");
            fail("Expected ArithmeticException was not thrown");
        } catch (ArithmeticException e) {
            assertEquals("Division by zero", e.getMessage());
        }
    }

    @Test
    public void testDerivative() {
        // Тестируем производные для сложного выражения
        Expression e = new Add(new Number(3), new Mul(new Number(2), new Variable("x"))); // 3 + 2*x
        Expression derivative = e.derivative(); // 0 + 2
        assertEquals(2, derivative.eval(""));
    }

    @Test
    public void testComplexExpression() {
        // Тестируем сложное выражение: (3 + 2*x) / (4 - x)
        Expression complexExpr = new Div(
                new Add(new Number(3), new Mul(new Number(2), new Variable("x"))),
                new Sub(new Number(4), new Variable("x"))
        );
        assertEquals(1, complexExpr.eval("x = 1"));
    }
}