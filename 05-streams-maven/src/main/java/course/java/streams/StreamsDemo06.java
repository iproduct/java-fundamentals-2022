package course.java.streams;

import java.util.Random;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamsDemo06 {
    public static void main(String[] args) {
        var treeSetCollector = Collector.of(
                TreeSet<Double>::new,  // supplier
                TreeSet<Double>::add, //accumulator
                (left, right) -> {
                    System.out.printf("Combinig TreeSets %s and %s in thread [%s]%n",
                            System.identityHashCode(left), System.identityHashCode(right),
                            Thread.currentThread().getName());
                    left.addAll(right);
                    return left;
                }, //combiner
                tsResult -> tsResult.stream().map(d -> d.toString()) //finisher
                        .collect(Collectors.joining(", ")));
        var result = new Random().doubles(1000).boxed()
                .parallel()
                .collect(treeSetCollector);
        System.out.println(result);

    }
}
