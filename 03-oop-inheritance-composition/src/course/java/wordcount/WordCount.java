package course.java.wordcount;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

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

    public static List<Map.Entry<String, Integer>> findTopKeywords(String filename, int count) throws IOException{
        Map<String, Integer> wordCounts = new HashMap<>();
        Path path = Path.of(filename);
        Files.lines(path).forEach(line -> {
            var words = line.split("[\\s.?!:\\\\/\"\',;\\[\\]\\-\\d\\(\\)\\{\\}]+");
            for(var word : words) {
                word = word.toLowerCase();
                if(word.length() < 2 || STOP_WORDS.contains(word)){
                    continue;
                }
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
//            System.out.println(Arrays.toString(words));
        });
        var results = new ArrayList<>(wordCounts.entrySet());
        results.sort(new WordCountComparator());
        return results.subList(0, count);
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
