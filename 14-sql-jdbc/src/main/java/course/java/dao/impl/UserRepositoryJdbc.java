package course.java.dao.impl;

import course.java.dao.UserRepository;
import course.java.exception.PersistenceException;
import course.java.model.User;
import course.java.util.JdbcUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;

@Slf4j
public class UserRepositoryJdbc implements UserRepository {
    private static final String FIND_ALL_USERS = "SELECT * FROM users; ";
    private static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE id=?; ";
    public static final String INSERT_NEW_USER =
            "INSERT INTO `users` " +
                    "(`firstName`, `lastName`, `age`, `phone`, `username`, `password`, `role`, `active`, `created`, `modified`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private Connection connection;

    public UserRepositoryJdbc(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Collection<User> findAll() {
        try (var ps = connection.prepareStatement(FIND_ALL_USERS)) {
            var rs = ps.executeQuery();
            var users = JdbcUtil.getEntities(rs, User.class);
            return users;
        } catch (SQLException e) {
            throw new PersistenceException("Error executing database query", e);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (var ps = connection.prepareStatement(FIND_USER_BY_ID)) {
            ps.setLong(1, id);
            var rs = ps.executeQuery();
            var users = JdbcUtil.getEntities(rs, User.class);
            if(users.size() > 0) {
                return Optional.of(users.get(0));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new PersistenceException("Error executing database query", e);
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User create(User user) {
        try (var ps = connection.prepareStatement(INSERT_NEW_USER, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setInt(3, user.getAge());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getUsername());
            ps.setString(6, user.getPassword());
            ps.setString(7, user.getRole().name());
            ps.setBoolean(8, user.isActive());
            ps.setTimestamp(9, Timestamp.valueOf(user.getCreated()));
            ps.setTimestamp(10, Timestamp.valueOf(user.getModified()));
            int numRecords = ps.executeUpdate();
            if(numRecords > 0) {
                var keys = ps.getGeneratedKeys();
                try {
                    keys.next();
                    user.setId(keys.getLong(1));
                    log.info("Successfully INSERTED {} records.", numRecords);
                    return user;
                } catch (SQLException e) {
                    throw new PersistenceException("Error fetching generated key for User: " + user.getUsername(), e);
                }
            }
            throw new PersistenceException("Error creating user'" + user.getUsername() + "' in database");
        } catch (SQLException e) {
            throw new PersistenceException("Error inserting User in database", e);
        }
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public Optional<User> deleteById(Long id) {
        return Optional.empty();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }
}
