package advanced.c3.class8;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class C02_MoneyWays {

    public static void main(String[] args) {
        int[] ordinary, special;
        int money;
        int len = 20, max = 100, times = 20000;
        System.out.println("Go");

        for (int i = 0; i < times; i++) {
            ordinary = generateArray(len, max);
            special = generateArray(len, max);
            money = (int) ((Math.random() + 0.1f) * max + len);

            int res1 = moneyWays0(ordinary, special, money);
            int res2 = moneyWaysZuo(ordinary, special, money);
            if (res2 != res1) {
//                System.out.println("res1: " + res1);
//                System.out.println("right: " + res2);
//                System.out.println("money: " + money);
//                System.out.println("ordinary: " + Arrays.toString(ordinary));
//                System.out.println("special: " + Arrays.toString(special));
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Done");
    }

    public static int moneyWays0(int[] ordinary, int[] special, int money) {
        int ways = 0;
        int[][] dpForOrdinary = new int[ordinary.length + 1][money + 1];
        for (int[] row : dpForOrdinary) {
            Arrays.fill(row, -1);
        }
        for (int firstPart = 0; firstPart <= money; firstPart++) {
            int p1 = process0WithOrdinary(ordinary, 0, firstPart, dpForOrdinary);
            int p2 = process0WithSpecial(special, 0, money - firstPart);
            ways += (p1 * p2);
        }
        return ways;
    }

    @Test
    public void testOrdinaryCoin() {
        int[] ordinary;
        int money;
        int len = 20, max = 100, times = 10000;
        System.out.println("Go");

        for (int i = 0; i < times; i++) {
            ordinary = generateArray(len, max);
            money = (int) (Math.random() * max + len);
            int[][] dp1 = getDpArb(ordinary, money);

            int[][] dpForOrdinary = new int[ordinary.length + 1][money + 1];
            for (int[] row : dpForOrdinary) {
                Arrays.fill(row, -1);
            }
            int res1 = process0WithOrdinary(ordinary, 0, money, dpForOrdinary);
            if (dp1[dp1.length - 1][money] != res1) {
//                System.out.println("money:" + money);
//                System.out.println(Arrays.toString(special));
//                System.out.println("res2: " + res1);
//                System.out.println(dp1[dp1.length - 1][money]);
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Done");
    }

    public static int process0WithOrdinary(int[] ordinary, int index, int rest, int[][] dp) {
        if (dp[index][rest] != -1) {
            return dp[index][rest];
        }
        if (index == ordinary.length) {
            dp[index][rest] = rest == 0 ? 1 : 0;
            return dp[index][rest];
        }
        int cur = ordinary[index];
        int ways = 0;

        for (int i = 0; cur * i <= rest; i++) {
            ways += process0WithOrdinary(ordinary, index + 1, rest - cur * i, dp);
        }
        dp[index][rest] = ways;
        return dp[index][rest];
    }

    @Test
    public void testSpecialCoin() {
        int[] special;
        int money;
        int len = 20, max = 200, times = 10000;
        System.out.println("Go");

        for (int i = 0; i < times; i++) {
            special = generateArray(len, max);
            money = (int) (Math.random() * max + len);
            int[][] dp2 = getDpOne(special, money);
            int res2 = process0WithSpecial(special, 0, money);

            if (dp2[dp2.length - 1][money] != res2) {
//                System.out.println("money:" + money);
//                System.out.println("special:" + Arrays.toString(special));
//                System.out.println("res2: " + res2);
//                System.out.println("dp2: " + dp2[dp2.length - 1][money]);
                System.out.println("Oops");
                break;
            }
        }
        System.out.println("Done");
    }

    // index.... 自由选择, 组成rest的方法数
    public static int process0WithSpecial(int[] special, int index, int rest) {
        if (rest < 0) return 0;
        if (index == special.length) {
            return rest == 0 ? 1 : 0;
        }
        int ways = 0;
        ways += process0WithSpecial(special, index + 1, rest);
        ways += process0WithSpecial(special, index + 1, rest - special[index]);
        return ways;
    }

    /* ------------------------------------  */

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

    public static int moneyWaysZuo(int[] arbitrary, int[] onlyone, int money) {
        if (money < 0) {
            return 0;
        }
        if ((arbitrary == null || arbitrary.length == 0) && (onlyone == null || onlyone.length == 0)) {
            return money == 0 ? 1 : 0;
        }
        // 任意张 的数组， 一张的数组，不可能都没有
        int[][] dparb = getDpArb(arbitrary, money);
        int[][] dpone = getDpOne(onlyone, money);
        if (dparb == null) { // 任意张的数组没有，一张的数组有
            return dpone[dpone.length - 1][money];
        }
        if (dpone == null) { // 任意张的数组有，一张的数组没有
            return dparb[dparb.length - 1][money];
        }
        int res = 0;
        for (int i = 0; i <= money; i++) {
            res += dparb[dparb.length - 1][i] * dpone[dpone.length - 1][money - i];
        }
        return res;
    }

    public static int[][] getDpArb(int[] arr, int money) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] dp = new int[arr.length][money + 1];
        // dp[i][j] 0..i券 自由选择张数， 搞定j元， 有多少方法？

        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        // [0] 5元 0元 5元 10元 15元 20元
        for (int j = 1; arr[0] * j <= money; j++) {
            dp[0][arr[0] * j] = 1;
        }
        // 0行 0列 填完了
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= money; j++) {
                dp[i][j] = dp[i - 1][j];

                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        }
        return dp;
    }

    public static int[][] getDpOne(int[] arr, int money) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] dp = new int[arr.length][money + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        if (arr[0] <= money) {
            dp[0][arr[0]] = 1;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= money; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i - 1][j - arr[i]] : 0;
            }
        }
        return dp;
    }


    // for test
    public static int[] generateArray(int len, int max) {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < len; i++) {
            int cur = (int) ((Math.random() + 0.1f) * max);
            set.add(cur);
        }
        int[] ans = new int[set.size()];
        int index = 0;
        for (int i : set) {
            ans[index++] = i;
        }
        return ans;
    }


}
