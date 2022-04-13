package course.java.controller;


import course.java.view.Menu;

import java.util.List;

public class MainController {
    private BookController bookController;
    private UserController userController;
    private  Menu menu;

    public MainController(BookController bookController, UserController userController) {
        this.bookController = bookController;
        this.userController = userController;
        init();
    }

    public void init() {
        menu = new Menu("Main Menu", List.of(new Menu.Option("Manage Books", () -> {
            bookController.showMenu();
            return "";
        }), new Menu.Option("Manage Users", () -> {
            userController.showMenu();
            return "";
        })));
    }

    public void showMenu() {
        menu.show();
    }

}
