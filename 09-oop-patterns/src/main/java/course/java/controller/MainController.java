package course.java.controller;


import course.java.model.Credentials;
import course.java.model.Role;
import course.java.model.User;
import course.java.view.EntityDialog;
import course.java.view.Menu;

import java.awt.*;
import java.util.List;
import java.util.Set;

public class MainController {
    private BookController bookController;
    private UserController userController;
    private LoginController loginController;
    private EntityDialog<Credentials> credentialsDialog;
    private Menu menu;

    public MainController(BookController bookController, UserController userController, LoginController loginController,
                          EntityDialog<Credentials> credentialsDialog) {
        this.bookController = bookController;
        this.userController = userController;
        this.loginController = loginController;
        this.credentialsDialog = credentialsDialog;
        init();
    }

    public void init() {
        menu = new Menu("Main Menu", List.of(
                new Menu.Option("Login", () -> {
                    var credentials = credentialsDialog.input();
                    if(loginController.login(credentials)) {
                        return String.format("User '%s' logged successfully.", credentials.username());
                    }
                    return String.format("Invalid username or password.");
                }),new Menu.Option("Manage Books", () -> {
                    bookController.showMenu();
                    return "";
                }), new Menu.Option("Manage Users", () -> {
                    userController.showMenu();
                    return "";
                }, Set.of(Role.ADMIN))), loginController);
    }

    public void showMenu() {
        menu.show();
    }

}
