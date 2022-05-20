package course.java.dao;

import course.java.model.User2;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface User2DaoJdbi {
    @SqlUpdate("CREATE TABLE \"user\" (id INTEGER PRIMARY KEY, \"name\" VARCHAR)")
    void createTable();

    @SqlUpdate("INSERT INTO \"user\" (id, \"name\") VALUES (?, ?)")
    void insertPositional(int id, String name);

    @SqlUpdate("INSERT INTO \"user\" (id, \"name\") VALUES (:id, :name)")
    void insertNamed(@Bind("id") int id, @Bind("name") String name);

    @SqlUpdate("INSERT INTO \"user\" (id, \"name\") VALUES (:id, :name)")
    void insertBean(@BindBean User2 user);

    @SqlQuery("SELECT * FROM \"user\" ORDER BY \"name\"")
    @RegisterBeanMapper(User2.class)
    List<User2> listUsers();
}
