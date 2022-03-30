package course;

import course.java.model.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var samplePersons = List.of(
                new User(1L, "Ivan", "Petrov", 25, "ivan", "ivan123", Role.ADMIN,true),
                new User(2L, "Nadezda", "Todorova", 29, "nadia", "nadia123", Role.ADMIN,true),
                new User(3L, "Hristo", "Yanakiev", 23, "hristo", "hris123", Role.ADMIN,true),
                new User(4L, "Gorgi", "Petrov", 45, "georgi", "gogo123", Role.ADMIN,true),
                new User(5L, "Maria", "Manolova", 22, "maria", "mari123", Role.ADMIN,true)
        );
        var allUsers = new ArrayList<>(samplePersons);
        var newUser = new UserBuilder().setId(6L).setName("Stefan Dimitrov").setAge(43)
                .setUsername("stefan").setPassword("stef123").setRole(Role.USER)
                .build();
        allUsers.add(newUser);
        for (User user : allUsers) {
            System.out.println(user.format());
        }
    }
}
