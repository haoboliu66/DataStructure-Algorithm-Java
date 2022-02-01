package src.main.java.leetcode;

public class Problem_0746_MinCostClimbingStairs {

    public static int minCostClimbingStairs1(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        return Math.min(process(cost, 0), process(cost, 1));
    }

    public static int process(int[] arr, int index) {
        if (index >= arr.length) {
            return 0;
        }
        int cost = 0;
        cost += arr[index];
        cost += Math.min(process(arr, index + 1), process(arr, index + 2));

        return cost;
    }

    // dp
    public static int minCostClimbingStairs2(int[] cost) {
        if (cost == null || cost.length == 0) {
            return 0;
        }
        int N = cost.length;
        int[] dp = new int[N + 1];
        dp[N] = 0;
        for (int i = N - 1; i >= 0; i--) {
            dp[i] += cost[i];
            dp[i] += Math.min(dp[i + 1], i + 2 <= N ? dp[i + 2] : 0);
        }

        return Math.min(dp[0], dp[1]);
    }


    public static void main(String[] args) {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(minCostClimbingStairs1(cost));
        System.out.println(minCostClimbingStairs2(cost));
    }


}
