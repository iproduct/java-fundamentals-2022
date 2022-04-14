package course.java.streams;

import java.util.stream.IntStream;

public class StreamsDemo04 {
    public static void main(String[] args) {
        int reducedParallel = IntStream.rangeClosed(1, 10000).boxed()
                .parallel()
                .reduce(0, (a, b) -> a + b, (a, b) -> {
                    System.out.printf("combiner called for %s and %s%n", a, b);
                    return a + b;
                });
        System.out.println("Reduced with accumulator and combiner: " + reducedParallel);
    }
}
