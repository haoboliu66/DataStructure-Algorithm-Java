package src.main.java.fundamental.sorting.mergesort;

import java.util.Arrays;

public class C01_SmallSumInArray {

    public static int getSmallSum(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        return process(arr, 0, arr.length - 1);
    }

    public static int process(int[] arr, int l, int r) {
        if (l >= r) {
            return 0;
        }
        int mid = l + (r - l) / 2;

        return process(arr, l, mid) +
                process(arr, mid + 1, r) +
                merge(arr, l, mid, r);
    }

    public static int merge(int[] arr, int L, int M, int R) {
        int[] buffer = new int[R - L + 1];
        int p1 = L, p2 = M + 1, index = 0;
        int res = 0;
        while (p1 <= M && p2 <= R) {

            if (arr[p1] < arr[p2]) {
                int count = R - p2 + 1;
                res += (arr[p1] * count);
                buffer[index++] = arr[p1++];
            } else {
                buffer[index++] = arr[p2++];
            }
        }

        while (p1 <= M) {
            buffer[index++] = arr[p1++];
        }
        while (p2 <= R) {
            buffer[index++] = arr[p2++];
        }
        for (int i = 0; i < buffer.length; i++) {
            arr[L + i] = buffer[i];
        }
        return res;
    }

    // for test
    public static int rightSmallSum(int[] arr) {
        int sum = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    sum += arr[j];
                }
            }
        }
        return sum;
    }

    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxSize = 200;
        int maxValue = 1000;
        int times = 20000;
        System.out.println("Started");
        for (int i = 0; i < times; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int[] copy = Arrays.copyOfRange(arr, 0, arr.length);
            int res1 = getSmallSum(arr);
            int res2 = rightSmallSum(copy);
            if (res1 != res2) {
                System.out.println("Oops");
                System.out.println(Arrays.toString(arr));
                System.out.println("res1: " + res1);
                System.out.println("res2: " + res2);
                break;
            }
        }
        System.out.println("Done");
    }

}
