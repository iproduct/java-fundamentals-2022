package course.java.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import course.java.dao.BookRepository;
import course.java.model.Book;
import course.java.model.jsonschemas.Books;
import course.java.model.jsonschemas.Item;
import course.java.util.BookValidator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    public static final String BOOKS_JSON_FILENAME = "schemas/books.json";
    private static Books books;

    @BeforeAll
    public static void setup() {
        ObjectMapper mapper = new ObjectMapper();
        URL booksFileUrl = BookServiceImplTest.class.getClassLoader().getResource(BOOKS_JSON_FILENAME);
        try {
            books = mapper.readValue(booksFileUrl, Books.class);
            books.getItems().stream().map(item -> item.getVolumeInfo().getTitle())
                    .forEach(System.out::println);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Mock
    private BookRepository mockBookRepo;

    @Spy
    private BookValidator spyBookValidator = new BookValidator();

    @Test
    @Disabled
    void loadData() {
    }

    @Test
    @Disabled
    void getAllBooks() {
    }

    @Test
    @Disabled
    void getBookById() {
    }

    @TestFactory
    Collection<DynamicTest> generateAddBookTestsFromSampleData() {
        return List.of(dynamicTest("Create book with default data", () -> {
            var bookService = new BookServiceImpl(mockBookRepo, spyBookValidator);
            var created = getBookCopy(SAMPLE_BOOK);
            created.setId(1L);
            when(mockBookRepo.create(SAMPLE_BOOK)).thenReturn(created);

            var actual = bookService.addBook(SAMPLE_BOOK);
            assertThat(actual).extracting("id").isNotNull();
            assertThat(actual).usingRecursiveComparison()
                    .ignoringFields("id")
                    .ignoringAllOverriddenEquals()
                    .isEqualTo(SAMPLE_BOOK);
        }));
    }

    @TestFactory
    Stream<DynamicTest> generateAddBookTestsFromJsonData() {
        return books.getItems().stream().map(Item::getVolumeInfo)
                .map(v -> new Book(v.getTitle(),
                        String.join(", ", v.getAuthors()),
                        Integer.parseInt(v.getPublishedDate().split("-")[0]),
                        v.getPublisher(),
                        25.90,
                        v.getDescription(),
                        new HashSet<>(v.getCategories())
                )).map(book -> dynamicTest("Create book: " + book.getTitle(), () -> {
                    var mockRepo = mock(BookRepository.class, "Book_" + book.getTitle());
                    var spyValidator = spy(new BookValidator());
                    var bookService = new BookServiceImpl(mockRepo, spyValidator);
                    var created = getBookCopy(book);
                    created.setId(1L);
                    when(mockRepo.create(book)).thenReturn(created);

                    var actual = bookService.addBook(book);
                    assertThat(actual).extracting("id").isNotNull();
                    assertThat(actual).usingRecursiveComparison()
                            .ignoringFields("id")
                            .ignoringAllOverriddenEquals()
                            .isEqualTo(book);

                    verify(mockRepo, times(1)).create(any(Book.class));
                    verify(spyValidator, times(1)).validate(any(Book.class));
                }));
    }

    @Test
    @Disabled
    void updateBook() {
    }

    @Test
    @Disabled
    void deleteBookById() {
    }

    @Test
    @Disabled
    void count() {
    }

    private Book getBookCopy(Book book) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(book);
            return mapper.readValue(jsonString, Book.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Book SAMPLE_BOOK = new Book("Thinking in Java", "Bruce Eckel", 2001,
            "Pearson", 35.5, "Detailed introduction to Java programming.",
            Set.of("java", "intro"));

}
