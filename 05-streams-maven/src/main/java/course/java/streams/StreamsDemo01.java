package course.java.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StreamsDemo01 {
    public static String extractLineComment(String line) { // extract line comment that is not quoted
        int start = line.indexOf('"');
        int startComment = line.indexOf("//");
        int end;
        while (start > -1 && start < startComment) {
            do {
                end = line.indexOf('"', start + 1);
            } while (end > 0 && line.substring(end - 1, end + 1).equals("\\\""));
            if(end == -1) {
                return "";
            }
            start = line.indexOf('"', end + 1);
            startComment = line.indexOf("//", end + 1);
        }
        if(startComment >= 0){
            return line.substring(startComment);
        }
        return "";
    }

    public static void main(String[] args) throws IOException { // printing  "own //" text in uppercase
        Path path = Paths.get("./src/main/java/course/java/streams/StreamsDemo01.java");
//        var upperLinesStream = Files.lines(path).map(line -> line.toUpperCase());
//        upperLinesStream.forEach(line -> System.out.println(line));
        var upperLinesStream = Files.lines(path).map(String::toUpperCase);
//        upperLinesStream.forEach(System.out::println);
//        upperLinesStream.map(line -> line.trim()).sorted((s1, s2) -> s1.compareToIgnoreCase(s2))
//                .forEach(System.out::println);
        upperLinesStream.map(line -> line.trim())
//                .filter(line -> line.startsWith("//"))
                .map(StreamsDemo01::extractLineComment)
                .filter(line -> line.length() > 0)
                .map(line -> line.substring(2).trim())
//                .sorted(String::compareToIgnoreCase)
                .forEach(System.out::println);
    }
}
