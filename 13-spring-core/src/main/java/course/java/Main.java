package course.java;

import course.java.service.ArticleProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("course.java");
        var provider = ctx.getBean(ArticleProvider.class);
        provider.getArticles().forEach(System.out::println);
    }
}
