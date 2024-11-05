package ru.nsu.khubanov;

import java.io.FileReader;
import java.util.*;

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
            // Увеличиваем количество столбцов в каждой строке матрицы инцидентности
            for (int i = 0; i < incidenceMatrix.size(); i++) {
                incidenceMatrix.set(i, Arrays.copyOf(incidenceMatrix.get(i), edgeCount));
            }
            // Устанавливаем значение инцидентности для стартовой и конечной вершин
            incidenceMatrix.get(startIndex)[edgeCount - 1] = 1;
            incidenceMatrix.get(endIndex)[edgeCount - 1] = -1;
        }
    }

    public void removeEdge(String startVertex, String endVertex) {
        int startIndex = vertices.indexOf(startVertex);
        int endIndex = vertices.indexOf(endVertex);
        if (startIndex != -1 && endIndex != -1) {
            for (int i = 0; i < edgeCount; i++) {
                if (incidenceMatrix.get(startIndex)[i] == 1 && incidenceMatrix.get(endIndex)[i] == -1) {
                    for (int[] row : incidenceMatrix) {
                        System.arraycopy(row, i + 1, row, i, edgeCount - i - 1);
                        row[edgeCount - 1] = 0;
                    }
                    edgeCount--;
                    break;
                }
            }
        }
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
        // Подсчет степеней входа
        Map<String, Integer> inDegree = new HashMap<>();
        for (String vertex : vertices) {
            inDegree.put(vertex, 0);
        }

        // Рассчитываем степени входа
        for (int i = 0; i < edgeCount; i++) {
            int startVertexIndex = -1;
            int endVertexIndex = -1;
            for (int j = 0; j < incidenceMatrix.size(); j++) {
                if (incidenceMatrix.get(j)[i] == 1) {
                    startVertexIndex = j;
                } else if (incidenceMatrix.get(j)[i] == -1) {
                    endVertexIndex = j;
                }
            }
            if (endVertexIndex != -1) {
                String endVertex = vertices.get(endVertexIndex);
                inDegree.put(endVertex, inDegree.get(endVertex) + 1);
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
            int vertexIndex = vertices.indexOf(vertex);
            int[] edges = incidenceMatrix.get(vertexIndex);
            for (int i = 0; i < edges.length; i++) {
                if (edges[i] == 1) {
                    for (int j = 0; j < incidenceMatrix.size(); j++) {
                        if (incidenceMatrix.get(j)[i] == -1) {
                            String neighbor = vertices.get(j);
                            inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                            if (inDegree.get(neighbor) == 0) {
                                queue.add(neighbor);
                            }
                        }
                    }
                }
            }
        }

        // Проверка на наличие цикла
        if (sortedList.size() != vertices.size()) {
            throw new IllegalStateException("Граф имеет хотя бы один цикл, топологическая сортировка невозможна.");
        }

        return sortedList;
    }
}
