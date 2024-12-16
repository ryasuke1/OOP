package ru.nsu.khubanov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    public void testSimpleNumber() {
        Expression result = Main.parse("42");
        assertEquals(42, result.eval(""));
    }

    @Test
    public void testSimpleVariable() {
        Expression result = Main.parse("x");
        assertEquals(10, result.eval("x = 10"));
    }

    @Test
    public void testAddition() {
        Expression result = Main.parse("3 + 4");
        assertEquals(7, result.eval(""));
    }

    @Test
    public void testMultiplication() {
        Expression result = Main.parse("2 * 5");
        assertEquals(10, result.eval(""));
    }

    @Test
    public void testComplexExpression() {
        Expression result = Main.parse("3 + 2 * x");
        assertEquals(23, result.eval("x = 10"));
    }


    @Test
    public void testMultipleOperations() {
        Expression result = Main.parse("3 + 2 * x - 4");
        assertEquals(19, result.eval("x = 10"));
    }

    @Test
    public void testDivision() {
        Expression result = Main.parse("10 / 2");
        assertEquals(5, result.eval(""));
    }


    @Test
    public void testVariablesInExpression() {
        Expression result = Main.parse("x + y * 2");
        assertEquals(26, result.eval("x = 10; y = 8"));
    }

    @Test
    public void testVariablesInExpressionWithTwoBrackets() {
        Expression result = Main.parse("(x + y * 2)");
        assertEquals(26, result.eval("x = 10; y = 8"));
    }

    @Test
    public void testVariablesInExpressionWithOneBrackets() {
        Expression result = Main.parse("(x + y * 2");
        assertEquals(26, result.eval("x = 10; y = 8"));
    }

}