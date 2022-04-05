package course.java.algorithms.csp;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class NQueens {
    public static final int N = 8;
    public static final int[] X = new int[N];
    public static final List<List<Integer>> solutions = new ArrayList<>();
    private static int k = 0;

    public static boolean isSafe(int[] positions, int row, int col) {
        for(int i = 0; i < row; i++){
            var posCol = positions[i];
            if(posCol == col || abs(posCol - col) == row - i) {
                return false;
            }
        }
        return true;
    }

    public static void printSolution(int[] positions){
        System.out.printf("Solution %d%n", ++k);
        for(int  i = 0; i <positions.length; i++) {
            for(int j = 0; j < positions.length; j ++) {
                if(positions[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean solveNQueens(int[] positions, int row){
        var size = positions.length;
        if(row >= size) {
            printSolution(positions);
            return true;
        }
        for(int i = 0; i < size; i++) {
            if(isSafe(X, row, i)) {
                X[row] = i;
                solveNQueens(X, row + 1);
                X[row] = 0;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        solveNQueens(X, 0);
    }
}
