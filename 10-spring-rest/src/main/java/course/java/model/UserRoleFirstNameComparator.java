package course.java.model;

import java.util.Comparator;

public class UserRoleFirstNameComparator implements Comparator<User> {
    @Override
    public int compare(User u1, User u2) {
        var roleCompareResult = u2.getRole().compareTo(u1.getRole());
        return roleCompareResult == 0 ?
                u1.getFirstName().compareToIgnoreCase(u2.getFirstName()) : roleCompareResult;
    }
}
