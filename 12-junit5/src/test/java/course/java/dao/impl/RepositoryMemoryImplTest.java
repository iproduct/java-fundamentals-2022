package course.java.dao.impl;

import course.java.model.Role;
import course.java.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RepositoryMemoryImplTest {
    private RepositoryMemoryImpl<User, Long> usersRepo;

    @BeforeEach
    void setUp() {
        usersRepo = new UserRepositoryMemoryImpl(new LongIdGenerator());
        SAMPLE_USERS.stream().map(u ->
                        new User(u.getId(), u.getFirstName(), u.getLastName(), u.getAge(), u.getUsername(), u.getPassword(),
                                u.getRole(), u.isActive(), u.getPhone()))
                .forEach(usersRepo::create);
    }

    @Test
    void findAll() {
        var actual = usersRepo.findAll();
        assertThat(actual).hasSize(SAMPLE_USERS.size())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id", "created", "modified")
                .containsExactlyInAnyOrderElementsOf(SAMPLE_USERS2);
    }

    @Test
    void findAllRecursiveComparison() {
        var actual = List.copyOf(usersRepo.findAll());
        assertThat(actual).hasSize(SAMPLE_USERS.size())
//                .containsExactlyInAnyOrderElementsOf(SAMPLE_USERS)
                .usingRecursiveComparison()
                .ignoringFields("id", "created", "modified")
                .ignoringAllOverriddenEquals()
                .isEqualTo(SAMPLE_USERS2);
    }

    @Test
    @Disabled
    void findById() {
    }

    @Test
    @Disabled
    void create() {
    }

    @Test
    @Disabled
    void update() {
    }

    @Test
    @Disabled
    void deleteById() {
    }

    @Test
    @Disabled
    void count() {
    }

    public static final List<User> SAMPLE_USERS = List.of(
            new User("Ivan", "Petrov", 25, "ivan", "Ivan123#", Role.ADMIN,
                    "+(359) 887 894356"),
            new User("Nadezda", "Todorova", 29, "nadia", "nadia123", Role.READER,
                    "+(359) 889 123456"),
            new User("Hristo", "Yanakiev", 23, "hristo", "hris123", Role.ADMIN, "")
    );

    public static final List<User> SAMPLE_USERS2 = List.of(
            new User("Ivan", "Petrov", 25, "ivan", "Ivan123#", Role.ADMIN,
                     "+(359) 887 894356"),
            new User("Nadezda", "Todorova", 29, "nadia2", "nadia123", Role.READER,
                    "+(359) 889 123456"),
            new User("Hristo", "Yanakiev", 23, "hristo", "hris123", Role.ADMIN,
                    "")
    );
}
