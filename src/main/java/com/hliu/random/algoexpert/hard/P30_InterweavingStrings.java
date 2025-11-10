package com.hliu.random.algoexpert.hard;

/*
Write a function that takes in three strings
and returns a boolean representing whether
the third string can be formed by interweaving the first two strings.
To interweave strings means to merge them
by alternating their letters without any specific
pattern. For instance, the strings "abc" and
"123" can be interwoven as "a1b2c3" , as
"abc123" , and as "ab1c23" (this list is nonexhaustive).
Letters within a string must maintain their
relative ordering in the interwoven string.

Input:
one = "algoexpert"
two = "your-dream-job"
three = "your-algodream-expertjob"

Output: true
 */

// https://leetcode.com/problems/interleaving-string/
public class P30_InterweavingStrings {

  public static void main(String[] args) {
    String one = "algoexpert";
    String two = "your-dream-job";
    String three = "your-algodream-expertjob";
    System.out.println(interweavingStrings(one, two, three));
  }

  public static boolean interweavingStrings(String one, String two, String three) {

    char[] str1 = one.toCharArray();
    char[] str2 = two.toCharArray();
    int M = str1.length;
    int N = str2.length;
    if (M + N != three.length()) {
      return false;
    }
    char[] str3 = three.toCharArray();
    // str1 i长度, str2 j长度 组成 str3 i+j长度
    boolean[][] dp = new boolean[M + 1][N + 1];
    dp[0][0] = true;

    for (int i = 1; i <= M; i++) {
      dp[i][0] = dp[i - 1][0] && str1[i - 1] == str3[i - 1];
    }
    for (int j = 1; j <= N; j++) {
      dp[0][j] = dp[0][j - 1] && str2[j - 1] == str3[j - 1];
    }

    for (int i = 1; i <= M; i++) {
      for (int j = 1; j <= N; j++) {
        dp[i][j] = dp[i - 1][j] && str1[i - 1] == str3[i + j - 1];
        dp[i][j] |= dp[i][j - 1] && str2[j - 1] == str3[i + j - 1];
      }
    }
    print(dp);
    return dp[M][N];
  }

  public static void print(boolean[][] m) {
    for (int i = 0; i < m.length; i++) {
      for (int j = 0; j < m[0].length; j++) {
        System.out.print(m[i][j] + " ");
      }
      System.out.println();
    }
  }


}

