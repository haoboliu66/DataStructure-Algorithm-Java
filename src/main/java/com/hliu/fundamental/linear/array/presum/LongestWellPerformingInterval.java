package com.hliu.fundamental.linear.array.presum;

import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/problems/longest-well-performing-interval
 */
public class LongestWellPerformingInterval {

  public int longestWPI(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }

    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 0);
    for (int i = 0; i < arr.length; i++) {
      arr[i] = arr[i] > 8 ? 1 : -1;
    }
    // 字数组的和 严格 > 0 的最长字数组

    int sum = 0;
    int fast = 0, slow = 0;
    int lenMax = 0;
    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
      if (sum > 0) {
        lenMax = i + 1;
      } else {

        if (sum == 0) {
          slow = i;
        }
      }


    }

    return lenMax;
  }
}
