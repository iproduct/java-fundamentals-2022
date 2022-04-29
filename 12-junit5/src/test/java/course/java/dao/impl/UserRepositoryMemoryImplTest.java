package course.java.dao.impl;

import course.java.model.Role;
import course.java.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryMemoryImplTest {
    private UserRepositoryMemoryImpl repository;

    @BeforeEach
    void setup(){
        repository = new UserRepositoryMemoryImpl(new LongIdGenerator());
        SAMPLE_USERS.forEach(repository::create);
    }

    @Test
//    @DisplayName("Given user with username exists, when findByUsername(), then correct user should be returned")
    void findByUsername() {
        var username = SAMPLE_USERS.get(1).getUsername();
        var actual = repository.findByUsername(username);
        assertThat(actual)
                .as("Finding user by username '%s' using findByUsername(\"%1$s\")", username)
                .isNotEmpty();
        assertThat(actual.get().getUsername())
//                .as("Finding user by username '%s' using findByUsername(\"%1$s\").getUsername()", username)
                .isEqualTo(username+"1");
    }

    @Test
//    @DisplayName("Given user with username exists, when findByUsername(), then correct user should be returned")
    void findByUsernameUsingExtractProperty() {
        var username = SAMPLE_USERS.get(1).getUsername();
        var actual = repository.findByUsername(username);
        assertThat(actual)
                .as("Finding user by username '%s' using findByUsername(\"%1$s\")", username)
                .isNotEmpty();
        assertThat(actual.get())
//                .as("Finding user by username '%s' using findByUsername(\"%1$s\")", username)
                .extracting("username")
                .isEqualTo(username+"1");
    }

    public static final List<User> SAMPLE_USERS = List.of(
            new User("Ivan", "Petrov", 25, "ivan", "Ivan123#", Role.ADMIN,
                    "+(359) 887 894356"),
            new User("Nadezda", "Todorova", 29, "nadia", "nadia123", Role.READER,
                    "+(359) 889 123456"),
            new User("Hristo", "Yanakiev", 23, "hristo", "hris123", Role.ADMIN, "")
    );
}
