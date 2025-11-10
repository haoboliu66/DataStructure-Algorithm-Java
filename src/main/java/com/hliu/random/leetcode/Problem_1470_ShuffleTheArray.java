package com.hliu.random.leetcode;

import java.util.Arrays;

public class Problem_1470_ShuffleTheArray {


    public static int[] shuffle(int[] arr, int n) {
        if (arr == null || arr.length == 0) return null;

        int[] res = new int[2 * n];
        int index = 0;
        for (int i = 0; i < n; i++) {
            res[index++] = arr[i];
            res[index++] = arr[i + n];
        }

        return res;
    }

    public static int[] shuffle2(int[] arr, int n) {
        if (arr == null || arr.length == 0) return null;

        int[] res = new int[2 * n];
        int index = 0;
        for (int i = 0, j = n; i < n; i++, j++) {
            res[index++] = arr[i];
            res[index++] = arr[j];
        }
        return res;
    }


    public static void main(String[] args) {
        int[] arr = {2, 5, 1, 3, 4, 7};
        int[] res = shuffle(arr, 3);
        System.out.println(Arrays.toString(res));
    }

}
