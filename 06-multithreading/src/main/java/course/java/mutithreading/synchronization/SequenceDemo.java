package course.java.mutithreading.synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class UnsafeSequence {
    private int nextId = 0;
    int getLastId() {
        return nextId;
    }
    int getNextId() {
        return ++nextId;
    }
}

class SafeSequenceSynchronized { // pessimistic concurrency (exclusive locking)
    private int nextId = 0;
    synchronized int getLastId() {
        return nextId;
    }
    synchronized int getNextId() {
        return ++nextId;
    }
}

class SafeSequenceAtomic { // optimistic concurrency (no locking -> comparing versions)
    private AtomicInteger nextId = new AtomicInteger(); // using CAS processor op
    int getLastId() {
        return nextId.get();
    }
    int getNextId() {
        return nextId.incrementAndGet();
    }
}

public class SequenceDemo{
    public static final int NUM_THREADS = 100;
    public static final int NUM_IDS = 1000000;
    public static void main(String[] args) throws InterruptedException {
//        var sequence = new UnsafeSequence();
        var sequence = new SafeSequenceSynchronized();
//        var sequence = new SafeSequenceAtomic();
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ExecutorService executor = Executors.newCachedThreadPool();
        var start = System.nanoTime();
        for(int i =0; i < NUM_THREADS; i ++ ){
            executor.execute(() -> {
                for(int j = 0; !Thread.interrupted() && j < NUM_IDS; j++){
                    var id = sequence.getNextId();
                    if(id == NUM_THREADS * NUM_IDS) {
                        var end = System.nanoTime();
                        System.out.printf("Total time (ms): %f%n", (end-start) / 1000000.0);
                    }
                }
                System.out.printf("Current thread [%s] is interrupted: %b%n",
                        Thread.currentThread().getName(), Thread.interrupted());
            });
        }
//        executor.shutdownNow();
        Thread.sleep(3000);
        System.out.println(sequence.getLastId());
        shutdownAndAwaitTermination(executor);
    }

    static void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(2, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(2, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            System.out.println(Thread.currentThread().isInterrupted()); // false
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
