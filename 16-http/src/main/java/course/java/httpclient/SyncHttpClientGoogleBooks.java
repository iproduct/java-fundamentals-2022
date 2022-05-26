package course.java.httpclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import course.java.model.jsonschemas.Books;

import java.io.IOException;
import java.net.URL;

public class SyncHttpClientGoogleBooks {
    public static final String BOOKS_JSON_FILENAME = "schemas/books.json";
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        URL booksFileUrl = SyncHttpClientGoogleBooks.class.getClassLoader().getResource(BOOKS_JSON_FILENAME);
        try {
            var books = mapper.readValue(booksFileUrl, Books.class);
            books.getItems().stream().map(item -> item.getVolumeInfo().getTitle())
                    .forEach(System.out::println);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
