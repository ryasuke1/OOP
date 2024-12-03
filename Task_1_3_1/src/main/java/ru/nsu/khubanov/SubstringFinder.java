package ru.nsu.khubanov;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SubstringFinder {

    public static List<Integer> find(String fileName, String substring) throws IOException {
        List<Integer> indices = new ArrayList<>();
        int bufferSize = 1024; // Размер буфера для чтения файла
        char[] buffer = new char[bufferSize];
        int totalCharsRead = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {
            StringBuilder window = new StringBuilder();
            int charsRead;

            while ((charsRead = reader.read(buffer)) != -1) {
                // Добавляем новые символы в окно
                window.append(buffer, 0, charsRead);

                // Пытаемся найти подстроку в текущем окне
                int index;
                while ((index = window.indexOf(substring)) != -1) {
                    indices.add(totalCharsRead + index);
                    // Обновляем окно, начиная с символа после найденного вхождения
                    window.delete(0, index + 1);
                    totalCharsRead += index + 1;
                }

                // Сохраняем последние символы окна для проверки перекрытий
                if (window.length() > substring.length()) {
                    totalCharsRead += window.length() - substring.length();
                    window = new StringBuilder(window.substring(window.length() - substring.length()));
                }
            }
        }

        return indices;
    }

    public static void main(String[] args) {
        try {
            List<Integer> result = find("input.txt", "бра");
            System.out.println(result); // Вывод [1, 8]
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
