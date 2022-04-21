package course.java.observer;

@FunctionalInterface
public interface Observer<T>{
    void update(Event<T> event);
}
