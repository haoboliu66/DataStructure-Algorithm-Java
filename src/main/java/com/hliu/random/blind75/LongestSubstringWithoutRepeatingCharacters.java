package com.hliu.random.blind75;

public class LongestSubstringWithoutRepeatingCharacters {

  public static void main(String[] args) {

    System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abcabcbb"));
    System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("bbbbb"));
    System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("pwwkew"));
    System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("aab"));
  }

  public int lengthOfLongestSubstring(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    int left = 0, right = 0;
    int maxLen = 0;
    int[] charCountMap = new int[128];
    char[] str = s.toCharArray();
    int len = 0;

    for (; right < str.length; ) {
      if (charCountMap[str[right]] == 0) {
        charCountMap[str[right]]++;
        len++;
        right++;
      } else {
        // str[right] > 0, seeing duplicate
        maxLen = Math.max(maxLen, len);

        // shrink the substring from the left until the count is not duplicate
        while (left < right && charCountMap[str[right]] != 0) {
          charCountMap[str[left]]--;
          len--;
          left++;
        }

        // current str[right] index becomes valid after substring shrink, loop again to evaluate
      }
    }
    return Math.max(maxLen, len);
  }

}
