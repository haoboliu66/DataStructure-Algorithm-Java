package com.hliu.random.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

public class Problem_0494_TargetSum {


  public int findTargetSumWaysMemo1(int[] nums, int target) {
    Map<Integer, Map<Integer, Integer>> memoMap = new HashMap<>();
    return processMemo1(nums, 0, 0, target, memoMap);
  }

  private int processMemo1(int[] arr, int index, int currSum, int target, Map<Integer, Map<Integer, Integer>> memoMap) {
    if (index == arr.length) {
      return currSum == target ? 1 : 0;
    }

    if (memoMap.containsKey(index)) {
      Map<Integer, Integer> sumMap = memoMap.get(index);
      if (sumMap.containsKey(currSum)) {
        return sumMap.get(currSum);
      }
    } else {
      memoMap.put(index, new HashMap<>());
    }

    int addNext = processMemo1(arr, index + 1, currSum + arr[index], target, memoMap);
    int subtractNext = processMemo1(arr, index + 1, currSum - arr[index], target, memoMap);

    memoMap.get(index)
        .put(currSum, addNext + subtractNext);

    return addNext + subtractNext;
  }


  public int findTargetSumWaysMemo(int[] nums, int target) {
    Map<String, Integer> memo = new HashMap<>();
    return processMemo(nums, 0, 0, target, memo);
  }

  private int processMemo(int[] arr, int index, int currSum, int target, Map<String, Integer> memo) {
    if (index == arr.length) {
      return currSum == target ? 1 : 0;
    }
    if (memo.containsKey(index + "_" + currSum)) {
      return memo.get(index + "_" + currSum);
    }

    int addNext = processMemo(arr, index + 1, currSum + arr[index], target, memo);
    int subtractNext = processMemo(arr, index + 1, currSum - arr[index], target, memo);

    memo.put(index + "_" + currSum, addNext + subtractNext);

    return addNext + subtractNext;
  }


  // recursive solution
  public int findTargetSumWays(int[] nums, int target) {
    return process(nums, 0, 0, target);
  }

  private int process(int[] arr, int index, int currSum, int target) {
    if (index == arr.length) {
      return currSum == target ? 1 : 0;
    }

    int addNext = process(arr, index + 1, currSum + arr[index], target);
    int subtractNext = process(arr, index + 1, currSum - arr[index], target);
    return addNext + subtractNext;
  }

}
