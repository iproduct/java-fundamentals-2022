package course.java.springrest;

import course.java.model.User;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class TestJdbi {
//    @Test
//    public static void testFluentApi() {
//        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test"); // (H2 in-memory database)
//
//        List<User> users = jdbi.withHandle(handle -> {
//            handle.execute("CREATE TABLE \"user\" (id INTEGER PRIMARY KEY, \"name\" VARCHAR)");
//
//            // Inline positional parameters
//            handle.execute("INSERT INTO \"user\" (id, \"name\") VALUES (?, ?)", 0, "Alice");
//
//            // Positional parameters
//            handle.createUpdate("INSERT INTO \"user\" (id, \"name\") VALUES (?, ?)")
//                    .bind(0, 1) // 0-based parameter indexes
//                    .bind(1, "Bob")
//                    .execute();
//
//            // Named parameters
//            handle.createUpdate("INSERT INTO \"user\" (id, \"name\") VALUES (:id, :name)")
//                    .bind("id", 2)
//                    .bind("name", "Clarice")
//                    .execute();
//
//            // Named parameters from bean properties
//            handle.createUpdate("INSERT INTO \"user\" (id, \"name\") VALUES (:id, :name)")
//                    .bindBean(new User(3, "David"))
//                    .execute();
//
//            // Easy mapping to any type
//            return handle.createQuery("SELECT * FROM \"user\" ORDER BY \"name\"")
//                    .mapToBean(User.class)
//                    .list();
//        });
//
//        assertThat(users).containsExactly(
//                new User(0, "Alice"),
//                new User(1, "Bob"),
//                new User(2, "Clarice"),
//                new User(3, "David"));
//    }

//    public interface UserDao {
//        @SqlUpdate("CREATE TABLE \"user\" (id INTEGER PRIMARY KEY, \"name\" VARCHAR)")
//        void createTable();
//
//        @SqlUpdate("INSERT INTO \"user\" (id, \"name\") VALUES (?, ?)")
//        void insertPositional(int id, String name);
//
//        @SqlUpdate("INSERT INTO \"user\" (id, \"name\") VALUES (:id, :name)")
//        void insertNamed(@Bind("id") int id, @Bind("name") String name);
//
//        @SqlUpdate("INSERT INTO \"user\" (id, \"name\") VALUES (:id, :name)")
//        void insertBean(@BindBean User user);
//
//        @SqlQuery("SELECT * FROM \"user\" ORDER BY \"name\"")
//        @RegisterBeanMapper(User.class)
//        List<User> listUsers();
//    }
    @Test
    public static void testDeclarativeApi() {
//        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
//        jdbi.installPlugin(new SqlObjectPlugin());
//
//        // Jdbi implements your interface based on annotations
//        List<User> userNames = jdbi.withExtension(UserDao.class, dao -> {
//            dao.createTable();
//
//            dao.insertPositional(0, "Alice");
//            dao.insertPositional(1, "Bob");
//            dao.insertNamed(2, "Clarice");
//            dao.insertBean(new User(3, "David"));
//
//            return dao.listUsers();
//        });
//
//        assertThat(userNames).containsExactly(
//                new User(0, "Alice"),
//                new User(1, "Bob"),
//                new User(2, "Clarice"),
//                new User(3, "David"));
    }
}
