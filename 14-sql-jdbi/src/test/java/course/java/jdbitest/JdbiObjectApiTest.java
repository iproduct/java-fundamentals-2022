package course.java.jdbitest;

import course.java.dao.User2DaoJdbi;
import course.java.model.User2;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JdbiObjectApiTest {
    @Test
    public void testJdbiObjectApi(){
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());

        // Jdbi implements your interface based on annotations
        List<User2> users = jdbi.withExtension(User2DaoJdbi.class, dao -> {
            dao.createTable();

            dao.insertPositional(1, "Alice");
            dao.insertPositional(2, "Bob");
            dao.insertNamed(3, "Clarice");
            dao.insertBean(new User2(4, "David"));

            return dao.listUsers();
        });

        users.forEach(System.out::println);

        assertThat(users).containsExactly(
                new User2(1, "Alice"),
                new User2(2, "Bob"),
                new User2(3, "Clarice"),
                new User2(4, "David")
        );
    }

}
