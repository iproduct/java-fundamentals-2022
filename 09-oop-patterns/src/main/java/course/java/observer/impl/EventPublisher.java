package course.java.observer.impl;

import course.java.dao.UserRepository;
import course.java.model.User;
import course.java.observer.Observable;
import course.java.observer.Observer;
import course.java.util.EntityValidator;

import java.util.LinkedList;
import java.util.List;

public class EventPublisher<T> implements Observable<T> {
    private List<Observer<T>> userObservers = new LinkedList<>();


    @Override
    public void subscribe(Observer<T> observer) {
        userObservers.add(observer);
    }

    @Override
    public void unsubscribe(Observer<T> observer) {
        userObservers.remove(observer);
    }

    public void notfyObservers(Event<T> event) {
        for(var observer : userObservers) {
            observer.update(event);
        }
    }
}
