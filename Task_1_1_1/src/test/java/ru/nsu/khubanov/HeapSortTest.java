package ru.nsu.khubanov;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class HeapSortTest {

    @Test
    public void testHeapSortWithDescendingArray() {
        int[] input = {5, 4, 3, 2, 1};
        int[] expected = {1, 2, 3, 4, 5};
        HeapSort.heapSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testHeapSortWithAlreadySortedArray() {
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        HeapSort.heapSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testHeapSortWithDuplicates() {
        int[] input = {3, 3, 1, 2, 2};
        int[] expected = {1, 2, 2, 3, 3};
        HeapSort.heapSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testHeapSortWithSingleElement() {
        int[] input = {1};
        int[] expected = {1};
        HeapSort.heapSort(input);
        assertArrayEquals(expected, input);
    }

    @Test
    public void testHeapSortWithEmptyArray() {
        int[] input = {};
        int[] expected = {};
        HeapSort.heapSort(input);
        assertArrayEquals(expected, input);
    }
}