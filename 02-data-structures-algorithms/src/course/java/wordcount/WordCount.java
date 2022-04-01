package course.java.wordcount;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class WordCount {
    public static final int KEYWORDS_COUNT = 20;

    public static List<Map.Entry<String, Integer>> findTopKeywords(String filename, int count) throws IOException{
        Map<String, Integer> wordCounts = new HashMap<>();
        Path path = Path.of(filename);
        Files.lines(path).forEach(line -> {
            var words = line.split("[\\s.?!:\\\\/\"\',;\\[\\]\\-\\d\\(\\)\\{\\}]+");
            for(var word : words) {
                if(word.length() < 2){
                    continue;
                }

            }
            System.out.println(Arrays.toString(words));
        });
        return Collections.emptyList();
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
                    System.out.printf("%20s -> %4d", wordCount.getKey(), wordCount.getValue());
                }
            } catch (IOException ex) {
                System.out.printf("Error indexing file '%s': %s%n", filename, ex.getMessage());
            }
        }
    }
}
