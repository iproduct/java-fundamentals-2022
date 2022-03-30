package course.java.model;

public class PersonBuilder {
    private Person builded = new Person();
    public PersonBuilder() {
    }
    public PersonBuilder setName(String name) {
        String[] names = name.trim().split("\\s+");
        builded.setFirstName(names[0]);
        if(names.length > 1) {
            builded.setLastName(names[names.length - 1]);
        }
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
}
