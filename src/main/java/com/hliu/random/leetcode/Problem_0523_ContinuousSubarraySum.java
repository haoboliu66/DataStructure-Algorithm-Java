package com.hliu.random.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem_0523_ContinuousSubarraySum {


  /*
  A good subarray is a subarray where:

    its length is at least two, and
    the sum of the elements of the subarray is a multiple of k.
   */

  public static void main(String[] args) {

    int[] arr = new int[] {1,3,6,0,9,6,9};
    int k = 7;
    System.out.println(checkSubarraySum(arr, k));
  }

  public static boolean checkSubarraySum(int[] arr, int k) {
    if (arr == null || arr.length < 2) {
      return false;
    }
    // preSum[i] = arr[0] + arr[1] + ... + arr[i-1];

    int[] preSum = new int[arr.length];
    preSum[0] = 0;
    for (int i = 1; i < arr.length; i++) {
      preSum[i] = arr[i] + preSum[i - 1];
    }
    System.out.println(Arrays.toString(preSum));

    Map<Integer, Integer> preSumMap = new HashMap<>();
    preSumMap.put(0, -1);
    for (int i = 1; i < preSum.length; i++) {
      if (preSumMap.containsKey(preSum[i])) {
        continue;
      }
      preSumMap.put(preSum[i], i);
    }

    int sum = arr[0];
    for (int i = 1; i < arr.length; i++) {
      sum += arr[i];
      if (i - 1 >= 0 && sum % k == 0) { // current sum is valid
        return true;
      }
      //    i-3, i-2, i-1, i
      int rest = sum % k;
      int target = sum - rest;
      if(preSumMap.containsKey(target)){
        Integer index = preSumMap.get(target);
        if(i - index >= 1) {
          return true;
        }
      }

    }
    return false;
  }

}
