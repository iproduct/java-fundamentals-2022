package course.java.view;

import course.java.model.Credentials;

import java.util.Scanner;
import java.util.regex.Pattern;

import static course.java.util.UserValidator.*;

public class CredentialsDialog implements EntityDialog<Credentials> {
    public static Scanner sc = new Scanner(System.in);

    @Override
    public Credentials input() {
        String username = null;
        while (username == null) {
            System.out.println("Username:");
            var ans = sc.nextLine().trim();
            if (!Pattern.matches(USERNAME_REGEX, ans)) {
                System.out.println("Error: The username should be between 2 and 15 characters long and contain only word characters.");
            } else {
                username = ans;
            }
        }

        String password = null;
        while (password == null) {
            System.out.println("Password:");
            var ans = sc.nextLine().trim();
            if (!Pattern.matches(PASSWORD_REGEX, ans)) {
                System.out.println("Error: The password should be between 8 and 15 characters long and should have atleast one small, capital letter, special symbol and digit.");
            } else {
                password = ans;
            }
        }

        return new Credentials(username, password);
    }
}
