package course.java.dao;

import java.util.Collection;
import java.util.Optional;

public interface Repository<V, K> {
    Collection<V> findAll();
    Optional<V> findById(K id);
    V create(V user);
    V update(V user);
    Optional<V> deleteById(K id);
    long count();
}
