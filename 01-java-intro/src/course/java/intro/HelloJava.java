package course.java.intro;

import course.java.model.Person;

public class HelloJava {
    public static void main(String[] args) {
        System.out.println("Hello Java World!");
        Person p1 = new Person(1L, "Ivan", "Petrov", 25);
        System.out.printf("Hello, %s%n", p1);
    }
}
