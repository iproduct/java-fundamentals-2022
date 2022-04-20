package course.java;

import course.java.controller.BookController;
import course.java.controller.MainController;
import course.java.controller.UserController;
import course.java.dao.BookRepository;
import course.java.dao.UserRepository;
import course.java.dao.impl.BookRepositoryMemoryImpl;
import course.java.dao.impl.LongIdGenerator;
import course.java.dao.impl.UserRepositoryMemoryImpl;
import course.java.model.Role;
import course.java.model.User;
import course.java.model.UserBuilder;
import course.java.service.BookService;
import course.java.service.UserService;
import course.java.service.impl.BookServiceImpl;
import course.java.service.impl.UserServiceImpl;
import course.java.util.BookValidator;
import course.java.util.UserValidator;
import course.java.view.NewBookDialog;
import course.java.view.NewUserDialog;

import static course.java.model.MockUsers.MOCK_USERS;

public class Main {
    public static void main(String[] args) {
        UserRepository userRepo = new UserRepositoryMemoryImpl(new LongIdGenerator());
        for (var user : MOCK_USERS) {
            userRepo.create(user);
        }
        var newUser = new UserBuilder().setName("Stefan Dimitrov").setAge(43)
                .setUsername("stefan").setPassword("stef123").setRole(Role.READER)
                .build();
        userRepo.create(newUser);
        var ivanOptional = userRepo.findByUsername("ivan");
        if(ivanOptional.isPresent()) {
            var ivan = ivanOptional.get();
            ivan.setLastName("Hristov");
            ivan.setPassword("new_pass");
            userRepo.update(ivan);
        }
        userRepo.deleteById(2L);
        for (User user : userRepo.findAll()) {
            System.out.println(user.format());
        }

        // persitence layer
        BookRepository bookRepo = new BookRepositoryMemoryImpl(new LongIdGenerator());
        // domain business logic layer
        BookService bookService = new BookServiceImpl(bookRepo, new BookValidator());
        UserService userService = new UserServiceImpl(userRepo, new UserValidator());
        // presentation layer - presentation logic and view
        var addBookDialog = new NewBookDialog();
        var addUserDialog = new NewUserDialog();
        BookController bookController = new BookController(bookService, addBookDialog);
        UserController userController = new UserController(userService, addUserDialog);
        MainController mainController = new MainController(bookController, userController);
        mainController.showMenu();
    }
}
