package course.java.controller;


import course.java.model.Book;
import course.java.service.BookService;
import course.java.view.EntityDialog;
import course.java.view.Menu;
import course.java.view.NewBookDialog;

import java.util.List;

public class BookController {
    private BookService bookService;
    private EntityDialog<Book> addBookDialog;

    public BookController(BookService bookService, EntityDialog<Book> addBookDialog) {
        this.bookService = bookService;
        this.addBookDialog = addBookDialog;
    }

    public void init() {
//        bookService.loadData();
        var menu = new Menu("Books Menu", List.of(
                new Menu.Option("Load Books", () -> {
                    System.out.println("Loading books ...");
                    bookService.loadData();
                    return "Books loaded successfully.";
                }),
                new Menu.Option("Print All Books", () -> {
                    var books = bookService.getAllBooks();
                    books.forEach(book -> System.out.println(book.format()));
                    return "Total book count: " + books.size();
                }),
                new Menu.Option("Add New Books", new Menu.Command() {
                    @Override
                    public String execute() throws Exception {
                        var book = addBookDialog.input();
                        var created = bookService.addBook(book);
                        return String.format("Book ID:%s: '%s' added successfully.",
                                created.getId(), created.getTitle());
                    }
                })
        ));
        menu.show();
    }

}
