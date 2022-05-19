package course.java.jdbc;

import course.java.model.MockUsers;
import course.java.model.User;
import course.java.util.JdbcUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static course.java.model.Role.ADMIN;

@Slf4j
public class JdbcInsertData {
    public static final String SELECT_ALL_USERS = "select * from users;";
    public static final String SELECT_ALL_USERS_IN_ROLE = "select * from users where role = ?;";
    public static final String INSERT_NEW_USER_USER =
            "INSERT INTO `users` " +
                    "(`firstName`, `lastName`, `age`, `phone`, `username`, `password`, `role`, `active`) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public void run() throws IOException, ClassNotFoundException {
        // 1. Load DB connection properties
        Properties props = new Properties();
        var dbConfigPath = JdbcInsertData.class.getClassLoader().getResource("jdbc.properties")
                .getPath();
        props.load(new FileReader(dbConfigPath));

        // 2. Load DB Driver class (optional)
        try {
            Class.forName(props.getProperty("driver"));
        } catch (ClassNotFoundException ex) {
            log.error("DB driver class not found: ", ex);
            throw ex;
        }
        log.info("DB driver loaded successfully");

        // 3. Create DB Connection and
        try (var con = DriverManager.getConnection(props.getProperty("url"), props)) {
            log.info("DB connection created successfully to schema: {}", con.getCatalog());
            //4. Insert users using PreparedStatement
            try (var stmt = con.prepareStatement(INSERT_NEW_USER_USER)) {
                for(User u : MockUsers.MOCK_USERS) {
                    stmt.setString(1, u.getFirstName());
                    stmt.setString(2, u.getLastName());
                    stmt.setInt(3, u.getAge());
                    stmt.setString(4, u.getPhone());
                    stmt.setString(5, u.getUsername());
                    stmt.setString(6, u.getPassword());
                    stmt.setString(7, u.getRole().name());
                    stmt.setBoolean(8, u.isActive());
                    int numRecords = stmt.executeUpdate();
                    log.info("Successfully INSERTED {} records.", numRecords);
                }
            } catch (SQLException e) {
                log.error("Error executing DB query: ", e);
                throw new RuntimeException(e);
            }

            //5. Create PreparedStatement and provide param value
            try (var stmt = con.prepareStatement(SELECT_ALL_USERS)) {
//                stmt.setString(1, ADMIN.name()); // set param with escaping user values to prevent SQL injection!
                ResultSet rs = stmt.executeQuery();
                List<User> users = JdbcUtil.getEntities(rs, User.class);
                users.forEach(System.out::println);
            } catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                     IllegalAccessException e) {
                log.error("Error executing DB query: ", e);
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            log.error("Error connecting to DB.", e);
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var demo = new JdbcInsertData();
        demo.run();
    }
}
