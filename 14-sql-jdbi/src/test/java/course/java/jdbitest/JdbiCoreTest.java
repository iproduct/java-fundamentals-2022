package course.java.jdbitest;

import course.java.model.User;
import course.java.model.User2;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JdbiCoreTest {
    @Test
    public void connectToDb(){
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        List<User2> users = jdbi.withHandle(handle -> {
            handle.execute("CREATE TABLE \"user\" (id INTEGER PRIMARY KEY, \"name\" VARCHAR)");
            // Inline positional parameters
            handle.execute("INSERT INTO \"user\" (id, \"name\") VALUES (?, ?)", 1, "Alice");
            // Positional parameters
            handle.createUpdate("INSERT INTO \"user\" (id, \"name\") VALUES (?, ?)")
                    .bind(0, 2) // 0-based parameter indexes
                    .bind(1, "Bob")
                    .execute();
            // Named parameters
            handle.createUpdate("INSERT INTO \"user\" (id, \"name\") VALUES (:id, :name)")
                    .bind("id", 3)
                    .bind("name", "Clarice")
                    .execute();
            handle.createUpdate("INSERT INTO \"user\" (id, \"name\") VALUES (:id, :name)")
                    .bindBean(new User2(4, "David"))
                    .execute();
            return handle.createQuery("SELECT * FROM \"user\" ORDER BY \"name\"")
                    .mapToBean(User2.class)
                    .list();
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
