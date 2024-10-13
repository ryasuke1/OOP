package ru.nsu.khubanov;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdjacencyMatrixGraph implements Graph {
    private List<String> vertices;
    private int[][] adjMatrix;

    public AdjacencyMatrixGraph() {
        this.vertices = new ArrayList<>();
        this.adjMatrix = new int[0][0];
    }

    public void addVertex(String vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            int newSize = vertices.size();
            int[][] newMatrix = new int[newSize][newSize];
            for (int i = 0; i < adjMatrix.length; i++) {
                System.arraycopy(adjMatrix[i], 0, newMatrix[i], 0, adjMatrix[i].length);
            }
            this.adjMatrix = newMatrix;
        }
    }

    public void removeVertex(String vertex) {
        int index = vertices.indexOf(vertex);
        if (index != -1) {
            vertices.remove(index);
            int newSize = vertices.size();
            int[][] newMatrix = new int[newSize][newSize];
            for (int i = 0, ni = 0; i < adjMatrix.length; i++) {
                if (i == index) continue;
                for (int j = 0, nj = 0; j < adjMatrix.length; j++) {
                    if (j == index) continue;
                    newMatrix[ni][nj++] = adjMatrix[i][j];
                }
                ni++;
            }
            this.adjMatrix = newMatrix;
        }
    }

    public void addEdge(String startVertex, String endVertex) {
        int startIndex = vertices.indexOf(startVertex);
        int endIndex = vertices.indexOf(endVertex);
        if (startIndex != -1 && endIndex != -1) {
            adjMatrix[startIndex][endIndex] = 1;
        }
    }

    public void removeEdge(String startVertex, String endVertex) {
        int startIndex = vertices.indexOf(startVertex);
        int endIndex = vertices.indexOf(endVertex);
        if (startIndex != -1 && endIndex != -1) {
            adjMatrix[startIndex][endIndex] = 0;
        }
    }

    public List<String> getNeighbors(String vertex) {
        List<String> neighbors = new ArrayList<>();
        int index = vertices.indexOf(vertex);
        if (index != -1) {
            for (int i = 0; i < adjMatrix.length; i++) {
                if (adjMatrix[index][i] == 1) {
                    neighbors.add(vertices.get(i));
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
        if (!(obj instanceof AdjacencyMatrixGraph)) return false;
        AdjacencyMatrixGraph other = (AdjacencyMatrixGraph) obj;
        return vertices.equals(other.vertices) && Arrays.deepEquals(adjMatrix, other.adjMatrix);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(vertices).append("\nAdjacency Matrix:\n");
        for (int[] row : adjMatrix) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }

    public List<String> topologicalSort() {
        // Реализация алгоритма топологической сортировки
        return new ArrayList<>();
    }
}

