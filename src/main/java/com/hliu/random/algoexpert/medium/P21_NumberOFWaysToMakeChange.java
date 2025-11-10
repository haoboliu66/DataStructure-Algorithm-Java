package com.hliu.random.algoexpert.medium;

public class P21_NumberOFWaysToMakeChange {

  public static int numberOfWaysToMakeChange(int n, int[] arr) {
    // Write your code here.
    return process(arr, 0, n);
  }

  public static int process(int[] arr, int index, int rest) {
    if (index == arr.length) {
      return rest == 0 ? 1 : 0;
    }
    if (rest < 0) {
      return 0;
    }

    int ways = 0;
    for (int i = 0; arr[index] * i <= rest; i++) {
      ways += process(arr, index + 1, rest - arr[index] * i);
    }

    return ways;
  }

}
