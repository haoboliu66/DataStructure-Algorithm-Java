package src.main.java.sys.c10;

import org.junit.Test;

public class C03_KInversePairsArray {

    // dp[i][j] => 1...i形成j个逆序对

    public int kInversePairs(int n, int k) {
        if (n < 1) return 0;

        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (j >= i) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - i];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[n][k];
    }


    public int kInversePairs2(int n, int k) {
        if (n < 1) return 0;
        int mod = 1000000007;
        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {

                if (j >= i) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - i] + mod;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }

                dp[i][j] = (dp[i][j]) % mod;
            }
        }
        return (int) dp[n][k];
    }


    @Test
    public void test1() {
        int n = 3, k = 0;
        System.out.println(kInversePairs(n, k));
    }

}
