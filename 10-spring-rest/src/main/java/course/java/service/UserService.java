package course.java.service;

import course.java.exception.InvalidEntityDataException;
import course.java.exception.NonexistingEntityException;
import course.java.model.User;

import java.util.Collection;

public interface UserService {
    void loadData();
    Collection<User> getAllUsers();
    User getUserById(Long id) throws NonexistingEntityException;
    User addUser(User user) throws InvalidEntityDataException;
    User updateUser(User user) throws NonexistingEntityException, InvalidEntityDataException;
    User deleteUserById(Long id) throws NonexistingEntityException;
    long count();
}
