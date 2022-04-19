package course.java.dao.impl;

import course.java.dao.BookRepository;
import course.java.dao.IdGenerator;
import course.java.model.Book;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Collections.disjoint;

public class BookRepositoryMemoryImpl extends RepositoryMemoryImpl<Book, Long> implements BookRepository {

    public BookRepositoryMemoryImpl(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }

    @Override
    public Collection<Book> findByTitle(String titlePart) {
        return findAll().stream().filter(book -> book.getTitle().contains(titlePart)).
                collect(Collectors.toList());
    }

    @Override
    public Collection<Book> findByTags(Set<String> tags) {
        return findAll().stream().filter(book -> disjoint(book.getTags(), tags)).collect(Collectors.toList());
    }
}
