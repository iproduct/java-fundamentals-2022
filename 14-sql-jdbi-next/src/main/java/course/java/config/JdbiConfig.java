package course.java.config;

import course.java.dao.UserRepository;
import course.java.dao.impl.UserRepositoryJdbi;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.core.statement.Slf4JSqlLogger;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@ComponentScan(basePackageClasses = UserRepositoryJdbi.class)
public class JdbiConfig {
    @Bean
    public Jdbi jdbi(DataSource ds, List<JdbiPlugin> jdbiPlugins, List<RowMapper<?>> rowMappers) {
        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(ds);
        Jdbi jdbi = Jdbi.create(proxy);
        jdbi.installPlugin(new SqlObjectPlugin());
        jdbiPlugins.forEach(plugin -> jdbi.installPlugin(plugin));
        rowMappers.forEach(mapper -> jdbi.registerRowMapper(mapper));
        jdbi.setSqlLogger(new Slf4JSqlLogger());
        return jdbi;
    }

    @Bean
    public UserRepositoryJdbi userRepositoryDao(Jdbi jdbi) {
        return jdbi.onDemand(UserRepositoryJdbi.class);
    }

}
