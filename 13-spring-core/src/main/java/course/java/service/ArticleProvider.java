package course.java.service;

import course.java.model.Article;

import java.util.List;

public interface ArticleProvider {
    List<Article> getArticles();

    List<Article> getArticlesByTitleContaining(String titlePart);
}
