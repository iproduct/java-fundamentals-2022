package course.java.io;

import java.io.*;
import java.util.Random;

record Reading(long timestamp, double temperature) {
}

public class InputOutputStreamDemo {
    public static final String TEMPERATURE_READINGS_FILENAME = "temperature.db";
    public static final int  NUM_READINGS = 15;

    public static void main(String[] args) {
        try (var input = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(TEMPERATURE_READINGS_FILENAME)))) {
            input.writeUTF("Timestamp");
            input.writeUTF("Temperature");
            new Random().doubles(NUM_READINGS)
                    .map(r -> (int)(r * 100 * 40)/100.0)
                    .peek(t -> {
                        try {
                            Thread.sleep((int)(Math.random() * 1000));
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }).mapToObj(t -> new Reading(System.currentTimeMillis(), t))
                    .forEach(r -> {
                        System.out.println(r);
                        try {
                            input.writeLong(r.timestamp());
                            input.writeDouble(r.temperature());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
