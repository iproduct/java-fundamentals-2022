package course.java.nio2;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.function.Predicate.not;

public class CodeStatistics {
    public static void main(String[] args) throws IOException {
        Path projectSources = Paths.get("./src");
        var javaFilesPattern = FileSystems.getDefault()
                .getPathMatcher("glob:**/*.java");
        var numLinesWritten = Files.walk(projectSources, FileVisitOption.FOLLOW_LINKS)
                .filter(Files::isRegularFile)
//                .map(path -> path.getFileName())
                .map(path -> path.toAbsolutePath())
                .filter(javaFilesPattern::matches)
                .peek(System.out::println)
                .flatMap(path -> {
                    try {
                        return Files.lines(path, Charset.forName("utf-8"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).filter(not(String::isBlank))
                .peek(System.out::println)
                .collect(Collectors.counting());
        System.out.println("Nomber of code lines in project: " + numLinesWritten);

    }
}
