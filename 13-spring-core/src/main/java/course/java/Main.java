package course.java;

import course.java.config.AppConfig;
import course.java.service.ArticleProvider;
import course.java.service.Presenter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("course.java");
//        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
        var articlePresenter = ctx.getBean("articlePresenter", Presenter.class);
        articlePresenter.present();
        var userPresenter = ctx.getBean("userPresenter", Presenter.class);
        userPresenter.present();
    }
}
