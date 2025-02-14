package ru.nsu.khubanov;

import java.util.stream.IntStream;

import static ru.nsu.khubanov.PrimeCheck.IsPrime;

public class ParallelStream {
    public static boolean HasNotPrimeParallel(int[] nums){
        return IntStream.of(nums).parallel().anyMatch(num -> !IsPrime(num));
    }
}
