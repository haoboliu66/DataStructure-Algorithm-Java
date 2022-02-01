package src.main.java.fundamental.recursion.dp;


public class MoneyChoice {

    // 322. Coin Change 是问张数最少的取法

    // 518. Coin Change 2 是问有多少种取法

    /*  Q: 一个数组中放不同面值的钱 [10,20,50,30] 每种可取任意数量, 给定目标aim = 1000, 问有多少种取法   */

    //暴力递归
    public static int getWays1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    public static int process1(int[] arr, int index, int rest) {
//        if (rest < 0) {   // rest在调用过程中不会小于0, 所以删除了
//            return 0;
//        }
        if (index == arr.length) {
            return rest == 0 ? 1 : 0;
        }
        //一个面值试 0 - N 张, N张面值总额不能超过rest
        int ways = 0;
        for (int num = 0; num * arr[index] <= rest; num++) {
            ways += process1(arr, index + 1, rest - num * arr[index]);
        }
        return ways;
    }


    /* 傻缓存 Memoization  */
    public static int getWays2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= aim; j++) {
                dp[i][j] = -1;
            }
        }
        return process2(arr, 0, aim, dp);
    }

    public static int process2(int[] arr, int index, int rest, int[][] dp) {
        //如果值没算过, dp[index][rest]值一定 == -1, 如果算过, 就不是-1
        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }
        if (index == arr.length) {
            dp[index][rest] = rest == 0 ? 1 : 0;
            return dp[index][rest];
        }
        int ways = 0;
        for (int num = 0; num * arr[index] <= rest; num++) {
            ways += process2(arr, index + 1, rest - num * arr[index], dp);
        }
        dp[index][rest] = ways;
        return dp[index][rest];
    }

    /* 二维表 DP 枚举行为  */
    public static int getWays3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1; //边界值
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {

                int ways = 0;
                for (int num = 0; num * arr[index] <= rest; num++) {    //枚举行为
                    ways += dp[index + 1][rest - num * arr[index]];
                }
                dp[index][rest] = ways;

            }
        }
        return dp[0][aim];
    }

    /* 枚举优化  */
    public static int getWays4(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1; //边界值
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= aim; rest++) {

                dp[index][rest] = dp[index + 1][rest];

                if (rest - arr[index] >= 0) {
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }
        return dp[0][aim];
    }


    public static void main(String[] args) {
        int[] arr = {10, 20, 50, 100};
        int aim = 1000;
        int ways1 = getWays1(arr, aim);
        System.out.println(ways1);
        int ways2 = getWays2(arr, aim);
        System.out.println(ways2);
        int ways3 = getWays3(arr, aim);
        System.out.println(ways3);
        int ways4 = getWays4(arr, aim);
        System.out.println(ways4);
    }
}
