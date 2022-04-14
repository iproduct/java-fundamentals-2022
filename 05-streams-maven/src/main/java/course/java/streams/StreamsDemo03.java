package course.java.streams;

import course.java.model.Book;

import java.util.Arrays;
import java.util.Map;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static course.java.model.MockBooks.MOCK_BOOKS;

public class StreamsDemo03 {
    public static void main(String[] args) {
        var words = Arrays.stream(MOCK_BOOKS)
                .map(Book::getTitle)
                .distinct()
                .flatMap(title -> Arrays.stream(title.split("\\W+")))
                .collect(Collectors.collectingAndThen(
                        Collectors.groupingBy(String::toLowerCase, Collectors.counting()),
                        wordCountMap -> wordCountMap.entrySet().stream()
                                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
//                                .findAny()
//                                .findFirst()
                                .limit(2).collect(Collectors.toList())
                        )
                );
//                .sorted()
//                .collect(Collectors.toList());
        System.out.println(words);

        var results = Arrays.stream(MOCK_BOOKS)
                .mapToDouble(Book::getPrice)
                .reduce(0, (acc, newVal) -> acc + newVal);
//                .summaryStatistics();
        System.out.println(results);
        var product = IntStream.of(1, 12, 3, 45, 72, 8, 15)
                .reduce(1, (acc, newVal) -> acc * newVal);
        var numbersString = IntStream.of(1, 12, 3, 45, 72, 8, 15)
                .mapToObj(Integer::toString)
                .collect(Collectors.joining(", "));
//                .reduce((acc, newVal) -> acc + ", " + newVal);
        var count = IntStream.of(1, 12, 3, 45, 72, 8, 15)
                .reduce(0, (acc, newVal) -> acc + 1);
        System.out.println(count);

        DoubleStream.generate(Math::random)
                .map(r -> (int)(r * 10000 + 0.5) / 100.0)
                .limit(10)
                .forEach(System.out::println);

        IntPredicate isPrime = n -> {
            int limit = (int) Math.sqrt(n);
            for(int i = 2; i < limit; i++){
                if(n % i == 0){
                    return false;
                }
            }
            return true;
        };

        var LIMIT = 10000000;
        var start=System.nanoTime();
        int[] a = IntStream.iterate(2, n -> n + 1)
                .parallel()
                .filter(isPrime)
                .takeWhile(n -> n < LIMIT)
                .toArray();
        var end=System.nanoTime();
        System.out.printf("Generating prime numbers < %d - time: %f ms%n", LIMIT, (end-start) / 1000000.0);
        System.out.println("Sum = " + Arrays.stream(a).sum());
    }
}
