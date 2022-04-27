package course.java.io;

import course.java.model.Reading;

import java.io.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class WriterDemo {
    public static final String TEMPERATURE_READINGS_FILENAME = "temperature.json";
    public static final String[] COLUMNS = {"timestamp", "temperature"};
    public static final int NUM_READINGS = 15;

    public static void main(String[] args) {
        try (var out = new PrintWriter(
                new BufferedWriter(
//                        new OutputStreamWriter(
                        new FileWriter(TEMPERATURE_READINGS_FILENAME)))) {
            out.println("[");
            var counter = new AtomicInteger();
            new Random().doubles(NUM_READINGS)
                    .map(r -> (int) (r * 100 * 40) / 100.0)
                    .peek(t -> {
                        try {
                            Thread.sleep((int) (Math.random() * 1000));
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }).mapToObj(t -> new Reading(System.currentTimeMillis(), t))
                    .forEach(r -> {
                        var json = String.format("    { \"%s\": %d, \"%s\": %s }",
                                COLUMNS[0], r.timestamp(), COLUMNS[1],
                                (int) (r.temperature() * 100) / 100.0);
                        json += counter.incrementAndGet() == NUM_READINGS ? "" : ", ";
                        System.out.println(json);
                        out.println(json);
                    });
            out.println("]");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
