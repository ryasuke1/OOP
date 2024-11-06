package ru.nsu.khubanov;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomCollection implements Iterable<Integer> {
    private final int[] elements;

    public CustomCollection(int[] elements) {
        this.elements = elements;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new CustomIterator();
    }

    private class CustomIterator implements Iterator<Integer> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < elements.length;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return elements[currentIndex++];
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        CustomCollection collection = new CustomCollection(nums);

        for (int num : collection) {
            System.out.println(num);
        }
    }
}

