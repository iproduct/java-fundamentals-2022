package course.java;

import course.java.controller.BookController;
import course.java.controller.MainController;
import course.java.controller.UserController;
import course.java.dao.BookRepository;
import course.java.dao.RepoFactory;
import course.java.dao.UserRepoFactory;
import course.java.dao.UserRepository;
import course.java.dao.impl.*;
import course.java.exception.InvalidEntityDataException;
import course.java.exception.NonexistingEntityException;
import course.java.model.Role;
import course.java.model.User;
import course.java.model.UserBuilder;
import course.java.observer.Observer;
import course.java.observer.impl.EventPublisher;
import course.java.service.BookService;
import course.java.service.ObservableUserService;
import course.java.service.OrderService;
import course.java.service.UserService;
import course.java.service.impl.BookServiceImpl;
import course.java.service.impl.ObservableUserServiceImpl;
import course.java.service.impl.OrderServiceImpl;
import course.java.service.impl.UserServiceImpl;
import course.java.util.BookValidator;
import course.java.util.UserValidator;
import course.java.view.NewBookDialog;
import course.java.view.NewUserDialog;

import java.util.Properties;
import java.util.stream.Collectors;

import static course.java.dao.impl.RepoFactoryInMemoryImpl.CONFIG_REPO_ID_GENERATOR_CLASS;
import static course.java.model.MockUsers.MOCK_USERS;

public class Main {
    public static void main(String[] args) {
        // persistence layer
        RepoFactory repoFactory = RepoFactoryInMemoryImpl.getInstance();
        var props = new Properties();
        props.put(CONFIG_REPO_ID_GENERATOR_CLASS, LongIdGenerator.class.getName());
        var bookRepo = repoFactory.createBookRepository(props);
        var userRepo = repoFactory.createUserRepository(props);
        var orderRepo = repoFactory.createOrderRepository(props);

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

        // domain business logic layer
        BookService bookService = new BookServiceImpl(bookRepo, new BookValidator());
        ObservableUserService userService = new ObservableUserServiceImpl(userRepo, new UserValidator(), new EventPublisher<>());
        OrderService orderService = new OrderServiceImpl(orderRepo);

        // Observer pattern demo
        Observer<User> userEventListener = event -> System.out.println("EVENT: User created/updated: " + event.getPayload());
        userService.subscribe(userEventListener );
        User jane = null;
        try {
            jane = userService.addUser(new User("Jane", "Doe", 35, "jane", "jane123A&",
                    Role.AUTHOR, "+359 887345612"));
            jane.setPassword("joan789B#");
            userService.updateUser(jane);
        } catch (InvalidEntityDataException | NonexistingEntityException e) {
            System.out.println("Error creating/updating user: "+ e.getMessage());
        }
//        userService.unsubscribe(userEventListener);

        // State pattern - order creation and state transitions demo
        var order = orderService.createOrder(jane);
        bookService.getAllBooks().stream().limit(3)
                .forEach(product -> orderService.addProduct(order, product));


        // presentation layer - presentation logic and view
        var addBookDialog = new NewBookDialog();
        var addUserDialog = new NewUserDialog();
        BookController bookController = new BookController(bookService, addBookDialog);
        UserController userController = new UserController(userService, addUserDialog);
        MainController mainController = new MainController(bookController, userController);
        mainController.showMenu();


    }
}
