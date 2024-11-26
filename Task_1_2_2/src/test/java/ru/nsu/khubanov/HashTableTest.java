package ru.nsu.khubanov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    private HashTable<String, Number> hashTable;

    @BeforeEach
    void setUp() {
        hashTable = new HashTable<>();
    }

    @Test
    void testPutAndGet() {
        hashTable.put("one", 1);
        assertEquals(1, hashTable.get("one"));
        hashTable.put("one", 1.0);
        assertEquals(1.0, hashTable.get("one"));
    }

    @Test
    void testUpdate() {
        hashTable.put("two", 2);
        hashTable.update("two", 2.0);
        assertEquals(2.0, hashTable.get("two"));
    }

    @Test
    void testRemove() {
        hashTable.put("three", 3);
        hashTable.remove("three");
        assertNull(hashTable.get("three"));
    }

    @Test
    void testContainsKey() {
        hashTable.put("four", 4);
        assertTrue(hashTable.containsKey("four"));
        assertFalse(hashTable.containsKey("five"));
    }

    @Test
    void testSize() {
        assertEquals(0, hashTable.size());
        hashTable.put("six", 6);
        assertEquals(1, hashTable.size());
        hashTable.put("seven", 7);
        assertEquals(2, hashTable.size());
        hashTable.remove("six");
        assertEquals(1, hashTable.size());
    }

    @Test
    void testEquality() {
        HashTable<String, Number> anotherTable = new HashTable<>();
        hashTable.put("key", 100);
        anotherTable.put("key", 100);
        assertEquals(hashTable, anotherTable);
        anotherTable.put("key2", 200);
        assertNotEquals(hashTable, anotherTable);
    }

    @Test
    void testToString() {
        hashTable.put("a", 1);
        hashTable.put("b", 2);
        String result = hashTable.toString();
        assertTrue(result.contains("a=1"));
        assertTrue(result.contains("b=2"));
    }

    @Test
    void testIterator() {
        hashTable.put("x", 10);
        hashTable.put("y", 20);

        var iterator = hashTable.iterator();
        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());

        // Modify hashTable while iterating to trigger ConcurrentModificationException
        hashTable.put("z", 30);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    void testCollisionHandling() {
        hashTable.put("Aa", 1);
        hashTable.put("BB", 2); // Assuming "Aa" and "BB" have the same hash
        assertEquals(1, hashTable.get("Aa"));
        assertEquals(2, hashTable.get("BB"));
    }

    @Test
    void testNullKey() {
        hashTable.put(null, 42);
        assertEquals(42, hashTable.get(null));
        hashTable.update(null, 43);
        assertEquals(43, hashTable.get(null));
        hashTable.remove(null);
        assertNull(hashTable.get(null));
    }

    @Test
    void testForeachIteration() {
        // Проверка итерации через foreach
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        hashTable.put("three", 3);

        StringBuilder result = new StringBuilder();
        for (Entry<String, Number> entry : hashTable) {
            result.append(entry.key).append("=").append(entry.value).append(";");
        }

        // Проверяем, что строки содержат все ключи и значения
        assertTrue(result.toString().contains("one=1;"));
        assertTrue(result.toString().contains("two=2;"));
        assertTrue(result.toString().contains("three=3;"));
    }

    @Test
    void testConcurrentModificationDuringIteration() {
        // Проверка выброса ConcurrentModificationException при изменении коллекции
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        hashTable.put("three", 3);

        assertThrows(ConcurrentModificationException.class, () -> {
            for (Entry<String, Number> entry : hashTable) {
                if (entry.key.equals("two")) {
                    hashTable.put("four", 4); // Изменение коллекции
                }
            }
        });
    }
}
