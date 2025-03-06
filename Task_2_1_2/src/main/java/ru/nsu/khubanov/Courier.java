package ru.nsu.khubanov;

import java.util.ArrayList;
import java.util.List;

public class Courier extends Thread{
    private final int id;
    private final int capacity;
    private final Storage storage;

    public Courier(int id, int capacity, Storage storage) {
        this.id=id;
        this.capacity=capacity;
        this.storage=storage;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                List<Order> pizzas = new ArrayList<>();
                for (int i = 0; i < capacity; i++){
                    pizzas.add(storage.takePizza());
                }
                System.out.println("Курьер " + id + " доставляет " + pizzas.size() + " пицц");
                Thread.sleep(2000);
                System.out.println("Курьер " + id + " доставил заказ");
            }
        }catch (InterruptedException e){
            System.out.println("Курьер " + id + " завершает работу");
        }
    }
}
