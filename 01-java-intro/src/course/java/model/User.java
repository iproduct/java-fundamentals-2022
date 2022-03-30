package course.java.model;

public class User extends Person{
    private String username;
    private String password;
    private Role role = Role.USER; // default value - init during declaration
    private boolean active = true;
    private String info;

    public User() {
//        super();
    }

    public User(String firstName, String lastName, int age,  String username, String password, Role role) {
        super(firstName, lastName, age);
        this.username = username;
        this.password = password;
        this.role = role; // init in constructor
    }

    public User(Long id, String firstName, String lastName, int age, String username, String password, Role role, boolean active) {
        super(id, firstName, lastName, age);
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = active;
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

    public String getInfo() {
        if(info == null) { // lazy init - not thread safe!
            info = fetchInfo();
        }
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(getId());
        sb.append(", firstName='").append(getFirstName()).append('\'');
        sb.append(", lastName='").append(getLastName()).append('\'');
        sb.append(", age=").append(getAge());
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String format() {
        return String.format("%s %-15.15s | %-15.15s | %-5.5s | %-4s |", super.format(), username, password, role, active);
    }

    // utility methods
    private String fetchInfo() {
        return String.format("This is extended information about user '%s' ...", username);
    }

}
