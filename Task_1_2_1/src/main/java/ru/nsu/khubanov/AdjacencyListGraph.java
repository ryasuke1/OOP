package ru.nsu.khubanov;

import java.util.*;

class AdjacencyListGraph implements Graph {
    private Map<String, List<String>> adjList;

    public AdjacencyListGraph() {
        this.adjList = new HashMap<>();
    }

    public void addVertex(String vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void removeVertex(String vertex) {
        adjList.values().forEach(e -> e.remove(vertex));
        adjList.remove(vertex);
    }

    public void addEdge(String startVertex, String endVertex) {
        adjList.get(startVertex).add(endVertex);
    }

    public void removeEdge(String startVertex, String endVertex) {
        List<String> edges = adjList.get(startVertex);
        if (edges != null) edges.remove(endVertex);
    }

    public List<String> getNeighbors(String vertex) {
        return adjList.getOrDefault(vertex, new ArrayList<>());
    }

    public void readFromFile(String filePath) {
        // Реализация для чтения графа из файла
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof AdjacencyListGraph)) return false;
        AdjacencyListGraph other = (AdjacencyListGraph) obj;
        return adjList.equals(other.adjList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Adjacency List:\n");
        for (Map.Entry<String, List<String>> entry : adjList.entrySet()) {
            sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    public List<String> topologicalSort() {
        // Реализация алгоритма топологической сортировки
        return new ArrayList<>();
    }
}
