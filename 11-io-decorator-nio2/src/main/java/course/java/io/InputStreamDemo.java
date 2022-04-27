package course.java.io;

import course.java.model.Reading;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static course.java.io.OutputStreamDemo.COLUMNS;
import static course.java.io.OutputStreamDemo.TEMPERATURE_READINGS_FILENAME;


public class InputStreamDemo {

    public static void main(String[] args) throws IOException {
        List<Reading> readings = new ArrayList<>();
        try (var input = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream(TEMPERATURE_READINGS_FILENAME)))) {
            var columns = new String[COLUMNS.length];
            for (int i = 0; i < columns.length; i++) {
                columns[i] = input.readUTF();
            }
            while (true) {
                try {
                    var time = input.readLong();
                    var temperature = input.readDouble();
                    readings.add(new Reading(time, temperature));
                } catch (EOFException e) {
                    System.out.println("Readings file imported successfully.");
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                    break;
                }
            }

            // Print imported temperature readings
            System.out.printf("| %22s | %11s |%n", columns[0], columns[1]);
            readings.stream().forEach(reading ->
                    System.out.printf("| %-22.22s | %11.2f |%n",
                            LocalDateTime.ofInstant(Instant.ofEpochMilli(reading.timestamp()), ZoneId.systemDefault()),
                            reading.temperature()));
        }
    }
}
