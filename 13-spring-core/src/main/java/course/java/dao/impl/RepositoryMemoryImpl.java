package course.java.dao.impl;

import course.java.dao.IdGenerator;
import course.java.dao.Repository;
import course.java.model.Identifiable;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class RepositoryMemoryImpl<K, V extends Identifiable<K>> implements Repository<K,V> {
    private Map<K, V> entities = new ConcurrentHashMap<>();
    private IdGenerator<K> idGenerator;

    public RepositoryMemoryImpl(IdGenerator<K> idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public Collection<V> findAll() {
        return null;
    }

    @Override
    public Optional<V> findById(K id) {
        return Optional.empty();
    }

    @Override
    public V create(V entity) {
        return null;
    }

    @Override
    public Optional<V> update(V entity) {
        return Optional.empty();
    }

    @Override
    public Optional<V> deleteById(K id) {
        return Optional.empty();
    }

    @Override
    public long count() {
        return 0;
    }
}
