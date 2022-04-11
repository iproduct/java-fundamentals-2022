package course.java.model;

import course.java.dao.Identifiable;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Book implements Identifiable<Long> {
    private static Random random;
    static {
        random = new Random();
        random.setSeed(System.nanoTime());
    }
    public static String getNextId() {
        return UUID.randomUUID().toString();
    }
    private Long id;
    private String title;
    private String author;
    private int year;
    private String publisher;
    private double price;
    private String description;
    private Set<String> tags = Collections.emptySet();

    // Overloaded constructors
    // No args constructor
    public Book() {
    }

    public Book(long id) {
        this.id = id;
    }

    // Required args constructor
    public Book(String title, String author, int year, String publisher, double price) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.publisher = publisher;
        this.price = price;

    }

    public Book(String title, String author, int year, String publisher, double price,
                String description, Set<String> tags) {
        this(title, author, year, publisher, price);
        this.description = description;
        this.tags = tags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        if(description == null){
            description = "id=" + id +
                    ", title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", publishingDate=" + year +
                    ", publisher='" + publisher + '\'' +
                    ", price=" + price;
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Book{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", author='").append(author).append('\'');
        sb.append(", year=").append(year);
        sb.append(", publisher='").append(publisher).append('\'');
        sb.append(", price=").append(price);
        sb.append(", description='").append(description).append('\'');
        sb.append(", tags=").append(tags);
        sb.append('}');
        return sb.toString();
    }

    public String format() {
        return String.format("| %4d | %-20.20s | %-20.20s | %4d | %6.2f | %-20.20s |",
                id, title, author, year, price, tags.stream().collect(Collectors.joining(", ")));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        return getId() != null ? getId().equals(book.getId()) : book.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

}
