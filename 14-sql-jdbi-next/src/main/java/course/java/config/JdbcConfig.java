package course.java.config;

import com.zaxxer.hikari.HikariDataSource;
import course.java.exception.PersistenceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@PropertySource("jdbc.properties")
public class JdbcConfig {
    @Value("${driver}")
    private String driverClassname;

    @Value("${url}")
    private String url;

    @Value("${user}")
    private String username;

    @Value("${password}")
    private String password;

    @Bean
    DataSource getDataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverClassname);
        dataSource.setJdbcUrl(url);//change url
        dataSource.setUsername(username);//change username
        dataSource.setPassword(password);//change pwddataSource.setJdbcUrl("jdbc:mysql://localhost:3306/simpsons");
        return dataSource;
    }

    @Bean
    public Connection getJdbcConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            throw new PersistenceException("Error getting database connection", e);
        }
    }

}
