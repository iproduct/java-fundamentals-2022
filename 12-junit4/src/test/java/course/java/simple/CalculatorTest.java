package course.java.simple;

import lombok.extern.slf4j.Slf4j;
import org.junit.*;

import static org.junit.Assert.assertEquals;

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
}
