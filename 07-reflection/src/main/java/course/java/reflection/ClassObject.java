package course.java.reflection;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;

interface Printable {
    void print();
}

interface Formattable {
    String format(int pageNumber);
}

interface Pageable {
    List<String> getPages();
}

class Document implements Printable {
    private String title;
    private String text;

    public Document() {
    }

    public Document(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void print() {
        System.out.println(title);
        System.out.println("=============================");
        System.out.println(text);
        System.out.println();
    }
}

class FancyDocument extends Document implements Formattable, Pageable {

    @Override
    public String format(int pageNumber) {
        StringJoiner sj = new StringJoiner("\n",
                "Page " + pageNumber + "\n------------------------------",
                "\n------------------------------");

        return null;
    }

    @Override
    public List<String> getPages() {
        AtomicInteger count = new AtomicInteger();
        return Arrays.stream(getText().split("\n")).collect(
                        Collectors.groupingBy(line -> count.getAndIncrement() / 3)
                ).entrySet().stream()
                .map(entry -> entry.getValue().stream().collect(Collectors.joining("\n")))
                .collect(Collectors.toList());
    }
}

public class ClassObject {
}
