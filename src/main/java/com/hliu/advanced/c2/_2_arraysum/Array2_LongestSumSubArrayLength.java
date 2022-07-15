package com.hliu.advanced.c2._2_arraysum;

import java.util.HashMap;
import java.util.Map;

public class Array2_LongestSumSubArrayLength {

    // 325. Maximum Size Subarray Sum Equals k
    //类似问题 src.main.java.com.hliu.leetcode 560.Subarray Sum Equals K;
    /*

    ----------------------------- 数组三连2 -----------------------------

    Q: 一个数组arr[] 有正数,有负数, 有0, 求累加和等于K的最长子数组
     */
    public static int getMaxLength(int[] arr, int K) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>(); // key:sum, val:index
        map.put(0, -1);
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i]; //0...i的累加和, 假设是1000, 如果目标K是200, 需要找800最早出现的位置
            if (map.containsKey(sum - K)) {
                len = Math.max(len, i - map.get(sum - K));
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }

        return len;
    }

    //brute force
    public static int right(int[] arr, int K) {
        int sum = 0;
        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                if (sum == K) {
                    len = Math.max(len, j - i + 1);
                }
            }
            sum = 0;
        }
        return len;
    }


    // for test
    public static int[] generateRandomArray(int size, int value) {
        int[] ans = new int[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) - (int) (Math.random() * value);
        }
        return ans;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 500000;

        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(len, value);
            int K = (int) (Math.random() * value) - (int) (Math.random() * value);
            int ans1 = getMaxLength(arr, K);
            int ans2 = right(arr, K);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println("K : " + K);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("test end");


        boolean ok = false;

        ok = ok || true;

    }

}
