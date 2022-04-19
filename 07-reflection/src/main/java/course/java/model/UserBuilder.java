package course.java.model;

public class UserBuilder extends PersonBuilder{
    private User builded = new User();
    public UserBuilder() {
    }

    @Override
    public UserBuilder setName(String name) {
        setNames(name);
        return this;
    }

    @Override
    public UserBuilder setAge(int age) {
        builded.setAge(age);
        return this;
    }

    @Override
    public UserBuilder setId(Long  id) {
        builded.setId(id);
        return this;
    }

    public UserBuilder setUsername(String username){
        builded.setUsername(username);
        return this;
    }
    public UserBuilder setPassword(String password){
        builded.setPassword(password);
        return this;
    }
    public UserBuilder setRole(Role role){
        builded.setRole(role);
        return this;
    }
    public UserBuilder setActive(boolean active){
        builded.setActive(active);
        return this;
    }

    @Override
    public User build(){ //covariant return types
        return builded;
    }
}
