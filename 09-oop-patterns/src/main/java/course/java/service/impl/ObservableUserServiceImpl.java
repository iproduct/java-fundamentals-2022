package course.java.service.impl;

import course.java.dao.UserRepository;
import course.java.exception.ConstraintViolationException;
import course.java.exception.InvalidEntityDataException;
import course.java.exception.NonexistingEntityException;
import course.java.model.User;
import course.java.observer.Observer;
import course.java.observer.impl.Event;
import course.java.service.ObservableUserService;
import course.java.service.UserService;
import course.java.util.EntityValidator;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static course.java.model.MockUsers.MOCK_USERS;

@Slf4j
public class ObservableUserServiceImpl extends  UserServiceImpl implements ObservableUserService {
    private UserRepository userRepo;
    private EntityValidator<User> userValidator;
    private List<Observer<User>> userObservers = new LinkedList<>();

    public ObservableUserServiceImpl(UserRepository userRepo, EntityValidator<User> userValidator) {
        super(userRepo, userValidator);
    }

    @Override
    public void subscribe(Observer<User> observer) {
        userObservers.add(observer);
    }

    @Override
    public void unsubscribe(Observer<User> observer) {
        userObservers.remove(observer);
    }

    @Override
    public void notfyObservers(Event<User> event) {
        for(var observer : userObservers) {
            observer.update(event);
        }
    }
}
