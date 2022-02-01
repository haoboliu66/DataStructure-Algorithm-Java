package src.main.java.advanced.c3.class3;

public class C02_SnacksWays {

    /*
    背包容量w, 一共n袋零食, 第i袋体积为v[i],总体积不超过背包容量情况下,
    一共有多少种零食放法, 总体积0也算一种
     */

    public static int ways1(int[] v, int w) {
        if (w < 0 || v == null || v.length == 0) {
            return 0;
        }
        return process(v, 0, w);
    }

    public static int process(int[] arr, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        // rest >= 0
        if (index == arr.length) {
            return 1;
        }

        int next1 = process(arr, index + 1, rest);
        int next2 = process(arr, index + 1, rest - arr[index]);

        return next1 + (next2 != -1 ? next2 : 0);
    }

    //  i....i 严格累加成j, 有多少种方案
    public static int ways2(int[] v, int w) {
        if (w < 0 || v == null || v.length == 0) {
            return 0;
        }
        int n = v.length;
        int[][] dp = new int[n + 1][w + 1];
        for (int j = 0; j <= w; j++) {
            dp[n][j] = 1;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= w; j++) {
                dp[i][j] = dp[i + 1][j] + (j - v[i] >= 0 ? dp[i + 1][j - v[i]] : 0);
            }
        }
        return dp[0][w];
    }

}
