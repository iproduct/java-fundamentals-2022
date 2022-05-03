package course.java.dao.impl;

import course.java.exception.InvalidEntityDataException;
import course.java.model.Role;
import course.java.model.User;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static jdk.dynalink.linker.support.Guards.isNotNull;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Tag("dao")
@Tag("generic")
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
    void create() {
        var u = NEW_USER;
        var actual = usersRepo.create(new User(u.getId(), u.getFirstName(), u.getLastName(), u.getAge(), u.getUsername(), u.getPassword(),
                u.getRole(), u.isActive(), u.getPhone()));
//        actual.setUsername(actual.getUsername() + 1);
        assertWith(actual,
                user -> {
                    assertThat(user).extracting("id").isNotNull();
                    assertThat(user.getId()).isInstanceOf(Long.class).isPositive();
                    assertThat(user.getId()).isInstanceOf(Long.class).isGreaterThan(SAMPLE_USERS.size());
                    assertThat(user.getCreated()).isBefore(LocalDateTime.now());
                    assertThat(user.getCreated().plus(1, ChronoUnit.SECONDS))
                            .as("Created no earlier than 1 sec before").isAfter(LocalDateTime.now());
                    assertThat(user.getModified()).isBefore(LocalDateTime.now());
                    assertThat(user.getModified().plus(1, ChronoUnit.SECONDS))
                            .as("Modified no earlier than 1 sec before").isAfter(LocalDateTime.now());
                    assertThat(user).usingRecursiveComparison()
                            .ignoringFields("id", "created", "modified")
                            .ignoringAllOverriddenEquals()
                            .isEqualTo(NEW_USER);
                });
    }

    @Test
    @Disabled
        // Test validation logic in service layer
    void createExistingUsernameThrowsException() {
        var u = SAMPLE_USERS.get(0);
        var actual = usersRepo.create(new User(u.getId(), u.getFirstName(), u.getLastName(), u.getAge(), u.getUsername(), u.getPassword(),
                u.getRole(), u.isActive(), u.getPhone()));
        assertThatExceptionOfType(InvalidEntityDataException.class)
                .isThrownBy(() -> {
                    usersRepo.create(new User(u.getId(), u.getFirstName(), u.getLastName(), u.getAge(), u.getUsername(), u.getPassword(),
                            u.getRole(), u.isActive(), u.getPhone()));
                })
                .withNoCause()
                .withMessage("boom!");
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
            new User("Nadezda", "Todorova", 29, "nadia", "nadia123", Role.READER,
                    "+(359) 889 123456"),
            new User("Hristo", "Yanakiev", 23, "hristo", "hris123", Role.ADMIN,
                    "")
    );

    public static final User NEW_USER = new User("John", "Doe", 43, "john", "John123#",
            Role.ADMIN, "+(359) 887 894356");
}
