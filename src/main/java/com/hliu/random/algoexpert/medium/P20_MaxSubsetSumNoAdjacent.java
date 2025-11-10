package com.hliu.random.algoexpert.medium;

public class P20_MaxSubsetSumNoAdjacent {

  public static int maxSubsetSumNoAdjacent(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    if (arr.length == 1) {
      return arr[0];
    }
    int[] dp = new int[arr.length];
    dp[0] = arr[0];
    dp[1] = Math.max(arr[0], arr[1]);

    for (int i = 2; i < arr.length; i++) {
      dp[i] = Math.max(arr[i] + dp[i - 2], dp[i - 1]);
    }
    int max = 0;
    for (int i : dp) {
      max = Math.max(i, max);
    }
    return max;
  }

}
