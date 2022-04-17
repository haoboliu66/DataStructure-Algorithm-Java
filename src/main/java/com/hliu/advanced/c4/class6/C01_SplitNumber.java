package src.main.java.advanced.c4.class6;

public class C01_SplitNumber {


    public static int ways(int n) {
        if (n < 1) {
            return 0;
        }
        return process(1, n);
    }

    public static int process(int pre, int rest) {

        if (rest == 0) {
            return 1;
        }

        if (pre > rest) {
            return 0;
        }
        // rest >= pre
        int ways = 0;
        for (int i = pre; i <= rest; i++) {
            // (1, 4) -->  (1,3), (2,2), (3,1), (4,0)
            ways += process(i, rest - i);
        }

        return ways;
    }

    public static int waysDP(int n) {

        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int pre = n; pre > 0; pre--) {
            for (int rest = pre; rest <= n; rest++) {

                for (int i = pre; i <= rest; i++) {
                    // (1, 4) -->  (1,3), (2,2), (3,1), (4,0)
                    dp[pre][rest] += dp[i][rest - i];
                }
            }
        }

        return dp[1][n];
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(ways(n));
        System.out.println(waysDP(n));
    }
}
