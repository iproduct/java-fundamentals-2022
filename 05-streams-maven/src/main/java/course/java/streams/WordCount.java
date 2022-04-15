package course.java.streams;

import com.google.protobuf.MapEntry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class WordCount {
    public static final Set<String> STOP_WORDS = Set.of(
            "when", "didn't", "wasn", "y", "few", "below", "into", "there", "his", "these", "about", "if", "again",
            "too", "were", "then", "doing", "haven", "such", "this", "me", "the", "further", "whom", "having",
            "mightn", "both", "him", "she", "don", "yourselves", "all", "do", "it's", "i", "what", "needn", "had",
            "during", "weren", "wasn't", "up", "will", "just", "isn", "theirs", "own", "ll", "hadn", "aren't",
            "while", "hasn't", "themselves", "re", "in", "she's", "wouldn't", "he", "couldn't", "their", "more",
            "that'll", "ain", "t", "a", "was", "but", "couldn", "only", "been", "so", "until", "have", "from",
            "those", "or", "our", "ourselves", "as", "doesn", "over", "how", "you'd", "where", "itself", "against",
            "very", "you'll", "who", "needn't", "by", "no", "each", "its", "at", "we", "any", "ours", "mightn't",
            "through", "does", "here", "didn", "between", "some", "which", "other", "did", "don't", "hasn", "won",
            "not", "should", "haven't", "on", "now", "with", "you", "them", "himself", "under", "you've", "yours",
            "they", "shouldn", "doesn't", "of", "can", "be", "above", "weren't", "wouldn", "for", "s", "and",
            "hadn't", "herself", "nor", "should've", "am", "once", "ve", "an", "hers", "myself", "to", "her", "is",
            "shan't", "are", "same", "being", "your", "than", "it", "aren", "shan", "d", "m", "isn't", "down",
            "shouldn't", "you're", "why", "o", "yourself", "has", "my", "ma", "out", "that", "mustn't", "because",
            "after", "before", "most", "off", "mustn", "won't");
    public static final int KEYWORDS_COUNT = 20;

    public static List<Map.Entry<String, Long>> findTopKeywords(String filename, int count) throws IOException{
        Path path = Path.of(filename);
        var wordCounts = Files.lines(path).flatMap(line ->
            Arrays.stream(line.split("[\\s.?!:\\\\/\"\',;\\[\\]\\-\\d\\(\\)\\{\\}]+")))
                .filter(word -> word.length() > 2)
                .filter(not(STOP_WORDS::contains))
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));

        return wordCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(15)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        if(args.length < 1){
            System.out.println("Usage: java WordCount <filename>");
        }
        for(String filename: args){
            try {
                var topKeywords = findTopKeywords(filename, KEYWORDS_COUNT);
                System.out.printf("%s:%n", filename);
                for(var wordCount : topKeywords){
                    System.out.printf("%-15s -> %4d%n", wordCount.getKey(), wordCount.getValue());
                }
            } catch (IOException ex) {
                System.out.printf("Error indexing file '%s': %s%n", filename, ex.getMessage());
            }
        }
    }
}
