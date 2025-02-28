package ru.nsu.khubanov;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ParallelThread extends SearchNotPrime {

    private final int threadcount;

    public ParallelThread(int threadcount) {
        this.threadcount = threadcount;
    }
    @Override
    public boolean HasNotPrime(int[] nums){
        CustomAnotomicBoolean foundNotPrime = new CustomAnotomicBoolean(false);
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
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return foundNotPrime.get();
    }
}
