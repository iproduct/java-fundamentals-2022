package course.java.controller;

import course.java.exception.NonexistingEntityException;
import course.java.model.Credentials;
import course.java.model.User;
import course.java.service.UserService;

import java.util.Optional;

public class LoginController {
    private User loggedUser;
    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    public boolean login(Credentials credentials) {
       return login(credentials.username(), credentials.password());
    }
    public boolean login(String username, String password) {
        try {
            var user = userService.getUserByUsername(username);
            if (user.getPassword().equals(password)) {
                loggedUser = user;
                return true;
            }
        } catch (NonexistingEntityException e) {
        }
        return false;
    }

    public void logout() {
        loggedUser = null;
    }

    public Optional<User> getLoggedUser() {
        return Optional.ofNullable(loggedUser);
    }
}
