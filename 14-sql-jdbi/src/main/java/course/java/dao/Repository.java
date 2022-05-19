package course.java.dao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface Repository<V, K> {
    Collection<V> findAll();
    Optional<V> findById(K id);
    V create(V user);
    List<V> createBatch(List<V> entities);
    Optional<V> update(V entity);
    Optional<V> deleteById(K id);
    long count();
}
