package course.java.algorithms.sorting;


import java.util.Arrays;
import java.util.StringJoiner;

class MyInt implements Comparable<MyInt> {
    int n;
    int val;

    public MyInt(int n, int val) {
        this.n = n;
        this.val = val;
    }

    @Override
    public int compareTo(MyInt other) {
        return Integer.compare(this.n, other.n);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MyInt.class.getSimpleName() + "[", "]")
                .add("" + n)
                .add("" + val)
                .toString();
    }
}

public class SimpleSorting {
    public static int minIndex(MyInt[] a, int start, int end) {
        if(a.length == 0) {
            return -1;
        }
        var min = a[start];
        var minIndex = start;
        for (int i = start + 1; i < end; i++) {
            if (a[i].compareTo(min) < 0) {
                min = a[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void swap(MyInt[] a, int i, int j) {
        var temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void selectionSort(MyInt[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            var minIndex = minIndex(a, i, a.length);
            swap(a, i, minIndex);
        }
    }

    public static void insertionSort(MyInt[] a) {
        for (int i = 1; i < a.length; i++) {
            var temp = a[i];
            int j = i - 1;
            while(j >= 0 && temp.compareTo(a[j]) < 0){
                a[j + 1] = a[j];
                j--;
            }
            a[j+1] = temp;
        }
    }

    public static void main(String[] args) {
        int[] a = {34, 4565, 4, 345, 34, 67, 4, 56, 345, 73, 986, 53, 12, 7, 85};
        MyInt[] myInts = new MyInt[a.length];
        for(int i = 0; i < a.length; i++){
            myInts[i] = new MyInt(a[i], i);
        }
        insertionSort(myInts);
        System.out.println(Arrays.toString(myInts));
    }
}
