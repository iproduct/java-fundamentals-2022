package course.java.observer;

import course.java.observer.impl.Event;

public interface Observable<T> {
    void subscribe(Observer<T> observer);
    void unsubscribe(Observer<T> observer);
}
