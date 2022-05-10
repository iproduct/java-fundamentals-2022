package course.java.service.impl;

import course.java.dao.ArticleRepository;
import course.java.model.Article;
import course.java.qualifiers.Mock;
import course.java.service.ArticleProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
//@Mock
//@Order(2)
//@Service("mockProvider")
public class ArticleProviderMockImpl implements ArticleProvider{
    public static final List<Article> MOCK_ARTICLES = List.of(
            new Article("Intro to Spring", "Spring MVC is easy ...",
                    "Trayan Iliev", Set.of("spring", "mvc", "boot", "intro")),
            new Article("Hibernate Performance", "Hibernate provides powerful ORM implementation ...",
                    "Georgi Petrov", Set.of("hibernate", "performance")),
            new Article( "Spring Boot is Easy", "Spring Boot makes bootstrapping new Spring projects easy ...",
                    "Trayan Iliev", Set.of("spring", "boot", "intro"))
    );

    @Override
    public List<Article> getArticles() {
        return MOCK_ARTICLES;
    }

    @Override
    public List<Article> getArticlesByTitleContaining(String titlePart) {
        return MOCK_ARTICLES.stream()
                .filter(art -> art.getTitle().contains(titlePart))
                .collect(Collectors.toList());
    }
}
