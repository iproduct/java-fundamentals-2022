package course.java;

import course.java.service.ArticleProvider;
import course.java.service.Presenter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("course.java");
        var presenter = ctx.getBean(Presenter.class);
        presenter.present();
    }
}
