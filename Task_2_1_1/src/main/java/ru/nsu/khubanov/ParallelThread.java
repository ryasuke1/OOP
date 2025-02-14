package ru.nsu.khubanov;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParallelThread {
    public static boolean HasPrimeParallelThreads(int[] nums, int threadcount) throws InterruptedException{
        AtomicBoolean foundNotPrime = new AtomicBoolean(false);
        List<Thread> threads = new ArrayList<>();
        int chunkSize = nums.length / threadcount;

        for(int i = 0; i < threadcount; i++){
            int start = i*chunkSize;
            int end;
            if (i == threadcount - 1) {
                end = nums.length;
            } else {
                end = (i + 1) * chunkSize;
            }
            Thread thread =new Thread(() -> {
               for(int j = start; j<end && !foundNotPrime.get(); j++){
                   if(!PrimeCheck.IsPrime(nums[j])){
                       foundNotPrime.set(true);
                       break;
                   }
               }
            });
            threads.add(thread);
            thread.start();
            if(foundNotPrime.get()){
                break;
            }
        }
        for(Thread thread : threads){
            thread.join();
        }
        return foundNotPrime.get();
    }
}
