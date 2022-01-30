package sys.c4;

public class C04_SubsequenceSumNoAdjacent {

    public static void main(String[] args) {
        int[] arr = {1, 2, 20, 4, 5, 6};
        System.out.println(sum(arr));
    }

    public static int sum(int[] arr) {
        int n = arr.length;
        int[] dp = new int[n];
        dp[0] = arr[0];
        dp[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(arr[i] + dp[i - 2], dp[i - 1]);
            dp[i] = Math.max(dp[i], arr[i]);
        }
        return dp[n - 1];
    }

}
