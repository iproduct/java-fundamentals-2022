package course.java.dao.impl;

import course.java.dao.UserRepository;
import course.java.exception.PersistenceException;
import course.java.model.User;
import course.java.util.JdbcUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Collection;
import java.util.Optional;
import java.util.logging.Level;

@Repository
@Slf4j
public class UserRepositoryJdbc implements UserRepository {
    private static final String USERS_TABLE = "`users`";
    private static final String FIND_ALL_USERS = "SELECT * FROM " + USERS_TABLE + "; ";
    private static final String FIND_USER_BY_ID = "SELECT * FROM " + USERS_TABLE + " WHERE id=?; ";
    public static final String INSERT_NEW_USER =
            "INSERT INTO " + USERS_TABLE + " " +
                    "(`firstName`, `lastName`, `age`, `phone`, `username`, `password`, `role`, `active`, `created`, `modified`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_USER =
            "UPDATE " + USERS_TABLE +
                    " SET `firstName` = ?, `lastName` = ?, `age` = ?, `phone` = ?, `username` = ? , `password` = ? , " +
                    "`role` = ? , `active` = ? , `created` = ? , `modified` = ? " +
                    " WHERE id = ?; ";

    public static final String DELETE_USER =
            "DELETE FROM " + USERS_TABLE + " WHERE id = ?; ";

    private static final String FIND_USER_BY_USERNAME = "SELECT * FROM " + USERS_TABLE + " WHERE `username` = ?; ";
    private static final String FIND_USERS_COUNT = "SELECT COUNT(*) FROM " + USERS_TABLE  + "; ";


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
    public Optional<User> update(User user) {
        if(user.getId() == null) { // product Id should be present
            return Optional.empty();
        }
        try {
            PreparedStatement ps = connection.prepareStatement(UPDATE_USER);
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
            ps.setLong(11, user.getId());
            int numExecutedStatements = ps.executeUpdate();
            if(numExecutedStatements > 0) {
                log.info(String.format(
                        "User {}: {} updated successfully", user.getId(), user.getUsername()));
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new PersistenceException("Error updating user: " + user.getUsername(), e);
        }
    }

    @Override
    public Optional<User> deleteById(Long id) {
        Optional<User> user = findById(id);
        if(user.isPresent()) {
            try {
                PreparedStatement ps = connection.prepareStatement(DELETE_USER);
                ps.setLong(1, id);
                var deletetRows = ps.executeUpdate();
                if(deletetRows == 0) {
                    return Optional.empty();
                }
            } catch (SQLException e) {
                throw new PersistenceException("Error deleting user by ID= " + id, e);
            }
        }
        return user;
    }

    @Override
    public long count() {
        try (var ps = connection.prepareStatement(FIND_USERS_COUNT)) {
            var rs = ps.executeQuery();
            try {
                rs.next();
                return rs.getLong(1);
            } catch (SQLException e) {
                throw new PersistenceException("Error fetching users count", e);
            }
        } catch (SQLException e) {
            throw new PersistenceException("Error executing database query", e);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try (var ps = connection.prepareStatement(FIND_USER_BY_USERNAME)) {
            ps.setString(1, username);
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
}
