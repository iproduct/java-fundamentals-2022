package course.java.mutithreading.threadmethods;

import course.java.mutithreading.executors.RocketStart;

public class SimpleDaemons {
    public static final int NUM_ROCKETS = 10;
    public static void main(String[] args) {
        for(int i = 0; i < NUM_ROCKETS; i++) {
            var thread = new Thread(new RocketStart(i));
            thread.setDaemon(true);
            thread.start();
        }
        System.out.println("Program finished.");
    }
}
