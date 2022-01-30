package advanced.c3.class7;

import java.util.Arrays;

public class C01_MinLengthForSort {

    public static int getMinLength(int[] arr) {
        int leftMax = arr[0];
        int rightMin = arr[arr.length - 1];
        int leftIndex = arr.length - 1;
        int rightIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < leftMax) {
                leftIndex = i;
            }
            leftMax = Math.max(arr[i], leftMax);
        }

        for (int j = arr.length - 1; j >= 0; j--) {
            if (arr[j] > rightMin) {
                rightIndex = j;
            }
            rightMin = Math.min(arr[j], rightMin);
        }
        return leftIndex - rightIndex + 1;
    }


    public static int getMinLengthZuo(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int min = arr[arr.length - 1];
        int noMinIndex = -1;
        for (int i = arr.length - 2; i != -1; i--) {
            if (arr[i] > min) {
                noMinIndex = i;
            } else {
                min = Math.min(min, arr[i]);
            }
        }
        if (noMinIndex == -1) {
            return 0;
        }
        int max = arr[0];
        int noMaxIndex = -1;
        for (int i = 1; i != arr.length; i++) {
            if (arr[i] < max) {
                noMaxIndex = i;
            } else {
                max = Math.max(max, arr[i]);
            }
        }
        return noMaxIndex - noMinIndex + 1;
    }

    // for test
    public static int right(int[] arr) {
        int n = arr.length;
        int[] copy = new int[n];
        System.arraycopy(arr, 0, copy, 0, n);
        Arrays.sort(copy);
        int left = 0, right = n - 1;
        for (int i = 0; i < n; i++) {
            if (arr[i] != copy[i]) {
                left = i;
                break;
            }
        }
        for (int j = n - 1; j >= 0; j--) {
            if (arr[j] != copy[j]) {
                right = j;
                break;
            }
        }
        return right - left + 1;
    }
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static int[] generateRandomSortishArray(int len, int val) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * val) + 1;
        }
        Arrays.sort(res);
//        int left = (int) (Math.random() * (len / 2));
//        int right = (int) (Math.random() * (len / 2 + 1));
//        for (int i = left, j = right; i <= j; i++, j--) {
//            swap(res, i, j);
//        }
        return res;
    }



    public static void main(String[] args) {
        int len = 10, max = 20, times = 2;
        int[] arr;
        System.out.println("Go");
        for (int i = 0; i < times; i++) {
            arr = generateRandomSortishArray(len, max);
            System.out.println(Arrays.toString(arr));
            int res1 = getMinLength(arr);
            int res2 = getMinLengthZuo(arr);
            System.out.println(res2);
            if (res1 != res2) {
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Done");
    }

}
