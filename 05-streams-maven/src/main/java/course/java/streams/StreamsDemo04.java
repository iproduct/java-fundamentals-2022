package course.java.streams;

import java.util.stream.IntStream;

public class StreamsDemo04 {
    public static void main(String[] args) {
        int reducedParallel = IntStream.rangeClosed(1, 10000).boxed()
                .parallel()
                .reduce(0, // initial value of accumulators
                        (a, b) -> a + b, // reducer
                        (a, b) -> { //combining accumulators from different threads
                            System.out.printf("combiner called for %s and %s in thread [%s]%n", a, b,
                            Thread.currentThread().getName());
                            return a + b;
                        });
        System.out.println("Reduced with accumulator and combiner: " + reducedParallel);
    }
}
