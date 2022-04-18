package course.java.mutithreading.threadmethods;

import course.java.util.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;


public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var executor = Executors.newCachedThreadPool();
        var keywords = List.of("java", "jdk", "CompletableFuture", "multithreading", "lambda", "StreamAPI");
        List<Tuple<String, Future<String>>> futures = new ArrayList<>();
        for(var kw : keywords) {
            var future = executor.submit(new SearchTask(kw));
            futures.add(new Tuple<>(kw, future));
        }

        // wait for all futures to complete
        while(futures.size() > 0) {
            if(futures.size() <= 3) { // cancel all remaining tasks
                for(int i = 0; i < futures.size(); i++) {
                    futures.get(i).getV2().cancel(true);
                }
            }
            for (int i = 0; i < futures.size(); i++) {
                if (futures.get(i).getV2().isDone()) {
                    try {
                        var result = futures.get(i).getV2().get(100, TimeUnit.MILLISECONDS);
                        System.out.printf("Task complete: %s, Remaining tasks: %d%n",
                               result, futures.size());
                        futures.remove(i);
                    } catch (TimeoutException e) {
                        System.out.println("Timeout: Task " + futures.get(i).getV1());
                    } catch (CancellationException ex) {
                        System.out.printf("Canceled: Task '%s'%n", futures.get(i).getV1());
                        futures.remove(i);
                    }
                }
            }
            Thread.sleep(10);
        }
        shutdownAndAwaitTermination(executor);
    }

    static void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(5, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
