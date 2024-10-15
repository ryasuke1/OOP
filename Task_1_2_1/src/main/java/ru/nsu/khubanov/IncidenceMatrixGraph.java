package ru.nsu.khubanov;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IncidenceMatrixGraph implements Graph {
    private List<String> vertices;
    private List<int[]> incidenceMatrix;
    private int edgeCount;

    public IncidenceMatrixGraph() {
        this.vertices = new ArrayList<>();
        this.incidenceMatrix = new ArrayList<>();
        this.edgeCount = 0;
    }

    public void addVertex(String vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            incidenceMatrix.add(new int[edgeCount]);
        }
    }

    public void removeVertex(String vertex) {
        int index = vertices.indexOf(vertex);
        if (index != -1) {
            vertices.remove(index);
            incidenceMatrix.remove(index);
        }
    }

    public void addEdge(String startVertex, String endVertex) {
        int startIndex = vertices.indexOf(startVertex);
        int endIndex = vertices.indexOf(endVertex);
        if (startIndex != -1 && endIndex != -1) {
            edgeCount++;
            for (int[] row : incidenceMatrix) {
                row = Arrays.copyOf(row, edgeCount);
            }
            incidenceMatrix.get(startIndex)[edgeCount - 1] = 1;
            incidenceMatrix.get(endIndex)[edgeCount - 1] = -1;
        }
    }

    public void removeEdge(String startVertex, String endVertex) {
        // Реализация для удаления ребра в матрице инцидентности
    }

    public List<String> getNeighbors(String vertex) {
        List<String> neighbors = new ArrayList<>();
        int index = vertices.indexOf(vertex);
        if (index != -1) {
            int[] edges = incidenceMatrix.get(index);
            for (int i = 0; i < edges.length; i++) {
                if (edges[i] == 1 || edges[i] == -1) {
                    for (int j = 0; j < incidenceMatrix.size(); j++) {
                        if (j != index && incidenceMatrix.get(j)[i] != 0) {
                            neighbors.add(vertices.get(j));
                        }
                    }
                }
            }
        }
        return neighbors;
    }

    public void readFromFile(String filePath) {
        // Реализация для чтения графа из файла
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof IncidenceMatrixGraph)) return false;
        IncidenceMatrixGraph other = (IncidenceMatrixGraph) obj;
        return vertices.equals(other.vertices) && incidenceMatrix.equals(other.incidenceMatrix);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(vertices).append("\nIncidence Matrix:\n");
        for (int[] row : incidenceMatrix) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }

    public List<String> topologicalSort() {
        // Реализация алгоритма топологической сортировки
        return new ArrayList<>();
    }
}
