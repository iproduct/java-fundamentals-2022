package course.java.dao.impl;

import course.java.dao.IdGenerator;
import course.java.dao.UserRepoFactory;
import course.java.dao.UserRepository;
import course.java.exception.ReflectionException;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class UserRepoFactoryInMemoryImpl implements UserRepoFactory {
    @Override
    public UserRepository createUserRepository(Properties options) {
        String idGenClassName =  options.getProperty("repository.id.generatorclass");
        try {
            Class<?> idGenClass = Class.forName(idGenClassName);
            IdGenerator idGen = (IdGenerator) idGenClass.getDeclaredConstructor().newInstance();
            return new UserRepositoryMemoryImpl(idGen);
        } catch (ClassNotFoundException e) {
            throw new ReflectionException("Error loading class: " + idGenClassName, e);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            throw new ReflectionException("Error creating instance of class: " + idGenClassName, e);
        } catch(ClassCastException e) {
            throw new ReflectionException("Error: repository.id.generatorclass should implement IdGenerator interface", e);
        }
    }
}
