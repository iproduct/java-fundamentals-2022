package course.java.service.impl;

import course.java.dao.RepoFactory;
import course.java.dao.UserRepository;
import course.java.dao.impl.LongIdGenerator;
import course.java.dao.impl.RepoFactoryInMemoryImpl;
import course.java.dao.impl.UserRepoFactoryInMemoryImpl;
import course.java.dao.impl.UserRepositoryMemoryImpl;
import course.java.exception.ConstraintViolation;
import course.java.exception.ConstraintViolationException;
import course.java.exception.InvalidEntityDataException;
import course.java.exception.NonexistingEntityException;
import course.java.model.Role;
import course.java.model.User;
import course.java.service.UserService;
import course.java.util.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static course.java.dao.impl.RepoFactoryInMemoryImpl.CONFIG_REPO_ID_GENERATOR_CLASS;
import static course.java.model.Role.ANONYMOUS;
import static course.java.service.impl.UserServiceImpl.INVALID_USER_DATA_FOR_USER;
import static course.java.util.UserValidator.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private UserService userService;
    @Mock
    private UserRepositoryMemoryImpl mockUserRepo;

    @BeforeEach
    void setup() {
//        mockUserRepo = Mockito.mock(UserRepositoryMemoryImpl.class);
        userService = new UserServiceImpl(mockUserRepo, new UserValidator());
    }

    @Test
    @Disabled
    void loadData() {
    }

    @Test
    @Disabled
    void getAllUsers() {
    }

    @Test
    @Disabled
    void getUserById() {
    }

    @Test
    @Disabled
    void getUserByUsername() {
    }

    @Test
    @Disabled
    void addUser() {
    }

    @ParameterizedTest
    @EnumSource(mode= EXCLUDE, names={"ANONYMOUS"}, value = Role.class)
    @DisplayName("Given exisiting user, when updateUser() with new role, then role should be updated")
    void givenExisitingUserWhenUpdateUserRoleThenRoleShouldBeUpdated(Role role) throws InvalidEntityDataException, NonexistingEntityException {
        // setup
        var user = userService.addUser(NEW_USER);
        var updated = copyUser(user);

        // call
        updated.setRole(role);
        var actual = userService.updateUser(updated);

        // assert
        assertEquals(role, actual.getRole());
    }

    @ParameterizedTest
    @EnumSource(mode= EXCLUDE, names={"ANONYMOUS"}, value = Role.class)
    @DisplayName("Given exisiting user, when updateUser() with new role, then role should be updated [with MOCKING]")
    void givenExisitingUserWhenUpdateUserRoleThenRoleShouldBeUpdatedUsingMockito(Role role) throws InvalidEntityDataException, NonexistingEntityException {
        // setup
        var updated = copyUser(NEW_USER);
        updated.setId(SAMPLE_ID);
        updated.setRole(role);
        when(mockUserRepo.update(any(User.class))).thenReturn(updated);
        when(mockUserRepo.findById(any(Long.class))).thenReturn(Optional.of(updated));

        // call
        updated.setRole(role);
        var actual = userService.updateUser(updated);

        // assert
        assertEquals(role, actual.getRole());
        assertEquals(SAMPLE_ID, actual.getId());

        // verify repo method called
        verify(mockUserRepo).update(any(User.class));
    }

    @ParameterizedTest
    @EnumSource(names={"ANONYMOUS"}, value = Role.class)
    @DisplayName("Given exisiting user, when updateUser() with new ANONYMOUS role, then InvalidEntityDataException should be thrown")
    void givenExisitingUserWhenUpdateUserAnonymousRoleThenThrow(Role role) throws InvalidEntityDataException, NonexistingEntityException {
        // setup
        var user = userService.addUser(NEW_USER);
        var updated = copyUser(user);

        // call & assert
        updated.setRole(role);
//        var exception = assertThrows(InvalidEntityDataException.class, () -> userService.updateUser(updated));
        assertThatExceptionOfType(InvalidEntityDataException.class)
                .isThrownBy(() -> userService.updateUser(updated))
                .withMessageStartingWith(INVALID_USER_DATA_FOR_USER)
                .withCauseExactlyInstanceOf(ConstraintViolationException.class);
//                .withCause( new ConstraintViolationException(INVALID_USER_FIELD,
//                        List.of(new ConstraintViolation(updated.getClass().getName(), ROLE_FIELD, updated.getRole(),
//                                ROLE_CAN_NOT_NOT_BE_SET_TO_ANONYMOUS))));

    }

    @Test
    @Disabled
    void deleteUserById() {
    }

    @Test
    @Disabled
    void count() {
    }

    private static User copyUser(User u) {
        return new User(u.getId(), u.getFirstName(), u.getLastName(), u.getAge(), u.getUsername(), u.getPassword(),
                u.getRole(), u.isActive(), u.getPhone());
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

    public static final long SAMPLE_ID = 123L;

}
