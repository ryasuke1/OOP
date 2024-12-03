package ru.nsu.khubanov;

import java.io.IOException;
import java.util.List;

import static ru.nsu.khubanov.SubstringFinder.find;
import static ru.nsu.khubanov.TestFileGenerator.generateLargeFile;


public class Main {
    public static void main(String[] args) {
        try {
            List<Integer> result = find("input.txt", "бра");
            System.out.println(result); // Вывод [1, 8]
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            generateLargeFile("large_input.txt", 100); // Генерация файла размером 100 МБ
            System.out.println("Файл сгенерирован.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}