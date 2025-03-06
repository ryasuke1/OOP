package ru.nsu.khubanov;
import java.util.LinkedList;
import java.util.Queue;

public class OrderQueue {

    public final Queue<Order> orders = new LinkedList<>();

    public synchronized void addOrder(Order order){
        orders.add(order);
        notifyAll();
    }

    public synchronized Order takeOrder() throws InterruptedException {
        while (orders.isEmpty()) {
            wait();
        }
        return orders.poll();
    }
}
