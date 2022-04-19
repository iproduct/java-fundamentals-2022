package course.java.service.impl;


import course.java.dao.BookRepository;
import course.java.exception.ConstraintViolationException;
import course.java.exception.InvalidEntityDataException;
import course.java.exception.NonexistingEntityException;
import course.java.model.Book;
import course.java.service.BookService;
import course.java.util.BookValidator;

import java.util.Arrays;
import java.util.Collection;

import static course.java.model.MockBooks.MOCK_BOOKS;

public class BookServiceImpl implements BookService {
    private final BookRepository bookRepo;
    private final BookValidator bookValidator;

    public BookServiceImpl(BookRepository bookRepository, BookValidator bookValidator) {
        this.bookRepo = bookRepository;
        this.bookValidator = bookValidator;
    }

    @Override
    public void loadData() {
        Arrays.stream(MOCK_BOOKS).forEach(bookRepo::create);
    }

    @Override
    public Collection<Book> getAllBooks() {
        return bookRepo.findAll();
    }
    @Override
    public Book getBookById(Long id) throws NonexistingEntityException {
        var book = bookRepo.findById(id);
        return book.orElseThrow(() -> new NonexistingEntityException("Book with ID='" + id + "' does not exist."));
    }

    @Override
    public Book addBook(Book book) throws InvalidEntityDataException {
        try {
            bookValidator.validate(book);
        } catch (ConstraintViolationException ex) {
            throw new InvalidEntityDataException(
                    String.format("Error creating book '%s'", book.getTitle()),
                    ex
            );
        }
        var newBook = bookRepo.create(book);
//        bookRepo.save();
        return newBook;
    }

    @Override
    public Book updateBook(Book book) throws NonexistingEntityException, InvalidEntityDataException {
        try {
            bookValidator.validate(book);
        } catch (ConstraintViolationException ex) {
            throw new InvalidEntityDataException(
                    String.format("Error creating book '%s'", book.getTitle()),
                    ex
            );
        }
        var updated = bookRepo.update(book);
//        bookRepo.save();
        return updated;
    }

    @Override
    public Book deleteBookById(Long id) throws NonexistingEntityException {
        var deleted = bookRepo.deleteById(id);
//        bookRepo.save();
        return deleted.orElseThrow(() -> new NonexistingEntityException("Book with ID='" + id + "' does not exist."));
    }

    @Override
    public long count() {
        return bookRepo.count();
    }
}
