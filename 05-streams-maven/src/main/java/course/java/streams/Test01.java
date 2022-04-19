package course.java.streams;

import java.util.Arrays;
import java.util.Comparator;

public class Test01 {
    public static void main(String[] args) {
        int[][] points = { { 5, 6 }, { 3, 4 }, { 1, 2 }, { 7, 8 } };
        Arrays.stream(points).map(p -> Math.hypot(p[0], p[1]) ).filter(d -> d > 5).sorted(Comparator.reverseOrder()).forEach(System.out::println);
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    }
}
