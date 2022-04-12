package course.java.arrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class MultiDimArray {
    public static final int NUM_BOOKS = 5;
    public static final int NUM_MONTHS = 12;
    private static final Random rand;

    static {
        rand = new Random();
    }

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
                if(j >= 1) {
                    rowSum += data[i][j];
                    columnSums[j] += data[i][j];
                }
                sb.append(String.format("| %4d ", data[i][j]));
            }
            grandTotal += rowSum;
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

    public static int findProductSalesByTotal(int[][] sales, int total, Comparator<int[]> comparator) {
        if(sales.length == 0) {
            return -1;
        }
        var qbe = new int[sales[0]. length];
        qbe[1] = total;
        return Arrays.binarySearch(sales, qbe, comparator);
    }

    public static void main(String[] args) {
        int[][] sales = new int[NUM_BOOKS][NUM_MONTHS];
        for (int i = 0; i < sales.length; i++) {
            sales[i][0] = i + 1;
            for (int j = 1; j < sales[i].length; j++) {
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
        var totlaSalesOfFirstProduct = Arrays.stream(sales[0]).skip(1).sum();

        System.out.println("\nAFTER SORTING:");
        var productSalesComp = new ProductSalesComparator().reversed();
        Arrays.sort(sales, productSalesComp);
        System.out.println(formatAsTableWithTotals(sales));

        System.out.println("\nBINARY SEARCH OF FIRST PRODUCT:");
        var firstProductIndex = findProductSalesByTotal(sales, totlaSalesOfFirstProduct, productSalesComp);
        System.out.printf("%d [Totlal: %d] -> %s%n",
                firstProductIndex, totlaSalesOfFirstProduct, Arrays.toString(sales[firstProductIndex]));

    }
}

class ProductSalesComparator implements Comparator<int[]>{

    @Override
    public int compare(int[] sales1, int[] sales2) {
        return Long.compare(
                Arrays.stream(sales1).skip(1).sum(),
                Arrays.stream(sales2).skip(1).sum()
        );
    }
}










