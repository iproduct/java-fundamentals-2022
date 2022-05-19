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
            handle.execute("CREATE TABLE `user` (id INTEGER PRIMARY KEY, `name` VARCHAR)");
            // Inline positional parameters
            handle.execute("INSERT INTO `user` (id, `name`) VALUES (?, ?)", 0, "Alice");
            return handle.createQuery("SELECT * FROM `user` ORDER BY `name`")
                    .mapToBean(User2.class)
                    .list();
        });
        users.forEach(System.out::println);
        assertThat(users).containsExactly(new User2(0, "Alice"));
    }

}
