package sys.c22;

public class C02_MaximumSumOf3NonOverlappingSubarrays {


    public int[] maxSumOfThreeSubarrays(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = i == 0 ? arr[i] : Math.max(dp[i - 1], dp[i - 1] + arr[i]);
        }


        return null;
    }
}
