package ru.nsu.khubanov;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InterruptedException {

        Runnable smrhdoing = new smthdo();
        Thread sleept =new Thread(smrhdoing);
        sleept.start();
        System.out.println("stopping");
        sleept.interrupt();
        // Генерация массива простых чисел
        int size = 12;

        int[] primes = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967, 6998009, 6998029, 6998039, 20165149, 6998051, 6998053};


        // Измерение времени выполнения
        long start, end;

        SearchNotPrime sequential = new Sequentially();
        start = System.nanoTime();
        sequential.HasNotPrime(primes);
        end = System.nanoTime();
        System.out.println("Последовательное выполнение: " + (end - start) / 1_000_000.0 + " мс");}
}

class smthdo implements Runnable {
    @Override
    public void run () {
        try{
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println(" Exception: " + e);
        }
    }
}