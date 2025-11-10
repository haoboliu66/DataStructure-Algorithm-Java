package com.hliu.fundamental.sorting.mergesort;

/*
https://leetcode.com/problems/count-of-range-sum/
1. merge sort
2. 手写有序表
 */
public class C04_CountOfRangeSum {

  /* 手写有序表也可解  */
  public static int countRangeSum(int[] arr, int lower, int upper) {

    return 0;
  }

  public static int process(int[] arr, int l, int r, int lower, int upper, int[] preSum) {
    if (l == r) {
      if (arr[l] >= lower && arr[l] <= upper) {
        return 1;
      }
      return 0;
    }
    int mid = l + ((r - 1) >>> 1);
    return process(arr, l, mid, lower, upper, preSum) +
        process(arr, mid + 1, r, lower, upper, preSum) +
        merge(arr, l, mid, r, lower, upper, preSum);
  }

  public static int merge(int[] arr, int l, int m, int r, int lower, int upper, int[] preSum) {
    int count = 0;
    int p1 = l, p2 = m + 1;

    return count;
  }


  public static int rightCountRangeSum(int[] arr, int lower, int upper) {
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
      int sum = 0;
      for (int j = i; j < arr.length; j++) {
        sum += arr[j];
        if (sum >= lower && sum <= upper) {
          count++;
        }
      }
    }
    return count;
  }

}
