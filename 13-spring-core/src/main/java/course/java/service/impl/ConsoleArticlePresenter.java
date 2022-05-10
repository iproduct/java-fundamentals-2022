package course.java.service.impl;

import course.java.qualifiers.Default;
import course.java.qualifiers.Mock;
import course.java.service.ArticleProvider;
import course.java.service.Presenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service("articlePresenter")
public class ConsoleArticlePresenter implements Presenter {
    private List<ArticleProvider> providers;

    @Autowired
    public ConsoleArticlePresenter(List<ArticleProvider> providers) {
        this.providers = providers;
    }

    @Override
    public void present() {
        providers.stream().flatMap(provider -> provider.getArticles().stream())
                .forEach(System.out::println);
    }
}
