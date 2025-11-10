package com.hliu.random.algoexpert.medium;

public class P23_LevenshteinDistance {

  public static int levenshteinDistance(String s1, String s2) {
    if (s1 == null || s1.length() == 0) {
      return s2 != null && s2.length() != 0 ? s2.length() : 0;
    }

    if (s1.equals(s2)) {
      return 0;
    }

    char[] str1 = s1.toCharArray();
    char[] str2 = s2.toCharArray();
    int m = str1.length, n = str2.length;
    int[][] dp = new int[m][n];

    dp[0][0] = str1[0] == str2[0] ? 0 : 1; // 只能替换

    for (int i = 1; i < m; i++) {
      dp[i][0] = str1[i] == str2[0] ? i : Math.min(dp[i - 1][0] + 1, i + 1);
    }
    for (int j = 1; j < n; j++) {
      dp[0][j] = str2[j] == str1[0] ? j : Math.min(dp[0][j - 1] + 1, j + 1);
    }
    for (int i = 1; i < m; i++) {
      for (int j = 1; j < n; j++) {

        // 0.... i
        // 0.... j

        dp[i][j] = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);

        if (str1[i] == str2[j]) {
          dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i][j]);
        } else {
          // dp[i][j] = Math.min(dp[i-1][j] + 1, dp[i][j-1] + 1);
          dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, dp[i][j]);
        }

      }

    }

    return dp[m - 1][n - 1];
  }

}
