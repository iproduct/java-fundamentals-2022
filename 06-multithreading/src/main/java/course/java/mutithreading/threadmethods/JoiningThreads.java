package course.java.mutithreading.threadmethods;

import course.java.mutithreading.executors.RocketStart;

import java.util.ArrayList;
import java.util.List;
class SafeSequenceSynchronized { // pessimistic concurrency (exclusive locking)
    private int nextId = 0;
    synchronized int getLastId() {
        return nextId;
    }
    synchronized int getNextId() {
        return ++nextId;
    }
}

public class JoiningThreads {
    public static final int NUM_THREADS = 100;
    public static final int NUM_IDS = 1000000;
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        var sequence = new SafeSequenceSynchronized();
        for(int i =0; i < NUM_THREADS; i ++ ){
            var thread = new Thread(() -> {
                for(int j = 0; !Thread.interrupted() && j < NUM_IDS; j++){
                    var id = sequence.getNextId();
                }
            });
            threads.add(thread);
            thread.start();
        }
        for(int i = 0; i < threads.size(); i++) {
            threads.get(i).join();
        }
        System.out.println("Total IDs: " + sequence.getLastId());
    }
}

