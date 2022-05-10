package course.java.dao;

import course.java.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends Repository<Long, User> {
    Optional<User> findByUsername(String username);
}
