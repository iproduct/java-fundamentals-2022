package course.java.dao.impl;

import course.java.dao.ArticleRepository;
import course.java.dao.IdGenerator;
import course.java.model.Article;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Repository("articleRepository")
public class ArticleRepositoryMemoryImpl extends RepositoryMemoryImpl<Long, Article>
        implements ArticleRepository {
    public ArticleRepositoryMemoryImpl(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }

    @Override
    public List<Article> findByTitleContaining(String titlePart) {
        return findAll().stream().filter(article -> article.getTitle().contains(titlePart))
                .collect(Collectors.toList());
    }
}
