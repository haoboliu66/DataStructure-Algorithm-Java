package com.hliu.big.c17;

import java.util.ArrayList;
import java.util.List;

public class C04_DistinctSubsequence {

  // https://leetcode.com/problems/distinct-subsequences/
  // 0....i 对应 0...j

  public static int equalSequenceDP(String s, String t) {
    char[] str1 = s.toCharArray();
    char[] str2 = t.toCharArray();
    int[][] dp = new int[str1.length][str2.length];
    dp[0][0] = str1[0] == str2[0] ? 1 : 0;
    for (int i = 1; i < str1.length; i++) {
      dp[i][0] = dp[i - 1][0] + (str1[i] == str2[0] ? 1 : 0);
    }
    for (int i = 1; i < str1.length; i++) {
      for (int j = 1; j < str2.length; j++) {
        dp[i][j] = dp[i - 1][j];
        if (str1[i] == str2[j]) {
          dp[i][j] += dp[i - 1][j - 1];
        }
      }
    }
    return dp[str1.length - 1][str2.length - 1];
  }


  public static int equalSequence(String s, String t) {
    List<String> set = new ArrayList<>();
    process(s.toCharArray(), 0, "", set);
    int res = 0;
    for (String each : set) {
      if (each.equals(t)) {
        res++;
      }
    }
    return res;
  }

  private static void process(char[] str, int i, String path, List<String> set) {
    if (i == str.length) {
      set.add(path);
      return;
    }
    process(str, i + 1, path, set);
    process(str, i + 1, path + str[i], set);
  }

  // for test
  public static void main(String[] args) {
    int len = 300, time = 20000;
    System.out.println("Go");
    for (int i = 0; i < time; i++) {
      String s = generateRandomString(len);
      String t = generateRandomString(len / 2);
      int ans1 = equalSequenceDP(s, t);
      int ans2 = numDistinct2(s, t);
      if (ans1 != ans2) {
        System.out.println("Oops");
        break;
      }
    }
    System.out.println("Done");
  }

  private static String generateRandomString(int len) {
    char[] str = new char[(int) ((Math.random() + 1) * len)];
    for (int i = 0; i < str.length; i++) {
      str[i] = (char) ((int) (Math.random() * 26 + 1) + 'a');
      // [0,1)  [0,26)
    }
    return new String(str);
  }


  // zuo
  public static int numDistinct2(String S, String T) {
    char[] s = S.toCharArray();
    char[] t = T.toCharArray();
    int[][] dp = new int[s.length + 1][t.length + 1];
    for (int j = 0; j <= t.length; j++) {
      dp[0][j] = 0;
    }
    for (int i = 0; i <= s.length; i++) {
      dp[i][0] = 1;
    }
    for (int i = 1; i <= s.length; i++) {
      for (int j = 1; j <= t.length; j++) {
        dp[i][j] = dp[i - 1][j] + (s[i - 1] == t[j - 1] ? dp[i - 1][j - 1] : 0);
      }
    }
    return dp[s.length][t.length];
  }

}
