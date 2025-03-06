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

        for (int i = 0; i < threadcount; i++) {
            if (foundNotPrime.get()) {
                break;
            }
            int start = i * chunkSize;
            int end = (i == threadcount - 1) ? nums.length : (i + 1) * chunkSize;

            Thread thread;
            thread = new Thread(() -> {
                for (int j = start; j < end; j++) {
                    if (foundNotPrime.get()) {
                        return;
                    }
                    if (!PrimeCheck.IsPrime(nums[j])) {
                        foundNotPrime.set(true);
                        return;
                    }
                }
            });
            threads.add(thread);
            thread.start();
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