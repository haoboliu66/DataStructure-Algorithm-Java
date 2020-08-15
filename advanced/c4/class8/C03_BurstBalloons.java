package advanced.c4.class8;

/**
 * @author andy-liu
 * @date 2020/8/9 - 12:13 PM
 */
public class C03_BurstBalloons {

    /*
    312. Burst Balloons
     */

    public static int maxCoins(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int[] arr = new int[nums.length + 2];
        int N = arr.length;
        arr[0] = 1;
        arr[N - 1] = 1;
        for (int i = 1; i < N; i++) {
            arr[i] = nums[i - 1];
        }
        int[][] dp = new int[N][N];

        return process(arr, 1, N - 2, dp);
    }


    public static int process(int[] arr, int L, int R, int[][] dp) {
        if (dp[L][R] != 0) {
            return dp[L][R];
        }
        if (L == R) {
            return arr[L - 1] * arr[L] * arr[R + 1];
        }
        int max = Math.max(process(arr, L + 1, R, dp) + arr[L - 1] * arr[L] * arr[R + 1],
                process(arr, L, R - 1, dp) + arr[L - 1] * arr[R] * arr[R + 1]
        );
        for (int i = L + 1; i < R; i++) {
            max = Math.max(max, process(arr, L, i - 1, dp) + process(arr, i + 1, R, dp) + arr[L - 1] * arr[i] * arr[R + 1]);
        }
        dp[L][R] = max;
        return max;
    }

}
