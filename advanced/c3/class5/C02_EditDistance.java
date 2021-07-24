package advanced.c3.class5;

public class C02_EditDistance {

    /*
    https://leetcode.com/problems/edit-distance/

    Q: str1, str2, 三种操作对应三种代价insert, replace, delete, 求把str1变成str2最小的代价
     */

    public static int minCost1(String s1, String s2, int insert, int replace, int delete) {
        int M = s1.length() + 1;
        int N = s2.length() + 1;
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int[][] dp = new int[M][N];

        for (int i = 0; i < N; i++) {
            dp[0][i] = i;
        }

        for (int j = 0; j < M; j++) {
            dp[j][0] = j;
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + replace;
                }
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + delete);
                dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + insert);
            }
        }

        return dp[M - 1][N - 1];
    }


    public int minDistance(String word1, String word2) {

        if (word1.equals(word2)) return 0;
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();

        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        int M = str1.length, N = str2.length;

        int[][] dp = new int[M][N];
        dp[0][0] = str1[0] == str2[0] ? 0 : 1;

        for (int i = 1; i < M; i++) {
            if (str1[i] == str2[0]) {
                dp[i][0] = Math.min(i, dp[i - 1][0] + 1);
            } else {
                dp[i][0] = Math.min(i + 1, dp[i - 1][0] + 1);
            }
        }
        for (int j = 1; j < N; j++) {
            if (str2[j] == str1[0]) {
                dp[0][j] = Math.min(j, dp[0][j - 1] + 1);
            } else {
                dp[0][j] = Math.min(j + 1, dp[0][j - 1] + 1);
            }
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                // delete or insert
                dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);

                // possible replace
                if (str1[i] != str2[j]) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1] + 1);
                } else {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
                }

            }
        }

        return dp[M - 1][N - 1];
    }


}
