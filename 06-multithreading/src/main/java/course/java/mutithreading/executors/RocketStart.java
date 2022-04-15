package course.java.mutithreading.executors;

public class RocketStart implements Runnable {
    private int rocketNumber;
    private volatile boolean canceled = false;

    public RocketStart(int rocketNumber) {
        this.rocketNumber = rocketNumber;
    }

//    synchronized public boolean isCanceled() {
//        return canceled;
//    }
//
//    synchronized public void cancel() {
//        canceled = true;
//    }
    public void cancel() {
        canceled = true;
    }

    @Override
    public void run() {
        try {
            for (int i = 10; !canceled && i > 0; i--) {
                System.out.printf("Rocket %d: Countdown: %d [%s]%n", rocketNumber, i,
                        Thread.currentThread().getName());
                Thread.sleep(1000);

            }
            if (canceled) {
                System.out.printf("Rocket %d: was canceled!!!!!!!!!!!!![%s]%n", rocketNumber,
                        Thread.currentThread().getName());
                return;
            }
            System.out.printf("Rocket %d: Liftoff!!! [%s]%n", rocketNumber, Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.printf("Rocket %d: was interrupted. Countdown canceled.[%s]%n", rocketNumber,
                    Thread.currentThread().getName());
        }
    }
}
