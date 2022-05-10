package course.java.service.impl;

import course.java.dao.ArticleRepository;
import course.java.model.Article;
import course.java.service.ArticleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleProviderDefaultImpl implements ArticleProvider {
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
