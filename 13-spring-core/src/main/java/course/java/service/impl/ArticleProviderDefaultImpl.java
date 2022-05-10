package course.java.service.impl;

import course.java.dao.ArticleRepository;
import course.java.model.Article;
import course.java.service.ArticleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ArticleProviderDefaultImpl implements ArticleProvider {
    public static final List<Article> DEFAULT_ARTICLES = List.of(
 new Article("Spring Data JPA Intro", "Spring Data JPA is easy ...",
                    "Trayan Iliev", Set.of("spring", "mvc", "boot", "intro")),
            new Article("Spring Data JPA and Hibernate", "Hibernate provides powerful ORM implementation ...",
                    "Georgi Petrov", Set.of("hibernate", "performance")),
            new Article( "Spring Data is Going Reactive", "Spring Data provides reactive db integrations for a number of databases ...",
                    "Trayan Iliev", Set.of("spring", "boot", "intro"))
    );
    private ArticleRepository articleRepo;

    @Autowired
    public ArticleProviderDefaultImpl(ArticleRepository articleRepo) {
        this.articleRepo = articleRepo;
    }

    @Override
    public List<Article> getArticles() {
        return articleRepo.findAll();
    }

    @Override
    public List<Article> getArticlesByTitleContaining(String titlePart) {
        return articleRepo.findByTitleContaining(titlePart);
    }
}
