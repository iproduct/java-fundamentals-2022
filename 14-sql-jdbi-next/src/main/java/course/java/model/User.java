package course.java.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.StringJoiner;

//@Entity
//@Table(name = "users",
//        uniqueConstraints = @UniqueConstraint(name = "UC_username", columnNames = {"username"}))
public class User extends Person {
    @Size(min = 2, max = 15)
//    @Basic(optional = false)
//    @Column(unique = true)
    private String username;
    @Pattern(regexp = "^.*(?=.{8,15})(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!#$%&?+]).*$",
            message = "Password should contain at least 8 characters, at least one digit, capital letter, and none-letter character")
    private String password;
//    @Enumerated(EnumType.STRING)
    private Role role; // default value - init during declaration
    private boolean active = true;
    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime modified = LocalDateTime.now();

    public User() {
//        super();
    }

    public User(String firstName, String lastName, int age, String username, String password, Role role, String phone) {
        super(firstName, lastName, age, phone);
        this.username = username;
        this.password = password;
        this.role = role; // init in constructor
    }

    public User(Long id, String firstName, String lastName, int age, String username, String password, Role role, boolean active, String phone) {
        super(id, firstName, lastName, age, phone);
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public User(Long id, String firstName, String lastName, int age, String phone, String username, String password, Role role, boolean active, LocalDateTime created, LocalDateTime modified) {
        super(id, firstName, lastName, age, phone);
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = active;
        this.created = created;
        this.modified = modified;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

//    public String getInfo() {
//        return fetchInfo();
//    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + getId())
                .add("firstName='" + getFirstName() + "'")
                .add("lastName='" + getLastName() + "'")
                .add("age=" + getAge())
                .add("phone='" + getPhone() + "'")
                .add("username='" + username + "'")
                .add("password='" + password + "'")
                .add("role=" + role)
                .add("active=" + active)
//                .add("info='" + getInfo() + "'")
                .add("created=" + created)
                .add("modified=" + modified)
                .toString();
    }

    @Override
    public String format() {
        return String.format("%s %-15.15s | %-15.15s | %-6.6s | %-4s |", super.format(), username, password, role, active);
    }

    // utility methods
    private String fetchInfo() {
        return String.format("This is extended information about user '%s' ...", username);
    }

}
