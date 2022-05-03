package course.java.simple;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

@Tag("fast")
@Tag("service")
public class AssumptionDemo {
    private final Calculator calculator = new Calculator();

//    @Test
//    void testOnlyInCIServer() {
//        assumeTrue("CI".equals(System.getenv("ENV")));
//        assertEquals(42, calculator.multiply(6, 7));
//    }
    @Test
    void testOnlyOnDeveloperWorkstation() {
        assumeTrue("DEV".equals(System.getenv("ENV")));
        assertEquals(BigInteger.valueOf(1000000007), calculator.generateNextPrime(BigInteger.valueOf(1000000000)));
    }
    @Test
    void testInAllEnvironments() {
        assumingThat("CI".equals(System.getenv("ENV")),
                () -> assertEquals(42, calculator.multiply(6, 7)));

        assumingThat("DEV".equals(System.getenv("ENV")),
                () -> assertEquals(BigInteger.valueOf(1000000007), calculator.generateNextPrime(BigInteger.valueOf(1000000000))));
    }


}
