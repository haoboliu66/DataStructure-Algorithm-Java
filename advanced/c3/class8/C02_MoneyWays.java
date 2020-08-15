package advanced.c3.class8;

/**
 * @author andy-liu
 * @date 2020/7/16 - 12:19 PM
 */
public class C02_MoneyWays {

    public static int moneyWays(int[] arbitrary, int[] onlyone, int money) {
        if (money < 0) {
            return 0;
        }

        if ((arbitrary == null || arbitrary.length == 0) && (onlyone == null || onlyone.length == 0)) {
            return money == 0 ? 1 : 0;
        }

        int[][] dp1 = multipleCoin(arbitrary, money);
        int[][] dp2 = uniqueCoin(onlyone, money);

        if (dp1 == null) {
            return dp2[onlyone.length - 1][money];
        }
        if (dp2 == null) {
            return dp1[arbitrary.length - 1][money];
        }
        int res = 0;
        for (int i = 0; i <= money; i++) {
            // 普通币占掉 i, 纪念币占掉 money - i
            res += dp1[arbitrary.length - 1][i] * dp2[onlyone.length - 1][money - i];
        }

        return res;
    }

    // 只用普通货币的动态规划二维表
    public static int[][] multipleCoin(int[] arr, int money) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        int N = arr.length;
        int[][] dp = new int[N][money + 1];

        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j <= money; j++) {
            dp[0][j] = money % arr[0] == 0 ? 1 : 0;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= money; j++) {

                dp[i][j] = dp[i - 1][j];
                dp[i][j] = j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
//                for (int amount = 1; amount * arr[i] <= j; amount++) {
//                    dp[i][j] += dp[i][j - amount * arr[i]];
//                }
            }
        }
        return dp;
    }


    //只用纪念币的动态规划二维表(背包)
    public static int[][] uniqueCoin(int[] arr, int money) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][money + 1];

        for (int i = 0; i < N; i++) {
            dp[i][0] = 1;
        }

        for (int j = 1; j <= money; j++) {
            dp[0][j] = arr[0] == j ? 1 : 0;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= money; j++) {

                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i - 1][j - arr[i]] : 0;
            }
        }

        return dp;
    }


}
