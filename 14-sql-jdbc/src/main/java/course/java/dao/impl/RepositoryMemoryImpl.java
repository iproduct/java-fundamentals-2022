package course.java.dao.impl;

import course.java.dao.IdGenerator;
import course.java.dao.Identifiable;
import course.java.dao.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

class RepositoryMemoryImpl<V extends Identifiable<K>, K> implements Repository<V, K> {
    private long nextId = 0;
    private Map<K, V> entities = new ConcurrentHashMap<>();
    private IdGenerator<K> idGenerator;

    public RepositoryMemoryImpl(IdGenerator<K> idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public Collection<V> findAll() {
        return entities.values();
    }

    @Override
    public Optional<V> findById(K id) {
        return Optional.ofNullable(entities.get(id)); // O(1)
    }

    @Override
    public V create(V entity) {
        entity.setId(idGenerator.getNextId());
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public List<V> createBatch(List<V> entities) {
        return entities.stream().map(this::create).collect(Collectors.toList());
    }

    @Override
    public Optional<V> update(V entity) {
        entities.put(entity.getId(), entity);
        return Optional.of(entity);
    }

    @Override
    public Optional<V> deleteById(K id) {
        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public long count() {
        return entities.size();
    }
}
