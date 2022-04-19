package course.java.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
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
    String formatPage(int pageNumber, String page);
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
    public FancyDocument() {
    }

    public FancyDocument(String title, String text) {
        super(title, text);
    }

    @Override
    public String formatPage(int pageNumber, String page) {
        StringJoiner sj = new StringJoiner("\n",
                "Page " + pageNumber + "\n------------------------------\n",
                "\n------------------------------\n");
        sj.add(page);

        return sj.toString();
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
    public static void main(String[] args) {
        System.out.println(Integer.class);
        System.out.println(Integer.TYPE);
        Class cls = null;
        try {
            cls = Class.forName("course.java.reflection.FancyDocument");
//            cls = Class.forName(FancyDocument.class.getName());
            System.out.println(cls.getName());
            System.out.println("Superclass: " + cls.getSuperclass());
            System.out.println(Arrays.toString(cls.getInterfaces()));
            System.out.println(Arrays.toString(cls.getSuperclass().getInterfaces()));
            Method[] methods = cls.getMethods();
            Constructor[] constructors = cls.getConstructors();
            printMethods(methods);
            printConstructors(constructors);
            System.out.println();

            FancyDocument doc = (FancyDocument) cls.newInstance();
            doc.setTitle("Hello Java Reflection");
            doc.setText("Line 1\nLine 2\nLine 3\nLine 4\nLine 5\nLine 6\nLine 7\nLine 8\nLine 9\n");
            var pages = doc.getPages();
            for(int i = 0; i < pages.size(); i ++) {
                System.out.println(doc.formatPage(i, pages.get(i)));
            }
            System.out.println(doc.getClass().getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printMethods(Method[] methods){
        if(methods.length < 1) return;
        System.out.printf("\nClass '%s' methods:%n------------------------------------%n",
                methods[0].getDeclaringClass().getSimpleName());
        Arrays.stream(methods).forEach(System.out::println);
    }
    public static void printConstructors(Constructor[] constructors){
        if(constructors.length < 1) return;
        System.out.printf("\nClass '%s' constructors:%n------------------------------------%n",
                constructors[0].getDeclaringClass().getSimpleName());
        Arrays.stream(constructors).forEach(System.out::println);
    }
}
