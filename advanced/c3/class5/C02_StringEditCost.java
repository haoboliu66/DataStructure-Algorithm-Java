package advanced.c3.class5;

/**
 * @author andy-liu
 * @date 2020/7/7 - 12:37 PM
 */
public class C02_StringEditCost {

    /*
    LeetCode 72. Edit Distance
     */

    /*
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


}
