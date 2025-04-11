package ru.nsu.khubanov.task_2_3_1.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {

    @Test
    void testEqualsAndHashCode() {
        Cell c1 = new Cell(2, 3);
        Cell c2 = new Cell(2, 3);
        Cell c3 = new Cell(3, 3);

        assertEquals(c1, c2);
        assertNotEquals(c1, c3);
        assertEquals(c1.hashCode(), c2.hashCode());
    }
}
