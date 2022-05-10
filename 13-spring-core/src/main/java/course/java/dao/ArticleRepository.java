package course.java.dao;

import course.java.model.Article;

import java.util.Collection;

public interface ArticleRepository extends Repository<Long, Article>{
    Collection<Article> findByTitleContaining(String titlePart);
}
