package course.java.springrest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@Slf4j
class UserAppTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepositoryDataJPA userRepo;

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
        TypeReference<List<User>> personList = new TypeReference<List<User>>() {};
        var userList = mapper.readValue(body, personList);

        org.hamcrest.MatcherAssert.assertThat(userList,
                Matchers.hasItems(MOCK_USERS.stream().map(u -> Matchers.samePropertyValuesAs(u))
                        .collect(Collectors.toList()).toArray(new Matcher[]{})));

        assertThat(userList).usingRecursiveComparison()
                .ignoringFields("id", "created", "modified")
                .ignoringAllOverriddenEquals()
                .isEqualTo(EXPECTED_USERS);

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
}
