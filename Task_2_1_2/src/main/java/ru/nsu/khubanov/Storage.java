package ru.nsu.khubanov;

import java.util.LinkedList;
import java.util.Queue;
public class Storage {
    private final int capacity;
    private final Queue<Order> pizzas = new LinkedList<>();

    public Storage(int capacity) {
        this.capacity=capacity;
    }
    public synchronized void storePizza(Order order) throws InterruptedException {
        while (pizzas.size() >= capacity){
            wait();
        }
        pizzas.add(order);
        System.out.println("[" + order.getId() + "]  Пицца на складе");
        notifyAll();
    }

    public synchronized Order takePizza() throws InterruptedException {
        while(pizzas.isEmpty()) {
            wait();
        }
        Order order = pizzas.poll();
        notifyAll();
        return order;
    }
}
