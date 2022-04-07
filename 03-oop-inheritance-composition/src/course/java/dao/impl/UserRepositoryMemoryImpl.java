package course.java.dao.impl;

import course.java.dao.IdGenerator;
import course.java.dao.Repository;
import course.java.dao.UserRepository;
import course.java.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryMemoryImpl extends RepositoryMemoryImpl<User, Long> implements UserRepository {
    private long nextId = 0;
    private Map<Long, User> entities = new HashMap<>();

    public UserRepositoryMemoryImpl(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }

    @Override
    public Optional<User> findByUsername(String username) {
//        for(var user : entities.values()) {
//            if(user.getUsername().equals(username)){
//                return Optional.of(user);
//            }
//        }
//        return Optional.empty();
        return entities.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findAny();
    }

}
