package ru.nsu.khubanov;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class TestFileGenerator {
    public static void generateLargeFile(String fileName, int sizeInMB) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))) {
            for (int i = 0; i < sizeInMB * 1024 * 1024 / 10; i++) { // Предположим, строка длиной 10 байт
                writer.write("абракадабра");
            }
        }
    }
}
