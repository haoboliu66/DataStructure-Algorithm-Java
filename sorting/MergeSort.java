package sorting;

import java.util.Arrays;

/**
 * @author andy-liu
 * @date 2020/5/7 - 7:32 PM
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {3,5,6,1,0,7,9,2,-4};
        mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    public static void mergeSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }

        process(arr,0,arr.length - 1);
    }

    public static void process(int[] arr, int left, int right){
        if(left == right){
            return;
        }

        int mid = left + ((right - left) >> 1);
        process(arr, left, mid);
        process(arr,mid + 1, right);
        merge(arr,left,mid,right);
    }

    public static void merge(int[] arr, int left, int mid, int right){
        int[] buffer = new int[right - left + 1];
        int index = 0;
        int leftPointer = left;
        int rightPointer = mid + 1;
        while(leftPointer <= mid && rightPointer <= right){
            buffer[index++] = arr[leftPointer] < arr[rightPointer]? arr[leftPointer++]: arr[rightPointer++];

        }
        while(leftPointer <= mid){
            buffer[index++] = arr[leftPointer++];
        }
        while(rightPointer <= right){
            buffer[index++] = arr[rightPointer++];
        }

        for(int i=0; i<buffer.length; i++){
            arr[left + i] = buffer[i];
        }


    }


}
