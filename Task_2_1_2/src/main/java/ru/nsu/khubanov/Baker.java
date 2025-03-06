package ru.nsu.khubanov;

public class Baker extends Thread{
    private final int id;
    private final int speed;
    private final OrderQueue orderQueue;
    private final Storage storage;

    public Baker(int id, int speed, OrderQueue orderQueue, Storage storage){
        this.id=id;
        this.speed=speed;
        this.orderQueue=orderQueue;
        this.storage=storage;
    }

    @Override
    public void run() {
        try{
            while(!isInterrupted()){
                Order order = orderQueue.takeOrder();
                System.out.println("[" + order.getId() + "] Пекарь " + id + " начал готовить");
                Thread.sleep(speed);
                System.out.println("[" + order.getId() + "] Пекарь " + id + " приготовил");
                storage.storePizza(order);
            }
        } catch (InterruptedException e) {
            System.out.println("Пекарь " + id + " завершает работу");
        }
    }
}
