package course.java.dao;

import course.java.model.User;
import course.java.model.User2;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepositoryJdbi {
    public static final String USERS_TABLE = "users";
    @SqlUpdate("CREATE TABLE `" + USERS_TABLE + "` ( " +
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
    public boolean createTable();

    @SqlQuery("SELECT * FROM `" + USERS_TABLE + "`;")
    @RegisterBeanMapper(User.class)
    Collection<User> findAll();

    @SqlQuery("SELECT * FROM `" + USERS_TABLE + "` WHERE `id` = :id;")
    @RegisterBeanMapper(User.class)
    Optional<User> findById(@Bind("id") Long id);

    @SqlUpdate("INSERT INTO `" + USERS_TABLE + "` " +
            "(`firstName`, `lastName`, `age`, `phone`, `username`, `password`, `role`, `active`, `created`, `modified`) " +
            " VALUES (:firstName, :lastName, :age, :phone, :username, :password, :role, :active, :created, :modified); ")
    @GetGeneratedKeys("id")
    long createNamed(@Bind("firstName") int firstName, @Bind("lastName") String lastName,
                        @Bind("age") String age, @Bind("phone") String phone,
                        @Bind("username") String username, @Bind("password") String password,
                        @Bind("role") String role, @Bind("active") String active,
                        @Bind("created") String created, @Bind("modified") String modified
    );

    @SqlUpdate("INSERT INTO `" + USERS_TABLE + "` " +
            " (`id`, `firstName`, `lastName`, `age`, `phone`, `username`, `password`, `role`, `active`, `created`, `modified`) " +
            " VALUES (:id, :firstName, :lastName, :age, :phone, :username, :password, :role, :active, :created, :modified) ")
    @GetGeneratedKeys("id")
    long create(@BindBean User user);

    @SqlBatch("INSERT INTO `" + USERS_TABLE + "` " +
            " (`id`, `firstName`, `lastName`, `age`, `phone`, `username`, `password`, `role`, `active`, `created`, `modified`) " +
            " VALUES (:id, :firstName, :lastName, :age, :phone, :username, :password, :role, :active, :created, :modified) ")
    @GetGeneratedKeys("id")
    long[] createBatch(@BindBean Iterable<User> users);

    @SqlUpdate("UPDATE `" + USERS_TABLE + "` " +
            " SET `firstName` = :firstName, `lastName` = :lastName, `age` = :age, `phone` = :phone, " +
            "`username` = :username , `password` = :password , " +
            "`role` = :role , `active` = :active , `created` = :created , `modified` = :modified " +
            " WHERE id = :id;")
    boolean update(User entity);

    @SqlUpdate("DELETE FROM `" + USERS_TABLE + "` WHERE id = :id; ")
    boolean deleteById(Long id);

    @SqlQuery("SELECT COUNT(*) FROM `" + USERS_TABLE + "`")
    long count();
}
