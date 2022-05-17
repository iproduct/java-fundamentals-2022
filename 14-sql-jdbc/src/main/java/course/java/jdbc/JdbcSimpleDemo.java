package course.java.jdbc;

import course.java.model.User;
import course.java.util.JdbcUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
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
public class JdbcSimpleDemo {
    public static final String SELECT_ALL_USERS = "select * from users;";
    public static final String SELECT_ALL_USERS_IN_ROLE = "select * from users where role = ?;";

    public void run() throws IOException, ClassNotFoundException {
        // 1. Load DB connection properties
        Properties props = new Properties();
        var dbConfigPath = JdbcSimpleDemo.class.getClassLoader().getResource("jdbc.properties")
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

        // 3. Create DB Connection and 4. Create Statement
        try (var con = DriverManager.getConnection(props.getProperty("url"), props)) {
            log.info("DB connection created successfully to schema: {}", con.getCatalog());
            try(var stmt = con.prepareStatement(SELECT_ALL_USERS_IN_ROLE)) {
                stmt.setString(1, ADMIN.name()); // set param with escaping user values to prevent SQL injection!
                ResultSet rs = stmt.executeQuery();
                List<User> users = JdbcUtil.getEntities(rs, User.class);
                users.forEach(System.out::println);
            }catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException |
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
        var demo = new JdbcSimpleDemo();
        demo.run();
    }
}
