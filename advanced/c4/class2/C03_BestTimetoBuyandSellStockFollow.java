package advanced.c4.class2;

public class C03_BestTimetoBuyandSellStockFollow {

    /*
    188. Best Time to Buy and Sell Stock IV - 限制k次transaction

    同时可解 123. Best Time to Buy and Sell Stock III - 限制2次transaction
     */

    /*
    dp[i][j]:  [0...i]上交易不超过j次, 得到的最大profit

    dp[i][j]
    [i]位置不参与交易 => dp[i-1][j]
    [i]参与交易,但是只作为卖出时机(贪心) => [0...i-1]分别作为买入时机
    (1) [0...i]只做k-1次交易, i位置买,i位置卖   dp[i][j] = dp[i][j-1] + [i] - [i]
    (2)
    for(int t = 0; t < i; t++){
        // 最后一次的买入时机在哪里
        dp[i][j] = dp[t][j-1] + [i] - [t];
    }
     */

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int n = prices.length;
        if (k > n / 2) return maxProfitWithNTransaction(prices);

        int[][] dp = new int[n][k + 1];

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j];

                for (int lastBuy = i; lastBuy >= 0; lastBuy--) {
                    dp[i][j] = Math.max(dp[i][j], dp[lastBuy][j - 1] + prices[i] - prices[lastBuy]);
                }
            }
        }

        return dp[n - 1][k];
    }

    public int maxProfitWithNTransaction(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            profit += prices[i] > prices[i - 1] ? prices[i] - prices[i - 1] : 0;
        }
        return profit;
    }

    // dp斜率优化
    public int maxProfit2(int k, int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int n = prices.length;
        if (k > n / 2) return maxProfitWithNTransaction(prices);

        int[][] dp = new int[n][k + 1];

        int t = 0;
        for (int j = 1; j <= k; j++) {

             t = dp[0][j-1] - prices[0];

            for (int i = 1; i < n; i++) {
                dp[i][j] = dp[i - 1][j];

               // if (t != 0) {
                    t = Math.max(t, dp[i][j - 1] - prices[i]);
                    dp[i][j] = Math.max(dp[i][j], t + prices[i]);
//                } else {
//                    for (int lastBuy = i; lastBuy >= 0; lastBuy--) {
//                        t = Math.max(t, dp[lastBuy][j - 1] - prices[lastBuy]);
//                        dp[i][j] = Math.max(dp[i][j], t + prices[i]);
//                    }
//                }

            }
        }

        return dp[n - 1][k];
    }


}
