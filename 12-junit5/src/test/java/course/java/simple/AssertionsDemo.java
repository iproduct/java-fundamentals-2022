package course.java.simple;

import course.java.extensions.TimingExtension;
import course.java.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigInteger;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(TimingExtension.class)
@Tag("service")
@Slf4j
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

    @Tag("fast")
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
                            () -> assertTrue(actualFirstName.endsWith("e"), "First name should end with 'n''")
                    );
                });
    }

    @Test
    public void givenXandZero_whenDivide_thenThrowsArtithmeticException() {
        // verify Exception thrown
        var exception = assertThrows(ArithmeticException.class,
                () -> calculator.divide(23, 0), "Should throw ArtithmenticException");
        assertEquals("/ by zero", exception.getMessage(), "Messsage should be '/ by zero'");
    }

    @Test
    void timeoutNotExceeded() {
        assertTimeout(Duration.ofMillis(17), () -> {
            var prime = calculator.generateNextPrime(new BigInteger("100000000000000000000000"));
            log.info("Prime generated: {}", prime);
        });
    }

    @Test
    void timeoutNotExceededWithResult() {
        var prime = assertTimeout(Duration.ofMillis(5), () ->
                calculator.generateNextPrime(new BigInteger("100000000000000000000000"))
        );
        log.info("Prime generated: {}", prime);
    }

    @Test
    void timeoutNotExceededWithPreemptiveTermination() {
        var prime = assertTimeoutPreemptively(Duration.ofMillis(15), () ->
                calculator.generateNextPrime(new BigInteger("100000000000000000000000"))
        );
        log.info("Prime generated: {}", prime);
    }

    @Test
    void hamcrestAssertions() {
        assertThat(calculator.add(2, 3), is(lessThan(6)));
    }

    @Test
    void assertjAssertions() {
        org.assertj.core.api.AssertionsForClassTypes.assertThat(calculator.add(2, 3))
                .as("check Calculator.add(%d, %d)", 2, 3)
                .isLessThan(6);
    }

}
