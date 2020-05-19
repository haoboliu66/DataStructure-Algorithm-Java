package sorting;


import static sorting.BubbleSort.*;

/**
 * @author andy-liu
 * @date 2020/5/7 - 9:17 PM
 */
public class SelectionSort {

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minimum = i;
            for (int j = i; j < arr.length; j++) {
                minimum = (arr[j] < arr[minimum]) ? j : minimum;
            }
            swap(arr, i, minimum);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int maxSize = 300;
        int maxValue = 100000;
        int times = 1000000;
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(200, 50000);
            int[] copyArr = copyArray(arr);
            selectionSort(arr);
            comparator(copyArr);
            boolean res = isEqual(arr, copyArr);
            if (!res) {
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Done");
    }

}
