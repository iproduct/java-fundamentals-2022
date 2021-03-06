package course.java.model;

import course.java.dao.Identifiable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@MappedSuperclass
public class Person implements Identifiable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=2, max=15)
    private String firstName;
    @Size(min=2, max=15)
    private String lastName;
    @Positive
    private int age;
    @Pattern(regexp = "^[\\+()\\d\\s]{8,20}$", message = "Phone should contain only digits, spaces, '(', ')' and '+' characters")
    private String phone;

    public Person() {
    }

    public Person(String firstName, String lastName, int age, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phone = phone;
    }

    public Person(Long id, String firstName, String lastName, int age, String phone) {
        this(firstName, lastName, age, phone);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return getId() != null ? getId().equals(person.getId()) : person.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", age=").append(age);
        sb.append(", phone='").append(phone).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String format() {
        return String.format("| %3.3s | %-15.15s | %-15.15s | %-3d | %-15.15s |",
                id, firstName, lastName, age, phone);
    }

    public String format(String format) {
        return String.format(format, id, firstName, lastName, age, phone);
    }
}
