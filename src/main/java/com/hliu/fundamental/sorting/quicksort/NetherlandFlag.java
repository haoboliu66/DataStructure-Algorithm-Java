package fundamental.sorting.quicksort;

import java.util.Arrays;

public class NetherlandFlag {


    public static void netherlandFlag(int[] arr, int k) {
        if (arr == null || arr.length <= 1) return;
        int less = -1, more = arr.length, index = 0;
        while (index < more) {
            if (arr[index] < k) {
                swap(arr, index++, ++less);
            } else if (arr[index] > k) {
                swap(arr, index, --more);
            } else {
                index++;
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static int[] netherlandFlag(int[] arr, int L, int R, int k) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int smaller = L - 1, greater = R + 1, index = 0;
        while (index < greater) {
            if (arr[index] < k) {
                swap(arr, index++, ++smaller);
            } else if (arr[index] > k) {
                swap(arr, index, --greater);
            } else {
                index++;
            }
        }
        return new int[]{smaller + 1, greater - 1};
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((Math.random() + 1) * maxSize)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxSize = 10;
        int maxValue = 20;
        int times = 1;
        System.out.println("Started");
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = arr[arr.length / 2];
            System.out.println(Arrays.toString(arr));
            int[] bound = netherlandFlag(arr, 0, arr.length - 1, k);
            System.out.println("k: " + k);
            System.out.println(Arrays.toString(arr));
            System.out.println(Arrays.toString(bound));
//            int res2 = rightSmallSum(copy);
//            if (res1 != res2) {
//                System.out.println("Oops");
//                System.out.println(Arrays.toString(arr));
//                System.out.println("res1: " + res1);
//                System.out.println("res2: " + res2);
//                break;
//            }
        }
        System.out.println("Done");
    }


}
