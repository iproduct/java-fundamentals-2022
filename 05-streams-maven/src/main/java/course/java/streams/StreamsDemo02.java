package course.java.streams;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamsDemo02 {
    public static void main(String[] args) throws IOException {
        String[] data = {"hello", "from", "java", "stream", "api", "hello", "from", "java", "stream", "api"};
        var dataStream = Arrays.stream(data);
//        Predicate<String> matchesA = val -> val.contains("a");
        var results = dataStream
                .peek(word -> {
                    System.out.println("In peek: " + word);
                }).dropWhile(word -> {
                    System.out.println("In dropWhile: " + word);
                    return !word.equals("api");
                })
                .filter(word -> {
                    System.out.println("In filter: " + word);
                    return word.contains("a");
                }).map(word -> {
                    System.out.println("In map: " + word);
                    return word.toUpperCase();
                }).limit(2)
                .collect(Collectors.toList());
        System.out.println("Results: " + results);
    }
}
