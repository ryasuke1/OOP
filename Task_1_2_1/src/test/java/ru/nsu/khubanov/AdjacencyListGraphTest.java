package ru.nsu.khubanov;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyListGraphTest {
    private AdjacencyListGraph graph;

    @BeforeEach
    void setUp() {
        graph = new AdjacencyListGraph();
    }

    @Test
    void testAddVertex() {
        graph.addVertex("A");
        graph.addVertex("B");

        assertTrue(graph.getNeighbors("A").isEmpty(), "Список соседей должен быть пустым для новой вершины");
        assertTrue(graph.getNeighbors("B").isEmpty(), "Список соседей должен быть пустым для новой вершины");
    }

    @Test
    void testAddEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        List<String> neighborsA = graph.getNeighbors("A");
        assertEquals(1, neighborsA.size(), "Вершина A должна иметь одного соседа");
        assertTrue(neighborsA.contains("B"), "B должна быть соседом A");

        List<String> neighborsB = graph.getNeighbors("B");
        assertTrue(neighborsB.isEmpty(), "Вершина B не должна иметь соседей в ориентированном графе");
    }

    @Test
    void testRemoveVertex() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.removeVertex("B");

        List<String> neighborsA = graph.getNeighbors("A");
        assertTrue(neighborsA.isEmpty(), "После удаления вершины B у A не должно быть соседей");
    }

    @Test
    void testRemoveEdge() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.removeEdge("A", "B");

        List<String> neighborsA = graph.getNeighbors("A");
        assertTrue(neighborsA.isEmpty(), "После удаления ребра A -> B у A не должно быть соседей");
    }
    @Test
    void testReadFromFile() {
        String filePath = "src/test/resources/graph.txt";

        try {
            graph.readFromFile(filePath);

            // Проверяем вершины
            List<String> neighborsA = graph.getNeighbors("A");
            assertTrue(neighborsA.contains("B"), "A должна быть соединена с B");
            assertTrue(neighborsA.contains("C"), "A должна быть соединена с C");

            List<String> neighborsB = graph.getNeighbors("B");
            assertTrue(neighborsB.contains("C"), "B должна быть соединена с C");

            List<String> neighborsC = graph.getNeighbors("C");
            assertTrue(neighborsC.contains("D"), "C должна быть соединена с D");

            List<String> neighborsD = graph.getNeighbors("D");
            assertTrue(neighborsD.isEmpty(), "D не должна иметь соседей");

        } catch (Exception e) {
            fail("Ошибка при чтении из файла: " + e.getMessage());
        }
    }


    @Test
    void testTopologicalSort() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("A", "C");
        graph.addEdge("C", "D");

        List<String> sortedList = graph.topologicalSort();
        assertEquals(4, sortedList.size(), "Сортированный список должен содержать все вершины графа");
        assertTrue(sortedList.indexOf("A") < sortedList.indexOf("B"), "A должна быть перед B");
        assertTrue(sortedList.indexOf("B") < sortedList.indexOf("C"), "B должна быть перед C");
        assertTrue(sortedList.indexOf("C") < sortedList.indexOf("D"), "C должна быть перед D");
    }
}
