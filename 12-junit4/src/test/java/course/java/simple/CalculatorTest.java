package course.java.simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@Slf4j
public class CalculatorTest {
    private Calculator calculator;

    @BeforeClass
    public static void beforeAll(){
        log.info("Before all tests");
    }

    @AfterClass
    public static void afterAll(){
        log.info("After all tests");
    }

    @Before
    public void setup(){
        calculator = new Calculator();
        log.info("Setup Calculator test");
    }

    @After
    public void cleanup(){
        calculator = null;
        log.info("Cleanup Calculator after test");
    }

    @Test
    public void givenXandY_whenAdd_thenAddResultXplusY() {
        log.info("Testing add");
        // test
        int result = calculator.add(5, 8);
        // verify
        assertEquals("Addition should work", 13, result);
    }

    @Test
    public void givenXandY_whenDivide_thenDivisionResultTimesYinX() {
        log.info("Testing divide");
        // test
        int result = calculator.divide(23, 5);
        // verify
        assertEquals("Regular division should work", 4, result);
    }

    @Test
    public void givenXandNegativeY_whenDivide_thenDivisionResultTimesYinX() {
        log.info("Testing divide ny negative");
        // test
        int result = calculator.divide(23, -5);
        // verify
        assertEquals("Regular division should work", -4, result);
    }

    @Test(expected = ArithmeticException.class)
    public void givenXandZero_whenDivide_thenArtithmeticException() {
        log.info("Testing divide by zero");
        // test
        int result = calculator.divide(23, 0);
    }

    @Test
    public void givenXandZero_whenDivide_thenThrowsArtithmeticException() {
        log.info("Testing divide by zero - assertThrowing()");
        // verify Exception thrown
        assertThrows("Should throw ArtithmenticException", ArithmeticException.class,
                () -> calculator.divide(23, 0));
    }
}
