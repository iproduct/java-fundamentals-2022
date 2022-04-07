package course.java.algorithms.sorting;


import course.java.model.Role;
import course.java.model.User;
import course.java.model.UserRoleFirstNameComparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;

class MyInt implements Comparable<MyInt> {
    int n;
    int index;

    public MyInt(int n, int val) {
        this.n = n;
        this.index = val;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public int compareTo(MyInt other) {
        return Integer.compare(this.n, other.n);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "[", "]")
                .add("" + n)
                .add("" + index)
                .toString();
    }
}

public class SimpleSorting {
    public static <T> int minIndex(T[] a, int start, int end, Comparator<T> comparator) {
        if(a.length == 0) {
            return -1;
        }
        var min = a[start];
        var minIndex = start;
        for (int i = start + 1; i < end; i++) {
            if (comparator.compare(a[i], min) < 0) {
                min = a[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static <T> void swap(T[] a, int i, int j) {
        var temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static <T> void selectionSort(T[] a, Comparator<T> comparator) {
        for (int i = 0; i < a.length - 1; i++) {
            var minIndex = minIndex(a, i, a.length, comparator);
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
//        insertionSort(myInts);
//        System.out.println(Arrays.toString(myInts));
        selectionSort(myInts,
                Comparator.<MyInt>naturalOrder()
                        .thenComparing(myInt -> Integer.valueOf(myInt.getIndex())));
        System.out.println(Arrays.toString(myInts));

        // sorting objects
        User[] users = {
                new User("Ivan", "Petrov", 25, "ivan", "ivan123", Role.ADMIN, ""),
                new User("Nadezda", "Todorova", 29, "nadia", "nadia123", Role.USER, ""),
                new User("Hristo", "Yanakiev", 23, "hristo", "hris123", Role.ADMIN, ""),
                new User("Gorgi", "Petrov", 45, "georgi", "gogo123", Role.USER, ""),
                new User("Petko", "Yanakiev", 23, "hristo", "hris123", Role.MANAGER, ""),
                new User("Stoyan", "Petrov", 45, "georgi", "gogo123", Role.ADMIN, ""),
                new User("Maria", "Manolova", 22, "maria", "mari123", Role.MANAGER, "")
        };

        selectionSort(users, new UserRoleFirstNameComparator());
        for(var u : users) {
            System.out.println(u);
        }
    }
}
