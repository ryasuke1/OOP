package ru.nsu.khubanov;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrimeTest {

    @Test
    public void testIsPrime() {
        assertTrue(PrimeCheck.IsPrime(2));
        assertTrue(PrimeCheck.IsPrime(17));
        assertFalse(PrimeCheck.IsPrime(18));
        assertFalse(PrimeCheck.IsPrime(100));
    }

    // 🔹 Тест последовательного метода
    @Test
    public void testHasNotPrimeSeq() {
        int[] primes = {2, 3, 5, 7, 11};  // Все числа простые
        int[] mixed = {2, 3, 4, 7, 11};   // 4 - не простое

        assertFalse(Sequentially.HasNotPrimeSeq(primes));
        assertTrue(Sequentially.HasNotPrimeSeq(mixed));
    }

    // 🔹 Тест параллельного метода через потоки
    @Test
    public void testHasPrimeParallelThreads() throws InterruptedException {
        int[] primes = {101, 103, 107, 109};
        int[] mixed = {101, 103, 110, 109};

        assertFalse(ParallelThread.HasPrimeParallelThreads(primes, 4));
        assertTrue(ParallelThread.HasPrimeParallelThreads(mixed, 4));
    }

    // 🔹 Тест параллельного метода через parallelStream
    @Test
    public void testHasNotPrimeParallel() {
        int[] primes = {13, 17, 19, 23};
        int[] mixed = {13, 17, 20, 23};

        assertFalse(ParallelStream.HasNotPrimeParallel(primes));
        assertTrue(ParallelStream.HasNotPrimeParallel(mixed));
    }

    // 🔹 Тестирование производительности (дополнительно)
    @Test
    public void testPerformance() throws InterruptedException {
        int size = 100_000;
        int[] primes = new int[size];
        for (int i = 0, num = 2; i < size; num++) {
            if (PrimeCheck.IsPrime(num)) {
                primes[i++] = num;
            }
        }

        long start, end;

        // Последовательное выполнение
        start = System.nanoTime();
        Sequentially.HasNotPrimeSeq(primes);
        end = System.nanoTime();
        System.out.println("Последовательное выполнение: " + (end - start) / 1_000_000.0 + " мс");

        // Параллельное выполнение с потоками (2 потока)
        start = System.nanoTime();
        ParallelThread.HasPrimeParallelThreads(primes, 2);
        end = System.nanoTime();
        System.out.println("Параллельное выполнение (2 потока): " + (end - start) / 1_000_000.0 + " мс");

        // Параллельное выполнение с потоками (4 потока)
        start = System.nanoTime();
        ParallelThread.HasPrimeParallelThreads(primes, 4);
        end = System.nanoTime();
        System.out.println("Параллельное выполнение (4 потока): " + (end - start) / 1_000_000.0 + " мс");

        // Параллельное выполнение с потоками (8 потока)
        start = System.nanoTime();
        ParallelThread.HasPrimeParallelThreads(primes, 8);
        end = System.nanoTime();
        System.out.println("Параллельное выполнение (8 потока): " + (end - start) / 1_000_000.0 + " мс");

        // Параллельное выполнение с paralelStream
        start = System.nanoTime();
        ParallelStream.HasNotPrimeParallel(primes);
        end = System.nanoTime();
        System.out.println("parallelStream выполнение: " + (end - start) / 1_000_000.0 + " мс");
    }
}
