package course.java.view;

import course.java.model.Book;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NewBookDialog implements EntityDialog<Book> {
    public static Scanner sc = new Scanner(System.in);

    @Override
    public Book input() {
        var book = new Book();
        while (book.getTitle() == null) {
            System.out.println("Tile:");
            var ans = sc.nextLine();
            if (ans.length() < 3) {
                System.out.println("Error: The book title should be at least 3 characters long.");
            } else {
                book.setTitle(ans);
            }
        }
        while (book.getAuthor() == null) {
            System.out.println("Authors (comma separated):");
            var ans = sc.nextLine();
            if (ans.length() < 3) {
                System.out.println("Error: The book authors should be at least 3 characters long.");
            } else {
                book.setAuthor(ans);
            }
        }
        while (book.getYear() == 0) {
            System.out.println("Publishing year [ex. 2018]:");
            String ans;
            int year = 0;
            ans = sc.nextLine();
            try {
                year = Integer.parseInt(ans);
            } catch (NumberFormatException ex) {
                System.out.println("Error: Invalid year format - numbers only.");
            }
            if (year < 1500 || year > LocalDate.now().getYear()) {
                System.out.println("Error: Invalid year - try again.");
            } else {
                book.setYear(year);
            }
        }

        while (book.getPrice() == 0.0) {
            System.out.println("Price [ex. 45.70]):");
            String ans;
            double price = 0.0;
            ans = sc.nextLine();
            try {
                price = Double.parseDouble(ans);
            } catch (NumberFormatException ex) {
                System.out.println("Error: Invalid year format - numbers only.");
            }
            if (price < 0 || price > 1000) {
                System.out.println("Error: Invalid price - try again.");
            } else {
                book.setPrice(price);
            }
        }

        while (book.getPublisher() == null) {
            System.out.println("Publisher [optional - press <Enter> to skip]:");
            var ans = sc.nextLine();
            if (ans.length() == 0) {
                break;
            }
            if (ans.length() < 3) {
                System.out.println("Error: The book publisher should be at least 3 characters long.");
            } else {
                book.setPublisher(ans);
            }
        }

        while (book.getDescription() == null) {
            System.out.println("Description [optional - press <Enter> to skip]:");
            var ans = sc.nextLine();
            if (ans.length() == 0) {
                break;
            }
            if (ans.length() < 3) {
                System.out.println("Error: The book publisher should be at least 3 characters long.");
            } else {
                book.setDescription(ans);
            }
        }

        do {
            System.out.println("Tags (comma separated):");
            var ans = sc.nextLine().trim();
            if (ans.length() == 0) {
                break;
            }
            var tags = Arrays.stream(ans.split(","))
                    .map(tag -> tag.trim())
                    .collect(Collectors.toSet());

            book.setTags(tags);
        } while (book.getTags().size() == 0);

        return book;
    }
}
