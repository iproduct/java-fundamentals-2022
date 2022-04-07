package course.java.algorithms.sorting;

import java.util.Arrays;

public class MergeSort {
    public static void merge(int[] a, int start, int middle, int end) {
        if(end - middle == 0 || a[middle] < a[middle + 1]) {
            return;
        }
        int nLeft = middle - start + 1;
        int nRight = end - middle;
        int[] L = new int[nLeft];
        int[] R = new int[nRight];

        for(int i = 0; i < nLeft; i++) {
            L[i] = a[start + i];
        }
        for(int i = 0; i < nRight; i++) {
            R[i] = a[middle + 1 + i];
        }

        // merge L and R until one is finished
        int i = 0, j = 0, k = start;
        while(i < nLeft && j < nRight) {
            if(L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
            k++;
        }

        // copy remaining elements at the end
        while(i < nLeft){
            a[k++] = L[i++];
        }
        while(j < nRight){
            a[k++] = R[j++];
        }
    }

    public static void mergeSort(int[] a, int start, int end) {
        if(start < end) {
            int middle = (start + end) / 2;
            // sort left and right sub-arrays recursively - recursion step
            mergeSort(a, start, middle);
            mergeSort(a, middle + 1, end);
            //merge left and right sorted arrays
            merge(a, start, middle, end);
        }
    }

    public static void main(String[] args) {
        int[] a = {34, 4565, 4, 345, 34, 67, 4, 56, 345, 73, 986, 53, 12, 7, 85};
        mergeSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
