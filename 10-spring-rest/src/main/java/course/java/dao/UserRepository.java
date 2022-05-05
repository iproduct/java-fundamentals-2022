package course.java.dao;

import course.java.model.User;

import java.util.Optional;

public interface UserRepository extends Repository<User, Long>{
    Optional<User> findByUsername(String username);
}
