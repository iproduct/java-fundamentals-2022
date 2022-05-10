package course.java;

import course.java.service.ArticleProvider;
import course.java.service.Presenter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("course.java");
        var articlePresenter = ctx.getBean("articlePresenter", Presenter.class);
        articlePresenter.present();
        var userPresenter = ctx.getBean("userPresenter", Presenter.class);
        userPresenter.present();
    }
}
