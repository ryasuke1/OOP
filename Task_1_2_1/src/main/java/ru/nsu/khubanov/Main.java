package ru.nsu.khubanov;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        IncidenceMatrixGraph graph = new IncidenceMatrixGraph();
        IncidenceMatrixGraph graphh = new IncidenceMatrixGraph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("A", "C");
        graph.addEdge("C", "D");

        graphh.addVertex("A");
        graphh.addVertex("B");
        graphh.addVertex("C");
        graphh.addVertex("D");
        graphh.addEdge("A", "B");
        graphh.addEdge("B", "C");
        graphh.addEdge("A", "C");
        graphh.addEdge("C", "D");

        System.out.println(graph.toString());
        System.out.println(graphh.toString());
    }
}