package top.stockproblem;

public class Problem_0188_BestTimeToBuyAndSellStockIV {

    // 如果k > N/2, 等同于无限次交易

    public int maxProfit(int k, int[] prices) {

        int N = prices.length;
        int[][] dp = new int[N][k + 1];
        int max = Integer.MIN_VALUE;
//        for (int i = 1; i <= k; i++) {
//            dp[1][i] = prices[1] > prices[0] ? i * (prices[1] - prices[0]) : 0;
//            max = Math.max(max, dp[1][i]);
//        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= k; j++) {
                // [i]位置不参与交易
                dp[i][j] = dp[i - 1][j];

                // [i]位置参与, 需要枚举
                for (int p = 1; p <= i; p++) {

                    dp[i][j] = Math.max(dp[i - 1][j] + prices[i], dp[i - 1][j - 1] + prices[i]);

                }
            }
        }

        return dp[N - 1][k];
    }
}
