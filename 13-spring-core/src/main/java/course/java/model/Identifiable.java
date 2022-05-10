package course.java.model;

public interface Identifiable<K> {
    K getId();

    void setId(K id);
}
