package course.java.service.impl;

import course.java.dao.ArticleRepository;
import course.java.service.ArticleProvider;

public class DefaultProviderFactory {
    private ArticleRepository repo;

    public DefaultProviderFactory(ArticleRepository repo) {
        this.repo = repo;
    }

    ArticleProvider createDefaultArticleProvider() {
        var provider = new ArticleProviderDefaultImpl();
        provider.setArticleRepo(repo);
        return provider;
    }
}
