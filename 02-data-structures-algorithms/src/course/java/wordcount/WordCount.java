package course.java.wordcount;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class WordCount {
    public static final int KEYWORDS_COUNT = 20;

    public static List<Map.Entry<String, Integer>> findTopKeywords(String filename, int count) throws IOException{
        // TODO
        return Collections.emptyList();
    }

    public static void main(String[] args) {
        if(args.length < 1){
            System.out.println("Usage: java WordCount <filename>");
        }
        for(String filename: args){
            try {
                var topKeywords = findTopKeywords(filename, KEYWORDS_COUNT);
                System.out.printf("File %s:%n", filename);
                for(var wordCount : topKeywords){
                    System.out.printf("%20s -> %4d", wordCount.getKey(), wordCount.getValue());
                }
            } catch (IOException ex) {
                System.out.printf("Error indexing file '%s': %s%n", filename, ex.getMessage());
            }
        }
    }
}
