package course.java.mutithreading.executors;

import course.java.mutithreading.RocketStart;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleExecutors {
    public static final int NUM_ROCKETS = 10;
    public static void main(String[] args) throws InterruptedException {
//        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        ExecutorService executor = Executors.newFixedThreadPool(20);
        ExecutorService executor = Executors.newCachedThreadPool();
        List<RocketStart> tasks = new ArrayList<>();

        executor.execute(() ->
                System.out.println("Runnable (using lambda) - Test 01 in thread: "
                        + Thread.currentThread().getName())
        );
        for(int i = 0; i < NUM_ROCKETS; i ++) {
            var task = new RocketStart(i);
            tasks.add(task);
            executor.execute(task);
        }

        Thread.sleep(4500);
        for(int i = 0; i < NUM_ROCKETS; i += 2){
            tasks.get(i).cancel();
        }
    }
}

