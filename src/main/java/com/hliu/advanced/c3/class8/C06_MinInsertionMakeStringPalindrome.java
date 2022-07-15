package com.hliu.advanced.c3.class8;

public class C06_MinInsertionMakeStringPalindrome {

  /*
  1312. Minimum Insertion Steps to Make a String Palindrome
   */
  public static int minInsertions(String s) {
    if (s == null || s.length() == 0 || s.length() == 1) {
      return 0;
    }

    if (s.length() == 2) {
      return s.charAt(0) == s.charAt(1) ? 0 : 1;
    }

    char[] str = s.toCharArray();
    int N = str.length;

    int[][] dp = new int[N][N];

    for (int i = 0; i < N; i++) {
      dp[i][i] = 0;
    }
    for (int i = 0; i < N - 1; i++) {
      dp[i][i + 1] = str[i] == str[i + 1] ? 0 : 1;
    }
    for (int i = N - 3; i >= 0; i--) {
      for (int j = i + 2; j < N; j++) {
        if (str[i] == str[j]) {
          dp[i][j] = dp[i + 1][j - 1];
        } else {
          dp[i][j] = 1 + Math.min(dp[i + 1][j], dp[i][j - 1]);
        }
      }
    }

    return dp[0][N - 1];
  }

  public static void main(String[] args) {
    String s = "abcbca";
    System.out.println(minInsertions(s));
  }
}
