package com.hliu.random.leetcode;

public class M_Problem_0718_MaximumLengthOfRepeatedSubarray {

    /* The same as Longest Common Substring  */

    public int findLength(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int[][] dp = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            dp[i][0] = A[i] == B[0] ? 1 : 0;
            max = Math.max(max, dp[i][0]);
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = A[0] == B[j] ? 1 : 0;
            max = Math.max(max, dp[0][j]);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {

                if (A[i] == B[j]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max;
    }

    // memory-saving-1
    public int findLength2(int[] A, int[] B) {
        int m = A.length, n = B.length, max = 0;
        int[] dp = new int[n];
        /* combine this part into for loop */
//        for (int j = 0; j < n; j++) {
//            dp[j] = A[0] == B[j] ? 1 : 0;
//            max = Math.max(max, dp[j]);
//        }
        int i = 0, j = 0, tmp = 0;
        while (i != m) {
            for (j = 0; j < n; j++) {
                if(i == 0){
                    dp[j] = A[i] == B[j] ? 1 : 0;
                    continue;
                }
                if (j == 0) {
                    tmp = dp[j];
                    dp[j] = A[i] == B[j] ? 1 : 0;
                } else {
                    int cur = A[i] == B[j] ? 1 + tmp : 0;
                    tmp = dp[j];
                    dp[j] = cur;
                }
                max = Math.max(max, dp[j]);
            }
            i++;
        }
        return max;
    }

    public int findLength2_1(int[] A, int[] B) {
        int m = A.length, n = B.length, max = 0;
        int[] dp = new int[n];

        int i = 0, j = 0, tmp = 0;
        while (i != m) {
            for (j = 0; j < n; j++) {
                if (j == 0) {
                    tmp = dp[j];
                    dp[j] = A[i] == B[j] ? 1 : 0;
                } else {
                    int cur = A[i] == B[j] ? 1 + tmp : 0;
                    tmp = dp[j];
                    dp[j] = cur;
                }
                max = Math.max(max, dp[j]);
            }
            i++;
        }
        return max;
    }

}
