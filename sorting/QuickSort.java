package sorting;

import org.junit.Test;

import java.util.Arrays;

import static sorting.BubbleSort.*;

/**
 * @author andy-liu
 * @date 2020/5/7 - 9:19 PM
 */
public class QuickSort {

    /** divide the array by num, numbers less or equal than num on the left side,
     * numbers greater on the right side of the array
     * */
    public static void divideArray(int[] arr, int num){
        if(arr == null || arr.length < 2){
            return;
        }
        int lessOrEqual = -1;
        int i = 0;
        while(i < arr.length){
            if(arr[i] <= num){
                swap(arr, i++, ++lessOrEqual);
            }else{
                i++;
            }
        }
    }

    /** !!! Do not swap the same index of an array !!!   */
    private static void swap(int[] arr, int index, int i) {
//        arr[index] = arr[index] + arr[i];
//        arr[i] = arr[index] - arr[i];
//        arr[index] = arr[index] - arr[i];

//        arr[index] = arr[index] ^ arr[i];
//        arr[i] = arr[index] ^ arr[i];
//        arr[index] = arr[index] ^ arr[i];

        int temp = arr[index];
        arr[index] = arr[i];
        arr[i] = temp;

    }


    /**
     *  divide the array by num, numbers less than num on the left side,
     *  numbers greater on the right side of the array, equal in the middle
     */
    public static void netherlandsFlag(int[] arr, int num){
        if(arr == null || arr.length < 2){
            return;
        }
        int less = -1;
        int more = arr.length;
        int i = 0;
        while (i < more){

            if(arr[i] > num){
                swap(arr,i,--more);

            }else if(arr[i] < num){
                swap(arr,i++,++less);

            }else{
                i++;
            }
        }
    }

    /** 在left到right范围内进行荷兰国旗算法, 默认以arr[right]为pivot做partition, 返回相等区的两个边界index   */
    public static int[] netherlandsFlag(int[] arr, int left, int right){
        if(left > right){
            return new int[]{-1, -1};
        }
        if(left == right){
            return new int[]{left, right};
        }

        int less = left - 1;  // less than area
        int more = right + 1; // more than area
        int index = left;
        int num = arr[right];
        while(index < more){

            if(arr[index] == num){
                index++;

            }else if(arr[index] < num){
                swap(arr,index++, ++less);

            }else{
                swap(arr,index,--more);
            }
        }
        return new int[]{less + 1, more - 1};
    }

    /**
     * partition within left ... right of arr, using arr[right] as pivot
     * @return index of the first occurrence of pivot
     * */
    public static int partition(int[] arr, int left, int right){
        if(left > right){
            throw new RuntimeException("Invalid Parameters");
        }
        if(left == right){
            return left;
        }

        int less = -1;
        int more = right + 1;
        int index = 0;
        int num = arr[right];
        while(index < more){
            if(arr[index] == num){
                index++;
            }else if(arr[index] > num){
                swap(arr, index, --more);
            }else{
                swap(arr, index++, ++less);
            }
        }
        return less + 1;
    }

    @Test
    public void testPartition(){
        int[] arr = {3,5,4,0,4,6,7,2,5};
        int index = partition(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        System.out.println(index);
    }

    /**  fix one number each time */
    public static void quickSort1(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        process1(arr,0, arr.length - 1);
    }

    public static void process1(int[] arr, int left, int right){
        if(left >= right){
            return;
        }
        int pivot = partition(arr, left, right);
        process1(arr,left, pivot - 1);
        process1(arr,pivot + 1, right);
    }
    @Test
    public void testQuickSort1(){
        int[] arr = {3,5,4,0,4,6,7,2,5};
        quickSort1(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**  fix several equal numbers each time */
    public static void quickSort2(int[] arr){
        if(arr == null | arr.length < 2){
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    public static void process2(int[] arr, int left, int right){
        if(left >= right){
            return;
        }
        int[] indices = netherlandsFlag(arr, left, right); // range of equal numbers
        process2(arr,left,indices[0] - 1);
        process2(arr,indices[1] + 1, right);
    }

    @Test
    public void testQuickSort2(){
        int[] arr = {3,5,4,0,4,6,7,2,5,-3};
        quickSort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    /** quickSort1 quickSort2, time complexity: O(N^2)
     *  quickSort3  time complexity: O(N*logN)
     *
     *  balanced partition VS unbalanced partition
     *  quickSort3  probability and long-term expectation
     *
     * */
    public static void quickSort3(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        process3(arr,0,arr.length - 1);
    }

    public static void process3(int[] arr, int left, int right){
        if(left >= right){
            return;
        }
        /*
         choose a random index within left...right, and swap arr[index] with arr[right]
         so that the worse situation is a possibility
         */
        int random = left + (int)(Math.random() * (right - left + 1));
        swap(arr,random, right);
        int[] equalArea = netherlandsFlag(arr, left, right);
        process3(arr, left, equalArea[0] - 1);
        process3(arr,equalArea[1] + 1, right);
    }

    @Test
    public void testQuickSort3(){
        int[] arr = {3,5,4,0,4,6,7,2,5,-3};
        quickSort3(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int maxSize = 200;
        int maxValue = 50000;
        int times = 1000000;
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(200, 50000);
            int[] copyArr = copyArray(arr);
            quickSort3(arr);
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
