package ru.nsu.khubanov;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;


class HeapSortTest {

    // Метод для генерации случайного массива с отрицательными и положительными числами
    private int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(20001) - 10000; // Генерация чисел от -1000 до 1000
        }
        return arr;
    }

    @Test
    public void testHeapSortWithRandomArrays() {
        // Тестирование на массивах разного размера
        int[] sizes = {1000, 5000, 10000, 50000, 100000, 500000, 1000000}; // Размеры массивов для тестирования

        for (int size : sizes) {
            // Генерация случайного массива
            int[] randomarr = generateRandomArray(size);

            // Копия массива для проверки правильности
            int[] expected = Arrays.copyOf(randomarr, randomarr.length);

            Arrays.sort(expected);
            long startTime = System.nanoTime();
            int[] sortedarr=HeapSort.heapSort(randomarr);
            long endTime = System.nanoTime();

            long duration = endTime - startTime; //
            System.out.println("Size: " + size + ", Time: " + duration );
            assertArrayEquals(expected, sortedarr);
        }
    }


    @Test
    public void testHeapSortWithDescendingArray() {
        int[] input = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        long startTime = System.nanoTime();
        int[] sortedarr=HeapSort.heapSort(input);
        long endTime = System.nanoTime();

        long duration = endTime - startTime; //
        System.out.println("Size: " + sortedarr.length + ", Time: " + duration );
        assertArrayEquals(expected, sortedarr);
    }

    @Test
    public void testHeapSortWithAlreadySortedArray() {
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        long startTime = System.nanoTime();
        int[] sortedarr=HeapSort.heapSort(input);
        long endTime = System.nanoTime();

        long duration = endTime - startTime; //
        System.out.println("Size: " + sortedarr.length + ", Time: " + duration );
        assertArrayEquals(expected, sortedarr);
    }

    @Test
    public void testHeapSortWithDuplicates() {
        int[] input = {3, 3, 1, 2, 2};
        int[] expected = {1, 2, 2, 3, 3};
        long startTime = System.nanoTime();
        int[] sortedarr=HeapSort.heapSort(input);
        long endTime = System.nanoTime();

        long duration = endTime - startTime; //
        System.out.println("Size: " + sortedarr.length + ", Time: " + duration );
        assertArrayEquals(expected, sortedarr);
    }

    @Test
    public void testHeapSortWithSingleElement() {
        int[] input = {1};
        int[] expected = {1};
        long startTime = System.nanoTime();
        int[] sortedarr=HeapSort.heapSort(input);
        long endTime = System.nanoTime();

        long duration = endTime - startTime; //
        System.out.println("Size: " + sortedarr.length + ", Time: " + duration );
        assertArrayEquals(expected, sortedarr);
    }

    @Test
    public void testHeapSortWithEmptyArray() {
        int[] input = {};
        int[] expected = {};
        long startTime = System.nanoTime();
        int[] sortedarr=HeapSort.heapSort(input);
        long endTime = System.nanoTime();

        long duration = endTime - startTime; //
        System.out.println("Size: " + sortedarr.length + ", Time: " + duration );
        assertArrayEquals(expected, sortedarr);
    }
}