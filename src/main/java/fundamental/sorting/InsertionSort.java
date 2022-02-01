package src.main.java.fundamental.sorting;


import java.util.Arrays;

public class InsertionSort {


    public static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i < right + 1; i++) {   // sort 0 ... i
            for (int j = i - 1; j >= left && arr[j + 1] < arr[j]; j--) { // each time sort i - 1 ... 0
                swap(arr, j, j + 1);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        int maxSize = 500;
        int maxValue = 80000;
        int times = 1000000;
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(200, 50000);
            int[] copyArr = copyArray(arr);
            insertionSort(arr, 0, arr.length - 1);
            comparator(copyArr);
            System.out.println(Arrays.toString(arr));
            boolean res = isEqual(arr, copyArr);
            if (!res) {
                System.out.println("Oops");
                break;
            }
        }

        System.out.println("Done");
    }

    // test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            return false;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            throw new RuntimeException("Null value");
        }
        int[] replica = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            replica[i] = arr[i];
        }
        return replica;
    }

    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }


}
