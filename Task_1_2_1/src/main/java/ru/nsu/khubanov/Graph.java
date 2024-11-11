package ru.nsu.khubanov;

import java.util.List;

public interface Graph {
    void addVertex(String vertex);
    void removeVertex(String vertex);
    void addEdge(String startVertex, String endVertex);
    void removeEdge(String startVertex, String endVertex);
    List<String> getNeighbors(String vertex);
    void readFromFile(String filePath) throws Exception;
    List<String> topologicalSort();
}

