package course.java.dao.impl;

import course.java.dao.Repository;
import course.java.model.Identifiable;

import java.util.Collection;
import java.util.Optional;

public class RepositoryMemoryImpl<K, V extends Identifiable<K>> implements Repository<K,V> {
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
