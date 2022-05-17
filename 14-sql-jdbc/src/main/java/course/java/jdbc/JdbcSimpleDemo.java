package course.java.jdbc;

import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class JdbcSimpleDemo {
    public static final String SELECT_ALL_USERS = "select * from users;";

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
        try (var con = DriverManager.getConnection(props.getProperty("url"), props);
             var stmt = con.createStatement()) {
            log.info("DB connection created successfully to schema: {}", con.getCatalog());
            ResultSet rs = stmt.executeQuery(SELECT_ALL_USERS);

        } catch (SQLException e) {
            log.error("Error connecting to DB driver class not found: ", e);
            throw new RuntimeException(e);
        }
    }



    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var demo = new JdbcSimpleDemo();
        demo.run();
    }
}
