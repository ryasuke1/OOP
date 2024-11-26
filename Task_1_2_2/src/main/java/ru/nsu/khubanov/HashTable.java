package ru.nsu.khubanov;
import java.util.*;

public class HashTable<K, V> implements Iterable<Entry<K, V>> {
    private static final int INITIAL_CAPACITY = 16; // Начальная емкость
    private static final float LOAD_FACTOR = 0.75f; // Коэффициент загрузки

    private int size; // Текущее количество элементов
    private int capacity; // Текущая емкость массива
    private int modCount; // Счетчик изменений для итератора
    private LinkedList<Entry<K, V>>[] table; // Массив списков для хранения пар ключ-значение

    @SuppressWarnings("unchecked")
    public HashTable() {
        this.capacity = INITIAL_CAPACITY;
        this.table = new LinkedList[capacity];
        this.size = 0;
        this.modCount = 0;
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }
        for (Entry<K, V> entry : table[index]) {
            if (Objects.equals(entry.key, key)) {
                entry.value = value;
                return;
            }
        }
        table[index].add(new Entry<>(key, value));
        size++;
        modCount++;
        if (size > capacity * LOAD_FACTOR) {
            resize();
        }
    }

    public V get(K key) {
        int index = getIndex(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (Objects.equals(entry.key, key)) {
                    return entry.value;
                }
            }
        }
        return null;
    }

    public void update(K key, V value) {
        put(key, value);
    }

    public boolean containsKey(K key) {
        int index = getIndex(key);
        if (table[index] != null) {
            for (Entry<K, V> entry : table[index]) {
                if (Objects.equals(entry.key, key)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void remove(K key) {
        int index = getIndex(key);
        if (table[index] != null) {
            Iterator<Entry<K, V>> iterator = table[index].iterator();
            while (iterator.hasNext()) {
                Entry<K, V> entry = iterator.next();
                if (Objects.equals(entry.key, key)) {
                    iterator.remove();
                    size--;
                    modCount++;
                    return;
                }
            }
        }
    }

    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> entrySet = new HashSet<>();
        for (LinkedList<Entry<K, V>> bucket : table) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    entrySet.add(new AbstractMap.SimpleEntry<>(entry.key, entry.value));
                }
            }
        }
        return entrySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashTable<?, ?> that = (HashTable<?, ?>) o;
        return Objects.equals(this.entrySet(), that.entrySet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(entrySet());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, V> entry : entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2); // Удалить последнюю запятую и пробел
        }
        sb.append("}");
        return sb.toString();
    }

    private int getIndex(K key) {
        return key == null ? 0 : Math.abs(key.hashCode() % capacity);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        capacity *= 2;
        LinkedList<Entry<K, V>>[] oldTable = table;
        table = new LinkedList[capacity];
        size = 0;
        for (LinkedList<Entry<K, V>> bucket : oldTable) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    put(entry.key, entry.value);
                }
            }
        }
    }
    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new EntryIterator();
    }


    private class EntryIterator implements Iterator<Entry<K, V>> {
        private final int expectedModCount = modCount; // Ожидаемое значение modCount для проверки изменений
        private int currentIndex = 0; // Текущий индекс массива table
        private Iterator<Entry<K, V>> bucketIterator = table[currentIndex] == null ? null : table[currentIndex].iterator(); // Итератор текущего бакета

        @Override
        public boolean hasNext() {
            // Проверяем текущий бакет
            if (bucketIterator != null && bucketIterator.hasNext()) {
                return true;
            }
            // Переходим к следующему бакету
            while (++currentIndex < table.length) {
                if (table[currentIndex] != null) {
                    bucketIterator = table[currentIndex].iterator();
                    if (bucketIterator.hasNext()) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public Entry<K, V> next() {
            // Проверяем, не изменилась ли таблица
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            // Если нет следующего элемента, выбрасываем исключение
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            // Получаем следующий элемент из текущего бакета
            return bucketIterator.next();
        }
    }


    public int size() {
        return size;
    }
}