package course;

import course.java.model.Person;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = List.of(
               new Person(1L, "Ivan", "Petrov", 25),
               new Person(2L, "Nadezda", "Todorova", 29),
               new Person(3L, "Hristo", "Yanakiev", 23),
               new Person(4L, "Gorgi", "Petrov", 45),
               new Person(5L, "Maria", "Manolova", 22)
        );
        for(Person p : persons) {
            System.out.println(p.format("| %3.3s | %-10.10s | %-10.10s | %-3d |"));
        }
    }
}
