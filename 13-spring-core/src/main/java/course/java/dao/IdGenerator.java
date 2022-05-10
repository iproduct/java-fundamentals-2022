package course.java.dao;

public interface IdGenerator<K> {
    K getNextId();
}
