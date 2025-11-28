package com.hliu.fundamental.linear.slidingwindow;

public class SlidingWindowQuestions {

  // https://leetcode.com/problems/minimum-size-subarray-sum/
  public int minSubArrayLenBruteForce(int target, int[] nums) {
    int minLength = Integer.MAX_VALUE;
    for (int i = 0; i < nums.length; i++) {
      int curSum = 0;
      for (int j = i; j < nums.length; j++) {
        curSum += nums[j];
        if (curSum >= target) {
          minLength = Math.min(minLength, j - i + 1);
        }
      }
    }
    return minLength == Integer.MAX_VALUE ? 0 : minLength;
  }

  public int minSubArrayLen(int target, int[] nums) {
    int minLength = Integer.MAX_VALUE;
    int left = 0, right = 0;
    int windowSum = 0;

    for (; right < nums.length; ) {
      while (windowSum < target && right < nums.length) { // extend window until windowSum >= target
        windowSum += nums[right++];
      }
      while (windowSum >= target && left < right) { // shrink window to see if we can get a smaller width
        minLength = Math.min(minLength, right - left);
        windowSum -= nums[left++];
      }
    }
    return minLength == Integer.MAX_VALUE ? 0 : minLength;
  }

  // https://leetcode.com/problems/longest-substring-without-repeating-characters/
  public int lengthOfLongestSubstring(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    char[] str = s.toCharArray();
    int[] charCount = new int[128];
    int maxLength = 0;
    int left = 0, right = 0;

    for (; right < str.length; ) {
      while (right < str.length && charCount[str[right]] == 0) {
        charCount[str[right++]]++;
        maxLength = Math.max(maxLength, right - left);
      }
      // found duplicate char at str[right], so the current maxLength is [left....right-1]

      // shrink window from left until the duplicate char is removed
      while (right < str.length && charCount[str[right]] > 0) {
        charCount[str[left++]]--;
      }
    }
    return maxLength;
  }

  public static void main(String[] args) {
    SlidingWindowQuestions questions = new SlidingWindowQuestions();
    String s = "cabwefgewcwaefgcf", t = "cae";
    String res = questions.minWindow(s, t);
    System.out.println(res);
  }

  // https://leetcode.com/problems/minimum-window-substring/
  public String minWindow(String s, String t) {
    int[] targetCount = new int[128];
    int totalCount = t.length();
    for (char c : t.toCharArray()) {
      targetCount[c]++;
    }
    char[] str = s.toCharArray();
    int minLength = Integer.MAX_VALUE;
    String res = "";
    for (int left = 0, right = 0; right < str.length; ) {
      while (right < str.length && totalCount > 0) { // expand window until all chars in t are matched
        if (targetCount[str[right]] > 0) { // if matching char, decrease totalCount
          totalCount--;
        }
        targetCount[str[right]]--;
        right++;
      }
      while (left <= right && totalCount == 0) { // try to shrink window from left
        if (right - left < minLength) { // collect result if a smaller window is found
          minLength = right - left;
          res = s.substring(left, right);
        }
        targetCount[str[left]]++; // remove [left] from window
        if (targetCount[str[left]] > 0) {
          // means we are removing a needed char, as a needed char's count will be 0 before it's removed from window
          totalCount++;
        }
        left++;
      }
    }
    return res;
  }

  // https://leetcode.com/problems/gas-station/
  public int canCompleteCircuit(int[] gas, int[] cost) {

    return 0;
  }
}
