package course.java.dao;

import course.java.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
    Collection<User> findAll();
    Optional<User> findById(long id);
    Optional<User> findByUsername(String username);
    User create(User user);
    User update(User user);
    Optional<User> deleteById(long id);
    long count();
}
