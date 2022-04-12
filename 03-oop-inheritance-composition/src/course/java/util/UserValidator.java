package course.java.util;


import course.java.exception.ConstraintViolation;
import course.java.exception.ConstraintViolationException;
import course.java.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserValidator implements EntityValidator<User> {
    public static final String USERNAME_REGEX = "\\w{2,15}";

    //            ^.*              : Start
//            (?=.{8,})        : Length
//            (?=.*[a-zA-Z])   : Letters
//            (?=.*\d)         : Digits
//            (?=.*[!#$%&? "]) : Special characters " +
//            .*$              : End
    public static final String PASSWORD_REGEX = "^.*(?=.{8,15})(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!#$%&?+]).*$";
    public static final String PHONE_REGEX = "^[+()\\d\\s]{8,}$";

    @Override
    public void validate(User user) throws ConstraintViolationException {
        List<ConstraintViolation> violations = new ArrayList<>();
        var firstNameLength = user.getFirstName().trim().length();
        if (firstNameLength < 2 || firstNameLength > 15) {
            violations.add(
                    new ConstraintViolation(user.getClass().getName(), "firstName", user.getFirstName(),
                            "User firstName length should be between 2 and 15 characters"));
        }
        var lastNameLength = user.getLastName().trim().length();
        if(lastNameLength < 2 || lastNameLength > 15){
            violations.add(
                    new ConstraintViolation(user.getClass().getName(), "lastName", user.getFirstName(),
                            "User lastName length should be between 2 and 15 characters"));
        }
        var usernameLength = user.getUsername().trim().length();
        if (!Pattern.matches(USERNAME_REGEX, user.getUsername())) {
            violations.add(
                    new ConstraintViolation(user.getClass().getName(), "username", user.getUsername(),
                            "The username should be between 2 and 15 characters long and contain only word characters"));
        }
        if (!Pattern.matches(PASSWORD_REGEX, user.getPassword())) {
            violations.add(
                    new ConstraintViolation(user.getClass().getName(), "password", user.getPassword(),
                            "The password should be between 8 and 15 characters long and should have at least one small, capital letter, special symbol and digit"));
        }

        if(violations.size() > 0) {
            throw new ConstraintViolationException("Invalid user field", violations);
        }
    }
}
