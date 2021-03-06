package course.java.httpclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import course.java.model.jsonschemas.Books;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static java.lang.Math.min;

public class AsyncHttpClientGoogleBooks {
    public static final String BOOKS_JSON_FILENAME = "schemas/books.json";
    public static final String GOOGLE_BOOKS_API_URI =
            "https://www.googleapis.com/books/v1/volumes?q=";

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // read and encode search terms
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter keywords to search books:");
        String[] keywords = sc.nextLine().split("[\\+\\,\\s]+");
        var searchStr = Arrays.stream(keywords).map(String::trim).filter(kw -> kw.length() > 1)
                .collect(Collectors.joining(" "));
        var encodedStr = URLEncoder.encode(searchStr, StandardCharsets.UTF_8);
        var searchUri = URI.create(GOOGLE_BOOKS_API_URI + encodedStr);
        System.out.println("Fetching URI: " + searchUri);

        // make HTTPClient request
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(5))
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(searchUri)
                .setHeader("Accept", "application/json; utf-8")
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();
        var completableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8))
                .thenAccept(response -> {
                    System.out.println("Response status code: " + response.statusCode());
                    // unmarshal json to java objects using Jackson ObjectMapper
                    ObjectMapper mapper = new ObjectMapper();
                    Books books = null;
                    try {
                        books = mapper.readValue(response.body(), Books.class);
                        System.out.println(formatBooksData(books));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                });
        completableFuture.get();
    }

    public static String formatBooksData(Books books) {
        return books.getItems().stream().map(item -> {
            var info = item.getVolumeInfo();
            return String.format("""
                            Title:      %s
                            Subtitle:   %s
                            Authors:    %s
                            Publisher:  %s
                            Date:       %s
                            Description:%s%n
                            """,
                    info.getTitle(),
                    info.getSubtitle() != null ? info.getSubtitle() : "",
                    info.getAuthors().stream().collect(Collectors.joining(", ")),
                    info.getPublisher(),
                    info.getPublishedDate(),
                    info.getDescription() != null ?
                            info.getDescription().substring(0, min(80, info.getDescription().length())) + "..."
                            : "");
        }).collect(Collectors.joining("\n"));
    }
}
