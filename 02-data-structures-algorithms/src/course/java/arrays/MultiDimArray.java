package course.java.arrays;

import java.util.Random;

public class MultiDimArray {
    public static final int NUM_BOOKS = 5;
    public static final int NUM_MONTHS = 12;
    private static Random rand = new Random();

    public static String formatAsTable(int[][] data){
        return ""; // TODO Your code here
    }

    public static void main(String[] args) {
        int[][] sales = new int[NUM_BOOKS][NUM_MONTHS];
        for(int i = 0; i < sales.length; i++) {
            for(int j = 0; j < sales[i].length; j++) {
                sales[i][j] = rand.nextInt(900) + 300;
            }
        }
        System.out.println(formatAsTable(sales));
    }
}










