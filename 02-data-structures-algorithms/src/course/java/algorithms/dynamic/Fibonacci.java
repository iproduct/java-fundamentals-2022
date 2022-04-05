package course.java.algorithms.dynamic;

public class Fibonacci {
    public static final int N = 1000;
    public static long[] cache = new long[N + 1];
    static {
        cache[0] = 1;
        cache[1] = 1;
    }

    public static long fib(int n) {
        if(cache[n] != 0) return cache[n]; // recursion bottom
        cache[n] = fib(n-1) + fib(n-2);
        return cache[n];
    }

    public static void main(String[] args) {
        System.out.println(fib(N)); // O(2^N)
    }
}
