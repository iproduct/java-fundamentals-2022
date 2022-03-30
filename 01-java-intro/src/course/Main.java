package course;

import course.java.model.Person;
import course.java.model.PersonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> samplePersons = List.of(
                new Person(1L, "Ivan", "Petrov", 25),
                new Person(2L, "Nadezda", "Todorova", 29),
                new Person(3L, "Hristo", "Yanakiev", 23),
                new Person(4L, "Gorgi", "Petrov", 45),
                new Person(5L, "Maria", "Manolova", 22)
        );
        List<Person> allPersons = new ArrayList<>(samplePersons);
        Person newPerson = new PersonBuilder().setId(6L).setName("Stefan Dimitrov").setAge(43).build();
        allPersons.add(newPerson);
        for (Person p : allPersons) {
            System.out.println(p.format());
        }
    }
}
