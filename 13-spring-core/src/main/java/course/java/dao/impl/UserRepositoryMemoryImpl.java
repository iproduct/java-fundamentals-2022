package course.java.dao.impl;

import course.java.dao.UserRepository;
import course.java.dao.IdGenerator;
import course.java.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryMemoryImpl extends RepositoryMemoryImpl<Long, User>
        implements UserRepository {
    public UserRepositoryMemoryImpl(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return findAll().stream().filter(user -> user.getUsername().equals(username))
                .findAny();

    }
}
