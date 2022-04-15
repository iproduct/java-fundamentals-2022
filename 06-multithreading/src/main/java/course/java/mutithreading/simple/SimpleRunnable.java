package course.java.mutithreading.simple;

public class SimpleRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable Test 01 in thread: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        new SimpleRunnable().run();
        var thread1 = new Thread(new SimpleRunnable());
        thread1.setName("Another thread");
        thread1.start();

        var runnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable (Anonymous inner class) - Test 01 in thread: "
                        + Thread.currentThread().getName());
            }
        };
        new Thread(runnable2).start();

        new Thread(() ->
                System.out.println("Runnable (using lambda) - Test 01 in thread: "
                        + Thread.currentThread().getName())
        ).start();

        new Thread(){
            @Override
            public void run() {
                System.out.println("Overriding Thread run() - Test 01 in thread: "
                        + Thread.currentThread().getName());
            }
        }.start();
    }
}
