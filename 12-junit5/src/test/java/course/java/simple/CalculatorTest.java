package course.java.simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("fast")
@Tag("service")
@Slf4j
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {
    private Calculator calculator;

    @BeforeAll
    public void beforeAll() {
        log.info("Before all tests");
    }

    @AfterAll
    public void afterAll() {
        log.info("After all tests");
    }

    @BeforeEach
    public void setup() {
        calculator = new Calculator();
        log.info("Setup Calculator test");
    }

    @AfterEach
    public void cleanup() {
        calculator = null;
        log.info("Cleanup Calculator after test");
    }

    @Test
    public void givenXandY_whenAdd_thenAddResultXplusY() {
        log.info("Testing add");
        // test
        int result = calculator.add(5, 8);
        // verify
        assertEquals(13, result, "Addition should work");
    }

    @Test
    public void givenXandY_whenDivide_thenDivisionResultTimesYinX() {
        log.info("Testing divide");
        // test
        int result = calculator.divide(23, 5);
        // verify
        assertEquals(4, result, "Regular division should work");
    }

    @Test
    public void givenXandNegativeY_whenDivide_thenDivisionResultTimesYinX() {
        log.info("Testing divide ny negative");
        // test
        int result = calculator.divide(23, -5);
        // verify
        assertEquals(-4, result, "Regular division should work");
    }

    @Test
    public void givenXandZero_whenDivide_thenThrowsArtithmeticException() {
        log.info("Testing divide by zero - assertThrowing()");
        // verify Exception thrown
        var exception = assertThrows(ArithmeticException.class,
                () -> calculator.divide(23, 0), "Should throw ArtithmenticException");
        assertEquals("/ by zero", exception.getMessage(), "Messsage should be '/ by zero'");
    }
}
