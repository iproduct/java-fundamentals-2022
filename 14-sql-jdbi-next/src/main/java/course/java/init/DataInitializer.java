package course.java.init;

import course.java.service.BookService;
import course.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

import static course.java.model.MockBooks.MOCK_BOOKS;
import static course.java.model.MockUsers.MOCK_USERS;

@Component
@Profile("!test")
public class DataInitializer implements CommandLineRunner {

    private UserService userService;

    @Autowired
    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
//        if (userService.count() == 0) {
//            userService.addUsersBatch(Arrays.asList(MOCK_USERS));
//        }
//        if (bookService.count() == 0) {
//            bookService.addBooksBatch(Arrays.asList(MOCK_BOOKS));
//        }
    }
}
