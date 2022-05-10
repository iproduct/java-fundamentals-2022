package course.java.service.impl;

import course.java.service.ArticleProvider;
import course.java.service.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("articlePresenter")
public class ConsoleArticlePresenter implements Presenter {
    private ArticleProvider provider;

    @Autowired
    public ConsoleArticlePresenter(ArticleProvider provider) {
        this.provider = provider;
    }

    @Override
    public void present() {
        provider.getArticles().forEach(System.out::println);
    }
}
