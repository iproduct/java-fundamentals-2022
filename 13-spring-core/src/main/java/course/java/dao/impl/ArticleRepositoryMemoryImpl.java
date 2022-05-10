package course.java.dao.impl;

import course.java.dao.ArticleRepository;
import course.java.dao.IdGenerator;
import course.java.model.Article;

import java.util.Collection;
import java.util.stream.Collectors;

public class ArticleRepositoryMemoryImpl extends RepositoryMemoryImpl<Long, Article>
        implements ArticleRepository {
    public ArticleRepositoryMemoryImpl(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }

    @Override
    public Collection<Article> findByTitleContaining(String titlePart) {
        return findAll().stream().filter(article -> article.getTitle().contains(titlePart))
                .collect(Collectors.toList());
    }
}
