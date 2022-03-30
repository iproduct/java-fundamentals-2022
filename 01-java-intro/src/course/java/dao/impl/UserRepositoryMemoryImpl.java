package course.java.dao.impl;

import course.java.dao.UserRepository;
import course.java.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryMemoryImpl implements UserRepository {
    private long nextId = 0;
    private Map<Long, User> entities = new HashMap<>();

    @Override
    public Collection<User> findAll() {
        return entities.values();
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(entities.get(id)); // O(1)
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

    @Override
    public User create(User user) {
        user.setId(++nextId);
        entities.put(user.getId(), user);
        return user;
    }

    @Override
    public User update(User user) {
        entities.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> deleteById(long id) {
        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public long count() {
        return entities.size();
    }
}
