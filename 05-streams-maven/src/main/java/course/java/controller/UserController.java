package course.java.controller;


import course.java.model.User;
import course.java.service.UserService;
import course.java.view.EntityDialog;
import course.java.view.Menu;

import java.util.List;

public class UserController {
    private UserService userService;
    private EntityDialog<User> addUserDialog;
    private Menu menu;

    public UserController(UserService userService, EntityDialog<User> addUserDialog) {
        this.userService = userService;
        this.addUserDialog = addUserDialog;
        init();
    }

    public void init() {
//        userService.loadData();
        menu = new Menu("Users Menu", List.of(
                new Menu.Option("Load Users", () -> {
                    System.out.println("Loading users ...");
                    userService.loadData();
                    return "Users loaded successfully.";
                }),
                new Menu.Option("Print All Users", () -> {
                    var users = userService.getAllUsers();
                    users.forEach(user -> System.out.println(user.format()));
                    return "Total user count: " + users.size();
                }),
                new Menu.Option("Add New User", () -> {
                    var user = addUserDialog.input();
                    var created = userService.addUser(user);
                    return String.format("User ID:%s: '%s' added successfully.",
                            created.getId(), created.getUsername());
                })
        ));
    }

    public void showMenu() {
        menu.show();
    }

}
