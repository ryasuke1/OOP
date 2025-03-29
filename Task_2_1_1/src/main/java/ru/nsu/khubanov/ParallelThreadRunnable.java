package ru.nsu.khubanov;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.lang.Runnable;
public class ParallelThreadRunnable extends SearchNotPrime {

    private final int threadcount;

    public ParallelThreadRunnable(int threadcount) {
        this.threadcount = threadcount;
    }
    @Override
    public boolean HasNotPrime(int[] nums){
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
            Runnable task = new PrimeCheckTask(nums, start,end, foundNotPrime,threads);
            Thread thread =new Thread(task);
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

    class PrimeCheckTask implements Runnable {
        private final int[] nums;
        private final int start;
        private final int end;
        private final AtomicBoolean foundNotPrime;
        private final List<Thread> threads;

        PrimeCheckTask(int[] nums, int start , int end, AtomicBoolean foundNotPrime, List<Thread> threads ) {
            this.nums = nums;
            this.end = end;
            this.start = start;
            this.foundNotPrime = foundNotPrime;
            this.threads = threads;
        }

        @Override
        public void run() {
            for(int j = start; j<end && !foundNotPrime.get(); j++){
                if(!PrimeCheck.IsPrime(nums[j])){
                    foundNotPrime.set(true);
                    stopallThreads();
                    break;
                }
            }
        }

        private void stopallThreads() {
            for (Thread thread : threads) {
                thread.interrupt();
            }
        }
    }
}

