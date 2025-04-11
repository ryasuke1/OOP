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

        SearchNotPrime sequential = new Sequentially();
        assertFalse(sequential.HasNotPrime(primes));
        assertTrue(sequential.HasNotPrime(mixed));
    }

    // 🔹 Тест параллельного метода через потоки
    @Test
    public void testHasPrimeParallelThreads() throws InterruptedException {
        int[] primes = {101, 103, 107, 109};
        int[] mixed = {101, 103, 110, 109};

        SearchNotPrime parallelThread = new ParallelThread(4);
        assertFalse(parallelThread.HasNotPrime(primes));
        assertTrue(parallelThread.HasNotPrime(mixed));
    }

    // 🔹 Тест параллельного метода через parallelStream
    @Test
    public void testHasNotPrimeParallel() {
        int[] primes = {13, 17, 19, 23};
        int[] mixed = {13, 17, 20, 23};

        SearchNotPrime parallelstream = new ParallelStream();
        assertFalse(parallelstream.HasNotPrime(primes));
        assertTrue(parallelstream.HasNotPrime(mixed));
    }

    // 🔹 Тестирование производительности (дополнительно)
    @Test
    public void testPerformance() throws InterruptedException {
        int size = 10000000;
        int[] primes = new int[size];
        for (int i = 0, num = 2; i < size; num++) {
            if (PrimeCheck.IsPrime(num)) {
                primes[i++] = num;
            }
        }

        long start, end;

        // Последовательное выполнение
        SearchNotPrime sequential = new Sequentially();
        start = System.nanoTime();
        sequential.HasNotPrime(primes);
        end = System.nanoTime();
        System.out.println("Последовательное выполнение: " + (end - start) / 1_000_000.0 + " мс");

        // Параллельное выполнение с потоками (2 потока)
        SearchNotPrime parallelThread2 = new ParallelThread(2);
        start = System.nanoTime();
        parallelThread2.HasNotPrime(primes);
        end = System.nanoTime();
        System.out.println("Параллельное выполнение (2 потока): " + (end - start) / 1_000_000.0 + " мс");

        // Параллельное выполнение с потоками (4 потока)
        SearchNotPrime parallelThread4 = new ParallelThread(4);
        start = System.nanoTime();
        parallelThread4.HasNotPrime(primes);
        end = System.nanoTime();
        System.out.println("Параллельное выполнение (4 потока): " + (end - start) / 1_000_000.0 + " мс");

        // Параллельное выполнение с потоками (8 потока)
        SearchNotPrime parallelThread8 = new ParallelThread(8);
        start = System.nanoTime();
        parallelThread8.HasNotPrime(primes);
        end = System.nanoTime();
        System.out.println("Параллельное выполнение (8 потока): " + (end - start) / 1_000_000.0 + " мс");

        // Параллельное выполнение с потоками (16 потока)
        SearchNotPrime parallelThread16 = new ParallelThread(16);
        start = System.nanoTime();
        parallelThread8.HasNotPrime(primes);
        end = System.nanoTime();
        System.out.println("Параллельное выполнение (16 потока): " + (end - start) / 1_000_000.0 + " мс");

        // Параллельное выполнение с paralelStream
        SearchNotPrime parallelstream = new ParallelStream();
        start = System.nanoTime();
        parallelstream.HasNotPrime(primes);
        end = System.nanoTime();
        System.out.println("parallelStream выполнение: " + (end - start) / 1_000_000.0 + " мс");
    }
}
