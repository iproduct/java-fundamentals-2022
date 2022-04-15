package course.java.mutithreading.threadmethods;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

public class CompletionServiceThreadPool {
    public static final int NUM_THREADS = 100;
    public static final int NUM_IDS = 1000000;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        var sequence = new SafeSequenceSynchronized();
        var executor = Executors.newCachedThreadPool();
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        for (int i = 0; i < NUM_THREADS; i++) {
            final int taskNumber = i;
            cs.submit(() -> {
                for (int j = 0; !Thread.interrupted() && j < NUM_IDS; j++) {
                    var id = sequence.getNextId();
                }
            }, taskNumber);
        }
        for (int i = 0; i < NUM_THREADS; i++) {
            System.out.printf("[%s] Task %d complete.%n",
                    Thread.currentThread().getName(),
                    cs.take().get());
        }
        System.out.println("Total IDs: " + sequence.getLastId());
        executor.shutdown();
    }
}

