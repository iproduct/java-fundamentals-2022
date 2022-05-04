package course.java.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import course.java.dao.BookRepository;
import course.java.model.Book;
import course.java.model.User;
import course.java.util.BookValidator;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository mockBookRepo;

    @Spy
    private BookValidator bookValidator = new BookValidator();

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
    Stream<DynamicTest> generateAddBookTestsFromJsonData() {
        return Stream.of(dynamicTest("Create book with default data", () -> {
            var bookService = new BookServiceImpl(mockBookRepo, bookValidator);
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

    private Book getBookCopy(Book book){
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
