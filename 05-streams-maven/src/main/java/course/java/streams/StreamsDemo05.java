package course.java.streams;

import course.java.model.Book;

import java.util.Arrays;
import java.util.Map;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static course.java.model.MockBooks.MOCK_BOOKS;

public class StreamsDemo05 {
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

    }
}
