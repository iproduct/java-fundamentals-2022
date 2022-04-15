package course.java.mutithreading.threadmethods;

import course.java.mutithreading.executors.RocketStart;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class DaemonThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        var thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}

public class DaemonsThreadPool {
    public static final int NUM_ROCKETS = 10;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for(int i = 0; i < NUM_ROCKETS; i ++) {
            executor.execute(new RocketStart(i));
        }
        Thread.sleep(2000);
        System.out.println("Program finished.");
    }
}
