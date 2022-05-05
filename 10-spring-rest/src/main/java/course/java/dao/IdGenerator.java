package course.java.dao;

@FunctionalInterface
public interface IdGenerator<K> {
    K getNextId();
}
