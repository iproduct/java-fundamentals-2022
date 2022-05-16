package course.java.service;

import course.java.exception.InvalidEntityDataException;
import course.java.exception.NonexistingEntityException;
import course.java.model.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    Collection<User> getAllUsers();
    Collection<User> getUsersByLastName(String lastNamePart);
    User getUserById(Long id) throws NonexistingEntityException;
    User addUser(User user) throws InvalidEntityDataException;
    List<User> addUsersBatch(List<User> users);
    User updateUser(User user) throws NonexistingEntityException, InvalidEntityDataException;
    User deleteUserById(Long id) throws NonexistingEntityException;
    long count();

}
