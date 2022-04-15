package course.java.model;

public class MockUsers {
    public static final User[] MOCK_USERS = {
            new User("Ivan", "Petrov", 25, "ivan", "ivan123", Role.ADMIN,
                    "+(359) 887 894356"),
            new User("Nadezda", "Todorova", 29, "nadia", "nadia123", Role.READER,
                    "+(359) 889 123456"),
            new User("Hristo", "Yanakiev", 23, "hristo", "hris123", Role.ADMIN, ""),
            new User("Gorgi", "Petrov", 45, "georgi", "gogo123", Role.READER,
                    "+(1) 456778898"),
            new User("Petko", "Yanakiev", 23, "hristo", "hris123", Role.AUTHOR,
                    "+(11) 56457567"),
            new User("Stoyan", "Petrov", 45, "georgi", "gogo123", Role.ADMIN,
                    "+(91) 456456456"),
            new User("Maria", "Manolova", 22, "maria", "mari123", Role.AUTHOR, "")
    };
}
