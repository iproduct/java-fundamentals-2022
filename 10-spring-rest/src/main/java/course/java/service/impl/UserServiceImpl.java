package course.java.service.impl;

import course.java.dao.UserRepository;
import course.java.dao.UserRepositoryDataJPA;
import course.java.exception.ConstraintViolationException;
import course.java.exception.InvalidEntityDataException;
import course.java.exception.NonexistingEntityException;
import course.java.model.User;
import course.java.service.UserService;
import course.java.util.EntityValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import static course.java.model.MockUsers.MOCK_USERS;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepositoryDataJPA userRepo;
    private EntityValidator<User> userValidator;

    @Autowired
    public UserServiceImpl(UserRepositoryDataJPA userRepo, EntityValidator<User> userValidator) {
        this.userRepo = userRepo;
        this.userValidator = userValidator;
    }

    @PostConstruct
    @Override
    public void loadData() {
        if(userRepo.count() == 0) {
            userRepo.saveAll(Arrays.asList(MOCK_USERS));
        }
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
        if(userRepo.findByUsername(user.getUsername()).isPresent()){
            throw new InvalidEntityDataException(
                    String.format("Username '%s' already exists.", user.getUsername()));
        }
//        try {
//            userValidator.validate(user);
//        } catch (ConstraintViolationException cve) {
//            throw new InvalidEntityDataException(
//                    String.format("Invalid user data for user '%s'.", user.getUsername()),
//                    cve
//            );
//        }
        var created = userRepo.save(user);
        log.info("Successfully created User: {}", created);
        return created;
    }

    @Override
    public User updateUser(User user) throws NonexistingEntityException, InvalidEntityDataException {
        var old = getUserById(user.getId());
        if(!old.getUsername().equals(user.getUsername())) {
            throw new InvalidEntityDataException(
                    String.format("Username '%s' can not be changed to '%s'.",
                            old.getUsername(), user.getUsername()));
        }
//        try {
//            userValidator.validate(user);
//        } catch (ConstraintViolationException cve) {
//            throw new InvalidEntityDataException(
//                    String.format("Invalid user data for user '%s'.", user.getUsername()),
//                    cve
//            );
//        }
        user.setCreated(old.getCreated());
        user.setModified(LocalDateTime.now());
        return userRepo.save(user);
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
