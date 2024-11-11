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

    @Override
    public void addVertex(String vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);

            // Добавляем новую строку для вершины в матрице инцидентности
            int[] newRow = new int[edgeCount];
            incidenceMatrix.add(newRow);
        }
    }

    @Override
    public void removeVertex(String vertex) {
        int index = vertices.indexOf(vertex);
        if (index != -1) {
            // Удаляем вершину из списка вершин
            vertices.remove(index);

            // Удаляем строку, связанную с вершиной, из матрицы инцидентности
            incidenceMatrix.remove(index);

            // Удаляем столбцы, связанные с удалённой вершиной, из всех строк матрицы
            for (int i = 0; i < incidenceMatrix.size(); i++) {
                int[] oldRow = incidenceMatrix.get(i);
                int[] newRow = new int[edgeCount];
                int newColIndex = 0;

                for (int j = 0; j < edgeCount; j++) {
                    if (oldRow[j] != 1 && oldRow[j] != -1) {
                        newRow[newColIndex++] = oldRow[j];
                    }
                }

                incidenceMatrix.set(i, Arrays.copyOf(newRow, newColIndex));
            }

            // Обновляем количество рёбер после удаления всех связанных рёбер
            edgeCount--;
        }
    }


    @Override
    public void addEdge(String startVertex, String endVertex) {
        int startIndex = vertices.indexOf(startVertex);
        int endIndex = vertices.indexOf(endVertex);

        if (startIndex != -1 && endIndex != -1) {
            edgeCount++;

            // Расширяем каждую строку матрицы инцидентности
            for (int i = 0; i < incidenceMatrix.size(); i++) {
                int[] oldRow = incidenceMatrix.get(i);
                int[] newRow = Arrays.copyOf(oldRow, edgeCount);
                incidenceMatrix.set(i, newRow);
            }

            // Устанавливаем связь между вершинами
            incidenceMatrix.get(startIndex)[edgeCount - 1] = 1;  // Стартовая вершина
            incidenceMatrix.get(endIndex)[edgeCount - 1] = -1; // Конечная вершина
        } else {
            throw new IllegalArgumentException("Указаны неверные вершины: " + startVertex + ", " + endVertex);
        }
    }

    @Override
    public void removeEdge(String startVertex, String endVertex) {
        int startIndex = vertices.indexOf(startVertex);
        int endIndex = vertices.indexOf(endVertex);

        if (startIndex != -1 && endIndex != -1) {
            int edgeIndex = -1;

            // Поиск столбца для рёбра
            for (int i = 0; i < edgeCount; i++) {
                if (incidenceMatrix.get(startIndex)[i] == 1 && incidenceMatrix.get(endIndex)[i] == -1) {
                    edgeIndex = i;
                    break;
                }
            }

            if (edgeIndex != -1) {
                // Удаляем столбец из всех строк матрицы
                for (int i = 0; i < incidenceMatrix.size(); i++) {
                    int[] oldRow = incidenceMatrix.get(i);
                    int[] newRow = new int[edgeCount - 1];
                    System.arraycopy(oldRow, 0, newRow, 0, edgeIndex);
                    System.arraycopy(oldRow, edgeIndex + 1, newRow, edgeIndex, edgeCount - edgeIndex - 1);
                    incidenceMatrix.set(i, newRow);
                }
                edgeCount--;
            }
        } else {
            throw new IllegalArgumentException("Указаны неверные вершины: " + startVertex + ", " + endVertex);
        }
    }

    @Override
    public List<String> getNeighbors(String vertex) {
        List<String> neighbors = new ArrayList<>();
        int vertexIndex = vertices.indexOf(vertex);

        if (vertexIndex != -1) {
            int[] edges = incidenceMatrix.get(vertexIndex);
            for (int i = 0; i < edges.length; i++) {
                if (edges[i] == 1) { // Найти вершины, в которые выходит ребро
                    for (int j = 0; j < incidenceMatrix.size(); j++) {
                        if (j != vertexIndex && incidenceMatrix.get(j)[i] == -1) {
                            neighbors.add(vertices.get(j));
                        }
                    }
                }
            }
        }

        return neighbors;
    }

    @Override
    public void readFromFile(String filePath) throws Exception {
        try (FileReader fileReader = new FileReader(filePath)) {
            Scanner scanner = new Scanner(fileReader);

            // Чтение первой строки для добавления всех вершин
            if (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] verticesArray = line.split(" ");
                for (String vertex : verticesArray) {
                    this.addVertex(vertex.trim());
                }
            } else {
                throw new Exception("Файл пуст. Ожидались вершины.");
            }

            // Чтение оставшихся строк для добавления рёбер
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue; // Пропуск пустых строк
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
    public List<String> topologicalSort() {
        // Инициализация степеней входа
        int[] inDegree = new int[vertices.size()];
        for (int i = 0; i < edgeCount; i++) {
            for (int j = 0; j < incidenceMatrix.size(); j++) {
                if (incidenceMatrix.get(j)[i] == -1) { // Конечная вершина рёбра
                    inDegree[j]++;
                }
            }
        }

        // Очередь для вершин с нулевой степенью входа
        List<String> sortedList = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Выполнение топологической сортировки
        while (!queue.isEmpty()) {
            int vertexIndex = queue.poll();
            sortedList.add(vertices.get(vertexIndex));

            // Уменьшаем степень входа для соседей
            for (int i = 0; i < edgeCount; i++) {
                if (incidenceMatrix.get(vertexIndex)[i] == 1) { // Начальная вершина рёбра
                    for (int j = 0; j < incidenceMatrix.size(); j++) {
                        if (incidenceMatrix.get(j)[i] == -1) { // Найдена конечная вершина
                            inDegree[j]--;
                            if (inDegree[j] == 0) {
                                queue.add(j);
                            }
                        }
                    }
                }
            }
        }

        // Проверка на наличие цикла
        if (sortedList.size() != vertices.size()) {
            throw new IllegalStateException("Граф содержит цикл, топологическая сортировка невозможна.");
        }

        return sortedList;
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
}
