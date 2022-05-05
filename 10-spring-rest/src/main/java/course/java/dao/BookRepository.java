package course.java.dao;

import course.java.model.Book;

import java.util.Collection;
import java.util.Set;

/**
 * Public interface for managing lifecycle of Book objects
 */
public interface BookRepository extends Repository<Book, Long> {
    Collection<Book> findByTitle(String titlePart);
    Collection<Book> findByTags(Set<String> tags);
}
