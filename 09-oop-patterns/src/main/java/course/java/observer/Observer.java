package course.java.observer;

import course.java.observer.impl.Event;

@FunctionalInterface
public interface Observer<T>{
    void update(Event<T> event);
}
