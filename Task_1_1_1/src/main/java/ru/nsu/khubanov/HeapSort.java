package ru.nsu.khubanov;

import java.util.Arrays;

public class HeapSort {

    public static int[] heapSort(int[] arr) {
        int n = arr.length;
        int[] sortedarr= Arrays.copyOf(arr,n);

        // Построение кучи (перегруппируем массив)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(sortedarr, n, i);
        }
        // Один за другим извлекаем элементы из кучи
        for (int i = n - 1; i >= 0; i--) {
            // Перемещаем текущий корень в конец
            int temp = sortedarr[0];
            sortedarr[0] = sortedarr[i];
            sortedarr[i] = temp;

            // Вызываем heapify на уменьшенной куче
            heapify(sortedarr, i, 0);
        }
        return(sortedarr);
    }

    // Функция для преобразования в бинарную кучу поддерева с корнем i
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;  // Инициализируем наибольший как корень
        int left = 2 * i + 1;  // левый = 2*i + 1
        int right = 2 * i + 2;  // правый = 2*i + 2

        // Если левый дочерний элемент больше корня
        if (left < n && arr[left] > arr[largest])
            largest = left;

        // Если правый дочерний элемент больше, чем самый большой элемент на данный момент
        if (right < n && arr[right] > arr[largest])
            largest = right;

        // Если самый большой элемент не корень
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Рекурсивно преобразуем в кучу затронутое поддерево
            heapify(arr, n, largest);
        }
    }
}
