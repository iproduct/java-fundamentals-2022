package course.java.arrays;

import java.util.Random;

public class MultiDimArray {
    public static final int NUM_BOOKS = 5;
    public static final int NUM_MONTHS = 12;
    private static Random rand = new Random();


    public static String formatAsTable(int[][] data) {
        return ""; // TODO Your code here
    }

    public static String formatAsTableWithTotals(int[][] data) {
        StringBuilder sb = new StringBuilder();
        int grandTotal = 0;
        int rowSum = 0;
        int[] columnSums = new int[NUM_MONTHS];
        for (int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[i].length; ++j) {
                rowSum += data[i][j];
                columnSums[j] += data[i][j];
                grandTotal += rowSum;
                sb.append(String.format("| %4d ", data[i][j]));
            }
            sb.append("| = ").append(rowSum).append("\n");

            rowSum = 0;
        }
        for (int i = 0; i < NUM_MONTHS; ++i) {
            sb.append(String.format("  %4d ", columnSums[i]));
        }
        sb.append(String.format("  %4d ", grandTotal));
        return sb.toString();
    }

    public static int[] findFirstIndexesSalesGreaterThan(int[][] data, int threshold) {
        int[] result = null;
        outer_label:
        for (int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[i].length; ++j) {
                if(data[i][j] > threshold){
                    result = new int[]{i, j};
                    break outer_label;
                }
            }
        }
        // Do more calculations here ...
        return result;
    }

    public static boolean findIfAllHasSalesGreaterThan(int[][] data, int threshold) {
        boolean result = true;
        outer_label:
        for (int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[i].length; ++j) {
                if(data[i][j] > threshold){
                    continue outer_label;
                }
            }
            result = false;
            break outer_label;
        }
        // Do more calculations here ...
        return result;
    }

    public static void main(String[] args) {
        int[][] sales = new int[NUM_BOOKS][NUM_MONTHS];
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                sales[i][j] = rand.nextInt(900) + 300;
            }
        }
        System.out.println(formatAsTableWithTotals(sales));
        var threshold = 1000;
        var indexes = findFirstIndexesSalesGreaterThan(sales, threshold);
        if(indexes != null) {
            System.out.printf("[%d,%d] = %d > %d%n",
                    indexes[0], indexes[1], sales[indexes[0]][indexes[1]], threshold);
        } else {
            System.out.println("No sales grater than " + threshold);
        }
        System.out.printf("Do all books have month sale > %2$d : %1$b%n",
               findIfAllHasSalesGreaterThan(sales, threshold),  threshold);
    }
}










