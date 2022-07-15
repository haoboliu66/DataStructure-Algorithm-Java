package com.hliu.advanced.c3.class7;

import java.util.Arrays;

public class C02_SmallestUnFormedSum {

  // 背包 solution
  public static int unformedSum0(int[] arr) {
    int n = arr.length;
    int max = 0, min = Integer.MAX_VALUE;
    for (int i = 0; i < n; i++) {
      min = Math.min(arr[i], min);
      max += arr[i];
    }
    // range -> [min, max]
    // 0...i 自由选择, 是否可以组成[min...max]
    boolean[][] dp = new boolean[n][max + 1]; // 1...5 => [1,2,3,4,5]
    for (int j = min; j <= max; j++) {
      for (int i = 0; i < n; i++) {
        dp[i][j] = arr[i] == j;
      }
    }
    // dp[i][j] => dp[i-1][j] | dp[i-1][j-arr[i]]
    for (int i = 1; i < n; i++) {
      for (int j = min; j <= max; j++) {
        dp[i][j] |= dp[i - 1][j];
        if (j - arr[i] >= min) {
          dp[i][j] |= dp[i - 1][j - arr[i]];
        }
      }
    }
    for (int j = min; j <= max; j++) {
      if (!dp[n - 1][j]) {
        return j;
      }
    }
    return max + 1;
  }


  public static int unformedSum1(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int N = arr.length;
    int sum = 0;
    int min = Integer.MAX_VALUE;

    for (int i = 0; i < N; i++) {
      sum += arr[i];
      min = Math.min(min, arr[i]);
    }
    //dp[i][j]含义: arr中0...i能否刚好组成j
    boolean[][] dp = new boolean[N][sum + 1];
    for (int i = 0; i < N; i++) {
      dp[i][0] = true;
    }
    dp[0][arr[0]] = true;
    for (int i = 1; i < N; i++) {
      for (int j = 1; j <= sum; j++) {
        //1. 0...i-1就能组成j这个值了
        //2. 0...i-1能组成j-arr[i]这个值, 所以0...i一定能组成j这个值
        dp[i][j] =
            dp[i - 1][j]
                || (j - arr[i] >= 0 ? dp[i - 1][j - arr[i]] : false);
      }
    }

    // j从min开始一直到sum, 最后一行第一个为false的, 就是最小不可组成和
    for (int j = min; j <= sum; j++) {
      if (!dp[N - 1][j]) {
        return j;
      }
    }
    return sum + 1;
  }


  // 进阶, 如果数组中一定有1
  // O(N*logN)
  public static int unformedSum2(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    Arrays.sort(arr);
    int range = 1;
    //arr[0] == 1
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] <= range + 1) {
        range += arr[i];
      } else {
        return range + 1;
      }
    }
    return range + 1;
  }


  public static int[] generateArray(int len, int maxValue) {
    int[] res = new int[len];
    res[0] = 1;
    for (int i = 1; i != res.length; i++) {
      res[i] = (int) (Math.random() * maxValue) + 1;
    }
    return res;
  }

  public static void printArray(int[] arr) {
    for (int i = 0; i != arr.length; i++) {
      System.out.print(arr[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int len = 27, max = 30, times = 10000;
    int[] arr = generateArray(len, max);
    //printArray(arr);
    for (int i = 0; i < times; i++) {
      arr = generateArray(len, max);
      int res1 = unformedSum0(arr);
      int res2 = unformedSum1(arr);
      if (res1 != res2) {
        System.out.println("Oops");
        break;
      }
    }
    System.out.println("Done");
//        start = System.currentTimeMillis();
//        System.out.println(unformedSum2(arr));
//        end = System.currentTimeMillis();
//        System.out.println("cost time: " + (end - start) + " ms");
//        System.out.println("======================================");

  }
}
