package ru.nsu.khubanov;

import java.io.IOException;
import java.util.List;
import static ru.nsu.khubanov.SubstringFinder.find;



public class Main {
    public static void main(String[] args) {
        try {
            List<Integer> result = find("main.txt", "a[[");
            System.out.println(result); // Вывод [1, 8]
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}