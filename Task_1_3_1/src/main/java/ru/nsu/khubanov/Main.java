package ru.nsu.khubanov;

import java.io.IOException;
import java.util.List;
import static ru.nsu.khubanov.SubstringFinder.find;



public class Main {
    public static void main(String[] args) {
        try {
            List<Integer> result = find("input.txt", "a[[");
            System.out.println(result); // Вывод [0, 3 , 7 ,12]
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}