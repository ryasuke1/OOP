package ru.nsu.khubanov;

import java.io.FileReader;
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
        adjList.computeIfAbsent(startVertex, k -> new ArrayList<>()).add(endVertex);
    }

    public void removeEdge(String startVertex, String endVertex) {
        List<String> edges = adjList.get(startVertex);
        if (edges != null) edges.remove(endVertex);
    }

    public List<String> getNeighbors(String vertex) {
        return adjList.getOrDefault(vertex, new ArrayList<>());
    }

    public void readFromFile(String filePath) throws Exception {
        try (FileReader fileReader = new FileReader(filePath)) {
            Scanner scanner = new Scanner(fileReader);

            // Чтение первой строки для добавления всех вершин
            String line = scanner.nextLine();
            String[] vertices = line.split(" ");
            for (String vertex : vertices) {
                this.addVertex(vertex.trim());
            }

            // Чтение оставшихся строк для добавления рёбер
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] edge = line.split(",");
                if (edge.length == 2) {
                    String startVertex = edge[0].trim();
                    String endVertex = edge[1].trim();
                    this.addEdge(startVertex, endVertex);
                } else {
                    throw new Exception("Некорректный формат строки: " + line);
                }
            }
        } catch (Exception e) {
            throw new Exception("Ошибка при чтении из файла: " + e.getMessage(), e);
        }
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
        // Подсчет степеней входа
        Map<String, Integer> inDegree = new HashMap<>();
        for (String vertex : adjList.keySet()) {
            inDegree.put(vertex, 0);
        }
        for (String vertex : adjList.keySet()) {
            for (String neighbor : adjList.get(vertex)) {
                inDegree.put(neighbor, inDegree.get(neighbor) + 1);
            }
        }

        // Поиск вершин с нулевой степенью входа
        Queue<String> queue = new LinkedList<>();
        for (Map.Entry<String, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        List<String> sortedList = new ArrayList<>();

        // Топологическая сортировка
        while (!queue.isEmpty()) {
            String vertex = queue.poll();
            sortedList.add(vertex);

            // Уменьшение степени входа для соседей
            for (String neighbor : adjList.getOrDefault(vertex, new ArrayList<>())) {
                inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                if (inDegree.get(neighbor) == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Проверка на наличие цикла
        if (sortedList.size() != adjList.size()) {
            throw new IllegalStateException("Граф имеет хотя бы один цикл, топологическая сортировка невозможна.");
        }

        return sortedList;
    }
}
