package ru.nsu.khubanov;

import java.util.Arrays;

import static ru.nsu.khubanov.PrimeCheck.IsPrime;

public class ParallelStream {
    public static boolean HasNotPrimeParallel(int[] nums){
        return Arrays.stream(nums).parallel().anyMatch(num -> !IsPrime(num));
    }
}
