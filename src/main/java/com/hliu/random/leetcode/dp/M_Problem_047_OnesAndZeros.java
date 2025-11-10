package com.hliu.random.leetcode.dp;

public class M_Problem_047_OnesAndZeros {

  public int findMaxFormBF(String[] strs, int m, int n) {

    return 0;
  }

  public int process(String[] strs, int index, int count, int m, int n) {
    if (index == strs.length) {
      return m == 0 && n == 0 ? count : -1;
    }
    int p1 = process(strs, index + 1, count + 1, m, n);

    String cur = strs[index];
    int zero = getZero(cur);
    int one = getZero(cur);

    int p2 = process(strs, index + 1, count + 1, m - zero, n - one);

    return Math.max(p1, p2);

  }


  public int findMaxForm(String[] strs, int m, int n) {
    int len = strs.length;
    int[] zeros = getZeroArray(strs);
    int[] ones = getOneArray(strs);
    int[][] dp = new int[len][len];

    dp[0][0] = zeros[0] <= m && ones[0] <= n ? 1 : 0;

    for (int i = 0; i < dp.length; i++) {

      for (int j = 0; j < dp.length; j++) {

      }

    }

    return 0;
  }

  public int[] getZeroArray(String[] strs) {
    int[] zeros = new int[strs.length];
    for (int i = 0; i < strs.length; i++) {
      zeros[i] = getZero(strs[i]);
    }
    return zeros;
  }

  public int[] getOneArray(String[] strs) {
    int[] ones = new int[strs.length];
    for (int i = 0; i < strs.length; i++) {
      ones[i] = getOne(strs[i]);
    }
    return ones;
  }

  public int getZero(String s) {
    int count = 0;
    for (char c : s.toCharArray()) {
      if (c == '0') {
        count++;
      }
    }
    return count;
  }

  public int getOne(String s) {
    int count = 0;
    for (char c : s.toCharArray()) {
      if (c == '1') {
        count++;
      }
    }
    return count;
  }

}
