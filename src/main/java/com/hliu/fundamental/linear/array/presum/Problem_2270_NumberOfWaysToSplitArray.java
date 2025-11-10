package com.hliu.fundamental.linear.array.presum;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*

  You are given a 0-indexed integer array nums of length n.

  nums contains a valid split at index i if the following are true:

  The sum of the first i + 1 elements is greater than or equal to the sum of the last n - i - 1 elements.
  There is at least one element to the right of i. That is, 0 <= i < n - 1.
  Return the number of valid splits in nums.

  2 <= nums.length <= 105
  -105 <= nums[i] <= 105

 */
public class Problem_2270_NumberOfWaysToSplitArray {

  // 一定要考虑计算求和int可能会溢出的问题
  public int waysToSplitArray(int[] nums) {
    long total = 0;
    for (int x : nums) {
      total += x;
    }
    int res = 0;
    long leftSum = 0;
    for (int i = 0; i < nums.length - 1; i++) {
      leftSum += nums[i];
      long rightSum = total - leftSum;
      if (leftSum >= rightSum) {
        res++;
      }
    }
    return res;
  }


  // 对数器
  public int waysToSplitArray0(int[] arr) {
    int res = 0;
    long leftSum = 0;
    for (int i = 0; i < arr.length - 1; i++) {
      leftSum += arr[i];
      long rightSum = 0;
      for (int j = i + 1; j < arr.length; j++) {
        rightSum += arr[j];
      }
      if (leftSum >= rightSum) {
        res++;
      }
    }
    return res;
  }

  public int waysToSplit(int[] arr) {
    return (int) ((process(arr, arr[0], arr[0], 1)) % 1000000007);
  }

  public long process(int[] arr, int leftSum, int currSum, int index) {
    if (index == arr.length) {
      return 0;
    }
    int sum = currSum + arr[index];
    if (sum < leftSum) {
      return 0;
    }
    long midFinish = process(arr, sum, 0, index + 1);
    long midContinue = process(arr, leftSum, sum, index + 1);
    return midFinish + midContinue;
  }


  public List<String> stringMatching(String[] words) {
    Set<String> sets = new HashSet<>();
    for (String word : words) {
      sets.add(word);
    }
    Set<String> res = new HashSet<>();
    for (String word : words) {

      for (String x : sets) {
        if (!x.equals(word) && x.contains(word)) {
          res.add(word);
        }
      }
    }
    return new ArrayList<>(res);
  }

}
