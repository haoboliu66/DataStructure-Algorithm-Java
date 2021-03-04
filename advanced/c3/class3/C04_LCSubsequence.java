package advanced.c3.class3;

public class C04_LCSubsequence {

/*
1143. Longest Common Subsequence

583. Delete Operation for Two Strings
可以转换成 求最长子序列

空间压缩
 */

    public static int getLCS(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int M = str1.length;
        int N = str2.length;
        int[][] dp = new int[M][N];

        dp[0][0] = str1[0] == str2[0] ? 1 : 0;
        for (int i = 1; i < M; i++) {
            dp[i][0] = str2[0] == str1[i] ? 1 : dp[i - 1][0];
        }

        for (int j = 1; j < N; j++) {
            dp[0][j] = str1[0] == str2[j] ? 1 : dp[0][j - 1];
        }
        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                //以i结尾，不以j结尾
                //以j结尾，不以i结尾
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);

                //不以i又不以j
                //以i又以j结尾
                if (str1[i] == str2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[M - 1][N - 1];
    }
}
