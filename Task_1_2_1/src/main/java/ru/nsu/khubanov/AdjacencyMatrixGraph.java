package ru.nsu.khubanov;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

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

    public void readFromFile(String filePath) throws Exception {
        try (FileReader fileReader = new FileReader(filePath)) {
            Scanner scanner = new Scanner(fileReader);

            // Чтение первой строки для добавления всех вершин
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] vertices = line.split(" ");
                for (String vertex : vertices) {
                    this.addVertex(vertex.trim());
                }
            } else {
                throw new Exception("Файл пуст. Ожидались вершины.");
            }

            // Чтение оставшихся строк для добавления рёбер
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue; // Пропускаем пустые строки
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
        int[] inDegree = new int[vertices.size()];

        // Рассчитываем степени входа для каждой вершины
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (adjMatrix[j][i] == 1) {
                    inDegree[i]++;
                }
            }
        }

        // Собираем вершины с нулевой степенью входа
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                stack.push(i);
            }
        }

        List<String> sortedList = new ArrayList<>();

        // Обрабатываем вершины с нулевой степенью входа
        while (!stack.isEmpty()) {
            int vertexIndex = stack.pop();
            sortedList.add(vertices.get(vertexIndex));

            // Уменьшаем степени входа соседей
            for (int i = 0; i < vertices.size(); i++) {
                if (adjMatrix[vertexIndex][i] == 1) {
                    inDegree[i]--;
                    if (inDegree[i] == 0) {
                        stack.push(i);
                    }
                }
            }
        }

        // Проверяем на наличие цикла
        if (sortedList.size() != vertices.size()) {
            throw new IllegalStateException("В графе есть хотя бы один цикл, топологическая сортировка невозможна.");
        }

        return sortedList;
    }
}
