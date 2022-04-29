package course.java.service;

import course.java.model.User;
import course.java.observer.Observable;

public interface ObservableUserService extends UserService, Observable<User> {
}
