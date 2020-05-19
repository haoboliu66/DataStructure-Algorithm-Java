package sorting;



import static sorting.BubbleSort.*;

/**
 * @author andy-liu
 * @date 2020/5/7 - 7:49 PM
 */
public class InsertionSort {


    public static void insertionSort(int[] arr, int left, int right){
        for(int i = left + 1; i < right + 1; i++){   // sort 0 ... i
            for(int j = i - 1; j >= left && arr[j + 1] < arr[j]; j--){ // each time sort i - 1 ... 0
                swap(arr,j,j + 1);
           }
        }
    }

    public static void swap(int[] arr, int i, int j){
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
            boolean res = isEqual(arr, copyArr);
            if (!res) {
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Done");
    }


}
