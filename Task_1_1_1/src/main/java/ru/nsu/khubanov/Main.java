package ru.nsu.khubanov;

import java.util.Arrays;

public class Main {
    public static void main(String[] a) {
        int[] arr = {7,8,5,6,3,1,35,67,88,952,35,53};
        int[] sortedarr = HeapSort.heapSort(arr);
        System.out.println(Arrays.toString(sortedarr));
    }
}