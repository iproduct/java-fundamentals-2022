package course.java.view;

import course.java.model.Role;
import course.java.model.User;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static course.java.util.UserValidator.PASSWORD_REGEX;
import static course.java.util.UserValidator.USERNAME_REGEX;

public class NewUserDialog implements EntityDialog<User> {
    public static Scanner sc = new Scanner(System.in);

    @Override
    public User input() {
        var user = new User();
        while (user.getFirstName() == null) {
            System.out.println("First Name:");
            var ans = sc.nextLine().trim();
            if (ans.length() < 2 || ans.length() > 15) {
                System.out.println("Error: The user fist name should be between 2 and 15 characters long.");
            } else {
                user.setFirstName(ans);
            }
        }
        while (user.getLastName() == null) {
            System.out.println("Last Name:");
            var ans = sc.nextLine().trim();
            if (ans.length() < 2 || ans.length() > 15) {
                System.out.println("Error: The user last name should be between 2 and 15 characters long.");
            } else {
                user.setLastName(ans);
            }
        }
        while (user.getUsername() == null) {
            System.out.println("Username:");
            var ans = sc.nextLine().trim();
            if (!Pattern.matches(USERNAME_REGEX, ans)) {
                System.out.println("Error: The username should be between 2 and 15 characters long and contain only word characters.");
            } else {
                user.setUsername(ans);
            }
        }
        while (user.getPassword() == null) {
            System.out.println("Password:");
            var ans = sc.nextLine().trim();
            if (!Pattern.matches(PASSWORD_REGEX, ans)) {
                System.out.println("Error: The username should be between 8 and 15 characters long and should have atleast one small, capital letter, special symbol and digit.");
            } else {
                user.setUsername(ans);
            }
        }
        while (user.getRole() == null) {
            var sj = new StringJoiner(", ");
            int i = 0;
            for(Role r :Role.values()) {
                  sj.add(++i + ") " + r.name());
            }
            System.out.printf("Role [%s]:%n", sj);
            var ans = sc.nextLine();
            var choice = -1;
            try {
                choice = Integer.parseInt(ans);
            } catch (NumberFormatException ex) {}
            if (choice < 1 || choice > Role.values().length) {
                System.out.printf("Error: Invalid choice - numbers only between 1 and %d.%n", Role.values().length);
            } else {
                user.setRole(Role.values()[choice-1]);
            }
        }
        System.out.println("User Info [optional - press <Enter> to skip]:");
        var ans = sc.nextLine();
        if (ans.length() > 0) {
            user.setInfo(ans);
        }

        return user;
    }
}
