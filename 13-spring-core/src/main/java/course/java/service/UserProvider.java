package course.java.service;

import course.java.model.User;

import java.util.List;
import java.util.Optional;

public interface UserProvider {
    List<User> getUsers();

    Optional<User> getUserByUsername(String username);
}
