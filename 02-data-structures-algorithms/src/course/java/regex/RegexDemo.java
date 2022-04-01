package course.java.regex;

import course.java.dao.UserRepository;
import course.java.dao.impl.UserRepositoryMemoryImpl;
import course.java.model.Role;
import course.java.model.User;

import java.util.List;
import java.util.regex.Pattern;

public class RegexDemo {
    public static final String COUNTRY_CODE_REGEX = "[0+]\\s+\\((?<code>\\d+)\\)\\s*(?<number>[\\d\\s]{5,12})";
    public static void main(String[] args) {
        var users = List.of(
                new User("Ivan", "Petrov", 25, "ivan", "ivan123", Role.ADMIN,
                        "+(359) 2 9765423"),
                new User("Nadezda", "Todorova", 29, "nadia", "nadia123", Role.ADMIN,
                        "+(359) 889 1523"),
                new User("Hristo", "Yanakiev", 23, "hristo", "hris123", Role.ADMIN,
                        "+(11) 9876543"),
                new User("Gorgi", "Petrov", 45, "georgi", "gogo123", Role.ADMIN,
                        "+(1) 48995502"),
                new User("Maria", "Manolova", 22, "maria", "mari123", Role.ADMIN,
                        "+(1) 98765432")
        );
        UserRepository userRepo = new UserRepositoryMemoryImpl();
        for (var user : users) {
            userRepo.create(user);
        }
        var pattern = Pattern.compile(COUNTRY_CODE_REGEX);
        for (User user : userRepo.findAll()) {
            var matcher = pattern.matcher(user.getPhone());
            System.out.printf("%s | %s |%n", user.format(), "");
        }
    }
}
