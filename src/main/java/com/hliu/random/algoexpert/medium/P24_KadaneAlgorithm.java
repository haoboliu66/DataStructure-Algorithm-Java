package com.hliu.random.algoexpert.medium;

public class P24_KadaneAlgorithm {

  public static int kadanesAlgorithm(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }

    int n = arr.length;
    int max = Integer.MIN_VALUE;
    int sum = 0;
    for (int i = 0; i < n; i++) {
      sum += arr[i];
      max = Math.max(max, sum);
      if (sum < 0) {
        sum = 0;
      }
    }

    return max;
  }
}
