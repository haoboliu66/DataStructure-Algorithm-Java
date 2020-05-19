package sorting;

import java.util.Arrays;

/**
 * @author andy-liu
 * @date 2020/5/7 - 9:17 PM
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {2,4,1,6,5,8,0,-4};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void selectionSort(int[] arr){
        for(int i=0; i<arr.length;i++){
            int minimum = i;
            for(int j= i; j<arr.length; j++){
                minimum = (arr[j] < arr[minimum])?j : minimum;
            }
            swap(arr,i,minimum);
        }
    }
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static boolean equals(int[] arr1, int[] arr2){
        if(arr1 == null || arr2 == null){
            return false;
        }
        if(arr1.length != arr2.length){
            return false;
        }
        for(int i=0; i<arr1.length;i++){
            if(arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }


}
