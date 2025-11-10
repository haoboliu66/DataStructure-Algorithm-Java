package com.hliu.random.algoexpert.veryhard;


/*
Write a function that takes in a string made up
of parentheses ( ( and ) ). The function
should return an integer representing the
length of the longest balanced substring with
regards to parentheses.
A string is said to be balanced if it has as
many opening parentheses as it has closing
parentheses and if no parenthesis is
unmatched. Note that an opening parenthesis
can't match a closing parenthesis that comes
before it, and similarly, a closing parenthesis
can't match an opening parenthesis that
comes after it.

Input: (()))(
Output: 4
 */
public class P34_LongestBalancedSubstring {

  public static void main(String[] args) {
    String s = "(()))(";
    System.out.println(longestBalancedSubstring(s));
  }

  public static int longestBalancedSubstring(String s) {
    char[] str = s.toCharArray();
    int[] dp = new int[str.length];
    int max = 0;
    for (int i = 0; i < str.length; i++) {
      int len = 0;
      if (i == 0 || str[i] == '(') {
        dp[i] = -1;
        continue;
      }

      if (str[i] == ')') {
        if (dp[i - 1] != -1) {  //      (.....() )
          len += dp[i - 1];
          int pre = i - dp[i - 1] - 1;
          if (pre >= 0 && str[pre] == '(') {
            len += 2;
            if (pre - 1 >= 0 && dp[pre - 1] != -1) {
              len += dp[pre - 1];
            }
          } else {
            len = -1;
          }

        } else if (str[i - 1] == '(') {
          len = 2;
          int pre = i - 2;
          if(pre >= 0 && dp[pre] != -1){
            len += dp[pre];
          }
        }
      }
      dp[i] = len;
      max = Math.max(max, dp[i]);
    }

    return max;
  }

}
