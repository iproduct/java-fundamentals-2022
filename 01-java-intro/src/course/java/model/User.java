package course.java.model;

public class User extends Person{
    private String username;
    private String password;
    private Role role;
    private boolean active = true;

    public User() {
//        super();
    }

    public User(String firstName, String lastName, int age,  String username, String password, Role role) {
        super(firstName, lastName, age);
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public User(Long id, String firstName, String lastName, int age, String username, String password, Role role, boolean active) {
        super(id, firstName, lastName, age);
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = active;
    }
}
