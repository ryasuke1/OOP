package ru.nsu.khubanov;

import java.util.Random;

import static ru.nsu.khubanov.ParallelStream.HasNotPrimeParallel;
import static ru.nsu.khubanov.ParallelThread.HasPrimeParallelThreads;
import static ru.nsu.khubanov.PrimeCheck.IsPrime;
import static ru.nsu.khubanov.Sequentially.HasNotPrimeSeq;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Генерация массива простых чисел
        int size = 12;

        int[] primes = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009, 6998029, 6998039, 20165149, 6998051, 6998053};


        // Измерение времени выполнения
        long start, end;

        start = System.nanoTime();
        HasNotPrimeSeq(primes);
        end = System.nanoTime();
        System.out.println("Последовательное выполнение: " + (end - start) / 1_000_000.0 + " мс");

        for (int threads = 2; threads <= 8; threads *= 2) {
            start = System.nanoTime();
            HasPrimeParallelThreads(primes, threads);
            end = System.nanoTime();
            System.out.println("Параллельное выполнение (" + threads + " потоков): " + (end - start) / 1_000_000.0 + " мс");
        }

        start = System.nanoTime();
        HasNotPrimeParallel(primes);
        end = System.nanoTime();
        System.out.println("parallelStream выполнение: " + (end - start) / 1_000_000.0 + " мс");
    }
}