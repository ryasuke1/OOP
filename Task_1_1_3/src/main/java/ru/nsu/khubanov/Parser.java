package ru.nsu.khubanov;
import Expression.*;

public class Parser {
    public static Expression parse(String input) {
        input = input.replaceAll("[()\\s]+", ""); // Удаляем пробелы и скобки
        return parseExpression(input);
    }

    private static Expression parseExpression(String input) {
        // Обработка сложения и вычитания (наименьший приоритет)
        int pos = findOperatorPosition(input, "+-");
        if (pos != -1) {
            char operator = input.charAt(pos);
            Expression left = parseExpression(input.substring(0, pos));
            Expression right = parseExpression(input.substring(pos + 1));
            return operator == '+' ? new Add(left, right) : new Sub(left, right);
        }

        // Обработка умножения и деления (высокий приоритет)
        pos = findOperatorPosition(input, "*/");
        if (pos != -1) {
            char operator = input.charAt(pos);
            Expression left = parseExpression(input.substring(0, pos));
            Expression right = parseExpression(input.substring(pos + 1));
            return operator == '*' ? new Mul(left, right) : new Div(left, right);
        }

        // Если это число или переменная
        try {
            return new MyNumber(Integer.parseInt(input));
        } catch (NumberFormatException e) {
            return new Variable(input);
        }
    }

    private static int findOperatorPosition(String input, String operators) {
        for (int i = input.length() - 1; i >= 0; i--) { // Ищем справа налево
            char c = input.charAt(i);
            if ( operators.indexOf(c) != -1) {
                return i; // Возвращаем позицию оператора
            }
        }
        return -1; // Оператор не найден
    }
}
