package course;

import course.java.dao.UserRepository;
import course.java.dao.impl.UserRepositoryMemoryImpl;
import course.java.model.Role;
import course.java.model.User;
import course.java.model.UserBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        var users = List.of(
                new User("Ivan", "Petrov", 25, "ivan", "ivan123", Role.ADMIN),
                new User("Nadezda", "Todorova", 29, "nadia", "nadia123", Role.ADMIN),
                new User( "Hristo", "Yanakiev", 23, "hristo", "hris123", Role.ADMIN),
                new User("Gorgi", "Petrov", 45, "georgi", "gogo123", Role.ADMIN),
                new User( "Maria", "Manolova", 22, "maria", "mari123", Role.ADMIN)
        );
        UserRepository userRepo = new UserRepositoryMemoryImpl();
        for (var user : users) {
            userRepo.create(user);
        }
        var newUser = new UserBuilder().setName("Stefan Dimitrov").setAge(43)
                .setUsername("stefan").setPassword("stef123").setRole(Role.USER)
                .build();
        userRepo.create(newUser);
        var ivanOptional = userRepo.findByUsername("ivan");
        if(ivanOptional.isPresent()) {
            var ivan = ivanOptional.get();
            ivan.setLastName("Hristov");
            ivan.setPassword("new_pass");
            userRepo.update(ivan);
        }
        userRepo.deleteById(2L);
        for (User user : userRepo.findAll()) {
            System.out.println(user.format());
        }
    }
}
