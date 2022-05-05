package course.java.model;

public class PersonBuilder {
    private Person builded = new Person();
    public PersonBuilder() {
    }

    public PersonBuilder setName(String name) {
        setNames(name);
        return this;
    }

    public PersonBuilder setAge(int age) {
        builded.setAge(age);
        return this;
    }
    public PersonBuilder setId(Long  id) {
        builded.setId(id);
        return this;
    }
    public Person build(){
        return builded;
    }

    // utility methods
    protected void setNames(String name) {
        String[] names = name.trim().split("\\s+");
        build().setFirstName(names[0]);
        if(names.length > 1) {
           build().setLastName(names[names.length - 1]);
        }
    }
}
