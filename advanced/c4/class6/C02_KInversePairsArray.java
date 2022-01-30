package advanced.c4.class6;


public class C02_KInversePairsArray {

    /*
    629. K Inverse Pairs Array
    逆序对问题
     */

    //样本做行做列模型
    public static int kInversePairs(int n, int k) {
        if (n < 1 || k < 0) {
            return 0;
        }
        if (k == 0) {
            return 1;
        }

        int[][] dp = new int[n + 1][k + 1];
        int mod = 1000000007;

        for (int i = 1; i <= n; i++) {
            dp[i][0] = 1;
        }

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                if (i > j) {
                    dp[i][j] = (dp[i - 1][j] % mod + dp[i][j - 1]) % mod;

                } else {
                    dp[i][j] = (dp[i][j - 1] + dp[i - 1][j] - dp[i - 1][j - i]) % mod;
                }
            }
        }

        return dp[n][k];
    }


    /*
    493. Reverse Pairs
     */

    public static int reversePairs(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        long[] nums = new long[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nums[i] = arr[i];
        }

        int pairs = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > 2 * nums[j]) {
                    pairs++;
                }

            }
        }
        return pairs;
    }



    /*
    373. Find K Pairs with Smallest Sums
     */


}
