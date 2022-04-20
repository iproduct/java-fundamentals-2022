package course.java.dao.impl;

import course.java.dao.*;
import course.java.exception.ReflectionException;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class RepoFactoryInMemoryImpl implements RepoFactory {
    public static final String CONFIG_REPO_ID_GENERATOR_CLASS = "repository.id.generatorclass";
    @Override
    public UserRepository createUserRepository(Properties options) {
        return getRepoInstance(options, UserRepositoryMemoryImpl.class);
    }

    @Override
    public BookRepository createBookRepository(Properties options) {
        return getRepoInstance(options, BookRepositoryMemoryImpl.class);
    }

    private static <V, K, R extends Repository<V,K>> R getRepoInstance(Properties options, Class<R> repoClass) {
        String idGenClassName =  options.getProperty(CONFIG_REPO_ID_GENERATOR_CLASS);
        try {
            Class<?> idGenClass = Class.forName(idGenClassName);
            IdGenerator<K> idGen = (IdGenerator<K>) idGenClass.getDeclaredConstructor().newInstance();
            return repoClass.getDeclaredConstructor(IdGenerator.class).newInstance(idGen);
        } catch (ClassNotFoundException e) {
            throw new ReflectionException("Error loading class: " + idGenClassName, e);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new ReflectionException("Error creating instance of class: " + idGenClassName, e);
        } catch(ClassCastException e) {
            throw new ReflectionException("Error: repository.id.generatorclass should implement IdGenerator interface", e);
        }
    }

}
