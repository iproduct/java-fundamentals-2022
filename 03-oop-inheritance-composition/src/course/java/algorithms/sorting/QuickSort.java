package course.java.algorithms.sorting;

import java.util.Arrays;

public class QuickSort {
    public static int partition(int[] a, int start, int end) {
        int pivot = a[end];
        var l = start;

        for (int i = start; i < end; i++) {
            if (a[i] <= pivot) {
                swap(a, l, i);
                l++;
            }
        }
        swap(a, l, end);
        return l;
    }

    public static void quickSort(int[] a, int start, int end) {
        if (start < end) {
            var pivotIndex = partition(a, start, end);
            quickSort(a, start, pivotIndex-1);
            quickSort(a, pivotIndex + 1, end);
        }
    }

    // utils
    public static void swap(int[] a, int i, int j) {
        var temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {34, 4565, 4, 345, 34, 67, 4, 56, 345, 73, 986, 53, 12, 7, 85};
        quickSort(a, 0, a.length - 1);
        System.out.println(Arrays.toString(a));
    }
}
