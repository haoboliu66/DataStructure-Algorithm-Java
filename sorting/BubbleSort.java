package sorting;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author andy-liu
 * @date 2020/5/7 - 7:48 PM
 */
public class BubbleSort{

    public static void main(String[] args) {
        int[] arr = {2,4,1,6,5,8,0,-4};
        bubbleSort(arr);
        int[] arr1 = {2,4,1,6,5,8,0,-4};
        Arrays.sort(arr1);
        System.out.println(equals(arr, arr1));
    }

    public static void bubbleSort(int[] arr){
        for(int i=arr.length-1; i>=0; i--){
            for(int j=0; j < i; j++){
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
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
