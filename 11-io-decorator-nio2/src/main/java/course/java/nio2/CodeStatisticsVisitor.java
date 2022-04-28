package course.java.nio2;

import java.io.FileFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.util.function.Predicate.not;

public class CodeStatisticsVisitor implements FileVisitor {
    private static final PathMatcher javaFilesPattern = FileSystems.getDefault()
            .getPathMatcher("glob:**/*.java");
    private int totalCodeLines = 0;

    public int getTotalCodeLines() {
        return totalCodeLines;
    }

    public static void main(String[] args) throws IOException {
        Path projectPath = Paths.get(".");
        var visitor = new CodeStatisticsVisitor();
        Files.walkFileTree(projectPath, visitor);
        System.out.println("Number of code lines in project: " + visitor.getTotalCodeLines());
    }

    @Override
    public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
        var path = (Path) dir;
        System.out.println("Visiting directory: " + path);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
        var path = (Path) file;
        System.out.println("Visiting file: " + path);
        if (javaFilesPattern.matches(path)) {
            totalCodeLines +=  Files.lines(path, Charset.forName("utf-8"))
                    .filter(not(String::isBlank))
                    .collect(Collectors.counting());
        }
//        if (StreamSupport.stream(path.spliterator(), false).noneMatch(segment -> "src".equals(segment))) {
//            return FileVisitResult.SKIP_SUBTREE;
//        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
        System.out.println("File visit FAILED for file: " + file);
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
        var path = (Path) dir;
        System.out.println("Visiting complete for directory: " + path);
        if (exc != null) {
            System.out.println("Error visiting directory " + path + ": " + exc);
        }
        if (path.endsWith("src")) {
            return FileVisitResult.TERMINATE;
        }
        return FileVisitResult.CONTINUE;
    }
}
