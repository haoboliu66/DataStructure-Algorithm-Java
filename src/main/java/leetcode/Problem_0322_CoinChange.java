package src.main.java.leetcode;

public class Problem_0322_CoinChange {

    /*
    https://www.youtube.com/watch?v=jgiZlGzXMBw
     */

    /*
    Top-Down Solution
     */
    public static int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) return -1;
        return process(coins, amount);
    }

    public static int process(int[] coins, int rest) {
        if (rest < 0) return -1;
        if (rest == 0) return 0;

        int p = Integer.MAX_VALUE;
        for (int coin : coins) {
            int next = process(coins, rest - coin);
            if (next != -1) {
                p = Math.min(p, 1 + next);
            }
        }
        return p != Integer.MAX_VALUE ? p : -1;
    }

    public static int coinChange2(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) return -1;
        int[] dp = new int[amount + 1];
        return process2(coins, amount, dp);
    }

    // rest [0...rest]
    public static int process2(int[] coins, int rest, int[] dp) {
        if (rest < 0) return -1;
        if (rest == 0) return 0;
        if (dp[rest] != 0) return dp[rest];

        int p = Integer.MAX_VALUE;
        for (int coin : coins) {
            int next = process2(coins, rest - coin, dp);
            if (next != -1) {
                p = Math.min(p, 1 + next);
            }
        }
        dp[rest] = p != Integer.MAX_VALUE ? p : -1;
        return dp[rest];
    }


    /*
    Bottom-Up Solution
     */
    public static int coinChange3(int[] coins, int amount) {
        if (coins == null || coins.length == 0 || amount < 0) return -1;
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int coin : coins) {
                // dp[i - coin] has a valid answer
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i - coin] + 1, dp[i]);
                }
            }
        }
        return dp[amount] != Integer.MAX_VALUE ? dp[amount] : -1;
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
//        int[] coins = {1};
        int amount = 11;
        int count = coinChange(coins, amount);
        System.out.println(count);
    }
}
