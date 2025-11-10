package com.hliu.fundamental.linear.slidingwindow;

public class Problem_1456_MaximumNumberOfVowelsInSubstringGivenLength {

  public int maxVowels(String s, int k) {
    char[] str = s.toCharArray();
    int max = 0;
    int left = 0, right = 0;
    int count = 0;
    for (; right < str.length; ) {
      if (isVowel(str[right])) {
        count++;
      }
      //  0 1 2 3
      if (right - left + 1 == k) {
        max = Math.max(max, count);
        if (isVowel(str[left])) {
          count--;
        }
        left++;
        right++;
      } else {
        right++;
      }
    }
    return max;
  }

  private boolean isVowel(char c) {
    return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
  }

}
