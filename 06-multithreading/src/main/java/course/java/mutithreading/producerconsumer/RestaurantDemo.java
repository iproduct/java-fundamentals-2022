package course.java.mutithreading.producerconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Pizza {
    private final int orderNumber;

    public Pizza(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String toString() {
        return "Pizza: " + orderNumber;
    }
}

class Waiter implements Runnable {
    private RestaurantDemo restaurant;
    private String name;

    public Waiter(RestaurantDemo r, String name) {
        this.restaurant = r;
        this.name = name;
    }

    public void run() {
        try {
            Pizza pizza;
            while (!Thread.interrupted()) {
                synchronized (restaurant) {
                    while (restaurant.pizza == null)
                        restaurant.wait(); // ... for the chef to produce a meal
                    pizza = restaurant.pizza;
                    restaurant.pizza = null;
                }
                System.out.println("Waiter " + name + " got " + pizza);
                synchronized (restaurant.chef) {
                    restaurant.chef.notifyAll(); // Ready for another pizza
                }
                Thread.sleep((long) (Math.random() * 2000));
            }
        } catch (InterruptedException e) {
            System.out.println("WaitPerson interrupted");
        }
    }
}

class Cook implements Runnable {
    private RestaurantDemo restaurant;
    private int orderNumber = 0;

    public Cook(RestaurantDemo r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.pizza != null)
                        wait(); // ... for the meal to be taken
                }
                if (++orderNumber > 15) {
                    System.out.println("Working time finished - closing restaurant ... ");
                    restaurant.exec.shutdownNow();
                    return;
                }
                System.out.println("New pizza No. " + orderNumber + " is ready!");
                synchronized (restaurant) {
                    restaurant.pizza = new Pizza(orderNumber);
                    restaurant.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Chef interrupted");
        }
    }
}

public class RestaurantDemo {
    Pizza pizza;
    ExecutorService exec = Executors.newCachedThreadPool();
    Waiter waitPerson = new Waiter(this, "PESHO");
    //                waitPerson2 = new WaitPerson(this, "TOSHO");
    Cook chef = new Cook(this);

    public RestaurantDemo() {
        exec.execute(chef);
        exec.execute(waitPerson);
//            exec.execute(waitPerson2);
    }

    public static void main(String[] args) {
        new RestaurantDemo();
    }
}
