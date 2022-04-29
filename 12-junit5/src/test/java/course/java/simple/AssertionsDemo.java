package course.java.simple;

import course.java.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AssertionsDemo {
    private Calculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new Calculator();
    }


    @Test
    void standardAssertions() {
        assertEquals(5, calculator.add(2, 3));
        assertEquals(6, calculator.divide(19, 3), "Optinal failure message");
        assertTrue(calculator.add(2, 3) < calculator.multiply(2, 3));
    }

    @Test
    void groupedAssertions() {
        //setup
        Person p = new Person(1L, "Jane", "Doe", 28, "+359889234567");
        assertAll("Person initialization",
                () -> assertTrue(p.getId() > 0, "ID should be positie number"),
                () -> assertEquals("Jane", p.getFirstName(), "First name should be set correctly"),
                () -> assertEquals("Doe", p.getLastName(), "Last name should be set correctly")
        );
    }

    @Test
    void dependentAssertions() {
        //setup
        Person p = new Person(1L, "Jane", "Doe", 28, "+359889234567");
        assertAll("Person initialization",
                () -> {
                    var actualFirstName = p.getFirstName();
                    assertNotNull(actualFirstName);
                    // Executed only if actualFirstName is not null
                    assertAll("First name should be valid",
                            () -> assertTrue(actualFirstName.startsWith("J"), "First name should start with 'J'"),
                            () -> assertTrue(actualFirstName.endsWith("n"), "First name should end with 'n''")
                    );
                });
    }


}
