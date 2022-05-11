package course.java.springrest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import course.java.dao.UserRepository;
import course.java.dao.UserRepositoryDataJPA;
import course.java.model.Person;
import course.java.model.Role;
import course.java.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.HashMap;
import java.util.List;

import static org.mockito.BDDMockito.given;
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
                .andDo(result -> log.info("HTTP Resposne: {}", result.getResponse().getContentAsString()))
                .andExpect(jsonPath("$.length()").value(MOCK_USERS.size()))
                .andExpect(jsonPath("$[0].username").value(MOCK_USERS.get(0).getUsername()));

        var body = response.andReturn().getResponse().getContentAsString();
        TypeReference<List<Person>> personList =  new   TypeReference<List<Person>>() {};
        var userList = mapper.readValue(body, personList );
        assertThat(userList).usingRecursiveComparison()
                .ignoringFields("id", "created", "modified")
                .ignoringAllOverriddenEquals()
                .isEqualTo(MOCK_USERS);

    }

    public List<User> MOCK_USERS = List.of (
            new User(1L, "Ivan", "Petrov", 25, "ivan", "Ivan123#",
                    Role.ADMIN, true,"+(359) 887 894356"),
            new User(2L, "Nadezda", "Todorova", 29, "nadia", "Nadia123#",
                    Role.READER, true,"+(359) 889 123456"),
            new User(3L, "Hristo","Yanakiev", 23, "hristo", "Hris123#",
                    Role.ADMIN, true, null)
    );
}
