package com.hliu.fundamental.linear.array.presum;

/*

Given a string s of zeros and ones, return the maximum score after splitting the string into two non-empty substrings (i.e. left substring and right substring).

The score after splitting a string is the number of zeros in the left substring plus the number of ones in the right substring.

Input: s = "011101"
Output: 5

Input: s = "1111"
Output: 3

 */

public class Problem_1422_MaximumScoreAfterSplittingAString {

  /*
  如果在每一个index, 我们可以知道左侧有多少个0, 和右侧有多少个1, 就可以计算当前的score了
   */

  public int maxScore(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    char[] str = s.toCharArray();
    int max = -1;
    int totalZero = 0;
    int totalOne = 0;
    for (int i = 0; i < str.length; i++) {
      if (str[i] == '0') {
        totalZero++;
      } else {
        totalOne++;
      }
    }

    if (totalZero == 0 || totalOne == 0) { // all '0' or all '1'
      return str.length - 1;
    }

    int currZero = 0;
    int currOne = 0;
    for (int i = 0; i < str.length - 1; i++) { // the last index is excluded to guarantee 2 non-empty substrings
      if (str[i] == '0') {
        currZero++;
      } else {
        currOne++;
      }
      // at every index, we know: [the number of '0' on the left side] and [the number of '1' on the right side]
      max = Math.max(max, currZero + (totalOne - currOne));
    }
    return max;
  }

}
