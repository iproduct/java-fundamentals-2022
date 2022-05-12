package course.java.service;

import course.java.exception.InvalidEntityDataException;
import course.java.exception.NonexistingEntityException;
import course.java.model.Book;
import course.java.model.User;

import java.util.Collection;
import java.util.List;

public interface BookService {
    void loadData();
    Collection<Book> getAllBooks();
    Book getBookById(Long id) throws NonexistingEntityException;
    Book addBook(Book book) throws InvalidEntityDataException;
//    List<Book> addBooksBatch(List<Book> books);
    Book updateBook(Book book) throws NonexistingEntityException, InvalidEntityDataException;
    Book deleteBookById(Long id) throws NonexistingEntityException;
    long count();
}
