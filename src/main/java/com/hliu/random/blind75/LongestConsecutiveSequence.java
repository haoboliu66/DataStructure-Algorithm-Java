package com.hliu.random.blind75;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {

  public int longestConsecutive0(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    Arrays.sort(nums);
    int maxLen = 0;
    int currLen = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] == nums[i - 1]) {
        continue;
      }
      if (nums[i] - nums[i - 1] == 1) {
        currLen++;
      } else {
        maxLen = Math.max(maxLen, currLen);
        currLen = 1;
      }
    }
    return Math.max(maxLen, currLen);
  }

  public int longestConsecutive(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    Map<Integer, Integer> lenMap = new HashMap<>();
    int maxLen = 1;
    for (int x : nums) {
      if (lenMap.containsKey(x)) {
        continue;
      }
      lenMap.put(x, 1); // starting with x length 1, ending with x length 1

      int prevLen = 0;
      if (lenMap.containsKey(x - 1)) {
        prevLen = lenMap.get(x - 1);
      }
      int postLen = 0;
      if (lenMap.containsKey(x + 1)) {
        postLen = lenMap.get(x + 1);
      }
      int totalLen = 1 + prevLen + postLen;
      lenMap.put(x - prevLen, totalLen);
      lenMap.put(x + postLen, totalLen);
      lenMap.put(x, totalLen);
      maxLen = Math.max(maxLen, totalLen);
    }

    return maxLen;
  }
}
