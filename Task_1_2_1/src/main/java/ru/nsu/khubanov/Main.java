package ru.nsu.khubanov;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        System.out.println(graph.toString());
    }
}