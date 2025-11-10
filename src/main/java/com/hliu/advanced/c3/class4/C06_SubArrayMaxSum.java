package com.hliu.advanced.c3.class4;

/*
 lc 53 https://leetcode.com/problems/maximum-subarray/

 Kadane's Algorithm
 */
public class C06_SubArrayMaxSum {

  public static int maxSum(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int max = Integer.MIN_VALUE;
    int cur = 0;
    for (int i = 0; i < arr.length; i++) {
      cur += arr[i];
      max = Math.max(cur, max);
      if (cur < 0) {
        cur = 0;
      }
    }
    return max;
  }

  public int maxSubArray(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int max = Integer.MIN_VALUE;
    int cur = 0;
    for (int i = 0; i < arr.length; i++) {
      if (cur + arr[i] < arr[i]) {
        cur = arr[i];
      } else {
        cur += arr[i];
      }
      max = Math.max(cur, max);
    }
    return max;
  }

  public static void main(String[] args) {
    int[] arr = {-2, -1, -5, -6, -3, -5, -8};
    System.out.println(maxSum(arr));
  }

}
