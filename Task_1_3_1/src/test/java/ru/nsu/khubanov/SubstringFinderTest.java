package ru.nsu.khubanov;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class SubstringFinderTest {

    @Test
    public void testSmallFileSingleMatch() throws IOException {
        createTestFile("test1.txt", "абракадабра");
        List<Integer> result = SubstringFinder.find("test1.txt", "бра");
        assertEquals(List.of(1, 8), result);
    }

    @Test
    public void testSmallFileNoMatch() throws IOException {
        createTestFile("test2.txt", "абракадабра");
        List<Integer> result = SubstringFinder.find("test2.txt", "xyz");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSmallFileOverlappingMatches() throws IOException {
        createTestFile("test3.txt", "aaaaa");
        List<Integer> result = SubstringFinder.find("test3.txt", "aaa");
        assertEquals(List.of(0, 1, 2), result);
    }

    @Test
    public void testLargeFileWithMatches() throws IOException {
        createTestFile("test4.txt", "абракадабра".repeat(1000));
        List<Integer> result = SubstringFinder.find("test4.txt", "бра");
        assertEquals(2000, result.size());
    }

    @Test
    public void testLargeFileNoMatches() throws IOException {
        createTestFile("test5.txt", "абракадабра".repeat(1000));
        List<Integer> result = SubstringFinder.find("test5.txt", "xyz");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testEmptyFile() throws IOException {
        createTestFile("test6.txt", "");
        List<Integer> result = SubstringFinder.find("test6.txt", "бра");
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSubstringLongerThanFileContent() throws IOException {
        createTestFile("test7.txt", "абра");
        List<Integer> result = SubstringFinder.find("test7.txt", "абракадабра");
        assertTrue(result.isEmpty());
    }

    private void createTestFile(String fileName, String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))) {
            writer.write(content);
        }
    }
}
