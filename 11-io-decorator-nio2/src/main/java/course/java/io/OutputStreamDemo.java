package course.java.io;

import course.java.model.Reading;

import java.io.*;
import java.util.Random;



public class OutputStreamDemo {
    public static final String TEMPERATURE_READINGS_FILENAME = "temperature.db";
    public static final String[] COLUMNS = {"Timestamp", "Temperature"};
    public static final int  NUM_READINGS = 15;

    public static void main(String[] args) {
        try (var input = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(TEMPERATURE_READINGS_FILENAME)))) {
            input.writeUTF(COLUMNS[0]);
            input.writeUTF(COLUMNS[1]);
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
