package ru.nsu.khubanov;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntryTest {

    @Test
    void testConstructor() {
        Entry<String, Integer> entry = new Entry<>("key", 123);
        assertEquals("key", entry.key);
        assertEquals(123, entry.value);
    }

    @Test
    void testEquals() {
        Entry<String, Integer> entry1 = new Entry<>("key", 123);
        Entry<String, Integer> entry2 = new Entry<>("key", 123);
        Entry<String, Integer> entry3 = new Entry<>("anotherKey", 456);

        assertEquals(entry1, entry2);
        assertNotEquals(entry1, entry3);
    }

    @Test
    void testHashCode() {
        Entry<String, Integer> entry1 = new Entry<>("key", 123);
        Entry<String, Integer> entry2 = new Entry<>("key", 123);

        assertEquals(entry1.hashCode(), entry2.hashCode());
    }

    @Test
    void testToString() {
        Entry<String, Integer> entry = new Entry<>("key", 123);
        assertEquals("key=123", entry.toString());
    }

    @Test
    void testMutableValue() {
        Entry<String, Integer> entry = new Entry<>("key", 123);
        entry.value = 456; // Проверка изменения значения
        assertEquals(456, entry.value);
    }
}
