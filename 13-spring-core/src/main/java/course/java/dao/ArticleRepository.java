package course.java.dao;

import course.java.model.Article;

import java.util.Collection;
import java.util.List;

public interface ArticleRepository extends Repository<Long, Article> {
    List<Article> findByTitleContaining(String titlePart);
}
