package com.hliu.random.leetcode;

import java.util.HashMap;

public class Problem_1027_LongestArithmeticSubsequence {

  public static int longestArithSeqLength(int[] arr) {

      if (arr == null || arr.length == 0) {
          return 0;
      }

    int N = arr.length;
    int len = 1;
    int max = 0;

    for (int i = 0; i < N - 1; i++) {

      int diff = arr[i + 1] - arr[i];
        if (diff <= 0) {
            continue;
        }
      len = 1;

      for (; ; ) {
        int j = i + 1;
        while (j < N && arr[j] - arr[j - 1] == diff) {
          len++;
          j++;
        }
        max = Math.max(max, len);
      }
    }

    return max;
  }


  public static int longestArithSeqLength2(int[] arr) {

      if (arr == null || arr.length == 0) {
          return 0;
      }

    int N = arr.length;
    int len = 1;
    int max = 0;

    int[] dp = new int[N];

    HashMap<Integer, Integer> map = new HashMap<>();
    dp[0] = 1;
    for (int i = 1; i < N; i++) {

    }

    return max;
  }

}
