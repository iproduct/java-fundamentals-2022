package course.java.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class StreamsDemo07 {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("./src/main/java/course/java/streams/StreamsDemo07.java");
        Files.lines(path)
//                .parallel()
                .collect(
                        HashMap<Integer, String>::new,
                        (map, line) -> map.put(map.size() + 1, line),
                        Map::putAll) // Create a map of the index to the object
                .forEach((i, o) -> { // Now we can use a BiConsumer forEach!
                    System.out.println(String.format("%d: %s", i, o));
                });
    }
}
