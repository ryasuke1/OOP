package ru.nsu.khubanov;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SubstringFinder {

    public static List<Integer> find(String fileName, String substring) throws IOException {
        List<Integer> indices = new ArrayList<>();
        int substringLength = substring.codePointCount(0, substring.length());
        StringBuilder window = new StringBuilder();
        int totalCodePointsRead = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            int ch;
            while ((ch = reader.read()) != -1) {
                window.append((char) ch);

                // Удаляем лишние символы из окна
                if (window.codePointCount(0, window.length()) > substringLength) {
                    window.delete(0, window.offsetByCodePoints(0, 1));
                }

                // Проверяем содержимое окна
                if (window.codePointCount(0, window.length()) == substringLength
                        && window.toString().equals(substring)) {
                    indices.add(totalCodePointsRead - substringLength + 1);
                }

                totalCodePointsRead++;
            }
        }

        return indices;
    }



}
