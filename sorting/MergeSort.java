package sorting;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @author andy-liu
 * @date 2020/5/7 - 7:32 PM
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {3,5,6,1,0,7,9,2,-4};
        mergeSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * recursive implementation
     * @param arr
     */
    public static void mergeSort1(int[] arr){
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
        int leftPointer = left; // left part scanner
        int rightPointer = mid + 1; // right part scanner
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


    /**
     * Iterative implementation
     * @param arr
     */
    public static void mergeSort2(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        int N = arr.length;
        int mergeSize = 1;   // merge group size = mergeSize * 2
        while(mergeSize < N){
            int left = 0;
            while(left < N){
                /*
                firstly locate mid and right of each group
                 */
                int mid = left + mergeSize - 1;  // mid of merge group
                int right = Math.min(left + 2 * mergeSize - 1, N - 1); // right of merge group
                /*
                 if mid reach the end of the array, meaning there is no right part,
                    and left part is already ordered
                 */
                if(mid > N - 1){
                    break;
                }
                // from left to N - 1, merge each group
                merge(arr, left, mid, right);
                // locate next group
                left = right + 1;
            }
            // Integer MAX_VALUE overflow
            if(mergeSize * 2 > N){
                break;
            }

            mergeSize <<= 1;

        }


    }

    @Test
    public void ttt(){
        Random random = new Random();
        int mergeSize = random.nextInt(500);
        System.out.println(mergeSize);
        int left = 1;
        int right = left + 2 * mergeSize - 1;
        int mid = left + (right - left) / 2;
        System.out.println(mergeSize);
        System.out.println(mid + " ,,, " + (left + mergeSize));
        System.out.println(mid == (left + mergeSize));
    }


}
