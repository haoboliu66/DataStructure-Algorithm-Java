package sorting;

import org.junit.Test;

import java.util.Arrays;

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


    @Test
    public void testInsertion(){
        int[] arr = {3,13,5,1,7,8,5,0,-2,-7,-8,0};
        insertionSort(arr,0,arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }


}
