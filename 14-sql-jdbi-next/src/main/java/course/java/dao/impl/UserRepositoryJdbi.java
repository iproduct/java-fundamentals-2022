package course.java.dao.impl;

import course.java.dao.UserRepository;
import course.java.model.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

import static course.java.dao.impl.UserRepositoryJdbc.USERS_TABLE;

@Primary
@Repository
public interface UserRepositoryJdbi {
    @SqlUpdate("CREATE TABLE `users` ( " +
            "`id`        BIGINT(20)   NOT NULL AUTO_INCREMENT, " +
            "`firstName` VARCHAR(30)  NOT NULL, " +
            "`lastName`  VARCHAR(30)  NOT NULL, " +
            "`age`       INT(11)      NULL, " +
            "`phone`     VARCHAR(45)  NULL, " +
            "`username`  VARCHAR(30)  NOT NULL, " +
            "`password`  VARCHAR(255) NOT NULL, " +
            "`role`      VARCHAR(10)  NOT NULL DEFAULT 'READER', " +
            "`active`    BIT(1)       NULL     DEFAULT 1, " +
            "`created`   DATETIME     NULL     DEFAULT NOW(), " +
            "`modified`  DATETIME     NULL     DEFAULT NOW(), " +
            "PRIMARY KEY (`id`), " +
            "UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE " +
            ");")
    void createTable();

    //    @Override
    @SqlUpdate("INSERT INTO " + USERS_TABLE +
            "(`firstName`, `lastName`, `age`, `phone`, `username`, `password`, `role`, `active`, `created`, `modified`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
    @GetGeneratedKeys
    boolean create(int firstName, String lastName,
                   String age, String phone,
                   String username, String password,
                   String role, String active,
                   String created, String modified);

    @SqlUpdate("INSERT INTO " + USERS_TABLE +
            "(`firstName`, `lastName`, `age`, `phone`, `username`, `password`, `role`, `active`, `created`, `modified`) " +
            " VALUES (:firstName, :lastName, :age, :phone, :username, :password, :role, :active, :created, :modified) ")
    @GetGeneratedKeys
    boolean createNamed(@Bind("firstName") int firstName, @Bind("lastName") String lastName,
                        @Bind("age") String age, @Bind("phone") String phone,
                        @Bind("username") String username, @Bind("password") String password,
                        @Bind("role") String role, @Bind("active") String active,
                        @Bind("created") String created, @Bind("modified") String modified
    );

    @SqlUpdate("INSERT INTO " + USERS_TABLE +
            " (`firstName`, `lastName`, `age`, `phone`, `username`, `password`, `role`, `active`, `created`, `modified`) " +
            " VALUES (:firstName, :lastName, :age, :phone, :username, :password, :role, :active, :created, :modified) ")
    @GetGeneratedKeys
    boolean createBean(@BindBean User user);

    @SqlBatch("INSERT INTO " + USERS_TABLE +
            "(`firstName`, `lastName`, `age`, `phone`, `username`, `password`, `role`, `active`, `created`, `modified`) " +
            " VALUES(:firstName, :lastName, :age, :phone, :username, :password, :role, :active, :created, :modified) ")
    @GetGeneratedKeys
    int[] createBatch(@BindBean Iterable<User> users);


    //    @Override
    @SqlQuery("SELECT * FROM " + USERS_TABLE + "; ")
    @RegisterBeanMapper(User.class)
    List<User> findAll();

    @SqlQuery("SELECT COUNT(*) FROM " + USERS_TABLE + "; ")
    long count();
}
