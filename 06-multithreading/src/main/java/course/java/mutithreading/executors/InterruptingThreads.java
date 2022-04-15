package course.java.mutithreading.executors;

import course.java.mutithreading.RocketStart;

import java.util.ArrayList;
import java.util.List;

public class InterruptingThreads {
    public static void main(String[] args) throws InterruptedException {
        List<RocketStart> tasks = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            var task = new RocketStart(i);
            tasks.add(task);
            var thread = new Thread(task);
            threads.add(thread);
            thread.start();
        }
        Thread.sleep(6500);
        for(int i = 0 ; i < 5; i ++){
            tasks.get(i).cancel();
        }
    }
}

