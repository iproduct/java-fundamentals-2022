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
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import static course.java.dao.impl.RepoFactoryInMemoryImpl.CONFIG_REPO_ID_GENERATOR_CLASS;
import static course.java.model.Role.ANONYMOUS;
import static course.java.service.impl.UserServiceImpl.INVALID_USER_DATA_FOR_USER;
import static course.java.util.UserValidator.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private UserService userService;
    @Mock(lenient = false)
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

    @ParameterizedTest(name="#{index} - Test with arguments: {0}, {1}, {2}, {3}, {4}, {5}, {6}")
    @CsvFileSource(resources = "/invalid_user_data.csv", numLinesToSkip = 1)
    void addUserThrowsWhenUserDataInvalid(String fName, String lName, int age, String username, String password, String roleStr, String phone) throws InvalidEntityDataException {
        var user = new User(fName, lName, age, username, password, Role.valueOf(roleStr), phone);
        when(mockUserRepo.findByUsername(anyString())).thenReturn(Optional.empty());

        // assert
        assertThatExceptionOfType(InvalidEntityDataException.class)
                .isThrownBy(() -> userService.addUser(user))
                .withMessageStartingWith(INVALID_USER_DATA_FOR_USER)
                .withCauseExactlyInstanceOf(ConstraintViolationException.class);

        // verify repo method called
        verifyNoMoreInteractions(mockUserRepo);
    }

    @ParameterizedTest(name="#{index} - Test with arguments: {0}, {1}, {2}, {3}, {4}, {5}, {6}")
    @CsvSource({
            "Ivan, Petrov, 25, ivan, Ivan123#, ADMIN, +(359) 887 894356",
            "John, Doe, 35, john, John123#, READER, +(1) 456676778",
            "Jane, doe, 28, jane, Jane123#, AUTHOR, +(19) 6573788329"
    })
    void addUser(String fName, String lName, int age, String username, String password, String roleStr, String phone) throws InvalidEntityDataException {
        var user = new User(fName, lName, age, username, password, Role.valueOf(roleStr), phone);
        var created = new User(1L, fName, lName, age, username, password, Role.valueOf(roleStr), true, phone);
        when(mockUserRepo.create(user)).thenReturn(created);
        when(mockUserRepo.findByUsername(anyString())).thenReturn(Optional.empty()); //.thenCallRealMethod();

        // call method under test
        var actual = userService.addUser(user);

        // assert
        assertThat(actual).usingRecursiveComparison()
                .ignoringFields("id", "created", "modified")
                .ignoringAllOverriddenEquals()
                .isEqualTo(created);

        // verify repo method called
        verify(mockUserRepo, times(1)).create(any(User.class));
        verifyNoMoreInteractions(mockUserRepo);
    }

    @ParameterizedTest
    @EnumSource(mode= EXCLUDE, names={"ANONYMOUS"}, value = Role.class)
    @DisplayName("Given exisiting user, when updateUser() with new role, then role should be updated")
    void givenExisitingUserWhenUpdateUserRoleThenRoleShouldBeUpdated(Role role) throws InvalidEntityDataException, NonexistingEntityException {
        // setup
        var updated = copyUser(NEW_USER);
        updated.setId(1L);
        when(mockUserRepo.update(any(User.class))).thenReturn(updated);
        when(mockUserRepo.findById(any(Long.class))).thenReturn(Optional.of(updated));

        // call
        updated.setRole(role);
        var actual = userService.updateUser(updated);

        // assert
        assertEquals(role, actual.getRole());

        // verify repo method called
        verify(mockUserRepo, times(1)).update(any(User.class));
        verifyNoMoreInteractions(mockUserRepo);
    }

    @ParameterizedTest(name="#{index} - Test with argument: {0}")
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
        verify(mockUserRepo, times(1)).update(any(User.class));
        verifyNoMoreInteractions(mockUserRepo);
    }

    @ParameterizedTest(name="#{index} - Test with argument: {0}")
    @EnumSource(names={"ANONYMOUS"}, value = Role.class)
    @DisplayName("Given exisiting user, when updateUser() with new ANONYMOUS role, then InvalidEntityDataException should be thrown")
    void givenExisitingUserWhenUpdateUserAnonymousRoleThenThrow(Role role) throws InvalidEntityDataException, NonexistingEntityException {
        // setup
        var updated = copyUser(NEW_USER);
        updated.setId(SAMPLE_ID);
        updated.setRole(role);
//        when(mockUserRepo.update(any(User.class))).thenReturn(updated);
        when(mockUserRepo.findById(any(Long.class))).thenReturn(Optional.of(updated));

        // call & assert
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
