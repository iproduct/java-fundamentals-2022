package course.java.service.impl;

import course.java.dao.UserRepository;
import course.java.dao.UserRepositoryJdbi;
import course.java.exception.InvalidEntityDataException;
import course.java.exception.NonexistingEntityException;
import course.java.exception.PersistenceException;
import course.java.model.User;
import course.java.service.UserService;
import course.java.util.EntityValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepositoryJdbi userRepo;
    private EntityValidator<User> userValidator;

    @Autowired
    public UserServiceImpl(UserRepositoryJdbi userRepo, EntityValidator<User> userValidator) {
        this.userRepo = userRepo;
        this.userValidator = userValidator;
    }


    @Override
    public Collection<User> getAllUsers() {
        return userRepo.findAll();
    }


    @Override
    public User getUserById(Long id) throws NonexistingEntityException {
        return userRepo.findById(id).orElseThrow(() ->
                new NonexistingEntityException(
                        String.format("User with ID='%s' does not exist.", id)));
    }

    @Override
    public User addUser(User user) throws InvalidEntityDataException {
//        if(userRepo.findByUsername(user.getUsername()).isPresent()){
//            throw new InvalidEntityDataException(
//                    String.format("Username '%s' already exists.", user.getUsername()));
//        }
        long id = userRepo.create(user);
        if(id > 0) {
            user.setId(id);
            log.info("Successfully created User: {}", user);
            return user;
        }
        log.error("Error creating user: {}", user);
        throw new PersistenceException("Error creating user: " + user);
    }

    @Override
//    @Transactional
    public List<User> addUsersBatch(List<User> users) {
        long[] ids = userRepo.createBatch(users);
        if (Arrays.stream(ids).allMatch(i -> i > 0)) {
            for(int i = 0; i < ids.length; i++) {
                users.get(i).setId(ids[i]);
            }
            log.info("Successfully created {} users in batch.", users.size());
            return users;
        }
        log.error("Error creating users in batch: {}", users);
        throw new PersistenceException("Error creating users in batch: " + users);
    }

    @Override
    public User updateUser(User user) throws NonexistingEntityException, InvalidEntityDataException {
        var old = getUserById(user.getId());
        if (!old.getUsername().equals(user.getUsername())) {
            throw new InvalidEntityDataException(
                    String.format("Username '%s' can not be changed to '%s'.",
                            old.getUsername(), user.getUsername()));
        }
        user.setCreated(old.getCreated());
        user.setModified(LocalDateTime.now());
        if(userRepo.update(user)) {
            log.info("Successfully updated User: {}", user);
            return user;
        }
        log.error("Error updating user: {}", user);
        throw new InvalidEntityDataException(String.format("Error updating user '%s'", user.getUsername()));
    }

    @Override
    public User deleteUserById(Long id) throws NonexistingEntityException {
        var old = userRepo.findById(id).orElseThrow(() ->
                new NonexistingEntityException(
                        String.format("User with ID='%s' does not exist.", id)));
        userRepo.deleteById(id);
        return old;
    }

    @Override
    public long count() {
        return userRepo.count();
    }
}
