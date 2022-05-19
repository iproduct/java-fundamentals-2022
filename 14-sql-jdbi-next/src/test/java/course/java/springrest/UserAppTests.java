package course.java.springrest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import course.java.dao.UserRepository;
import course.java.dao.UserRepositoryDataJPA;
import course.java.model.Person;
import course.java.model.Role;
import course.java.model.User;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Slf4j
@ActiveProfiles("test")
class UserAppTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepo;

    @Autowired
    ObjectMapper mapper;

    @Test
    void givenUsers_whenGetUsers_thenStatus200andJsonArray() throws Exception {
        given(userRepo.findAll()).willReturn(MOCK_USERS);

        var response = mockMvc.perform(get("/api/users").accept(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
                .andDo(result -> log.info("HTTP Resposne: {}", result.getResponse().getContentAsString()))
                .andExpect(jsonPath("$.length()").value(MOCK_USERS.size()))
                .andExpect(jsonPath("$[0].username").value(MOCK_USERS.get(0).getUsername()))
                .andExpect(jsonPath("$[0]", Matchers.hasEntry("firstName", MOCK_USERS.get(0).getFirstName())))
                .andExpect(jsonPath("$[*].firstName",
                        Matchers.hasItems(MOCK_USERS.stream().map(User::getFirstName).collect(Collectors.toList()).toArray())))
                .andExpect(jsonPath("$[*].lastName",
                        Matchers.hasItems(MOCK_USERS.stream().map(User::getLastName).collect(Collectors.toList()).toArray())))
                .andExpect(jsonPath("$[*].username",
                        Matchers.hasItems(MOCK_USERS.stream().map(User::getUsername).collect(Collectors.toList()).toArray())));


        var body = response.andReturn().getResponse().getContentAsString();
        TypeReference<List<User>> personList = new TypeReference<>() {
        };
        var userList = mapper.readValue(body, personList);

        org.hamcrest.MatcherAssert.assertThat(userList,
                Matchers.hasItems(MOCK_USERS.stream().map(u -> Matchers.samePropertyValuesAs(u, "created", "modified"))
                        .collect(Collectors.toList()).toArray(new Matcher[]{})));

        assertThat(userList).usingRecursiveComparison()
                .ignoringFields("id", "created", "modified")
                .ignoringAllOverriddenEquals()
                .isEqualTo(EXPECTED_USERS);

        then(userRepo).should(times(1)).findAll();
        then(userRepo).shouldHaveNoMoreInteractions();
    }

    @Test
    void givenUser_whenPostUser_thenStatus201LocationAndJsonObject() throws Exception {
        given(userRepo.create(any(User.class))).willReturn(CREATED_USER);

        var response = mockMvc.perform(
                post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(NEW_USER))
                        .accept(MediaType.APPLICATION_JSON));

        response.andExpect(status().isCreated())
                .andExpect(header().string("location", Matchers.endsWith("/api/users/1")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
                .andDo(result -> log.info("HTTP Resposne: {}", result.getResponse().getContentAsString()))
                .andExpect(jsonPath("$.firstName").value(CREATED_USER.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(CREATED_USER.getLastName()))
                .andExpect(jsonPath("$.username").value(CREATED_USER.getUsername())) // ...
                .andExpect(jsonPath("$", Matchers.hasEntry("firstName", CREATED_USER.getFirstName())));


        var body = response.andReturn().getResponse().getContentAsString();

        var user = mapper.readValue(body, User.class);

        org.hamcrest.MatcherAssert.assertThat(user,
                Matchers.samePropertyValuesAs(CREATED_USER, "created", "modified"));


        assertThat(user).usingRecursiveComparison()
                .ignoringFields("id", "created", "modified")
                .ignoringAllOverriddenEquals()
                .isEqualTo(CREATED_USER);

        then(userRepo).should(times(1)).findByUsername("georgi");
        then(userRepo).should(times(1)).create(NEW_USER);
        then(userRepo).shouldHaveNoMoreInteractions();
    }

    @Test
    void givenUserWithSameUsernameExists_whenPostUser_thenStatus400ErrorJsonObject() throws Exception {
        given(userRepo.findByUsername("georgi")).willReturn(Optional.of(CREATED_USER));

        mockMvc.perform(
                        post("/api/users")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(mapper.writeValueAsString(NEW_USER))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(header().doesNotExist("location"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
                .andDo(result -> log.info("HTTP Resposne: {}", result.getResponse().getContentAsString()))
                .andExpect(jsonPath("$.status").value(BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message").value("Username 'georgi' already exists."))
                .andExpect(jsonPath("$.violations").isEmpty());

        then(userRepo).should(times(1)).findByUsername("georgi");
        then(userRepo).shouldHaveNoMoreInteractions();
    }


    @Test
    void givenUser_whenPutUser_thenStatus200JsonObject() throws Exception {
        given(userRepo.findById(UPDATED_USER.getId())).willReturn(Optional.of(CREATED_USER));
        given(userRepo.update(UPDATED_USER)).willReturn(Optional.of(UPDATED_USER));

        var response = mockMvc.perform(
                put("/api/users/{userId}", UPDATED_USER.getId())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(mapper.writeValueAsString(UPDATED_USER))
                        .accept(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andDo(print())
                .andDo(result -> log.info("HTTP Resposne: {}", result.getResponse().getContentAsString()))
                .andExpect(jsonPath("$.id").value(UPDATED_USER.getId()))
                .andExpect(jsonPath("$.firstName").value(UPDATED_USER.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(UPDATED_USER.getLastName()))
                .andExpect(jsonPath("$.password").value(UPDATED_USER.getPassword()))
                .andExpect(jsonPath("$.age").value(UPDATED_USER.getAge()))
                .andExpect(jsonPath("$.role").value(UPDATED_USER.getRole().name()));


        var body = response.andReturn().getResponse().getContentAsString();
        var user = mapper.readValue(body, User.class);

        org.hamcrest.MatcherAssert.assertThat(user,
                Matchers.samePropertyValuesAs(UPDATED_USER, "created", "modified"));

        assertThat(user).usingRecursiveComparison()
                .ignoringFields("created", "modified")
                .ignoringAllOverriddenEquals()
                .isEqualTo(UPDATED_USER);

        then(userRepo).should(times(1)).findById(UPDATED_USER.getId());
        then(userRepo).should(times(1)).update(UPDATED_USER);
        then(userRepo).shouldHaveNoMoreInteractions();
    }

    public List<User> MOCK_USERS = List.of(
            new User(1L, "Ivan", "Petrov", 25, "ivan", "Ivan123#",
                    Role.ADMIN, true, "+(359) 887 894356"),
            new User(2L, "Nadezda", "Todorova", 29, "nadia", "Nadia123#",
                    Role.READER, true, "+(359) 889 123456"),
            new User(3L, "Hristo", "Yanakiev", 23, "hristo", "Hris123#",
                    Role.ADMIN, true, null)
    );
    public List<User> EXPECTED_USERS = List.of(
            new User(1L, "Ivan", "Petrov", 25, "ivan", "Ivan123#",
                    Role.ADMIN, true, "+(359) 887 894356"),
            new User(2L, "Nadezda", "Todorova", 29, "nadia", "Nadia123#",
                    Role.READER, true, "+(359) 889 123456"),
            new User(3L, "Hristo", "Yanakiev", 23, "hristo", "Hris123#",
                    Role.ADMIN, true, null)
    );

    public static User NEW_USER = new User("Gorgi", "Petrov", 45,
            "georgi", "Gogo123#", Role.READER, "+(1) 456778898");
    public static User CREATED_USER = new User(1L, "Gorgi", "Petrov", 45,
            "georgi", "Gogo123#", Role.READER, true, "+(1) 456778898");
    public static User UPDATED_USER = new User(1L, "George", "Peev", 25,
            "georgi", "George123#", Role.ADMIN, true, "+(1) 456778898");
}
