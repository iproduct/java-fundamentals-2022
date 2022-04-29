package course.java.simple;

import java.math.BigInteger;

public class Calculator {
    public int add(int x, int y) {
        return x + y;
    }

    public int multiply(int x, int y) {
        return x * y;
    }

    public int divide(int x, int y) {
        return x / y;
    }

    BigInteger generateNextPrime(BigInteger biggerThan){
        return biggerThan.nextProbablePrime();
    }
}
