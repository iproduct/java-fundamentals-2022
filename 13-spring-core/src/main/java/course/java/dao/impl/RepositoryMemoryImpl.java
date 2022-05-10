package course.java.dao.impl;

import course.java.dao.IdGenerator;
import course.java.dao.Repository;
import course.java.model.Identifiable;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RepositoryMemoryImpl<K, V extends Identifiable<K>> implements Repository<K, V> {
    private Map<K, V> entities = new ConcurrentHashMap<>();
    private IdGenerator<K> idGenerator;

    public RepositoryMemoryImpl(IdGenerator<K> idGenerator) {
        this.idGenerator = idGenerator;
    }

    @Override
    public List<V> findAll() {
        return new ArrayList<>(entities.values());
    }

    @Override
    public Optional<V> findById(K id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public V create(V entity) {
        entity.setId(idGenerator.getNextId());
        entities.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<V> update(V entity) {
        var old = entities.get(entity.getId());
        if (old == null) {
            return Optional.empty();
        }
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
