package ru.nsu.khubanov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.nsu.khubanov.Parser.parse;

class ParserTest {
    @Test
    public void testSimpleNumber() {
        Expression result = parse("42");
        assertEquals(42, result.eval(""));
    }

    @Test
    public void testSimpleVariable() {
        Expression result = parse("x");
        assertEquals(10, result.eval("x = 10"));
    }

    @Test
    public void testAddition() {
        Expression result = parse("3 + 4");
        assertEquals(7, result.eval(""));
    }

    @Test
    public void testMultiplication() {
        Expression result = parse("2 * 5");
        assertEquals(10, result.eval(""));
    }

    @Test
    public void testComplexExpression() {
        Expression result = parse("3 + 2 * x");
        assertEquals(23, result.eval("x = 10"));
    }


    @Test
    public void testMultipleOperations() {
        Expression result = parse("3 + 2 * x - 4");
        assertEquals(19, result.eval("x = 10"));
    }

    @Test
    public void testDivision() {
        Expression result = parse("10 / 2");
        assertEquals(5, result.eval(""));
    }


    @Test
    public void testVariablesInExpression() {
        Expression result = parse("x + y * 2");
        assertEquals(26, result.eval("x = 10; y = 8"));
    }

    @Test
    public void testVariablesInExpressionWithTwoBrackets() {
        Expression result = parse("(x + y * 2)");
        assertEquals(26, result.eval("x = 10; y = 8"));
    }

    @Test
    public void testVariablesInExpressionWithOneBrackets() {
        Expression result = parse("(x + y * 2");
        assertEquals(26, result.eval("x = 10; y = 8"));
    }
    @Test
    public void testDerivative() {
        // Тестируем производные для сложного выражения
        Expression e = parse("f-x*x*x/y+z");
        assertEquals(58, e.eval("f = 50; x = 2; y = 7; z = 9"));
    }

}
